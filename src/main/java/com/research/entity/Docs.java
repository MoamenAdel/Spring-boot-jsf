/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sheko
 */
@Entity
@Table(name = "docs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docs.findAll", query = "SELECT d FROM Docs d")
    , @NamedQuery(name = "Docs.findById", query = "SELECT d FROM Docs d WHERE d.id = :id")
    , @NamedQuery(name = "Docs.findByCreateDate", query = "SELECT d FROM Docs d WHERE d.createDate = :createDate")
    , @NamedQuery(name = "Docs.findByModifyDate", query = "SELECT d FROM Docs d WHERE d.modifyDate = :modifyDate")
    , @NamedQuery(name = "Docs.findByRetireDate", query = "SELECT d FROM Docs d WHERE d.retireDate = :retireDate")
    , @NamedQuery(name = "Docs.findByRetired", query = "SELECT d FROM Docs d WHERE d.retired = :retired")
    , @NamedQuery(name = "Docs.findByDocPath", query = "SELECT d FROM Docs d WHERE d.docPath = :docPath")
    , @NamedQuery(name = "Docs.findByIsUploaded", query = "SELECT d FROM Docs d WHERE d.isUploaded = :isUploaded")})
@SequenceGenerator(name="SEQ",allocationSize=1,sequenceName="SEQ_PROJECT")
public class Docs extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 255)
    @Column(name = "doc_path")
    private String docPath;
    @Column(name = "is_uploaded")
    private Short isUploaded;
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @ManyToOne
    private Project projectId;

    public Docs() {
    }


      public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public Short getIsUploaded() {
        return isUploaded;
    }

    public void setIsUploaded(Short isUploaded) {
        this.isUploaded = isUploaded;
    }

    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }


    
    
}
