package com.alxbryann.foc.model;

import com.alxbryann.foc.view.View;
import com.alxbryann.foc.view.FontManager;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // Configurar FlatLaf Look and Feel
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Failed to initialize FlatLaf Look and Feel: " + e.getMessage());
        }
        
        FontManager.loadAllFontsFromResources();
        Controller controller = new Controller();
        Model model = new Model(controller);
        controller.setModel(model);
        new View(controller);
    }
}
