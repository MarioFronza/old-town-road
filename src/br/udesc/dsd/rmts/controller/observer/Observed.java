package br.udesc.dsd.rmts.controller.observer;

public interface Observed {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);
    
    void notifyMessage(String message);
}
