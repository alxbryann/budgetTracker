package com.alxbryann.foc.view.tabbed;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author RAVEN
 */
public class PanelTabbed extends JPanel {

    private final ButtonGroup buttonGroup;

    public PanelTabbed() {
        // Alinear las tabs al borde inferior de la barra de título y sin espacio extra
        setLayout(new MigLayout("insets -3 8 0 8, aligny bottom"));
        buttonGroup = new ButtonGroup();
        setOpaque(false);
    }

    public void addTab(JToggleButton item) {
        buttonGroup.add(item);
        add(item);
        repaint();
        revalidate();
    }
}
