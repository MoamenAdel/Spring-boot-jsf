package com.research.JSFBackingBeans.project;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import com.research.dto.employee.EmployeeDto;
import com.research.dto.project.ProjectDto;
import com.research.dto.project.ProjectEmployeesDto;
import com.research.service.interfaces.EmployeeService;
import com.research.service.interfaces.ProjectEmployeeService;
import com.research.service.interfaces.ProjectService;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import lombok.Data;

/**
 *
 * @author Moamenovic
 */
@Scope(value = "view")
@Component("AssignEmployeeToProjectController")
@ManagedBean(value = "AssignEmployeeToProjectController")
@ViewScoped
@Data
public class AssignEmployeeToProjectController implements Serializable {

	private static final long serialVersionUID = 9006980830134897009L;
	@Autowired
	ProjectService projectService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	ProjectEmployeeService projectEmployeeService;
	EmployeeDto selectedEmployeeDto;
	List<ProjectEmployeesDto> thisProjectsEmployees;
	ProjectDto selectedProjectDto;
	List<EmployeeDto> employees;
	List<EmployeeDto> autoComplete;
	ProjectEmployeesDto selectedProjectEmployeesDto;

	@PostConstruct
	public void loadData() {
		selectedProjectDto = (ProjectDto) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("projectDto");
		employees = employeeService.getAllEmployees();
		// thisProjectsEmployees = (List<ProjectEmployeesDto>)
		// FacesContext.getCurrentInstance().getExternalContext()
		// .getFlash().get("thisProjectsEmployees");

		setThisProjectsEmployees(
				projectEmployeeService.getSelectedProjectEmployeesByProjectId(selectedProjectDto.getId()));

	}

	public List<EmployeeDto> completeEmps(String name) {
		autoComplete = new ArrayList<>();
		// autoComplete = employees.parallelStream().filter(emp ->
		// emp.getName().contains(name))
		// .collect(Collectors.toList());
		for (EmployeeDto ed : employees) {
			if (ed.getName().contains(name)) {
				autoComplete.add(ed);
			}
		}
		return autoComplete;
	}

	public String addNewEmployeeToProject() {
		if (selectedEmployeeDto != null) {
			ProjectEmployeesDto ped = new ProjectEmployeesDto();
			ped.setEmployeeId(selectedEmployeeDto);
			ped.setProjectId(selectedProjectDto);
			thisProjectsEmployees.add(ped);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisProjectsEmployees",
					thisProjectsEmployees);
			employees.remove(selectedEmployeeDto);
		}
		selectedEmployeeDto = null;
		return "AssignEmployees";
	}

	public String create() {
		projectEmployeeService.addProjectEmployee(thisProjectsEmployees);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Employees successfully added", ""));

		return "AssignEmployees";
	}

	public List<EmployeeDto> getEmployees() {
		if (employees == null) {
			employees = new ArrayList<>();
		}
		return employees;
	}

	public String deleteFromThisProjectsEmployees() {
		thisProjectsEmployees.remove(selectedProjectEmployeesDto);
		employees.add(selectedProjectEmployeesDto.getEmployeeId());
		return "AssignEmployees";
	}

	public String editEmployeesHours(ProjectEmployeesDto ped) {
		selectedEmployeeDto = ped.getEmployeeId();
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("employeeDto", selectedEmployeeDto);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("projectDto", selectedProjectDto);
		return "AssignHours";
	}

	public List<ProjectEmployeesDto> getThisProjectsEmployees() {
		if (thisProjectsEmployees == null) {
			thisProjectsEmployees = new ArrayList<ProjectEmployeesDto>();
		}
		return thisProjectsEmployees;
	}

	public void setThisProjectsEmployees(List<ProjectEmployeesDto> thisProjectsEmployees) {
		this.thisProjectsEmployees = thisProjectsEmployees;
	}

}
