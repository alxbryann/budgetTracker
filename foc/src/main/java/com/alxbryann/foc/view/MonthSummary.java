package com.alxbryann.foc.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author alxbryann
 */
public class MonthSummary extends JPanel {

    public MonthSummary() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Panel izquierdo (Summary)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);

        // Título
        JLabel title = new JLabel("Month Summary");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        JLabel subtitle = new JLabel("March 2025");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 18));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.add(title);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(subtitle);

        leftPanel.add(headerPanel);
        leftPanel.add(Box.createVerticalStrut(20));

        // Bloques
        leftPanel.add(createSummaryBox("Total incomes", "$1,200,000", new Color(154, 213, 180)));
        leftPanel.add(Box.createVerticalStrut(15));
        leftPanel.add(createSummaryBox("Total obligations", "$900,000", new Color(213, 154, 154)));
        leftPanel.add(Box.createVerticalStrut(15));
        leftPanel.add(createSummaryBox("Net Balance", "$300,000", new Color(236, 229, 186)));

        // Panel derecho (Interesting data)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(220, 220, 220));
        rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        JLabel interestingLabel = new JLabel("Interesting data");
        interestingLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        rightPanel.add(interestingLabel);

        // Dividir en dos columnas
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(350);
        splitPane.setEnabled(false);
        splitPane.setBorder(null);

        add(splitPane, BorderLayout.CENTER);
    }

    private JPanel createSummaryBox(String label, String value, Color bgColor) {
        JPanel box = new JPanel();
        box.setBackground(bgColor);
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBorder(new EmptyBorder(15, 15, 15, 15));
        box.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel title = new JLabel(label);
        title.setFont(new Font("SansSerif", Font.BOLD, 14));

        JLabel amount = new JLabel(value);
        amount.setFont(new Font("SansSerif", Font.PLAIN, 20));

        box.add(title);
        box.add(Box.createVerticalStrut(5));
        box.add(amount);

        return box;
    }

    // Para probarlo
    public static void main(String[] args) {
        JFrame frame = new JFrame("Month Summary Mockup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setContentPane(new MonthSummary());
        frame.setVisible(true);
    }
}
