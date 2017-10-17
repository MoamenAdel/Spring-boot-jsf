package com.research.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import com.research.dto.project.LFMDto;
import com.research.dto.project.TaskDTO;
import com.research.dto.project.TasksExpectedOutcomesDto;
import com.research.entity.Lfm;
import com.research.entity.Project;
import com.research.entity.Tasks;
import com.research.entity.TasksExpectedOutcomes;
import com.research.exception.BusinessException;
import com.research.repositories.BaseRepository;
import com.research.repositories.project.LFMRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.LFMService;
import com.research.service.interfaces.ProjectService;
import com.research.service.interfaces.TasksExpectedOutcomesService;
import com.research.service.interfaces.TasksService;

@Service
@Transactional
public class LFMServiceImpl extends BaseServiceImpl<Lfm> implements LFMService {

	@Autowired
	LFMRepo lFMRepo;
	@Autowired
	DozerBeanMapper mapper;
	@Autowired
	ProjectService projectService;
	@Autowired
	private TasksExpectedOutcomesService tasksExpectedOutcomesService;
	@Autowired
	private TasksService tasksService;

	@Override
	public BaseRepository getBaseRepo() {
		return lFMRepo;
	}

	@Override
	public LFMDto addLFMDto(LFMDto lFMDto) {
		Lfm lfm = null;
		try {
			lfm = new Lfm();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// project.setTypeId(projectTypeService.getOne(projectDto.getProjectTypeId()));
		lfm = save(lfm);
		return mapper.map(lfm, lFMDto.getClass());
	}

	@Override
	public Lfm getLFM(Long id) {
		Lfm lfm = lFMRepo.findOne(id);
		if (lfm == null) {
			throw new BusinessException();
		}
		return lfm;
	}

	@Override
	public LFMDto updateLFM(LFMDto lFMDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lfm getLFMByProjectid(Long projectId) {
		return (Lfm) projectService.getOne(projectId).getLfmCollection().toArray()[0];

	}

	@Override
	public LFMDto findByProjectId(Long id) {
		Lfm lfm = lFMRepo.findByProjectId(id);
		if (lfm == null) {
			throw new RuntimeException();
		}
		LFMDto lfmDto = mapper.map(lfm, LFMDto.class);
		// List<Tasks> tasks = (List<Tasks>) lfm.getTasksCollection();
		List<Tasks> tasks = tasksService.getTasksByLfm(lfm.getId());
		List<TaskDTO> taskDTOs = new ArrayList<>();
		DateFormat dt1 = new SimpleDateFormat("d MMM yyyy");
		for (Tasks task : tasks) {
			List<TasksExpectedOutcomesDto> tasksExpectedOutcomesDtos = tasksExpectedOutcomesService
					.findByTaskId(task.getId());
			TaskDTO taskDTO = new TaskDTO();
			// mapper.map(task, taskDTO);
			taskDTO.setId(task.getId());
			taskDTO.setEndDate(task.getEndDate());
			taskDTO.setStartDate(task.getStartDate());
			taskDTO.setDuration(task.getDuration());
			taskDTO.setLfmId(task.getLfmId().getId());
			taskDTO.setName(task.getName());
			taskDTO.setFormatedEndDate(dt1.format(taskDTO.getEndDate()));
			taskDTO.setFormatedStartDate(dt1.format(taskDTO.getStartDate()));
			taskDTO.setTasksExpectedOutcomesCollection(tasksExpectedOutcomesDtos);
			taskDTOs.add(taskDTO);
		}
		lfmDto.setTasksDtoCollection(taskDTOs);

		return lfmDto;
	}

	@Override
	public TaskDTO editTask(TaskDTO selectedTask) {
		tasksExpectedOutcomesService.retireByTaskId(selectedTask.getId());
		tasksService.retireTask(selectedTask.getId());
		selectedTask.setId(null);
		selectedTask = tasksService.addTask(selectedTask);
		return selectedTask;
	}

	@Override
	public void retireTask(Long id) {
		tasksExpectedOutcomesService.retireByTaskId(id);
		tasksService.retireTask(id);
	}

}
