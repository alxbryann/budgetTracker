package com.alxbryann.foc.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.alxbryann.foc.model.Day;

/**
 *
 * @author alxbryann
 */
public class DayJpaController implements Serializable {

    private EntityManagerFactory emf;

    public DayJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public DayJpaController() {
        this.emf = Persistence.createEntityManagerFactory("focPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Day day) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(day);
            transaction.commit();
        } finally {
            em.close();
        }
    }

    public void edit(Day day) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(day);
            transaction.commit();
        } finally {
            em.close();
        }
    }

    public void destroy(int id) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Day day = em.find(Day.class, id);
            if (day != null) {
                em.remove(day);
            }
            transaction.commit();
        } finally {
            em.close();
        }
    }

    public Day findDayById(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Day.class, id);
        } finally {
            em.close();
        }
    }

    public List<Day> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT o FROM Day o", Day.class).getResultList();
        } finally {
            em.close();
        }
    }
}
