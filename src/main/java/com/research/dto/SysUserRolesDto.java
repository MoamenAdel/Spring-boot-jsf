package com.research.dto;

public class SysUserRolesDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private String name;
	private RoleDto roleId;
	private SysUserDto userId;

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