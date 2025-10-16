package com.alxbryann.foc.persistence;

import com.alxbryann.foc.model.Day;
import com.alxbryann.foc.model.Transaction;
import com.alxbryann.foc.model.Month;
import com.alxbryann.foc.model.RepetitiveIncome;
import com.alxbryann.foc.model.Year;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author alxbryann
 */
public class PersistenceController {

    private TransactionJpaController TransactionJpa = new TransactionJpaController();
    private DayJpaController dayJpa = new DayJpaController();
    private YearJpaController yearJpa = new YearJpaController();
    private MonthJpaController monthJpa = new MonthJpaController();
    
    public void createTransaction(Transaction income) {
        TransactionJpa.create(income);
    }

    public void deleteTransaction(int id) {
        TransactionJpa.destroy(id);
    }

    public void editTransaction(Transaction income) {
        TransactionJpa.edit(income);
    }
    
    public Transaction findTransactionById(int id) {
        return TransactionJpa.findTransactionById(id);
    }

    public List findAllTransactions() {
        return TransactionJpa.findAll();
    }

    // Day methods
    public void createDay(Day day) {
        dayJpa.create(day);
    }

    public Day findDayById(int id) {
        return dayJpa.findDayById(id);
    }

    public List findAllDays() {
        return dayJpa.findAll();
    }

    public void editDay(Day day) {
        dayJpa.edit(day);
    }

    public void deleteDay(int id) {
        dayJpa.destroy(id);
    }

    // Year methods
    public void createYear(Year year) {
        yearJpa.create(year);
    }

    public Year findYearById(int id) {
        return yearJpa.findYear(id);
    }

    public List<Year> findAllYears() {
        return yearJpa.findAll();
    }

    public void editYear(Year year) throws Exception {
        yearJpa.edit(year);
    }

    public void deleteYear(int id) throws Exception {
        yearJpa.destroy(id);
    }

    // Month methods
    public void createMonth(Month month) {
        monthJpa.create(month);
    }

    public Month findMonthById(int id) {
        return monthJpa.findMonth(id);
    }

    public List<Month> findAllMonths() {
        return monthJpa.findAll();
    }

    public void editMonth(Month month) throws Exception {
        monthJpa.edit(month);
    }

    public void deleteMonth(int id) throws Exception {
        monthJpa.destroy(id);
    }

    public Month findMonthByYearAndNumber(int yearId, int monthNumber) {
        try {
            EntityManager em = monthJpa.getEntityManager();
            Query query = em
                    .createQuery("SELECT m FROM Month m WHERE m.year.id = :yearId AND m.monthNumber = :monthNumber");
            query.setParameter("yearId", yearId);
            query.setParameter("monthNumber", monthNumber);
            return (Month) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Day findDayByDate(int yearId, int monthNumber, int dayNumber) {
        try {
            EntityManager em = dayJpa.getEntityManager();
            Query query = em
                    .createQuery("SELECT d FROM Day d WHERE d.month.year.id = :yearId AND d.month.monthNumber = :monthNumber AND d.dayNumber = :dayNumber");
            query.setParameter("yearId", yearId);
            query.setParameter("monthNumber", monthNumber);
            query.setParameter("dayNumber", dayNumber);
            return (Day) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}