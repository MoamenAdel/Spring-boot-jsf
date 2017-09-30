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
import com.research.entity.Role;
import com.research.entity.SysUser;
import com.research.entity.SysUserRoles;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Moamenovic
 */
public class SysUserRolesJpaController implements Serializable {

    public SysUserRolesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SysUserRoles sysUserRoles) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Role roleId = sysUserRoles.getRoleId();
            if (roleId != null) {
                roleId = em.getReference(roleId.getClass(), roleId.getId());
                sysUserRoles.setRoleId(roleId);
            }
            SysUser userId = sysUserRoles.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getId());
                sysUserRoles.setUserId(userId);
            }
            em.persist(sysUserRoles);
            if (roleId != null) {
                roleId.getSysUserRolesCollection().add(sysUserRoles);
                roleId = em.merge(roleId);
            }
            if (userId != null) {
                userId.getSysUserRolesCollection().add(sysUserRoles);
                userId = em.merge(userId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SysUserRoles sysUserRoles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SysUserRoles persistentSysUserRoles = em.find(SysUserRoles.class, sysUserRoles.getId());
            Role roleIdOld = persistentSysUserRoles.getRoleId();
            Role roleIdNew = sysUserRoles.getRoleId();
            SysUser userIdOld = persistentSysUserRoles.getUserId();
            SysUser userIdNew = sysUserRoles.getUserId();
            if (roleIdNew != null) {
                roleIdNew = em.getReference(roleIdNew.getClass(), roleIdNew.getId());
                sysUserRoles.setRoleId(roleIdNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getId());
                sysUserRoles.setUserId(userIdNew);
            }
            sysUserRoles = em.merge(sysUserRoles);
            if (roleIdOld != null && !roleIdOld.equals(roleIdNew)) {
                roleIdOld.getSysUserRolesCollection().remove(sysUserRoles);
                roleIdOld = em.merge(roleIdOld);
            }
            if (roleIdNew != null && !roleIdNew.equals(roleIdOld)) {
                roleIdNew.getSysUserRolesCollection().add(sysUserRoles);
                roleIdNew = em.merge(roleIdNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getSysUserRolesCollection().remove(sysUserRoles);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getSysUserRolesCollection().add(sysUserRoles);
                userIdNew = em.merge(userIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = sysUserRoles.getId();
                if (findSysUserRoles(id) == null) {
                    throw new NonexistentEntityException("The sysUserRoles with id " + id + " no longer exists.");
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
            SysUserRoles sysUserRoles;
            try {
                sysUserRoles = em.getReference(SysUserRoles.class, id);
                sysUserRoles.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sysUserRoles with id " + id + " no longer exists.", enfe);
            }
            Role roleId = sysUserRoles.getRoleId();
            if (roleId != null) {
                roleId.getSysUserRolesCollection().remove(sysUserRoles);
                roleId = em.merge(roleId);
            }
            SysUser userId = sysUserRoles.getUserId();
            if (userId != null) {
                userId.getSysUserRolesCollection().remove(sysUserRoles);
                userId = em.merge(userId);
            }
            em.remove(sysUserRoles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SysUserRoles> findSysUserRolesEntities() {
        return findSysUserRolesEntities(true, -1, -1);
    }

    public List<SysUserRoles> findSysUserRolesEntities(int maxResults, int firstResult) {
        return findSysUserRolesEntities(false, maxResults, firstResult);
    }

    private List<SysUserRoles> findSysUserRolesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SysUserRoles.class));
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

    public SysUserRoles findSysUserRoles(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SysUserRoles.class, id);
        } finally {
            em.close();
        }
    }

    public int getSysUserRolesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SysUserRoles> rt = cq.from(SysUserRoles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
