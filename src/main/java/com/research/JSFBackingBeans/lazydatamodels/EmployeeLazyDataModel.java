package com.research.JSFBackingBeans.lazydatamodels;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.dto.employee.EmployeeDto;
import com.research.service.interfaces.EmployeeService;

@Component
@Scope("prototype")
public class EmployeeLazyDataModel extends LazyDataModel<EmployeeDto> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private EmployeeService employeeService;
	
	@PostConstruct
	public void init(){
		setRowCount( employeeService.count().intValue());
	}
	
	@Override
	public List<EmployeeDto> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		// TODO Auto-generated method stub
//		return super.load(first, pageSize, sortField, sortOrder, filters);
		return employeeService.getEmployeePage(first / pageSize, pageSize);
	}
	
	@Override
	public List<EmployeeDto> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return super.load(first, pageSize, multiSortMeta, filters);
	}
}
