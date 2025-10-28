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

    public List<Transaction> findTransactionsByDay(int day) {
        EntityManager em = getEntityManager();
        day++;
        try {
            java.time.LocalDate now = java.time.LocalDate.now();
            if (day < 1 || day > now.lengthOfMonth()) {
                throw new IllegalArgumentException("Invalid day of month: " + day);
            }
            java.time.LocalDate startOfDay = now.withDayOfMonth(day);
            java.time.LocalDate startOfNextDay = startOfDay.plusDays(1);

            java.util.Date start = java.util.Date
                    .from(startOfDay.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
            java.util.Date end = java.util.Date
                    .from(startOfNextDay.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());

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

    public List<Transaction> findAllTransactionsForCurrentMonth() {
        EntityManager em = getEntityManager();
        try {
            java.time.LocalDate now = java.time.LocalDate.now();
            java.time.LocalDate startOfMonth = now.withDayOfMonth(1);
            java.time.LocalDate startOfNextMonth = startOfMonth.plusMonths(1);

            java.util.Date start = java.util.Date
                .from(startOfMonth.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
            java.util.Date end = java.util.Date
                .from(startOfNextMonth.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());

            return em.createQuery(
                "SELECT t FROM Transaction t WHERE t.date >= :start AND t.date < :end",
                Transaction.class)
                .setParameter("start", start, javax.persistence.TemporalType.TIMESTAMP)
                .setParameter("end", end, javax.persistence.TemporalType.TIMESTAMP)
                .getResultList();
        } finally {
            em.close();
        }
        }

        public double getTotalCostByDay(int day) {
        EntityManager em = getEntityManager();
        day++;
        double totalCost = 0.0;
        try {
            java.time.LocalDate now = java.time.LocalDate.now();
            if (day < 1 || day > now.lengthOfMonth()) {
            throw new IllegalArgumentException("Invalid day of month: " + day);
            }
            java.time.LocalDate startOfDay = now.withDayOfMonth(day);
            java.time.LocalDate startOfNextDay = startOfDay.plusDays(1);

            java.util.Date start = java.util.Date
                .from(startOfDay.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
            java.util.Date end = java.util.Date
                .from(startOfNextDay.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());

            List<Transaction> transactions = em.createQuery(
                "SELECT t FROM Transaction t WHERE t.date >= :start AND t.date < :end AND t.value < 0",
                Transaction.class)
                .setParameter("start", start, javax.persistence.TemporalType.TIMESTAMP)
                .setParameter("end", end, javax.persistence.TemporalType.TIMESTAMP)
                .getResultList();

            for (Transaction tran : transactions) {
            totalCost += Math.abs(tran.getValue());
            }
            return totalCost;
        } finally {
            em.close();
        }
        }

        public double getTotalIncomeByDay(int day) {
        EntityManager em = getEntityManager();
        day++;
        double totalIncome = 0.0;
        try {
            java.time.LocalDate now = java.time.LocalDate.now();
            if (day < 1 || day > now.lengthOfMonth()) {
            throw new IllegalArgumentException("Invalid day of month: " + day);
            }
            java.time.LocalDate startOfDay = now.withDayOfMonth(day);
            java.time.LocalDate startOfNextDay = startOfDay.plusDays(1);

            java.util.Date start = java.util.Date
                .from(startOfDay.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
            java.util.Date end = java.util.Date
                .from(startOfNextDay.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());

            List<Transaction> transactions = em.createQuery(
                "SELECT t FROM Transaction t WHERE t.date >= :start AND t.date < :end AND t.value > 0",
                Transaction.class)
                .setParameter("start", start, javax.persistence.TemporalType.TIMESTAMP)
                .setParameter("end", end, javax.persistence.TemporalType.TIMESTAMP)
                .getResultList();

            for (Transaction tran : transactions) {
            totalIncome += tran.getValue();
            }
            return totalIncome;
        } finally {
            em.close();
        }
        }

        public double getTotalNetByDay(int day) {
        double totalCost = getTotalCostByDay(day);
        double totalIncome = getTotalIncomeByDay(day);
        return totalIncome - totalCost;
        }

}
