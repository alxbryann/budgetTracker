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

    public void createIncome(Income income) {
        try {
            pc.createIncome(income);
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

    public List findAllIncome() {
        return pc.finAllIncome();
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setInfoFinancialObligation(String name, String cost, String date, Color selectedColor, boolean isRepetitive, boolean isRepetitiveByWeek, boolean isRepetitiveByMonth) {
        model.setInfoFinancialObligation(name, cost, date, selectedColor, isRepetitive, isRepetitiveByWeek, isRepetitiveByMonth);
    }

    public void setInfoIncome(String name, String value, String date) {
        model.setInfoIncome(name, value, date);
    }

    public int getNumberOfDaysInCurrentMonth() {
        return model.getNumberOfDaysInCurrentMonth();
    }

    public void assignFinancialObligationToDays() {
        model.assignFinancialObligationToDays();
    }

    public ArrayList paintDays() {
        return model.getListOfFinancialObligationsInCurrentMonth();
    }

    public ArrayList paintRepetitiveDays() {
        return model.getListOfRepetitiveFinancialObligationsInCurrentMonth();
    }
    
    public List getFinancialObligationsByDay(int day){
        return model.getFinancialObligationsByDay(day);
    }

}
