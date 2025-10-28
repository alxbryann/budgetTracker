package com.alxbryann.foc.view.glasspanepopup;


import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author Raven
 */
public class WindowSnapshots {

    private final JFrame frame;

    private JComponent snapshotLayer;
    private boolean inShowSnapshot;

    public WindowSnapshots(JFrame frame) {
        this.frame = frame;
    }

    public void createSnapshot() {
        if (inShowSnapshot) {
            return;
        }
        inShowSnapshot = true;
        if (frame.isShowing()) {
            // Use BufferedImage instead of VolatileImage for better macOS compatibility
            java.awt.image.BufferedImage snapshot = new java.awt.image.BufferedImage(
                frame.getWidth(), frame.getHeight(), java.awt.image.BufferedImage.TYPE_INT_RGB);
            
            if (snapshot != null) {
                JLayeredPane layeredPane = frame.getLayeredPane();
                Graphics snapshotGraphics = snapshot.getGraphics();
                // Enable antialiasing for smoother rendering on macOS
                if (snapshotGraphics instanceof java.awt.Graphics2D) {
                    java.awt.Graphics2D g2d = (java.awt.Graphics2D) snapshotGraphics;
                    g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, 
                                       java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setRenderingHint(java.awt.RenderingHints.KEY_RENDERING, 
                                       java.awt.RenderingHints.VALUE_RENDER_QUALITY);
                }
                layeredPane.paint(snapshotGraphics);
                snapshotGraphics.dispose();
                
                snapshotLayer = new JComponent() {
                    @Override
                    public void paint(Graphics g) {
                        // Add null check and use Graphics2D for better rendering
                        if (snapshot != null) {
                            if (g instanceof java.awt.Graphics2D) {
                                java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
                                g2d.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, 
                                                   java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                            }
                            g.drawImage(snapshot, 0, 0, null);
                        }
                    }

                    @Override
                    public void removeNotify() {
                        super.removeNotify();
                        // BufferedImage doesn't need explicit flushing but we can help GC
                        if (snapshot != null) {
                            snapshot.flush();
                        }
                    }
                };
                snapshotLayer.setSize(layeredPane.getSize());
                layeredPane.add(snapshotLayer, Integer.valueOf(JLayeredPane.DRAG_LAYER + 1));
            }
        }
    }

    public void removeSnapshot() {
        frame.getLayeredPane().remove(snapshotLayer);
        inShowSnapshot = false;
    }
}
