package com.research.entity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Table(name = "PAYMENT_REQUEST")
@Where(clause="retired = 0")
public class PaymentRequest extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1469092034927631734L;
	@Column(name = "EMPLOYEE_NAME")
	private String employeeName;
	@Column(name = "ROLE")
	private String role;
	@Column(name = "MONTHLY_INCENTIVE")
	private Double monthlyIncentive;
	@Column(name = "HOURS_PER_WEEK")
	private Integer hoursPerWeek;
	@Column(name = "PARTICIPATION")
	private Double participation;
	@Column(name = "NUMBER_OF_MONTHS")
	private Integer numberOfMonths;
	@Column(name = "TOTAL")
	private Double total;
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_ID")
	private Employee employee;
	
	public PaymentRequest() {
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Double getMonthlyIncentive() {
		return monthlyIncentive;
	}

	public void setMonthlyIncentive(Double monthlyIncentive) {
		this.monthlyIncentive = monthlyIncentive;
	}

	public Integer getHoursPerWeek() {
		return hoursPerWeek;
	}

	public void setHoursPerWeek(Integer hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	public Double getParticipation() {
		return participation;
	}

	public void setParticipation(Double participation) {
		this.participation = participation;
	}

	public Integer getNumberOfMonths() {
		return numberOfMonths;
	}

	public void setNumberOfMonths(Integer numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
