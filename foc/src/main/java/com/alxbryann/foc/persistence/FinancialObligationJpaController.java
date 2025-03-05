package com.alxbryann.foc.persistence;

import com.alxbryann.foc.model.FinancialObligation;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

public class FinancialObligationJpaController implements Serializable {

    private EntityManagerFactory emf;

    public FinancialObligationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public FinancialObligationJpaController() {
        this.emf = Persistence.createEntityManagerFactory("focPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FinancialObligation obligation) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(obligation);
            transaction.commit();
        } finally {
            em.close();
        }
    }

    public void edit(FinancialObligation obligation) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(obligation);
            transaction.commit();
        } finally {
            em.close();
        }
    }

    public void destroy(Long id) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            FinancialObligation obligation = em.find(FinancialObligation.class, id);
            if (obligation != null) {
                em.remove(obligation);
            }
            transaction.commit();
        } finally {
            em.close();
        }
    }

    public FinancialObligation findFinancialObligation(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FinancialObligation.class, id);
        } finally {
            em.close();
        }
    }

    public List<FinancialObligation> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT o FROM FinancialObligation o", FinancialObligation.class).getResultList();
        } finally {
            em.close();
        }
    }
}
