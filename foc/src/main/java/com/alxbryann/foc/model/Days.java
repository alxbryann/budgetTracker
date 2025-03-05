package com.alxbryann.foc.model;

import java.util.ArrayList;

public class Days {

    private int numberDay;
    private ArrayList<FinancialObligation> obligations = new ArrayList<>();
    private double totalCost;

    public void setNumberDay(int numberDay) {
        this.numberDay = numberDay;
    }

    public int getNumberDay() {
        return numberDay;
    }

    public void setNewObligation(FinancialObligation fo) {
        obligations.add(fo);
    }

    public void removeObligation(FinancialObligation fo) {
        obligations.remove(fo);
    }

    public ArrayList<FinancialObligation> getObligations() {
        return obligations;
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
        return "Days{" + "numberDay=" + numberDay + ", obligations=" + obligations + ", totalCost=" + totalCost + '}';
    }
    
}
