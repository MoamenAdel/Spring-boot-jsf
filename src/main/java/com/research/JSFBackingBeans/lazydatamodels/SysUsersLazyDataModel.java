package com.research.JSFBackingBeans.lazydatamodels;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.research.dto.sysuser.SysUserDto;
import com.research.service.interfaces.sysuser.SystemUserService;

@Component
@Lazy
public class SysUsersLazyDataModel extends LazyDataModel<SysUserDto> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	SystemUserService systemUserService;

	@PostConstruct
	private void init() {
		setRowCount(systemUserService.count().intValue());
	}

	@Override
	public List<SysUserDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return systemUserService.getSysUserPage(first / pageSize, pageSize);
	}
}
