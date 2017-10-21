package com.research.JSFBackingBeans.project;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.research.JSFBackingBeans.lazydatamodels.ProjectLazyDataModel;
import com.research.dto.employee.EmployeeDto;
import com.research.dto.project.ProjectDto;
import com.research.dto.project.ProjectEmployeesDto;
import com.research.service.interfaces.EmployeeService;
import com.research.service.interfaces.ProjectService;

import org.springframework.stereotype.Component;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
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
    EmployeeDto selectedEmployeeDto;
    List<ProjectEmployeesDto> thisProjectsEmployees;
    ProjectDto selectedProjectDto;
    List<EmployeeDto> employees;

    @PostConstruct
    public void loadData() {
        selectedProjectDto = (ProjectDto) FacesContext.getCurrentInstance().getExternalContext().getFlash()
                .get("projectDto");
        employees = completeEmps();

    }

    //  public List<EmployeeDto> completeEmps(String name) {
    public List<EmployeeDto> completeEmps() {
//        List<EmployeeDto> temp = employeeService.getAvailableItems(name);
        return employeeService.getAllEmployees();
    }

    public String addNewEmployeeToProject() {
        if (selectedEmployeeDto != null) {
            ProjectEmployeesDto ped = new ProjectEmployeesDto();
            ped.setEmployeeId(selectedEmployeeDto);
            ped.setProjectId(selectedProjectDto);
            thisProjectsEmployees.add(ped);
        }
        selectedEmployeeDto = null;
        return "AssignEmployees";
    }

    /// MOA auto complete tring
    @FacesConverter(forClass = EmployeeDto.class)
    public static class EmployeeDtoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AssignEmployeeToProjectController controller = (AssignEmployeeToProjectController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "AssignEmployeeToProjectController");
            return controller.employeeService.findOne(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof EmployeeDto) {
                EmployeeDto o = (EmployeeDto) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EmployeeDto.class.getName());
            }
        }
    }

    public List<EmployeeDto> getEmployees() {
        if (employees == null) {
            employees = new ArrayList<>();
        }
        return employees;
    }
}
