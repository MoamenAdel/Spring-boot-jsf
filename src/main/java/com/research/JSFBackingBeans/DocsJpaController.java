/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.JSFBackingBeans;

import com.research.JSFBackingBeans.exceptions.NonexistentEntityException;
import com.research.entity.Docs;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.research.entity.Project;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Moamenovic
 */
public class DocsJpaController implements Serializable {

    public DocsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Docs docs) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Project projectId = docs.getProjectId();
            if (projectId != null) {
                projectId = em.getReference(projectId.getClass(), projectId.getId());
                docs.setProjectId(projectId);
            }
            em.persist(docs);
            if (projectId != null) {
                projectId.getDocsCollection().add(docs);
                projectId = em.merge(projectId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docs docs) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docs persistentDocs = em.find(Docs.class, docs.getId());
            Project projectIdOld = persistentDocs.getProjectId();
            Project projectIdNew = docs.getProjectId();
            if (projectIdNew != null) {
                projectIdNew = em.getReference(projectIdNew.getClass(), projectIdNew.getId());
                docs.setProjectId(projectIdNew);
            }
            docs = em.merge(docs);
            if (projectIdOld != null && !projectIdOld.equals(projectIdNew)) {
                projectIdOld.getDocsCollection().remove(docs);
                projectIdOld = em.merge(projectIdOld);
            }
            if (projectIdNew != null && !projectIdNew.equals(projectIdOld)) {
                projectIdNew.getDocsCollection().add(docs);
                projectIdNew = em.merge(projectIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = docs.getId();
                if (findDocs(id) == null) {
                    throw new NonexistentEntityException("The docs with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docs docs;
            try {
                docs = em.getReference(Docs.class, id);
                docs.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docs with id " + id + " no longer exists.", enfe);
            }
            Project projectId = docs.getProjectId();
            if (projectId != null) {
                projectId.getDocsCollection().remove(docs);
                projectId = em.merge(projectId);
            }
            em.remove(docs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Docs> findDocsEntities() {
        return findDocsEntities(true, -1, -1);
    }

    public List<Docs> findDocsEntities(int maxResults, int firstResult) {
        return findDocsEntities(false, maxResults, firstResult);
    }

    private List<Docs> findDocsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Docs.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Docs findDocs(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Docs.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Docs> rt = cq.from(Docs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
