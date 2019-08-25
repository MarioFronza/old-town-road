package br.udesc.dsd.rmts.model;

public class Car extends Thread{

    private String imagePath;

    public Car(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Carr "+this.getId()+" andando...");
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}

