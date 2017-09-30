/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.JSFBackingBeans;

import com.research.JSFBackingBeans.exceptions.NonexistentEntityException;
import com.research.entity.SysUser;
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
public class SysUserJpaController implements Serializable {

    public SysUserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SysUser sysUser) {
        if (sysUser.getSysUserRolesCollection() == null) {
            sysUser.setSysUserRolesCollection(new ArrayList<SysUserRoles>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<SysUserRoles> attachedSysUserRolesCollection = new ArrayList<SysUserRoles>();
            for (SysUserRoles sysUserRolesCollectionSysUserRolesToAttach : sysUser.getSysUserRolesCollection()) {
                sysUserRolesCollectionSysUserRolesToAttach = em.getReference(sysUserRolesCollectionSysUserRolesToAttach.getClass(), sysUserRolesCollectionSysUserRolesToAttach.getId());
                attachedSysUserRolesCollection.add(sysUserRolesCollectionSysUserRolesToAttach);
            }
            sysUser.setSysUserRolesCollection(attachedSysUserRolesCollection);
            em.persist(sysUser);
            for (SysUserRoles sysUserRolesCollectionSysUserRoles : sysUser.getSysUserRolesCollection()) {
                SysUser oldUserIdOfSysUserRolesCollectionSysUserRoles = sysUserRolesCollectionSysUserRoles.getUserId();
                sysUserRolesCollectionSysUserRoles.setUserId(sysUser);
                sysUserRolesCollectionSysUserRoles = em.merge(sysUserRolesCollectionSysUserRoles);
                if (oldUserIdOfSysUserRolesCollectionSysUserRoles != null) {
                    oldUserIdOfSysUserRolesCollectionSysUserRoles.getSysUserRolesCollection().remove(sysUserRolesCollectionSysUserRoles);
                    oldUserIdOfSysUserRolesCollectionSysUserRoles = em.merge(oldUserIdOfSysUserRolesCollectionSysUserRoles);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SysUser sysUser) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SysUser persistentSysUser = em.find(SysUser.class, sysUser.getId());
            Collection<SysUserRoles> sysUserRolesCollectionOld = persistentSysUser.getSysUserRolesCollection();
            Collection<SysUserRoles> sysUserRolesCollectionNew = sysUser.getSysUserRolesCollection();
            Collection<SysUserRoles> attachedSysUserRolesCollectionNew = new ArrayList<SysUserRoles>();
            for (SysUserRoles sysUserRolesCollectionNewSysUserRolesToAttach : sysUserRolesCollectionNew) {
                sysUserRolesCollectionNewSysUserRolesToAttach = em.getReference(sysUserRolesCollectionNewSysUserRolesToAttach.getClass(), sysUserRolesCollectionNewSysUserRolesToAttach.getId());
                attachedSysUserRolesCollectionNew.add(sysUserRolesCollectionNewSysUserRolesToAttach);
            }
            sysUserRolesCollectionNew = attachedSysUserRolesCollectionNew;
            sysUser.setSysUserRolesCollection(sysUserRolesCollectionNew);
            sysUser = em.merge(sysUser);
            for (SysUserRoles sysUserRolesCollectionOldSysUserRoles : sysUserRolesCollectionOld) {
                if (!sysUserRolesCollectionNew.contains(sysUserRolesCollectionOldSysUserRoles)) {
                    sysUserRolesCollectionOldSysUserRoles.setUserId(null);
                    sysUserRolesCollectionOldSysUserRoles = em.merge(sysUserRolesCollectionOldSysUserRoles);
                }
            }
            for (SysUserRoles sysUserRolesCollectionNewSysUserRoles : sysUserRolesCollectionNew) {
                if (!sysUserRolesCollectionOld.contains(sysUserRolesCollectionNewSysUserRoles)) {
                    SysUser oldUserIdOfSysUserRolesCollectionNewSysUserRoles = sysUserRolesCollectionNewSysUserRoles.getUserId();
                    sysUserRolesCollectionNewSysUserRoles.setUserId(sysUser);
                    sysUserRolesCollectionNewSysUserRoles = em.merge(sysUserRolesCollectionNewSysUserRoles);
                    if (oldUserIdOfSysUserRolesCollectionNewSysUserRoles != null && !oldUserIdOfSysUserRolesCollectionNewSysUserRoles.equals(sysUser)) {
                        oldUserIdOfSysUserRolesCollectionNewSysUserRoles.getSysUserRolesCollection().remove(sysUserRolesCollectionNewSysUserRoles);
                        oldUserIdOfSysUserRolesCollectionNewSysUserRoles = em.merge(oldUserIdOfSysUserRolesCollectionNewSysUserRoles);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = sysUser.getId();
                if (findSysUser(id) == null) {
                    throw new NonexistentEntityException("The sysUser with id " + id + " no longer exists.");
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
            SysUser sysUser;
            try {
                sysUser = em.getReference(SysUser.class, id);
                sysUser.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sysUser with id " + id + " no longer exists.", enfe);
            }
            Collection<SysUserRoles> sysUserRolesCollection = sysUser.getSysUserRolesCollection();
            for (SysUserRoles sysUserRolesCollectionSysUserRoles : sysUserRolesCollection) {
                sysUserRolesCollectionSysUserRoles.setUserId(null);
                sysUserRolesCollectionSysUserRoles = em.merge(sysUserRolesCollectionSysUserRoles);
            }
            em.remove(sysUser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SysUser> findSysUserEntities() {
        return findSysUserEntities(true, -1, -1);
    }

    public List<SysUser> findSysUserEntities(int maxResults, int firstResult) {
        return findSysUserEntities(false, maxResults, firstResult);
    }

    private List<SysUser> findSysUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SysUser.class));
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

    public SysUser findSysUser(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SysUser.class, id);
        } finally {
            em.close();
        }
    }

    public int getSysUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SysUser> rt = cq.from(SysUser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
