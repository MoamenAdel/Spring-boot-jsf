package com.research.dto.sysuser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		if (sysUserRolesCollection == null) {
			sysUserRolesCollection = new ArrayList<>();
		}

		List<SysUserRolesDto> temp = (ArrayList<SysUserRolesDto>) sysUserRolesCollection;
		if (temp.size() > 0)
			roleString = temp.get(0).getRole();
		return sysUserRolesCollection;
	}

	public void setSysUserRolesCollection(Collection<SysUserRolesDto> sysUserRolesCollection) {
		this.sysUserRolesCollection = sysUserRolesCollection;
	}

}