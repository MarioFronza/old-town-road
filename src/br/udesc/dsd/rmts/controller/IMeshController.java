package br.udesc.dsd.rmts.controller;

import br.udesc.dsd.rmts.controller.observer.Observed;
import br.udesc.dsd.rmts.model.Car;
import br.udesc.dsd.rmts.model.RoadItem;
import br.udesc.dsd.rmts.model.abstractfactory.AbstractRoadItemFactory;

import java.io.File;
import java.util.concurrent.ExecutorService;

public interface IMeshController extends Observed {

    void readAndCreateMatrix();

    void runSimulation();

    void stopSimulation();

    void addCar(Car car, int x, int y);

    void removeCar(int x, int y);

    void removeThread(Car car);

    void defineRouteAndStartThread(int x, int y);

    void checkCrossPont(int x, int y, int direction);

    void checkEntryPointOnTop(int x, int y, int direction);

    void checkEntryPointOnLeft(int x, int y, int direction);

    void checkEntryPointOnRight(int x, int y, int direction);

    void checkEntryPointOnBottom(int x, int y, int direction);

    String getMatrixPosition(int rowIndex, int columnIndex);

    ExecutorService getExecutorService();

    File getFile();

    int getLines();

    int getColumns();

    int getTimeInterval();

    int getNumberOfCars();

    RoadItem[][] getMatrix();

    boolean isTerminate();

    void setPathName(File file);

    void setTerminate();

    void setFactory(AbstractRoadItemFactory factory);

    void setNumberOfCars(int numberOfCars);

    void setTimeInterval(int timeInterval);


}
