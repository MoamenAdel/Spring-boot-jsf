package com.research.dto;

public class EmployeeDto extends BaseDto {
	private static final long serialVersionUID = 1L;

	private String name;
	private String comments;
	private String criminalStatus;
	private String cv;
	private String hiringNote;
	private String position;
	private String center;
	private String serialNumber;

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCriminalStatus() {
		return criminalStatus;
	}

	public void setCriminalStatus(String criminalStatus) {
		this.criminalStatus = criminalStatus;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public String getHiringNote() {
		return hiringNote;
	}

	public void setHiringNote(String hiringNote) {
		this.hiringNote = hiringNote;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

}
