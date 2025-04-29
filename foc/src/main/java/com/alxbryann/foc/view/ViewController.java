package com.alxbryann.foc.view;

import com.alxbryann.foc.model.Controller;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author barr2
 */
public class ViewController {

    private Controller controller;
    private View vt;

    public ViewController() {
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setView(View vt) {
        this.vt = vt;
    }

    public void setInfoFo(String name, String price, String date, Color selectedColor, boolean isRepetitive, boolean isRepetitiveByWeek, boolean isRepetitiveByMonth) {
        controller.setInfoFinancialObligation(name, price, date, selectedColor, isRepetitive, isRepetitiveByWeek, isRepetitiveByMonth);
    }

    public void setInfoIncome(String name, String value, String date) {
        controller.setInfoIncome(name, value, date);
    }

    public List getInfoFo() {
        return controller.findAllFinancialObligations();
    }

    public List getInfoIncome() {
        return controller.findAllIncome();
    }

    public int getDaysInCurrentMonth() {
        return controller.getNumberOfDaysInCurrentMonth();
    }

    public void assignFoToDays() {
        controller.assignFinancialObligationToDays();
    }

    public ArrayList paintDays() {
        return controller.paintDays();
    }
    
    public ArrayList paintRepetitiveDays() {
        return controller.paintRepetitiveDays();
    }

    public void paintDaysInView() {
        vt.paintDays();
    }
    
    public List getFOsByDay(int day){
        return controller.getFinancialObligationsByDay(day);
    }

}
