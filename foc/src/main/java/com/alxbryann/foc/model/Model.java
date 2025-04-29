package com.alxbryann.foc.model;

import java.awt.Color;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alxbryann
 */
public class Model {

    Calendar calendar = new Calendar();
    Controller controller = new Controller();

    public Model(Controller controller) {
        this.controller = controller;
    }

    public void setInfoFinancialObligation(String name, String cost, String dateStr, Color selectedColor, boolean isRepetitive, boolean repetitiveByWeek, boolean repetitiveByMonth) {
        FinancialObligation financialObligation = new FinancialObligation();
        financialObligation.setName(name);
        double costDouble = Double.parseDouble(cost);
        financialObligation.setCost(costDouble);
        LocalDate localDate = LocalDate.parse(dateStr);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        financialObligation.setDate(date);
        financialObligation.setColor(selectedColor);
        financialObligation.setIsRepetitive(isRepetitive);
        financialObligation.setRepetitiveByWeek(repetitiveByWeek);
        financialObligation.setRepetitiveByMonth(repetitiveByMonth);
        controller.createFinancialObligation(financialObligation);
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

    public void assignFinancialObligationToDays() {
        List allFinancialObligation = controller.findAllFinancialObligations();
        if (!allFinancialObligation.isEmpty()) {
            for (int i = 0; i < allFinancialObligation.size(); i++) {
                FinancialObligation temporalFinancialObligation = (FinancialObligation) allFinancialObligation.get(i);
                Date date = temporalFinancialObligation.getDate();
                int dayOfFinancialObligation = calendar.getDayFromFo(date);
                int monthOfFinancialObligation = calendar.getMonthFromFo(date);
                Day temporalDay = calendar.getDayByNumberInSpecificMonth(dayOfFinancialObligation, monthOfFinancialObligation);
                temporalDay.setNewFinancialObligation(temporalFinancialObligation);
                if (!calendar.getBusyDaysInCurrentMonth().contains(temporalDay)) {
                    calendar.addToBusyDaysInSpecificMonth(temporalDay, monthOfFinancialObligation);
                }
            }
        }
    }

    public int getNumberOfDaysInCurrentMonth() {
        return calendar.getNumberOfDaysInCurrentMonth();
    }

    public ArrayList<Integer> getListOfFinancialObligationsInCurrentMonth() {
        ArrayList FinancialObligationsInCurrentMonth = new ArrayList<>();
        for (int i = 0; i < calendar.getBusyDaysInCurrentMonth().size(); i++) {
            Day temporalDay = calendar.getBusyDaysInCurrentMonth().get(i);
            for (int j = 0; j < temporalDay.getFinancialObligations().size(); j++) {
                FinancialObligation temporalFinancialObligation = (FinancialObligation) temporalDay.getFinancialObligations().get(j);
                if (temporalFinancialObligation.isRepetitiveByMonth()) {
                    FinancialObligationsInCurrentMonth.add(temporalDay.getNumberDay());
                    FinancialObligationsInCurrentMonth.add(temporalFinancialObligation.getRgb());
                    FinancialObligationsInCurrentMonth.add(temporalFinancialObligation.getName());
                }
            }
        }
        return FinancialObligationsInCurrentMonth;
    }

    public ArrayList<Integer> getListOfRepetitiveFinancialObligationsInCurrentMonth() {
        ArrayList RepetitiveFinancialObligationsInCurrentMonth = new ArrayList<>();
        List<RepetitiveFO> rf;
        rf = (List<RepetitiveFO>) controller.findAllRepetitiveFinancialObligations();
        for (int i = 0; i < rf.size(); i++) {
            int id = rf.get(i).getFo_id();
            FinancialObligation temporalFinancialObligation = (FinancialObligation) controller.findFinancialObligationById(id);
            if (temporalFinancialObligation.isRepetitiveByWeek()) { //if is repetitive by week
                int foDay = calendar.getDayFromFo(temporalFinancialObligation.getDate());
                int dayToPaint = foDay;
                int foMonth = calendar.getMonthFromFo(temporalFinancialObligation.getDate());
                int thisMonth = calendar.getCurrentMonth();
                while (dayToPaint < calendar.getNumberOfDaysInCurrentMonth()) {
                    try {
                        if (foMonth <= thisMonth) {
                            RepetitiveFinancialObligationsInCurrentMonth.add(dayToPaint);
                            RepetitiveFinancialObligationsInCurrentMonth.add(temporalFinancialObligation.getRgb());
                            RepetitiveFinancialObligationsInCurrentMonth.add(temporalFinancialObligation.getName());
                            dayToPaint += 7;
                            if (!RepetitiveFinancialObligationsInCurrentMonth.contains(dayToPaint)) {
                                Day tempDay = calendar.getDayByNumberInSpecificMonth(dayToPaint, calendar.getCurrentMonth());
                                tempDay.setNewFinancialObligation(temporalFinancialObligation);
                            }
                        }
                    } catch (Exception e) {
                    }
                }

            } else { // if is repetitive by month
                int dayToPaint = calendar.getDayFromFo(temporalFinancialObligation.getDate());
                if (calendar.getMonthFromFo(temporalFinancialObligation.getDate()) != calendar.getCurrentMonth()) {
                    RepetitiveFinancialObligationsInCurrentMonth.add(dayToPaint);
                    RepetitiveFinancialObligationsInCurrentMonth.add(temporalFinancialObligation.getRgb());
                    RepetitiveFinancialObligationsInCurrentMonth.add(temporalFinancialObligation.getName());
                    Day tempDay = calendar.getDayByNumberInSpecificMonth(dayToPaint, calendar.getCurrentMonth());
                    tempDay.setNewFinancialObligation(temporalFinancialObligation);
                }
            }
        }
        return RepetitiveFinancialObligationsInCurrentMonth;
    }

    public List getFinancialObligationsByDay(int day) {
        Day tempDay = calendar.getDayByNumberInSpecificMonth(day, calendar.getCurrentMonth());
        return tempDay.getFinancialObligations();
    }

}
