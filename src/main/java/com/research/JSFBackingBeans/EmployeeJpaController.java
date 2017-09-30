/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.JSFBackingBeans;

import com.research.JSFBackingBeans.exceptions.NonexistentEntityException;
import com.research.entity.Employee;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.research.entity.ProjectEmployees;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Moamenovic
 */
public class EmployeeJpaController implements Serializable {

    public EmployeeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Employee employee) {
        if (employee.getProjectEmployeesCollection() == null) {
            employee.setProjectEmployeesCollection(new ArrayList<ProjectEmployees>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ProjectEmployees> attachedProjectEmployeesCollection = new ArrayList<ProjectEmployees>();
            for (ProjectEmployees projectEmployeesCollectionProjectEmployeesToAttach : employee.getProjectEmployeesCollection()) {
                projectEmployeesCollectionProjectEmployeesToAttach = em.getReference(projectEmployeesCollectionProjectEmployeesToAttach.getClass(), projectEmployeesCollectionProjectEmployeesToAttach.getId());
                attachedProjectEmployeesCollection.add(projectEmployeesCollectionProjectEmployeesToAttach);
            }
            employee.setProjectEmployeesCollection(attachedProjectEmployeesCollection);
            em.persist(employee);
            for (ProjectEmployees projectEmployeesCollectionProjectEmployees : employee.getProjectEmployeesCollection()) {
                Employee oldEmployeeIdOfProjectEmployeesCollectionProjectEmployees = projectEmployeesCollectionProjectEmployees.getEmployeeId();
                projectEmployeesCollectionProjectEmployees.setEmployeeId(employee);
                projectEmployeesCollectionProjectEmployees = em.merge(projectEmployeesCollectionProjectEmployees);
                if (oldEmployeeIdOfProjectEmployeesCollectionProjectEmployees != null) {
                    oldEmployeeIdOfProjectEmployeesCollectionProjectEmployees.getProjectEmployeesCollection().remove(projectEmployeesCollectionProjectEmployees);
                    oldEmployeeIdOfProjectEmployeesCollectionProjectEmployees = em.merge(oldEmployeeIdOfProjectEmployeesCollectionProjectEmployees);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Employee employee) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee persistentEmployee = em.find(Employee.class, employee.getId());
            Collection<ProjectEmployees> projectEmployeesCollectionOld = persistentEmployee.getProjectEmployeesCollection();
            Collection<ProjectEmployees> projectEmployeesCollectionNew = employee.getProjectEmployeesCollection();
            Collection<ProjectEmployees> attachedProjectEmployeesCollectionNew = new ArrayList<ProjectEmployees>();
            for (ProjectEmployees projectEmployeesCollectionNewProjectEmployeesToAttach : projectEmployeesCollectionNew) {
                projectEmployeesCollectionNewProjectEmployeesToAttach = em.getReference(projectEmployeesCollectionNewProjectEmployeesToAttach.getClass(), projectEmployeesCollectionNewProjectEmployeesToAttach.getId());
                attachedProjectEmployeesCollectionNew.add(projectEmployeesCollectionNewProjectEmployeesToAttach);
            }
            projectEmployeesCollectionNew = attachedProjectEmployeesCollectionNew;
            employee.setProjectEmployeesCollection(projectEmployeesCollectionNew);
            employee = em.merge(employee);
            for (ProjectEmployees projectEmployeesCollectionOldProjectEmployees : projectEmployeesCollectionOld) {
                if (!projectEmployeesCollectionNew.contains(projectEmployeesCollectionOldProjectEmployees)) {
                    projectEmployeesCollectionOldProjectEmployees.setEmployeeId(null);
                    projectEmployeesCollectionOldProjectEmployees = em.merge(projectEmployeesCollectionOldProjectEmployees);
                }
            }
            for (ProjectEmployees projectEmployeesCollectionNewProjectEmployees : projectEmployeesCollectionNew) {
                if (!projectEmployeesCollectionOld.contains(projectEmployeesCollectionNewProjectEmployees)) {
                    Employee oldEmployeeIdOfProjectEmployeesCollectionNewProjectEmployees = projectEmployeesCollectionNewProjectEmployees.getEmployeeId();
                    projectEmployeesCollectionNewProjectEmployees.setEmployeeId(employee);
                    projectEmployeesCollectionNewProjectEmployees = em.merge(projectEmployeesCollectionNewProjectEmployees);
                    if (oldEmployeeIdOfProjectEmployeesCollectionNewProjectEmployees != null && !oldEmployeeIdOfProjectEmployeesCollectionNewProjectEmployees.equals(employee)) {
                        oldEmployeeIdOfProjectEmployeesCollectionNewProjectEmployees.getProjectEmployeesCollection().remove(projectEmployeesCollectionNewProjectEmployees);
                        oldEmployeeIdOfProjectEmployeesCollectionNewProjectEmployees = em.merge(oldEmployeeIdOfProjectEmployeesCollectionNewProjectEmployees);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = employee.getId();
                if (findEmployee(id) == null) {
                    throw new NonexistentEntityException("The employee with id " + id + " no longer exists.");
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
            Employee employee;
            try {
                employee = em.getReference(Employee.class, id);
                employee.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employee with id " + id + " no longer exists.", enfe);
            }
            Collection<ProjectEmployees> projectEmployeesCollection = employee.getProjectEmployeesCollection();
            for (ProjectEmployees projectEmployeesCollectionProjectEmployees : projectEmployeesCollection) {
                projectEmployeesCollectionProjectEmployees.setEmployeeId(null);
                projectEmployeesCollectionProjectEmployees = em.merge(projectEmployeesCollectionProjectEmployees);
            }
            em.remove(employee);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Employee> findEmployeeEntities() {
        return findEmployeeEntities(true, -1, -1);
    }

    public List<Employee> findEmployeeEntities(int maxResults, int firstResult) {
        return findEmployeeEntities(false, maxResults, firstResult);
    }

    private List<Employee> findEmployeeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Employee.class));
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

    public Employee findEmployee(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployeeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Employee> rt = cq.from(Employee.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
