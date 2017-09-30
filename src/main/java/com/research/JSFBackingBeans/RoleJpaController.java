/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.JSFBackingBeans;

import com.research.JSFBackingBeans.exceptions.NonexistentEntityException;
import com.research.entity.Role;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.research.entity.SysUserRoles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Moamenovic
 */
public class RoleJpaController implements Serializable {

    public RoleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Role role) {
        if (role.getSysUserRolesCollection() == null) {
            role.setSysUserRolesCollection(new ArrayList<SysUserRoles>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<SysUserRoles> attachedSysUserRolesCollection = new ArrayList<SysUserRoles>();
            for (SysUserRoles sysUserRolesCollectionSysUserRolesToAttach : role.getSysUserRolesCollection()) {
                sysUserRolesCollectionSysUserRolesToAttach = em.getReference(sysUserRolesCollectionSysUserRolesToAttach.getClass(), sysUserRolesCollectionSysUserRolesToAttach.getId());
                attachedSysUserRolesCollection.add(sysUserRolesCollectionSysUserRolesToAttach);
            }
            role.setSysUserRolesCollection(attachedSysUserRolesCollection);
            em.persist(role);
            for (SysUserRoles sysUserRolesCollectionSysUserRoles : role.getSysUserRolesCollection()) {
                Role oldRoleIdOfSysUserRolesCollectionSysUserRoles = sysUserRolesCollectionSysUserRoles.getRoleId();
                sysUserRolesCollectionSysUserRoles.setRoleId(role);
                sysUserRolesCollectionSysUserRoles = em.merge(sysUserRolesCollectionSysUserRoles);
                if (oldRoleIdOfSysUserRolesCollectionSysUserRoles != null) {
                    oldRoleIdOfSysUserRolesCollectionSysUserRoles.getSysUserRolesCollection().remove(sysUserRolesCollectionSysUserRoles);
                    oldRoleIdOfSysUserRolesCollectionSysUserRoles = em.merge(oldRoleIdOfSysUserRolesCollectionSysUserRoles);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Role role) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Role persistentRole = em.find(Role.class, role.getId());
            Collection<SysUserRoles> sysUserRolesCollectionOld = persistentRole.getSysUserRolesCollection();
            Collection<SysUserRoles> sysUserRolesCollectionNew = role.getSysUserRolesCollection();
            Collection<SysUserRoles> attachedSysUserRolesCollectionNew = new ArrayList<SysUserRoles>();
            for (SysUserRoles sysUserRolesCollectionNewSysUserRolesToAttach : sysUserRolesCollectionNew) {
                sysUserRolesCollectionNewSysUserRolesToAttach = em.getReference(sysUserRolesCollectionNewSysUserRolesToAttach.getClass(), sysUserRolesCollectionNewSysUserRolesToAttach.getId());
                attachedSysUserRolesCollectionNew.add(sysUserRolesCollectionNewSysUserRolesToAttach);
            }
            sysUserRolesCollectionNew = attachedSysUserRolesCollectionNew;
            role.setSysUserRolesCollection(sysUserRolesCollectionNew);
            role = em.merge(role);
            for (SysUserRoles sysUserRolesCollectionOldSysUserRoles : sysUserRolesCollectionOld) {
                if (!sysUserRolesCollectionNew.contains(sysUserRolesCollectionOldSysUserRoles)) {
                    sysUserRolesCollectionOldSysUserRoles.setRoleId(null);
                    sysUserRolesCollectionOldSysUserRoles = em.merge(sysUserRolesCollectionOldSysUserRoles);
                }
            }
            for (SysUserRoles sysUserRolesCollectionNewSysUserRoles : sysUserRolesCollectionNew) {
                if (!sysUserRolesCollectionOld.contains(sysUserRolesCollectionNewSysUserRoles)) {
                    Role oldRoleIdOfSysUserRolesCollectionNewSysUserRoles = sysUserRolesCollectionNewSysUserRoles.getRoleId();
                    sysUserRolesCollectionNewSysUserRoles.setRoleId(role);
                    sysUserRolesCollectionNewSysUserRoles = em.merge(sysUserRolesCollectionNewSysUserRoles);
                    if (oldRoleIdOfSysUserRolesCollectionNewSysUserRoles != null && !oldRoleIdOfSysUserRolesCollectionNewSysUserRoles.equals(role)) {
                        oldRoleIdOfSysUserRolesCollectionNewSysUserRoles.getSysUserRolesCollection().remove(sysUserRolesCollectionNewSysUserRoles);
                        oldRoleIdOfSysUserRolesCollectionNewSysUserRoles = em.merge(oldRoleIdOfSysUserRolesCollectionNewSysUserRoles);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = role.getId();
                if (findRole(id) == null) {
                    throw new NonexistentEntityException("The role with id " + id + " no longer exists.");
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
            Role role;
            try {
                role = em.getReference(Role.class, id);
                role.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The role with id " + id + " no longer exists.", enfe);
            }
            Collection<SysUserRoles> sysUserRolesCollection = role.getSysUserRolesCollection();
            for (SysUserRoles sysUserRolesCollectionSysUserRoles : sysUserRolesCollection) {
                sysUserRolesCollectionSysUserRoles.setRoleId(null);
                sysUserRolesCollectionSysUserRoles = em.merge(sysUserRolesCollectionSysUserRoles);
            }
            em.remove(role);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Role> findRoleEntities() {
        return findRoleEntities(true, -1, -1);
    }

    public List<Role> findRoleEntities(int maxResults, int firstResult) {
        return findRoleEntities(false, maxResults, firstResult);
    }

    private List<Role> findRoleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Role.class));
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

    public Role findRole(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Role.class, id);
        } finally {
            em.close();
        }
    }

    public int getRoleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Role> rt = cq.from(Role.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
