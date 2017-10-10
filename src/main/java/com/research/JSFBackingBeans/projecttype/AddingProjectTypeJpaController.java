package com.research.JSFBackingBeans.projecttype;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "test2")
@Scope(value = "view")
@ManagedBean(name = "test2")
@ViewScoped
public class AddingProjectTypeJpaController {

	private String str = "helloworld";
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public AddingProjectTypeJpaController() {
		System.out.println("testing");
	}
	
	
}
