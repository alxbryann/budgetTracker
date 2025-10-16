package com.alxbryann.foc.model;

import com.alxbryann.foc.persistence.PersistenceController;
import java.awt.Color;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author alxbryann
 */
public class Controller {

    private PersistenceController pc = new PersistenceController();
    private Model model;

    public void editTransaction(Transaction transaction) {
        try {
            pc.editTransaction(transaction);
        } catch (Exception e) {
            System.out.println("Exception editing income: " + e.getMessage());
        }
    }

    public void createTransaction(String name, String value, String dateStr, Color selectedColor, boolean isRepetitive,
            boolean isRepetitiveByWeek, boolean isRepetitiveByMonth) {
        Transaction income = new Transaction();
        income.setName(name);
        double valueDouble = Double.parseDouble(value);
        income.setValue(valueDouble);

        LocalDate localDate = LocalDate.parse(dateStr);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        income.setDate(date);

        income.setColor(selectedColor);
        income.setIsRepetitive(isRepetitive);
        income.setRepetitiveByWeek(isRepetitiveByWeek);
        income.setRepetitiveByMonth(isRepetitiveByMonth);

        // Crear o buscar Year
        int yearNumber = localDate.getYear();
        Year year = pc.findYearById(yearNumber);
        if (year == null) {
            year = new Year();
            year.setId(yearNumber);
            pc.createYear(year);
        }

        // Crear o buscar Month
        int monthNumber = localDate.getMonthValue();
        Month month = pc.findMonthByYearAndNumber(yearNumber, monthNumber);
        if (month == null) {
            month = new Month();
            month.setYear(year);
            month.setId(monthNumber);
            pc.createMonth(month);
        }

        // Buscar el Day específico
        int dayNumber = localDate.getDayOfMonth();
        Day day = pc.findDayByDate(yearNumber, monthNumber, dayNumber);
        if (day == null) {
            day = new Day();
            day.setMonth(month);
            day.setId(dayNumber);
            pc.createDay(day);
        }

        // Asociar el income al día
        income.setDay(day);

        income.setColor(selectedColor);
        income.setIsRepetitive(isRepetitive);
        income.setRepetitiveByWeek(isRepetitiveByWeek);
        income.setRepetitiveByMonth(isRepetitiveByMonth);

        try {
            pc.createTransaction(income);
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

    public void createTransaction(Transaction income) {
    }

    public void deleteTransactionById(int id) {
        try {
            Transaction temporalTransaction = findTransactionById(id);
            pc.deleteTransaction(id);
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

    public List findAllTransactions() {
        return pc.findAllTransactions();
    }

    public Transaction findTransactionById(int id) {
        return pc.findTransactionById(id);
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setInfoTransaction(String name, String value, String date, Color selectedColor, boolean isRepetitive,
            boolean isRepetitiveByWeek, boolean isRepetitiveByMonth) {
        model.setInfoTransaction(name, value, date, selectedColor, isRepetitive, isRepetitiveByWeek,
                isRepetitiveByMonth);
    }

    public int getNumberOfDaysInCurrentMonth() {
        return model.getNumberOfDaysInCurrentMonth();
    }
    /*
     * 
     * public void assignFinancialObligationToDays() {
     * model.assignFinancialObligationToDays();
     * }
     * 
     * public void assignTransactionsToDays() {
     * model.assignIncomesToDays();
     * }
     * 
     * public ArrayList paintFOs() {
     * return model.getListOfFinancialObligationsInCurrentMonth();
     * }
     * 
     * public ArrayList paintINs() {
     * return model.getListOfIncomesInCurrentMonth();
     * }
     * 
     * public ArrayList paintRepetitiveFinancialObligations() {
     * return model.getListOfRepetitiveFinancialObligationsInCurrentMonth();
     * }
     * 
     * public ArrayList paintRepetitiveIncomes() {
     * return model.getListOfRepetitiveIncomesInCurrentMonth();
     * }
     * 
     * public List getFinancialObligationsByDay(int day) {
     * return model.getFinancialObligationsByDay(day);
     * }
     * 
     * public List getIncomesByDay(int day) {
     * return model.getIncomesByDay(day);
     * }
     * 
     * public double getTotalCostByDay(int day) {
     * return model.getTotalCostByDay(day);
     * }
     * 
     * public double getTotalIncomeByDay(int day) {
     * return model.getTotalIncomeByDay(day);
     * }
     * 
     * public double getTotalNet(int day) {
     * return model.getTotalNetByDay(day);
     * }
     */

    public String getCurrentMonthInString() {
        return model.getCurrentMonthInString();
    }

    public int getCurrentYear() {
        return model.getCurrentYear();
    }

    /*
     * public void removeFinancialObligationFromDayById(int id, int numberDay) {
     * model.removeFinancialObligationFromDayById(id, numberDay);
     * }
     * 
     * public void removeIncomeFromDayById(int id, int numberDay) {
     * model.removeIncomeFromDayById(id, numberDay);
     * }
     * 
     * public HashMap getInformationOfFinancialObligation(int id) {
     * return model.getInformationOfFinancialObligation(id);
     * }
     * 
     * public HashMap getInformationOfIncome(int id) {
     * return model.getInformationOfIncome(id);
     * }
     * 
     * public void deleteAllFinancialObligations() {
     * model.deleteFinancialObligationsFromDays();
     * }
     * 
     * public void deleteAllIncomes() {
     * model.deleteIncomesFromDays();
     * }
     */
}
