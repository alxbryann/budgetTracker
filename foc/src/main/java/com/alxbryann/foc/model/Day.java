package com.alxbryann.foc.model;

import java.util.ArrayList;

public class Day {

    private int numberDay;
    private ArrayList<Transaction> incomes = new ArrayList<>();

    public void setNumberDay(int numberDay) {
        this.numberDay = numberDay;
    }

    public int getNumberDay() {
        return numberDay;
    }

    public void setNewTransaction(Transaction in) {
        incomes.add(in);
    }

    public void removeIncome(Transaction in) {
        incomes.remove(in);
    }

    public void removeIncomeById(int id) {
        incomes.removeIf(in -> in.getId() == id);
    }

    public void removeAllIncomes() {
        incomes = new ArrayList<>();
    }

    public ArrayList<Transaction> getIncomes() {
        return incomes;
    }

    public double getTotalIncome() {
        double totalIncome = 0;
        if (incomes.isEmpty()) {
            return 0;
        }
        for (Transaction in : incomes) {
            totalIncome += in.getValue();
        }
        return totalIncome;
    }

    public double getTotalNet() {
        double totalNet = 0;
        totalNet = getTotalIncome();
        return totalNet;
    }

}
