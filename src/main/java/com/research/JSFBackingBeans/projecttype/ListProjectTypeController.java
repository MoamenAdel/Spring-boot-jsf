package com.research.JSFBackingBeans.projecttype;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.research.JSFBackingBeans.lazydatamodels.ProjectTypeLazyDataModel;
import com.research.dto.project.ProjectTypeDto;
import com.research.service.interfaces.ProjectService;
import com.research.service.interfaces.ProjectTypeService;

import org.springframework.stereotype.Component;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Moamenovic
 */

@Scope(value = "view")
@Component("ListProjectTypeJpaController")
@ManagedBean(value = "ListProjectTypeJpaController")
@ViewScoped
public class ListProjectTypeController implements Serializable {
	private static final long serialVersionUID = 9006980830134897009L;
	@Autowired
	ProjectService projectService;
	@Autowired
	private ProjectTypeService projectTypeService;

	@Autowired
	private ProjectTypeLazyDataModel model;
	
	@PostConstruct
	public void loadData() {
//		items = projectService.getAllProjects();
		model.setRowCount( projectTypeService.count().intValue());
	}

	
	public ProjectTypeLazyDataModel getModel() {
		return model;
	}

	public void setModel(ProjectTypeLazyDataModel model) {
		this.model = model;
	}


}