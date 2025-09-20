package com.alxbryann.foc.model;

import com.alxbryann.foc.persistence.PersistenceController;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alxbryann
 */
public class Controller {

    private PersistenceController pc = new PersistenceController();
    private Model model;

    public void createFinancialObligation(FinancialObligation fo) {
        try {
            pc.createFo(fo);
            if (fo.isRepetitive()) {
                RepetitiveFO rf = new RepetitiveFO();
                rf.setFo_id(fo.getId());
                pc.createRepetitiveFo(rf);
            }
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
    
    public void deleteFinancialObligationById(int id) {
        try {
            FinancialObligation temporalFinancialObligation = findFinancialObligationById(id);
            System.out.println("i am here");
            if (temporalFinancialObligation.isRepetitive()) {
                System.out.println("first");
                pc.deleteFo(id);
                System.out.println("good");
                pc.deleteRepetitiveFo(id);
                System.out.println("good 2");
            } else {
                System.out.println("second");
                pc.deleteFo(id);
            }
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

    public void createIncome(Income income) {
        try {
            pc.createIncome(income);
            if (income.isRepetitive()) {
                RepetitiveIncome ri = new RepetitiveIncome();
                ri.setIncomeId(income.getId());
                pc.createRepetitiveIncome(ri);
            }
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
    
    public void deleteIncomeById(int id) {
        try {
            Income temporalIncome = findIncomeById(id);
            if (temporalIncome.isRepetitive()) {
                pc.deleteIncome(id);
                pc.deleteRepetitiveIncome(id);
            } else {
                pc.deleteIncome(id);
            }
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }


    public List findAllRepetitiveFinancialObligations() {
        return pc.findAllRepetitiveFO();
    }

    public List findAllFinancialObligations() {
        return pc.findAllFo();
    }

    public FinancialObligation findFinancialObligationById(int id) {
        return pc.findFoById(id);
    }

    public List findAllRepetitiveIncomes() {
        return pc.findAllRepetitiveIncomes();
    }

    public List findAllIncomes() {
        return pc.findAllIncomes();
    }

    public Income findIncomeById(int id) {
        return pc.findIncomeById(id);
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setInfoFinancialObligation(String name, String cost, String date, Color selectedColor, boolean isRepetitive, boolean isRepetitiveByWeek, boolean isRepetitiveByMonth) {
        model.setInfoFinancialObligation(name, cost, date, selectedColor, isRepetitive, isRepetitiveByWeek, isRepetitiveByMonth);
    }

    public void setInfoIncome(String name, String value, String date, Color selectedColor, boolean isRepetitive, boolean isRepetitiveByWeek, boolean isRepetitiveByMonth) {
        model.setInfoIncome(name, value, date, selectedColor, isRepetitive, isRepetitiveByWeek, isRepetitiveByMonth);
    }

    public int getNumberOfDaysInCurrentMonth() {
        return model.getNumberOfDaysInCurrentMonth();
    }

    public void assignFinancialObligationToDays() {
        model.assignFinancialObligationToDays();
    }

    public void assignIncomesToDays() {
        model.assignIncomesToDays();
    }

    public ArrayList paintFOs() {
        return model.getListOfFinancialObligationsInCurrentMonth();
    }

    public ArrayList paintINs() {
        return model.getListOfIncomesInCurrentMonth();
    }

    public ArrayList paintRepetitiveFinancialObligations() {
        return model.getListOfRepetitiveFinancialObligationsInCurrentMonth();
    }

    public ArrayList paintRepetitiveIncomes() {
        return model.getListOfRepetitiveIncomesInCurrentMonth();
    }

    public List getFinancialObligationsByDay(int day) {
        return model.getFinancialObligationsByDay(day);
    }

    public List getIncomesByDay(int day) {
        return model.getIncomesByDay(day);
    }

    public double getTotalCostByDay(int day) {
        return model.getTotalCostByDay(day);
    }
    
    public double getTotalIncomeByDay(int day) {
        return model.getTotalIncomeByDay(day);
    }
    
    public double getTotalNet(int day){
        return model.getTotalNetByDay(day);
    }

    public String getCurrentMonthInString() {
        return model.getCurrentMonthInString();
    }

    public int getCurrentYear() {
        return model.getCurrentYear();
    }
}
