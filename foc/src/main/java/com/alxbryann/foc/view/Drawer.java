package com.alxbryann.foc.view;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Clase Drawer para manejar un panel lateral deslizable
 * 
 * @author RAVEN
 */
public class Drawer {
    
    private static Drawer instance;
    private JPanel drawerPanel;
    private JFrame parentFrame;
    private boolean isOpen = false;
    
    public static Drawer getInstance() {
        if (instance == null) {
            instance = new Drawer();
        }
        return instance;
    }
    
    private Drawer() {
        initComponents();
    }
    
    private void initComponents() {
        drawerPanel = new JPanel(new BorderLayout());
        drawerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Panel.background;"
                + "border:0,0,0,1,$Component.borderColor");
    }
    
    public void install(JFrame frame) {
        this.parentFrame = frame;
    }
    
    public void showDrawer() {
        if (!isOpen && parentFrame != null) {
            isOpen = true;
            // Aquí se implementaría la animación del drawer
            // Por simplicidad, mostramos el drawer instantáneamente
            if (drawerPanel.getParent() == null) {
                parentFrame.add(drawerPanel, BorderLayout.WEST);
            }
            drawerPanel.setVisible(true);
            parentFrame.revalidate();
            parentFrame.repaint();
        }
    }
    
    public void closeDrawer() {
        if (isOpen && parentFrame != null) {
            isOpen = false;
            // Aquí se implementaría la animación del drawer
            // Por simplicidad, ocultamos el drawer instantáneamente
            drawerPanel.setVisible(false);
            parentFrame.revalidate();
            parentFrame.repaint();
        }
    }
    
    public void toggleDrawer() {
        if (isOpen) {
            closeDrawer();
        } else {
            showDrawer();
        }
    }
    
    public boolean isOpen() {
        return isOpen;
    }
    
    public void setDrawerContent(Component content) {
        drawerPanel.removeAll();
        drawerPanel.add(content, BorderLayout.CENTER);
        drawerPanel.revalidate();
        drawerPanel.repaint();
    }
    
    public JPanel getDrawerPanel() {
        return drawerPanel;
    }
}