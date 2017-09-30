package com.research.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.research.dto.ProjectTypeDto;
import com.research.service.ProjectTypeService;

@RestController
@RequestMapping("/projecttype")
@CrossOrigin("${angular.url}")
public class ProjectTypeController {

	@Autowired
	ProjectTypeService projectTypeService;

	@RequestMapping(path = "/getAll")
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<List<ProjectTypeDto>>(projectTypeService.getAllProjectTypes(), HttpStatus.OK);
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addProject(@RequestBody ProjectTypeDto projectTypeDto) {
		projectTypeService.addProjectType(projectTypeDto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
