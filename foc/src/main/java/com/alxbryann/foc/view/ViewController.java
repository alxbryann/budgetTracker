package com.alxbryann.foc.view;

import com.alxbryann.foc.model.Controller;
import com.alxbryann.foc.model.Transaction;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author alxbryann
 */
public class ViewController {

    private Controller controller;
    private View view;
    // Removed NextPaymentsPanel (payments) support
    private NextTransactionsPanel nextTransactionsPanel;
    private DetailContainer currentDetailContainer;

    public ViewController() {
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setView(View view) {
        this.view = view;
    }

    // Removed setter for NextPaymentsPanel

    public void setNextTransactionsPanel(NextTransactionsPanel nextTransactionsPanel) {
        this.nextTransactionsPanel = nextTransactionsPanel;
    }

    public void setCurrentDetailContainer(DetailContainer detailContainer) {
        this.currentDetailContainer = detailContainer;
    }

    public void updateDetailContainer() {
        if (currentDetailContainer != null) {
            currentDetailContainer.refreshElementsDetail();
        }
    }

    // Removed: FinancialObligation creation

    public void setInfoIncome(String name, String value, String date, Color selectedColor, boolean isRepetitive,
            boolean isRepetitiveByWeek, boolean isRepetitiveByMonth) {
        controller.setInfoIncome(name, value, date, selectedColor, isRepetitive, isRepetitiveByWeek,
                isRepetitiveByMonth);
    }

    // Removed: FinancialObligation listing

    public List<Transaction> getInfoIncome() {
        return controller.findAllTransactions();
    }

    public int getDaysInCurrentMonth() {
        return controller.getNumberOfDaysInCurrentMonth();
    }

    // Removed: assign financial obligations to days

    public void assignTransactionToDays() {
        controller.assignTransactionToDays();
    }

    // Removed: paint financial obligations

    public ArrayList getDaysToPaint() {
        return controller.getDaysToPaint();
    }

    // Removed: paint repetitive financial obligations

public ArrayList paintRepetitiveTransactions() {
        return controller.paintRepetitiveTransactions();
    }

    /*public void paintTransactionsInView() {
        view.paintTransactionsInView();
    }*/

    // Removed: get financial obligations by day

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
        return view.getDayClicked();
    }
    
    public double getTotalIncomeByDay(int day) {
        return controller.getTotalIncomeByDay(day);
    }

    public double getTotalNetByDay(int day) {
        return controller.getTotalNet(day);
    }

    // Removed: delete financial obligation

    public void deleteTransactionById(int id) {
        controller.deleteTransactionById(id);
    }

    public void clearViewCalendar() {
        view.clearViewCalendar();
    }

    /*public void updateViewCalendar() {
        view.updateViewCalendar();
    }*/

    public void updateNextIncomes() {
        nextTransactionsPanel.updateTransactionsContainer();
    }

    // Removed: update next financial obligations panel

    // Removed: remove financial obligation from a day

    public void removeIncomeFromDayById(int id, int numberDay) {
        controller.removeIncomeFromDayById(id, numberDay);
    }

    // Removed: get information of a financial obligation

    public HashMap getInformationOfIncome(int id) {
        return controller.getInformationOfIncome(id);
    }

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

    // Removed: edit financial obligation

    // Removed: delete all financial obligations

    public void deleteAllIncomes() {
        controller.deleteAllIncomes();
    }
}
