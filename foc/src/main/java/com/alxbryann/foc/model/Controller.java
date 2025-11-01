package com.alxbryann.foc.model;

import com.alxbryann.foc.persistence.PersistenceController;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author alxbryann
 */
public class Controller {

    public void addTransactionToDay(Transaction transaction, int dayNumber) {
        model.addTransactionToDay(transaction, dayNumber);
    }

    public List<Transaction> findTransactionsByDay(int day) {
        return pc.findTransactionsByDay(day);
    }

    private PersistenceController pc = new PersistenceController();
    private Model model;


    public void editTransaction(Transaction transaction) {
        try {
            pc.editTransaction(transaction);
        } catch (Exception e) {
            System.out.println("Exception editing income: " + e.getMessage());
        }
    }

    public void createIncome(Transaction transaction) {
        try {
            pc.createTransaction(transaction);
            if (transaction.isRepetitive()) {
                RepetitiveTransaction ri = new RepetitiveTransaction();
                ri.setRepetitiveTransaction_id(transaction.getId());
                pc.createRepetitiveTransaction(ri);
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
                pc.deleteRepetitiveTransaction(id);
            } else {
                pc.deleteTransaction(id);
            }
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

    public List<RepetitiveTransaction> findAllRepetitiveTransactions() {
        return pc.findAllRepetitiveTransaction();
    }

    public List<Transaction> findAllTransactions() {
        return pc.findAllTransactions();
    }

    public Transaction findTransactionById(int id) {
        return pc.findTransactionById(id);
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setInfoIncome(String name, String value, String date, Color selectedColor, boolean isRepetitive,
            boolean isRepetitiveByWeek, boolean isRepetitiveByMonth) {
        model.setInfoIncome(name, value, date, selectedColor, isRepetitive, isRepetitiveByWeek, isRepetitiveByMonth);
    }

    public int getNumberOfDaysInCurrentMonth() {
        return model.getNumberOfDaysInCurrentMonth();
    }


    public void assignTransactionToDays() {
        model.assignTransactionToDays();
    }


    public ArrayList getDaysToPaint() {
        return model.getListOfTransactionsInCurrentMonth();
    }


    public  List<HashMap<String, Object>> getListOfRepetitiveTransactionsInCurrentMonth() {
        return model.getListOfRepetitiveTransactionsInCurrentMonth();
    }


    public List<HashMap<String, Object>> getTransactionsByDay(int day) {
        return model.getTransactionsByDay(day);
    }

    public double getTotalCostByDay(int day) {
        return pc.getTotalCostByDay(day);
    }

    public double getTotalIncomeByDay(int day) {
        return pc.getTotalIncomeByDay(day);
    }

    public double getTotalNet(int day) {
        return pc.getTotalNetByDay(day);
    }

    public String getCurrentMonthInString() {
        return model.getCurrentMonthInString();
    }

    public int getCurrentYear() {
        return model.getCurrentYear();
    }

    public void removeIncomeFromDayById(int id, int numberDay) {
        model.removeIncomeFromDayById(id, numberDay);
    }

    public HashMap<String, Object> getInformationOfTransaction(int id) {
        return model.getInformationOfTransaction(id);
    }

    public void deleteAllIncomes() {    
        model.deleteIncomesFromDays();
    }

    public List<Transaction> getListOfTransactionsCurrentMonth(){
        return pc.findAllTransactionsForCurrentMonth();
    }

    public void copyRepetitiveTransactionsForThisMonth() {
        pc.copyRepetitiveTransactionsForThisMonth();
    }
}
