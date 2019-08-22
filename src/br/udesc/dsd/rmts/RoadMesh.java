package br.udesc.dsd.rmts;

import br.udesc.dsd.rmts.controller.MeshController;
import br.udesc.dsd.rmts.view.MainFrame;

public class RoadMesh {
    public static void main(String[] args) {
        MeshController meshController = new MeshController();

        MainFrame mainFrame = new MainFrame(meshController);
        mainFrame.setVisible(true);
    }
}
