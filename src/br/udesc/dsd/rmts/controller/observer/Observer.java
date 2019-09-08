package br.udesc.dsd.rmts.controller.observer;

/**
 * Observer interface
 *
 * @author João Pedro Schmitz, Mário Fronza
 * @version 1.0.0
 */
public interface Observer {
	
	void message(String message);

	void roadMeshUpdate();
	
}
