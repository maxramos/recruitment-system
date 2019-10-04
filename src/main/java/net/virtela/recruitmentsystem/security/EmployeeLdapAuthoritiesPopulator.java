package net.virtela.recruitmentsystem.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import net.virtela.recruitmentsystem.model.Employee;
import net.virtela.recruitmentsystem.model.Role;
import net.virtela.recruitmentsystem.service.EmployeeService;

@Component
public class EmployeeLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator {

	@Autowired
	private EmployeeService employeeService;

	@Override
	public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
		Optional<Role> result = employeeService.findRoleByUsername(username);

		if (result.isPresent()) {
			return Collections.singleton(result.get());
		}

		Employee employee = employeeService.saveUnassigned(username);
		return Collections.singleton(employee.getRole());
	}

}
