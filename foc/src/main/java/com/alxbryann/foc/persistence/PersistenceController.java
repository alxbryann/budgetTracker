package com.alxbryann.foc.persistence;

import com.alxbryann.foc.model.Day;
import com.alxbryann.foc.model.FinancialObligation;
import com.alxbryann.foc.model.Income;
import com.alxbryann.foc.model.Month;
import com.alxbryann.foc.model.RepetitiveFO;
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

    private FinancialObligationJpaController foJpa = new FinancialObligationJpaController();
    private IncomeJpaController IncomeJpa = new IncomeJpaController();
    private RepetitiveFoJpaController RepetitiveFoJpa = new RepetitiveFoJpaController();
    private RepetitiveIncomeJpaController RepetitiveIncomeJpa = new RepetitiveIncomeJpaController();
    private DayJpaController dayJpa = new DayJpaController();
    private YearJpaController yearJpa = new YearJpaController();
    private MonthJpaController monthJpa = new MonthJpaController();

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

    public void editFo(FinancialObligation fo) {
        foJpa.edit(fo);
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

    public void editIncome(Income income) {
        IncomeJpa.edit(income);
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