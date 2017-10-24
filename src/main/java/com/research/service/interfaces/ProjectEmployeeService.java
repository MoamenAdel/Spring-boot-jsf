package com.research.service.interfaces;

import java.io.IOException;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import com.research.dto.project.ProjectEmployeesDto;
import com.research.entity.ProjectEmployees;
import com.research.service.BaseService;

public interface ProjectEmployeeService extends BaseService<ProjectEmployees> {
	
	List<ProjectEmployeesDto> addProjectEmployee(List<ProjectEmployeesDto> ProjectEmployeeDto);

	List<ProjectEmployeesDto> getSelectedProjectEmployeesByProjectId(Long projectId);

@Query("SELECT pe FROM ProjectEmployees WHERE projectId.id = ?")
	List<ProjectEmployees> findByProjectId(Long id);

}
