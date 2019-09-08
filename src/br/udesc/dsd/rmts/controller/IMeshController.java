package br.udesc.dsd.rmts.controller;

import br.udesc.dsd.rmts.controller.observer.Observed;
import br.udesc.dsd.rmts.model.Car;
import br.udesc.dsd.rmts.model.RoadItem;

import java.io.File;
import java.util.List;

public interface IMeshController extends Observed {

    void readAndCreateMatrix();

    void addCar(Car car, int x, int y);

    void removeCar(int x, int y);

    void defineRouteAndStartThreaad(int x, int y);

    void checkEntryPointOnTop(int x, int y, int direction);

    void checkEntryPointOnLeft(int x, int y, int direction);

    void checkEntryPointOnRight(int x, int y, int direction);

    void checkEntryPointOnBottom(int x, int y, int direction);

    String getMatrixPosition(int rowIndex, int columnIndex);

    File getFile();

    int getLines();

    int getColumns();

    void runSimulation();

    void setPathName(File file);

    void setNumberOfCars(int numberOfCars);

    void setTimeInterval(int timeInterval);

    int getTimeInterval();
    
    String getGeneralInformation();
    
    int getNumberOfCars();
    
    void setPositions(int x, int y, List<RoadItem> positions);

    RoadItem[][] getMatrix();

}
