package com.alxbryann.foc.persistence;

import com.alxbryann.foc.model.RepetitiveIncome;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

public class RepetitiveIncomeJpaController implements Serializable {

    private EntityManagerFactory emf;

    public RepetitiveIncomeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public RepetitiveIncomeJpaController() {
        this.emf = Persistence.createEntityManagerFactory("focPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RepetitiveIncome ri) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(ri);
            transaction.commit();
        } finally {
            em.close();
        }
    }

    public void edit(RepetitiveIncome ri) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(ri);
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
            RepetitiveIncome ri = em.find(RepetitiveIncome.class, id);
            if (ri != null) {
                em.remove(ri);
            }
            transaction.commit();
        } finally {
            em.close();
        }
    }

    public RepetitiveIncome findRepetitiveIncome(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RepetitiveIncome.class, id);
        } finally {
            em.close();
        }
    }

    public List<RepetitiveIncome> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT o FROM RepetitiveIncome o", RepetitiveIncome.class).getResultList();
        } finally {
            em.close();
        }
    }
}
