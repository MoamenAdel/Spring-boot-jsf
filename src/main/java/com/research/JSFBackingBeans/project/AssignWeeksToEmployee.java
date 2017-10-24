package com.research.JSFBackingBeans.project;

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
import com.research.service.interfaces.EmployeeWeekService;

import lombok.Data;

@Scope(value = "view")
@Component("AssignWeeksToEmployee")
@ManagedBean(value = "AssignWeeksToEmployee")
@ViewScoped
@Data
public class AssignWeeksToEmployee {
	@Autowired
	EmployeeWeekService employeeWeekService;

	EmployeeWeekDto employeeWeekDto;
	EmployeeDto employeeDto;
	@PostConstruct
	public void loadData() {
		employeeDto = (EmployeeDto) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("employeeDto");
		employeeWeekDto.setEmployeeDto(employeeDto);
	}
	
	public String addEmployeeWeek() {
		employeeWeekService.addEmployeeWeek(employeeWeekDto);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Employees Hours successfully assigned", ""));
		return null;
	}
}
