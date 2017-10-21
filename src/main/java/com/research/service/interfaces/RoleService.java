package com.research.service.interfaces;

import java.util.List;

import com.research.dto.sysuser.RoleDto;
import com.research.entity.Role;
import com.research.service.BaseService;

public interface RoleService extends BaseService<Role> {

	RoleDto addRole(RoleDto roleDto);

	List<RoleDto> getRolePage(int i, int pageSize);
}
