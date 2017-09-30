/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.JSFBackingBeans;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.research.JSFBackingBeans.exceptions.NonexistentEntityException;
import com.research.entity.Tasks;
import com.research.entity.TasksExpectedOutcomes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Moamenovic
 */
public class TasksExpectedOutcomesJpaController implements Serializable {

    public TasksExpectedOutcomesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TasksExpectedOutcomes tasksExpectedOutcomes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tasks taskId = tasksExpectedOutcomes.getTaskId();
            if (taskId != null) {
                taskId = em.getReference(taskId.getClass(), taskId.getId());
                tasksExpectedOutcomes.setTaskId(taskId);
            }
            em.persist(tasksExpectedOutcomes);
            if (taskId != null) {
                taskId.getTasksExpectedOutcomesCollection().add(tasksExpectedOutcomes);
                taskId = em.merge(taskId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TasksExpectedOutcomes tasksExpectedOutcomes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TasksExpectedOutcomes persistentTasksExpectedOutcomes = em.find(TasksExpectedOutcomes.class, tasksExpectedOutcomes.getId());
            Tasks taskIdOld = persistentTasksExpectedOutcomes.getTaskId();
            Tasks taskIdNew = tasksExpectedOutcomes.getTaskId();
            if (taskIdNew != null) {
                taskIdNew = em.getReference(taskIdNew.getClass(), taskIdNew.getId());
                tasksExpectedOutcomes.setTaskId(taskIdNew);
            }
            tasksExpectedOutcomes = em.merge(tasksExpectedOutcomes);
            if (taskIdOld != null && !taskIdOld.equals(taskIdNew)) {
                taskIdOld.getTasksExpectedOutcomesCollection().remove(tasksExpectedOutcomes);
                taskIdOld = em.merge(taskIdOld);
            }
            if (taskIdNew != null && !taskIdNew.equals(taskIdOld)) {
                taskIdNew.getTasksExpectedOutcomesCollection().add(tasksExpectedOutcomes);
                taskIdNew = em.merge(taskIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tasksExpectedOutcomes.getId();
                if (findTasksExpectedOutcomes(id) == null) {
                    throw new NonexistentEntityException("The tasksExpectedOutcomes with id " + id + " no longer exists.");
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
            TasksExpectedOutcomes tasksExpectedOutcomes;
            try {
                tasksExpectedOutcomes = em.getReference(TasksExpectedOutcomes.class, id);
                tasksExpectedOutcomes.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tasksExpectedOutcomes with id " + id + " no longer exists.", enfe);
            }
            Tasks taskId = tasksExpectedOutcomes.getTaskId();
            if (taskId != null) {
                taskId.getTasksExpectedOutcomesCollection().remove(tasksExpectedOutcomes);
                taskId = em.merge(taskId);
            }
            em.remove(tasksExpectedOutcomes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TasksExpectedOutcomes> findTasksExpectedOutcomesEntities() {
        return findTasksExpectedOutcomesEntities(true, -1, -1);
    }

    public List<TasksExpectedOutcomes> findTasksExpectedOutcomesEntities(int maxResults, int firstResult) {
        return findTasksExpectedOutcomesEntities(false, maxResults, firstResult);
    }

    private List<TasksExpectedOutcomes> findTasksExpectedOutcomesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TasksExpectedOutcomes.class));
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

    public TasksExpectedOutcomes findTasksExpectedOutcomes(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TasksExpectedOutcomes.class, id);
        } finally {
            em.close();
        }
    }

    public int getTasksExpectedOutcomesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TasksExpectedOutcomes> rt = cq.from(TasksExpectedOutcomes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
