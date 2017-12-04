package com.research.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

	@Override
	public List<SysUserDto> getSysUserPage(int first, int pageSize) {
		List<SysUserDto> users = new ArrayList<>();
		PageRequest request = new PageRequest(first, pageSize);
		Page<SysUser> sysUsers = systemUserRepo.findAll(request);
		sysUsers.forEach((u) -> {
			
			users.add(mapper.map(u, SysUserDto.class));
		});
		return users;
	}

}
