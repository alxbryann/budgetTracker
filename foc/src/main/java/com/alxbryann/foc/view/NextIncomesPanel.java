package com.alxbryann.foc.view;

import com.alxbryann.foc.model.Income;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class NextIncomesPanel extends JPanel {

    private ViewController viewController;
    private JPanel incomesContainer;

    public NextIncomesPanel(ViewController viewController) {
        this.viewController = viewController;
        initializeUI();
        updateIncomesContainer();
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
        RoundedJDialog modal = new RoundedJDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Create New Incomes", 400, 450, 30);
        modal.setTitle("Create New Incomes");
        modal.setSize(400, 450);
        modal.setLayout(null);
        modal.setLocationRelativeTo(null);
        modal.setResizable(false);
        modal.setUndecorated(true);
        modal.getContentPane().setBackground(new Color(240, 240, 240));

        JPanel addIncome = new JPanel();
        addIncome.setLayout(null);
        addIncome.setBounds(0, 0, 400, 450);
        addIncome.setBackground(Color.WHITE);
        addIncome.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));

        JLabel title = new JLabel("Create New Incomes");
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
        nameIncome.setBounds(45, 75, 300, 30);
        nameIncome.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        nameIncome.setFont(new Font("Lexend", Font.PLAIN, 14));
        nameIncome.setBackground(Color.WHITE);
        nameIncome.setForeground(Color.BLACK);
        addIncome.add(nameIncome);

        JLabel valueLabel = new JLabel("Value:");
        valueLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
        valueLabel.setBounds(45, 115, 100, 20);
        valueLabel.setForeground(new Color(60, 60, 60));
        addIncome.add(valueLabel);

        JTextArea valueIncome = new JTextArea();
        valueIncome.setBounds(45, 140, 300, 30);
        valueIncome.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        valueIncome.setFont(new Font("Lexend", Font.PLAIN, 14));
        valueIncome.setBackground(Color.WHITE);
        valueIncome.setForeground(Color.BLACK);
        addIncome.add(valueIncome);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
        dateLabel.setBounds(45, 180, 100, 20);
        dateLabel.setForeground(new Color(60, 60, 60));
        addIncome.add(dateLabel);

        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFormatForDatesCommonEra("yyyy-MM-dd");

        Font datePickerFont = new Font("Lexend", Font.PLAIN, 14);
        dateSettings.setFontValidDate(datePickerFont);
        dateSettings.setFontVetoedDate(datePickerFont);
        dateSettings.setFontMonthAndYearMenuLabels(datePickerFont);

        DatePicker datePicker = new DatePicker(dateSettings);
        datePicker.setBounds(45, 205, 300, 30);
        addIncome.add(datePicker);

        RoundedButton send = new RoundedButton("Create", 30);
        send.setBounds(120, 320, 150, 40);
        send.setBackground(new Color(86, 60, 16));
        send.setForeground(Color.WHITE);
        send.setFont(new Font("Lexend", Font.PLAIN, 16));
        send.addActionListener(e -> {
            String name = nameIncome.getText().trim();
            String value = valueIncome.getText().trim();
            LocalDate selectedDate = datePicker.getDate();

            if (name.isEmpty() || value.isEmpty() || selectedDate == null) {
                JOptionPane.showMessageDialog(modal, "Please complete all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String date = selectedDate.toString();

            if (viewController != null) {
                viewController.setInfoIncome(name, value, date);
            }

            nameIncome.setText("");
            valueIncome.setText("");
            datePicker.clear();
            JOptionPane.showMessageDialog(modal, "Added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            modal.dispose();
            viewController.assignFoToDays();
        });

        RoundedButton closeButton = new RoundedButton("Close", 30);
        closeButton.setBounds(120, 370, 150, 40);
        closeButton.setBackground(new Color(86, 60, 16));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Lexend", Font.PLAIN, 16));
        closeButton.addActionListener(e -> modal.dispose());

        addIncome.add(send);
        addIncome.add(closeButton);
        modal.add(addIncome);
        modal.setVisible(true);
        updateIncomesContainer();
    }

    private void updateIncomesContainer() {
        List<Income> incomeList = viewController.getInfoIncome();
        incomesContainer.removeAll();
        if (!incomeList.isEmpty()) {
            int y = 30;
            for (Income temp : incomeList) {
                LocalDate today = LocalDate.now();
                LocalDate foDate = temp.getDate().toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate();
                if (foDate.getMonthValue() == today.getMonthValue()) {
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
                    nameContainer.setBackground(new Color(133, 187, 101));
                    incomesContainer.add(nameContainer);
                    JLabel name = new JLabel(temp.getName());
                    name.setFont(new Font("Lexend", Font.PLAIN, 22));
                    name.setForeground(Color.BLACK);
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
                    incomesContainer.add(costContainer);
                    JLabel cost = new JLabel("        " + "$" + String.valueOf(temp.getValue()));
                    costContainer.setOpaque(true);
                    costContainer.setBackground(Color.WHITE);
                    cost.setFont(new Font("Lexend", Font.PLAIN, 22));
                    cost.setForeground(Color.BLACK);
                    cost.setBounds(100, 0, 100, 100);
                    costContainer.add(cost);
                    y += 50;
                }
            }
            incomesContainer.revalidate();
            incomesContainer.repaint();
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
