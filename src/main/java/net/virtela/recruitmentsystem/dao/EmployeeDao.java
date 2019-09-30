package net.virtela.recruitmentsystem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import net.virtela.recruitmentsystem.model.Employee;
import net.virtela.recruitmentsystem.model.Role;

@RepositoryDefinition(
		domainClass = Employee.class,
		idClass = Long.class)
public interface EmployeeDao {

	Optional<Employee> findByUsername(String username);

	@Query("SELECT e.role FROM Employee e WHERE e.username = :username")
	Optional<Role> findRoleByUsername(@Param("username") String username);

	Employee saveAndFlush(Employee employee);

}
