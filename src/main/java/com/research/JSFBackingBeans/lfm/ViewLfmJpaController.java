package com.research.JSFBackingBeans.lfm;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.dto.project.LFMDto;
import com.research.dto.project.ProjectDto;
import com.research.dto.project.TaskDTO;
import com.research.dto.project.TasksExpectedOutcomesDto;
import com.research.service.interfaces.LFMService;
import com.research.service.interfaces.TasksService;

/**
 *
 * @author Moamenovic
 */
@Scope(value = "view")
@Component(value = "ViewLfmJpaController")
@ManagedBean
@ViewScoped
public class ViewLfmJpaController implements Serializable {

	private static final long serialVersionUID = -9006980830134897009L;
	@Autowired
	LFMService lfmService;
	@Autowired
	private TasksService tasksService;

	private LFMDto selected = new LFMDto();
	private int numberOfMonths;
	private ProjectDto projectDto;
	DateFormat dt1 = new SimpleDateFormat("d MMM yyyy");
	private List<String> outcomes;
	private TaskDTO newTaskDTO = new TaskDTO();
	private TaskDTO selectedTask = new TaskDTO();

	public ViewLfmJpaController() {
	}

	@PostConstruct
	public void init() {
		projectDto = (ProjectDto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("projectDto");
		if (projectDto == null || projectDto.getId() == null) {
			throw new RuntimeException();
		}

		calculateMonths();
	}

	private void calculateMonths() {
		selected = lfmService.findByProjectId(projectDto.getId());
		
		List<TaskDTO> taskDTOs = (List<TaskDTO>) selected.getTasksDtoCollection();
		numberOfMonths = 0;
		if (taskDTOs == null || taskDTOs.isEmpty()) {
			return;
		}
		Date endDate = taskDTOs.get(0).getEndDate();
		Date startDate = taskDTOs.get(0).getStartDate();

		for (TaskDTO taskDTO : taskDTOs) {
			// MOA outcomes string
			for (TasksExpectedOutcomesDto teod : taskDTO.getTasksExpectedOutcomesCollection()) {
				taskDTO.setTasksExpectedOutcomesString(
						taskDTO.getTasksExpectedOutcomesString() + "\r\n\n" + teod.getExpectation());
			}

			if (endDate.compareTo(taskDTO.getEndDate()) <= 0) {
				endDate = taskDTO.getEndDate();
			}
			if (startDate.compareTo(taskDTO.getStartDate()) > 0) {
				startDate = taskDTO.getStartDate();
			}
		}

		Calendar calStart = new GregorianCalendar();
		calStart.setTime(startDate);
		Calendar calEnd = new GregorianCalendar();
		calEnd.setTime(endDate);

		int yearDef = calEnd.get(Calendar.YEAR) - calStart.get(Calendar.YEAR);
		numberOfMonths = yearDef * 12 + calEnd.get(Calendar.MONTH) - calStart.get(Calendar.MONTH);

		for (TaskDTO taskDTO : taskDTOs) {
			calculateTaskMonths(startDate, taskDTO);
		}

	}

	private void calculateTaskMonths(Date projectStart, TaskDTO taskDTO) {
		Date taskStartDate = taskDTO.getStartDate();
		Date taskEndDate = taskDTO.getEndDate();

		Calendar calProject = new GregorianCalendar();
		calProject.setTime(projectStart);
		Calendar calTask = new GregorianCalendar();
		calTask.setTime(taskStartDate);

		int yearDef = calTask.get(Calendar.YEAR) - calProject.get(Calendar.YEAR);
		int monthDef = yearDef * 12 + calTask.get(Calendar.MONTH) - calProject.get(Calendar.MONTH);

		taskDTO.setStartMonth(monthDef);
		calTask.setTime(taskEndDate);

		yearDef = calTask.get(Calendar.YEAR) - calProject.get(Calendar.YEAR);
		monthDef = yearDef * 12 + calTask.get(Calendar.MONTH) - calProject.get(Calendar.MONTH);

		taskDTO.setEndMonth(monthDef);
	}

	public String addTask() {

		for (String str : outcomes) {
			TasksExpectedOutcomesDto teod = new TasksExpectedOutcomesDto();
			teod.setExpectation(str);
			teod.setTaskDTO(newTaskDTO);
			newTaskDTO.getTasksExpectedOutcomesCollection().add(teod);
		}
		newTaskDTO.setLfmId(selected.getId());
		newTaskDTO.setProjectEndDate(projectDto.getSubmissionDate());
		newTaskDTO = tasksService.addTask(newTaskDTO);
		newTaskDTO.setFormatedEndDate(dt1.format(newTaskDTO.getEndDate()));
		newTaskDTO.setFormatedStartDate(dt1.format(newTaskDTO.getStartDate()));
		selected.getTasksDtoCollection().add(newTaskDTO);
		calculateMonths();
		newTaskDTO = new TaskDTO();
		outcomes = new ArrayList<>();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Successful", "Task added successfully"));
		return null;
	}
	
	public void editEventHandler(SelectEvent event){
		TaskDTO dto = (TaskDTO) event.getObject();
		for (TasksExpectedOutcomesDto outcomeDto : dto.getTasksExpectedOutcomesCollection()){
			outcomes.add(outcomeDto.getExpectation());
		}
	}
	
	public String editTask(){
		selectedTask.setTasksExpectedOutcomesCollection(constructOutcomes(outcomes, selectedTask));
		selectedTask.setLfmId(selected.getId());
		selectedTask.setProjectEndDate(projectDto.getSubmissionDate());
		selectedTask = lfmService.editTask(selectedTask);
		selectedTask.setFormatedEndDate(dt1.format(selectedTask.getEndDate()));
		selectedTask.setFormatedStartDate(dt1.format(selectedTask.getStartDate()));
		selected.getTasksDtoCollection().add(selectedTask);
		calculateMonths();
		outcomes = new ArrayList<>();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Successful", "Task edited successfully"));
		return null;
	}
	
	public String deleteTask(Long id){
		lfmService.retireTask(id);
		TaskDTO taskdto = null;
		for (TaskDTO task : selected.getTasksDtoCollection()){
			if (task.getId() == selectedTask.getId()){
				selected.getTasksDtoCollection().remove(task);
				break;
			}
				
		}
		calculateMonths();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Successful", "Task deleted successfully"));
		return null;
	}

	private List<TasksExpectedOutcomesDto> constructOutcomes(List<String> expectations, TaskDTO taskDTO){
		List<TasksExpectedOutcomesDto> outcomesDtos = new ArrayList<>();
		for (String expectation : expectations){
			TasksExpectedOutcomesDto outcomeDto = new TasksExpectedOutcomesDto();
			outcomeDto.setExpectation(expectation);
			outcomeDto.setTaskDTO(taskDTO);
			outcomesDtos.add(outcomeDto);
		}
		return outcomesDtos;
	}
        
        public String back() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("projectDto", projectDto);
		return "../project/View?faces-redirect=true";
	}
	
	public void clearOutcomes(CloseEvent event){
		outcomes = new ArrayList<>();
	}
	
	public LFMDto getSelected() {
		return selected;
	}

	public void setSelected(LFMDto selected) {
		this.selected = selected;
	}

	public int getNumberOfMonths() {
		return numberOfMonths;
	}

	public void setNumberOfMonths(int numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
	}

	public ProjectDto getProjectDto() {
		if (projectDto == null) {
			projectDto = new ProjectDto();
		}
		return projectDto;
	}

	public void setProjectDto(ProjectDto projectDto) {
		this.projectDto = projectDto;
	}

	public TaskDTO getNewTaskDTO() {
		return newTaskDTO;
	}

	public void setNewTaskDTO(TaskDTO newTaskDTO) {
		this.newTaskDTO = newTaskDTO;
	}

	public List<String> getOutcomes() {

		if (outcomes == null) {
			outcomes = new ArrayList<String>();
		}
		return outcomes;
	}

	public void setOutcomes(List<String> outcomes) {
		this.outcomes = outcomes;
	}

	public TaskDTO getSelectedTask() {
		return selectedTask;
	}

	public void setSelectedTask(TaskDTO selectedTask) {
		this.selectedTask = selectedTask;
	}

}
