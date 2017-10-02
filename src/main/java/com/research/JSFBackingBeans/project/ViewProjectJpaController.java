package com.research.JSFBackingBeans.project;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.dto.project.DocsDTO;
import com.research.dto.project.ProjectDto;
import com.research.exception.BusinessException;
import com.research.repositories.project.DocsRepository;
import com.research.service.interfaces.DocsService;
import com.research.service.interfaces.ProjectService;

@Scope(value = "session")
@Component(value = "ViewProjectJpaController")
@ELBeanName(value = "ViewProjectJpaController")
@Join(path = "/project", to = "/project/View.xhtml")
public class ViewProjectJpaController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private DocsService docsService;
	private ProjectDto projectDto;
	private UploadedFile file;
	private Boolean editable = false;
	
	public ViewProjectJpaController() {
	}
	
	@PostConstruct
	public void init(){
		projectDto = (ProjectDto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("projectDto");
		if (projectDto == null) {
			throw new RuntimeException();
		}
//		projectDto = projectService.findOne((long) 38);
	}

	public ProjectDto getProjectDto() {
		return projectDto;
	}

	public void setProjectDto(ProjectDto projectDto) {
		this.projectDto = projectDto;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public String addDocument(){
		DocsDTO docDTO = new DocsDTO();
		docDTO.setFile(file);
		docDTO.setProjectId(projectDto.getId());
		docDTO.setProjectDTO(projectDto);
		docsService.addNewDoc(docDTO);
		return null;
	}
	
}
