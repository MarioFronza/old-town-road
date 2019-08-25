package br.udesc.dsd.rmts.controller;

import br.udesc.dsd.rmts.controller.observer.Observed;

import java.io.File;

public interface IMeshController extends Observed {
	
	void readAndCreateMatrix(); 
	
	String getMatrixPosition(int rowIndex, int columnIndex);

	File getFile();

	int getLines();
	
	int getColumns();

	int getNumberOfCars();

	void setPathName(File file);

	void setNumberOfCars(int numberOfCars);
	
}
