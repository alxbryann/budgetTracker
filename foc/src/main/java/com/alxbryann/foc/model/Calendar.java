package com.alxbryann.foc.model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

public final class Calendar {

    private int daysInMonth;
    private final ArrayList<Day> days = new ArrayList<>();
    private final ArrayList<Day> busyDays = new ArrayList<>();
    private final ArrayList<Months> months = new ArrayList<>();

    public Calendar() {
        for (int i = 0; i < 11; i++) {
            months.add(new Months(getDaysInMonth(i + 1)));
        }
    }

    public Day getDayByNumber(int numberDay, int month) {
        return months.get(month).getDayByNumber(numberDay);
    }

    public int getDaysInMonth(int numberOfMonth) {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = numberOfMonth;

        YearMonth yearMonth = YearMonth.of(year, month);
        daysInMonth = yearMonth.lengthOfMonth();
        return daysInMonth;
    }

    public int getDaysInMonth() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        YearMonth yearMonth = YearMonth.of(year, month);
        daysInMonth = yearMonth.lengthOfMonth();
        return daysInMonth;
    }

    public ArrayList<Day> getBusyDays() {
        return months.get(LocalDate.now().getMonthValue()).getBusyDays();
    }

    public void addToBusyDays(Day days, int month) {
        months.get(month).addToBusyDays(days);
    }

    public LocalDate getLocalDate() {
        return LocalDate.now();
    }

    public int getMonth() {
        return LocalDate.now().getMonthValue(); 
    }

    public int getMonthFromFo(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
        String localDateStr = String.valueOf(localDate);
        return Integer.parseInt(localDateStr.substring(localDateStr.length() - 5, localDateStr.length() - 3));
    }

    public int getDay() {
        return LocalDate.now().getDayOfMonth(); 
    }

    public int getDayFromFo(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
        String localDateStr = String.valueOf(localDate);
        return Integer.parseInt(localDateStr.substring(localDateStr.length() - 2, localDateStr.length()));
    }
}
