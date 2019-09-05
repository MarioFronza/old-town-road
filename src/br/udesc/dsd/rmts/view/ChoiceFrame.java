package br.udesc.dsd.rmts.view;

import br.udesc.dsd.rmts.controller.IMeshController;
import br.udesc.dsd.rmts.controller.MeshController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ChoiceFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JButton buttonStart;
    private JButton buttonSelectMeshFile;
    private JTextField numberOfCars;
    private JLabel labelCars;
    private JTextField timeInterval;
    private JLabel labelTimeInterval;
    private ButtonGroup buttonGroup;
    private JRadioButton mechanismSemaphore, mechanismMonitor;
    private FileChooser fileChooser;
    private JLabel labelMechanisms;
    private JPanel mechanisms;
    private JPanel choice;
    private IMeshController meshController;

    public ChoiceFrame() {
        super.setTitle("🐎 Old town road - New RoadItemMonitorFactory");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setFocusable(true);
        super.setFocusTraversalKeysEnabled(false);
        super.setLayout(new BorderLayout());
        super.setPreferredSize(new Dimension(400, 230));
        super.pack();
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        this.meshController = MeshController.getInstance();

        initComponents();
        addComponents();
    }

    private void initComponents() {
        this.choice = new JPanel();
        this.choice.setLayout(new BoxLayout(this.choice, BoxLayout.Y_AXIS));
        this.choice.setBorder(new EmptyBorder(new Insets(10, 0, 10, 0)));

        this.labelCars = new JLabel("Choose the number of cars:");
        this.labelCars.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.numberOfCars = new JTextField();
        this.numberOfCars.setPreferredSize(new Dimension(350, 20));
        this.numberOfCars.setMaximumSize(new Dimension(350, 20));
        this.numberOfCars.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.labelTimeInterval = new JLabel("Vehicle insertion range (in milliseconds):");
        this.labelTimeInterval.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.timeInterval = new JTextField();
        this.timeInterval.setPreferredSize(new Dimension(350, 20));
        this.timeInterval.setMaximumSize(new Dimension(350, 20));
        this.timeInterval.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.mechanisms = new JPanel();
        this.mechanisms.setForeground(Color.white);
        this.labelMechanisms = new JLabel("Choose a mechanism:");
        this.labelMechanisms.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.mechanismSemaphore = new JRadioButton("Semaphore");
        this.mechanismSemaphore.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.mechanismMonitor = new JRadioButton("Monitor");
        this.mechanismMonitor.setAlignmentX(Component.RIGHT_ALIGNMENT);

        this.buttonGroup = new ButtonGroup();

        this.buttonStart = new JButton();
        this.buttonStart.setText("Start simulation");
        this.buttonStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.buttonStart.addActionListener((ActionEvent e) -> {
            if (validateFields()) {
                super.setVisible(false);
                this.meshController.setNumberOfCars(Integer.parseInt(numberOfCars.getText()));
                this.meshController.setTimeInterval(Integer.parseInt(timeInterval.getText()));
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });

        this.buttonSelectMeshFile = new JButton();
        this.buttonSelectMeshFile.setText("Select mesh file");
        this.buttonSelectMeshFile.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.buttonSelectMeshFile.addActionListener((ActionEvent e) -> {
            this.fileChooser = new FileChooser();
            this.buttonSelectMeshFile.setText(this.fileChooser.getChoosedFileName());
        });

    }

    private void addComponents() {
        this.mechanisms.add(mechanismMonitor);
        this.mechanisms.add(mechanismSemaphore);
        this.mechanisms.setBackground(Color.white);

        this.buttonGroup.add(mechanismMonitor);
        this.buttonGroup.add(mechanismSemaphore);

        this.choice.add(labelCars);
        this.choice.add(numberOfCars);
        this.choice.add(labelTimeInterval);
        this.choice.add(timeInterval);
        this.choice.add(labelMechanisms);
        this.choice.add(mechanisms);
        this.choice.add(buttonSelectMeshFile);
        this.choice.add(buttonStart);
        this.choice.setBackground(Color.white);
        super.add(this.choice);
    }

    private boolean validateFields() {
        if ((this.numberOfCars.getText().equals("") || this.timeInterval.getText().equals("")) || (!this.mechanismSemaphore.isSelected() && !this.mechanismMonitor.isSelected())) {
            JOptionPane.showMessageDialog(this, "All fields are required");
            return false;
        }

        if (!isNumeric(this.numberOfCars.getText())) {
            JOptionPane.showMessageDialog(this, "Number of cars should be a number");
            return false;
        }

        if (!isNumeric(this.timeInterval.getText())) {
            JOptionPane.showMessageDialog(this, "Vehicle insertion range should be a number");
            return false;
        }

        if (this.meshController.getFile() == null) {
            JOptionPane.showMessageDialog(this, "Choose a mesh file to run the simulation");
            return false;
        }

        return true;
    }

    private boolean isNumeric(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

}
