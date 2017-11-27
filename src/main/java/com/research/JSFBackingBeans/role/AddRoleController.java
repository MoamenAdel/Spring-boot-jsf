package com.research.JSFBackingBeans.role;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.dto.sysuser.RoleDto;
import com.research.service.interfaces.RoleService;

import lombok.Data;

@Scope(value = "view")
@Component("addRole")
@ManagedBean(value = "addRole")
@ViewScoped
@Data
public class AddRoleController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RoleDto roleDto = new RoleDto();
	@Autowired
	RoleService roleService;

	public void addRole() {
		roleService.addRole(roleDto);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Role successfully Saved", ""));
		roleDto = new RoleDto();
	}

}
