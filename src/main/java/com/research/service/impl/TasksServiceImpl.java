package com.research.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.research.dto.project.TaskDTO;
import com.research.entity.Tasks;
import com.research.repositories.BaseRepository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;

import com.research.repositories.project.TaskRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.LFMService;
import com.research.service.interfaces.TasksService;

@Service
@Transactional
public class TasksServiceImpl extends BaseServiceImpl<Tasks> implements TasksService {

	@Autowired
	TaskRepo taskRepo;
	@Autowired
	DozerBeanMapper mapper;
	@Autowired
	LFMService lfmService;

	@SuppressWarnings("rawtypes")
	@Override
	public BaseRepository getBaseRepo() {
		// TODO Auto-generated method stub
		return taskRepo;
	}

	@Override
	public TaskDTO addTask(TaskDTO taskDTO) {
		Tasks tasks = new Tasks();
		tasks.setCreateDate(taskDTO.getCreationDate());
		tasks.setDuration(taskDTO.getDuration());
		tasks.setEndDate(taskDTO.getEndDate());
		tasks.setName(taskDTO.getName());
		// Lfm lfm = new Lfm();
		// lfm.setId(taskDTO.getId());
		// tasks.setLfmId(lfm);
		tasks.setStartDate(new Date());
		tasks = this.save(tasks);
		this.save(tasks);
		taskDTO.setId(tasks.getId());
		// TODO
		return taskDTO;
	}

	@Override
	public List<TaskDTO> getAllTasks() {
		List<TaskDTO> tasksDtos = new ArrayList<>();
		mapper.map(getAll(), tasksDtos);
		return tasksDtos;
	}

	@Override
	public List<TaskDTO> getTaskForProject(Long projectId) {
		// TODO Auto-generated method stub
		List<TaskDTO> tasksDtos = new ArrayList<>();
		lfmService.getLFMByProjectid(projectId).getTasksCollection();
		mapper.map(lfmService.getLFMByProjectid(projectId).getTasksCollection(), tasksDtos);
		return tasksDtos;
	}

	@Override
	public void deleteTask(Long taskId) {
		// TODO Auto-generated method stub

	}
}
