package com.research.repositories.employee;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.research.entity.Employee;
import com.research.repositories.BaseRepository;

public interface EmployeeRepo extends BaseRepository<Employee>{
	@Query("select e from Employee e where e.name like :name")
	public List<Employee> getAutoCompleteEmployees(@Param("name") String name);
}
