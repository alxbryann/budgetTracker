package com.alxbryann.foc.model;

import java.awt.Color;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author alxbryann
 */
public class Model {

    public void addTransactionToDay(Transaction transaction, int dayNumber) {
        Day day = calendar.getDayByNumberInSpecificMonth(dayNumber, calendar.getCurrentMonth());
        day.setNewTransaction(transaction);
    }

    Calendar calendar = new Calendar();
    Controller controller = new Controller();

    public Model(Controller controller) {
        this.controller = controller;
    }

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
        
        if (isRepetitive && repetitiveByWeek) {
            int currentMonth = calendar.getCurrentMonth();
            int currentYear = calendar.getCurrentYear();
            YearMonth ym = YearMonth.of(currentYear, currentMonth);
            int daysInMonth = ym.lengthOfMonth();

            for (int day = 1; day <= daysInMonth; day++) {
                try {
                    LocalDate occurrence = LocalDate.of(currentYear, currentMonth, day);
                    // match day of week
                    if (occurrence.getDayOfWeek().equals(localDate.getDayOfWeek())) {
                        if (!occurrence.equals(localDate)) {
                            Transaction occTrans = new Transaction();
                            occTrans.setName(name);
                            occTrans.setValue(valueDouble);
                            Date occDate = Date.from(occurrence.atStartOfDay(defaultZoneId).toInstant());
                            occTrans.setDate(occDate);
                            occTrans.setColor(selectedColor);
                            // these created occurrences are not marked as repetitive
                            occTrans.setIsRepetitive(false);
                            occTrans.setRepetitiveByWeek(false);
                            occTrans.setRepetitiveByMonth(false);
                            controller.createIncome(occTrans);
                        }
                    }
                } catch (Exception e) {
                    // ignore invalid dates or persistence exceptions for individual occurrences
                }
            }
        }
    }

    public int getNumberOfDaysInCurrentMonth() {
        return calendar.getNumberOfDaysInCurrentMonth();
    }

    public void assignTransactionToDays() {
        List<Transaction> allTransactions = controller.findAllTransactions();

        if (allTransactions.isEmpty())
            return;

        for (int i = 0; i < allTransactions.size(); i++) {
            Transaction temporalTransaction = (Transaction) allTransactions.get(i);
            Date date = temporalTransaction.getDate();
            int dayOfTransaction = calendar.getDayFromTransaction(date) - 1;
            int monthOfTransaction = calendar.getMonthFromTransaction(date);
            Day temporalDay = calendar.getDayByNumberInSpecificMonth(dayOfTransaction, monthOfTransaction);
            temporalDay.setNewTransaction(temporalTransaction);
            if (!calendar.getBusyDaysInCurrentMonth().contains(temporalDay)) {
                calendar.addToBusyDaysInSpecificMonth(temporalDay, monthOfTransaction);
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

    public ArrayList<Integer> getListOfTransactionsInCurrentMonth() {
        ArrayList transactionsInCurrentMonth = new ArrayList<>();
        for (int i = 0; i < controller.getListOfTransactionsCurrentMonth().size(); i++) {
            Transaction temporalTransaction = controller.getListOfTransactionsCurrentMonth().get(i);

            Date transactionDate = temporalTransaction.getDate();
            // Transaction day -1 because of index in viewCalendar
            int transactionDay = calendar.getDayFromTransaction(transactionDate) - 1;
            String transactionName = temporalTransaction.getName();
            String transactionRgb = temporalTransaction.getRgb();
            transactionsInCurrentMonth.add(transactionDay);
            transactionsInCurrentMonth.add(transactionRgb);
            transactionsInCurrentMonth.add(transactionName);

        }

        return transactionsInCurrentMonth;
    }

    public List<HashMap<String, Object>> getTransactionsByDay(int day) {
        List<Transaction> transactions = controller.findTransactionsByDay(day);
        List<HashMap<String, Object>> result = new ArrayList<>();
        for (Transaction t : transactions) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", t.getId());
            map.put("name", t.getName());
            map.put("value", t.getValue());
            result.add(map);
        }
        return result;
    }

    public ArrayList<Integer> getListOfRepetitiveTransactionsInCurrentMonth() {
        ArrayList RepetitiveIncomesInCurrentMonth = new ArrayList<>();
        List<RepetitiveTransaction> ri;
        ri = (List<RepetitiveTransaction>) controller.findAllRepetitiveTransactions();
        for (int i = 0; i < ri.size(); i++) {
            int id = ri.get(i).getRepetitiveTransaction_id();
            Transaction temporalIncome = (Transaction) controller.findTransactionById(id);
            // Only process repetitive-by-month transactions for painting.
            if (temporalIncome.isRepetitiveByMonth()) {
                int dayToPaint = calendar.getDayFromTransaction(temporalIncome.getDate());
                if (calendar.getMonthFromTransaction(temporalIncome.getDate()) != calendar.getCurrentMonth()) {
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

    public String getCurrentMonthInString() {
        return calendar.getCurrentMonthInString();
    }

    public int getCurrentYear() {
        return calendar.getCurrentYear();
    }

    public HashMap<String, Object> getInformationOfTransaction(int id) {
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
}
