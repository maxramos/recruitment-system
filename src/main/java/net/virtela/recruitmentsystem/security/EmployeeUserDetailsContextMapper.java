package net.virtela.recruitmentsystem.security;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

import net.virtela.recruitmentsystem.model.Employee;
import net.virtela.recruitmentsystem.service.EmployeeService;

@Component
public class EmployeeUserDetailsContextMapper implements UserDetailsContextMapper {

	@Autowired
	private EmployeeService employeeService;

	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
		Optional<Employee> result = employeeService.findByUsername(username);
		Employee employee = result.orElseThrow(() -> new RuntimeException("User '" + username + "' not found."));
		return employee;
	}

	@Override
	public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
	}

}
