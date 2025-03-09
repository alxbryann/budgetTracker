package com.alxbryann.foc.persistence;

import com.alxbryann.foc.model.FinancialObligation;
import com.alxbryann.foc.model.Income;
import com.alxbryann.foc.model.RepetitiveFO;
import java.util.List;

/**
 *
 * @author barr2
 */
public class PersistenceController {

    private FinancialObligationJpaController foJpa = new FinancialObligationJpaController();
    private IncomeJpaController IncomeJpa = new IncomeJpaController();
    private RepetitiveFoJpaController RepetitiveFoJpa = new RepetitiveFoJpaController();

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
    
    public List findAllRepetitiveFO(){
        return RepetitiveFoJpa.findAll();
    }

    public void createIncome(Income income) {
        IncomeJpa.create(income);
    }

    public List finAllIncome() {
        return IncomeJpa.findAll();
    }

}
