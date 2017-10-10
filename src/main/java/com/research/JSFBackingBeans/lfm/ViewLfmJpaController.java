package com.research.JSFBackingBeans.lfm;

import com.research.dto.project.LFMDto;
import com.research.dto.project.ProjectDto;
import com.research.dto.project.TaskDTO;
import com.research.dto.project.TasksExpectedOutcomesDto;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.research.service.interfaces.LFMService;
import com.research.service.interfaces.TasksService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Phrase;

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
	private LFMDto selected = new LFMDto();
	private int numberOfMonths, listSize;
	private ProjectDto projectDto;
	DateFormat dt1 = new SimpleDateFormat("d MMM yyyy");

	private List<String> outcomes;
	@Autowired
	private TasksService tasksService;

	private TaskDTO newTaskDTO = new TaskDTO();

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
	
	private void calculateMonths(){
		selected = lfmService.findByProjectId(projectDto.getId());
		List<TaskDTO> taskDTOs = (List<TaskDTO>) selected.getTasksDtoCollection();
		numberOfMonths = 0;
		Date endDate = taskDTOs.get(0).getEndDate();
		Date startDate = taskDTOs.get(0).getStartDate();
		for (TaskDTO taskDTO : taskDTOs) {
			if (endDate.compareTo(taskDTO.getEndDate()) <= 0 ){
				endDate = taskDTO.getEndDate();
			}
			if (startDate.compareTo(taskDTO.getStartDate()) <= 0){
				startDate = taskDTO.getStartDate();
			}
		}
		
		Calendar calStart = new GregorianCalendar();
		calStart.setTime(startDate);
		Calendar calEnd = new GregorianCalendar();
		calEnd.setTime(endDate);
		
		int yearDef = calEnd.get(Calendar.YEAR) - calStart.get(Calendar.YEAR);
		numberOfMonths = yearDef * 12 + calEnd.get(Calendar.MONTH) - calStart.get(Calendar.MONTH);
		
	}

	public String addTask() {

		for (String str : outcomes) {
			TasksExpectedOutcomesDto teod = new TasksExpectedOutcomesDto();
			teod.setExpectation(str);
			newTaskDTO.getTasksExpectedOutcomesCollection().add(teod);
		}
		newTaskDTO.setLfmId(selected.getId());
		newTaskDTO = tasksService.addTask(newTaskDTO);
		newTaskDTO.setFormatedEndDate(dt1.format(newTaskDTO.getEndDate()));
		newTaskDTO.setFormatedStartDate(dt1.format(newTaskDTO.getStartDate()));
		selected.getTasksDtoCollection().add(newTaskDTO);
		calculateMonths();
		newTaskDTO = new TaskDTO();
		return null;
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

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
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

}
