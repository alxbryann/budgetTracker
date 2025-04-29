package com.alxbryann.foc.model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

public final class Calendar {

    private final ArrayList<Month> months = new ArrayList<>();

    public Calendar() {
        for (int i = 0; i < 11; i++) {
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

    public LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    public int getCurrentMonth() {
        return LocalDate.now().getMonthValue();
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
