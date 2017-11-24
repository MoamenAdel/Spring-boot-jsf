package com.research.JSFBackingBeans.sysuser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.research.dto.sysuser.RoleDto;
import com.research.dto.sysuser.SysUserDto;
import com.research.dto.sysuser.SysUserRolesDto;
import com.research.entity.Role;
import com.research.service.interfaces.RoleService;
import com.research.service.interfaces.sysuser.SystemUserRoleService;
import com.research.service.interfaces.sysuser.SystemUserService;

import lombok.Data;

@Scope(value = "view")
@Component(value = "AddSysUserController")
@ManagedBean(name = "AddSysUserController")
@ViewScoped
@Data
public class AddSysUser {

	@Autowired
	SystemUserService sysUserService;

	SystemUserRoleService systemUserRoleService;
	@Autowired
	RoleService roleService;
	@Autowired
	Mapper mapper;

	SysUserRolesDto sysUserRolesDto = new SysUserRolesDto();;
	private static final long serialVersionUID = 1L;
	SysUserDto selected = new SysUserDto();
	long selectedRole = 0;
	List<RoleDto> allRoles = new ArrayList<RoleDto>();

	@PostConstruct
	public void prepateList() {
		for (Role r : roleService.getAll()) {
			allRoles.add(mapper.map(r, RoleDto.class));
		}
		sysUserRolesDto.setRoleId(new RoleDto());
		sysUserRolesDto.setUserId(new SysUserDto());
	}

	public String create() throws ServletException, IOException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		selected.setPassword(passwordEncoder.encode(selected.getPassword()));
		sysUserRolesDto.setUserId(selected);
		sysUserRolesDto.getRoleId().setId(selectedRole);
		sysUserRolesDto.setUsername(selected.getUserName());
		for (RoleDto r : allRoles) {
			if (r.getId() == selectedRole) {
				sysUserRolesDto.setRole(r.getRole());
			}
		}

		selected.getSysUserRolesCollection().add(sysUserRolesDto);
		sysUserService.create(selected);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "System user is successfully Saved", ""));
		selected = new SysUserDto();
		allRoles = new ArrayList<RoleDto>();
		return null;
	}

}
