package com.research.JSFBackingBeans.employee;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;

import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.dto.employee.EmployeeDto;
import com.research.service.interfaces.EmployeeService;

import lombok.Data;

@Scope(value = "view")
@Component(value = "EditEmployeeController")
@ManagedBean(name = "EditEmployeeController")
@ViewScoped
@Data
public class EditEmployeeController {

	@Autowired
	private EmployeeService employeeService;

	private static final long serialVersionUID = -9006980830134897009L;
	private EmployeeDto employeeDto = new EmployeeDto();

	@PostConstruct
	public void doPost() {
		employeeDto = (EmployeeDto) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("employeeDto");
	}

	public String editEmployee() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("employeeDto", employeeDto);
		employeeService.updateEmployee(employeeDto);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Employee successfully edited", ""));
		return "View";
	}

}
