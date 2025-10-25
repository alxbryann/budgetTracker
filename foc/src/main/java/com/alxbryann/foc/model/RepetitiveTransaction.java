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
public class RepetitiveTransaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Basic
    private int repetitiveTransaction_id;

    public RepetitiveTransaction() {

    }

    public RepetitiveTransaction(int id, int repetitiveTransaction_id) {
        this.id = id;
        this.repetitiveTransaction_id = repetitiveTransaction_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRepetitiveTransaction_id() {
        return repetitiveTransaction_id;
    }

    public void setRepetitiveTransaction_id(int repetitiveTransaction_id) {
        this.repetitiveTransaction_id = repetitiveTransaction_id;
    }

}
