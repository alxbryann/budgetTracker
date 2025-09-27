package com.alxbryann.foc.model;

import java.util.ArrayList;

public class Day {

    private int numberDay;
    private ArrayList<FinancialObligation> obligations = new ArrayList<>();
    private ArrayList<Income> incomes = new ArrayList<>();

    public void setNumberDay(int numberDay) {
        this.numberDay = numberDay;
    }

    public int getNumberDay() {
        return numberDay;
    }

    public void setNewIncome(Income in) {
        incomes.add(in);
    }

    public void removeIncome(Income in) {
        incomes.remove(in);
    }

    public void removeIncomeById(int id) {
        incomes.removeIf(in -> in.getId() == id);
    }

    public void setNewFinancialObligation(FinancialObligation fo) {
        obligations.add(fo);
    }

    public void removeFinancialObligation(FinancialObligation fo) {
        obligations.remove(fo);
    }

    public void removeFinancialObligationById(int id) {
        obligations.removeIf(fo -> fo.getId() == id);
    }

    public ArrayList<FinancialObligation> getFinancialObligations() {
        return obligations;
    }

    public ArrayList<Income> getIncomes() {
        return incomes;
    }

    public double getTotalCost() {
        double totalCost = 0;
        if (obligations.isEmpty()) {
            return 0;
        }
        for (FinancialObligation fo : obligations) {
            totalCost += fo.getCost();
        }
        return totalCost;
    }

    public double getTotalIncome() {
        double totalIncome = 0;
        if (incomes.isEmpty()) {
            return 0;
        }
        for (Income in : incomes) {
            totalIncome += in.getValue();
        }
        return totalIncome;
    }

    public double getTotalNet() {
        double totalNet = 0;
        totalNet = -getTotalCost() + getTotalIncome();
        return totalNet;
    }

}
