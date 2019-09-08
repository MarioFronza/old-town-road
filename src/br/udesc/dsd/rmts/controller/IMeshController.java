package br.udesc.dsd.rmts.controller;

import br.udesc.dsd.rmts.controller.observer.Observed;
import br.udesc.dsd.rmts.model.Car;
import br.udesc.dsd.rmts.model.RoadItem;
import br.udesc.dsd.rmts.model.abstractfactory.AbstractRoadItemFactory;

import java.io.File;
<<<<<<< HEAD
import java.util.List;
=======
import java.util.concurrent.ExecutorService;
>>>>>>> b9f302084f6db9cf7e4f409ab0ddfb512146d69e

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

    int getTimeInterval();
    
    String getGeneralInformation();
    
    int getNumberOfCars();
    
    void setPositions(int x, int y, List<RoadItem> positions);

    void setNumberOfCars(int numberOfCars);

    void setTimeInterval(int timeInterval);


}
