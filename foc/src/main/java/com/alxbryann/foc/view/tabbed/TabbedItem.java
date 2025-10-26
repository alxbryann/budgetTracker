
package com.alxbryann.foc.view.tabbed;

import java.awt.Color;

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
       private JLabel label;
       private JButton cmd;

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
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setMargin(new java.awt.Insets(4, 8, 4, 6));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "background:null;"
                + "selectedBackground:null;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "arc:5;"
                + "margin:4,8,4,6;"
                + "foreground:#ffffff");

        label = new JLabel(name);
        label.setFont(new java.awt.Font("Kantumruy Pro", java.awt.Font.BOLD, 13));
        label.putClientProperty(FlatClientProperties.STYLE, "foreground:#ffffff");

        cmd = new JButton();
        cmd.setPreferredSize(new java.awt.Dimension(20, 20));
        cmd.setContentAreaFilled(false);
        cmd.setBorderPainted(false);
        cmd.setFocusPainted(false);
        cmd.addActionListener((ae) -> {
            if (titleBar != null) {
                titleBar.removeTab(this);
            } else {
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
                + "foreground:#ffffff");
        add(label);
        add(cmd, BorderLayout.EAST);
    }

    @Override
    public java.awt.Dimension getPreferredSize() {
        java.awt.Dimension d = super.getPreferredSize();
        // Establecer altura adecuada para las pestañas
        return new java.awt.Dimension(d.width, 32);
    }

    @Override
    public void paint(Graphics grphcs) {
        // Determinar si hay más de una pestaña
        boolean multipleTabs = getParent() != null && getParent().getComponentCount() > 1;
        if (isSelected()) {
            Graphics2D g2 = (Graphics2D) grphcs.create();
            FlatUIUtils.setRenderingHints(g2);
            Color bgColor;
            Color fgColor;
            if (component.getClass().getSimpleName().equals("CalendarTab")) {
                bgColor = new Color(101, 164, 118); // Fondo Calendar
                fgColor = Color.WHITE;
            } else {
                bgColor = Color.WHITE; // Fondo día
                fgColor = Color.BLACK;
            }
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
            g2.dispose();
            label.setForeground(fgColor);
            label.setFont(new java.awt.Font("Kantumruy Pro", java.awt.Font.BOLD, 13));
            // Botón de cerrar para pestaña activa
            cmd.setIcon(new FlatSVGIcon("raven/svg/close.svg", 0.75f));
            cmd.setBackground(new Color(0,0,0,0));
        } else if (multipleTabs) {
            // Pestaña inactiva con fondo negro y botón de cerrar blanco
            Graphics2D g2 = (Graphics2D) grphcs.create();
            FlatUIUtils.setRenderingHints(g2);
            g2.setColor(Color.BLACK);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
            g2.dispose();
            label.setForeground(Color.WHITE);
            label.setFont(new java.awt.Font("Kantumruy Pro", java.awt.Font.BOLD, 13));
            cmd.setIcon(new FlatSVGIcon("raven/svg/close-white.svg", 0.75f));
            cmd.setBackground(Color.BLACK);
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
