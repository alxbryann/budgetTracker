package com.alxbryann.foc.persistence;

import com.alxbryann.foc.model.Month;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MonthJpaController {
    
    private EntityManagerFactory emf = null;
    
    public MonthJpaController() {
        emf = Persistence.createEntityManagerFactory("focPU");
    }
    
    public MonthJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(Month month) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(month);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(Month month) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            month = em.merge(month);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = month.getId();
                if (findMonth(id) == null) {
                    throw new Exception("The month with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void destroy(int id) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Month month;
            try {
                month = em.getReference(Month.class, id);
                month.getId();
            } catch (Exception enfe) {
                throw new Exception("The month with id " + id + " no longer exists.", enfe);
            }
            em.remove(month);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Month> findAll() {
        return findMonthEntities(true, -1, -1);
    }
    
    public List<Month> findMonthEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Month as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Month findMonth(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Month.class, id);
        } finally {
            em.close();
        }
    }
    
    public int getMonthCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Month as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}