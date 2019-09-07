package br.udesc.dsd.rmts.controller.observer;

public interface Observed {

    void addObserver(Observer observer);
    
    void notifyMessage(String message);

    void notifyRoadMeshUpdate();
}
