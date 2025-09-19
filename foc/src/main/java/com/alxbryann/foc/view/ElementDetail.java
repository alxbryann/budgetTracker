package com.alxbryann.foc.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ElementDetail extends JPanel {

    private final int id;
    private final String name;
    private final double cost;
    private final boolean isFinancialObligation;

    private JLabel nameLabel;
    private JLabel costLabel;
    private JButton deleteButton;
    private JButton editButton;

    public ElementDetail(int id, String name, double cost, boolean isFinancialObligation) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.isFinancialObligation = isFinancialObligation;

        if (isFinancialObligation) {
            initializeUI(new Color(210, 133, 133));
        } else {
            initializeUI(new Color(144, 203, 173));
        }
    }

    private void initializeUI(Color color) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 80));
        setBackground(color);

        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 25));
        textPanel.setOpaque(false);

        nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));

        costLabel = new JLabel("$" + String.format("%,.0f", cost));
        costLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        textPanel.add(nameLabel);
        textPanel.add(costLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        buttonPanel.setOpaque(false);

        deleteButton = new JButton();
        ImageIcon rawIcon = new ImageIcon(getClass().getResource("/trash.png"));
        Image scaledImage = rawIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon trashIcon = new ImageIcon(scaledImage);
        deleteButton.setIcon(trashIcon);
        deleteButton.setPreferredSize(new Dimension(40, 40));
        deleteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("id: " + id);
            }
            
        });
        
        
        editButton = new JButton();
        rawIcon = new ImageIcon(getClass().getResource("/pencil.png"));
        scaledImage = rawIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon pencilIcon = new ImageIcon(scaledImage);
        editButton.setIcon(pencilIcon);
        editButton.setPreferredSize(new Dimension(40, 40));
        editButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("id: " + id);
            }
            
        });
        

        styleButton(deleteButton);
        styleButton(editButton);

        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);

        add(textPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.EAST);
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(new Font("SansSerif", Font.PLAIN, 18));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
