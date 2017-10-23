package com.research.JSFBackingBeans.role;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.JSFBackingBeans.lazydatamodels.DocsLazyDataModel;
import com.research.JSFBackingBeans.project.ViewProjectJpaController;
import com.research.dto.sysuser.RoleDto;
import com.research.service.interfaces.ProjectService;
import com.research.service.interfaces.RoleService;

import lombok.Data;

@Scope(value = "view")
@Component("addRole")
@ManagedBean(value = "addRole")
@ViewScoped
@Data
public class AddRoleController {

	RoleDto roleDto = new RoleDto();
	@Autowired
	RoleService roleService;

	public void addRole() {
		roleService.addRole(roleDto);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Project type successfully Saved", ""));
		roleDto = new RoleDto();
	}

}
