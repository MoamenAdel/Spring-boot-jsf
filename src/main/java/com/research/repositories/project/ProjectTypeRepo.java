package com.research.repositories.project;

import org.springframework.data.jpa.repository.Query;

import com.research.entity.ProjectTypes;
import com.research.repositories.BaseRepository;

public interface ProjectTypeRepo extends BaseRepository<ProjectTypes>{

	@Query("select t from ProjectTypes t where retired = 0 and t.type = ?")
	ProjectTypes findByType(String type);

	@Query("select count(*) from ProjectTypes p")
	long count();
}
