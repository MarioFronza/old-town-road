package br.udesc.dsd.rmts.model;


import java.util.concurrent.TimeUnit;

/**
 * Road implementation with monitor
 *
 * @author João Pedro Schmitz, Mário Fronza
 * @version 1.0.0
 */
public class RoadMonitor extends RoadItem {


    public RoadMonitor(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean tryAcquire() {
        boolean acquired = false;
        try {
            acquired = this.semaphore.tryAcquire(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return acquired;
    }

    @Override
    public void release() {
        this.semaphore.release();
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
