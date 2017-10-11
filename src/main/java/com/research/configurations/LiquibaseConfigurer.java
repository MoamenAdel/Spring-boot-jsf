package com.research.configurations;

import java.beans.PropertyVetoException;
import java.util.Properties;

import liquibase.integration.spring.SpringLiquibase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class LiquibaseConfigurer {

	@Autowired
	private Environment env;
	
	public LiquibaseConfigurer() {
		System.out.println(System.getProperty("java.class.path") + "//////////////////////////////////////////////////////////");
		System.out.println("liquibaseReady");
	}
	
	@Bean
	public SpringLiquibase liquibase() throws PropertyVetoException {
	    SpringLiquibase liquibase = new SpringLiquibase();
	    liquibase.setChangeLog(env.getProperty("database.liquibase.configuration"));
	    liquibase.setDataSource(dataSource());
	    liquibase.setDefaultSchema("research_center");
	    return liquibase;
	}
	
	@Bean
	public ComboPooledDataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(env.getProperty("database.driverclass"));
		dataSource.setJdbcUrl(env.getProperty("database.url"));
		dataSource.setUser(env.getProperty("database.username"));
		dataSource.setPassword(env.getProperty("database.password"));
		dataSource.setMaxPoolSize(Integer.valueOf(env.getProperty("database.maxpool")));
		dataSource.setMinPoolSize(Integer.valueOf(env.getProperty("database.maxpool")));
		dataSource.setMaxStatements(Integer.valueOf(env.getProperty("database.maxstatements")));
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { env.getProperty("database.entity.packagepath") });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}
	
	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("database.hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.dialect", env.getProperty("database.hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", env.getProperty("database.hibernate.show_sql"));
		properties.setProperty("hibernate.default_schema","research_center");
		
		return properties;
	}

}
