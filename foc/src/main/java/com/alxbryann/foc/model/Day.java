package com.alxbryann.foc.model;

import java.util.ArrayList;

public class Day {

    private int numberDay;
    private ArrayList<FinancialObligation> obligations = new ArrayList<>();
    private ArrayList<Income> incomes = new ArrayList<>();

    private double totalCost;

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

    public void setNewFinancialObligation(FinancialObligation fo) {
        obligations.add(fo);
    }

    public void removeFinancialObligation(FinancialObligation fo) {
        obligations.remove(fo);
    }

    public ArrayList<FinancialObligation> getFinancialObligations() {
        return obligations;
    }
    
    public ArrayList<Income> getIncomes() {
        return incomes;
    }

    public double getTotalCost() {
        if (obligations.isEmpty()) {
            return 0;
        }
        for (FinancialObligation fo : obligations) {
            totalCost += fo.getCost();
        }
        return totalCost;
    }

    @Override
    public String toString() {
        return "Day{" + "numberDay=" + numberDay + ", obligations=" + obligations + ", incomes=" + incomes + ", totalCost=" + totalCost + '}';
    }

    

}
