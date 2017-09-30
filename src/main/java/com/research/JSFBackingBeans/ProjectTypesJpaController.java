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
import com.research.entity.Project;
import com.research.entity.ProjectTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Moamenovic
 */
@Scope(value = "session")
@Component(value = "ProjectTypesJpaController")
@ELBeanName(value = "ProjectTypesJpaController")
public class ProjectTypesJpaController implements Serializable {

	String test = new String();

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public ProjectTypesJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}

	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(ProjectTypes projectTypes) {
		if (projectTypes.getProjectCollection() == null) {
			projectTypes.setProjectCollection(new ArrayList<Project>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Collection<Project> attachedProjectCollection = new ArrayList<Project>();
			for (Project projectCollectionProjectToAttach : projectTypes.getProjectCollection()) {
				projectCollectionProjectToAttach = em.getReference(projectCollectionProjectToAttach.getClass(),
						projectCollectionProjectToAttach.getId());
				attachedProjectCollection.add(projectCollectionProjectToAttach);
			}
			projectTypes.setProjectCollection(attachedProjectCollection);
			em.persist(projectTypes);
			for (Project projectCollectionProject : projectTypes.getProjectCollection()) {
				ProjectTypes oldTypeIdOfProjectCollectionProject = projectCollectionProject.getTypeId();
				projectCollectionProject.setTypeId(projectTypes);
				projectCollectionProject = em.merge(projectCollectionProject);
				if (oldTypeIdOfProjectCollectionProject != null) {
					oldTypeIdOfProjectCollectionProject.getProjectCollection().remove(projectCollectionProject);
					oldTypeIdOfProjectCollectionProject = em.merge(oldTypeIdOfProjectCollectionProject);
				}
			}
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(ProjectTypes projectTypes) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			ProjectTypes persistentProjectTypes = em.find(ProjectTypes.class, projectTypes.getId());
			Collection<Project> projectCollectionOld = persistentProjectTypes.getProjectCollection();
			Collection<Project> projectCollectionNew = projectTypes.getProjectCollection();
			Collection<Project> attachedProjectCollectionNew = new ArrayList<Project>();
			for (Project projectCollectionNewProjectToAttach : projectCollectionNew) {
				projectCollectionNewProjectToAttach = em.getReference(projectCollectionNewProjectToAttach.getClass(),
						projectCollectionNewProjectToAttach.getId());
				attachedProjectCollectionNew.add(projectCollectionNewProjectToAttach);
			}
			projectCollectionNew = attachedProjectCollectionNew;
			projectTypes.setProjectCollection(projectCollectionNew);
			projectTypes = em.merge(projectTypes);
			for (Project projectCollectionOldProject : projectCollectionOld) {
				if (!projectCollectionNew.contains(projectCollectionOldProject)) {
					projectCollectionOldProject.setTypeId(null);
					projectCollectionOldProject = em.merge(projectCollectionOldProject);
				}
			}
			for (Project projectCollectionNewProject : projectCollectionNew) {
				if (!projectCollectionOld.contains(projectCollectionNewProject)) {
					ProjectTypes oldTypeIdOfProjectCollectionNewProject = projectCollectionNewProject.getTypeId();
					projectCollectionNewProject.setTypeId(projectTypes);
					projectCollectionNewProject = em.merge(projectCollectionNewProject);
					if (oldTypeIdOfProjectCollectionNewProject != null
							&& !oldTypeIdOfProjectCollectionNewProject.equals(projectTypes)) {
						oldTypeIdOfProjectCollectionNewProject.getProjectCollection()
								.remove(projectCollectionNewProject);
						oldTypeIdOfProjectCollectionNewProject = em.merge(oldTypeIdOfProjectCollectionNewProject);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = projectTypes.getId();
				if (findProjectTypes(id) == null) {
					throw new NonexistentEntityException("The projectTypes with id " + id + " no longer exists.");
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
			ProjectTypes projectTypes;
			try {
				projectTypes = em.getReference(ProjectTypes.class, id);
				projectTypes.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The projectTypes with id " + id + " no longer exists.", enfe);
			}
			Collection<Project> projectCollection = projectTypes.getProjectCollection();
			for (Project projectCollectionProject : projectCollection) {
				projectCollectionProject.setTypeId(null);
				projectCollectionProject = em.merge(projectCollectionProject);
			}
			em.remove(projectTypes);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<ProjectTypes> findProjectTypesEntities() {
		return findProjectTypesEntities(true, -1, -1);
	}

	public List<ProjectTypes> findProjectTypesEntities(int maxResults, int firstResult) {
		return findProjectTypesEntities(false, maxResults, firstResult);
	}

	private List<ProjectTypes> findProjectTypesEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(ProjectTypes.class));
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

	public ProjectTypes findProjectTypes(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(ProjectTypes.class, id);
		} finally {
			em.close();
		}
	}

	public int getProjectTypesCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<ProjectTypes> rt = cq.from(ProjectTypes.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}