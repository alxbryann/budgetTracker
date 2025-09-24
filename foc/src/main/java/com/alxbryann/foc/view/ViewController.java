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

    public void setInfoIncome(String name, String value, String date, Color selectedColor, boolean isRepetitive, boolean isRepetitiveByWeek, boolean isRepetitiveByMonth) {
        controller.setInfoIncome(name, value, date, selectedColor, isRepetitive, isRepetitiveByWeek, isRepetitiveByMonth);
    }

    public List getInfoFo() {
        return controller.findAllFinancialObligations();
    }

    public List getInfoIncome() {
        return controller.findAllIncomes();
    }

    public int getDaysInCurrentMonth() {
        return controller.getNumberOfDaysInCurrentMonth();
    }

    public void assignFoToDays() {
        controller.assignFinancialObligationToDays();
    }

    public void assignIncomesToDays() {
        controller.assignIncomesToDays();
    }

    public ArrayList paintFOs() {
        return controller.paintFOs();
    }

    public ArrayList paintINs() {
        return controller.paintINs();
    }

    public ArrayList paintRepetitiveFinancialObligations() {
        return controller.paintRepetitiveFinancialObligations();
    }

    public ArrayList paintRepetitiveIncomes() {
        return controller.paintRepetitiveIncomes();
    }

    public void paintDaysInView() {
        vt.paintFOsInView();
        vt.paintINsInView();
    }

    public List getFinancialObligationsByDay(int day) {
        return controller.getFinancialObligationsByDay(day);
    }

    public List getIncomesByDay(int day) {
        return controller.getIncomesByDay(day);
    }

    public String getCurrentMonthInString() {
        return controller.getCurrentMonthInString();
    }

    public int getCurrentYear() {
        return controller.getCurrentYear();
    }

    public int getClickedDay() {
        return vt.getDayClicked();
    }

    public double getTotalCostByDay(int day) {
        return controller.getTotalCostByDay(day);
    }

    public double getTotalIncomeByDay(int day) {
        return controller.getTotalIncomeByDay(day);
    }

    public double getTotalNetByDay(int day) {
        return controller.getTotalNet(day);
    }

    public void deleteFinancialObligationById(int id) {
        controller.deleteFinancialObligationById(id);
    }
    
    public void deleteIncomeById(int id) {
        controller.deleteIncomeById(id);
    }
    
    public void clearViewCalendar() {
        vt.clearViewCalendar();
    }
}
