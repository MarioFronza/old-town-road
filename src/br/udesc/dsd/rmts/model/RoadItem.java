package br.udesc.dsd.rmts.model;

public class RoadItem {

    private String imagePath;
    private boolean isEntryPoint;
    private boolean isExitPoint;

    public RoadItem() {
        this.isEntryPoint = false;
        this.isExitPoint = false;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isEntryPoint() {
        return isEntryPoint;
    }

    public void setEntryPoint(boolean entryPoint) {
        isEntryPoint = entryPoint;
    }

    public boolean isExitPoint() {
        return isExitPoint;
    }

    public void setExitPoint(boolean exitPoint) {
        isExitPoint = exitPoint;
    }
}
