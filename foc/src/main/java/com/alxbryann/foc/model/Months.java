package com.alxbryann.foc.model;

import java.util.ArrayList;

/**
 *
 * @author barr2
 */
public class Months {

    private ArrayList<Days> days = new ArrayList<>();
    private ArrayList<Days> busyDays = new ArrayList<>();

    public Months(int numberOfDays) {
        for (int i = 0; i < numberOfDays; i++) {
            Days temp = new Days();
            temp.setNumberDay(i);
            days.add(temp);
        }
    }

    public Days getDayByNumber(int numberDay) {
        return days.get(numberDay);
    }

    public ArrayList<Days> getBusyDays() {
        return busyDays;
    }

    public void addToBusyDays(Days days) {
        busyDays.add(days);
    }

}
