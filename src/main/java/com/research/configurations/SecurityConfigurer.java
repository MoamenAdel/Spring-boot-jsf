package com.research.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		 auth.jdbcAuthentication().passwordEncoder(passwordEncoder()).dataSource(dataSource)
			.usersByUsernameQuery(
				"select user_name , password,true from research_center.sys_user where user_name=?")
			.authoritiesByUsernameQuery(
				"select username, role from research_center.sys_user_roles where username=?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/javax.faces.resource/**").permitAll()		
		.antMatchers("/project/*").access("hasAuthority('ADMIN')")	
		.and().csrf().disable()
		  .formLogin().loginPage("/Login.xhtml").permitAll()
		  .failureUrl("/Login.xhtml?error")
		  .usernameParameter("username").passwordParameter("password")  
		  .defaultSuccessUrl("/project/List.xhtml")
		  .and().logout().logoutUrl("/logoutURL").logoutSuccessUrl("/Login.xhtml?logout")
		  .and().exceptionHandling().accessDeniedPage("/403.xhtml");
	}
	
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("moamen").password("111").roles("ADMIN");
//	}
		
}
