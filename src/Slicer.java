import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Slicer {

    private static Slicer slicer;
    private StentParam params;
    private File outputGCodeFile;
    private FileOutputStream fos;
    private BufferedWriter writer;
    private final String fileDirectory = "C:\\Users\\conno\\Documents\\arGenOutput\\";

    private double ePos, zPos, rPos, thetaPos, fRate;

    private Slicer() {

    }

    static public Slicer getInstance() {
        if (slicer == null) {
            slicer = new Slicer();
        }
        return slicer;
    }

    //return -- if stent can successfully be generated
    public boolean generateStent(StentParam params) {
        this.params = params;
        if (params.isValid()) {
            try {
                outputGCodeFile = new File(fileDirectory + params.getFilename() + ".gcode");
                if (outputGCodeFile.createNewFile()) {
                } else {
                    System.out.println("File name not sufficiently unique");
                }

                fos = new FileOutputStream(outputGCodeFile);
                writer = new BufferedWriter(new OutputStreamWriter(fos));
                resetVals();
                initialize();
                skirt();
                lowerRingLayer();
                meshLayer();
                upperRingLayer();
                printTermination();
                writer.close();
                

                return true;
            } catch (IOException e) {
                System.out.println("File output failed");
                return false;
            }
        } else {
            return false;
        }
    }

    private void resetVals() {
        ePos = 0;
        zPos = 0;
        thetaPos = 0;
        rPos = 0;
    }

    private void initialize() throws IOException {
        writer.write(";Flavor: Pseudo-Marlin");
        writer.newLine();
        writer.write(";Layer Height: " + Constants.LAYER_HEIGHT);
        writer.newLine();

        writer.write("M140 S" + Constants.BED_TEMP);
        writer.newLine();
        writer.write("M105");
        writer.newLine();
        writer.write("M190 S" + Constants.BED_TEMP);
        writer.newLine();
        writer.write("M104 S" + Constants.NOZZLE_TEMP);
        writer.newLine();
        writer.write("M105");
        writer.newLine();
        writer.write("M109 S" + Constants.NOZZLE_TEMP);
        writer.newLine();
        writer.write("M82");
        writer.newLine();
        writer.write("G21");
        writer.newLine();
        writer.write("G90");
        writer.newLine();
        writer.write("M82");
        writer.newLine();
        writer.write("M107");
        writer.newLine();

        //home the theta axis
        writer.write("G28 X0");
        writer.newLine();

        //home the r axis
        writer.write("G28 Y0");
        writer.newLine();

        //home the z-axis
        writer.write("G28 Z0");
        writer.newLine();

        fRate = 1000;
        writer.write("G1 Z15.0 F" + fRate);
        writer.newLine();
        writer.write("G92 E0");
        writer.newLine();
        writer.write("G1 F200 E3");
        writer.newLine();
        writer.write("G92 E0");
        writer.newLine();
        fRate = 3000;
        writer.write("G1 F" + fRate);
        writer.newLine();
        writer.write("M117 Printing...");
        writer.newLine();

        //retracitng filament to prevent ooze
        writer.write("G92 E0");
        writer.newLine();
        fRate = 1500;
        writer.write("G1 F" + fRate + " E-3");
        writer.newLine();

        //resetting e pos
        writer.write("G92 E0");
        writer.newLine();

        //setting max accel
        writer.write("M107");
        writer.newLine();
        writer.write("M204 S" + Constants.MAX_ACCEL);
        writer.newLine();

        //move to center
        fRate = 3000;
        writer.write("G0 F" + fRate + " X0 Y" + Constants.R_CENTER_POS + " Z" + Constants.LAYER_HEIGHT);
        writer.newLine();

        //set r pos 0 at center of plate
        writer.write("G92 Y0");
        writer.newLine();

        //reset other positions;
        writer.write("G92 X0 E0");
        writer.newLine();
        ePos = 0;
        rPos = 0;
        thetaPos = 0;
        zPos = Constants.LAYER_HEIGHT;
    }

    private void skirt() throws IOException {
        rPos = (params.getOuterDiameter() / 2) + 5;
        fRate = 1500;

        //move to inner skirt line
        writer.write("G1 F" + fRate + " Y" + rPos);
        writer.newLine();
        absThetaPos(360, true);
        rPos += Constants.NOZZLE_DIAM;
        absRPos(rPos + Constants.NOZZLE_DIAM, true);
        absThetaPos(0, true);
        absZPos(1);
    }

    private void lowerRingLayer() throws IOException {
        for (int i = 0; i < params.getRingHeight(); i += Constants.LAYER_HEIGHT) {
            //moving to correct location for outer loop
            absThetaPos(0, false);
            absRPos((params.getOuterDiameter() - Constants.NOZZLE_DIAM)/2, false);
            absZPos(i + Constants.LAYER_HEIGHT);
            absThetaPos(360, true);

            for (double j = (params.getOuterDiameter()/2) - params.getWallThickness() ; j < (params.getOuterDiameter()/2) - Constants.NOZZLE_DIAM ; j += 0.95 * Constants.NOZZLE_DIAM) {
                absRPos(j + (Constants.NOZZLE_DIAM/2), true);
                absThetaPos((((j-(params.getOuterDiameter()/2)+params.getWallThickness())/(0.95 * Constants.NOZZLE_DIAM)) % 2 == 0) ? 0 : 360, true);
            }
        }
    }

    private void meshLayer() {

    }

    private void upperRingLayer() {

    }

    private void printTermination() {

    }

    private double rMovementExtrusion(double rShift) {
        return (4 * Constants.LAYER_HEIGHT * Constants.NOZZLE_DIAM * Math.abs(rShift)) / (Math.pow(Constants.FILAMENT_DIAMETER, 2));
    }

    private double thetaMovementExtrusion(double thetaShift, double rPos) {
        return (4 * Constants.LAYER_HEIGHT * Constants.NOZZLE_DIAM / Math.pow(Constants.FILAMENT_DIAMETER, 2)) * Math.abs(thetaShift * Math.PI * rPos / 180);
    }

    private void absMovementG1(double theta, double r, double z, double e) throws IOException{
        String command = "G1 ";
        if (theta != thetaPos) {
            thetaPos = theta;
            command += "X" + thetaPos + " ";
        }
        if (r != rPos) {
            rPos = r;
            command += "Y" + rPos + " ";
        }
        if (z != zPos) {
            zPos = z;
            command += "Z" + zPos + " ";
        }
        if (e != ePos) {
            ePos = e;
            command += "E" + ePos + " ";
        }
        writer.write(command);
        writer.newLine();
    }

    private void absRPos(double r, boolean extrude) throws IOException {
        if (extrude) {
            ePos += rMovementExtrusion(r-rPos);
        }
        writer.write("G1 Y" + r + ((extrude) ? " E" + ePos : ""));
        writer.newLine();
        rPos = r;
    }

    private void absThetaPos(double theta, boolean extrude) throws IOException {
        if (extrude) {
            ePos += thetaMovementExtrusion(theta-thetaPos, rPos);
        }
        writer.write("G1 X" + theta + ((extrude) ? " E" + ePos : ""));
        writer.newLine();
        thetaPos = theta;
    }

    private void absZPos(double z) throws IOException {
        writer.write("G1 Z" + z);
        writer.newLine();
        zPos = z;
    }
}
