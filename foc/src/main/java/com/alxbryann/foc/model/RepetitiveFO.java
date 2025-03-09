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
public class RepetitiveFO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Basic
    private int fo_id;

    public RepetitiveFO() {

    }

    public RepetitiveFO(int id, int fo_id) {
        this.id = id;
        this.fo_id = fo_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFo_id() {
        return fo_id;
    }

    public void setFo_id(int fo_id) {
        this.fo_id = fo_id;
    }

}
