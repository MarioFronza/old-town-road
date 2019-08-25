package br.udesc.dsd.rmts.model;

public class RoadItem {

    private String imagePath;
    private boolean isEntryPoint;
    private boolean isExitPoint;
    private int direction;
    private Car car;

    public RoadItem() {
        this.isEntryPoint = false;
        this.isExitPoint = false;
        this.car = null;
        this.direction = 0;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
        setImagePath(car.getImagePath());
    }
}
