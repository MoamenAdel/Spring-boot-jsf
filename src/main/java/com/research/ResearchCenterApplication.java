package com.research;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@PropertySource("classpath:/com/research/research.properties")
@EnableJpaRepositories("com")
@SpringBootApplication
@EnableAutoConfiguration
public class ResearchCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResearchCenterApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
	 
	    return new EmbeddedServletContainerCustomizer() {
	        @Override
	        public void customize(ConfigurableEmbeddedServletContainer container) {
	 
	            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/404.xhtml");
	            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.xhtml");
	            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/404.xhtml");
	 
	            container.addErrorPages(error401Page, error404Page, error500Page);
	        }

		
	    };
	}

//	// MOA
//	@Bean(name = "dataSource2")
//	public DriverManagerDataSource dataSource() {
//		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//		driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
//		driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/RESEARCHCENTER");
//		driverManagerDataSource.setUsername("postgres");
//		driverManagerDataSource.setPassword("admin");
//		return driverManagerDataSource;
//	}

}
