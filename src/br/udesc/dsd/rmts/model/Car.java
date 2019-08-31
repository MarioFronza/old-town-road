package br.udesc.dsd.rmts.model;

import br.udesc.dsd.rmts.controller.IMeshController;
import br.udesc.dsd.rmts.controller.MeshController;

import java.util.*;

public class Car extends Thread {

    private String setImagePath;
    private Queue<RoadItem> route;
    private RoadItem matrix[][];
    private int currentX;
    private int currentY;
    private RoadItem currentRoad;
    private IMeshController meshController;

    public Car() {
        this.setImagePath = "";
        this.route = new LinkedList<>();
        this.meshController = MeshController.getInstance();
        this.matrix = meshController.getMatrix();
        this.currentRoad = null;
    }

    @Override
    public void run() {

        while (!route.isEmpty()) {
            boolean andou = false;
            do {
                try {

                    RoadItem item = route.remove();

                    this.meshController.acquireRoadItem(item.getX(), item.getY());
                    this.meshController.addCar(this, item.getX(), item.getY());
                    this.meshController.removeCar(currentRoad.getX(), currentRoad.getY());
                    this.currentRoad = item;
                    andou = true;
                    
                    this.meshController.releaseRoadItem(item.getX(), item.getY());
                    try {
                        sleep(200);
                    } catch (Exception ie) {
                        ie.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        sleep(100);
                    } catch (Exception ie) {
                        ie.printStackTrace();
                    }
                }

            } while (!andou);
        }
        this.meshController.removeCar(currentRoad.getX(), currentRoad.getY());
    }

    public void defineRoute(int x, int y) {
        boolean isExitPoint = false;
        this.currentX = x;
        this.currentY = y;

        while (!isExitPoint) {
            switch (matrix[currentX][currentY].getDirection()) {
                case 1:
                    if (!isCrossroad(currentX, currentY))
                        this.currentX--;
                    else
                        chooseCrossRoad(matrix[currentX][currentY].getDirection());
                    break;
                case 2:
                    if (!isCrossroad(currentX, currentY))
                        this.currentY++;
                    else
                        chooseCrossRoad(matrix[currentX][currentY].getDirection());
                    break;
                case 3:
                    if (!isCrossroad(currentX, currentY))
                        this.currentX++;
                    else
                        chooseCrossRoad(matrix[currentX][currentY].getDirection());
                    break;
                case 4:
                    if (!isCrossroad(currentX, currentY))
                        this.currentY--;
                    else
                        chooseCrossRoad(matrix[currentX][currentY].getDirection());
                    break;
                default:
                    chooseCrossRoad(matrix[currentX][currentY].getDirection());
                    break;
            }
            route.add(matrix[currentX][currentY]);
            if (matrix[currentX][currentY].isExitPoint())
                isExitPoint = true;
        }
    }

    public boolean isCrossroad(int x, int y) {
        boolean crossroad = false;
        for (int i = 5; i <= 12; i++)
            if (matrix[x][y].getDirection() == i) {
                crossroad = true;
            }


        return crossroad;
    }

    public void chooseCrossRoad(int direction) {
        Random random = new Random();
        int num;

        switch (direction) {
            case 5:
                this.currentX--;
                break;
            case 6:
                this.currentY++;
                break;
            case 7:
                this.currentX++;
                break;
            case 8:
                this.currentY--;
                break;
            case 9:
                num = random.nextInt(2);
                if (num == 0)
                    this.currentX--;
                else
                    this.currentY++;
                break;
            case 10:
                num = random.nextInt(2);
                if (num == 0)
                    this.currentX--;
                else
                    this.currentY--;
                break;
            case 11:
                num = random.nextInt(2);
                if (num == 0)
                    this.currentY++;
                else
                    this.currentX++;
                break;
            case 12:
                num = random.nextInt(2);
                if (num == 0)
                    this.currentX++;
                else
                    this.currentY--;
                break;
        }
    }

    public String getImagePath() {
        return setImagePath;
    }

    public void setImagePath(String setImagePath) {
        this.setImagePath = setImagePath;
    }

    public void setCurrentRoad(RoadItem currentRoad) {
        this.currentRoad = currentRoad;
    }


}

