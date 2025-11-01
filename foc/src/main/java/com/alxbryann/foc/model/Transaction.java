package com.alxbryann.foc.model;

import java.awt.Color;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author alxbryann
 */
@Entity
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Basic
    private String name;
    @Basic
    private double value;
    private LocalDate date;
    @Basic
    private String rgb;
    @Basic
    private boolean isRepetitive;
    @Basic
    private boolean isRepetitiveByWeek;
    @Basic
    private boolean isRepetitiveByMonth;

    public Transaction() {

    }

    public Transaction(int id, String name, double value, LocalDate date, Color selectedColor, boolean isRepetitive,
            boolean isRepetitiveByWeek, boolean isRepetitiveByMonth) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.date = date;
        this.rgb = selectedColor.getRed() + ", " + selectedColor.getGreen() + ", " + selectedColor.getBlue();
        this.isRepetitive = isRepetitive;
        this.isRepetitiveByWeek = isRepetitiveByWeek;
        this.isRepetitiveByMonth = isRepetitiveByMonth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public void setColor(Color color) {
        this.rgb = color.getRed() + ", " + color.getGreen() + ", " + color.getBlue();
    }

    public boolean isRepetitive() {
        return isRepetitive;
    }

    public void setIsRepetitive(boolean isRepetitive) {
        this.isRepetitive = isRepetitive;
    }

    public boolean isRepetitiveByWeek() {
        return isRepetitiveByWeek;
    }

    public void setRepetitiveByWeek(boolean isRepetitiveByWeek) {
        this.isRepetitiveByWeek = isRepetitiveByWeek;
    }

    public boolean isRepetitiveByMonth() {
        return isRepetitiveByMonth;
    }

    public void setRepetitiveByMonth(boolean isRepetitiveByMonth) {
        this.isRepetitiveByMonth = isRepetitiveByMonth;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", date=" + date +
                ", rgb='" + rgb + '\'' +
                ", isRepetitive=" + isRepetitive +
                ", isRepetitiveByWeek=" + isRepetitiveByWeek +
                ", isRepetitiveByMonth=" + isRepetitiveByMonth +
                '}';
    }
}
