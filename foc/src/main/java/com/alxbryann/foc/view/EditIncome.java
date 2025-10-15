package com.alxbryann.foc.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author alxbryann
 */
public class EditIncome extends JPanel {

    private final ViewController viewController;
    private int id;
    private int dayNumber;
    
    private JTextArea nameIncome;
    private JTextArea amountIncome;
    private DatePicker datePicker;
    private ModernCheckBox isRepetitive;
    private JComboBox<String> colorComboBox;
    private ModernToggleButton weekOrMonth;

    public EditIncome(ViewController viewController, int id, int dayNumber) {
        this.viewController = viewController;
        this.id = id;
        this.dayNumber = dayNumber;
        editIncomeDialog();
    }

    private void editIncomeDialog() {
        RoundedJDialog modal = new RoundedJDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Edit Income", 400, 520, 30);
        modal.setTitle("Edit an Income");
        modal.setSize(400, 520);
        modal.setLayout(null);
        modal.setLocationRelativeTo(null);
        modal.setResizable(false);
        modal.setUndecorated(true);
        modal.getContentPane().setBackground(new Color(240, 240, 240));

        JPanel addIncome = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Dibujar el fondo con bordes redondeados
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                
                // Dibujar el borde con bordes redondeados
                g2.setColor(new Color(200, 200, 200));
                g2.setStroke(new BasicStroke(1.0f));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
                
                g2.dispose();
            }
        };
        addIncome.setLayout(null);
        addIncome.setBounds(0, 0, 400, 520);
        addIncome.setBackground(Color.WHITE);
        addIncome.setOpaque(false);

        JLabel title = new JLabel("Edit an Income");
        title.setFont(new Font("Lexend", Font.BOLD, 18));
        title.setBounds(45, 10, 500, 30);
        title.setForeground(new Color(60, 60, 60));
        addIncome.add(title);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
        nameLabel.setBounds(45, 50, 100, 20);
        nameLabel.setForeground(new Color(60, 60, 60));
        addIncome.add(nameLabel);

        JTextArea nameIncome = new JTextArea();
        nameIncome.setBounds(45, 75, 300, 40);
        nameIncome.setFont(new Font("Lexend", Font.PLAIN, 14));
        nameIncome.setBackground(new Color(217, 217, 217));
        nameIncome.setForeground(Color.BLACK);
        nameIncome.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.nameIncome = nameIncome; // Asignar a variable de instancia
        addIncome.add(nameIncome);

        JLabel priceLabel = new JLabel("Amount:");
        priceLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
        priceLabel.setBounds(45, 115, 100, 20);
        priceLabel.setForeground(new Color(60, 60, 60));
        addIncome.add(priceLabel);

        JTextArea amountIncome = new JTextArea();
        amountIncome.setBounds(45, 140, 300, 40);
        amountIncome.setFont(new Font("Lexend", Font.PLAIN, 14));
        amountIncome.setBackground(new Color(217, 217, 217));
        amountIncome.setForeground(Color.BLACK);
        amountIncome.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.amountIncome = amountIncome; // Asignar a variable de instancia
        addIncome.add(amountIncome);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
        dateLabel.setBounds(45, 180, 100, 20);
        dateLabel.setForeground(new Color(60, 60, 60));
        addIncome.add(dateLabel);

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
        addIncome.add(datePicker);

        JLabel textIsRepetitive = new JLabel("Is repetitive?");
        textIsRepetitive.setBounds(45, 260, 100, 20);
        textIsRepetitive.setFont(new Font("Lexend", Font.PLAIN, 14));
        ModernCheckBox isRepetitive = new ModernCheckBox();
        isRepetitive.setBounds(150, 260, 30, 20);
        this.isRepetitive = isRepetitive; // Asignar a variable de instancia
        addIncome.add(textIsRepetitive);
        addIncome.add(isRepetitive);

        JLabel colorLabel = new JLabel("Color:");
        colorLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
        colorLabel.setBounds(45, 290, 100, 20);
        colorLabel.setForeground(new Color(60, 60, 60));
        addIncome.add(colorLabel);

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
        colorComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                return label;
            }
        });
        colorComboBox.setBounds(45, 320, 300, 30);
        this.colorComboBox = colorComboBox; 
        addIncome.add(colorComboBox);

        RoundedButton closeButton = new RoundedButton("Close", 30);
        closeButton.setBounds(120, 420, 150, 40);
        closeButton.setBackground(new Color(86, 60, 16));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Lexend", Font.PLAIN, 16));
        closeButton.addActionListener(e -> modal.dispose());
        addIncome.add(closeButton);

        RoundedButton send = new RoundedButton("Update", 30);
        send.setBounds(120, 370, 150, 40);
        send.setBackground(new Color(86, 60, 16));
        send.setForeground(Color.WHITE);
        send.setFont(new Font("Lexend", Font.PLAIN, 16));
        ModernToggleButton weekOrMonth = new ModernToggleButton();
        this.weekOrMonth = weekOrMonth;

        isRepetitive.addActionListener(e -> {
            if (isRepetitive.isSelected()) {
                weekOrMonth.addActionListener(evt -> {
                    if (weekOrMonth.isSelected()) {
                        weekOrMonth.setText("Week");
                    } else {
                        weekOrMonth.setText("Month");
                    }
                });
                weekOrMonth.setBounds(45, 300, 100, 30);
                addIncome.add(weekOrMonth);
                colorLabel.setBounds(45, 350, 100, 20);
                colorComboBox.setBounds(45, 380, 300, 30);
                send.setBounds(120, 420, 150, 40);
                closeButton.setBounds(120, 470, 150, 40);
            } else {
                addIncome.remove(weekOrMonth);
                colorLabel.setBounds(45, 290, 100, 20);
                colorComboBox.setBounds(45, 320, 300, 30);
                send.setBounds(120, 370, 150, 40);
                closeButton.setBounds(120, 420, 150, 40);
            }
            addIncome.revalidate();
            addIncome.repaint();
        });

        send.addActionListener(e -> {
            String name = nameIncome.getText().trim(); 
            String amount = amountIncome.getText().trim();
            LocalDate selectedDate = datePicker.getDate();
            if (selectedDate.getDayOfMonth() == 31) {
                UIManager.put("OptionPane.background", new Color(245, 245, 235));
                UIManager.put("Panel.background", new Color(245, 245, 235));
                UIManager.put("OptionPane.messageForeground", new Color(0, 111, 74));
                UIManager.put("Button.background", new Color(0, 111, 74));
                UIManager.put("Button.foreground", Color.white);
                UIManager.put("Button.focus", new Color(0, 90, 60));

                JOptionPane.showMessageDialog(
                        modal,
                        "El día 31 no es válido. Se ajustará automáticamente al día 30.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );

                selectedDate = selectedDate.withDayOfMonth(30);
            }

            String selectedColorName = (String) colorComboBox.getSelectedItem();
            boolean isRepetitiveIncome = false;
            boolean weekOrMonthIncome = false;

            if (isRepetitive.isSelected()) {
                isRepetitiveIncome = true;
                if (weekOrMonth.isSelected()) {
                    weekOrMonthIncome = true;
                } else {
                    weekOrMonthIncome = false;
                }
            }

            if (name.isEmpty() || amount.isEmpty() || selectedDate == null) {
                JOptionPane.showMessageDialog(modal, "Please complete all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
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
                if (weekOrMonthIncome) {
                    viewController.editIncome(id, name, amount, date, selectedColor, isRepetitiveIncome, true, false);
                } else {
                    viewController.editIncome(id, name, amount, date, selectedColor, isRepetitiveIncome, false, true);
                }
            }

            nameIncome.setText("");
            amountIncome.setText("");
            datePicker.clear();
            colorComboBox.setSelectedIndex(0);
            JOptionPane.showMessageDialog(modal, "Updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            modal.dispose();
        });

        addIncome.add(send);
        modal.add(addIncome);
        loadIncomeInformation(id, dayNumber);
        
        modal.setVisible(true);
    }

    public void loadIncomeInformation(int id, int dayNumber) {
        /*try { 
            HashMap<String, Object> temporalIncomeInformation = viewController.getInformationOfIncome(id);
            String name = String.valueOf(temporalIncomeInformation.get("name"));
            String date = String.valueOf(temporalIncomeInformation.get("date"));
            String amount = String.valueOf(temporalIncomeInformation.get("amount"));
            String rgb = String.valueOf(temporalIncomeInformation.get("rgb"));
            boolean isIncomeRepetitive = Boolean.parseBoolean(String.valueOf(temporalIncomeInformation.get("isRepetitive")));
            boolean repetitiveByWeek = Boolean.parseBoolean(String.valueOf(temporalIncomeInformation.get("repetitiveByWeek")));
            boolean repetitiveByMonth = Boolean.parseBoolean(String.valueOf(temporalIncomeInformation.get("repetitiveByMonth")));

            if (temporalIncomeInformation != null) {
            if (nameIncome != null) {
                nameIncome.setText(name);
            }

            if (amountIncome != null) {
                amountIncome.setText(String.valueOf(amount));
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
                isRepetitive.setSelected(isIncomeRepetitive);

                if (isIncomeRepetitive && weekOrMonth != null) {
                weekOrMonth.addActionListener(evt -> {
                    if (weekOrMonth.isSelected()) {
                    weekOrMonth.setText("Week");
                    } else {
                    weekOrMonth.setText("Month");
                    }
                });

                weekOrMonth.setBounds(45, 300, 100, 30);
                if (weekOrMonth.getParent() == null) {
                    java.awt.Container parent = isRepetitive.getParent();
                    if (parent != null) {
                    parent.add(weekOrMonth);

                    if (colorComboBox != null) {
                        colorComboBox.setBounds(45, 380, 300, 30);
                    }
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
                    parent.revalidate();
                    parent.repaint();
                    }
                }

                if (repetitiveByWeek) {
                    weekOrMonth.setSelected(true);
                    weekOrMonth.setText("Week");
                } else if (repetitiveByMonth) {
                    weekOrMonth.setSelected(false);
                    weekOrMonth.setText("Month");
                }
                }
            }
                if (colorComboBox != null && rgb != null) {
                    Color incomeColor = parseColorFromRgb(rgb);
                    
                   
                    Color[] pastelColors = {
                        new Color(194, 80, 80), 
                        new Color(77, 189, 133), 
                        new Color(135, 129, 129), 
                        new Color(86, 141, 242), 
                        new Color(69, 74, 183), 
                        new Color(85, 37, 37) 
                    };
                    
                    int closestColorIndex = findClosestColorIndex(incomeColor, pastelColors);
                    colorComboBox.setSelectedIndex(closestColorIndex);
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading income information: " + e.getMessage());
            e.printStackTrace();
        }*/
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

}
