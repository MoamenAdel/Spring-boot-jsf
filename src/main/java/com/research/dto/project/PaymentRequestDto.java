package com.research.dto.project;

import com.research.dto.BaseDto;

public class PaymentRequestDto extends BaseDto {

	private EmployeeDto employeeDto;
	private Long employeeId;
	private String employeeName;
	private String role;
	private Double total;
	private Integer hoursPerWeek;
	private Double monthlyIncentive;
	private Double participation;
	private Integer numberOfMonths;

	public PaymentRequestDto() {
	}

	public EmployeeDto getEmployeeDto() {
		return employeeDto;
	}

	public void setEmployeeDto(EmployeeDto employeeDto) {
		this.employeeDto = employeeDto;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getHoursPerWeek() {
		return hoursPerWeek;
	}

	public void setHoursPerWeek(Integer hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	public Double getMonthlyIncentive() {
		return monthlyIncentive;
	}

	public void setMonthlyIncentive(Double monthlyIncentive) {
		this.monthlyIncentive = monthlyIncentive;
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
}
