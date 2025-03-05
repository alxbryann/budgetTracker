package com.alxbryann.foc.view;

/**
 *
 * @author barr2
 */
import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    private int cornerRadius;

    public RoundedButton(String text, int cornerRadius) {
        super(text);
        this.cornerRadius = cornerRadius;
        setContentAreaFilled(false); // Desactiva el fondo predeterminado
        setFocusPainted(false); // Desactiva el borde de enfoque
        setBorderPainted(false); // Desactiva el borde predeterminado
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Colores de fondo y borde
        Color backgroundColor = getBackground();
        Color borderColor = getForeground();

        // Fondo del botón
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Borde del botón
        g2.setColor(backgroundColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        // Texto del botón
        super.paintComponent(g);
    }
}
