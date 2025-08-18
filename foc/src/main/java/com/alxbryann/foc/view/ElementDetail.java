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
public class ElementDetail extends JPanel {

    private boolean isFinancialObligation;

    public ElementDetail(boolean isFinancialObligation) {
        if (isFinancialObligation) {
            Color color = new Color(210, 133, 133);
            initializeUI(color);
        } else {
            Color color = new Color(144, 203, 173);
            initializeUI(color);
        }
    }

    private void initializeUI(Color color) {
        setLayout(null);
        setBounds(20, 20, 600, 80);
        setBackground(color);
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
