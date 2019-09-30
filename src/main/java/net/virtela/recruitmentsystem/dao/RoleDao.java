package net.virtela.recruitmentsystem.dao;

import java.util.Optional;

import org.springframework.data.repository.RepositoryDefinition;

import net.virtela.recruitmentsystem.model.Role;

@RepositoryDefinition(
		domainClass = Role.class,
		idClass = Long.class)
public interface RoleDao {

	Optional<Role> findByName(String name);

}
