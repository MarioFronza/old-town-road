package br.udesc.dsd.rmts.view;

import br.udesc.dsd.rmts.controller.MeshController;

public class Main {
    public static void main(String[] args) {
        MeshController meshController = new MeshController();

        MainFrame mainFrame = new MainFrame(meshController);
        mainFrame.setVisible(true);
    }
}
