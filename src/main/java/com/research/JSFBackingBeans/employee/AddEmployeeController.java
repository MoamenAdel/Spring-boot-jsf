package com.research.JSFBackingBeans.employee;

import java.io.IOException;

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
@Component(value = "AddEmployeeController")
@ManagedBean(name = "AddEmployeeController")
@ViewScoped
public class AddEmployeeController {

	
		@Autowired
		private EmployeeService employeeService;
		
		private static final long serialVersionUID = -9006980830134897009L;
		private EmployeeDto selected = new EmployeeDto();
		private UploadedFile cv;
		private UploadedFile certificate;
		private UploadedFile criminalStatus;
		
		
		public String create() throws ServletException, IOException {
			
			employeeService.addEmployee(selected, cv, certificate, criminalStatus);
			selected = new EmployeeDto();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee successfully Saved",""));
			return "Create";
			
		}


		public EmployeeService getEmployeeService() {
			return employeeService;
		}


		public void setEmployeeService(EmployeeService employeeService) {
			this.employeeService = employeeService;
		}


		public EmployeeDto getSelected() {
			return selected;
		}


		public void setSelected(EmployeeDto selected) {
			this.selected = selected;
		}


		public UploadedFile getCv() {
			return cv;
		}


		public void setCv(UploadedFile cv) {
			this.cv = cv;
		}


		public UploadedFile getCertificate() {
			return certificate;
		}


		public void setCertificate(UploadedFile certificate) {
			this.certificate = certificate;
		}


		public UploadedFile getCriminalStatus() {
			return criminalStatus;
		}


		public void setCriminalStatus(UploadedFile criminalStatus) {
			this.criminalStatus = criminalStatus;
		}
}
