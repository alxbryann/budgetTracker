package com.alxbryann.foc.view;

import com.alxbryann.foc.model.Transaction;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.alxbryann.foc.view.glasspanepopup.GlassPanePopup;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public final class NextTransactionsPanel extends JPanel {

    private final ViewController viewController;
    private JPanel transactionsContainer;

    public NextTransactionsPanel(ViewController viewController) {
        this.viewController = viewController;
        viewController.setNextTransactionsPanel(this);
        initializeUI();
        updateTransactionsContainer();
    }

    private void initializeUI() {
        setLayout(null);
        setBounds(30, 50, 300, 460);
        setBackground(new Color(0xE8E0BE));

        JLabel titleNextTransactions = new JLabel("Next Transactions");
        titleNextTransactions.setFont(new Font("Kantumruy Pro", Font.BOLD, 22));
        titleNextTransactions.setBounds(60, 10, 250, 30);
        titleNextTransactions.setForeground(Color.BLACK);

        transactionsContainer = new JPanel();
        transactionsContainer.setLayout(new BoxLayout(transactionsContainer, BoxLayout.Y_AXIS));
        transactionsContainer.setBounds(10, 45, 280, 400);
        transactionsContainer.setOpaque(false);

        add(transactionsContainer);
        add(titleNextTransactions);

    }

    public void openCreateTransactionDialog(boolean isExpense) {
        // Use GlassPanePopup instead of a separate modal dialog
        JPanel addIncome = new JPanel();
        addIncome.setLayout(null);
        addIncome.setPreferredSize(new Dimension(400, 520));
        addIncome.setBackground(Color.WHITE);
        addIncome.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, false));

        JLabel title = new JLabel(isExpense ? "Create New Expense" : "Create New Income");
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
        isRepetitive.setFont(new Font("Lexend", Font.PLAIN, 14));
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
        colorComboBox.setBounds(45, 320, 300, 30);
        addIncome.add(colorComboBox);

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(120, 420, 150, 40);
        closeButton.setFont(new Font("Lexend", Font.PLAIN, 16));
        // Aplicar estilo FlatLaf - rojo como Expense
        FlatLafStyleManager.applyDangerButtonStyle(closeButton);
        closeButton.addActionListener(e -> GlassPanePopup.closePopupLast());
        addIncome.add(closeButton);

        JButton send = new JButton("Create");
        send.setBounds(120, 370, 150, 40);
        send.setFont(new Font("Lexend", Font.PLAIN, 16));
        // Aplicar estilo FlatLaf - verde como Income
        FlatLafStyleManager.applySuccessButtonStyle(send);
        ModernToggleButton weekOrMonth = new ModernToggleButton();
        weekOrMonth.setFont(new Font("Lexend", Font.PLAIN, 14));

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
                JOptionPane.showMessageDialog(NextTransactionsPanel.this, "Please complete all the fields.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // If expense, ensure value is negative
            try {
                double v = Double.parseDouble(value.replace(",", "."));
                if (isExpense && v > 0) {
                    v = -v;
                }
                value = Double.toString(v);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(NextTransactionsPanel.this, "Invalid value.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Adjust invalid 31st day after ensuring date is not null
            if (selectedDate.getDayOfMonth() == 31) {
                UIManager.put("OptionPane.background", new Color(245, 245, 235));
                UIManager.put("Panel.background", new Color(245, 245, 235));
                UIManager.put("OptionPane.messageForeground", new Color(0, 111, 74));
                UIManager.put("Button.background", new Color(0, 111, 74));
                UIManager.put("Button.foreground", Color.white);
                UIManager.put("Button.focus", new Color(0, 90, 60));

                JOptionPane.showMessageDialog(
                        NextTransactionsPanel.this,
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
            JOptionPane.showMessageDialog(NextTransactionsPanel.this, "Added successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            GlassPanePopup.closePopupLast();
            viewController.assignTransactionToDays();
            viewController.updateNextTransactions();
            viewController.updateDetailContainer();
            viewController.updateCalendarView();
        });

        addIncome.add(send);
        // Install and show the popup on the parent frame
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (parentFrame != null) {
            GlassPanePopup.install(parentFrame);
            GlassPanePopup.showPopup(addIncome);
        }
        // No refresh here to avoid layout jitter; we'll refresh after successful
        // creation
    }

    public void updateTransactionsContainer() {
        List<Transaction> incomeList = viewController.getInfoIncome();
        transactionsContainer.removeAll();

        if (!incomeList.isEmpty()) {
            for (int i = 0; i < incomeList.size(); i++) {
                Transaction transaction = incomeList.get(i);
                // Get the day number from the transaction date
                LocalDate incomeDate = transaction.getDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                int dayNumber = incomeDate.getDayOfMonth();

                // Create ElementDetail for each transaction
                ElementDetail elementDetail = new ElementDetail(
                        transaction.getId(),
                        transaction.getName(),
                        transaction.getValue(),
                        viewController,
                        dayNumber);

                // Set size to fill the container width and have consistent height
                elementDetail.setPreferredSize(new Dimension(270, 50));
                elementDetail.setMaximumSize(new Dimension(270, 50));
                elementDetail.setMinimumSize(new Dimension(270, 50));

                transactionsContainer.add(elementDetail);

                // Add spacing between elements except after the last one
                if (i < incomeList.size() - 1) {
                    transactionsContainer.add(Box.createRigidArea(new Dimension(0, 8)));
                }
            }
        }
        transactionsContainer.revalidate();
        transactionsContainer.repaint();
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
