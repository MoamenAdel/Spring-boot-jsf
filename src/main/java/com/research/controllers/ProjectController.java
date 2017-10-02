
package com.research.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.research.dto.project.ProjectDto;
import com.research.service.interfaces.ProjectService;

@RestController
@CrossOrigin("${angular.url}")
@RequestMapping("/project/faces")
public class ProjectController {
@Autowired
ProjectService projectService;
	@RequestMapping(path = "/selectOne")
	public ResponseEntity<?> getOne(@RequestParam("Id") @NotNull Long id) {
		ProjectDto projectDto = new ProjectDto();
		projectDto.setTitle("title");
		projectDto.setType("type");
		return new ResponseEntity<ProjectDto>(projectDto, HttpStatus.OK);
	}

	@RequestMapping("getall")
	public ResponseEntity<?> getAll() {

		
		return new ResponseEntity<List<ProjectDto>>(projectService.getAllProjects(), HttpStatus.OK);
	}

	@RequestMapping(path = "add", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody ProjectDto projectDto) {
		projectService.addProject(projectDto);
		return new ResponseEntity<ProjectDto>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(path = "update/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> update(@PathParam("id") Long id, @RequestBody ProjectDto projectDto) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(path = "delete/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> delete(@PathParam("id") Long id, @RequestBody ProjectDto projectDto) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

