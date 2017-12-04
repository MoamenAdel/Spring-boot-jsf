package com.research.service.interfaces.sysuser;

import java.util.List;

import com.research.dto.sysuser.SysUserDto;
import com.research.entity.SysUser;
import com.research.service.BaseService;

public interface SystemUserService extends BaseService<SysUser> {
	public void create(SysUserDto sysUserDto);
	List<SysUserDto> getSysUserPage(int i, int pageSize);
}
