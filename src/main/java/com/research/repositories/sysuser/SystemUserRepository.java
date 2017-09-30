package com.research.repositories.sysuser;

import com.research.entity.SysUser;
import com.research.repositories.BaseRepository;

public interface SystemUserRepository extends BaseRepository<SysUser> {

	public SysUser findSysUserByUserName(String name);
}
