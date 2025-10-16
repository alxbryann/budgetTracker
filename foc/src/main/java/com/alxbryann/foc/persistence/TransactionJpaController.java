package com.alxbryann.foc.persistence;

import com.alxbryann.foc.model.Transaction;
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
public class TransactionJpaController implements Serializable {

    private EntityManagerFactory emf;

    public TransactionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public TransactionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("focPU");
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Transaction income) {
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

    public void edit(Transaction income) {
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
            Transaction income = em.find(Transaction.class, id);
            if (income != null) {
                em.remove(income);
            }
            transaction.commit();
        } finally {
            em.close();
        }
    }

    public Transaction findTransactionById(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Transaction.class, id);
        } finally {
            em.close();
        }
    }

    public List<Transaction> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT o FROM Transaction o", Transaction.class).getResultList();
        } finally {
            em.close();
        }
    }
}
