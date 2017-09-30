/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sheko
 */
@SequenceGenerator(name="SEQ",allocationSize=1,sequenceName="SEQ_PROJECT")
@Entity
@Table(name = "role")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
//    , @NamedQuery(name = "Role.findById", query = "SELECT r FROM Role r WHERE r.id = :id")
//    , @NamedQuery(name = "Role.findByCreateDate", query = "SELECT r FROM Role r WHERE r.createDate = :createDate")
//    , @NamedQuery(name = "Role.findByModifyDate", query = "SELECT r FROM Role r WHERE r.modifyDate = :modifyDate")
//    , @NamedQuery(name = "Role.findByRetireDate", query = "SELECT r FROM Role r WHERE r.retireDate = :retireDate")
//    , @NamedQuery(name = "Role.findByRetired", query = "SELECT r FROM Role r WHERE r.retired = :retired")
//    , @NamedQuery(name = "Role.findByRole", query = "SELECT r FROM Role r WHERE r.role = :role")
//    , @NamedQuery(name = "Role.findByDescription", query = "SELECT r FROM Role r WHERE r.description = :description")})
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @NotNull
//    
    @Size(max = 255)
    @Column(name = "role")
    private String role;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "roleId")
    private Collection<SysUserRoles> sysUserRolesCollection;

    public Role() {
    }

    

  
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<SysUserRoles> getSysUserRolesCollection() {
        return sysUserRolesCollection;
    }

    public void setSysUserRolesCollection(Collection<SysUserRoles> sysUserRolesCollection) {
        this.sysUserRolesCollection = sysUserRolesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.cassandrarest.Role[ id=" + id + " ]";
    }
    
}
