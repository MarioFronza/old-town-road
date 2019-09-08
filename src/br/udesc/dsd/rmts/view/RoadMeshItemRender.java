package br.udesc.dsd.rmts.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Roadmesh item render
 *
 * @author João Pedro Schmitz, Mário Fronza
 * @version 1.0.0
 */
public class RoadMeshItemRender extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        this.setHorizontalAlignment(SwingConstants.CENTER);
        setIcon((ImageIcon) value);

        return this;
    }
}
