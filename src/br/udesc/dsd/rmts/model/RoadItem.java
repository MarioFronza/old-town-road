package br.udesc.dsd.rmts.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Road item abstract class
 *
 * @author João Pedro Schmitz, Mário Fronza
 * @version 1.0.0
 */
public abstract class RoadItem {

    protected Semaphore semaphore;
    protected String imagePath;
    protected boolean isEntryPoint;
    protected boolean isExitPoint;
    protected int direction;
    protected Car car;
    protected int x;
    protected int y;

    public RoadItem(int x, int y) {
        this.car = null;
        this.direction = 0;
        this.x = x;
        this.y = y;
        this.semaphore = new Semaphore(1);
    }

    public abstract boolean tryAcquire();

    public abstract void release();

    public abstract void addCar(Car car);

    public abstract void removeCar();

    public void setImageByDirection() {
        if (direction > 4) {
            setImagePath("assets/stone.png");
            if (direction == 5)
                setImagePath("assets/" + car.getColor() + "car" + 1 + ".png");

            if (direction == 6)
                setImagePath("assets/" + car.getColor() + "car" + 2 + ".png");

            if (direction == 7)
                setImagePath("assets/" + car.getColor() + "car" + 3 + ".png");

            if (direction == 8)
                setImagePath("assets/" + car.getColor() + "car" + 4 + ".png");

            if (direction == 9) {
                if (car.getCurrentRoad().getDirection() <= 4) {
                    setImagePath("assets/" + car.getColor() + "car" + 1 + ".png");
                } else {
                    setImagePath("assets/" + car.getColor() + "car" + 2 + ".png");
                }
            }

            if (direction == 10) {
                if (car.getCurrentRoad().getDirection() <= 4) {
                    setImagePath("assets/" + car.getColor() + "car" + 4 + ".png");
                } else {
                    setImagePath("assets/" + car.getColor() + "car" + 1 + ".png");
                }
            }

            if (direction == 11) {
                if (car.getCurrentRoad().getDirection() <= 4) {
                    setImagePath("assets/" + car.getColor() + "car" + 2 + ".png");
                } else {
                    setImagePath("assets/" + car.getColor() + "car" + 3 + ".png");
                }
            }

            if (direction == 12) {
                if (car.getCurrentRoad().getDirection() <= 4) {
                    setImagePath("assets/" + car.getColor() + "car" + 3 + ".png");
                } else {
                    setImagePath("assets/" + car.getColor() + "car" + 4 + ".png");
                }
            }
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
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

    public Car getCar() {
        return car;
    }

}
