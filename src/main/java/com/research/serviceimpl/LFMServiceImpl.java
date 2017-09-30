package com.research.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.research.dto.LFMDto;
import com.research.entity.Lfm;
import com.research.entity.Project;
import com.research.repositories.BaseRepository;
import com.research.repositories.LFMRepo;
import com.research.service.LFMService;
import com.research.service.ProjectService;

@Service
public class LFMServiceImpl extends BaseServiceImpl<Lfm> implements LFMService {

	@Autowired
	LFMRepo lFMRepo;
	@Autowired
	DozerBeanMapper mapper;
	@Autowired
	ProjectService projectService;

	@Override
	public BaseRepository getBaseRepo() {
		// TODO Auto-generated method stub
		return null;
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
	public LFMDto getLFM() {
		// TODO Auto-generated method stub
		return null;
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

}
