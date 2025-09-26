package com.alxbryann.foc.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.time.LocalDate;
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
public class EditPayment extends JPanel {

    private final ViewController viewController;

    public EditPayment(ViewController viewController) {
        this.viewController = viewController;
    }

    private void editPaymentDialog() {
        RoundedJDialog modal = new RoundedJDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Edio New Financial Obligation", 400, 520, 30);
        modal.setTitle("Edit a Financial Obligation");
        modal.setSize(400, 520);
        modal.setLayout(null);
        modal.setLocationRelativeTo(null);
        modal.setResizable(false);
        modal.setUndecorated(true);
        modal.getContentPane().setBackground(new Color(240, 240, 240));

        JPanel addFo = new JPanel();
        addFo.setLayout(null);
        addFo.setBounds(0, 0, 400, 520);
        addFo.setBackground(Color.WHITE);
        addFo.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));

        JLabel title = new JLabel("Edit a Financial Obligation");
        title.setFont(new Font("Lexend", Font.BOLD, 18));
        title.setBounds(45, 10, 500, 30);
        title.setForeground(new Color(60, 60, 60));
        addFo.add(title);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
        nameLabel.setBounds(45, 50, 100, 20);
        nameLabel.setForeground(new Color(60, 60, 60));
        addFo.add(nameLabel);

        JTextArea nameFo = new JTextArea();
        nameFo.setBounds(45, 75, 300, 40);
        nameFo.setFont(new Font("Lexend", Font.PLAIN, 14));
        nameFo.setBackground(new Color(217, 217, 217));
        nameFo.setForeground(Color.BLACK);
        nameFo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addFo.add(nameFo);

        JLabel priceLabel = new JLabel("Cost:");
        priceLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
        priceLabel.setBounds(45, 115, 100, 20);
        priceLabel.setForeground(new Color(60, 60, 60));
        addFo.add(priceLabel);

        JTextArea costFo = new JTextArea();
        costFo.setBounds(45, 140, 300, 40);
        costFo.setFont(new Font("Lexend", Font.PLAIN, 14));
        costFo.setBackground(new Color(217, 217, 217));
        costFo.setForeground(Color.BLACK);
        costFo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addFo.add(costFo);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
        dateLabel.setBounds(45, 180, 100, 20);
        dateLabel.setForeground(new Color(60, 60, 60));
        addFo.add(dateLabel);

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
        addFo.add(datePicker);

        JLabel textIsRepetitive = new JLabel("Is repetitive?");
        textIsRepetitive.setBounds(45, 260, 100, 20);
        textIsRepetitive.setFont(new Font("Lexend", Font.PLAIN, 14));
        ModernCheckBox isRepetitive = new ModernCheckBox();
        isRepetitive.setBounds(150, 260, 30, 20);
        addFo.add(textIsRepetitive);
        addFo.add(isRepetitive);

        JLabel colorLabel = new JLabel("Color:");
        colorLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
        colorLabel.setBounds(45, 290, 100, 20);
        colorLabel.setForeground(new Color(60, 60, 60));
        addFo.add(colorLabel);

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
                if (index >= 0 && index < pastelColors.length) {
                    Color color = pastelColors[index];
                    //label.setIcon(new NextPaymentsPanel.ColorIcon(color, 20, 20));
                }
                return label;
            }
        });
        colorComboBox.setBounds(45, 320, 300, 30);
        addFo.add(colorComboBox);

        RoundedButton closeButton = new RoundedButton("Close", 30);
        closeButton.setBounds(120, 420, 150, 40);
        closeButton.setBackground(new Color(86, 60, 16));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Lexend", Font.PLAIN, 16));
        closeButton.addActionListener(e -> modal.dispose());
        addFo.add(closeButton);

        RoundedButton send = new RoundedButton("Create", 30);
        send.setBounds(120, 370, 150, 40);
        send.setBackground(new Color(86, 60, 16));
        send.setForeground(Color.WHITE);
        send.setFont(new Font("Lexend", Font.PLAIN, 16));
        ModernToggleButton weekOrMonth = new ModernToggleButton();

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
                addFo.add(weekOrMonth);
                colorLabel.setBounds(45, 350, 100, 20);
                colorComboBox.setBounds(45, 380, 300, 30);
                send.setBounds(120, 420, 150, 40);
                closeButton.setBounds(120, 470, 150, 40);
            } else {
                addFo.remove(weekOrMonth);
                colorLabel.setBounds(45, 290, 100, 20);
                colorComboBox.setBounds(45, 320, 300, 30);
                send.setBounds(120, 370, 150, 40);
                closeButton.setBounds(120, 420, 150, 40);
            }
            addFo.revalidate();
            addFo.repaint();
        });

        send.addActionListener(e -> {
            String name = nameFo.getText().trim();
            String cost = costFo.getText().trim();
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
            boolean isRepetitiveFo = false;
            boolean weekOrMonthFo = false;

            if (isRepetitive.isSelected()) {
                isRepetitiveFo = true;
                if (weekOrMonth.isSelected()) {
                    weekOrMonthFo = true;
                } else {
                    weekOrMonthFo = false;
                }
            }

            if (name.isEmpty() || cost.isEmpty() || selectedDate == null) {
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
                if (weekOrMonthFo) {
                    viewController.setInfoFo(name, cost, date, selectedColor, isRepetitiveFo, true, false);
                } else {
                    viewController.setInfoFo(name, cost, date, selectedColor, isRepetitiveFo, false, true);

                }
            }

            nameFo.setText("");
            costFo.setText("");
            datePicker.clear();
            colorComboBox.setSelectedIndex(0);
            JOptionPane.showMessageDialog(modal, "Added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            modal.dispose();
            viewController.assignFoToDays();
            viewController.paintDaysInView();
        });

        addFo.add(send);
        modal.add(addFo);
        modal.setVisible(true);
    }
}
