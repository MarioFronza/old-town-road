package br.udesc.dsd.rmts.controller;

import br.udesc.dsd.rmts.controller.observer.Observer;
import br.udesc.dsd.rmts.model.RoadItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MeshController implements IMeshController {

    private static MeshController instance;
    private List<Observer> observers;
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
                            	matrix[i][j].setImagePath("assets/stone.png");
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
		Simulation simulation = new Simulation();
		simulation.start();
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
    public int getTimeInterval() {
        return this.timeInterval;
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
		builder.append("<html><body>Time interval: " + this.getTimeInterval() + "<br />");
		builder.append("Number of cars: " + this.getNumberOfCars() + "<br>"); 
		builder.append("Filename: " + this.file.getName() + "<br></body></html>");
		
		return builder.toString();
	}

}
