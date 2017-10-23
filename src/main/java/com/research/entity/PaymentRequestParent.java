package com.research.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT_REQUEST_PARENT")
@SequenceGenerator(allocationSize = 1, name = "SEQ", sequenceName = "SEQ_PAYMENT_REQUEST_PARENT")
public class PaymentRequestParent extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "PROJECT_ID")
	private Project project;
	@Column(name = "START_DATE")
	private Date startDate;
	@Column(name = "END_DATE")
	private Date endDate;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
