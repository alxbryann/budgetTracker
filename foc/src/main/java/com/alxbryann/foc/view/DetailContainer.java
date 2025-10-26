package com.alxbryann.foc.view;

import com.alxbryann.foc.model.Transaction;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public final class DetailContainer extends JPanel {

    private final ViewController viewController;
    private int dayNumber;

    public DetailContainer(ViewController viewController, int dayNumber) {
        this.viewController = viewController;
        this.dayNumber = dayNumber;
        initializeUI();
        prepareElementsDetail();
    }

    private void prepareElementsDetail() {
        cleanElementsDetail();
        ArrayList incomes = getIncomesByDay();
        int spaceY = 20;
        int elementWidth = 600;
        int elementHeight = 80;
        int containerWidth = 640; // ancho del DetailContainer
        int x = (containerWidth - elementWidth) / 2;
        for (int i = 0; i < incomes.size(); i++) {
            Transaction temporalIncome = (Transaction) incomes.get(i);
            int id = temporalIncome.getId();
            String name = temporalIncome.getName();
            double cost = temporalIncome.getValue();
            ElementDetail ed = new ElementDetail(id, name, cost, viewController, dayNumber);
            ed.setBounds(x, spaceY, elementWidth, elementHeight);
            spaceY += 100;
            ed.setVisible(true);
            add(ed);
        }
    }

    private void cleanElementsDetail() {
        Component[] components = getComponents();
        for (Component comp : components) {
            if (comp instanceof ElementDetail) {
                remove(comp);
            }
        }
        revalidate();
        repaint();
    }

    private ArrayList getIncomesByDay() {
        return (ArrayList) viewController.getIncomesByDay(dayNumber);
    }

    public void refreshElementsDetail() {
        prepareElementsDetail();
    }

    private void initializeUI() {
        setLayout(null);
        setBounds(60, 70, 640, 450);
        setBackground(new Color(232, 248, 245));

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
