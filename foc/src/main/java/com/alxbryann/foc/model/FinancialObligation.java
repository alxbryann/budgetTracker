package com.alxbryann.foc.model;

/**
 *
 * @author alxbryann
 */

import java.awt.Color;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class FinancialObligation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Basic
    private String name;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic
    private double cost;
    @Basic
    private String rgb;
    @Basic
    private boolean isRepetitive;
    @Basic
    private boolean isRepetitiveByWeek;
    @Basic
    private boolean isRepetitiveByMonth;

    public FinancialObligation() {

    }

    public FinancialObligation(int id, String name, Date date, double cost, Color selectedColor, boolean isRepetitive, boolean isRepetitiveByWeek, boolean isRepetitiveByMonth) {
        this.id = id;        this.name = name;
        this.date = date;
        this.cost = cost;
        rgb += selectedColor.getRed() + ", " + selectedColor.getGreen() + ", "
                + selectedColor.getBlue();
        this.isRepetitive = isRepetitive;
        this.isRepetitiveByWeek = isRepetitiveByWeek;
        this.isRepetitiveByMonth = isRepetitiveByMonth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public String getRgb() {
        return rgb;
    }

public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public void setColor(Color color) {
        rgb = color.getRed() + ", " + color.getGreen() + ", "
                + color.getBlue();
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
    
    public boolean isRepetitiveByMonth() {
        return isRepetitiveByMonth;
    }

    public void setRepetitiveByWeek(boolean isRepetitiveByWeek) {
        this.isRepetitiveByWeek = isRepetitiveByWeek;
    }
    
    public void setRepetitiveByMonth(boolean isRepetitiveByMonth) {
        this.isRepetitiveByMonth = isRepetitiveByMonth;
    }

    @Override
    public String toString() {
        return "FinancialObligation{" + "id=" + id + ", name=" + name + ", date=" + date + ", cost=" + cost + ", rgb=" + rgb + ", isRepetitive=" + isRepetitive + ", repetitiveByWeek=" + isRepetitiveByWeek + ", repetitiveByMonth=" + isRepetitiveByMonth + '}';
    }
}
