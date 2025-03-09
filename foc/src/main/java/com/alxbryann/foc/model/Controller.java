package com.alxbryann.foc.model;

import com.alxbryann.foc.persistence.PersistenceController;
import com.alxbryann.foc.view.ViewController;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alxbryann
 */
public class Controller {

    private PersistenceController pc = new PersistenceController();
    private ViewController vc = new ViewController();
    private Model model;

    public void createFo(FinancialObligation fo) {
        try {
            pc.createFo(fo);
            System.out.println("creando fo, id: " + fo.getId());
            if (fo.isRepetitive()) {
                System.out.println("creando repetitivefo");
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

    public List findAllRepetitiveFo() {
        return pc.findAllRepetitiveFO();
    }

    public List findAllFo() {
        return pc.findAllFo();
    }

    public FinancialObligation findFOById(int id) {
        return pc.findFoById(id);
    }

    public List findAllIncome() {
        return pc.finAllIncome();
    }

    public void setViewController(ViewController vc) {
        this.vc = vc;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setInfoFo(String name, String cost, String date, Color selectedColor, boolean isRepetitive, boolean weekOrMonth) {
        model.setInfoFo(name, cost, date, selectedColor, isRepetitive, weekOrMonth);
    }

    public void setInfoIncome(String name, String value, String date) {
        model.setInfoIncome(name, value, date);
    }

    public int getDaysInMonth() {
        return model.getDaysInMonth();
    }

    public void assignFoToDays() {
        model.assignFoToDays();
    }

    public ArrayList paintDays() {
        return model.paintDays();
    }

    public void getRepetitiveFO() {
        model.getRepetitiveFO();
    }

}
