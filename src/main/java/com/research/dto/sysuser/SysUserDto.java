package com.research.dto.sysuser;

import java.util.ArrayList;
import java.util.Collection;

import com.research.dto.BaseDto;

import lombok.Data;

@Data
public class SysUserDto extends BaseDto {

	private static final long serialVersionUID = 1L;


	private String userName;
	private String password;
	private String roleString;
	private Collection<SysUserRolesDto> sysUserRolesCollection;
	public Collection<SysUserRolesDto> getSysUserRolesCollection() {
		if(sysUserRolesCollection == null){
			sysUserRolesCollection = new ArrayList<>();
		}
		return sysUserRolesCollection;
	}
	public void setSysUserRolesCollection(Collection<SysUserRolesDto> sysUserRolesCollection) {
		this.sysUserRolesCollection = sysUserRolesCollection;
	}

		
}