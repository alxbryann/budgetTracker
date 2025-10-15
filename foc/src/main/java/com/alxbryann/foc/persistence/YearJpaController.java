package com.alxbryann.foc.persistence;

import com.alxbryann.foc.model.Year;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class YearJpaController {
    
    private EntityManagerFactory emf = null;
    
    public YearJpaController() {
        emf = Persistence.createEntityManagerFactory("focPU");
    }
    
    public YearJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(Year year) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(year);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(Year year) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            year = em.merge(year);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = year.getId();
                if (findYear(id) == null) {
                    throw new Exception("The year with id " + id + " no longer exists.");
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
            Year year;
            try {
                year = em.getReference(Year.class, id);
                year.getId();
            } catch (Exception enfe) {
                throw new Exception("The year with id " + id + " no longer exists.", enfe);
            }
            em.remove(year);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Year> findAll() {
        return findYearEntities(true, -1, -1);
    }
    
    public List<Year> findYearEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Year as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Year findYear(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Year.class, id);
        } finally {
            em.close();
        }
    }
    
    public int getYearCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Year as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}