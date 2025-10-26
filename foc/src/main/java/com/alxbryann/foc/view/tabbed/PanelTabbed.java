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
        // Configurar layout con margen inferior negativo para mezclar con el contenido
        setLayout(new MigLayout("insets 3 8 -3 8, aligny bottom"));
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
