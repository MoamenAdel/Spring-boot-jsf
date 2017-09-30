/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sheko
 */
@SequenceGenerator(name="SEQ",allocationSize=1,sequenceName="SEQ_PROJECT")
@Entity
@Table(name = "lfm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lfm.findAll", query = "SELECT l FROM Lfm l")
    , @NamedQuery(name = "Lfm.findById", query = "SELECT l FROM Lfm l WHERE l.id = :id")
    , @NamedQuery(name = "Lfm.findByCreateDate", query = "SELECT l FROM Lfm l WHERE l.createDate = :createDate")
    , @NamedQuery(name = "Lfm.findByModifyDate", query = "SELECT l FROM Lfm l WHERE l.modifyDate = :modifyDate")
    , @NamedQuery(name = "Lfm.findByRetireDate", query = "SELECT l FROM Lfm l WHERE l.retireDate = :retireDate")
    , @NamedQuery(name = "Lfm.findByRetired", query = "SELECT l FROM Lfm l WHERE l.retired = :retired")})
public class Lfm extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @ManyToOne
    private Project projectId;
    @OneToMany(mappedBy = "lfmId")
    private Collection<Tasks> tasksCollection;

    public Lfm() {
    }

   
    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }

    @XmlTransient
    public Collection<Tasks> getTasksCollection() {
        return tasksCollection;
    }

    public void setTasksCollection(Collection<Tasks> tasksCollection) {
        this.tasksCollection = tasksCollection;
    }




    @Override
    public String toString() {
        return "com.mycompany.cassandrarest.Lfm[ id=" + id + " ]";
    }
    
}
