package com.alxbryann.foc.model;

import com.alxbryann.foc.persistence.PersistenceController;
import java.awt.Color;
import java.util.ArrayList;
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


    public void editTransaction(Transaction income) {
        try {
            pc.editTransaction(income);
        } catch (Exception e) {
            System.out.println("Exception editing income: " + e.getMessage());
        }
    }

    public void createIncome(Transaction income) {
        try {
            pc.createTransaction(income);
            if (income.isRepetitive()) {
                RepetitiveIncome ri = new RepetitiveIncome();
                ri.setIncomeId(income.getId());
                pc.createRepetitiveIncome(ri);
            }
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

    public void deleteTransactionById(int id) {
        try {
            Transaction temporalIncome = findTransactionById(id);
            if (temporalIncome.isRepetitive()) {
                pc.deleteTransaction(id);
                pc.deleteRepetitiveIncome(id);
            } else {
                pc.deleteTransaction(id);
            }
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

    // Removed financial obligation methods

    public List findAllRepetitiveIncomes() {
        return pc.findAllRepetitiveIncomes();
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

    // Removed: set info for financial obligation

    public void setInfoIncome(String name, String value, String date, Color selectedColor, boolean isRepetitive,
            boolean isRepetitiveByWeek, boolean isRepetitiveByMonth) {
        model.setInfoIncome(name, value, date, selectedColor, isRepetitive, isRepetitiveByWeek, isRepetitiveByMonth);
    }

    public int getNumberOfDaysInCurrentMonth() {
        return model.getNumberOfDaysInCurrentMonth();
    }

    // Removed: assign financial obligations to days

    public void assignTransactionToDays() {
        model.assignTransactionToDays();
    }

    // Removed: paint financial obligations

    public ArrayList paintINs() {
        return model.getListOfIncomesInCurrentMonth();
    }

    // Removed: paint repetitive financial obligations

    public ArrayList paintRepetitiveIncomes() {
        return model.getListOfRepetitiveIncomesInCurrentMonth();
    }

    // Removed: get financial obligations by day

    public List getIncomesByDay(int day) {
        return model.getIncomesByDay(day);
    }

    public double getTotalIncomeByDay(int day) {
        return model.getTotalIncomeByDay(day);
    }

    public double getTotalNet(int day) {
        return model.getTotalNetByDay(day);
    }

    public String getCurrentMonthInString() {
        return model.getCurrentMonthInString();
    }

    public int getCurrentYear() {
        return model.getCurrentYear();
    }

    // Removed: remove financial obligation from day

    public void removeIncomeFromDayById(int id, int numberDay) {
        model.removeIncomeFromDayById(id, numberDay);
    }

    // Removed: get information of financial obligation

    public HashMap getInformationOfIncome(int id) {
        return model.getInformationOfIncome(id);
    }

    // Removed: delete all financial obligations

    public void deleteAllIncomes() {    
        model.deleteIncomesFromDays();
    }
}
