package com.research.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.research.entity.ProjectEmployees;

public interface ProjectEmployeeRepo extends BaseRepository<ProjectEmployees> {

	@Query("SELECT pe FROM ProjectEmployees pe WHERE projectId.id = ?")
	List<ProjectEmployees> findByProjectrId(Long id);

}
