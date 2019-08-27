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
    private JButton buttonOtherSimulation;
    private JPanel info;
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
        super.getContentPane().setBackground(Color.WHITE);
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
        this.main.setLayout(new GridBagLayout());
        
        this.info = new JPanel();
        this.info.setLayout(new GridBagLayout());
        this.info.setBorder(new EmptyBorder(10, 10, 10, 10));
        final Dimension sizeButton = new Dimension(145, 25);

        this.buttonStartSimulation = new JButton();
        this.buttonStartSimulation.setText("Start simulation");
        this.buttonStopSimulation.setForeground(new Color(85, 239, 196));
		this.buttonStartSimulation.addActionListener((ActionEvent e) -> {
			this.meshController.runSimulation();
		});
		
		this.buttonOtherSimulation = new JButton();
        this.buttonOtherSimulation.setText("Other simulation");
        this.buttonOtherSimulation.setPreferredSize(sizeButton);
		this.buttonOtherSimulation.addActionListener((ActionEvent e) -> {
			ChoiceFrame choice = new ChoiceFrame();
			choice.setVisible(true);
			super.setVisible(false);
		});

        this.buttonStopSimulation = new JButton();
        this.buttonStopSimulation.setText("Stop simulation");
        this.buttonStopSimulation.setForeground(new Color(250, 177, 160));
        this.buttonStopSimulation.setPreferredSize(sizeButton);
    }

    private void addComponents() {
        GridBagConstraints cons;
        
        cons = new GridBagConstraints();
    	cons.gridy = 0;
    	cons.gridx = 0;
    	cons.anchor = GridBagConstraints.FIRST_LINE_START;
        this.info.add(this.buttonStartSimulation, cons);

    	cons = new GridBagConstraints();
    	cons.gridy = 1;
    	cons.gridx = 0;
    	cons.anchor = GridBagConstraints.FIRST_LINE_START;
    	this.info.add(this.buttonStopSimulation, cons);
    	
        cons = new GridBagConstraints();
    	cons.gridy = 2;
    	cons.gridx = 0;
    	cons.anchor = GridBagConstraints.FIRST_LINE_START;
    	this.info.add(this.buttonOtherSimulation, cons);
        
    	cons = new GridBagConstraints();
    	cons.gridy = 0;
    	cons.gridx = 0;
    	cons.anchor = GridBagConstraints.NORTH;
        this.main.add(this.info, cons);
        
        cons = new GridBagConstraints();
    	cons.gridy = 0;
    	cons.gridx = 1;
        
    	this.main.add(this.roadMeshPanel, cons);
    	
        this.info.setBackground(Color.WHITE);
        this.main.setBackground(Color.WHITE);
        super.add(this.main);
    }

	private void start() {
        super.pack();
        super.setLocationRelativeTo(null);
    }

    @Override
    public void message(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void roadMeshUpdate() {
    	this.roadMeshPanel.updateUI();
    }

}
