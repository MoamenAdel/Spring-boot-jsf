package com.research.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class EmployeeWeek extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5261701662315234974L;

	@Column(name = "WEEK1")
	private Integer week1;
	@Column(name = "WEEK2")
	private Integer week2;
	@Column(name = "WEEK3")
	private Integer week3;
	@Column(name = "WEEK4")
	private Integer week4;
	@Column(name = "MONTH")
	private Date month;
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_ID")
	private Employee employee;

	public Integer getWeek1() {
		return week1;
	}

	public void setWeek1(Integer week1) {
		this.week1 = week1;
	}

	public Integer getWeek2() {
		return week2;
	}

	public void setWeek2(Integer week2) {
		this.week2 = week2;
	}

	public Integer getWeek3() {
		return week3;
	}

	public void setWeek3(Integer week3) {
		this.week3 = week3;
	}

	public Integer getWeek4() {
		return week4;
	}

	public void setWeek4(Integer week4) {
		this.week4 = week4;
	}

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
