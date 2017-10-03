package com.research.JSFBackingBeans.lfm;

import com.research.dto.project.LFMDto;
import com.research.dto.project.ProjectDto;
import com.research.dto.project.TaskDTO;
import com.research.dto.project.TasksExpectedOutcomesDto;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.research.service.impl.LFMServiceImpl;
import com.research.service.interfaces.LFMService;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Moamenovic
 */
@Scope(value = "request")
@Component(value = "ViewLfmJpaController")
@ELBeanName(value = "ViewLfmJpaController")
@Join(path = "/lfm", to = "/lfm/View.xhtml")
public class ViewLfmJpaController implements Serializable {
	private static final long serialVersionUID = -9006980830134897009L;
	@Autowired
	LFMService lfmService;
	private LFMDto selected = new LFMDto();
	private int numberOfMonths, listSize;
	private ProjectDto projectDto;
	DateFormat dt1 = new SimpleDateFormat("d MMM yyyy");

	public ViewLfmJpaController() {
		numberOfMonths = 0;

		for (int i = 0; i < 7; i++) {
			TaskDTO taskDto = new TaskDTO(new String("task" + i), dt1.format(new Date()), dt1.format(new Date()), 2);
			TasksExpectedOutcomesDto teo1 = new TasksExpectedOutcomesDto("outcome1");
			TasksExpectedOutcomesDto teo2 = new TasksExpectedOutcomesDto("outcome2");
			TasksExpectedOutcomesDto teo3 = new TasksExpectedOutcomesDto("outcome3");
			taskDto.getTasksExpectedOutcomesCollection().add(teo1);
			taskDto.getTasksExpectedOutcomesCollection().add(teo2);
			taskDto.getTasksExpectedOutcomesCollection().add(teo3);
			selected.getTasksDtoCollection().add(taskDto);
			numberOfMonths += taskDto.getDuration();
			listSize = selected.getTasksDtoCollection().size();

		}
	}
	
	@PostConstruct
	public void init(){
		projectDto = (ProjectDto)FacesContext.getCurrentInstance().getExternalContext().getFlash().get("projectDto");
		if (projectDto == null || projectDto.getId() == null){
			projectDto = new ProjectDto();
			projectDto.setId((long)38);
		}
		selected = lfmService.findByProjectId(projectDto.getId());
		List<TaskDTO> taskDTOs = (List<TaskDTO>) selected.getTasksDtoCollection();
		numberOfMonths = 0;
		for (TaskDTO taskDTO : taskDTOs){
			numberOfMonths += taskDTO.getDuration();
		}
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
		if (projectDto == null){
			projectDto = new ProjectDto();
		}
		return projectDto;
	}

	public void setProjectDto(ProjectDto projectDto) {
		this.projectDto = projectDto;
	}


}
