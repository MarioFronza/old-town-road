package br.udesc.dsd.rmts.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import br.udesc.dsd.rmts.controller.IMeshController;
import br.udesc.dsd.rmts.controller.MeshController;
import br.udesc.dsd.rmts.controller.observer.*;

public class MainFrame extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    private IMeshController meshController;
    private RoadMeshPanel roadMeshPanel;
    private JButton buttonStartSimulation;
    private JButton buttonStopSimulation;
    private JPanel main;

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

        this.main = new JPanel();
        this.main.setLayout(new BoxLayout(this.main, BoxLayout.Y_AXIS));
        this.main.setBorder(new EmptyBorder(new Insets(10, 0, 10, 0)));

        this.buttonStartSimulation = new JButton();
        this.buttonStartSimulation.setText("Start simulation");
        this.buttonStartSimulation.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.buttonStartSimulation.addActionListener((ActionEvent e) -> {
			this.meshController.runSimulation();
		});

        this.buttonStopSimulation = new JButton();
        this.buttonStopSimulation.setText("Stop simulation");
        this.buttonStopSimulation.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void addComponents() {
        this.main.add(buttonStartSimulation);
        this.main.add(buttonStopSimulation);
        this.main.add(this.roadMeshPanel);
        this.main.setBackground(new Color(121, 121, 121));
        super.add(this.main);
    }

    private void start() {
        super.setPreferredSize(new Dimension(roadMeshPanel.getWidth(), roadMeshPanel.getHeigth() + 91));
        super.pack();
        super.setLocationRelativeTo(null);
    }

    @Override
    public void message(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void roadMeshUpdate() {
//        this.roadMeshPanel.updateUI();
    }

}
