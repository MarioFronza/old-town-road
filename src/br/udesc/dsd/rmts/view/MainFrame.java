package br.udesc.dsd.rmts.view;


import br.udesc.dsd.rmts.controller.MeshController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private RoadMeshPanel roadMeshPanel;

    private MeshController meshController;

    public MainFrame(MeshController meshController) {
        this.meshController = meshController;

        super.setFocusable(true);
        super.setFocusTraversalKeysEnabled(false);
        super.setTitle("Road Mesh Traffic Simulator");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setLayout(new GridLayout(1, 1));
        super.setPreferredSize(new Dimension(30 * meshController.getX(), 30 * meshController.getY()));
        super.pack();
        super.setLocationRelativeTo(null);

        initComponents();
        addComponents();

    }

    private void initComponents() {
        this.roadMeshPanel = new RoadMeshPanel(meshController);
    }

    private void addComponents() {
        super.add(roadMeshPanel);
    }

}
