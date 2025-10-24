package com.alxbryann.foc.model;

import java.awt.Color;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    // Removed: financial obligation edit and creation methods

    public void setInfoIncome(String name, String value, String dateStr, Color selectedColor, boolean isRepetitive,
            boolean repetitiveByWeek, boolean repetitiveByMonth) {
        Transaction income = new Transaction();
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

    // Removed: assignment and removal of financial obligations in days

    public int getNumberOfDaysInCurrentMonth() {
        return calendar.getNumberOfDaysInCurrentMonth();
    }

    // Removed: repetitive financial obligations list

    public void assignTransactionToDays() {
        List<Transaction> allTransactions = controller.findAllTransactions();
        if (allTransactions.isEmpty()) {
            return;
        }
        for (int i = 0; i < allTransactions.size(); i++) {
            Transaction temporalIncome = (Transaction) allTransactions.get(i);
            Date date = temporalIncome.getDate();
            int dayOfIncome = calendar.getDayFromFo(date) - 1;
            int monthOfIncome = calendar.getMonthFromFo(date);
            Day temporalDay = calendar.getDayByNumberInSpecificMonth(dayOfIncome, monthOfIncome);
            temporalDay.setNewTransaction(temporalIncome);
            if (!calendar.getBusyDaysInCurrentMonth().contains(temporalDay)) {
                calendar.addToBusyDaysInSpecificMonth(temporalDay, monthOfIncome);
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

    // Removed: financial obligations in current month list

    public ArrayList<Integer> getListOfIncomesInCurrentMonth() {
        ArrayList IncomesInCurrentMonth = new ArrayList<>();
        for (int i = 0; i < calendar.getBusyDaysInCurrentMonth().size(); i++) {
            Day temporalDay = calendar.getBusyDaysInCurrentMonth().get(i);
            for (int j = 0; j < temporalDay.getIncomes().size(); j++) {
                Transaction temporalIncome = (Transaction) temporalDay.getIncomes().get(j);
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
            Transaction temporalIncome = (Transaction) tempDay.getIncomes().get(i);
        }
        return tempDay.getIncomes();
    }

    public ArrayList<Integer> getListOfRepetitiveIncomesInCurrentMonth() {
        ArrayList RepetitiveIncomesInCurrentMonth = new ArrayList<>();
        List<RepetitiveIncome> ri;
        ri = (List<RepetitiveIncome>) controller.findAllRepetitiveIncomes();
        for (int i = 0; i < ri.size(); i++) {
            int id = ri.get(i).getIncomeId();
            Transaction temporalIncome = (Transaction) controller.findTransactionById(id);
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
                                tempDay.setNewTransaction(temporalIncome);
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
                    tempDay.setNewTransaction(temporalIncome);
                }
            }
        }
        return RepetitiveIncomesInCurrentMonth;
    }

    // Removed: get financial obligations by day

    // Removed: delete all financial obligations from days

    public String getCurrentMonthInString() {
        return calendar.getCurrentMonthInString();
    }

    public int getCurrentYear() {
        return calendar.getCurrentYear();
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

    // Removed: information of financial obligation

    public HashMap<String, Object> getInformationOfIncome(int id) {
        Transaction temporalIncome = controller.findTransactionById(id);
        HashMap<String, Object> incomeInformation = new HashMap<>();
        String name = temporalIncome.getName();
        Date date = temporalIncome.getDate();
        double amount = temporalIncome.getValue();
        String rgb = temporalIncome.getRgb();
        boolean isRepetitive = temporalIncome.isRepetitive();
        boolean repetitiveByWeek = temporalIncome.isRepetitiveByWeek();
        boolean repetitiveByMonth = temporalIncome.isRepetitiveByMonth();

        incomeInformation.put("name", name);
        incomeInformation.put("date", date);
        incomeInformation.put("amount", amount);
        incomeInformation.put("rgb", rgb);
        incomeInformation.put("isRepetitive", isRepetitive);
        incomeInformation.put("repetitiveByWeek", repetitiveByWeek);
        incomeInformation.put("repetitiveByMonth", repetitiveByMonth);

        return incomeInformation;
    }

    public void editIncome(int id, String name, String amount, String dateStr, Color selectedColor,
            boolean isRepetitive, boolean isRepetitiveByWeek, boolean isRepetitiveByMonth) {
        try {
            Transaction existingIncome = controller.findTransactionById(id);
            if (existingIncome != null) {
                existingIncome.setName(name);
                double amountDouble = Double.parseDouble(amount);
                existingIncome.setValue(amountDouble);

                LocalDate localDate = LocalDate.parse(dateStr);
                ZoneId defaultZoneId = ZoneId.systemDefault();
                Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
                existingIncome.setDate(date);

                String rgb = selectedColor.getRed() + ", " + selectedColor.getGreen() + ", " + selectedColor.getBlue();
                existingIncome.setRgb(rgb);
                existingIncome.setColor(selectedColor);
                existingIncome.setIsRepetitive(isRepetitive);
                existingIncome.setRepetitiveByWeek(isRepetitiveByWeek);
                existingIncome.setRepetitiveByMonth(isRepetitiveByMonth);

                controller.editTransaction(existingIncome);
            }
        } catch (Exception e) {
            System.err.println("Error editing income: " + e.getMessage());
        }
    }
}
