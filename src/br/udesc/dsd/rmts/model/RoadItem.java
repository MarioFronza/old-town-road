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

    public void addCar(Car car) {
        this.car = car;
        setImageByDirection();
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
            assert mutex.availablePermits() == 1 : "Mutex deveria ser igual a 1";
            this.mutex.acquire();
            if (this.car != null)
                throw new Exception("Tem carro");

            assert mutex.availablePermits() == 0 : "Mutex deveria ser igual a 0";
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void releaseRoadItem() {
        assert mutex.availablePermits() == 0 : "Mutex deveria ser igual a 0";
        this.mutex.release();
        if (this.car == null)
            try {
                throw new Exception("n tem carro");
            } catch (Exception e) {
                e.printStackTrace();
            }
        assert mutex.availablePermits() == 1 : "Mutex deveria ser igual a 1";
    }

    public void setImageByDirection() {
        if (direction > 4) {
            setImagePath("assets/stone.png");
            if (direction == 5 || direction == 9 || direction == 10)
                setImagePath("assets/" + car.getColor() + "car" + 1 + ".png");

            if (direction == 6 || direction == 11)
                setImagePath("assets/" + car.getColor() + "car" + 2 + ".png");

            if (direction == 7 || direction == 12)
                setImagePath("assets/" + car.getColor() + "car" + 3 + ".png");

            if (direction == 8)
                setImagePath("assets/" + car.getColor() + "car" + 4 + ".png");
        } else {
            setImagePath("assets/" + car.getColor() + "car" + this.direction + ".png");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
}
