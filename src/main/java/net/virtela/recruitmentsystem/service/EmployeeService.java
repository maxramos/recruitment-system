package net.virtela.recruitmentsystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.virtela.recruitmentsystem.dao.EmployeeDao;
import net.virtela.recruitmentsystem.dao.RoleDao;
import net.virtela.recruitmentsystem.model.Employee;
import net.virtela.recruitmentsystem.model.Role;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private RoleDao roleDao;

	public Optional<Employee> findByUsername(String username) {
		return employeeDao.findByUsername(username);
	}

	public Optional<Role> findRoleByUsername(String username) {
		return employeeDao.findRoleByUsername(username);
	}

	public Employee saveUnassigned(String username) {
		Optional<Role> result = roleDao.findByName(Role.UNASSIGNED_VALUE);
		Role role = result.orElseThrow(() -> new RuntimeException("Role 'unassigned' not found."));

		Employee employee = new Employee();
		employee.setUsername(username);
		employee.setRole(role);
		employee.setEnabled(true);

		return employeeDao.saveAndFlush(employee);
	}

}
