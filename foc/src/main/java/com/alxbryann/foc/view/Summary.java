package com.alxbryann.foc.view;

import java.awt.*;
import javax.swing.*;

public class Summary extends JPanel {

    private final ViewController viewController;
    private int clickedDay;
    private double totalCost;
    private double totalIncome;
    private double totalNet;

    public Summary(ViewController viewController, int clickedDay) {
        this.viewController = viewController;
        this.clickedDay = clickedDay;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBounds(0, 0, 360, 580);
        setBackground(new Color(232, 248, 245)); // azul claro

        // Título
        JLabel title = new JLabel("Summary");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        add(title);

        // Items (centrados)
        SummaryItem costItem = new SummaryItem("Total cost", totalCost, new Color(210, 133, 133));
        costItem.setAlignmentX(CENTER_ALIGNMENT);
        add(costItem);

        add(Box.createVerticalStrut(20));

        SummaryItem incomeItem = new SummaryItem("Total income", totalIncome, new Color(144, 203, 173));
        incomeItem.setAlignmentX(CENTER_ALIGNMENT);
        add(incomeItem);

        add(Box.createVerticalStrut(20));

        SummaryItem netItem = new SummaryItem("Net total", totalNet, new Color(232,224,190));
        netItem.setAlignmentX(CENTER_ALIGNMENT);
        add(netItem);
    }
    
    private static class SummaryItem extends JPanel {
        private final String label;
        private final double value;
        private final Color bgColor;

        public SummaryItem(String label, double value, Color bgColor) {
            this.label = label;
            this.value = value;
            this.bgColor = bgColor;

            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(280, 80)); 
            setMaximumSize(new Dimension(280, 80));
            setOpaque(false);

            JLabel labelText = new JLabel(label);
            labelText.setFont(new Font("SansSerif", Font.PLAIN, 18));
            labelText.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

            JLabel valueText = new JLabel("$" + String.format("%,.0f", value));
            valueText.setFont(new Font("SansSerif", Font.BOLD, 18));
            valueText.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

            add(labelText, BorderLayout.WEST);
            add(valueText, BorderLayout.EAST);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            g2.dispose();
        }

        @Override
        public boolean isOpaque() {
            return false;
        }
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
    public boolean isOpaque() {
        return false;
    }
}
