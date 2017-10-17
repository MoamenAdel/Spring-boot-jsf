package com.research.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.dozer.DozerBeanMapper;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.research.dto.project.DocsDTO;
import com.research.entity.Docs;
import com.research.repositories.BaseRepository;
import com.research.repositories.project.DocsRepository;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.DocsService;
import com.research.service.interfaces.ProjectService;

@Service
@Transactional
public class DocsServiceImpl extends BaseServiceImpl<Docs> implements DocsService {

	@Autowired
	private DocsRepository docsRepository;
	@Autowired
	private Environment env;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public BaseRepository getBaseRepo() {
		return docsRepository;
	}

	@Override
	public DocsDTO addNewDoc(DocsDTO docDTO) {
		UploadedFile file = docDTO.getFile();
		try (InputStream stream = file.getInputstream()) {
//			File createFolder=new File(env.getProperty("upload.path")+"/"+docDTO.getProjectId());
//			createFolder.mkdirs();
//			File localVersion = new File(
//					env.getProperty("upload.path")	 + docDTO.getProjectId() + "/" + file.getFileName());
//			if (localVersion.exists())
//				return null;
//			Files.copy(stream, localVersion.toPath());
//			String path = resolvePath(docDTO);
			String path = env.getProperty("upload.path")  + "/" + docDTO.getProjectDTO().getTitle()+ "/" + file.getFileName();
			FileUtils.copyInputStreamToFile(stream, new File(path));
			Docs doc = new Docs();
			doc.setDocPath(path);
			doc.setIsUploaded(true);
			doc.setName(file.getFileName());
			doc.setProjectId(projectService.getOne(docDTO.getProjectDTO().getId()));
			doc = this.save(doc);
			docDTO.setId(doc.getId());
			return docDTO;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	private String resolvePath(DocsDTO docDTO) {
		String path = env.getProperty("upload.path") + "/" + docDTO.getProjectId() + "/"
				+ docDTO.getFile().getFileName();
		return path;
	}

	@Override
	public Long getCountByProject(Long id) {
		Long count = docsRepository.countByProject(id);
		return count;
	}

}
