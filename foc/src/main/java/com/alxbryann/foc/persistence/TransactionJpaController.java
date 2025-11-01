package com.alxbryann.foc.persistence;

import com.alxbryann.foc.model.RepetitiveTransaction;
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

    public void copyRepetitiveTransactionsForThisMonth() {
        EntityManager em = getEntityManager();
        RepetitiveTransactionJpaController repController = new RepetitiveTransactionJpaController(emf);
        
        try {
            // Obtener todas las transacciones repetitivas
            List<RepetitiveTransaction> repetitiveTransactions = repController.findAll();
            
            java.time.LocalDate now = java.time.LocalDate.now();
            java.time.LocalDate startOfMonth = now.withDayOfMonth(1);
            java.time.LocalDate endOfMonth = now.withDayOfMonth(now.lengthOfMonth());
            
            for (RepetitiveTransaction repTran : repetitiveTransactions) {
                // Obtener la transacción original
                Transaction originalTransaction = findTransactionById(repTran.getRepetitiveTransaction_id());
                
                if (originalTransaction == null) {
                    continue;
                }
                
                // Si es repetitiva por mes, crear una copia para este mes
                if (originalTransaction.isRepetitiveByMonth()) {
                    // Obtener la fecha de la transacción original
                    java.time.LocalDate originalDate = originalTransaction.getDate()
                            .toInstant()
                            .atZone(java.time.ZoneId.systemDefault())
                            .toLocalDate();
                    
                    // Primero corregir el día (sumar 1 para compensar el desfase), luego sumar un mes
                    java.time.LocalDate correctedDate = originalDate.plusDays(1);
                    java.time.LocalDate newDate = correctedDate.plusMonths(1);
                    
                    // Verificar si ya existe una transacción para este día con el mismo nombre
                    if (!transactionExistsForDate(originalTransaction.getName(), newDate)) {
                        createCopyOfTransaction(originalTransaction, newDate);
                    }
                }
                
                // Si es repetitiva por semana, crear copias para cada semana del mes
                if (originalTransaction.isRepetitiveByWeek()) {
                    java.time.LocalDate originalDate = originalTransaction.getDate()
                            .toInstant()
                            .atZone(java.time.ZoneId.systemDefault())
                            .toLocalDate();
                    
                    java.time.DayOfWeek dayOfWeek = originalDate.getDayOfWeek();
                    
                    // Encontrar el primer día de la semana en el mes actual
                    java.time.LocalDate currentDate = startOfMonth;
                    while (currentDate.getDayOfWeek() != dayOfWeek && currentDate.isBefore(endOfMonth.plusDays(1))) {
                        currentDate = currentDate.plusDays(1);
                    }
                    
                    // Crear una copia para cada semana del mes
                    while (currentDate.isBefore(endOfMonth.plusDays(1))) {
                        if (!transactionExistsForDate(originalTransaction.getName(), currentDate)) {
                            createCopyOfTransaction(originalTransaction, currentDate);
                        }
                        currentDate = currentDate.plusWeeks(1);
                    }
                }
            }
        } finally {
            em.close();
        }
    }
    
    private boolean transactionExistsForDate(String name, java.time.LocalDate date) {
        EntityManager em = getEntityManager();
        try {
            java.time.LocalDate startOfDay = date;
            java.time.LocalDate startOfNextDay = startOfDay.plusDays(1);

            java.util.Date start = java.util.Date
                    .from(startOfDay.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
            java.util.Date end = java.util.Date
                    .from(startOfNextDay.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());

            List<Transaction> transactions = em.createQuery(
                    "SELECT t FROM Transaction t WHERE t.name = :name AND t.date >= :start AND t.date < :end",
                    Transaction.class)
                    .setParameter("name", name)
                    .setParameter("start", start, javax.persistence.TemporalType.DATE)
                    .setParameter("end", end, javax.persistence.TemporalType.DATE)
                    .getResultList();

            return !transactions.isEmpty();
        } finally {
            em.close();
        }
    }
    
    private void createCopyOfTransaction(Transaction original, java.time.LocalDate newDate) {
        Transaction copy = new Transaction();
        copy.setName(original.getName());
        copy.setValue(original.getValue());
        
        // Usar mediodía para evitar problemas de zona horaria
        copy.setDate(java.util.Date.from(newDate.atTime(12, 0).atZone(java.time.ZoneId.systemDefault()).toInstant()));
        
        copy.setRgb(original.getRgb());
        copy.setIsRepetitive(false); // La copia no es repetitiva
        copy.setRepetitiveByWeek(false);
        copy.setRepetitiveByMonth(false);
        
        create(copy);
    }

}
