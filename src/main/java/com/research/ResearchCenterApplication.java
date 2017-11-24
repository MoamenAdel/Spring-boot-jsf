package com.research;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import javax.persistence.EntityManagerFactory;

@PropertySource("research.properties")
@EnableJpaRepositories("com")
@SpringBootApplication
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

	// MOA
//	@Bean(name = "dataSource")
//	public DriverManagerDataSource dataSource() {
//		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//		driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
//		driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/RESEARCHCENTER");
//		driverManagerDataSource.setUsername("postgres");
//		driverManagerDataSource.setPassword("admin");
//		return driverManagerDataSource;
//	}

}
