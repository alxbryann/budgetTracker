package com.alxbryann.foc.persistence;

import com.alxbryann.foc.model.Transaction;
import com.alxbryann.foc.model.RepetitiveTransaction;
import java.util.List;

/**
 *
 * @author alxbryann
 */
public class PersistenceController {
    
    private TransactionJpaController transactionJpa = new TransactionJpaController();
    private RepetitiveTransactionJpaController repetitiveTransactionJpa = new RepetitiveTransactionJpaController();

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
    
    public void deleteRepetitiveTransaction(int id) {
        repetitiveTransactionJpa.destroy(id);
    }
    
    public Transaction findTransactionById(int id) {
        return transactionJpa.findTransactionById(id);
    }
    
    public List<Transaction> findAllTransactions() {
        return transactionJpa.findAll();
    }

    public List<Transaction> findFutureTransactions() {
        return transactionJpa.findFutureTransactions();
    }
    
    public void createRepetitiveTransaction(RepetitiveTransaction ri) {
        repetitiveTransactionJpa.create(ri);
    }

    public List<RepetitiveTransaction> findAllRepetitiveTransaction() {
        return repetitiveTransactionJpa.findAll();
    }

    public List<Transaction> findAllTransactionsForCurrentMonth() {
        return transactionJpa.findAllTransactionsForCurrentMonth();
    }

    public List<Transaction> findAllRpetitiveTransactionsForCurrentMonth() {
        return transactionJpa.findAllRpetitiveTransactionsForCurrentMonth();
    }

    public List<Transaction> findTransactionsByDay(int day) {
        return transactionJpa.findTransactionsByDay(day);
    }

    public double getTotalNetByDay(int day) {
        return transactionJpa.getTotalNetByDay(day);
    }

    public double getTotalIncomeByDay(int day) {
        return transactionJpa.getTotalIncomeByDay(day);
    }

    public double getTotalCostByDay(int day) {
        return transactionJpa.getTotalCostByDay(day);
    }

    public void copyRepetitiveTransactionsForThisMonth() {
        transactionJpa.copyRepetitiveTransactionsForThisMonth();
    }
}
