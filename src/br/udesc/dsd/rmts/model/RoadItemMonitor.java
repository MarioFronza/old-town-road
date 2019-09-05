package br.udesc.dsd.rmts.model;


public class RoadItemMonitor extends RoadItem {


    public RoadItemMonitor(int x, int y) {
        super(x, y);
    }

    public synchronized void addCar(Car car) {
        try {
            while (super.car != null) {
                wait();
            }
            super.car = car;
            setImageByDirection();
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void removeCar() {

        try {
            while (super.car == null) {
                wait();
            }
            super.car = null;
            if (direction > 4) {
                setImagePath("assets/stone.png");
            } else {
                setImagePath("assets/road" + super.direction + ".png");
            }
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
