package br.udesc.dsd.rmts.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RoadMeshItemRender extends DefaultTableCellRenderer {


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        this.setHorizontalAlignment(SwingConstants.CENTER);
        setIcon((ImageIcon) value);

        return this;
    }
}
