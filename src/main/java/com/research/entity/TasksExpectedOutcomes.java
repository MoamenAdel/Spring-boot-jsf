/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Sheko
 */
@SequenceGenerator(name="SEQ",allocationSize=1,sequenceName="SEQ_PROJECT")
@Entity
@Table(name = "tasks_expected_outcomes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TasksExpectedOutcomes.findAll", query = "SELECT t FROM TasksExpectedOutcomes t")
    , @NamedQuery(name = "TasksExpectedOutcomes.findById", query = "SELECT t FROM TasksExpectedOutcomes t WHERE t.id = :id")
    , @NamedQuery(name = "TasksExpectedOutcomes.findByCreateDate", query = "SELECT t FROM TasksExpectedOutcomes t WHERE t.createDate = :createDate")
    , @NamedQuery(name = "TasksExpectedOutcomes.findByModifyDate", query = "SELECT t FROM TasksExpectedOutcomes t WHERE t.modifyDate = :modifyDate")
    , @NamedQuery(name = "TasksExpectedOutcomes.findByRetireDate", query = "SELECT t FROM TasksExpectedOutcomes t WHERE t.retireDate = :retireDate")
    , @NamedQuery(name = "TasksExpectedOutcomes.findByRetired", query = "SELECT t FROM TasksExpectedOutcomes t WHERE t.retired = :retired")
    , @NamedQuery(name = "TasksExpectedOutcomes.findByExpectation", query = "SELECT t FROM TasksExpectedOutcomes t WHERE t.expectation = :expectation")})
public class TasksExpectedOutcomes extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "expectation")
    private String expectation;
    @JsonIgnore
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    @ManyToOne
    private Tasks taskId;

    public TasksExpectedOutcomes() {
    }

    
    public String getExpectation() {
        return expectation;
    }

    public void setExpectation(String expectation) {
        this.expectation = expectation;
    }

    public Tasks getTaskId() {
        return taskId;
    }

    public void setTaskId(Tasks taskId) {
        this.taskId = taskId;
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
        if (!(object instanceof TasksExpectedOutcomes)) {
            return false;
        }
        TasksExpectedOutcomes other = (TasksExpectedOutcomes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.cassandrarest.TasksExpectedOutcomes[ id=" + id + " ]";
    }
    
}
