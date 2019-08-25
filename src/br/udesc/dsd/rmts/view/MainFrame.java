package br.udesc.dsd.rmts.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import br.udesc.dsd.rmts.controller.IMeshController;
import br.udesc.dsd.rmts.controller.MeshController;
import br.udesc.dsd.rmts.controller.observer.*;

public class MainFrame extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	
	private IMeshController meshController;
	private RoadMeshPanel roadMeshPanel;

    public MainFrame() {
    	this.meshController = MeshController.getInstance();
    	this.meshController.addObserver(this);
    	
        super.setFocusable(true);
        super.setFocusTraversalKeysEnabled(false);
        super.setTitle("🐎 Old town road");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setLayout(new BorderLayout());
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        initComponents();
        addComponents();
        start();
    }

    private void initComponents() {
        this.roadMeshPanel = new RoadMeshPanel();
    }

    private void addComponents() {
        super.add(roadMeshPanel);
    }
    
    private void start() {
        super.setPreferredSize(new Dimension(roadMeshPanel.getWidth(), roadMeshPanel.getHeigth() + 22));
        super.pack();
        super.setLocationRelativeTo(null);
    }

	@Override
	public void message(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

}
