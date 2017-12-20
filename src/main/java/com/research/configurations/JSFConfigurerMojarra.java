//package com.research.configurations;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
//import org.springframework.beans.factory.config.CustomScopeConfigurer;
//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
//import org.springframework.boot.web.servlet.ErrorPage;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletContextInitializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import com.research.JSFBackingBeans.util.ViewScope;
//
//@Configuration
//public class JSFConfigurer2 implements WebApplicationInitializer {
//
//	public JSFConfigurer2() {
//	}
//
////	@Bean
////	public ServletRegistrationBean servletRegistrationBean() {
////		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
////		servletRegistrationBean.setLoadOnStartup(1);
////		return servletRegistrationBean;
////	}
//	//
//	// @Bean
//	// public FilterRegistrationBean rewriteFilter() {
//	// FilterRegistrationBean rwFilter = new FilterRegistrationBean(new
//	// RewriteFilter());
//	// rwFilter.setDispatcherTypes(
//	// EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST,
//	// DispatcherType.ASYNC, DispatcherType.ERROR));
//	// rwFilter.addUrlPatterns("/*");
//	// return rwFilter;
//	// }
//
//	@Bean
//	public ServletContextInitializer initializer() {
//		return new ServletContextInitializer() {
//			@Override
//			public void onStartup(ServletContext servletContext) throws ServletException {
//				// servletContext.setInitParameter("primefaces.THEME",
//				// "bluesky");
//				servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
//				servletContext.setInitParameter("com.sun.faces.expressionFactory", "com.sun.el.ExpressionFactoryImpl");
//				servletContext.setInitParameter("primefaces.UPLOADER", "commons");
//				// servletContext.addListener("com.sun.faces.config.ConfigureListener");
//				servletContext.setInitParameter("org.springframework.web.context.ContextLoaderListener",
//						Boolean.TRUE.toString());
//			}
//		};
//	}
//
//	@Bean
//	public FilterRegistrationBean FileUploadFilter() {
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		registration.setFilter(new org.primefaces.webapp.filter.FileUploadFilter());
//		registration.setName("PrimeFaces FileUpload Filter");
////		registration.addServletRegistrationBeans(servletRegistrationBean());
//		return registration;
//	}
//
//	@Bean
//	public CustomScopeConfigurer customScopeConfigurer() {
//		CustomScopeConfigurer scopeConfigurer = new CustomScopeConfigurer();
//		scopeConfigurer.addScope("view", viewScope());
//		return scopeConfigurer;
//	}
//
//	@Bean
//	public ViewScope viewScope() {
//		return new ViewScope();
//	}
//
//	@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer() {
//
//		return new EmbeddedServletContainerCustomizer() {
//			@Override
//			public void customize(ConfigurableEmbeddedServletContainer container) {
//
//				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/404.xhtml");
//				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.xhtml");
//				ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/404.xhtml");
//
//				container.addErrorPages(error401Page, error404Page, error500Page);
//			}
//
//		};
//	}
//
//	@Override
//	public void onStartup(ServletContext container) throws ServletException {
//		// Create the 'root' Spring application context
//		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//		rootContext.register(JSFConfigurer2.class);
//
//		// Manage the lifecycle of the root application context
//		container.addListener(new ContextLoaderListener(rootContext));
//
//		// Create the dispatcher servlet's Spring application context
//		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
//		dispatcherContext.register(WebAppInitializer.class);
//
//		// Register and map the dispatcher servlet
//		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher",
//				new DispatcherServlet(dispatcherContext));
//		dispatcher.setLoadOnStartup(1);
//		dispatcher.addMapping("/");
//	}
//
//}