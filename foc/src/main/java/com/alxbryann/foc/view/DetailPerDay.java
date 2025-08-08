package com.alxbryann.foc.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author alxbryann
 */
public class DetailPerDay extends JFrame {

    private ViewController viewController;
    private String currentMonth;
    private int clickedDay;
    private int currentYear;

    public DetailPerDay(ViewController viewController) {
        this.viewController = viewController;
        getCurrentMonthInString();
        getCurrentDay();
        getCurrentYear();
        initializeUI();
    }

    private void initializeUI() {
        setLayout(null);
        setBounds(45, 50, 1280, 720);
        String date = currentMonth + " " + clickedDay + " " + currentYear;
        JLabel titleDate = new JLabel(date);
        titleDate.setFont(new Font("Kantumruy Pro", Font.BOLD, 55));
        titleDate.setBounds(60, 40, 450, 100);
        titleDate.setHorizontalAlignment(SwingConstants.CENTER);
        titleDate.setVerticalAlignment(SwingConstants.CENTER);
        titleDate.setForeground(Color.BLACK);
        add(titleDate);
    }

    private void getCurrentMonthInString() {
        currentMonth = viewController.getCurrentMonthInString();
    }

    private void getCurrentDay() {
        clickedDay = viewController.getClickedDay();
    }

    private void getCurrentYear() {
        currentYear = viewController.getCurrentYear();
    }
}
