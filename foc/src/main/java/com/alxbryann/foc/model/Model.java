package com.alxbryann.foc.model;

import java.awt.Color;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alxbryann
 */
public class Model {

    Calendar cal = new Calendar();
    Controller controller = new Controller();

    public Model(Controller controller) {
        this.controller = controller;
    }

    public void setInfoFo(String name, String cost, String dateStr, Color selectedColor, boolean isRepetitive, boolean weekOrMonth) {
        FinancialObligation fo = new FinancialObligation();
        fo.setName(name);
        double costDouble = Double.parseDouble(cost);
        fo.setCost(costDouble);
        LocalDate localDate = LocalDate.parse(dateStr);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        fo.setDate(date);
        fo.setColor(selectedColor);
        fo.setIsRepetitive(isRepetitive);
        fo.setWeekOrMonth(weekOrMonth);
        controller.createFo(fo);
    }

    public void setInfoIncome(String name, String value, String dateStr) {
        Income income = new Income();
        income.setName(name);
        double valueDouble = Double.parseDouble(value);
        income.setValue(valueDouble);
        LocalDate localDate = LocalDate.parse(dateStr);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        income.setDate(date);
        controller.createIncome(income);
    }

    public void assignFoToDays() {
        List allFo = controller.findAllFo();
        if (!allFo.isEmpty()) {
            for (int i = 0; i < allFo.size(); i++) {
                FinancialObligation fo = (FinancialObligation) allFo.get(i);
                Date date = fo.getDate();
                LocalDate localDate = date.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
                String localDateStr = String.valueOf(localDate);
                int day = Integer.parseInt(localDateStr.substring(localDateStr.length() - 2, localDateStr.length()));
                int month = Integer.parseInt(localDateStr.substring(localDateStr.length() - 5, localDateStr.length() - 3));
                Days temp = cal.getDayByNumber(day, month);
                temp.setNewObligation(fo);
                if (!cal.getBusyDays().contains(temp)) {
                    cal.addToBusyDays(temp, month);
                }
            }
        }
    }

    public int getDaysInMonth() {
        return cal.getDaysInMonth();
    }

    public ArrayList<Integer> paintDays() {
        ArrayList days = new ArrayList<>();
        for (int i = 0; i < cal.getBusyDays().size(); i++) {
            Days tempDay = cal.getBusyDays().get(i);
            for (int j = 0; j < tempDay.getObligations().size(); j++) {
                FinancialObligation tempFo = (FinancialObligation) tempDay.getObligations().get(j);
                days.add(tempDay.getNumberDay());
                days.add(tempFo.getRgb());
                days.add(tempFo.getName());
            }
        }
        return days;
    }

    public /*ArrayList<String>*/ void getRepetitiveFO() {
        List<RepetitiveFO> rf;
        rf = (List<RepetitiveFO>) controller.findAllRepetitiveFo();
        System.out.println("size: " + rf.size());
        for (int i = 0; i < rf.size(); i++) {
            System.out.println("el id es: " + rf.get(i).getFo_id());
            int id = rf.get(i).getFo_id();
            FinancialObligation fo = (FinancialObligation) controller.findFOById(id);
            System.out.println(fo.getName() + ", se repite, desde : " + fo.getDate());
            if (fo.isWeekOrMonth()) {
                
            }
        }
    }
}
