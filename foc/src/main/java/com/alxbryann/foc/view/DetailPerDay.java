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
        setBounds(0, 0, 1280, 720);
        getContentPane().setBackground(Color.WHITE);
        String date = currentMonth + " " + (clickedDay + 1) + " " + currentYear;
        JLabel titleDate = new JLabel(date);
        titleDate.setFont(new Font("Kantumruy Pro", Font.BOLD, 75));
        titleDate.setBounds(60, 60, 600, 100);
        titleDate.setForeground(Color.BLACK);
        add(titleDate);
        DetailContainer detailContainer = new DetailContainer(viewController);
        detailContainer.setBounds(60, 190, 700, 450);
        add(detailContainer);
        Summary summary = new Summary(viewController);
        summary.setBounds(800, 60, 360, 580);
        add(summary);
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
