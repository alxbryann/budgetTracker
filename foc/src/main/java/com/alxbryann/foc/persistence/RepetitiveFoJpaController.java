package com.alxbryann.foc.persistence;

import com.alxbryann.foc.model.FinancialObligation;
import com.alxbryann.foc.model.RepetitiveFO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

public class RepetitiveFoJpaController implements Serializable {

    private EntityManagerFactory emf;

    public RepetitiveFoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public RepetitiveFoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("focPU");
    }
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RepetitiveFO rf) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(rf);
            transaction.commit();
        } finally {
            em.close();
        }
    }

    public void edit(RepetitiveFO rf) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(rf);
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
            RepetitiveFO rf = em.find(RepetitiveFO.class, id);
            if (rf != null) {
                em.remove(rf);
            }
            transaction.commit();
        } finally {
            em.close();
        }
    }

    public RepetitiveFO findFinancialObligation(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RepetitiveFO.class, id);
        } finally {
            em.close();
        }
    }

    public List<RepetitiveFO> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT o FROM RepetitiveFO o", RepetitiveFO.class).getResultList();
        } finally {
            em.close();
        }
    }
}
