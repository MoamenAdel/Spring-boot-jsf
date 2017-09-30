package com.research.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.research.dto.LFMDto;
import com.research.service.LFMService;

@RestController
@CrossOrigin("${angular.url}")
@RequestMapping("/lfm")
public class LFMController {
	@Autowired
	LFMService lfmService;

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addLFM(@RequestBody LFMDto lfmDto) {
		lfmService.addLFMDto(lfmDto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
