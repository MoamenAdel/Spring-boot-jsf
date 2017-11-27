/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.JSFBackingBeans.project;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.dto.project.ProjectDto;
import com.research.dto.project.ProjectTypeDto;
import com.research.service.interfaces.ProjectService;

/**
 *
 * @author Moamenovic
 */

// @Scope(value = "session")
@Scope(value = "view")
@Component(value = "ProjectJpaController")
@ManagedBean(name = "ProjectJpaController")
@ViewScoped
public class ProjectJpaController implements Serializable {

	/**
	 * 
	 */

	@Autowired
	private ProjectService projectService;

	private static final long serialVersionUID = -9006980830134897009L;
	private ProjectDto selected = new ProjectDto();
	private List<ProjectTypeDto> projectTypes = new ArrayList<>();
	private List<String> types = new ArrayList<>();
	private String selectedType = new String();
	// private UploadedFile file;
	private StreamedContent file;

	@PostConstruct
	public void getProjectTypeList() throws ServletException, IOException {
		projectTypes = projectService.getProjectTypes();

		for (ProjectTypeDto projectType : projectTypes) {
			types.add(projectType.getType());
		}

		InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/Test.txt");
		file = new DefaultStreamedContent(stream);
		System.out.println("ready");
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
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

	public ProjectDto getSelected() {
		return selected;
	}

	public List<ProjectTypeDto> getProjectTypes() {
		return projectTypes;
	}

	public void setProjectTypes(List<ProjectTypeDto> projectTypes) {
		this.projectTypes = projectTypes;
	}

	public void setSelected(ProjectDto selected) {
		this.selected = selected;
	}

	public String create() throws ServletException, IOException {
		// if (file != null){
		//// Files.copy(file.getInputstream(), new File("D:/",
		// file.getFileName()).toPath());
		// FileUtils.copyInputStreamToFile(file.getInputstream(), new
		// File("D:/File", file.getFileName()));
		// }
		selected.setType(selectedType);
		projectService.addProject(selected);
		selected = new ProjectDto();
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Project successfully Saved",""));
		return "Create";
	}
	//
	// public static Collection<Part> getAllParts(Part part) throws
	// ServletException, IOException {
	// HttpServletRequest request = (HttpServletRequest)
	// FacesContext.getCurrentInstance().getExternalContext().getRequest();
	// return request.getParts().stream().filter(p ->
	// part.getName().equals(p.getName())).collect(Collectors.toList());
	// }

}