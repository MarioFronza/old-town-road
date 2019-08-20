package br.udesc.dsd.rmts.controller;

import br.udesc.dsd.rmts.model.RoadItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MeshController {

    private RoadItem matrix[][];
    private int x;
    private int y;

    public MeshController() {
        readAndCreateMatrix();
    }

    public void readAndCreateMatrix() {
        try {
            Scanner input = new Scanner(new File("mesh/mesh1.txt"));
            while (input.hasNextInt()) {
                x = input.nextInt();
                y = input.nextInt();
                matrix = new RoadItem[x][y];
                for (int i = 0; i < x; i++) {
                    for (int j = 0; j < y; j++) {
                        matrix[i][j] = new RoadItem();
                        switch (input.nextInt()) {
                            case 0:
                                matrix[i][j].setImagePath("assets/default.png");
                                break;
                            case 1:
                                matrix[i][j].setImagePath("assets/up.png");
                                break;
                            case 2:
                                matrix[i][j].setImagePath("assets/right.png");
                                break;
                            case 3:
                                matrix[i][j].setImagePath("assets/down.png");
                                break;
                            case 4:
                                matrix[i][j].setImagePath("assets/left.png");
                                break;
                            default:
                                matrix[i][j].setImagePath("assets/stone.png");
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getMatrixPosition(int rowIndex, int columnIndex) {
        return matrix[rowIndex][columnIndex].getImagePath();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
