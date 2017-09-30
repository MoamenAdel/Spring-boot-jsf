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
@Table(name = "sys_user")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "SysUser.findAll", query = "SELECT s FROM SysUser s"),
		@NamedQuery(name = "SysUser.findById", query = "SELECT s FROM SysUser s WHERE s.id = :id"),
		@NamedQuery(name = "SysUser.findByCreateDate", query = "SELECT s FROM SysUser s WHERE s.createDate = :createDate"),
		@NamedQuery(name = "SysUser.findByModifyDate", query = "SELECT s FROM SysUser s WHERE s.modifyDate = :modifyDate"),
		@NamedQuery(name = "SysUser.findByRetireDate", query = "SELECT s FROM SysUser s WHERE s.retireDate = :retireDate"),
		@NamedQuery(name = "SysUser.findByRetired", query = "SELECT s FROM SysUser s WHERE s.retired = :retired"),
		@NamedQuery(name = "SysUser.findByUserName", query = "SELECT s FROM SysUser s WHERE s.userName = :userName"),
		@NamedQuery(name = "SysUser.findByPassword", query = "SELECT s FROM SysUser s WHERE s.password = :password") })
public class SysUser extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Size(max = 255)
	@Column(name = "user_name")
	private String userName;
	@Size(max = 255)
	@Column(name = "password")
	private String password;
	@OneToMany(mappedBy = "userId")
	private Collection<SysUserRoles> sysUserRolesCollection;

	public SysUser() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof SysUser)) {
			return false;
		}
		SysUser other = (SysUser) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.mycompany.cassandrarest.SysUser[ id=" + id + " ]";
	}

}
