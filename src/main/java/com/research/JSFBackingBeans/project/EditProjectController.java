package com.research.JSFBackingBeans.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.dto.project.ProjectDto;
import com.research.dto.project.ProjectTypeDto;
import com.research.service.interfaces.ProjectService;

import lombok.Data;

@Component("EditProjectController")
@Scope("view")
@ManagedBean(name = "EditProjectController")
@ViewScoped
@Data
public class EditProjectController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProjectService projectService;
	private ProjectDto projectDto;
	private List<ProjectTypeDto> projectTypes = new ArrayList<>();
	private List<String> types = new ArrayList<>();
	private String selectedType = new String();


	public EditProjectController() {
	}

	@PostConstruct
	public void init() {
		ProjectDto temp = (ProjectDto) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("projectDto");
		selectedType= temp.getTypeId().getType();
		if (temp != null) {
			projectDto = temp;
		}
		projectTypes = projectService.getProjectTypes();
		for (ProjectTypeDto projectType : projectTypes) {
			types.add(projectType.getType());
		}
	}

	public String editProject() {
		projectDto.setType(selectedType);
		projectService.updateProject(projectDto);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("projectDto", projectDto);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Successful", "Project successfully edited"));
		return "View";
	}



}
