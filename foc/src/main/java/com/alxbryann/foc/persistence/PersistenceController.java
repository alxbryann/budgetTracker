package com.alxbryann.foc.persistence;

import com.alxbryann.foc.model.FinancialObligation;
import com.alxbryann.foc.model.Income;
import com.alxbryann.foc.model.RepetitiveFO;
import com.alxbryann.foc.model.RepetitiveIncome;
import java.util.List;

/**
 *
 * @author barr2
 */
public class PersistenceController {
    
    private FinancialObligationJpaController foJpa = new FinancialObligationJpaController();
    private IncomeJpaController IncomeJpa = new IncomeJpaController();
    private RepetitiveFoJpaController RepetitiveFoJpa = new RepetitiveFoJpaController();
    private RepetitiveIncomeJpaController RepetitiveIncomeJpa = new RepetitiveIncomeJpaController();
    
    public void createFo(FinancialObligation fo) {
        foJpa.create(fo);
    }
    
    public FinancialObligation findFoById(int id) {
        return foJpa.findFinancialObligation(id);
    }
    
    public List findAllFo() {
        return foJpa.findAll();
    }
    
    public void createRepetitiveFo(RepetitiveFO rf) {
        RepetitiveFoJpa.create(rf);
    }
    
    public void deleteFo(int id) {
        foJpa.destroy(id);
    }
    
    public void deleteRepetitiveFo(int id) {
        RepetitiveFoJpa.destroy(id);
    }
    
    public List findAllRepetitiveFO() {
        return RepetitiveFoJpa.findAll();
    }    
    
    public void createIncome(Income income) {
        IncomeJpa.create(income);
    }
    
    public void deleteIncome(int id) {
        IncomeJpa.destroy(id);
    }
    
    public void deleteRepetitiveIncome(int id) {
        RepetitiveIncomeJpa.destroy(id);
    }
    
    public Income findIncomeById(int id) {
        return IncomeJpa.findIncomeById(id);
    }
    
    public List findAllIncomes() {
        return IncomeJpa.findAll();
    }
    
    public void createRepetitiveIncome(RepetitiveIncome ri) {
        RepetitiveIncomeJpa.create(ri);
    }
    
    public List findAllRepetitiveIncomes() {
        return RepetitiveIncomeJpa.findAll();
    }
}
