package br.udesc.dsd.rmts.controller;

import br.udesc.dsd.rmts.controller.observer.Observed;

public interface IMeshController extends Observed {
	
	void readAndCreateMatrix(); 
	
	String getMatrixPosition(int rowIndex, int columnIndex);
	
	int getX();
	
	int getY();
	
}
