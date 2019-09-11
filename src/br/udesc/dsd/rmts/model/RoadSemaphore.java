package br.udesc.dsd.rmts.model;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Road implementation with semaphore
 *
 * @author João Pedro Schmitz, Mário Fronza
 * @version 1.0.0
 */
public class RoadSemaphore extends RoadItem {

    private Semaphore mutex;
    private Semaphore full;
    private Semaphore free;

    public RoadSemaphore(int x, int y) {
        super(x, y);
        full = new Semaphore(0);
        free = new Semaphore(1);
        mutex = new Semaphore(1);
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

    public void addCar(Car car) {
        try {
            free.acquire();
            mutex.acquire();
            super.car = car;
            setImageByDirection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release();
            full.release();
        }
    }

    public void removeCar() {
        try {
            full.acquire();
            mutex.acquire();
            super.car = null;
            if (direction > 4) {
                setImagePath("assets/stone.png");
            } else {
                setImagePath("assets/road" + super.direction + ".png");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release();
            free.release();
        }
    }

}
