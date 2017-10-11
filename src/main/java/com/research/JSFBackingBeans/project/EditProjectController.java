package com.research.JSFBackingBeans.project;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.dto.project.ProjectDto;
import com.research.dto.project.ProjectTypeDto;
import com.research.service.interfaces.ProjectService;

@Component("EditProjectController")
@Scope("view")
@ManagedBean(name = "EditProjectController")
@ViewScoped
public class EditProjectController {

	@Autowired
	private ProjectService projectService;

	private ProjectDto projectDto;
	private List<ProjectTypeDto> projectTypes = new ArrayList<>();
	private List<String> types = new ArrayList<>();
	private String selectedType = new String();
	

	public EditProjectController() {
	}
	
	@PostConstruct
	public void init(){
		ProjectDto temp = (ProjectDto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("projectDto");
		if (temp != null){
			projectDto = temp;
		}
		projectTypes = projectService.getProjectTypes();
		for (ProjectTypeDto projectType : projectTypes){
			types.add(projectType.getType());
		}
	}
	
	public String editProject(){
		projectDto.setType(selectedType);
		projectService.updateProject(projectDto);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("projectDto", projectDto);
		return "View";
	}

	public ProjectDto getProjectDto() {
		return projectDto;
	}

	public void setProjectDto(ProjectDto projectDto) {
		this.projectDto = projectDto;
	}

	public List<ProjectTypeDto> getProjectTypes() {
		return projectTypes;
	}

	public void setProjectTypes(List<ProjectTypeDto> projectTypes) {
		this.projectTypes = projectTypes;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public String getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}

	
}
