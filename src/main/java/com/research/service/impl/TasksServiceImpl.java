package com.research.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.research.dto.project.TaskDTO;
import com.research.dto.project.TasksExpectedOutcomesDto;
import com.research.entity.Lfm;
import com.research.entity.Tasks;
import com.research.exception.BusinessException;
import com.research.repositories.BaseRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;

import com.research.repositories.project.TaskRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.LFMService;
import com.research.service.interfaces.TasksExpectedOutcomesService;
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
	@Autowired
	private TasksExpectedOutcomesService tasksExpectedOutcomesService;

	@SuppressWarnings("rawtypes")
	@Override
	public BaseRepository getBaseRepo() {
		return taskRepo;
	}
	

	@Override
	public TaskDTO addTask(TaskDTO taskDTO) {
		validateDTO(taskDTO);
		Lfm lfm = lfmService.getLFM(taskDTO.getLfmId());
		Tasks tasks = new Tasks();
		tasks.setLfmId(lfm);
		Date start = taskDTO.getStartDate();
		Date end = taskDTO.getEndDate();
		Calendar calStart = new GregorianCalendar();
		calStart.setTime(start);
		Calendar calEnd = new GregorianCalendar();
		calEnd.setTime(end);
		int yearDif = calEnd.get(Calendar.YEAR) - calStart.get(Calendar.YEAR);
		int duration = yearDif * 12 +  calEnd.get(Calendar.MONTH) - calStart.get(Calendar.MONTH);
		System.out.println("The Duration is : " + duration);
		if (duration <= 0){
			throw new BusinessException();
		}
		tasks.setDuration(duration);
		tasks.setEndDate(taskDTO.getEndDate());
		tasks.setName(taskDTO.getName());
		tasks.setStartDate(taskDTO.getStartDate());
		tasks = this.save(tasks);
		taskDTO.setId(tasks.getId());
		taskDTO.setDuration(duration);
		for (TasksExpectedOutcomesDto expectedTaskDto : taskDTO.getTasksExpectedOutcomesCollection()){
			expectedTaskDto.setTaskDTO(taskDTO);
		}
		tasksExpectedOutcomesService.save(taskDTO.getTasksExpectedOutcomesCollection(), tasks);
		return taskDTO;
	}

	private void validateDTO(TaskDTO taskDTO) {
		
		if (taskDTO.getName() == null){
			throw new BusinessException();
		}
		List<TasksExpectedOutcomesDto> list = taskDTO.getTasksExpectedOutcomesCollection();
		
		if (list == null || list.isEmpty()){
			throw new BusinessException();
		}
		if (taskDTO.getStartDate() == null || taskDTO.getEndDate() == null){
			throw new BusinessException();
		}
		
		Date projectEnd = taskDTO.getProjectEndDate();
		if (projectEnd == null){
			throw new BusinessException();
		}
		Date startDate = taskDTO.getStartDate();
		Date endDate = taskDTO.getEndDate();
		if (endDate.compareTo(startDate) <= 0) {
			throw new BusinessException();
		}
	}
//		if (startDate.compareTo(new Date()) < 0 || endDate.compareTo(new Date()) < 0){
//			throw new BusinessException();
//		}
//			
//		if (projectEnd.compareTo(startDate) < 0 || projectEnd.compareTo(endDate) < 0){
//			throw new BusinessException();
//		}	}

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


	@Override
	public List<Tasks> getTasksByLfm(Long lfm) {
	
		List<Tasks> tasks = taskRepo.getTaskByLfm(lfm);
		return tasks;
	}
}
