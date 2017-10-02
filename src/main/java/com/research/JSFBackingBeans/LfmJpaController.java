/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research.JSFBackingBeans;

import com.research.JSFBackingBeans.exceptions.NonexistentEntityException;
import com.research.dto.project.LFMDto;
import com.research.entity.Lfm;

import java.io.Serializable;

import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.research.entity.Project;
import com.research.entity.Tasks;
import com.research.service.interfaces.LFMService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Moamenovic
 */
@Scope(value = "session")
@Component(value = "LfmJpaController")
@ELBeanName(value = "LfmJpaController")
//@RequestMapping("/lfm")
public class LfmJpaController implements Serializable {

	@Autowired
	LFMService lfmService;

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addLFM(@RequestBody LFMDto lfmDto) {
		lfmService.addLFMDto(lfmDto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
