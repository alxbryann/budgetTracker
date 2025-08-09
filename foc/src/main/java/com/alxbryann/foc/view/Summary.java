package com.alxbryann.foc.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author alxbryann
 */
public class Summary extends JPanel{

    private final ViewController viewController;

    public Summary(ViewController viewController) {
        this.viewController = viewController;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(null);
        setBounds(0, 0, 360, 580);
        setBackground(new Color(232, 248, 245));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

}
