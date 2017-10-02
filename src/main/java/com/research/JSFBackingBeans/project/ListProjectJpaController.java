package com.research.JSFBackingBeans.project;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.research.dto.project.ProjectDto;
import com.research.service.interfaces.ProjectService;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 *
 * @author Moamenovic
 */

@Scope(value = "session")
@Component(value = "ListProjectJpaController")
@ELBeanName(value = "ListProjectJpaController")
@Join(path = "/listProject", to = "/project/List.xhtml")
public class ListProjectJpaController implements Serializable {
	private static final long serialVersionUID = 9006980830134897009L;
	@Autowired
	ProjectService projectService;

	private List<ProjectDto> items;

	@PostConstruct
	public void loadData() {
		items = projectService.getAllProjects();
		System.out.println("");
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
	
	public String viewProject(ProjectDto project){
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("projectDto", project);
		return "View";
	}

}