/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sheko
 */
@Entity
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
    , @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id")
    , @NamedQuery(name = "Employee.findByCreateDate", query = "SELECT e FROM Employee e WHERE e.createDate = :createDate")
    , @NamedQuery(name = "Employee.findByModifyDate", query = "SELECT e FROM Employee e WHERE e.modifyDate = :modifyDate")
    , @NamedQuery(name = "Employee.findByRetireDate", query = "SELECT e FROM Employee e WHERE e.retireDate = :retireDate")
    , @NamedQuery(name = "Employee.findByRetired", query = "SELECT e FROM Employee e WHERE e.retired = :retired")
    , @NamedQuery(name = "Employee.findByName", query = "SELECT e FROM Employee e WHERE e.name = :name")
    , @NamedQuery(name = "Employee.findByComments", query = "SELECT e FROM Employee e WHERE e.comments = :comments")
    , @NamedQuery(name = "Employee.findByCriminalStatus", query = "SELECT e FROM Employee e WHERE e.criminalStatus = :criminalStatus")
    , @NamedQuery(name = "Employee.findByCv", query = "SELECT e FROM Employee e WHERE e.cv = :cv")
    , @NamedQuery(name = "Employee.findByHiringNote", query = "SELECT e FROM Employee e WHERE e.hiringNote = :hiringNote")
    , @NamedQuery(name = "Employee.findByPosition", query = "SELECT e FROM Employee e WHERE e.position = :position")
    , @NamedQuery(name = "Employee.findByCenter", query = "SELECT e FROM Employee e WHERE e.center = :center")
    , @NamedQuery(name = "Employee.findBySerialNumber", query = "SELECT e FROM Employee e WHERE e.serialNumber = :serialNumber")})
@SequenceGenerator(name="SEQ",allocationSize=1,sequenceName="SEQ_PROJECT")
public class Employee extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "comments")
    private String comments;
    @Size(max = 255)
    @Column(name = "criminal_status")
    private String criminalStatus;
    @Size(max = 255)
    @Column(name = "cv")
    private String cv;
    @Size(max = 255)
    @Column(name = "hiring_note")
    private String hiringNote;
    @Size(max = 255)
    @Column(name = "position")
    private String position;
    @Size(max = 255)
    @Column(name = "center")
    private String center;
    @Size(max = 255)
    @Column(name = "serial_number")
    private String serialNumber;
    @OneToMany(mappedBy = "employeeId")
    private Collection<ProjectEmployees> projectEmployeesCollection;

    public Employee() {
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCriminalStatus() {
        return criminalStatus;
    }

    public void setCriminalStatus(String criminalStatus) {
        this.criminalStatus = criminalStatus;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getHiringNote() {
        return hiringNote;
    }

    public void setHiringNote(String hiringNote) {
        this.hiringNote = hiringNote;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @XmlTransient
    public Collection<ProjectEmployees> getProjectEmployeesCollection() {
        return projectEmployeesCollection;
    }

    public void setProjectEmployeesCollection(Collection<ProjectEmployees> projectEmployeesCollection) {
        this.projectEmployeesCollection = projectEmployeesCollection;
    }



	
    
}
