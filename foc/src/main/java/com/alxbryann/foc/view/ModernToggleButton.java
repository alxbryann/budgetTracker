package com.alxbryann.foc.view;

/**
 *
 * @author barr2
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModernToggleButton extends JToggleButton {

    private final int WIDTH = 80;
    private final int HEIGHT = 30;
    private int circleX = 5;
    private Color backgroundColor =  new Color(69, 74, 183);

    public ModernToggleButton() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);

        addActionListener(e -> animateSwitch());
    }

    private void animateSwitch() {
        boolean isOn = isSelected();
        Timer timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isOn && circleX < WIDTH - HEIGHT + 5) {
                    circleX += 5;
                } else if (!isOn && circleX > 5) {
                    circleX -= 5;
                } else {
                    ((Timer) e.getSource()).stop();
                }
                backgroundColor = isOn ? new Color(243, 150, 0) : new Color(69, 74, 183);
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, WIDTH, HEIGHT, HEIGHT, HEIGHT);
        g2.setColor(Color.WHITE);
        g2.fillOval(circleX, 5, HEIGHT - 10, HEIGHT - 10);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        if (isSelected()) {
            g2.drawString("Week", 10, HEIGHT / 2 + 5);
        } else {
            g2.drawString("Month", WIDTH - 50, HEIGHT / 2 + 5);
        }
    }
}
