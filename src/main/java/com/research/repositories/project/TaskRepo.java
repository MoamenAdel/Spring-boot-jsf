package com.research.repositories.project;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.research.entity.Tasks;
import com.research.repositories.BaseRepository;

public interface TaskRepo extends BaseRepository<Tasks> {
	@Query("select t from Tasks t where t.lfmId.id = ?")
	List<Tasks> getTaskByLfm(Long lfm);

	@Query("update Tasks t set retired = 1 , retireDate = CURRENT_TIMESTAMP where t.id = ?")
	@Modifying
	void retireTask(Long id);

}
