package com.alxbryann.foc.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.alxbryann.foc.view.glasspanepopup.GlassPanePopup;

public class ElementDetail extends JPanel {

    private final int id;
    private final String name;
    private final double cost;
    private final ViewController viewController;
    private final int dayNumber;

    private JLabel nameLabel;
    private JLabel costLabel;
    private JButton deleteButton;
    private JButton editButton;

    public ElementDetail(int id, String name, double cost, ViewController viewController, int dayNumber) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.viewController = viewController;
        this.dayNumber = dayNumber;

        // Only incomes are supported now
        initializeUI(new Color(144, 203, 173));
    }

    private void initializeUI(Color color) {
        setLayout(null);
        setPreferredSize(new Dimension(600, 80));
        setBackground(color);
        
        // Añadir listener para ajustar posiciones cuando cambie el tamaño
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                ElementDetail.this.updatePositions();
            }
        });

        // Nombre en posición proporcional
        nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Kantumruy Pro Medium", Font.PLAIN, 13));

        // Costo en posición proporcional
        costLabel = new JLabel("$" + String.format("%,.0f", cost));
        costLabel.setFont(new Font("Kantumruy Pro Medium", Font.PLAIN, 13));

        add(nameLabel);
        add(costLabel);

        deleteButton = new JButton();
        ImageIcon rawIcon = new ImageIcon(getClass().getResource("/img/trash.png"));
        Image scaledImage = rawIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon trashIcon = new ImageIcon(scaledImage);
        deleteButton.setIcon(trashIcon);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "Are you sure you want to delete Income '" + name + "'?";
                String title = "Confirm delete";
                
                Runnable onConfirm = () -> {
                    viewController.deleteTransactionById(id);
                    viewController.removeIncomeFromDayById(id, dayNumber);
                    updateView();
                };
                
                Runnable onCancel = () -> {
                };
                
                ConfirmationDialog confirmDialog = new ConfirmationDialog(message, title, onConfirm, onCancel);
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(ElementDetail.this);
                if (parentFrame != null) {
                    GlassPanePopup.install(parentFrame);
                    GlassPanePopup.showPopup(confirmDialog);
                }
            }

        });

        editButton = new JButton();
        rawIcon = new ImageIcon(getClass().getResource("/img/pencil.png"));
        scaledImage = rawIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon pencilIcon = new ImageIcon(scaledImage);
        editButton.setIcon(pencilIcon);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditIncome editIncomeWindow = new EditIncome(viewController, id, dayNumber);
                editIncomeWindow.setVisible(true);
                updateView();

            }

        });

        styleButton(deleteButton);
        styleButton(editButton);

        add(deleteButton);
        add(editButton);
        
        // Establecer posiciones iniciales
        updatePositions();
    }
    
    private void updatePositions() {
        int width = getPreferredSize().width;
        int height = getPreferredSize().height;
        
        // Calcular el tamaño de fuente basado en la altura del componente
        // Para altura 80px usa 20pt, para altura 40px usa 12pt
        int fontSize;
        if (height >= 70) {
            fontSize = 20; // Fuente más grande para DetailPerDay
        } else if (height >= 50) {
            fontSize = 15; // Fuente media
        } else {
            fontSize = 12; // Fuente pequeña para NextTransactionsPanel
        }
        Font currentFont = new Font("Kantumruy Pro Medium", Font.PLAIN, fontSize);
        nameLabel.setFont(currentFont);
        costLabel.setFont(currentFont);
        
        // Calcular el tamaño de los botones basado en la altura
        int buttonSize = (int) (height * 0.35);
        buttonSize = Math.max(20, Math.min(buttonSize, 28)); // Entre 20 y 28 px
        
        // Calcular posiciones proporcionalmente basadas en el ancho
        // Columna 1 (Nombre): 4% del ancho, ocupa ~32% del ancho
        int nameX = (int) (width * 0.04);
        int nameWidth = (int) (width * 0.32);
        
        // Columna 2 (Costo): 38% del ancho, ocupa ~36% del ancho
        int costX = (int) (width * 0.38);
        int costWidth = (int) (width * 0.36);
        
        // Columna 3 (Botones): comienzan al 76% del ancho
        int buttonsX = (int) (width * 0.76);
        int buttonSpacing = (int) (width * 0.12); // Espacio entre botones: 12% del ancho
        
        // Altura vertical centrada
        int labelY = (int) (height * 0.3);
        int buttonY = (int) ((height - buttonSize) / 2);
        
        // Altura de labels ajustada al tamaño de fuente
        int labelHeight = fontSize + 5;
        
        // Actualizar iconos de botones si es necesario
        updateButtonIcons(buttonSize);
        
        // Aplicar posiciones
        nameLabel.setBounds(nameX, labelY, nameWidth, labelHeight);
        costLabel.setBounds(costX, labelY, costWidth, labelHeight);
        deleteButton.setBounds(buttonsX, buttonY, buttonSize, buttonSize);
        editButton.setBounds(buttonsX + buttonSpacing, buttonY, buttonSize, buttonSize);
    }
    
    private void updateButtonIcons(int size) {
        // Actualizar icono del botón de eliminar
        ImageIcon rawIcon = new ImageIcon(getClass().getResource("/img/trash.png"));
        Image scaledImage = rawIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        ImageIcon trashIcon = new ImageIcon(scaledImage);
        deleteButton.setIcon(trashIcon);
        
        // Actualizar icono del botón de editar
        rawIcon = new ImageIcon(getClass().getResource("/img/pencil.png"));
        scaledImage = rawIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        ImageIcon pencilIcon = new ImageIcon(scaledImage);
        editButton.setIcon(pencilIcon);
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(new Font("SansSerif", Font.PLAIN, 18));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void updateView() {
        viewController.updateViewCalendar();
        viewController.updateNextIncomes();
        viewController.updateDetailContainer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
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
