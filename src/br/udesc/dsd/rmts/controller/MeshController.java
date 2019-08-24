package br.udesc.dsd.rmts.controller;

import br.udesc.dsd.rmts.controller.observer.Observer;
import br.udesc.dsd.rmts.model.RoadItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MeshController implements IMeshController {

	
	private static MeshController instance;
	private List<Observer> observers;
    private RoadItem matrix[][];
    private int x;
    private int y;
    
    public static MeshController getInstance() {
        if (instance == null) {
            instance = new MeshController();
        }

        return instance;
    }
    
    private MeshController() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void readAndCreateMatrix() {
        try {
            @SuppressWarnings("resource")
			Scanner input = new Scanner(new File("mesh/mesh3.txt"));
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

    @Override
    public String getMatrixPosition(int rowIndex, int columnIndex) {
        return matrix[rowIndex][columnIndex].getImagePath();
    }
    
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

}
