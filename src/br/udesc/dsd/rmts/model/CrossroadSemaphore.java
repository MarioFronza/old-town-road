package br.udesc.dsd.rmts.model;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Crossroad implementation with semaphore 
 *
 * @author João Pedro Schmitz, Mário Fronza
 * @version 1.0.0
 */
public class CrossroadSemaphore extends RoadItem {

    private Semaphore mutex;
    private Semaphore full;
    private Semaphore free;

    public CrossroadSemaphore(int x, int y) {
        super(x, y);
        full = new Semaphore(0);
        free = new Semaphore(1);
        mutex = new Semaphore(1);
    }
    
    public void tryToEnterCrossRoad(ArrayList<RoadItem> positions) {
    	for (int i = 0; i < positions.size(); i++) {
    		
    	}
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
