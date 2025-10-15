package com.alxbryann.foc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author alxbryann
 */

@Entity
public class Month {

    @Id
    private int id;

    @OneToMany(mappedBy = "month", cascade = CascadeType.ALL)
    private List<Day> days = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "year_id")
    private Year year;

    public Month() {}

    public Month(int numberOfDays) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Day getDayByNumber(int numberDay) {
        return days.stream()
            .filter(day -> day.getId() == numberDay)
            .findFirst()
            .orElse(null);
    }
}