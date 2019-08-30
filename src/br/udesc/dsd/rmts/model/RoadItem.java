package br.udesc.dsd.rmts.model;


import br.udesc.dsd.rmts.controller.MeshController;

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

    public void setCar(Car car) {
        this.car = car;
        this.car.setImagePath("assets/car" + direction + ".png");
        setImagePath(car.getImagePath());
    }

    public void addCart(int x, int y) {
        setCar(new Car());
    }

    public void alocar() {
        try {
            this.mutex.acquire();
            //veriricar permicao
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void liberar(){
        this.mutex.release();
    }

    public void removeCart() {
        this.car = null;
        this.car.setImagePath("assets/car" + direction + ".png");
        setImagePath(car.getImagePath());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
