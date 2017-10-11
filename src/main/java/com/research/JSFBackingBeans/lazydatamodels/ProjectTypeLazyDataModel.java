/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.JSFBackingBeans.lazydatamodels;

import com.research.dto.project.ProjectTypeDto;
import com.research.service.interfaces.ProjectTypeService;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author sherif
 */
@Component
public class ProjectTypeLazyDataModel extends LazyDataModel<ProjectTypeDto> {

    @Autowired
    ProjectTypeService projectTypeService;

    @PostConstruct
    public void init() {
        setRowCount( projectTypeService.count().intValue());
    }

    @Override
    public List<ProjectTypeDto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return projectTypeService.getPage(first, pageSize);
        
    }

    @Override
    public List<ProjectTypeDto> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        return super.load(first, pageSize, multiSortMeta, filters); //To change body of generated methods, choose Tools | Templates.
    }
    
}
