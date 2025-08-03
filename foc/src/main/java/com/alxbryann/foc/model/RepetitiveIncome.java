package com.alxbryann.foc.model;

import java.io.Serializable;
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
public class RepetitiveIncome implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Basic
    private int income_id;

    public RepetitiveIncome() {

    }

    public RepetitiveIncome(int id, int income_id) {
        this.id = id;
        this.income_id = income_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIncomeId() {
        return income_id;
    }

    public void setIncomeId(int income_id) {
        this.income_id = income_id;
    }

}
