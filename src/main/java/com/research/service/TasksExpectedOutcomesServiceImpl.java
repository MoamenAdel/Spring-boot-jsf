package com.research.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.research.dto.project.TasksExpectedOutcomesDto;
import com.research.entity.TasksExpectedOutcomes;
import com.research.repositories.BaseRepository;
import com.research.repositories.project.TasksExpectedOutcomesRepo;
import com.research.service.interfaces.TasksExpectedOutcomesService;

@Service
public class TasksExpectedOutcomesServiceImpl extends BaseServiceImpl<TasksExpectedOutcomes>
		implements TasksExpectedOutcomesService {

	@Autowired
	private TasksExpectedOutcomesRepo tasksExpectedOutcomesRepo;
	@Autowired
	DozerBeanMapper mapper;
	
	@Override
	public List<TasksExpectedOutcomes> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TasksExpectedOutcomes save(TasksExpectedOutcomes entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TasksExpectedOutcomes update(TasksExpectedOutcomes entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TasksExpectedOutcomes getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(TasksExpectedOutcomes entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<TasksExpectedOutcomes> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public BaseRepository getBaseRepo() {
		return tasksExpectedOutcomesRepo;
	}

	@Override
	public List<TasksExpectedOutcomesDto> findByTaskId(Long id) {
		List<TasksExpectedOutcomes> tasksExpectedOutcomes = tasksExpectedOutcomesRepo.findByTaskId(id);
		List<TasksExpectedOutcomesDto> tasksExpectedOutcomesDtos= new ArrayList<>();
		for (TasksExpectedOutcomes tasksExpectedOutcome : tasksExpectedOutcomes){
			TasksExpectedOutcomesDto tasksExpectedOutcomesDto = new TasksExpectedOutcomesDto();
			mapper.map(tasksExpectedOutcome, tasksExpectedOutcomesDto);
			tasksExpectedOutcomesDtos.add(tasksExpectedOutcomesDto);
		}	
		return tasksExpectedOutcomesDtos;
	}

}
