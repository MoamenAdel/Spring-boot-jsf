package com.research.dto.sysuser;

import java.util.Collection;

import com.research.dto.BaseDto;
import com.research.entity.SysUserRoles;

public class SysUserDto extends BaseDto {

	private static final long serialVersionUID = 1L;


	private String userName;
	private String password;

	private Collection<SysUserRoles> sysUserRolesCollection;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<SysUserRoles> getSysUserRolesCollection() {
		return sysUserRolesCollection;
	}

	public void setSysUserRolesCollection(Collection<SysUserRoles> sysUserRolesCollection) {
		this.sysUserRolesCollection = sysUserRolesCollection;
	}

}