package com.alxbryann.foc.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MonthSummary extends JPanel {

    private double totalIncome;
    private double totalObligations;
    private double totalNet;

    public MonthSummary(double totalIncome, double totalObligations) {
        this.totalIncome = totalIncome;
        this.totalObligations = totalObligations;
        this.totalNet = totalIncome - totalObligations;

        initializeUI();
    }
    
    public MonthSummary(){
        initializeUI();
    }

    private void initializeUI() {
        setLayout(null);
        setBounds(0, 0, 1280, 720);
        setBackground(new Color(232, 248, 245)); // mismo estilo que Summary
        
        // Título
        JLabel title = new JLabel("Month Summary");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title);

        JLabel subtitle = new JLabel("March 2025");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 18));
        subtitle.setAlignmentX(CENTER_ALIGNMENT);
        subtitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(subtitle);

        // Items
        SummaryItem incomeItem = new SummaryItem("Total incomes", totalIncome, new Color(144, 203, 173));
        incomeItem.setAlignmentX(CENTER_ALIGNMENT);
        add(incomeItem);

        add(Box.createVerticalStrut(20));

        SummaryItem obligationsItem = new SummaryItem("Total obligations", totalObligations, new Color(210, 133, 133));
        obligationsItem.setAlignmentX(CENTER_ALIGNMENT);
        add(obligationsItem);

        add(Box.createVerticalStrut(20));

        SummaryItem netItem = new SummaryItem("Net Balance", totalNet, new Color(232, 224, 190));
        netItem.setAlignmentX(CENTER_ALIGNMENT);
        add(netItem);
        
    }

    /**
     * Item redondeado estilo Summary
     */
    private static class SummaryItem extends JPanel {

        private final String label;
        private final double value;
        private final Color bgColor;

        public SummaryItem(String label, double value, Color bgColor) {
            this.label = label;
            this.value = value;
            this.bgColor = bgColor;

            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(320, 80));
            setMaximumSize(new Dimension(320, 80));
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
