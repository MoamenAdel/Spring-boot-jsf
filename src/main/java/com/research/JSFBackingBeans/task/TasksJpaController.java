/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.JSFBackingBeans.task;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import com.research.dto.project.TaskDTO;
import com.research.service.interfaces.TasksService;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Moamenovic
 */
@Scope(value = "session")
@Component(value = "TasksJpaController")
@ELBeanName(value = "TasksJpaController")
@Join(path = "/task", to = "/task/Create.xhtml")
public class TasksJpaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private TasksService tasksService;

	private TaskDTO selected = new TaskDTO();

	@PostConstruct
	public void init() {

	}

	public TaskDTO getSelected() {
		return selected;
	}

	public void setSelected(TaskDTO selected) {
		this.selected = selected;
	}

	public String addTask() {
		tasksService.addTask(selected);
		selected = new TaskDTO();
		return null;
	}
}
