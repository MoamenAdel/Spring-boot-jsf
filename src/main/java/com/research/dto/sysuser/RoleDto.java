package com.research.dto.sysuser;


import com.research.dto.BaseDto;

import lombok.Data;
@Data
public class RoleDto extends BaseDto {

	private static final long serialVersionUID = 1L;


	private String role;
	private String description;


}