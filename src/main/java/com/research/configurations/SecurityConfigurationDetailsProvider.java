package com.research.configurations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User;

import com.research.entity.SysUser;
import com.research.entity.SysUserRoles;
import com.research.repositories.sysuser.SystemUserRepository;

@Component("SecurityConfigurationDetailsProvider")
public class SecurityConfigurationDetailsProvider implements UserDetailsService{

	@Autowired
	private SystemUserRepository systemUserRepository;

	@Override
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		System.out.println("Init loadUser" + "  name : " + name);
		SysUser employee = systemUserRepository.findSysUserByUserName(name);
		
		if (employee == null){
			System.out.println("exception");
			throw new RuntimeException();
		}

		List<GrantedAuthority> authorities = getAuthorities(employee);
		return buildUserForAuthentication(employee, authorities);
	}

	private List<GrantedAuthority> getAuthorities(SysUser employee) {
		System.out.println("Init getAuthorities");
		Set<SysUserRoles> roles = (Set<SysUserRoles>) employee.getSysUserRolesCollection();

		List<GrantedAuthority> authorities = new ArrayList<>();
		for (SysUserRoles role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	private UserDetails buildUserForAuthentication(SysUser employee,
			List<GrantedAuthority> authorities) {
		System.out.println("Init buildUser");
		return new User(employee.getUserName(), employee.getPassword(), true,
				true, true, true, authorities);
	}
}
