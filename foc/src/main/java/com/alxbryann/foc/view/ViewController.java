package com.alxbryann.foc.view;

import com.alxbryann.foc.model.Controller;
import com.alxbryann.foc.model.Transaction;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author alxbryann
 */
public class ViewController {
    // Crea una nueva transacción y la asigna al día
    public void createTransaction(int dayNumber, String name, double value) {
        Transaction transaction = new Transaction();
        transaction.setName(name);
        transaction.setValue(value);
        // Puedes ajustar la fecha aquí si tienes lógica para el mes/año actual
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.DAY_OF_MONTH, dayNumber);
        transaction.setDate(cal.getTime());
        controller.addTransactionToDay(transaction, dayNumber);
    }

    private Controller controller;
    private View view;
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

    public void setInfoIncome(String name, String value, String date, Color selectedColor, boolean isRepetitive,
            boolean isRepetitiveByWeek, boolean isRepetitiveByMonth) {
        controller.setInfoIncome(name, value, date, selectedColor, isRepetitive, isRepetitiveByWeek,
                isRepetitiveByMonth);
    }

    public List<Transaction> getInfoIncome() {
        return controller.findAllTransactions();
    }

    public int getDaysInCurrentMonth() {
        return controller.getNumberOfDaysInCurrentMonth();
    }

    public void assignTransactionToDays() {
        controller.assignTransactionToDays();
    }

    public ArrayList getDaysToPaint() {
        return controller.getDaysToPaint();
    }

    public  List<HashMap<String, Object>> getListOfRepetitiveTransactionsInCurrentMonth() {
        return controller.getListOfRepetitiveTransactionsInCurrentMonth();
    }

    public List<HashMap<String, Object>> getTransactionsByDay(int day) {
        return controller.getTransactionsByDay(day);
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

    public double getTotalCostByDay(int day) {
        return controller.getTotalCostByDay(day);
    }

    public double getTotalIncomeByDay(int day) {
        return controller.getTotalIncomeByDay(day);
    }

    public double getTotalNetByDay(int day) {
        return controller.getTotalNet(day);
    }

    public void deleteTransactionById(int id) {
        controller.deleteTransactionById(id);
    }

    public void clearViewCalendar() {
        view.clearViewCalendar();
    }

    public void updateNextTransactions() {
        nextTransactionsPanel.updateTransactionsContainer();
    }

    public void removeIncomeFromDayById(int id, int numberDay) {
        controller.removeIncomeFromDayById(id, numberDay);
    }

    public HashMap<String, Object> getInformationOfTransaction(int id) {
        return controller.getInformationOfTransaction(id);
    }

    public void editTransaction(int id, String name, String amount, String date, Color selectedColor,
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

    public void deleteAllIncomes() {
        controller.deleteAllIncomes();
    }

    /**
     * Updates the calendar view by clearing the current view and repainting all
     * transactions.
     * This method should be called whenever transactions are added, edited, or
     * deleted
     * to ensure the calendar displays the most current data.
     */
    public void updateCalendarView() {
        if (view != null) {
            view.clearViewCalendar();
            controller.assignTransactionToDays();
            JPanel[] calendarPanels = view.getViewCalendar();
            if (calendarPanels != null) {
                view.paintTransactionsInCalendarTab(calendarPanels);
            }
        }
    }
}
