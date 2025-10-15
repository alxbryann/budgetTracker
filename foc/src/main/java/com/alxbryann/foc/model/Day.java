package com.alxbryann.foc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author alxbryann
 */

 @Entity
 public class Day {
 
     @Id
     private int id;
 
     @ManyToOne
     @JoinColumn(name = "month_id") 
     private Month month;
 
     public void setId(int id) {
        this.id = id;
     }

     public int getId() {
         return id;
     }
 
     public Month getMonth() {
         return month;
     }
 
     public void setMonth(Month month) {
         this.month = month;
     }
 }
