package com.research.dto.sysuser;

import com.research.dto.BaseDto;

public class SysUserRolesDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private String name;
	private RoleDto roleId;
	private SysUserDto userId;
	private String username;
	private String role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public SysUserRolesDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoleDto getRoleId() {
		return roleId;
	}

	public void setRoleId(RoleDto roleId) {
		this.roleId = roleId;
	}

	public SysUserDto getUserId() {
		return userId;
	}

	public void setUserId(SysUserDto userId) {
		this.userId = userId;
	}

}