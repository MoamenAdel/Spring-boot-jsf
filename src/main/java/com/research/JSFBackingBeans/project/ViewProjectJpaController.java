package com.research.JSFBackingBeans.project;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;

import com.research.JSFBackingBeans.lazydatamodels.DocsLazyDataModel;
import com.research.dto.project.DocsDTO;
import com.research.dto.project.ProjectDto;
import com.research.exception.BusinessException;
import com.research.repositories.project.DocsRepository;
import com.research.service.interfaces.DocsService;
import com.research.service.interfaces.ProjectService;

import lombok.Data;

@Scope(value = "view")
@Component("ViewProjectJpaController")
@ManagedBean(value = "ViewProjectJpaController")
@ViewScoped
@Data
public class ViewProjectJpaController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private DocsService docsService;
	private ProjectDto projectDto;

	private Boolean editable = false;
	@Autowired
	private DocsLazyDataModel docsLazyDataModel;

	private StreamedContent streamedContent;

	public ViewProjectJpaController() {
	}

	@PostConstruct
	public void doPost() {
		ProjectDto temp = (ProjectDto) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("projectDto");
		if (temp != null) {
			projectDto = temp;
		}
		// projectDto = projectService.findOne((long) 38);
	}

	public ProjectDto getProjectDto() {
		// if (projectDto == null){
		// ProjectDto temp = (ProjectDto)
		// FacesContext.getCurrentInstance().getExternalContext().getFlash().get("projectDto");
		// if (temp != null) {
		// projectDto = temp;
		// }
		// }
		return projectDto;
	}

	public void setProjectDto(ProjectDto projectDto) {
		this.projectDto = projectDto;
	}

	public String viewLfm() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("projectDto", projectDto);
		return "../lfm/View";
	}

	public String editProject() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("projectDto", projectDto);
		return "Edit";
	}

	public void download(DocsDTO docsDTO)
	{
		
		File file=new File(docsDTO.getPath());
		try {
			InputStream inputStream=new  FileInputStream(file);
			streamedContent=new  DefaultStreamedContent(inputStream,
					Files.probeContentType(file.toPath()), docsDTO.getPath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
