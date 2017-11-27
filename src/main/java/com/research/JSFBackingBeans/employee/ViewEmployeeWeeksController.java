package com.research.JSFBackingBeans.employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
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
@Component(value = "ViewEmployeeWeeksController")
@ManagedBean(name = "ViewEmployeeWeeksController")
@ViewScoped
@Data
public class ViewEmployeeWeeksController implements Serializable {

	@Autowired
	private EmployeeWeekService employeeWeekService;

	private static final long serialVersionUID = -9006980830134897009L;
	private EmployeeDto employeeDto = new EmployeeDto();

	public List<EmployeeWeekDto> employeeWeeks;

	@PostConstruct
	public void doPost() {
		employeeDto = (EmployeeDto) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("employeeDto");
		employeeWeeks = employeeWeekService.getWeeksByEmployeeId(employeeDto.getId());

	}

	public List<EmployeeWeekDto> getEmployeeWeeks() {
		if (employeeWeeks == null)
			employeeWeeks = new ArrayList<>();
		return employeeWeeks;
	}

	public void setEmployeeWeeks(List<EmployeeWeekDto> employeeWeeks) {
		this.employeeWeeks = employeeWeeks;
	}

}
