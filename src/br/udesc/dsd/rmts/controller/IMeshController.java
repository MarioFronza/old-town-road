package br.udesc.dsd.rmts.controller;

public interface IMeshController {
	
	void readAndCreateMatrix(); 
	
	String getMatrixPosition(int rowIndex, int columnIndex);
	
	int getX();
	
	int getY();
	
}
