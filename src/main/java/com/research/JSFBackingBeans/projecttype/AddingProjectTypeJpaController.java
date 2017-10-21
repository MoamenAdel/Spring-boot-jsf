package com.research.JSFBackingBeans.projecttype;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.dto.project.ProjectTypeDto;
import com.research.service.interfaces.ProjectService;
import com.research.service.interfaces.ProjectTypeService;

@Component(value = "AddProjectTypeController")
@Scope(value = "view")
@ManagedBean(name = "AddProjectTypeController")
@ViewScoped
public class AddingProjectTypeJpaController {

	ProjectTypeDto projectTypeDto = new ProjectTypeDto();
	@Autowired
	ProjectTypeService projectTypeService;

	public void addProject() {
		projectTypeService.addProjectType(projectTypeDto);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Project type successfully Saved",""));
		projectTypeDto=new ProjectTypeDto();
		
	}

	public ProjectTypeDto getProjectTypeDto() {
		return projectTypeDto;
	}

	public void setProjectTypeDto(ProjectTypeDto projectTypeDto) {
		this.projectTypeDto = projectTypeDto;
	}

}
