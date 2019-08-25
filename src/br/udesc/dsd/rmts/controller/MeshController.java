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
    private File file = null;
    private int lines;
    private int columns;
    private int numberOfCars;

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
        this.notifyMessage("Creating the road mesh");
        try {
            @SuppressWarnings("resource")
            Scanner input = new Scanner(file);
            while (input.hasNextInt()) {
                lines = input.nextInt();
                columns = input.nextInt();
                matrix = new RoadItem[lines][columns];
                for (int i = 0; i < lines; i++) {
                    for (int j = 0; j < columns; j++) {
                        matrix[i][j] = new RoadItem();
                        switch (input.nextInt()) {
                            case 0:
                                matrix[i][j].setImagePath("assets/default.png");
                                break;
                            case 1:
                                checkEntryPointOnBottom(i, j);
                                break;
                            case 2:
                                checkEntryPointOnLeft(i, j);
                                break;
                            case 3:
                                checkEntryPointOnTop(i, j);
                                break;
                            case 4:
                                checkEntryPointOnRight(i, j);
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

    public void runSimulation(){

    }

    public void checkEntryPointOnTop(int x, int y) {
        if (x - 1 < 0)
            matrix[x][y].setEntryPoint(true);
        else if (x + 1 >= this.lines)
            matrix[x][y].setExitPoint(true);
        matrix[x][y].setImagePath("assets/down.png");
    }

    public void checkEntryPointOnLeft(int x, int y) {
        if (y - 1 < 0)
            matrix[x][y].setEntryPoint(true);
        else if (y + 1 >= this.columns)
            matrix[x][y].setExitPoint(true);
        matrix[x][y].setImagePath("assets/right.png");
    }

    public void checkEntryPointOnRight(int x, int y) {
        if (y + 1 >= this.columns)
            matrix[x][y].setEntryPoint(true);
        else if (y - 1 < 0)
            matrix[x][y].setExitPoint(true);
        matrix[x][y].setImagePath("assets/left.png");
    }

    public void checkEntryPointOnBottom(int x, int y) {
        if (x + 1 >= this.lines)
            matrix[x][y].setEntryPoint(true);
        else if (x - 1 < 0)
            matrix[x][y].setExitPoint(true);
        matrix[x][y].setImagePath("assets/up.png");
    }

    @Override
    public String getMatrixPosition(int rowIndex, int columnIndex) {
        return matrix[rowIndex][columnIndex].getImagePath();
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public int getLines() {
        return lines;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public int getNumberOfCars() {
        return numberOfCars;
    }

    @Override
    public void setPathName(File file) {
        this.file = file;
    }

    @Override
    public void setNumberOfCars(int numberOfCars) {
        this.numberOfCars = numberOfCars;
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyMessage(String message) {
        for (Observer observer : observers) {
            observer.message(message);
        }
    }

}
