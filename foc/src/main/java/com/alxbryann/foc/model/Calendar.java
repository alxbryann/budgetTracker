package com.alxbryann.foc.model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

public final class Calendar {

    private final ArrayList<Month> months = new ArrayList<>();

    public Calendar() {
        for (int i = 0; i < 12; i++) {
            int numberOfDays = getNumberOfDaysInSpecificMonth(i + 1);
            months.add(new Month(numberOfDays));
        }
    }

    public Day getDayByNumberInSpecificMonth(int numberDay, int month) {
        return months.get(month).getDayByNumber(numberDay);
    }

    public int getNumberOfDaysInSpecificMonth(int numberOfMonth) {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = numberOfMonth;

        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        return daysInMonth;
    }

    public int getNumberOfDaysInCurrentMonth() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        return daysInMonth;
    }

    public ArrayList<Day> getBusyDaysInCurrentMonth() {
        return months.get(LocalDate.now().getMonthValue()).getBusyDays();
    }

    public void addToBusyDaysInSpecificMonth(Day days, int month) {
        months.get(month).addToBusyDays(days);
    }

    public void removeFromBusyDaysInSpecificMonth(Day days, int month) {
        months.get(month).addToBusyDays(days);
    }


    public LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    public int getCurrentMonth() {
        return LocalDate.now().getMonthValue();
    }

    public String getCurrentMonthInString() {
        int currentMonth = getCurrentMonth();
        switch (currentMonth) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "Mes inválido";
        }
    }
    
    public int getCurrentYear() {
        return LocalDate.now().getYear();
    }

    public int getMonthFromFo(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
        String localDateStr = String.valueOf(localDate);
        return Integer.parseInt(localDateStr.substring(localDateStr.length() - 5, localDateStr.length() - 3));
    }

    public int getCurrentDay() {
        return LocalDate.now().getDayOfMonth();
    }

    public int getDayFromFo(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
        String localDateStr = String.valueOf(localDate);
        return Integer.parseInt(localDateStr.substring(localDateStr.length() - 2, localDateStr.length()));
    }
}
