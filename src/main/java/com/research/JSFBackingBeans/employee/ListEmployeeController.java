package com.research.JSFBackingBeans.employee;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.research.JSFBackingBeans.lazydatamodels.EmployeeLazyDataModel;
import com.research.dto.employee.EmployeeDto;
import com.research.service.interfaces.EmployeeService;

import lombok.Data;

import org.springframework.stereotype.Component;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Moamenovic
 */
@Scope(value = "view")
@Component("ListEmployeeController")
@ManagedBean(value = "ListEmployeeController")
@ViewScoped
@Data
public class ListEmployeeController implements Serializable {

	private static final long serialVersionUID = 9006980830134897009L;
	@Autowired
	EmployeeService employeeService;

	private List<EmployeeDto> items;
	@Autowired
	private EmployeeLazyDataModel model;

	@PostConstruct
	public void loadData() {
		model.setRowCount(employeeService.count().intValue());
	}

	public List<EmployeeDto> getItems() {
		if (items == null) {
			items = new ArrayList<EmployeeDto>();
		}
		return items;
	}

	public void setItems(List<EmployeeDto> items) {
		this.items = items;
	}

	public EmployeeLazyDataModel getModel() {
		return model;
	}

	public void setModel(EmployeeLazyDataModel model) {
		this.model = model;
	}

	public String viewEmployee(EmployeeDto employee) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("employeeDto", employee);
		return "View";
	}

}
