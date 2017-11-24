package com.research.service.impl;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.research.dto.sysuser.SysUserRolesDto;
import com.research.entity.SysUserRoles;
import com.research.repositories.BaseRepository;
import com.research.repositories.sysuser.SystemUserRoleRepository;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.sysuser.SystemUserRoleService;
import com.research.service.interfaces.sysuser.SystemUserService;

public class SystemUserRolesServiceImpl extends BaseServiceImpl<SysUserRoles> implements SystemUserRoleService{

	@Autowired
	SystemUserRoleRepository systemUserRolesRepo;
	@Autowired
	DozerBeanMapper mapper;
	@Override
	public BaseRepository getBaseRepo() {
		return systemUserRolesRepo;
	}

	@Override
	public void create(SysUserRolesDto sysUserRoleDto) {
		save(mapper.map(sysUserRoleDto,SysUserRoles.class));
	}

}
