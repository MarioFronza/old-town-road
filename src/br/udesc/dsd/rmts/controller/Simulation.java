package br.udesc.dsd.rmts.controller;

import java.util.LinkedList;
import java.util.Queue;

import br.udesc.dsd.rmts.model.Car;
import br.udesc.dsd.rmts.model.RoadItem;

public class Simulation extends Thread {

    private Queue<Car> cars;
    private IMeshController meshController;

    public Simulation() {
        this.meshController = MeshController.getInstance();
        this.cars = new LinkedList<>();
    }

    @Override
    public void run() {
        this.loadCarsInQueue();
        RoadItem[][] matrix = this.meshController.getMatrix();

        while (!cars.isEmpty()) {
            for (int i = 0; i < this.meshController.getLines(); i++) {
                for (int j = 0; j < this.meshController.getColumns(); j++) {
                    if (matrix[i][j].isEntryPoint() && !cars.isEmpty()) {
                        try {
                            sleep(this.meshController.getTimeInterval() * 1000);
                            RoadItem item = matrix[i][j];
                            this.meshController.addCar(cars.remove(), item.getX(), item.getY());
                            this.meshController.defineRouteAndStartThreaad(item.getX(), item.getY());
                            this.meshController.notifyRoadMeshUpdate();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void loadCarsInQueue() {
        for (int i = 0; i < this.meshController.getNumberOfCars(); i++) {
            cars.add(new Car());
        }
    }

}
