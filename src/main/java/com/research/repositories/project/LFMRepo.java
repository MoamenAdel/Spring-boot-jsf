package com.research.repositories.project;

import org.springframework.data.jpa.repository.Query;

import com.research.entity.Lfm;
import com.research.repositories.BaseRepository;

public interface LFMRepo extends BaseRepository<Lfm>{

	@Query("select l from Lfm l where retired = 0 and l.projectId.id = ?")
	Lfm findByProjectId(Long id);

}
