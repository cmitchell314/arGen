

public class StentParam {

    private double outerDiameter, wallThickness, length, meshAngle, meshWidth, ringHeight;
    private int meshPillarCount;
    private String filename;

    public StentParam() {

    }

    public void setOuterDiameter(double outerDiameter) {
        this.outerDiameter = outerDiameter;
    }

    public void setWallThickness(double wallThickness) {
        this.wallThickness = wallThickness;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setMeshAngle(double meshAngle) {
        this.meshAngle = meshAngle;
    }

    public void setMeshWidth(double meshWidth) {
        this.meshWidth = meshWidth;
    }

    public void setName(String filename) {
        this.filename = filename;
    }

    public boolean setMeshPillarCount(int meshPillarCount) {
        this.meshPillarCount = meshPillarCount;
        return true;
    }

    public void setRingHeight(double ringHeight) {
        this.ringHeight = ringHeight;
    }

    public double getOuterDiameter() {
        if (outerDiameter == 0) {
            return -1;
        } else {
            return outerDiameter;
        }
    }

    public double getWallThickness() {
        if (wallThickness == 0) {
            return -1;
        } else {
            return wallThickness;
        }
    }

    public double getLength() {
        if (length == 0) {
            return -1;
        } else {
            return length;
        }
    }

    public double getMeshAngle() {
        if (meshAngle == 0) {
            return -1;
        } else {
            return meshAngle;
        }
    }

    public double getMeshWidth() {
        if (meshWidth == 0) {
            return -1;
        } else {
            return meshWidth;
        }
    }

    public int getMeshPillarCount() {
        if (meshPillarCount == 0) {
            return -1;
        } else {
            return meshPillarCount;
        }
    }

    public String getFilename() {
        if (filename == null) {
            return null;
        } else {
            return filename;
        }
    }

    public double getRingHeight() {
        if (ringHeight == 0) {
            return -1;
        } else {
            return ringHeight;
        }
    }

    public boolean isValid() {
        if (getOuterDiameter() == -1 || getWallThickness() == -1 || getLength() == -1 || getMeshAngle() == -1 || getMeshWidth() == -1 || getMeshPillarCount() == -1 || getRingHeight() == -1) {
            return false;
        } else return true;
    }


}