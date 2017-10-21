package com.research.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.research.dto.sysuser.RoleDto;
import com.research.entity.Role;
import com.research.repositories.BaseRepository;
import com.research.repositories.sysuser.RoleRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	@Autowired
	RoleRepo roleRepo;
	@Autowired
	DozerBeanMapper mapper;

	@Override
	public BaseRepository getBaseRepo() {
		return roleRepo;
	}

	public RoleDto addRole(RoleDto roleDto) {

		Role role = save(mapper.map(roleDto, Role.class));
		return mapper.map(role, RoleDto.class);
	}

	@Override
	public List<RoleDto> getRolePage(int first, int pageSize) {
		List<RoleDto> roleDtos = new ArrayList<>();
		PageRequest request = new PageRequest(first, pageSize);
		Page<Role> roles = roleRepo.findAll(request);
		roles.forEach((role) -> {
			roleDtos.add(mapper.map(role, RoleDto.class));
		});
		return roleDtos;
	}

}
