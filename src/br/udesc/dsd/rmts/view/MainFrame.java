package br.udesc.dsd.rmts.view;


import br.udesc.dsd.rmts.controller.MeshController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private RoadMeshPanel roadMeshPanel;

    private MeshController meshController;

    public MainFrame(MeshController meshController) {
        this.meshController = meshController;

        super.setFocusable(true);
        super.setFocusTraversalKeysEnabled(false);
        super.setTitle("Road Mesh Traffic Simulator");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setResizable(true);
        super.setLayout(new BorderLayout());

        initComponents();

        System.out.println(roadMeshPanel.getWidth());
        addComponents();
    }

    private void initComponents() {
        this.roadMeshPanel = new RoadMeshPanel(meshController);
    }

    private void addComponents() {
        super.add(roadMeshPanel);
        super.setPreferredSize(new Dimension( roadMeshPanel.getWidth(), roadMeshPanel.getHeigth() + 22));
        super.pack();
        super.setLocationRelativeTo(null);
    }

}
