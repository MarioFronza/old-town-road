package br.udesc.dsd.rmts.model;

import br.udesc.dsd.rmts.controller.IMeshController;
import br.udesc.dsd.rmts.controller.MeshController;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Car thread class
 *
 * @author João Pedro Schmitz, Mário Fronza
 * @version 1.0.0
 */
public class Car implements Runnable {

    private Queue<RoadItem> route;
    private RoadItem matrix[][];
    private int currentX;
    private int currentY;
    private Random random;
    private RoadItem currentRoad;
    private IMeshController meshController;
    private String color;
    private int velocity;
    private int amountOfChoices;

    public Car(int type) {
        this.route = new LinkedList<>();
        this.meshController = MeshController.getInstance();
        this.matrix = meshController.getMatrix();
        this.currentRoad = null;
        this.amountOfChoices = 0;
        this.random = new Random();
        this.velocity = random.nextInt(100) + 200;
        switch (type) {
            case 0:
                this.color = "red";
                break;
            case 1:
                this.color = "blue";
                break;
            case 2:
                this.color = "green";
                break;
        }
    }

    @Override
    public void run() {
        boolean free = false;
        Queue<RoadItem> positions = new LinkedList<>();
        List<Semaphore> semaphores = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            semaphores.add(new Semaphore(1));
            semaphores.get(i).tryAcquire();
        }

        while (!route.isEmpty()) {
            boolean moved = false;
            RoadItem item = route.remove();
            if (item.direction <= 4) {
                free = false;
            }
            try {
                Thread.sleep(velocity);
            } catch (Exception ie) {
                ie.printStackTrace();
            }
            do {
                Random rand = new Random();
                try {

                    if (item.direction > 4 && !free) {
                        positions.add(item);
                        for (RoadItem roadItem : route) {
                            if (roadItem.direction > 4) {
                                if (!positions.contains(roadItem))
                                    positions.add(roadItem);
                            } else {
                                break;
                            }
                        }
                        int amountAcquired = 0;
                        int sz = positions.size();
                        for (int i = 0; i < positions.size(); i++) {
                            boolean acquired = semaphores.get(i).tryAcquire(500, TimeUnit.MILLISECONDS);
                            if (acquired) {
                                amountAcquired++;
                            }
                        }
                        if (amountAcquired == sz) {
                            free = true;
                        } else {
                            free = false;
                            for (int i = 0; i < positions.size(); i++) {
                                semaphores.get(i).release();
                            }
                            positions.clear();
                            Thread.sleep(200 + rand.nextInt(400));
                        }
                        moved = false;
                    } else {
                        this.meshController.addCar(this, item.getX(), item.getY());
                        this.meshController.removeCar(currentRoad.getX(), currentRoad.getY());
                        this.currentRoad = item;
                        for (int i = 0; i < positions.size(); i++) {
                            semaphores.get(i).release();
                        }
                        positions.clear();
                        moved = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        Thread.sleep(100);
                    } catch (Exception ie) {
                        ie.printStackTrace();
                    }
                }
            } while (!moved);
        }
        this.meshController.removeThread(currentRoad.getCar());
        this.meshController.removeCar(currentRoad.getX(), currentRoad.getY());
    }

    public void defineRoute(int x, int y) {
        boolean isExitPoint = false;
        this.currentX = x;
        this.currentY = y;

        while (!isExitPoint) {
            switch (matrix[currentX][currentY].getDirection()) {
                case 1:
                    this.currentX--;
                    break;
                case 2:
                    this.currentY++;
                    break;
                case 3:
                    this.currentX++;
                    break;
                case 4:
                    this.currentY--;
                    break;
                default:
                    chooseCrossroad(matrix[currentX][currentY].getDirection());
                    break;
            }
            route.add(matrix[currentX][currentY]);
            if (matrix[currentX][currentY].isExitPoint())
                isExitPoint = true;
        }
    }

    private void chooseCrossroad(int direction) {
        Random random = new Random();
        int num;
        switch (direction) {
            case 5:
                this.currentX--;
                this.amountOfChoices++;
                break;
            case 6:
                this.currentY++;
                this.amountOfChoices++;
                break;
            case 7:
                this.currentX++;
                this.amountOfChoices++;
                break;
            case 8:
                this.currentY--;
                this.amountOfChoices++;
                break;
            case 9:
                if (amountOfChoices == 3) {
                    this.currentY++;
                    this.amountOfChoices = 0;
                } else {
                    num = random.nextInt(2);
                    if (num == 0)
                        this.currentX--;
                    else
                        this.currentY++;

                    this.amountOfChoices++;
                }
                break;
            case 10:
                if (amountOfChoices == 3) {
                    this.currentX--;
                    this.amountOfChoices = 0;
                } else {
                    num = random.nextInt(2);
                    if (num == 0)
                        this.currentX--;
                    else
                        this.currentY--;

                    this.amountOfChoices++;
                }
                break;
            case 11:
                if (amountOfChoices == 3) {
                    this.currentX++;
                    this.amountOfChoices = 0;
                } else {
                    num = random.nextInt(2);
                    if (num == 0)
                        this.currentY++;
                    else
                        this.currentX++;

                    this.amountOfChoices++;
                }
                break;
            case 12:
                if (amountOfChoices == 3) {
                    this.currentY--;
                    this.amountOfChoices = 0;
                } else {
                    num = random.nextInt(2);
                    if (num == 0)
                        this.currentX++;
                    else
                        this.currentY--;

                    this.amountOfChoices++;
                }
                break;
        }

    }

    public String getColor() {
        return color;
    }

    public RoadItem getCurrentRoad() {
        return currentRoad;
    }

    public void setCurrentRoad(RoadItem currentRoad) {
        this.currentRoad = currentRoad;
    }


}