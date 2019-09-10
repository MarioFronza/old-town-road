package br.udesc.dsd.rmts.controller;

import br.udesc.dsd.rmts.controller.observer.Observer;
import br.udesc.dsd.rmts.model.Car;
import br.udesc.dsd.rmts.model.RoadItem;
import br.udesc.dsd.rmts.model.abstractfactory.AbstractRoadItemFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Mesh controller, contains the simulation logic
 *
 * @author João Pedro Schmitz, Mário Fronza
 * @version 1.0.0
 */
public class MeshController implements IMeshController {

    private static MeshController instance;
    private List<Observer> observers;
    private RoadItem matrix[][];
    private File file = null;
    private Simulation simulation;
    private List<Car> threadList;
    private AbstractRoadItemFactory factory;
    private ExecutorService executorService;
    private int lines;
    private int columns;
    private int numberOfCars;
    private int timeInterval;
    private boolean terminate;

    public static MeshController getInstance() {
        if (instance == null) {
            instance = new MeshController();
        }

        return instance;
    }

    private MeshController() {
        this.observers = new ArrayList<>();
        this.threadList = new ArrayList<>();
        this.terminate = false;
    }

    @Override
    public void readAndCreateMatrix() {
        try {
            @SuppressWarnings("resource")
            Scanner input = new Scanner(file);
            while (input.hasNextInt()) {
                lines = input.nextInt();
                columns = input.nextInt();
                matrix = new RoadItem[lines][columns];
                for (int i = 0; i < lines; i++) {
                    for (int j = 0; j < columns; j++) {
                        matrix[i][j] = factory.createRoad(i, j);
                        int valueOfPositionOnMesh = input.nextInt();
                        switch (valueOfPositionOnMesh) {
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
                            default: {
                                checkCrossPont(i, j, valueOfPositionOnMesh);
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void runSimulation() {
        this.terminate = false;
        this.simulation = new Simulation(this);
        this.executorService = Executors.newCachedThreadPool();
        this.simulation.setRunning();
        this.simulation.start();
    }

    @Override
    public void stopSimulation() {
        this.simulation.terminate();
    }

    @Override
    public void addCar(Car car, int x, int y) {
        this.matrix[x][y].addCar(car);
        this.threadList.add(matrix[x][y].getCar());
        notifyRoadMeshUpdate();
    }

    @Override
    public void removeCar(int x, int y) {
        this.matrix[x][y].removeCar();
        notifyRoadMeshUpdate();
    }

    @Override
    public void checkCrossPont(int x, int y, int direction) {
        this.matrix[x][y].setImagePath("assets/stone.png");
        this.matrix[x][y].setDirection(direction);
    }

    @Override
    public void defineRouteAndStartThread(int x, int y) {
        if (!terminate) {
            this.matrix[x][y].getCar().defineRoute(x, y);
            this.matrix[x][y].getCar().setCurrentRoad(matrix[x][y]);
            this.executorService.execute(matrix[x][y].getCar());
        }
    }

    @Override
    public void removeThread(Car car) {
        this.simulation.addCar();
        this.threadList.remove(car);
    }

    @Override
    public void checkEntryPointOnTop(int x, int y, int direction) {
        if (x - 1 < 0)
            this.matrix[x][y].setEntryPoint(true);
        else if (x + 1 >= this.lines)
            this.matrix[x][y].setExitPoint(true);
        this.matrix[x][y].setDirection(direction);
        this.matrix[x][y].setImagePath("assets/road" + direction + ".png");
    }

    @Override
    public void checkEntryPointOnLeft(int x, int y, int direction) {
        if (y - 1 < 0)
            this.matrix[x][y].setEntryPoint(true);
        else if (y + 1 >= this.columns)
            this.matrix[x][y].setExitPoint(true);
        this.matrix[x][y].setDirection(direction);
        this.matrix[x][y].setImagePath("assets/road" + direction + ".png");
    }

    @Override
    public void checkEntryPointOnRight(int x, int y, int direction) {
        if (y + 1 >= this.columns)
            this.matrix[x][y].setEntryPoint(true);
        else if (y - 1 < 0)
            this.matrix[x][y].setExitPoint(true);
        this.matrix[x][y].setDirection(direction);
        this.matrix[x][y].setImagePath("assets/road" + direction + ".png");
    }

    @Override
    public void checkEntryPointOnBottom(int x, int y, int direction) {
        if (x + 1 >= this.lines)
            this.matrix[x][y].setEntryPoint(true);
        else if (x - 1 < 0)
            this.matrix[x][y].setExitPoint(true);
        this.matrix[x][y].setDirection(direction);
        this.matrix[x][y].setImagePath("assets/road" + direction + ".png");
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
    public int getTimeInterval() {
        return this.timeInterval;
    }

    @Override
    public ExecutorService getExecutorService() {
        return executorService;
    }

    @Override
    public boolean isTerminate() {
        return terminate;
    }

    @Override
    public void setPathName(File file) {
        this.file = file;
    }

    @Override
    public void setTerminate() {
        this.terminate = true;
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
    public void setFactory(AbstractRoadItemFactory factory) {
        this.factory = factory;
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
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

    @Override
    public int getNumberOfCars() {
        return this.numberOfCars;
    }

    @Override
    public RoadItem[][] getMatrix() {
        return this.matrix;
    }

    @Override
    public String getGeneralInformation() {
        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>Time interval: " + this.getTimeInterval() + "s<br />");
        builder.append("Number of cars: " + this.getNumberOfCars() + "<br>");
        builder.append("Filename: " + this.file.getName() + "<br></body></html>");

        return builder.toString();
    }

    @Override
    public void setPositions(int x, int y, List<RoadItem> positions) {
        // TODO Auto-generated method stub

    }
}
