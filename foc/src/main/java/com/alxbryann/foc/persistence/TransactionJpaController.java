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

    public void create(Transaction tran) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(tran);
            transaction.commit();
        } finally {
            em.close();
        }
    }

    public void edit(Transaction tran) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(tran);
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
            Transaction tran = em.find(Transaction.class, id);
            if (tran != null) {
                em.remove(tran);
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

    public List<Transaction> findAllTransactionsForCurrentMonth() {
        EntityManager em = getEntityManager();
        try {
            // Use a portable date range query instead of YEAR/MONTH functions to avoid provider/dialect issues
            java.time.LocalDate now = java.time.LocalDate.now();
            java.time.LocalDate startOfMonth = now.withDayOfMonth(1);
            java.time.LocalDate startOfNextMonth = startOfMonth.plusMonths(1);

            java.util.Date start = java.util.Date.from(startOfMonth.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
            java.util.Date end = java.util.Date.from(startOfNextMonth.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());

            return em.createQuery(
                    "SELECT o FROM Transaction o WHERE o.date >= :start AND o.date < :end",
                    Transaction.class)
                .setParameter("start", start, javax.persistence.TemporalType.DATE)
                .setParameter("end", end, javax.persistence.TemporalType.DATE)
                .getResultList();
        } finally {
            em.close();
        }
    }
}
