package com.alxbryann.foc.persistence;

import com.alxbryann.foc.model.Day;
import com.alxbryann.foc.model.Transaction;
import com.alxbryann.foc.model.RepetitiveIncome;
import java.util.List;

/**
 *
 * @author alxbryann
 */
public class PersistenceController {
    
    private TransactionJpaController transactionJpa = new TransactionJpaController();
    private RepetitiveIncomeJpaController repetitiveIncomeJpa = new RepetitiveIncomeJpaController();
    private DayJpaController dayJpa = new DayJpaController();

    // Removed FinancialObligation and RepetitiveFO persistence methods
    
    public void createTransaction(Transaction transaction) {
        transactionJpa.create(transaction);
    }
    
    public void deleteTransaction(int id) {
        transactionJpa.destroy(id);
    }
    
    public void editTransaction(Transaction transaction) {
        transactionJpa.edit(transaction);
    }
    
    public void deleteRepetitiveIncome(int id) {
        repetitiveIncomeJpa.destroy(id);
    }
    
    public Transaction findTransactionById(int id) {
        return transactionJpa.findTransactionById(id);
    }
    
    public List findAllTransactions() {
        return transactionJpa.findAll();
    }
    
    public void createRepetitiveIncome(RepetitiveIncome ri) {
        repetitiveIncomeJpa.create(ri);
    }
    
    public List findAllRepetitiveIncomes() {
        return repetitiveIncomeJpa.findAll();
    }

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
}
