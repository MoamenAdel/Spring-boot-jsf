/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.JSFBackingBeans.project;

import java.io.Serializable;


import org.springframework.context.annotation.Scope;

import com.research.entity.Project;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Component;

/**
 *
 * @author Moamenovic
 */

@Scope(value = "session")
@Component(value = "ProjectJpaController")
@ELBeanName(value = "ProjectJpaController")
@Join(path = "/project", to = "/project/Create.xhtml")
public class ProjectJpaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9006980830134897009L;
	Project selected = new Project();

	public Project getSelected() {
		return selected;
	}

	public void setSelected(Project selected) {
		this.selected = selected;
	}

}
