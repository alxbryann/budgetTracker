package com.alxbryann.foc.persistence;

import com.alxbryann.foc.model.Income;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author alxbryann
 */
public class IncomeJpaController implements Serializable {

    private EntityManagerFactory emf;

    public IncomeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public IncomeJpaController() {
        this.emf = Persistence.createEntityManagerFactory("focPU");
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Income income) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(income);
            transaction.commit();
        } finally {
            em.close();
        }
    }

    public void edit(Income income) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(income);
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
            Income income = em.find(Income.class, id);
            if (income != null) {
                em.remove(income);
            }
            transaction.commit();
        } finally {
            em.close();
        }
    }

    public Income findIncomeById(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Income.class, id);
        } finally {
            em.close();
        }
    }

    public List<Income> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT o FROM Income o", Income.class).getResultList();
        } finally {
            em.close();
        }
    }
}
