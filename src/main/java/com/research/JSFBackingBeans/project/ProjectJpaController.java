/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.JSFBackingBeans.project;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.context.annotation.Scope;

import com.research.dto.project.ProjectDto;
import com.research.dto.project.ProjectTypeDto;

import java.util.ArrayList;
import java.util.Collection;

import com.research.service.interfaces.ProjectService;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Moamenovic
 */

@Scope(value = "session")
@Component(value = "ProjectJpaController")
@ELBeanName(value = "ProjectJpaController")
@Join(path = "/project", to = "/project/Create.xhtml")
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
	private UploadedFile file;

	@PostConstruct
	public void getProjectTypeList() throws ServletException, IOException{
		projectTypes = projectService.getProjectTypes();
		
		for (ProjectTypeDto projectType : projectTypes){
			types.add(projectType.getType());
		}
		System.out.println("blablabla");
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

	public String create() throws ServletException, IOException{
		if (file != null){
//			Files.copy(file.getInputstream(), new File("D:/", file.getFileName()).toPath());
			FileUtils.copyInputStreamToFile(file.getInputstream(), new File("D:/File", file.getFileName()));
			}
		selected.setType(selectedType);
		projectService.addProject(selected);
		return null;
	}
	
	public static Collection<Part> getAllParts(Part part) throws ServletException, IOException {
	    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    return request.getParts().stream().filter(p -> part.getName().equals(p.getName())).collect(Collectors.toList());
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
}