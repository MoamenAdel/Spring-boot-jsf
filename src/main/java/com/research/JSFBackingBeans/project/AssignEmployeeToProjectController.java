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
		for(ProjectEmployeesDto ped : thisProjectsEmployees){
			for (EmployeeDto ed : employees){
				if(ed.equals(ped.getEmployeeId())){
					employees.remove(ed);
				}
			}
			
		}

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
			return "AssignEmployees";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Please select a valid User", ""));
			return "AssignEmployees";
		}

	}

	public String create() {
		if (thisProjectsEmployees == null || thisProjectsEmployees.size() == 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Employees List cannot be empty", ""));

		}
		projectEmployeeService.addProjectEmployee(thisProjectsEmployees);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Employees successfully added", ""));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("projectDto", selectedProjectDto);
		return "AssignEmployees";
	}

	public List<EmployeeDto> getEmployees() {
		if (employees == null) {
			employees = new ArrayList<>();
		}
		return employees;
	}

	public String deleteFromThisProjectsEmployees(ProjectEmployeesDto ped) {
		EmployeeDto temp = new EmployeeDto();
		temp = ped.getEmployeeId();
		thisProjectsEmployees.remove(ped);
		if(ped.getId()!=null){
			projectEmployeeService.delete(ped.getId());
		}	
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, temp.getName() + "is removed successfully.", ""));
		employees.add(temp);
		return null;
	}

	public String editEmployeesHours(ProjectEmployeesDto ped) {
		selectedEmployeeDto = ped.getEmployeeId();
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("employeeDto", selectedEmployeeDto);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("projectDto", selectedProjectDto);
		return "AssignHours";
	}

	public String back() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("projectDto", selectedProjectDto);
		return "../project/View?faces-redirect=true";
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
