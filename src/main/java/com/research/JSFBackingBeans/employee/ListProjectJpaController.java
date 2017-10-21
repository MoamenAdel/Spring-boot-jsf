package com.research.JSFBackingBeans.employee;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.research.JSFBackingBeans.lazydatamodels.ProjectLazyDataModel;
import com.research.dto.project.ProjectDto;
import com.research.service.interfaces.ProjectService;

import org.springframework.stereotype.Component;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Moamenovic
 */
@Scope(value = "view")
@Component("ListProjectJpaController")
@ManagedBean(value = "ListProjectJpaController")
@ViewScoped
public class ListProjectJpaController implements Serializable {

	private static final long serialVersionUID = 9006980830134897009L;
	@Autowired
	ProjectService projectService;

	private List<ProjectDto> items;
	@Autowired
	private ProjectLazyDataModel model;

	@PostConstruct
	public void loadData() {
		// items = projectService.getAllProjects();
		model.setRowCount(projectService.count().intValue());
	}

	public List<ProjectDto> getItems() {
		if (items == null) {
			items = new ArrayList<ProjectDto>();
		}
		return items;
	}

	public void setItems(List<ProjectDto> items) {
		this.items = items;
	}

	public ProjectLazyDataModel getModel() {
		return model;
	}

	public void setModel(ProjectLazyDataModel model) {
		this.model = model;
	}

	public String viewProject(ProjectDto project) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("projectDto", project);
		return "View";
	}

}
