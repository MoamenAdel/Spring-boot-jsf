package com.research.dto.project;

import org.primefaces.model.UploadedFile;

import com.research.dto.BaseDto;

public class DocsDTO extends BaseDto {

	private UploadedFile file;
	private Long projectId;
	private ProjectDto projectDTO;
	private String path;
	private boolean isUploaded;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public ProjectDto getProjectDTO() {
		return projectDTO;
	}

	public void setProjectDTO(ProjectDto projectDTO) {
		this.projectDTO = projectDTO;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isUploaded() {
		return isUploaded;
	}

	public void setUploaded(boolean isUploaded) {
		this.isUploaded = isUploaded;
	}

}
