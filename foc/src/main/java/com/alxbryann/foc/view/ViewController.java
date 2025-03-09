package com.alxbryann.foc.view;

import com.alxbryann.foc.model.Controller;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author barr2
 */
public class ViewController {

    private Controller controller;
    private View vt;

    public ViewController() {
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setView(View vt) {
        this.vt = vt;
    }

    public void setInfoFo(String name, String price, String date, Color selectedColor, boolean isRepetitive, boolean weekOrMonth) {
        controller.setInfoFo(name, price, date, selectedColor, isRepetitive, weekOrMonth);
    }

    public void setInfoIncome(String name, String value, String date) {
        controller.setInfoIncome(name, value, date);
    }

    public List getInfoFo() {
        return controller.findAllFo();
    }

    public void getRepetitiveFO() {
        controller.getRepetitiveFO();
    }

    public List getInfoIncome() {
        return controller.findAllIncome();
    }

    public int getDaysInMonth() {
        return controller.getDaysInMonth();
    }

    public void assignFoToDays() {
        controller.assignFoToDays();
    }

    public ArrayList paintDays() {
        return controller.paintDays();
    }

    public void paintDaysInView() {
        vt.paintDays();
    }

}
