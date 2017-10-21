package com.research.repositories.project;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.research.entity.Docs;
import com.research.entity.Project;
import com.research.repositories.BaseRepository;

public interface DocsRepository extends BaseRepository<Docs> {

	Page<Docs> findByProjectId(Project projectId,Pageable pageable);

	@Query("select count(d) from Docs d where d.projectId.id = ?")
	Long countByProject(Long id);
        @Query("select count(d) from Docs d where d.projectId.id = ?") //MOA to Do
	Long countByEmployee(Long id);
        
}
