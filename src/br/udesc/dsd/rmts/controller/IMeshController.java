package br.udesc.dsd.rmts.controller;

import br.udesc.dsd.rmts.controller.observer.Observed;

import java.io.File;

public interface IMeshController extends Observed {
	
	void readAndCreateMatrix(); 
	
	String getMatrixPosition(int rowIndex, int columnIndex);

	File getFile();

	void setPathName(File file);
	
	int getLines();
	
	int getColumns();
	
}
