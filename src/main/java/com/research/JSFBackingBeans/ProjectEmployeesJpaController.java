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
import com.research.entity.Employee;
import com.research.entity.Project;
import com.research.entity.ProjectEmployees;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Moamenovic
 */
public class ProjectEmployeesJpaController implements Serializable {

    public ProjectEmployeesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProjectEmployees projectEmployees) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee employeeId = projectEmployees.getEmployeeId();
            if (employeeId != null) {
                employeeId = em.getReference(employeeId.getClass(), employeeId.getId());
                projectEmployees.setEmployeeId(employeeId);
            }
            Project projectId = projectEmployees.getProjectId();
            if (projectId != null) {
                projectId = em.getReference(projectId.getClass(), projectId.getId());
                projectEmployees.setProjectId(projectId);
            }
            em.persist(projectEmployees);
            if (employeeId != null) {
                employeeId.getProjectEmployeesCollection().add(projectEmployees);
                employeeId = em.merge(employeeId);
            }
            if (projectId != null) {
                projectId.getProjectEmployeesCollection().add(projectEmployees);
                projectId = em.merge(projectId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProjectEmployees projectEmployees) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProjectEmployees persistentProjectEmployees = em.find(ProjectEmployees.class, projectEmployees.getId());
            Employee employeeIdOld = persistentProjectEmployees.getEmployeeId();
            Employee employeeIdNew = projectEmployees.getEmployeeId();
            Project projectIdOld = persistentProjectEmployees.getProjectId();
            Project projectIdNew = projectEmployees.getProjectId();
            if (employeeIdNew != null) {
                employeeIdNew = em.getReference(employeeIdNew.getClass(), employeeIdNew.getId());
                projectEmployees.setEmployeeId(employeeIdNew);
            }
            if (projectIdNew != null) {
                projectIdNew = em.getReference(projectIdNew.getClass(), projectIdNew.getId());
                projectEmployees.setProjectId(projectIdNew);
            }
            projectEmployees = em.merge(projectEmployees);
            if (employeeIdOld != null && !employeeIdOld.equals(employeeIdNew)) {
                employeeIdOld.getProjectEmployeesCollection().remove(projectEmployees);
                employeeIdOld = em.merge(employeeIdOld);
            }
            if (employeeIdNew != null && !employeeIdNew.equals(employeeIdOld)) {
                employeeIdNew.getProjectEmployeesCollection().add(projectEmployees);
                employeeIdNew = em.merge(employeeIdNew);
            }
            if (projectIdOld != null && !projectIdOld.equals(projectIdNew)) {
                projectIdOld.getProjectEmployeesCollection().remove(projectEmployees);
                projectIdOld = em.merge(projectIdOld);
            }
            if (projectIdNew != null && !projectIdNew.equals(projectIdOld)) {
                projectIdNew.getProjectEmployeesCollection().add(projectEmployees);
                projectIdNew = em.merge(projectIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = projectEmployees.getId();
                if (findProjectEmployees(id) == null) {
                    throw new NonexistentEntityException("The projectEmployees with id " + id + " no longer exists.");
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
            ProjectEmployees projectEmployees;
            try {
                projectEmployees = em.getReference(ProjectEmployees.class, id);
                projectEmployees.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The projectEmployees with id " + id + " no longer exists.", enfe);
            }
            Employee employeeId = projectEmployees.getEmployeeId();
            if (employeeId != null) {
                employeeId.getProjectEmployeesCollection().remove(projectEmployees);
                employeeId = em.merge(employeeId);
            }
            Project projectId = projectEmployees.getProjectId();
            if (projectId != null) {
                projectId.getProjectEmployeesCollection().remove(projectEmployees);
                projectId = em.merge(projectId);
            }
            em.remove(projectEmployees);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProjectEmployees> findProjectEmployeesEntities() {
        return findProjectEmployeesEntities(true, -1, -1);
    }

    public List<ProjectEmployees> findProjectEmployeesEntities(int maxResults, int firstResult) {
        return findProjectEmployeesEntities(false, maxResults, firstResult);
    }

    private List<ProjectEmployees> findProjectEmployeesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProjectEmployees.class));
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

    public ProjectEmployees findProjectEmployees(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProjectEmployees.class, id);
        } finally {
            em.close();
        }
    }

    public int getProjectEmployeesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProjectEmployees> rt = cq.from(ProjectEmployees.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
