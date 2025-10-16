package com.alxbryann.foc.view;

import com.alxbryann.foc.model.Controller;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author barr2
 */
public class ViewController {

    private Controller controller;
    private View view;
    private NextIncomesPanel nextIncomesPanel;
    private DetailContainer currentDetailContainer;

    public ViewController() {
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setNextIncomesPanel(NextIncomesPanel nextIncomesPanel) {
        this.nextIncomesPanel = nextIncomesPanel;
    }

    public void setCurrentDetailContainer(DetailContainer detailContainer) {
        this.currentDetailContainer = detailContainer;
    }

    public void updateDetailContainer() {
        if (currentDetailContainer != null) {
            currentDetailContainer.refreshElementsDetail();
        }
    }

    public void setInfoIncome(String name, String value, String date, Color selectedColor, boolean isRepetitive,
            boolean isRepetitiveByWeek, boolean isRepetitiveByMonth) {
        controller.setInfoTransaction(name, value, date, selectedColor, isRepetitive, isRepetitiveByWeek,
                isRepetitiveByMonth);
    }

    public void createIncome(String name, String value, String date, Color selectedColor, boolean isRepetitive,
            boolean isRepetitiveByWeek, boolean isRepetitiveByMonth) {
        controller.createTransaction(name, value, date, selectedColor, isRepetitive, isRepetitiveByWeek,
                isRepetitiveByMonth);
    }

    public List getInfoTransaction() {
        return controller.findAllTransactions();
    }

    public int getDaysInCurrentMonth() {
        return controller.getNumberOfDaysInCurrentMonth();
    }

    /*
     * public void assignFoToDays() {
     * controller.assignFinancialObligationToDays();
     * }
     * 
     * public void assignIncomesToDays() {
     * controller.assignIncomesToDays();
     * }
     * 
     * public ArrayList paintFOs() {
     * return controller.paintFOs();
     * }
     * 
     * public ArrayList paintINs() {
     * return controller.paintINs();
     * }
     * 
     * public ArrayList paintRepetitiveFinancialObligations() {
     * return controller.paintRepetitiveFinancialObligations();
     * }
     * 
     * public ArrayList paintRepetitiveIncomes() {
     * return controller.paintRepetitiveIncomes();
     * }
     * 
     * public void paintDaysInView() {
     * view.paintFOsInView();
     * view.paintINsInView();
     * }
     * 
     * public List getFinancialObligationsByDay(int day) {
     * return controller.getFinancialObligationsByDay(day);
     * }
     * 
     * public List getIncomesByDay(int day) {
     * return controller.getIncomesByDay(day);
     * }
     */

    public String getCurrentMonthInString() {
        return controller.getCurrentMonthInString();
    }

    public int getCurrentYear() {
        return controller.getCurrentYear();
    }

    public int getClickedDay() {
        return view.getDayClicked();
    }

    /*
     * public double getTotalCostByDay(int day) {
     * return controller.getTotalCostByDay(day);
     * }
     * 
     * public double getTotalIncomeByDay(int day) {
     * return controller.getTotalIncomeByDay(day);
     * }
     * 
     * public double getTotalNetByDay(int day) {
     * return controller.getTotalNet(day);
     * }
     */

    public void deleteTransactionById(int id) {
        controller.deleteTransactionById(id);
    }

    public void clearViewCalendar() {
        view.clearViewCalendar();
    }

    public void updateViewCalendar() {
        view.updateViewCalendar();
    }

    public void updateNextIncomes() {
        nextIncomesPanel.updateIncomesContainer();
    }

    /*
     * public void removeFinancialObligationFromDayById(int id, int numberDay) {
     * controller.removeFinancialObligationFromDayById(id, numberDay);
     * }
     * 
     * public void removeIncomeFromDayById(int id, int numberDay) {
     * controller.removeIncomeFromDayById(id, numberDay);
     * }
     * 
     * public HashMap getInformationOfFinancialObligation(int id) {
     * return controller.getInformationOfFinancialObligation(id);
     * }
     * 
     * public HashMap getInformationOfIncome(int id) {
     * return controller.getInformationOfIncome(id);
     * }
     */

    public void editIncome(int id, String name, String amount, String date, Color selectedColor,
            boolean isRepetitive, boolean isRepetitiveByWeek, boolean isRepetitiveByMonth) {
        // Obtener el ingreso existente
        com.alxbryann.foc.model.Transaction existingIncome = controller.findTransactionById(id);
        if (existingIncome != null) {
            try {
                existingIncome.setName(name);
                double amountDouble = Double.parseDouble(amount);
                existingIncome.setValue(amountDouble);
                java.time.LocalDate localDate = java.time.LocalDate.parse(date);
                java.time.ZoneId defaultZoneId = java.time.ZoneId.systemDefault();
                java.util.Date dateObj = java.util.Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
                existingIncome.setDate(dateObj);
                existingIncome.setColor(selectedColor);
                existingIncome.setIsRepetitive(isRepetitive);
                existingIncome.setRepetitiveByWeek(isRepetitiveByWeek);
                existingIncome.setRepetitiveByMonth(isRepetitiveByMonth);
                controller.editTransaction(existingIncome);
            } catch (Exception e) {
                System.err.println("Error editing income: " + e.getMessage());
            }
        }
    }

    /*
     * public void deleteAllFinancialObligations() {
     * controller.deleteAllFinancialObligations();
     * }
     * 
     * public void deleteAllIncomes() {
     * controller.deleteAllIncomes();
     * }
     */
}
