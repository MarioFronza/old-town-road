package br.udesc.dsd.rmts.model;

import br.udesc.dsd.rmts.controller.MeshController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Car extends Thread {

    private String imagePath;
    private List<RoadItem> route;
    private RoadItem matrix[][];
    private int currentX;
    private int currentY;
    private RoadItem currentRoad;

    public Car() {
        this.imagePath = "";
        this.route = new ArrayList<>();
        this.matrix = MeshController.getInstance().getMatrix();
        this.currentRoad = null;
    }

    @Override
    public void run() {

        while (true) {
            boolean andou = false;
            do {
                try {
                    RoadItem item = route.remove(0);
                    item.alocar();
                    item.addCart(item.getX(), item.getY());

                    currentRoad.removeCart(); //retirar carro
                    item.liberar();
                    currentRoad = item;

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
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
                        validateCrossRodad(matrix[currentX][currentY].getDirection());
                    break;
                case 2:
                    if (!isCrossroad(currentX, currentY))
                        this.currentY++;
                    else
                        validateCrossRodad(matrix[currentX][currentY].getDirection());
                    break;
                case 3:
                    if (!isCrossroad(currentX, currentY))
                        this.currentX++;
                    else
                        validateCrossRodad(matrix[currentX][currentY].getDirection());
                    break;
                case 4:
                    if (!isCrossroad(currentX, currentY))
                        this.currentY--;
                    else
                        validateCrossRodad(matrix[currentX][currentY].getDirection());
                    break;
                default:
                    validateCrossRodad(matrix[currentX][currentY].getDirection());
                    break;
            }
            route.add(new RoadItem(currentX, currentY));
            if (matrix[currentX][currentY].isExitPoint())
                isExitPoint = true;
        }

        for (RoadItem roadItem : route) {
            System.out.print(roadItem.getX() + "-" + roadItem.getY() + " ");
        }
    }

    public void validateCrossRodad(int direction) {
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
                System.out.println(num);
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

    public boolean isCrossroad(int x, int y) {
        boolean crossroad = false;
        for (int i = 5; i <= 12; i++)
            if (matrix[x][y].getDirection() == i) {
                crossroad = true;
            }


        return crossroad;
    }

}

