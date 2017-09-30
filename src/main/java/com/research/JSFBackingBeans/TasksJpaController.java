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
import com.research.entity.Lfm;
import com.research.entity.Tasks;
import com.research.entity.TasksExpectedOutcomes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Moamenovic
 */
public class TasksJpaController implements Serializable {

    public TasksJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tasks tasks) {
        if (tasks.getTasksExpectedOutcomesCollection() == null) {
            tasks.setTasksExpectedOutcomesCollection(new ArrayList<TasksExpectedOutcomes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lfm lfmId = tasks.getLfmId();
            if (lfmId != null) {
                lfmId = em.getReference(lfmId.getClass(), lfmId.getId());
                tasks.setLfmId(lfmId);
            }
            Collection<TasksExpectedOutcomes> attachedTasksExpectedOutcomesCollection = new ArrayList<TasksExpectedOutcomes>();
            for (TasksExpectedOutcomes tasksExpectedOutcomesCollectionTasksExpectedOutcomesToAttach : tasks.getTasksExpectedOutcomesCollection()) {
                tasksExpectedOutcomesCollectionTasksExpectedOutcomesToAttach = em.getReference(tasksExpectedOutcomesCollectionTasksExpectedOutcomesToAttach.getClass(), tasksExpectedOutcomesCollectionTasksExpectedOutcomesToAttach.getId());
                attachedTasksExpectedOutcomesCollection.add(tasksExpectedOutcomesCollectionTasksExpectedOutcomesToAttach);
            }
            tasks.setTasksExpectedOutcomesCollection(attachedTasksExpectedOutcomesCollection);
            em.persist(tasks);
            if (lfmId != null) {
                lfmId.getTasksCollection().add(tasks);
                lfmId = em.merge(lfmId);
            }
            for (TasksExpectedOutcomes tasksExpectedOutcomesCollectionTasksExpectedOutcomes : tasks.getTasksExpectedOutcomesCollection()) {
                Tasks oldTaskIdOfTasksExpectedOutcomesCollectionTasksExpectedOutcomes = tasksExpectedOutcomesCollectionTasksExpectedOutcomes.getTaskId();
                tasksExpectedOutcomesCollectionTasksExpectedOutcomes.setTaskId(tasks);
                tasksExpectedOutcomesCollectionTasksExpectedOutcomes = em.merge(tasksExpectedOutcomesCollectionTasksExpectedOutcomes);
                if (oldTaskIdOfTasksExpectedOutcomesCollectionTasksExpectedOutcomes != null) {
                    oldTaskIdOfTasksExpectedOutcomesCollectionTasksExpectedOutcomes.getTasksExpectedOutcomesCollection().remove(tasksExpectedOutcomesCollectionTasksExpectedOutcomes);
                    oldTaskIdOfTasksExpectedOutcomesCollectionTasksExpectedOutcomes = em.merge(oldTaskIdOfTasksExpectedOutcomesCollectionTasksExpectedOutcomes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tasks tasks) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tasks persistentTasks = em.find(Tasks.class, tasks.getId());
            Lfm lfmIdOld = persistentTasks.getLfmId();
            Lfm lfmIdNew = tasks.getLfmId();
            Collection<TasksExpectedOutcomes> tasksExpectedOutcomesCollectionOld = persistentTasks.getTasksExpectedOutcomesCollection();
            Collection<TasksExpectedOutcomes> tasksExpectedOutcomesCollectionNew = tasks.getTasksExpectedOutcomesCollection();
            if (lfmIdNew != null) {
                lfmIdNew = em.getReference(lfmIdNew.getClass(), lfmIdNew.getId());
                tasks.setLfmId(lfmIdNew);
            }
            Collection<TasksExpectedOutcomes> attachedTasksExpectedOutcomesCollectionNew = new ArrayList<TasksExpectedOutcomes>();
            for (TasksExpectedOutcomes tasksExpectedOutcomesCollectionNewTasksExpectedOutcomesToAttach : tasksExpectedOutcomesCollectionNew) {
                tasksExpectedOutcomesCollectionNewTasksExpectedOutcomesToAttach = em.getReference(tasksExpectedOutcomesCollectionNewTasksExpectedOutcomesToAttach.getClass(), tasksExpectedOutcomesCollectionNewTasksExpectedOutcomesToAttach.getId());
                attachedTasksExpectedOutcomesCollectionNew.add(tasksExpectedOutcomesCollectionNewTasksExpectedOutcomesToAttach);
            }
            tasksExpectedOutcomesCollectionNew = attachedTasksExpectedOutcomesCollectionNew;
            tasks.setTasksExpectedOutcomesCollection(tasksExpectedOutcomesCollectionNew);
            tasks = em.merge(tasks);
            if (lfmIdOld != null && !lfmIdOld.equals(lfmIdNew)) {
                lfmIdOld.getTasksCollection().remove(tasks);
                lfmIdOld = em.merge(lfmIdOld);
            }
            if (lfmIdNew != null && !lfmIdNew.equals(lfmIdOld)) {
                lfmIdNew.getTasksCollection().add(tasks);
                lfmIdNew = em.merge(lfmIdNew);
            }
            for (TasksExpectedOutcomes tasksExpectedOutcomesCollectionOldTasksExpectedOutcomes : tasksExpectedOutcomesCollectionOld) {
                if (!tasksExpectedOutcomesCollectionNew.contains(tasksExpectedOutcomesCollectionOldTasksExpectedOutcomes)) {
                    tasksExpectedOutcomesCollectionOldTasksExpectedOutcomes.setTaskId(null);
                    tasksExpectedOutcomesCollectionOldTasksExpectedOutcomes = em.merge(tasksExpectedOutcomesCollectionOldTasksExpectedOutcomes);
                }
            }
            for (TasksExpectedOutcomes tasksExpectedOutcomesCollectionNewTasksExpectedOutcomes : tasksExpectedOutcomesCollectionNew) {
                if (!tasksExpectedOutcomesCollectionOld.contains(tasksExpectedOutcomesCollectionNewTasksExpectedOutcomes)) {
                    Tasks oldTaskIdOfTasksExpectedOutcomesCollectionNewTasksExpectedOutcomes = tasksExpectedOutcomesCollectionNewTasksExpectedOutcomes.getTaskId();
                    tasksExpectedOutcomesCollectionNewTasksExpectedOutcomes.setTaskId(tasks);
                    tasksExpectedOutcomesCollectionNewTasksExpectedOutcomes = em.merge(tasksExpectedOutcomesCollectionNewTasksExpectedOutcomes);
                    if (oldTaskIdOfTasksExpectedOutcomesCollectionNewTasksExpectedOutcomes != null && !oldTaskIdOfTasksExpectedOutcomesCollectionNewTasksExpectedOutcomes.equals(tasks)) {
                        oldTaskIdOfTasksExpectedOutcomesCollectionNewTasksExpectedOutcomes.getTasksExpectedOutcomesCollection().remove(tasksExpectedOutcomesCollectionNewTasksExpectedOutcomes);
                        oldTaskIdOfTasksExpectedOutcomesCollectionNewTasksExpectedOutcomes = em.merge(oldTaskIdOfTasksExpectedOutcomesCollectionNewTasksExpectedOutcomes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tasks.getId();
                if (findTasks(id) == null) {
                    throw new NonexistentEntityException("The tasks with id " + id + " no longer exists.");
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
            Tasks tasks;
            try {
                tasks = em.getReference(Tasks.class, id);
                tasks.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tasks with id " + id + " no longer exists.", enfe);
            }
            Lfm lfmId = tasks.getLfmId();
            if (lfmId != null) {
                lfmId.getTasksCollection().remove(tasks);
                lfmId = em.merge(lfmId);
            }
            Collection<TasksExpectedOutcomes> tasksExpectedOutcomesCollection = tasks.getTasksExpectedOutcomesCollection();
            for (TasksExpectedOutcomes tasksExpectedOutcomesCollectionTasksExpectedOutcomes : tasksExpectedOutcomesCollection) {
                tasksExpectedOutcomesCollectionTasksExpectedOutcomes.setTaskId(null);
                tasksExpectedOutcomesCollectionTasksExpectedOutcomes = em.merge(tasksExpectedOutcomesCollectionTasksExpectedOutcomes);
            }
            em.remove(tasks);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tasks> findTasksEntities() {
        return findTasksEntities(true, -1, -1);
    }

    public List<Tasks> findTasksEntities(int maxResults, int firstResult) {
        return findTasksEntities(false, maxResults, firstResult);
    }

    private List<Tasks> findTasksEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tasks.class));
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

    public Tasks findTasks(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tasks.class, id);
        } finally {
            em.close();
        }
    }

    public int getTasksCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tasks> rt = cq.from(Tasks.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
