package com.alxbryann.foc.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.alxbryann.foc.view.glasspanepopup.GlassPanePopup;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 *
 * @author alxbryann
 */
public class EditTransaction extends JPanel {

    private final ViewController viewController;
    private int id;
    private int dayNumber;

    private JTextArea nameTransaction;
    private JTextArea amountTransaction;
    private DatePicker datePicker;
    private ModernCheckBox isRepetitive;
    private JComboBox<String> colorComboBox;
    private ModernToggleButton weekOrMonth;
    private JLabel colorLabelRef;

    public EditTransaction(ViewController viewController, int id, int dayNumber) {
        this.viewController = viewController;
        this.id = id;
        this.dayNumber = dayNumber;
        buildEditPanel();
    }

    private void buildEditPanel() {
        setOpaque(false);
        setLayout(null);
        setPreferredSize(new Dimension(400, 520));

        JPanel addTransaction = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Dibujar el fondo con bordes rectos
                g2.setColor(getBackground());
                g2.fillRect(0, 0, getWidth(), getHeight());

                // Dibujar el borde con esquinas rectas
                g2.setColor(new Color(200, 200, 200));
                g2.setStroke(new BasicStroke(1.0f));
                g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

                g2.dispose();
            }
        };
        addTransaction.setLayout(null);
        addTransaction.setBounds(0, 0, 400, 520);
        addTransaction.setBackground(Color.WHITE);
        addTransaction.setOpaque(true);

        JLabel title = new JLabel("Edit Transaction");
        title.setFont(new Font("Lexend", Font.BOLD, 18));
        title.setBounds(45, 10, 500, 30);
        title.setForeground(new Color(60, 60, 60));
        addTransaction.add(title);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
        nameLabel.setBounds(45, 50, 100, 20);
        nameLabel.setForeground(new Color(60, 60, 60));
        addTransaction.add(nameLabel);

        JTextArea nameTransaction = new JTextArea();
        nameTransaction.setBounds(45, 75, 300, 40);
        nameTransaction.setFont(new Font("Lexend", Font.PLAIN, 14));
        nameTransaction.setBackground(new Color(217, 217, 217));
        nameTransaction.setForeground(Color.BLACK);
        nameTransaction.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.nameTransaction = nameTransaction; // Asignar a variable de instancia
        addTransaction.add(nameTransaction);

        JLabel priceLabel = new JLabel("Amount:");
        priceLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
        priceLabel.setBounds(45, 115, 100, 20);
        priceLabel.setForeground(new Color(60, 60, 60));
        addTransaction.add(priceLabel);

        JTextArea amountTransaction = new JTextArea();
        amountTransaction.setBounds(45, 140, 300, 40);
        amountTransaction.setFont(new Font("Lexend", Font.PLAIN, 14));
        amountTransaction.setBackground(new Color(217, 217, 217));
        amountTransaction.setForeground(Color.BLACK);
        amountTransaction.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.amountTransaction = amountTransaction; // Asignar a variable de instancia
        addTransaction.add(amountTransaction);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
        dateLabel.setBounds(45, 180, 100, 20);
        dateLabel.setForeground(new Color(60, 60, 60));
        addTransaction.add(dateLabel);

        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFormatForDatesCommonEra("yyyy-MM-dd");
        dateSettings.setAllowKeyboardEditing(false);
        Font datePickerFont = new Font("Lexend", Font.PLAIN, 14);
        dateSettings.setFontValidDate(datePickerFont);
        dateSettings.setFontVetoedDate(datePickerFont);
        dateSettings.setFontMonthAndYearMenuLabels(datePickerFont);
        dateSettings.setColor(DatePickerSettings.DateArea.TextFieldBackgroundValidDate, new Color(217, 217, 217));
        DatePicker datePicker = new DatePicker(dateSettings);
        datePicker.setBounds(45, 205, 300, 40);
        this.datePicker = datePicker; // Asignar a variable de instancia
        addTransaction.add(datePicker);

        JLabel textIsRepetitive = new JLabel("Is repetitive?");
        textIsRepetitive.setBounds(45, 260, 100, 20);
        textIsRepetitive.setFont(new Font("Lexend", Font.PLAIN, 14));
        ModernCheckBox isRepetitive = new ModernCheckBox();
        isRepetitive.setBounds(150, 260, 30, 20);
        isRepetitive.setFont(new Font("Lexend", Font.PLAIN, 14));
        this.isRepetitive = isRepetitive; // Asignar a variable de instancia
        addTransaction.add(textIsRepetitive);
        addTransaction.add(isRepetitive);

        // Agregar weekOrMonth toggle button
        ModernToggleButton weekOrMonth = new ModernToggleButton();
        weekOrMonth.setFont(new Font("Lexend", Font.PLAIN, 14));
        weekOrMonth.setBounds(45, 300, 100, 30);
        this.weekOrMonth = weekOrMonth;
        addTransaction.add(weekOrMonth);

        JLabel colorLabel = new JLabel("Color:");
        colorLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
        colorLabel.setBounds(45, 350, 100, 20);
        colorLabel.setForeground(new Color(60, 60, 60));
        addTransaction.add(colorLabel);
        this.colorLabelRef = colorLabel;

        // Lista de colores pastel
        Color[] pastelColors = {
                new Color(194, 80, 80), // Rojo
                new Color(77, 189, 133), // Verde
                new Color(135, 129, 129), // Gris
                new Color(86, 141, 242), // Azul
                new Color(69, 74, 183), // Morado
                new Color(85, 37, 37) // Cafe
        };

        String[] colorNames = {
                "Red",
                "Green",
                "Gray",
                "Blue",
                "Purple",
                "Brown"
        };

        JComboBox<String> colorComboBox = new JComboBox<>(colorNames);
        colorComboBox.setFont(new Font("Lexend", Font.PLAIN, 14));
        colorComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
                        cellHasFocus);
                label.setFont(new Font("Lexend", Font.PLAIN, 14));
                if (index >= 0 && index < pastelColors.length) {
                    Color color = pastelColors[index];
                    label.setIcon(new ColorIcon(color, 20, 20));
                }
                return label;
            }
        });
        colorComboBox.setBounds(45, 380, 300, 30);
        this.colorComboBox = colorComboBox;
        addTransaction.add(colorComboBox);

        JButton send = new JButton("Update");
        send.setBounds(120, 420, 150, 40);
        send.setFont(new Font("Lexend", Font.PLAIN, 16));
        // Aplicar estilo FlatLaf - verde como Income
        FlatLafStyleManager.applySuccessButtonStyle(send);
        addTransaction.add(send);

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(120, 470, 150, 40);
        closeButton.setFont(new Font("Lexend", Font.PLAIN, 16));
        // Aplicar estilo FlatLaf - rojo como Expense
        FlatLafStyleManager.applyDangerButtonStyle(closeButton);
        closeButton.addActionListener(e -> GlassPanePopup.closePopupLast());
        addTransaction.add(closeButton);

        // Configurar acciones del weekOrMonth toggle
        weekOrMonth.addActionListener(evt -> {
            if (weekOrMonth.isSelected()) {
                weekOrMonth.setText("Week");
            } else {
                weekOrMonth.setText("Month");
            }
        });

        isRepetitive.addActionListener(e -> {
            // Ya no necesitamos mover los elementos porque están en sus posiciones finales
            // Solo mostramos u ocultamos el weekOrMonth toggle
            if (isRepetitive.isSelected()) {
                weekOrMonth.setVisible(true);
            } else {
                weekOrMonth.setVisible(false);
            }
            addTransaction.revalidate();
            addTransaction.repaint();
        });

        send.addActionListener(e -> {
            String name = nameTransaction.getText().trim();
            String amount = amountTransaction.getText().trim();
            LocalDate selectedDate = datePicker.getDate();

            String selectedColorName = (String) colorComboBox.getSelectedItem();
            boolean isRepetitiveTransaction = false;
            boolean weekOrMonthTransaction = false;

            if (isRepetitive.isSelected()) {
                isRepetitiveTransaction = true;
                if (weekOrMonth.isSelected()) {
                    weekOrMonthTransaction = true;
                } else {
                    weekOrMonthTransaction = false;
                }
            }

            if (name.isEmpty() || amount.isEmpty() || selectedDate == null) {
                JOptionPane.showMessageDialog(EditTransaction.this, "Please complete all the fields.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Ajustar día 31 inválido después de validar que la fecha no sea null
            if (selectedDate.getDayOfMonth() == 31) {
                UIManager.put("OptionPane.background", new Color(245, 245, 235));
                UIManager.put("Panel.background", new Color(245, 245, 235));
                UIManager.put("OptionPane.messageForeground", new Color(0, 111, 74));
                UIManager.put("Button.background", new Color(0, 111, 74));
                UIManager.put("Button.foreground", Color.white);
                UIManager.put("Button.focus", new Color(0, 90, 60));

                JOptionPane.showMessageDialog(
                        EditTransaction.this,
                        "El día 31 no es válido. Se ajustará automáticamente al día 30.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);

                selectedDate = selectedDate.withDayOfMonth(30);
            }

            Color selectedColor = null;
            switch (selectedColorName) {
                case "Red" ->
                    selectedColor = pastelColors[0];
                case "Green" ->
                    selectedColor = pastelColors[1];
                case "Gray" ->
                    selectedColor = pastelColors[2];
                case "Blue" ->
                    selectedColor = pastelColors[3];
                case "Purple" ->
                    selectedColor = pastelColors[4];
                case "Brown" ->
                    selectedColor = pastelColors[5];
            }

            String date = selectedDate.toString();

            if (viewController != null) {
                // Llamar al método de edición en lugar de creación
                if (weekOrMonthTransaction) {
                    viewController.editTransaction(id, name, amount, date, selectedColor, isRepetitiveTransaction, true,
                            false);
                } else {
                    viewController.editTransaction(id, name, amount, date, selectedColor, isRepetitiveTransaction,
                            false, true);
                }
                // Reasignar y refrescar vistas
                viewController.assignTransactionToDays();
                viewController.updateNextTransactions();
                viewController.updateDetailContainer();
            }

            nameTransaction.setText("");
            amountTransaction.setText("");
            datePicker.clear();
            colorComboBox.setSelectedIndex(0);
            JOptionPane.showMessageDialog(EditTransaction.this, "Updated successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            GlassPanePopup.closePopupLast();
        });

        addTransaction.add(send);
        add(addTransaction);
        loadTransactionInformation(id, dayNumber);
    }

    @SuppressWarnings("unchecked")
    public void loadTransactionInformation(int id, int dayNumber) {
        try {
            HashMap<String, Object> temporalTransactionInformation = (HashMap<String, Object>) viewController
                    .getInformationOfTransaction(id);
            String name = String.valueOf(temporalTransactionInformation.get("name"));
            String date = String.valueOf(temporalTransactionInformation.get("date"));
            String amount = String.valueOf(temporalTransactionInformation.get("amount"));
            String rgb = String.valueOf(temporalTransactionInformation.get("rgb"));
            boolean isTransactionRepetitive = Boolean
                    .parseBoolean(String.valueOf(temporalTransactionInformation.get("isRepetitive")));
            boolean repetitiveByWeek = Boolean
                    .parseBoolean(String.valueOf(temporalTransactionInformation.get("repetitiveByWeek")));
            boolean repetitiveByMonth = Boolean
                    .parseBoolean(String.valueOf(temporalTransactionInformation.get("repetitiveByMonth")));

            if (temporalTransactionInformation != null) {
                if (nameTransaction != null) {
                    nameTransaction.setText(name);
                }

                if (amountTransaction != null) {
                    amountTransaction.setText(String.valueOf(amount));
                }

                if (datePicker != null && date != null && !date.isEmpty() && !"null".equalsIgnoreCase(date)) {
                    try {
                        LocalDate localDate = parseDate(date);
                        datePicker.setDate(localDate);
                    } catch (DateTimeParseException ex) {
                        System.err.println("Error parsing date: " + date + " - " + ex.getMessage());
                        datePicker.clear();
                    }
                } else if (datePicker != null) {
                    datePicker.clear();
                }

                if (isRepetitive != null) {
                    // Seleccionar/limpiar el checkbox según datos
                    isRepetitive.setSelected(isTransactionRepetitive);
                    isRepetitive.repaint();

                    // Configurar el toggle y layout como si el usuario hubiese interactuado
                    java.awt.Container parent = isRepetitive.getParent();
                    if (parent != null) {
                        // Asegurar estado limpio
                        if (weekOrMonth != null && weekOrMonth.getParent() == parent) {
                            parent.remove(weekOrMonth);
                        }

                        if (isTransactionRepetitive && weekOrMonth != null) {
                            // Colocar toggle y ajustar posiciones
                            weekOrMonth.setBounds(45, 300, 100, 30);
                            // Establecer texto/estado inicial coherente
                            if (repetitiveByWeek) {
                                weekOrMonth.setSelected(true);
                                weekOrMonth.setText("Week");
                            } else if (repetitiveByMonth) {
                                weekOrMonth.setSelected(false);
                                weekOrMonth.setText("Month");
                            }
                            parent.add(weekOrMonth);
                            if (colorLabelRef != null)
                                colorLabelRef.setBounds(45, 350, 100, 20);
                            if (colorComboBox != null)
                                colorComboBox.setBounds(45, 380, 300, 30);
                            for (java.awt.Component comp : parent.getComponents()) {
                                if (comp instanceof RoundedButton) {
                                    RoundedButton button = (RoundedButton) comp;
                                    if ("Update".equals(button.getText())) {
                                        button.setBounds(120, 420, 150, 40);
                                    } else if ("Close".equals(button.getText())) {
                                        button.setBounds(120, 470, 150, 40);
                                    }
                                }
                            }
                        } else {
                            // No es repetitivo: posiciones por defecto
                            if (colorLabelRef != null)
                                colorLabelRef.setBounds(45, 290, 100, 20);
                            if (colorComboBox != null)
                                colorComboBox.setBounds(45, 320, 300, 30);
                            for (java.awt.Component comp : parent.getComponents()) {
                                if (comp instanceof RoundedButton) {
                                    RoundedButton button = (RoundedButton) comp;
                                    if ("Update".equals(button.getText())) {
                                        button.setBounds(120, 370, 150, 40);
                                    } else if ("Close".equals(button.getText())) {
                                        button.setBounds(120, 420, 150, 40);
                                    }
                                }
                            }
                        }
                        parent.revalidate();
                        parent.repaint();
                    }
                }
                if (colorComboBox != null && rgb != null) {
                    Color TransactionColor = parseColorFromRgb(rgb);

                    Color[] pastelColors = {
                            new Color(194, 80, 80),
                            new Color(77, 189, 133),
                            new Color(135, 129, 129),
                            new Color(86, 141, 242),
                            new Color(69, 74, 183),
                            new Color(85, 37, 37)
                    };

                    int closestColorIndex = findClosestColorIndex(TransactionColor, pastelColors);
                    colorComboBox.setSelectedIndex(closestColorIndex);
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading Transaction information: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Color parseColorFromRgb(String rgb) {
        try {
            String[] parts = rgb.split(", ");
            if (parts.length >= 3) {
                int r = Integer.parseInt(parts[0].trim());
                int g = Integer.parseInt(parts[1].trim());
                int b = Integer.parseInt(parts[2].trim());
                return new Color(r, g, b);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error parsing RGB color: " + rgb);
        }
        return new Color(194, 80, 80);
    }

    private int findClosestColorIndex(Color targetColor, Color[] colors) {
        int closestIndex = 0;
        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < colors.length; i++) {
            double distance = calculateColorDistance(targetColor, colors[i]);
            if (distance < minDistance) {
                minDistance = distance;
                closestIndex = i;
            }
        }

        return closestIndex;
    }

    private double calculateColorDistance(Color c1, Color c2) {
        int deltaR = c1.getRed() - c2.getRed();
        int deltaG = c1.getGreen() - c2.getGreen();
        int deltaB = c1.getBlue() - c2.getBlue();
        return Math.sqrt(deltaR * deltaR + deltaG * deltaG + deltaB * deltaB);
    }

    private LocalDate parseDate(String dateString) throws DateTimeParseException {
        if (dateString == null || dateString.trim().isEmpty()) {
            throw new DateTimeParseException("Date string is null or empty", dateString, 0);
        }

        String trimmedDate = dateString.trim();

        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy"),
                DateTimeFormatter.ofPattern("MM-dd-yyyy"),
                DateTimeFormatter.ISO_LOCAL_DATE,

                DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss 'GMT'XXX yyyy", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH),
                DateTimeFormatter.RFC_1123_DATE_TIME
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                if (trimmedDate.contains("GMT") || trimmedDate.contains(":")) {
                    ZonedDateTime zonedDateTime = ZonedDateTime.parse(trimmedDate, formatter);
                    return zonedDateTime.toLocalDate();
                } else {
                    return LocalDate.parse(trimmedDate, formatter);
                }
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new DateTimeParseException("Unable to parse date: " + trimmedDate +
                ". Supported formats: yyyy-MM-dd, yyyy/MM/dd, dd/MM/yyyy, dd-MM-yyyy, MM/dd/yyyy, MM-dd-yyyy, and datetime formats",
                trimmedDate, 0);
    }

    // Clase interna para renderizar iconos de colores
    public class ColorIcon implements Icon {

        private final Color color;
        private final int width;
        private final int height;

        public ColorIcon(Color color, int width, int height) {
            this.color = color;
            this.width = width;
            this.height = height;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);
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

}
