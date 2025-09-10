package com.alxbryann.foc.view;

import com.alxbryann.foc.model.FinancialObligation;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;

public final class NextPaymentsPanel extends JPanel {

    private final ViewController viewController;
    private JPanel foContainer;

    public NextPaymentsPanel(ViewController viewController) {
        this.viewController = viewController;
        initializeUI();
        updateFoContainer();
    }

    private void initializeUI() {
        setLayout(null);
        setBounds(45, 350, 270, 260);
        setBackground(new Color(204, 168, 109, 255));

        JLabel titleNextPayment = new JLabel("Next Payments");
        titleNextPayment.setFont(new Font("Lexend", Font.PLAIN, 22));
        titleNextPayment.setBounds(55, 10, 180, 30);
        titleNextPayment.setForeground(Color.BLACK);

        foContainer = new JPanel();
        foContainer.setLayout(null);
        foContainer.setBounds(20, 30, 300, 130);
        foContainer.setOpaque(false);

        add(foContainer);
        add(titleNextPayment);

        RoundedButton show = new RoundedButton("Show more", 30);
        show.setBounds(50, 175, 180, 30);
        show.setBackground(new Color(86, 60, 16));
        show.setForeground(Color.WHITE);
        show.setFont(new Font("Lexend", Font.PLAIN, 15));
        add(show);

        RoundedButton addNewPayments = new RoundedButton("Add new payments", 30);
        addNewPayments.setBounds(50, 210, 180, 30);
        addNewPayments.setBackground(new Color(86, 60, 16));
        addNewPayments.setForeground(Color.WHITE);
        addNewPayments.setFont(new Font("Lexend", Font.PLAIN, 15));
        addNewPayments.addActionListener(e -> createNewPaymentDialog());
        add(addNewPayments);
    }

    private void createNewPaymentDialog() {
        RoundedJDialog modal = new RoundedJDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Create New Financial Obligation", 400, 520, 30);
        modal.setTitle("Create New Financial Obligation");
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

        JLabel title = new JLabel("Create New Financial Obligation");
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
        updateFoContainer();
    }

    public void updateFoContainer() {
        java.util.List foList = viewController.getInfoFo();
        foContainer.removeAll();
        if (!foList.isEmpty()) {
            FinancialObligation temp;
            int y = 30;
            for (int i = 0; i < foList.size(); i++) {
                temp = (FinancialObligation) foList.get(i);
                LocalDate today = LocalDate.now();
                LocalDate foDate = temp.getDate().toInstant()
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
                    foContainer.add(nameContainer);
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
                    JPanel costContainer = new JPanel() {
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
                    costContainer.setBounds(70, y, 165, 40);
                    foContainer.add(costContainer);
                    JLabel cost = new JLabel("        " + "$" + String.valueOf(temp.getCost()));
                    costContainer.setOpaque(true);
                    costContainer.setBackground(Color.WHITE);
                    if (String.valueOf(temp.getCost()).length() <= 7) {
                        cost.setFont(new Font("Lexend", Font.PLAIN, 22));
                    } else {
                        if (String.valueOf(temp.getCost()).length() > 7) {
                            cost.setFont(new Font("Lexend", Font.PLAIN, 16));
                        } else {
                            if (String.valueOf(temp.getCost()).length() > 10) {
                                cost.setFont(new Font("Lexend", Font.PLAIN, 14));
                            }
                        }
                    }
                    cost.setForeground(Color.BLACK);
                    cost.setBounds(100, 0, 100, 100);
                    costContainer.add(cost);
                    y += 50;
                }

            }
            foContainer.revalidate();
            foContainer.repaint();
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
