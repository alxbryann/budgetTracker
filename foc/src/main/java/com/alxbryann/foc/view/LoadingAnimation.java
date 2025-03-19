package com.alxbryann.foc.view;

/**
 *
 * @author alxbryann
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

public class LoadingAnimation extends JPanel {
    private double angle = 0;
    private boolean loading = false; // Ahora inicia en falso
    private Timer timer;

    public LoadingAnimation() {
        timer = new Timer(50, e -> {
            if (loading) {
                angle += 10;
                repaint();
            } else {
                timer.stop();
                repaint();
            }
        });
    }

    // Método para iniciar la animación de carga
    public void startLoading() {
        loading = true;
        timer.start();
        repaint();
    }

    // Método para detener la animación y mostrar el check verde
    public void stopLoading() {
        loading = false;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (loading) {
            // Círculo gris de fondo
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fill(new Ellipse2D.Double(40, 40, 80, 80));
            // Arco azul que gira
            g2d.setColor(Color.BLUE);
            g2d.fill(new Arc2D.Double(40, 40, 80, 80, angle, 90, Arc2D.OPEN));
        } else {
            // Checkmark verde cuando la carga termina
            g2d.setColor(Color.GREEN);
            g2d.setStroke(new BasicStroke(5));
            g2d.drawLine(55, 80, 75, 100);
            g2d.drawLine(75, 100, 110, 60);
        }
    }
}
