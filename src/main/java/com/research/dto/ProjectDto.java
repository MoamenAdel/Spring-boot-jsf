package com.research.dto;

import java.util.Date;

public class ProjectDto extends BaseDto {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String type;
	private String applicantName;
	private String applicantOrganization;
	private Date submissionDate;
	private Double budget;
	private String abbreviation;
	private Long ProjectTypeId;
	
	public Long getProjectTypeId() {
		return ProjectTypeId;
	}

	public void setProjectTypeId(Long projectTypeId) {
		ProjectTypeId = projectTypeId;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApplicantOrganization() {
		return applicantOrganization;
	}

	public void setApplicantOrganization(String applicantOrganization) {
		this.applicantOrganization = applicantOrganization;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

}
