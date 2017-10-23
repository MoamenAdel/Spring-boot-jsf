package com.research.repositories.project;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.research.entity.EmployeeWeek;
import com.research.repositories.BaseRepository;

public interface EmployeeWeekRepo extends BaseRepository<EmployeeWeek> {

	@Query("SELECT ew FROM EmployeeWeek ew WHERE employee.id = ?1 AND month >= ?2 AND month <= ?3")
	List<EmployeeWeek> findWithinDate(Long id, Date startDate, Date endDate);

//	@Query("SELECT ew FROM EmployeeWeek ew WHERE employeeId IN ")
//	List<EmployeeWeek> findAllWithinDate(List<EmployeeDto> employeeDtos,
//			Date startDate, Date endDate);

}
