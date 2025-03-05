package com.alxbryann.foc.view;

/**
 *
 * @author barr2
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedJDialog extends JDialog {

    public RoundedJDialog(JFrame parent, String title, int width, int height, int cornerRadius) {
        super(parent, title, true); 
        setSize(width, height);
        setLocationRelativeTo(parent); 
        setResizable(false);
        setUndecorated(true);

  
        setShape(new RoundRectangle2D.Double(0, 0, width, height, cornerRadius, cornerRadius));

        JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

                g2.dispose();
            }
        };
        contentPanel.setBackground(new Color(240, 240, 240));
        contentPanel.setLayout(null);
        contentPanel.setBounds(0, 0, width, height); 

        setContentPane(contentPanel);
    }
}
