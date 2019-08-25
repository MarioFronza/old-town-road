package br.udesc.dsd.rmts.controller;

import br.udesc.dsd.rmts.controller.observer.Observer;
import br.udesc.dsd.rmts.model.Car;
import br.udesc.dsd.rmts.model.RoadItem;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MeshController implements IMeshController {


    private static MeshController instance;
    private List<Observer> observers;
    private Queue<Car> cars;
    private RoadItem matrix[][];
    private File file = null;
    private int lines;
    private int columns;
    private int numberOfCars;
    private int timeInterval;

    public static MeshController getInstance() {
        if (instance == null) {
            instance = new MeshController();
        }

        return instance;
    }

    private MeshController() {
        this.observers = new ArrayList<>();
        this.cars = new LinkedList<>();
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
                                checkEntryPointOnBottom(i, j, 1);
                                break;
                            case 2:
                                checkEntryPointOnLeft(i, j, 2);
                                break;
                            case 3:
                                checkEntryPointOnTop(i, j, 3);
                                break;
                            case 4:
                                checkEntryPointOnRight(i, j, 4);
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
    public void runSimulation() { //Colocar metodo em thread separada

        loadCarsInQueue();

        while (!cars.isEmpty())
            for (int i = 0; i < lines; i++) {
                for (int j = 0; j < columns; j++) {
                    if (matrix[i][j].isEntryPoint() && !cars.isEmpty()) {
                        //definir intervalo aqui
                        matrix[i][j].setCar(cars.remove());
                        matrix[i][j].getCar().start();
                        notifyRoadMeshUpdate();
                        System.out.println("show");
                    }
                }
            }

    }

    @Override
    public void loadCarsInQueue() {
        for (int i = 0; i < numberOfCars; i++) {
            cars.add(new Car());
        }
    }

    @Override
    public void checkEntryPointOnTop(int x, int y, int direction) {
        if (x - 1 < 0)
            matrix[x][y].setEntryPoint(true);
        else if (x + 1 >= this.lines)
            matrix[x][y].setExitPoint(true);
        matrix[x][y].setDirection(direction);
        matrix[x][y].setImagePath("assets/down.png");
    }

    @Override
    public void checkEntryPointOnLeft(int x, int y, int direction) {
        if (y - 1 < 0)
            matrix[x][y].setEntryPoint(true);
        else if (y + 1 >= this.columns)
            matrix[x][y].setExitPoint(true);
        matrix[x][y].setDirection(direction);
        matrix[x][y].setImagePath("assets/right.png");
    }

    @Override
    public void checkEntryPointOnRight(int x, int y, int direction) {
        if (y + 1 >= this.columns)
            matrix[x][y].setEntryPoint(true);
        else if (y - 1 < 0)
            matrix[x][y].setExitPoint(true);
        matrix[x][y].setDirection(direction);
        matrix[x][y].setImagePath("assets/left.png");
    }

    @Override
    public void checkEntryPointOnBottom(int x, int y, int direction) {
        if (x + 1 >= this.lines)
            matrix[x][y].setEntryPoint(true);
        else if (x - 1 < 0)
            matrix[x][y].setExitPoint(true);
        matrix[x][y].setDirection(direction);
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
    public void setPathName(File file) {
        this.file = file;
    }

    @Override
    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
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

    @Override
    public void notifyRoadMeshUpdate() {
        for (Observer observer : observers) {
            observer.roadMeshUpdate();
        }
    }

}
