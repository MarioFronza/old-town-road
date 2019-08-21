package br.udesc.dsd.rmts.view;

import br.udesc.dsd.rmts.controller.MeshController;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class RoadMeshPanel extends JPanel {

    class RoadTableModel extends AbstractTableModel{
        @Override
        public int getRowCount() {
            return meshController.getX();
        }

        @Override
        public int getColumnCount() {
            return meshController.getY();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return new ImageIcon(meshController.getMatrixPosition(rowIndex, columnIndex));
        }
    }

    private MeshController meshController;
    private JTable roadMesh;

    public RoadMeshPanel(MeshController meshController) {
        this.meshController = meshController;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);
        this.initComponents();
        this.addComponents();
    }


    private void initComponents() {
        roadMesh = new JTable();
        roadMesh.setModel(new RoadTableModel());
        for (int x = 0; x < roadMesh.getColumnModel().getColumnCount(); x++) {
            roadMesh.getColumnModel().getColumn(x).setWidth(25);
            roadMesh.getColumnModel().getColumn(x).setMinWidth(25);
            roadMesh.getColumnModel().getColumn(x).setMaxWidth(25);
        }
        roadMesh.setRowHeight(25);
        roadMesh.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roadMesh.setIntercellSpacing(new Dimension(0, 0));
        roadMesh.setDefaultRenderer(Object.class, new RoadMeshItemRender());
    }

    public int getHeigth(){
        return (int) roadMesh.getMaximumSize().getHeight();
    }


    public int getWidth(){
        return (int) roadMesh.getMaximumSize().getWidth();
    }

    private void addComponents(){
        this.add(roadMesh);
    }

}
