package com.research.JSFBackingBeans.lazydatamodels;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.research.dto.sysuser.RoleDto;
import com.research.service.interfaces.RoleService;

@Component
@Lazy
public class RoleLazyDataModel extends LazyDataModel<RoleDto> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	RoleService roleService;

	@PostConstruct
	private void init() {
		setRowCount(roleService.count().intValue());
	}
@Override
public List<RoleDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
		Map<String, Object> filters) {
	// TODO Auto-generated method stub
	return roleService.getRolePage(first / pageSize, pageSize);
}
}
