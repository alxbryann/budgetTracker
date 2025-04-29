package com.alxbryann.foc.model;

import java.util.ArrayList;

/**
 *
 * @author barr2
 */
public class Month {

    private ArrayList<Day> days = new ArrayList<>();
    private ArrayList<Day> busyDays = new ArrayList<>();

    public Month(int numberOfDays) {
        for (int i = 0; i < numberOfDays; i++) {
            Day temp = new Day();
            temp.setNumberDay(i);
            days.add(temp);
        }
    }

    public Day getDayByNumber(int numberDay) {
        return days.get(numberDay);
    }

    public ArrayList<Day> getBusyDays() {
        return busyDays;
    }

    public void addToBusyDays(Day days) {
        busyDays.add(days);
    }

}
