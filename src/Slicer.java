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
                initialize();
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



    }

    private void lowerRingLayer() {

    }

    private void meshLayer() {

    }

    private void upperRingLayer() {

    }

    private void printTermination() {


    }

}
