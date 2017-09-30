package com.research.controllers.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.research.dto.project.TaskDTO;
import com.research.service.TasksService;

@Controller
@CrossOrigin("${angular.url}")
@RequestMapping(value = "/Task")
public class TaskController {

	@Autowired
	private TasksService tasksService;
	
	@RequestMapping(value = "/addTask" ,method = RequestMethod.POST)
	public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDTO){
		if (taskDTO == null){
			throw new RuntimeException();
		}
		taskDTO = tasksService.addTask(taskDTO);
		ResponseEntity<TaskDTO> response = new ResponseEntity<>(taskDTO, HttpStatus.OK);
		return response;
	}
}
