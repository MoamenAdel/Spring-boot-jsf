package com.research.JSFBackingBeans.sysuser;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.JSFBackingBeans.lazydatamodels.SysUsersLazyDataModel;
import com.research.dto.sysuser.SysUserDto;
import com.research.service.interfaces.sysuser.SystemUserService;

import lombok.Data;

@Scope(value = "view")
@Component(value = "ListSysUserController")
@ManagedBean(name = "ListSysUserController")
@ViewScoped
@Data
public class ListSysUser implements Serializable {

	@Autowired
	SystemUserService sysUserService;


	private static final long serialVersionUID = 1L;
	@Autowired
	SysUsersLazyDataModel sysUsersLazyDataModel;
	
	public void delete(SysUserDto sysUserDto) {
		sysUserService.delete(sysUserDto.getId());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "User successfully Deleted", ""));
	}

}
