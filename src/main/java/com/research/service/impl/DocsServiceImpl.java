package com.research.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

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
public class DocsServiceImpl extends BaseServiceImpl<Docs> implements DocsService {

	@Autowired
	private DocsRepository docsRepository;
	@Autowired
	private Environment env;
	@Autowired
	private ProjectService projectService;
	
	@Override
	public List<Docs> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Docs save(Docs entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Docs update(Docs entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Docs getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Docs entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<Docs> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public BaseRepository getBaseRepo() {
		return docsRepository;
	}

	@Override
	public DocsDTO addNewDoc(DocsDTO docDTO) {
		UploadedFile file = docDTO.getFile();
		try (InputStream stream = file.getInputstream()){
			Files.copy(stream, new File(env.getProperty("upload.path") + "/" + docDTO.getProjectDTO().getTitle()
		, file.getFileName()).toPath());
			String path = resolvePath(docDTO);
			Docs doc = new Docs();
			doc.setDocPath(path);
			doc.setIsUploaded(true);
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
		String path = env.getProperty("upload.path") + "/" + docDTO.getFile().getFileName();
		return path;
	}

}
