package com.research.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.research.dto.sysuser.SysUserDto;
import com.research.entity.SysUser;
import com.research.repositories.BaseRepository;
import com.research.repositories.sysuser.SystemUserRepository;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.sysuser.SystemUserService;



@Service
public class SystemUserServiceImpl extends BaseServiceImpl<SysUser> implements SystemUserService{
	@Autowired
	SystemUserRepository systemUserRepo;
	@Autowired
	DozerBeanMapper mapper;
	@Override
	public void create(SysUserDto sysUserDto) {
		save(mapper.map(sysUserDto, SysUser.class));
	}

	@Override
	public BaseRepository getBaseRepo() {
		return systemUserRepo;
	}

}
