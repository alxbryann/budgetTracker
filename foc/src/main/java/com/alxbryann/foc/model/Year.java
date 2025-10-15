package com.alxbryann.foc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author alxbryann
 */

@Entity
public class Year {

    @Id
    private int id;

    @OneToMany(mappedBy = "year")
    private List<Month> months;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Month> getMonths() {
        return months;
    }

    public void setMonths(List<Month> months) {
        this.months = months;
    }
}