package com.research.configurations;

import java.util.EnumSet;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.research.JSFBackingBeans.util.ViewScope;

@Configuration
public class JSFConfigurer {

	public JSFConfigurer() {
	}
//	
//	@Bean
//	CommonsMultipartResolver multipartResolver(@Autowired ServletContext context) {
//		return new CommonsMultipartResolver(context);
//	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		FacesServlet servlet = new FacesServlet();
		return new ServletRegistrationBean(servlet, "*.xhtml");
	}
//
//	@Bean
//	public FilterRegistrationBean rewriteFilter() {
//		FilterRegistrationBean rwFilter = new FilterRegistrationBean(new RewriteFilter());
//		rwFilter.setDispatcherTypes(
//				EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.ERROR));
//		rwFilter.addUrlPatterns("/*");
//		return rwFilter;
//	}

	@Bean
	public ServletContextInitializer initializer() {
		return new ServletContextInitializer() {
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				// servletContext.setInitParameter("primefaces.THEME",
				// "bluesky");
				servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
				servletContext.setInitParameter("com.sun.faces.expressionFactory", "com.sun.el.ExpressionFactoryImpl");
				servletContext.setInitParameter("primefaces.UPLOADER", "commons");
			}
		};
	}

	@Bean
	public FilterRegistrationBean FileUploadFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new org.primefaces.webapp.filter.FileUploadFilter());
		registration.setName("PrimeFaces FileUpload Filter");
		registration.addServletRegistrationBeans(servletRegistrationBean());;
		return registration;
	}
	
	@Bean
	public CustomScopeConfigurer customScopeConfigurer(){
		CustomScopeConfigurer scopeConfigurer = new CustomScopeConfigurer();
		scopeConfigurer.addScope("view", viewScope());
		return scopeConfigurer;
	}
	
	@Bean
	public ViewScope viewScope(){
		return new ViewScope();
	}
}
