package com.alxbryann.foc.model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public final class Calendar {

    private int daysInMonth;
    private final ArrayList<Days> days = new ArrayList<>();
    private final ArrayList<Days> busyDays = new ArrayList<>();
    private final ArrayList<Months> months = new ArrayList<>();

    public Calendar() {
        for (int i = 0; i < 11; i++) {
            months.add(new Months(getDaysInMonth(i + 1)));
        }
    }

    public Days getDayByNumber(int numberDay, int month) {
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

    public ArrayList<Days> getBusyDays() {
        return months.get(LocalDate.now().getMonthValue()).getBusyDays();
    }

    public void addToBusyDays(Days days, int month) {
        months.get(month).addToBusyDays(days);
    }

    public LocalDate getLocalDate() {
        return LocalDate.now();
    }
}
