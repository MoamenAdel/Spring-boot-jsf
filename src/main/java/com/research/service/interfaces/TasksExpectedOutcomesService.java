package com.research.service.interfaces;

import java.util.List;

import com.research.dto.project.TasksExpectedOutcomesDto;
import com.research.entity.Tasks;
import com.research.entity.TasksExpectedOutcomes;
import com.research.service.BaseService;

public interface TasksExpectedOutcomesService extends BaseService<TasksExpectedOutcomes>{

	List<TasksExpectedOutcomesDto> findByTaskId(Long id);
	
	List<TasksExpectedOutcomesDto> save(List<TasksExpectedOutcomesDto> list, Tasks task);
}
