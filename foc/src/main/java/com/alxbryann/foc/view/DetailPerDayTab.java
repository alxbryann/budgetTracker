package com.alxbryann.foc.view;

import com.alxbryann.foc.view.tabbed.TabbedForm;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author alxbryann
 */
public class DetailPerDayTab extends TabbedForm {

    private ViewController viewController;
    private String currentMonth;
    private int clickedDay;
    private int currentYear;

    public DetailPerDayTab(ViewController viewController) {
        this.viewController = viewController;
        getCurrentMonthInString();
        getCurrentDay();
        getCurrentYear();
        initializeUI();
    }

    private void initializeUI() {
        setLayout(null);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1280, 720));
        setBounds(0, 0, 1280, 720);
        
        String date = currentMonth + " " + (clickedDay + 1) + " " + currentYear;
        JLabel titleDate = new JLabel(date);
        titleDate.setFont(new Font("Kantumruy Pro", Font.BOLD, 60));
        titleDate.setBounds(60, 60, 600, 100);
        titleDate.setForeground(Color.BLACK);
        add(titleDate);
        
        DetailContainer detailContainer = new DetailContainer(viewController, (clickedDay));
        detailContainer.setBounds(60, 190, 700, 450);
        viewController.setCurrentDetailContainer(detailContainer);
        add(detailContainer);
        
        Summary summary = new Summary(viewController, (clickedDay));
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
    
    @Override
    public void formOpen() {
        super.formOpen();
        revalidate();
        repaint();
    }
    
    @Override
    public boolean formClose() {
        return true;
    }
    
    public String getTabTitle() {
        return currentMonth + " " + (clickedDay + 1);
    }
}