package com.research.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

@MappedSuperclass
@Where(clause="retired<>1")
public abstract class BaseEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
//    @Basic(optional = false)
    @Column(name = "id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ")
    protected Long id;
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createDate;
    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date modifyDate;
    @Column(name = "retire_date")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date retireDate;
    @Column(name = "retired", columnDefinition = "SMALLINT default 0")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    protected boolean retired;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Date getRetireDate() {
		return retireDate;
	}
	public void setRetireDate(Date retireDate) {
		this.retireDate = retireDate;
	}
	public boolean isRetired() {
		return retired;
	}
	public void setRetired(boolean retired) {
		this.retired = retired;
	}
	
}
