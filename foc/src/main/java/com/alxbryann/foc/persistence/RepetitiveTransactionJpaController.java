package com.alxbryann.foc.persistence;

import com.alxbryann.foc.model.RepetitiveTransaction;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

public class RepetitiveTransactionJpaController implements Serializable {

    private EntityManagerFactory emf;

    public RepetitiveTransactionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public RepetitiveTransactionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("focPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RepetitiveTransaction ri) {
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

    public void edit(RepetitiveTransaction ri) {
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
            RepetitiveTransaction ri = em.find(RepetitiveTransaction.class, id);
            if (ri != null) {
                em.remove(ri);
            }
            transaction.commit();
        } finally {
            em.close();
        }
    }

    public RepetitiveTransaction findRepetitiveIncome(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RepetitiveTransaction.class, id);
        } finally {
            em.close();
        }
    }

    public List<RepetitiveTransaction> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT o FROM RepetitiveTransaction o", RepetitiveTransaction.class).getResultList();
        } finally {
            em.close();
        }
    }
}
