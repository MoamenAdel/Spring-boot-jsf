package com.research.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.research.dto.employee.EmployeeDto;
import com.research.dto.project.ProjectDto;
import com.research.dto.project.ProjectEmployeesDto;
import com.research.entity.ProjectEmployees;
import com.research.repositories.BaseRepository;
import com.research.repositories.project.ProjectEmployeeRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.ProjectEmployeeService;

@Service
@Transactional
public class ProjectEmployeeServiceImpl extends BaseServiceImpl<ProjectEmployees> implements ProjectEmployeeService {
	@Autowired
	ProjectEmployeeRepo projectEmployeeRepo;
	@Autowired
	DozerBeanMapper mapper;
	@Autowired
	private Environment env;

	@Override
	public BaseRepository getBaseRepo() {
		return projectEmployeeRepo;
	}

	@Override
	public List<ProjectEmployeesDto> addProjectEmployee(List<ProjectEmployeesDto> projectEmployeeDtos) {
		List<ProjectEmployeesDto> listDtos = new ArrayList<>();
		List<ProjectEmployees> list = new ArrayList<>();
		for (ProjectEmployeesDto projectEmployeeDto : projectEmployeeDtos) {
			ProjectEmployees temp = mapper.map(projectEmployeeDto, ProjectEmployees.class);
			list.add(temp);
			EmployeeDto employeeDto = projectEmployeeDto.getEmployeeId();
			ProjectDto projectDto = projectEmployeeDto.getProjectId();
			if (employeeDto.getCv() != null) {
				File file = new File(employeeDto.getCv());
				if (file.exists()) {
					try (InputStream stream = new FileInputStream(file);) {
						String path = env.getProperty("upload.path") + "/" + "Projects/" + projectDto.getTitle()
								+ "/employees/" + employeeDto.getName() + employeeDto.getId() + "/cv/" + file.getName();
						FileUtils.copyInputStreamToFile(stream, new File(path));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (employeeDto.getCertificate() != null) {
				File file = new File(employeeDto.getCertificate());
				if (file.exists()) {
					try (InputStream stream = new FileInputStream(file)) {
						String path = env.getProperty("upload.path") + "/" + projectDto.getTitle() + "/employees/"
								+ employeeDto.getName() + employeeDto.getId() + "/certificate/" + file.getName();
						FileUtils.copyInputStreamToFile(stream, new File(path));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (employeeDto.getCriminalStatus() != null) {
				File file = new File(employeeDto.getCriminalStatus());
				if (file.exists()) {
					try (InputStream stream = new FileInputStream(file)) {
						String path = env.getProperty("upload.path") + "/" + projectDto.getTitle() + "/employees/"
								+ employeeDto.getName() + employeeDto.getId() + "/criminalStatus/" + file.getName();
						FileUtils.copyInputStreamToFile(stream, new File(path));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		projectEmployeeRepo.save(list);
		return null;
	}

	@Override
	public List<ProjectEmployeesDto> getSelectedProjectEmployeesByProjectId(Long projectId) {
		List<ProjectEmployees> projectEmployees = new ArrayList<ProjectEmployees>();
		List<ProjectEmployeesDto> projectEmployeesDto = new ArrayList<ProjectEmployeesDto>();
		projectEmployees = projectEmployeeRepo.getSelectedProjectEmployeesByProjectId(projectId);
		for (ProjectEmployees pe : projectEmployees) {
			ProjectEmployeesDto tempPED = new ProjectEmployeesDto();

			tempPED = mapper.map(pe, ProjectEmployeesDto.class);
			projectEmployeesDto.add(tempPED);
		}
		return projectEmployeesDto;
	}

	@Override
	public List<ProjectEmployees> findByProjectId(Long id) {
		return projectEmployeeRepo.getSelectedProjectEmployeesByProjectId(id);
	}

}
