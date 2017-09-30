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
@SequenceGenerator(name="SEQ",allocationSize=1,sequenceName="SEQ_PROJECT_TYPE")
@Entity
@Table(name = "project_types")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "ProjectTypes.findAll", query = "SELECT p FROM ProjectTypes p"),
		@NamedQuery(name = "ProjectTypes.findById", query = "SELECT p FROM ProjectTypes p WHERE p.id = :id"),
		@NamedQuery(name = "ProjectTypes.findByCreateDate", query = "SELECT p FROM ProjectTypes p WHERE p.createDate = :createDate"),
		@NamedQuery(name = "ProjectTypes.findByModifyDate", query = "SELECT p FROM ProjectTypes p WHERE p.modifyDate = :modifyDate"),
		@NamedQuery(name = "ProjectTypes.findByRetireDate", query = "SELECT p FROM ProjectTypes p WHERE p.retireDate = :retireDate"),
		@NamedQuery(name = "ProjectTypes.findByRetired", query = "SELECT p FROM ProjectTypes p WHERE p.retired = :retired"),
		@NamedQuery(name = "ProjectTypes.findByType", query = "SELECT p FROM ProjectTypes p WHERE p.type = :type"),
		@NamedQuery(name = "ProjectTypes.findByDescription", query = "SELECT p FROM ProjectTypes p WHERE p.description = :description") })
public class ProjectTypes extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Size(max = 255)
	@Column(name = "type")
	private String type;
	@Size(max = 255)
	@Column(name = "description")
	private String description;
	@OneToMany(mappedBy = "typeId")
	private Collection<Project> projectCollection;

	public ProjectTypes() {
	}

		public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlTransient
	public Collection<Project> getProjectCollection() {
		return projectCollection;
	}

	public void setProjectCollection(Collection<Project> projectCollection) {
		this.projectCollection = projectCollection;
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
		if (!(object instanceof ProjectTypes)) {
			return false;
		}
		ProjectTypes other = (ProjectTypes) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.mycompany.cassandrarest.ProjectTypes[ id=" + id + " ]";
	}

}
