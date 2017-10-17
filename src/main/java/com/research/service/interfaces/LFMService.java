package com.research.service.interfaces;

import com.research.dto.project.LFMDto;
import com.research.dto.project.TaskDTO;
import com.research.entity.Lfm;

public interface LFMService {
	LFMDto addLFMDto(LFMDto lFMDto);

	Lfm getLFM(Long id);

	LFMDto updateLFM(LFMDto lFMDto);

	Lfm getLFMByProjectid(Long projectId);
	
	Lfm save(Lfm lfm);
	
	LFMDto findByProjectId(Long id);

	TaskDTO editTask(TaskDTO selectedTask);

	void retireTask(Long id);

}
