package br.udesc.dsd.rmts.model;

public class Car extends Thread{

    private String imagePath;

    public Car() {
        this.imagePath = "";
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Car "+this.getId()+" andando...");
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}

