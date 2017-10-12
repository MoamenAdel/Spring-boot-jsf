package com.research.JSFBackingBeans.lazydatamodels;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.research.dto.project.ProjectDto;
import com.research.entity.Project;
import com.research.service.interfaces.ProjectService;

@Component("prototype")
public class ProjectLazyDataModel extends LazyDataModel<ProjectDto> {

	@Autowired
	private ProjectService projectService;
	
	@PostConstruct
	public void init(){
		setRowCount( projectService.count().intValue());
	}
	
	@Override
	public List<ProjectDto> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		// TODO Auto-generated method stub
//		return super.load(first, pageSize, sortField, sortOrder, filters);
		return projectService.getProjectPage(first / pageSize, pageSize);
	}
	
	@Override
	public List<ProjectDto> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return super.load(first, pageSize, multiSortMeta, filters);
	}
}
