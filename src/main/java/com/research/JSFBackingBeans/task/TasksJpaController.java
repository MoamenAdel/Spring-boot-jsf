/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.JSFBackingBeans.task;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.research.JSFBackingBeans.exceptions.NonexistentEntityException;
import com.research.dto.project.TaskDTO;
import com.research.entity.Lfm;
import com.research.entity.Tasks;
import com.research.entity.TasksExpectedOutcomes;
import com.research.service.interfaces.TasksService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

	@Autowired
	private TasksService tasksService;
	
	private TaskDTO taskDTO = new TaskDTO();
	
	@PostConstruct
	public void init(){
		
	}

	public TaskDTO getTaskDTO() {
		return taskDTO;
	}

	public void setTaskDTO(TaskDTO taskDTO) {
		this.taskDTO = taskDTO;
	}
    
	public String addTask(){
		
		return null;
	}
}
