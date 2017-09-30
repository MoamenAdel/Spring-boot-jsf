/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.JSFBackingBeans;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.JSFBackingBeans.exceptions.NonexistentEntityException;
import com.research.entity.Project;
import com.research.entity.ProjectTypes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Moamenovic
 */
@Scope(value = "session")
@Component(value = "ProjectTypesJpaController")
@ELBeanName(value = "ProjectTypesJpaController")
@Join(path = "/projectTypes", to = "/projectTypes/Create.xhtml")
public class ProjectTypesJpaController implements Serializable {
	private static final long serialVersionUID = 9006980830134897009L;
	ProjectTypes selected = new ProjectTypes();

	public ProjectTypes getSelected() {
		return selected;
	}

	public void setSelected(ProjectTypes selected) {
		this.selected = selected;
	}

	}
