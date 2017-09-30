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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Sheko
 */
@Entity
@Table(name = "PROJECT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p")
    , @NamedQuery(name = "Project.findById", query = "SELECT p FROM Project p WHERE p.id = :id")
    , @NamedQuery(name = "Project.findByCreateDate", query = "SELECT p FROM Project p WHERE p.createDate = :createDate")
    , @NamedQuery(name = "Project.findByModifyDate", query = "SELECT p FROM Project p WHERE p.modifyDate = :modifyDate")
    , @NamedQuery(name = "Project.findByRetireDate", query = "SELECT p FROM Project p WHERE p.retireDate = :retireDate")
    , @NamedQuery(name = "Project.findByRetired", query = "SELECT p FROM Project p WHERE p.retired = :retired")
    , @NamedQuery(name = "Project.findByTitle", query = "SELECT p FROM Project p WHERE p.title = :title")
    , @NamedQuery(name = "Project.findByApplicantName", query = "SELECT p FROM Project p WHERE p.applicantName = :applicantName")
    , @NamedQuery(name = "Project.findByApllicantOrg", query = "SELECT p FROM Project p WHERE p.apllicantOrg = :apllicantOrg")
    , @NamedQuery(name = "Project.findByBudget", query = "SELECT p FROM Project p WHERE p.budget = :budget")
    , @NamedQuery(name = "Project.findBySubmissionDate", query = "SELECT p FROM Project p WHERE p.submissionDate = :submissionDate")
    , @NamedQuery(name = "Project.findByAbbreviation", query = "SELECT p FROM Project p WHERE p.abbreviation = :abbreviation")})
@SequenceGenerator(name="SEQ",allocationSize=1,sequenceName="SEQ_PROJECT")
public class Project extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Size(max = 255)
    @Column(name = "applicant_name")
    private String applicantName;
    @Size(max = 255)
    @Column(name = "apllicant_org")
    private String apllicantOrg;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "budget")
    private Double budget;
    @Column(name = "submission_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submissionDate;
    @Size(max = 255)
    @Column(name = "abbreviation")
    private String abbreviation;
    @OneToMany(mappedBy = "projectId")
    private Collection<ProjectEmployees> projectEmployeesCollection;
    @OneToMany(mappedBy = "projectId")
    private Collection<Lfm> lfmCollection;
    @OneToMany(mappedBy = "projectId")
    private Collection<Docs> docsCollection;
    @JsonIgnore
    @JoinColumn(name = "type_id", referencedColumnName = "id",insertable=false,updatable=false)
    @ManyToOne
    private ProjectTypes typeId;

    public Project() {
    }

      public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApllicantOrg() {
        return apllicantOrg;
    }

    public void setApllicantOrg(String apllicantOrg) {
        this.apllicantOrg = apllicantOrg;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @XmlTransient
    public Collection<ProjectEmployees> getProjectEmployeesCollection() {
        return projectEmployeesCollection;
    }

    public void setProjectEmployeesCollection(Collection<ProjectEmployees> projectEmployeesCollection) {
        this.projectEmployeesCollection = projectEmployeesCollection;
    }

    @XmlTransient
    public Collection<Lfm> getLfmCollection() {
        return lfmCollection;
    }

    public void setLfmCollection(Collection<Lfm> lfmCollection) {
        this.lfmCollection = lfmCollection;
    }

    @XmlTransient
    public Collection<Docs> getDocsCollection() {
        return docsCollection;
    }

    public void setDocsCollection(Collection<Docs> docsCollection) {
        this.docsCollection = docsCollection;
    }

    public ProjectTypes getTypeId() {
        return typeId;
    }

    public void setTypeId(ProjectTypes typeId) {
        this.typeId = typeId;
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
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.cassandrarest.Project[ id=" + id + " ]";
    }
    
}
