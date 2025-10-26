package com.alxbryann.foc.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ConfirmationDialog extends JPanel {
    
    private boolean confirmed = false;
    private Runnable onConfirm;
    private Runnable onCancel;
    
    public ConfirmationDialog(String message, String title, Runnable onConfirm, Runnable onCancel) {
        this.onConfirm = onConfirm;
        this.onCancel = onCancel;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(400, 200));
        setLayout(new BorderLayout());
        
        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Kantumruy Pro", Font.BOLD, 20));
        titleLabel.setForeground(new Color(60, 60, 60));
        titlePanel.add(titleLabel);
        
        // Message panel
        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        messagePanel.setBackground(Color.WHITE);
        JLabel messageLabel = new JLabel("<html><div style='text-align: center; width: 300px;'>" + message + "</div></html>");
        messageLabel.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
        messageLabel.setForeground(new Color(80, 80, 80));
        messagePanel.add(messageLabel);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton yesButton = new JButton("Yes");
        yesButton.setPreferredSize(new Dimension(100, 40));
        yesButton.setFont(new Font("Kantumruy Pro", Font.BOLD, 14));
        // Aplicar estilo FlatLaf
        FlatLafStyleManager.applyCancelButtonStyle(yesButton);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                closeDialog();
                if (onConfirm != null) {
                    onConfirm.run();
                }
            }
        });
        
        JButton noButton = new JButton("No");
        noButton.setPreferredSize(new Dimension(100, 40));
        noButton.setFont(new Font("Kantumruy Pro", Font.BOLD, 14));
        // Aplicar estilo FlatLaf
        FlatLafStyleManager.applyConfirmButtonStyle(noButton);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                closeDialog();
                if (onCancel != null) {
                    onCancel.run();
                }
            }
        });
        
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        
        add(titlePanel, BorderLayout.NORTH);
        add(messagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void closeDialog() {
        // Close the glasspane popup
        com.alxbryann.foc.view.glasspanepopup.GlassPanePopup.closePopupLast();
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.dispose();
    }
    
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(200, 200, 200));
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        g2.dispose();
    }
    
    @Override
    public boolean isOpaque() {
        return false;
    }
}