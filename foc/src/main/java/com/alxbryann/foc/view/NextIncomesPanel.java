package com.alxbryann.foc.view;

import com.alxbryann.foc.model.Income;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public final class NextIncomesPanel extends JPanel {

    private final ViewController viewController;
    private JPanel incomesContainer;

    public NextIncomesPanel(ViewController viewController) {
        this.viewController = viewController;
        initializeUI();
        updateIncomesContainer();
        System.out.println("updateIncomesContainer");
    }

    private void initializeUI() {
        setLayout(null);
        setBounds(45, 50, 270, 260);
        setBackground(new Color(204, 168, 109, 255));

        JLabel titleNextIncomes = new JLabel("Next Incomes");
        titleNextIncomes.setFont(new Font("Lexend", Font.PLAIN, 22));
        titleNextIncomes.setBounds(55, 10, 180, 30);
        titleNextIncomes.setForeground(Color.BLACK);

        incomesContainer = new JPanel();
        incomesContainer.setLayout(null);
        incomesContainer.setBounds(20, 30, 300, 130);
        incomesContainer.setOpaque(false);

        add(incomesContainer);
        add(titleNextIncomes);

        RoundedButton show = new RoundedButton("Show more", 30);
        show.setBounds(50, 175, 180, 30);
        show.setBackground(new Color(86, 60, 16));
        show.setForeground(Color.WHITE);
        show.setFont(new Font("Lexend", Font.PLAIN, 15));
        add(show);

        RoundedButton addNewIncomes = new RoundedButton("Add new incomes", 30);
        addNewIncomes.setBounds(50, 210, 180, 30);
        addNewIncomes.setBackground(new Color(86, 60, 16));
        addNewIncomes.setForeground(Color.WHITE);
        addNewIncomes.setFont(new Font("Lexend", Font.PLAIN, 15));
        addNewIncomes.addActionListener(e -> createNewIncomeDialog());
        add(addNewIncomes);
    }

    private void createNewIncomeDialog() {
        RoundedJDialog modal = new RoundedJDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Create New Income", 400, 520, 30);
        modal.setTitle("Create New Income");
        modal.setSize(400, 520);
        modal.setLayout(null);
        modal.setLocationRelativeTo(null);
        modal.setResizable(false);
        modal.setUndecorated(true);
        modal.getContentPane().setBackground(new Color(240, 240, 240));

        JPanel addIncome = new JPanel();
        addIncome.setLayout(null);
        addIncome.setBounds(0, 0, 400, 520);
        addIncome.setBackground(Color.WHITE);
        addIncome.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));

        JLabel title = new JLabel("Create New Income");
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
        addIncome.add(nameIncome);

        JLabel valueLabel = new JLabel("Value:");
        valueLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
        valueLabel.setBounds(45, 115, 100, 20);
        valueLabel.setForeground(new Color(60, 60, 60));
        addIncome.add(valueLabel);

        JTextArea valueIncome = new JTextArea();
        valueIncome.setBounds(45, 140, 300, 40);
        valueIncome.setFont(new Font("Lexend", Font.PLAIN, 14));
        valueIncome.setBackground(new Color(217, 217, 217));
        valueIncome.setForeground(Color.BLACK);
        valueIncome.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addIncome.add(valueIncome);

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
        addIncome.add(datePicker);

        JLabel textIsRepetitive = new JLabel("Is repetitive?");
        textIsRepetitive.setBounds(45, 260, 100, 20);
        textIsRepetitive.setFont(new Font("Lexend", Font.PLAIN, 14));
        ModernCheckBox isRepetitive = new ModernCheckBox();
        isRepetitive.setBounds(150, 260, 30, 20);
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
            "Rojo",
            "Verde",
            "Gris",
            "Azul",
            "Morado",
            "Cafe"
        };

        JComboBox<String> colorComboBox = new JComboBox<>(colorNames);
        colorComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (index >= 0 && index < pastelColors.length) {
                    Color color = pastelColors[index];
                    label.setIcon(new ColorIcon(color, 20, 20));
                }
                return label;
            }
        });
        colorComboBox.setBounds(45, 320, 300, 30);
        addIncome.add(colorComboBox);

        RoundedButton closeButton = new RoundedButton("Close", 30);
        closeButton.setBounds(120, 420, 150, 40);
        closeButton.setBackground(new Color(86, 60, 16));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Lexend", Font.PLAIN, 16));
        closeButton.addActionListener(e -> modal.dispose());
        addIncome.add(closeButton);

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
            String value = valueIncome.getText().trim();
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

            if (name.isEmpty() || value.isEmpty() || selectedDate == null) {
                JOptionPane.showMessageDialog(modal, "Please complete all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Color selectedColor = null;
            switch (selectedColorName) {
                case "Rojo" ->
                    selectedColor = pastelColors[0];
                case "Verde" ->
                    selectedColor = pastelColors[1];
                case "Gris" ->
                    selectedColor = pastelColors[2];
                case "Azul" ->
                    selectedColor = pastelColors[3];
                case "Morado" ->
                    selectedColor = pastelColors[4];
                case "Cafe" ->
                    selectedColor = pastelColors[5];
            }

            String date = selectedDate.toString();

            if (viewController != null) {
                if (weekOrMonthIncome) {
                    viewController.setInfoIncome(name, value, date, selectedColor, isRepetitiveIncome, true, false);
                } else {
                    viewController.setInfoIncome(name, value, date, selectedColor, isRepetitiveIncome, false, true);
                }
            }

            nameIncome.setText("");
            valueIncome.setText("");
            datePicker.clear();
            colorComboBox.setSelectedIndex(0);
            JOptionPane.showMessageDialog(modal, "Added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            modal.dispose();
            viewController.assignIncomesToDays();
            viewController.paintDaysInView();
        });

        addIncome.add(send);
        modal.add(addIncome);
        modal.setVisible(true);
        updateIncomesContainer();
    }

    public void updateIncomesContainer() {
        List<Income> incomeList = viewController.getInfoIncome();
        System.out.println("incomeList: " + incomeList);
        incomesContainer.removeAll();
        if (!incomeList.isEmpty()) {
            Income temp;
            int y = 30;
            for (int i = 0; i < incomeList.size(); i++) {
                temp = incomeList.get(i);
                LocalDate today = LocalDate.now();
                LocalDate incomeDate = temp.getDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                if (true) {
                    JPanel nameContainer = new JPanel() {
                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            Graphics2D g2 = (Graphics2D) g.create();
                            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            g2.setColor(getBackground());
                            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);
                            g2.dispose();
                        }

                        @Override
                        protected void paintBorder(Graphics g) {
                        }

                        @Override
                        public boolean isOpaque() {
                            return false;
                        }
                    };
                    nameContainer.setBounds(0, y, 120, 40);
                    nameContainer.setLayout(new GridBagLayout());
                    String rgb = temp.getRgb();
                    int red = Integer.parseInt(rgb.substring(0, rgb.indexOf(",")));
                    rgb = rgb.substring(rgb.indexOf(",") + 2);
                    int green = Integer.parseInt(rgb.substring(0, rgb.indexOf(",")));
                    rgb = rgb.substring(rgb.indexOf(",") + 2);
                    int blue = Integer.parseInt(rgb);

                    nameContainer.setBackground(new Color(red, green, blue));
                    incomesContainer.add(nameContainer);
                    JLabel name = new JLabel(temp.getName());
                    if (temp.getName().length() <= 7) {
                        name.setFont(new Font("Lexend", Font.PLAIN, 22));
                    } else {
                        if (temp.getName().length() > 7) {
                            name.setFont(new Font("Lexend", Font.PLAIN, 16));
                        } else {
                            if (temp.getName().length() > 10) {
                                name.setFont(new Font("Lexend", Font.PLAIN, 14));
                            }
                        }
                    }
                    nameContainer.add(name);
                    JPanel valueContainer = new JPanel() {
                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            Graphics2D g2 = (Graphics2D) g.create();
                            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            g2.setColor(getBackground());
                            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);
                            g2.dispose();
                        }

                        @Override
                        protected void paintBorder(Graphics g) {
                        }

                        @Override
                        public boolean isOpaque() {
                            return false;
                        }
                    };
                    valueContainer.setBounds(70, y, 165, 40);
                    incomesContainer.add(valueContainer);
                    JLabel value = new JLabel("        " + "$" + String.valueOf(temp.getValue()));
                    valueContainer.setOpaque(true);
                    valueContainer.setBackground(Color.WHITE);
                    if (String.valueOf(temp.getValue()).length() <= 7) {
                        value.setFont(new Font("Lexend", Font.PLAIN, 22));
                    } else {
                        if (String.valueOf(temp.getValue()).length() > 7) {
                            value.setFont(new Font("Lexend", Font.PLAIN, 16));
                        } else {
                            if (String.valueOf(temp.getValue()).length() > 10) {
                                value.setFont(new Font("Lexend", Font.PLAIN, 14));
                            }
                        }
                    }
                    value.setForeground(Color.BLACK);
                    value.setBounds(100, 0, 100, 100);
                    valueContainer.add(value);
                    y += 50;
                }
            }
            incomesContainer.revalidate();
            incomesContainer.repaint();
        }
    }

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
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
