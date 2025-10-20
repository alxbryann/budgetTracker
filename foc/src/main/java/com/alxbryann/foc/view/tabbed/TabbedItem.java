package com.alxbryann.foc.view.tabbed;

import com.alxbryann.foc.view.CustomTitleBar;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author RAVEN
 */
public class TabbedItem extends JToggleButton {

    public TabbedForm getComponent() {
        return component;
    }

    public String getTabbedName() {
        return tabbedName;
    }

    private final TabbedForm component;
    private final String tabbedName;
    private CustomTitleBar titleBar;

    public TabbedItem(String name, TabbedForm component) {
        this.tabbedName = name;
        this.component = component;
        init(name);
    }
    
    public void setTitleBar(CustomTitleBar titleBar) {
        this.titleBar = titleBar;
    }

    private void init(String name) {
        setLayout(new MigLayout("", "[]10[]"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "background:null;"
                + "arc:5;"
                + "margin:3,8,3,5;"
                + "foreground:white");
        JButton cmd = new JButton(new FlatSVGIcon("raven/svg/close.svg", 0.8f));
        cmd.addActionListener((ae) -> {
            if (titleBar != null) {
                titleBar.removeTab(this);
            } else {
                // Fallback al método original si no hay titleBar establecido
                WindowsTabbed.getInstance().removeTab(this);
            }
        });
        cmd.putClientProperty(FlatClientProperties.STYLE, ""
                + "margin:2,2,2,2;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "background:null;"
                + "arc:999;"
                + "foreground:white");
        JLabel label = new JLabel(name);
        label.putClientProperty(FlatClientProperties.STYLE, ""
                + "foreground:white");
        add(label);
        add(cmd, BorderLayout.EAST);
    }

    @Override
    public void paint(Graphics grphcs) {
        if (isSelected()) {
            // Dibujar fondo para pestaña seleccionada
            Graphics2D g2 = (Graphics2D) grphcs.create();
            FlatUIUtils.setRenderingHints(g2);
            g2.setColor(new java.awt.Color(255, 255, 255, 40)); // Fondo semi-transparente blanco
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
            g2.dispose();
        }
        super.paint(grphcs);
        if (!isSelected() && getParent().getComponentZOrder(this) != getParent().getComponentCount() - 1) {
            Graphics2D g2 = (Graphics2D) grphcs.create();
            FlatUIUtils.setRenderingHints(g2);
            g2.setColor(new java.awt.Color(255, 255, 255, 60)); // Separador blanco semi-transparente
            float m = UIScale.scale(5);
            float s = UIScale.scale(1);
            g2.fill(new Rectangle2D.Double(getWidth() - s, m, s, getHeight() - m * 2));
            g2.dispose();
        }
    }
}
