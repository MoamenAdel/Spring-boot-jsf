package com.research.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.research.dto.project.TasksExpectedOutcomesDto;
import com.research.entity.Tasks;
import com.research.entity.TasksExpectedOutcomes;
import com.research.exception.BusinessException;
import com.research.repositories.BaseRepository;
import com.research.repositories.project.TasksExpectedOutcomesRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.TasksExpectedOutcomesService;

@Service
@Transactional
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

	@Override
	public List<TasksExpectedOutcomesDto> save(
			List<TasksExpectedOutcomesDto> list, Tasks task) {
		List<TasksExpectedOutcomes> tasksExpectedOutcomes = new ArrayList<>();
		for (TasksExpectedOutcomesDto expectedOutcomeDto: list){
			TasksExpectedOutcomes expectedOutcome = new TasksExpectedOutcomes();
			if (expectedOutcomeDto.getExpectation() == null || task == null || task.getId() == null){
				throw new BusinessException();
			}
			expectedOutcome.setExpectation(expectedOutcomeDto.getExpectation());
			expectedOutcome.setTaskId(task);
			tasksExpectedOutcomes.add(expectedOutcome);
		}
		tasksExpectedOutcomesRepo.save(tasksExpectedOutcomes);
		return list;
	}
	
	private void validateDTO(TasksExpectedOutcomesDto dto){
		
	}

}
