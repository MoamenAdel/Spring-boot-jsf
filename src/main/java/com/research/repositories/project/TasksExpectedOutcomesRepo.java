package com.research.repositories.project;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.research.entity.TasksExpectedOutcomes;
import com.research.repositories.BaseRepository;

public interface TasksExpectedOutcomesRepo extends BaseRepository<TasksExpectedOutcomes> {

	@Query("select e from TasksExpectedOutcomes e where e.retired = 0 and e.taskId.id = ?")
	List<TasksExpectedOutcomes> findByTaskId(Long id);

}
