package br.udesc.dsd.rmts.controller;

import br.udesc.dsd.rmts.controller.observer.Observed;
import br.udesc.dsd.rmts.model.RoadItem;

import java.io.File;

public interface IMeshController extends Observed {

    void readAndCreateMatrix();

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
    
    int getNumberOfCars();
    
    RoadItem[][] getMatrix();

}
