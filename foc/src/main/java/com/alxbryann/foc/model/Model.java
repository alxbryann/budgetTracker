package com.alxbryann.foc.model;

import java.awt.Color;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.alxbryann.foc.view.ElementDetail;

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

    public void editFinancialObligation(int id, String name, String cost, String dateStr, Color selectedColor,
            boolean isRepetitive, boolean repetitiveByWeek, boolean repetitiveByMonth) {
        try {
            FinancialObligation existingFo = controller.findFinancialObligationById(id);
            if (existingFo != null) {
                existingFo.setName(name);
                double costDouble = Double.parseDouble(cost);
                existingFo.setCost(costDouble);
                LocalDate localDate = LocalDate.parse(dateStr);
                ZoneId defaultZoneId = ZoneId.systemDefault();
                Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
                existingFo.setDate(date);
                existingFo.setColor(selectedColor);
                existingFo.setIsRepetitive(isRepetitive);
                existingFo.setRepetitiveByWeek(repetitiveByWeek);
                existingFo.setRepetitiveByMonth(repetitiveByMonth);
                controller.editFinancialObligation(existingFo);
            }
        } catch (Exception e) {
            System.err.println("Error editing financial obligation: " + e.getMessage());
        }
    }

    public void setInfoFinancialObligation(String name, String cost, String dateStr, Color selectedColor,
            boolean isRepetitive, boolean repetitiveByWeek, boolean repetitiveByMonth) {
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

    public void setInfoIncome(String name, String value, String dateStr, Color selectedColor, boolean isRepetitive,
            boolean repetitiveByWeek, boolean repetitiveByMonth) {
        Income income = new Income();
        income.setName(name);
        double valueDouble = Double.parseDouble(value);
        income.setValue(valueDouble);

        LocalDate localDate = LocalDate.parse(dateStr);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        income.setDate(date);

        income.setColor(selectedColor);
        income.setIsRepetitive(isRepetitive);
        income.setRepetitiveByWeek(repetitiveByWeek);
        income.setRepetitiveByMonth(repetitiveByMonth);

        controller.createIncome(income);
    }

    public void assignFinancialObligationToDays() {
        List allFinancialObligation = controller.findAllFinancialObligations();
        if (!allFinancialObligation.isEmpty()) {
            for (int i = 0; i < allFinancialObligation.size(); i++) {
                FinancialObligation temporalFinancialObligation = (FinancialObligation) allFinancialObligation.get(i);
                Date date = temporalFinancialObligation.getDate();
                int dayOfFinancialObligation = calendar.getDayFromFo(date) - 1;
                int monthOfFinancialObligation = calendar.getMonthFromFo(date);
                Day temporalDay = calendar.getDayByNumberInSpecificMonth(dayOfFinancialObligation,
                        monthOfFinancialObligation);
                temporalDay.setNewFinancialObligation(temporalFinancialObligation);
                if (!calendar.getBusyDaysInCurrentMonth().contains(temporalDay)) {
                    calendar.addToBusyDaysInSpecificMonth(temporalDay, monthOfFinancialObligation);
                }
            }
        }
    }

    public void removeFinancialObligationFromDayById(int id, int numberDay) {
        Day temporalDay = calendar.getDayByNumberInSpecificMonth(numberDay, calendar.getCurrentMonth());
        temporalDay.removeFinancialObligationById(id);
    }

    public int getNumberOfDaysInCurrentMonth() {
        return calendar.getNumberOfDaysInCurrentMonth();
    }

    public ArrayList<Integer> getListOfRepetitiveFinancialObligationsInCurrentMonth() {
        ArrayList RepetitiveFinancialObligationsInCurrentMonth = new ArrayList<>();
        List<RepetitiveFO> rf;
        rf = (List<RepetitiveFO>) controller.findAllRepetitiveFinancialObligations();
        for (int i = 0; i < rf.size(); i++) {
            int id = rf.get(i).getFo_id();
            FinancialObligation temporalFinancialObligation = (FinancialObligation) controller
                    .findFinancialObligationById(id);
            if (temporalFinancialObligation.isRepetitiveByWeek()) { // if is repetitive by week
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
                                Day tempDay = calendar.getDayByNumberInSpecificMonth(dayToPaint,
                                        calendar.getCurrentMonth());
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

    public void assignIncomesToDays() {
        List allIncomes = controller.findAllIncomes();
        if (!allIncomes.isEmpty()) {
            for (int i = 0; i < allIncomes.size(); i++) {
                Income temporalIncome = (Income) allIncomes.get(i);
                Date date = temporalIncome.getDate();
                int dayOfIncome = calendar.getDayFromFo(date) - 1;
                int monthOfIncome = calendar.getMonthFromFo(date);
                Day temporalDay = calendar.getDayByNumberInSpecificMonth(dayOfIncome, monthOfIncome);
                temporalDay.setNewIncome(temporalIncome);
                if (!calendar.getBusyDaysInCurrentMonth().contains(temporalDay)) {
                    calendar.addToBusyDaysInSpecificMonth(temporalDay, monthOfIncome);
                }
            }
        }
    }

    public void deleteIncomesFromDays() {
        for (int i = 0; i < calendar.getBusyDaysInCurrentMonth().size(); i++) {
            int numberDay = calendar.getBusyDaysInCurrentMonth().get(i).getNumberDay();
            int numberMonth = calendar.getCurrentMonth();
            Day temporalDay = calendar.getDayByNumberInSpecificMonth(numberDay, numberMonth);
            temporalDay.removeAllIncomes();
        }
    }

    public void removeIncomeFromDayById(int id, int numberDay) {
        Day temporalDay = calendar.getDayByNumberInSpecificMonth(numberDay, calendar.getCurrentMonth());
        temporalDay.removeIncomeById(id);
    }

    public ArrayList<Integer> getListOfFinancialObligationsInCurrentMonth() {
        ArrayList FinancialObligationsInCurrentMonth = new ArrayList<>();
        for (int i = 0; i < calendar.getBusyDaysInCurrentMonth().size(); i++) {
            Day temporalDay = calendar.getBusyDaysInCurrentMonth().get(i);
            for (int j = 0; j < temporalDay.getFinancialObligations().size(); j++) {
                FinancialObligation temporalFinancialObligation = (FinancialObligation) temporalDay
                        .getFinancialObligations().get(j);
                if (temporalFinancialObligation.isRepetitiveByMonth()) {
                    FinancialObligationsInCurrentMonth.add(temporalDay.getNumberDay());
                    FinancialObligationsInCurrentMonth.add(temporalFinancialObligation.getRgb());
                    FinancialObligationsInCurrentMonth.add(temporalFinancialObligation.getName());
                }
            }
        }
        return FinancialObligationsInCurrentMonth;
    }

    public ArrayList<Integer> getListOfIncomesInCurrentMonth() {
        ArrayList IncomesInCurrentMonth = new ArrayList<>();
        for (int i = 0; i < calendar.getBusyDaysInCurrentMonth().size(); i++) {
            Day temporalDay = calendar.getBusyDaysInCurrentMonth().get(i);
            for (int j = 0; j < temporalDay.getIncomes().size(); j++) {
                Income temporalIncome = (Income) temporalDay.getIncomes().get(j);
                if (temporalIncome.isRepetitiveByMonth()) {
                    IncomesInCurrentMonth.add(temporalDay.getNumberDay());
                    IncomesInCurrentMonth.add(temporalIncome.getRgb());
                    IncomesInCurrentMonth.add(temporalIncome.getName());
                }
            }
        }
        return IncomesInCurrentMonth;
    }

    public List getIncomesByDay(int day) {
        Day tempDay = calendar.getDayByNumberInSpecificMonth(day, calendar.getCurrentMonth());
        for (int i = 0; i < tempDay.getIncomes().size(); i++) {
            Income temporalIncome = (Income) tempDay.getIncomes().get(i);
        }
        return tempDay.getIncomes();
    }

    public ArrayList<Integer> getListOfRepetitiveIncomesInCurrentMonth() {
        ArrayList RepetitiveIncomesInCurrentMonth = new ArrayList<>();
        List<RepetitiveIncome> ri;
        ri = (List<RepetitiveIncome>) controller.findAllRepetitiveIncomes();
        for (int i = 0; i < ri.size(); i++) {
            int id = ri.get(i).getIncomeId();
            Income temporalIncome = (Income) controller.findIncomeById(id);
            if (temporalIncome.isRepetitiveByWeek()) { // if is repetitive by week
                int incomeDay = calendar.getDayFromFo(temporalIncome.getDate());
                int dayToPaint = incomeDay;
                int incomeMonth = calendar.getMonthFromFo(temporalIncome.getDate());
                int thisMonth = calendar.getCurrentMonth();
                while (dayToPaint < calendar.getNumberOfDaysInCurrentMonth()) {
                    try {
                        if (incomeMonth <= thisMonth) {
                            RepetitiveIncomesInCurrentMonth.add(dayToPaint);
                            RepetitiveIncomesInCurrentMonth.add(temporalIncome.getRgb());
                            RepetitiveIncomesInCurrentMonth.add(temporalIncome.getName());
                            dayToPaint += 7;
                            if (!RepetitiveIncomesInCurrentMonth.contains(dayToPaint)) {
                                Day tempDay = calendar.getDayByNumberInSpecificMonth(dayToPaint,
                                        calendar.getCurrentMonth());
                                tempDay.setNewIncome(temporalIncome);
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            } else { // if is repetitive by month
                int dayToPaint = calendar.getDayFromFo(temporalIncome.getDate());
                if (calendar.getMonthFromFo(temporalIncome.getDate()) != calendar.getCurrentMonth()) {
                    RepetitiveIncomesInCurrentMonth.add(dayToPaint);
                    RepetitiveIncomesInCurrentMonth.add(temporalIncome.getRgb());
                    RepetitiveIncomesInCurrentMonth.add(temporalIncome.getName());
                    Day tempDay = calendar.getDayByNumberInSpecificMonth(dayToPaint, calendar.getCurrentMonth());
                    tempDay.setNewIncome(temporalIncome);
                }
            }
        }
        return RepetitiveIncomesInCurrentMonth;
    }

    public List getFinancialObligationsByDay(int day) {
        Day tempDay = calendar.getDayByNumberInSpecificMonth(day, calendar.getCurrentMonth());
        return tempDay.getFinancialObligations();
    }

    public void deleteFinancialObligationsFromDays() {
        for (int i = 0; i < calendar.getBusyDaysInCurrentMonth().size(); i++) {
            int numberDay = calendar.getBusyDaysInCurrentMonth().get(i).getNumberDay();
            int numberMonth = calendar.getCurrentMonth();
            Day temporalDay = calendar.getDayByNumberInSpecificMonth(numberDay, numberMonth);
            temporalDay.removeAllFinancialObligations();
        }
    }

    public String getCurrentMonthInString() {
        return calendar.getCurrentMonthInString();
    }

    public int getCurrentYear() {
        return calendar.getCurrentYear();
    }

    public double getTotalCostByDay(int day) {
        Day tempDay = calendar.getDayByNumberInSpecificMonth(day, calendar.getCurrentMonth());
        double totalCost = tempDay.getTotalCost();
        return totalCost;
    }

    public double getTotalIncomeByDay(int day) {
        Day tempDay = calendar.getDayByNumberInSpecificMonth(day, calendar.getCurrentMonth());
        double totalIncome = tempDay.getTotalIncome();
        return totalIncome;
    }

    public double getTotalNetByDay(int day) {
        Day tempDay = calendar.getDayByNumberInSpecificMonth(day, calendar.getCurrentMonth());
        double totalNet = tempDay.getTotalNet();
        return totalNet;
    }

    public HashMap<String, Object> getInformationOfFinancialObligation(int id) {
        FinancialObligation temporalFinancialObligation = controller.findFinancialObligationById(id);
        HashMap<String, Object> financialObligationInformation = new HashMap<>();
        String name = temporalFinancialObligation.getName();
        Date date = temporalFinancialObligation.getDate();
        double cost = temporalFinancialObligation.getCost();
        String rgb = temporalFinancialObligation.getRgb();
        boolean isRepetitive = temporalFinancialObligation.isRepetitive();
        boolean repetitiveByWeek = temporalFinancialObligation.isRepetitiveByWeek();
        boolean repetitiveByMonth = temporalFinancialObligation.isRepetitiveByMonth();
        
        financialObligationInformation.put("name", name);
        financialObligationInformation.put("date", date);
        financialObligationInformation.put("cost", cost);
        financialObligationInformation.put("rgb", rgb);
        financialObligationInformation.put("isRepetitive", isRepetitive);
        financialObligationInformation.put("repetitiveByWeek", repetitiveByWeek);
        financialObligationInformation.put("repetitiveByMonth", repetitiveByMonth);
        
        return financialObligationInformation;
    }
}
