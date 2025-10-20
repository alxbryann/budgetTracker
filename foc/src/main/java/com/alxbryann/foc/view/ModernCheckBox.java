package com.alxbryann.foc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alxbryann
 */
public class ModernCheckBox extends JCheckBox {

    public ModernCheckBox(String text) {
        super(text); // Llama al constructor de la clase padre (JCheckBox)
        setModernStyle(); // Aplica el estilo moderno
    }
    
    public ModernCheckBox(){
        setModernStyle();
    }

    private void setModernStyle() {
        // Personalizar la apariencia
        setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Cambiar la fuente
        setForeground(new Color(50, 50, 50)); // Cambiar el color del texto
        setBackground(Color.WHITE); // Cambiar el color de fondo
        setFocusPainted(false); // Eliminar el borde de enfoque
        setIcon(new CustomCheckBoxIcon()); // Icono personalizado para el checkbox
        setSelectedIcon(new CustomCheckBoxIcon(true)); // Icono personalizado cuando está seleccionado
    }
}

// Clase para personalizar el icono del JCheckBox
class CustomCheckBoxIcon implements Icon {

    private int width = 16;
    private int height = 16;
    private boolean selected;

    public CustomCheckBoxIcon() {
        this(false);
    }

    public CustomCheckBoxIcon(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar el fondo del checkbox
        g2d.setColor(new Color(200, 200, 200));
        g2d.fillRoundRect(x, y, width, height, 4, 4);

        // Dibujar el borde
        g2d.setColor(new Color(100, 100, 100));
        g2d.drawRoundRect(x, y, width, height, 4, 4);

        // Dibujar la marca de verificación si está seleccionado
        if (selected) {
            g2d.setColor(new Color(0, 120, 215));
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(x + 3, y + 8, x + 7, y + 12);
            g2d.drawLine(x + 7, y + 12, x + 13, y + 4);
        }

        g2d.dispose();
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }
}
