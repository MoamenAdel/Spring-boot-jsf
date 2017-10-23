package com.research.repositories.project;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.research.entity.ProjectEmployees;
import com.research.repositories.BaseRepository;

public interface ProjectEmployeeRepo extends BaseRepository<ProjectEmployees> {
	@Query("select pe from ProjectEmployees pe where pe.projectId.id = ?")
	List<ProjectEmployees> getSelectedProjectEmployeesByProjectId(Long id);

	List<ProjectEmployees> findByProjectId(Long id);
}
