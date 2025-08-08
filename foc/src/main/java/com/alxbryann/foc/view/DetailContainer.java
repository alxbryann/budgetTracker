package com.alxbryann.foc.view;

import javax.swing.*;
import java.awt.*;

public final class DetailContainer extends JPanel {

    private final ViewController viewController;
    private JPanel incomesContainer;

    public DetailContainer(ViewController viewController) {
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
    }

    public void updateIncomesContainer() {
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