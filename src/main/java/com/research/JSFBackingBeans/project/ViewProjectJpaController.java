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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;


import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
import javax.faces.application.FacesMessage;

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
	private BeanFactory beanFactory;

	private ProjectDto projectDto;

	private Boolean editable = false;
	private DocsLazyDataModel docsLazyDataModel;

	private StreamedContent streamedContent;
	private UploadedFile uploadedFile;
	@Autowired
	private DocsService docsService;
	private Boolean disableSubmit = true;

	public ViewProjectJpaController() {
	}

	@PostConstruct
	public void doPost() {
		ProjectDto temp = (ProjectDto) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("projectDto");
		if (temp != null) {
			projectDto = temp;

			docsLazyDataModel = beanFactory.getBean(DocsLazyDataModel.class, temp);
		}
		docsLazyDataModel.setRowCount(projectService.countDocs(projectDto.getId()).intValue());
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
		return "../lfm/View?faces-redirect=true";
	}

	public String viewPaymentRequest() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("projectDto", projectDto);
		return "../payment/List?faces-redirect=true";
	}

	public String editProject() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("projectDto", projectDto);
		return "Edit?faces-redirect=true";
	}

	public void download(DocsDTO docsDTO) {

		File file = new File(docsDTO.getPath());
		try {
			InputStream inputStream = new FileInputStream(file);
			streamedContent = new DefaultStreamedContent(inputStream, Files.probeContentType(file.toPath()),
					docsDTO.getPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

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

	public String assignEmployees() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("projectDto", projectDto);
		return "AssignEmployees?faces-redirect=true";
	}

}
