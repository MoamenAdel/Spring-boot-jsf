package com.research.JSFBackingBeans;

import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

@Component
@ManagedBean
@ViewScoped
public class Test {

	private String message = new String("Hello world");

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String test(){
		System.out.println(message);
		return "/project/Create.xhtml";
	}
}
