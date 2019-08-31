package br.udesc.dsd.rmts.model;

import java.util.concurrent.Semaphore;

public class RoadItem {

    private String imagePath;
    private boolean isEntryPoint;
    private boolean isExitPoint;
    private int direction;
    private Car car;
    private int x;
    private int y;
    private Semaphore mutex;

    public RoadItem(int x, int y) {
        this.isEntryPoint = false;
        this.isExitPoint = false;
        this.car = null;
        this.direction = 0;
        this.x = x;
        this.y = y;
        this.mutex = new Semaphore(1);
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

    public void addCar(Car car) {
        this.car = car;
        if (direction > 4) {
            setImagePath("assets/car" + 1 + ".png");
        } else {
            setImagePath("assets/car" + this.direction + ".png");
        }
    }

    public void removeCar() {
        this.car = null;
        if (direction > 4) {
            setImagePath("assets/stone.png");
        } else {
            setImagePath("assets/road" + this.direction + ".png");
        }
    }

    public void acquireRoadItem() {
        try {
            this.mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void releaseRoadItem() {
        this.mutex.release();
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
