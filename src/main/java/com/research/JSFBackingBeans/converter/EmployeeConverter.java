package com.research.JSFBackingBeans.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.research.JSFBackingBeans.project.AssignEmployeeToProjectController;
import com.research.dto.employee.EmployeeDto;
import com.research.service.interfaces.EmployeeService;

/// MOA auto complete trying
	@Service
	public  class EmployeeConverter implements Converter {

		@Autowired
		private EmployeeService employeeService;
		
		@Override
		public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			AssignEmployeeToProjectController controller = (AssignEmployeeToProjectController) facesContext
					.getApplication().getELResolver()
					.getValue(facesContext.getELContext(), null, "AssignEmployeeToProjectController");
			return employeeService.findOne(getKey(value));
		}

		java.lang.Long getKey(String value) {
			java.lang.Long key;
			key = Long.valueOf(value);
			return key;
		}

		String getStringKey(java.lang.Long value) {
			StringBuilder sb = new StringBuilder();
			sb.append(value);
			return sb.toString();
		}

		@Override
		public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
			if (object == null) {
				return null;
			}
			if (object instanceof EmployeeDto) {
				EmployeeDto o = (EmployeeDto) object;
				return getStringKey(o.getId());
			} else {
				throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName()
						+ "; expected type: " + EmployeeDto.class.getName());
			}
		}
	}
