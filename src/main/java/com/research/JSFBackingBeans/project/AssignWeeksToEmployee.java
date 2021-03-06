package com.research.JSFBackingBeans.project;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.dto.employee.EmployeeDto;
import com.research.dto.project.EmployeeWeekDto;
import com.research.dto.project.ProjectDto;
import com.research.service.interfaces.EmployeeWeekService;

import lombok.Data;

@Scope(value = "view")
@Component("AssignWeeksToEmployee")
@ManagedBean(value = "AssignWeeksToEmployee")
@ViewScoped
@Data
public class AssignWeeksToEmployee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	EmployeeWeekService employeeWeekService;

	EmployeeWeekDto employeeWeekDto;
	EmployeeDto employeeDto;
	ProjectDto tempForavoidNullPointer;

	@PostConstruct
	public void loadData() {
		employeeDto = (EmployeeDto) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("employeeDto");
		tempForavoidNullPointer = (ProjectDto) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("projectDto");
		employeeWeekDto = new EmployeeWeekDto();
		employeeWeekDto.setEmployee(employeeDto);
		employeeWeekDto.setEmployeeId(employeeDto.getId());
	}

	public String addEmployeeWeek() {
		employeeWeekDto.setEmployee(employeeDto);
		employeeWeekDto.setEmployeeId(employeeDto.getId());
		employeeWeekService.addEmployeeWeek(employeeWeekDto);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Employees Hours successfully assigned to " + employeeDto.getName(), ""));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("projectDto", tempForavoidNullPointer);
		return "AssignEmployees";
	}
	
	public String back() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("projectDto", tempForavoidNullPointer);
		return "../project/AssignEmployees?faces-redirect=true";
	}
}
