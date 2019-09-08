package br.udesc.dsd.rmts.controller.observer;

/**
 * Observed interface
 *
 * @author João Pedro Schmitz, Mário Fronza
 * @version 1.0.0
 */
public interface Observed {

    void addObserver(Observer observer);
    
    void notifyMessage(String message);

    void notifyRoadMeshUpdate();
}
