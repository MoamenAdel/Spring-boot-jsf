package com.research.JSFBackingBeans.employee;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.dto.employee.EmployeeDto;
import com.research.service.interfaces.EmployeeService;

import lombok.Data;

@Scope(value = "view")
@Component(value = "AddEmployeeController")
@ManagedBean(name = "AddEmployeeController")
@ViewScoped
@Data
public class AddEmployeeController {

	
		@Autowired
		private EmployeeService employeeService;
		
		private static final long serialVersionUID = -9006980830134897009L;
		private EmployeeDto selected = new EmployeeDto();
		
		
		public String create() throws ServletException, IOException {
			
			employeeService.addEmployee(selected);
			selected = new EmployeeDto();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee successfully Saved",""));
			return "Create";
			
		}
}
