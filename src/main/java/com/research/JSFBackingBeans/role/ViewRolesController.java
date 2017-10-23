package com.research.JSFBackingBeans.role;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.JSFBackingBeans.lazydatamodels.RoleLazyDataModel;
import com.research.dto.sysuser.RoleDto;
import com.research.service.interfaces.RoleService;

import lombok.Data;

@Scope(value = "view")
@Component("ViewRole")
@ManagedBean(value = "ViewRole")
@ViewScoped
@Data
public class ViewRolesController {
	@Autowired
	RoleLazyDataModel roleLazyDataModel;
	@Autowired
	RoleService roleService;

	public void delete(RoleDto roleDto) {
		roleService.delete(roleDto.getId());
	}

}
