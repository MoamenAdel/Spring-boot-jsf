package com.research.JSFBackingBeans.lazydatamodels;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.dto.project.DocsDTO;
import com.research.dto.project.ProjectDto;
import com.research.service.interfaces.DocsService;
import com.research.service.interfaces.ProjectService;

@Component
@Scope("view")
public class DocsLazyDataModel extends LazyDataModel<DocsDTO> {

	@Autowired
	ProjectService projectService;

	@PostConstruct
	void init() {

		ProjectDto projectDto = (ProjectDto) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("projectDto");
		if (projectDto == null)
			return;
		Collection<DocsDTO> docsDTOs = projectService.getDocs(projectDto);
		setRowCount(docsDTOs.size());

	}

	@Override
	public List<DocsDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		// TODO Auto-generated method stub
		ProjectDto projectDto = (ProjectDto) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("projectDto");
		return projectService.getDocs(projectDto);

	}

	@Override
	public List<DocsDTO> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return super.load(first, pageSize, multiSortMeta, filters);
	}

}
