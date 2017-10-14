package com.research.JSFBackingBeans.project;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.dto.project.DocsDTO;
import com.research.dto.project.ProjectDto;
import com.research.dto.project.ProjectTypeDto;
import com.research.service.interfaces.DocsService;
import com.research.service.interfaces.ProjectService;

import lombok.Data;

@Component("EditProjectController")
@Scope("view")
@ManagedBean(name = "EditProjectController")
@ViewScoped
@Data
public class EditProjectController {

	@Autowired
	private ProjectService projectService;
	private ProjectDto projectDto;
	private List<ProjectTypeDto> projectTypes = new ArrayList<>();
	private List<String> types = new ArrayList<>();
	private String selectedType = new String();
	private UploadedFile uploadedFile;
	@Autowired
	private DocsService docsService;
	private Boolean disableSubmit = true;

	public EditProjectController() {
	}

	@PostConstruct
	public void init() {
		ProjectDto temp = (ProjectDto) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("projectDto");
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

	public void upload() {
		if (uploadedFile == null)
			return;
		DocsDTO docDTO = new DocsDTO();
		docDTO.setFile(uploadedFile);
		docDTO.setProjectId(projectDto.getId());
		docDTO.setProjectDTO(projectDto);
		docDTO.setName(uploadedFile.getFileName());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, docDTO.getName() + " successfully uploaded", ""));

		docsService.addNewDoc(docDTO);

	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		if (uploadedFile != null)
			disableSubmit = false;
		this.uploadedFile = uploadedFile;

	}

}
