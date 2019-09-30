package net.virtela.recruitmentsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Table(name = "RS_ROLES")
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role implements GrantedAuthority {

	public static final String UNASSIGNED_VALUE = "unassigned";

	private static final long serialVersionUID = -5809433210601779232L;

	@Id
	@Column(name = "ROLE_ID")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Override
	public String getAuthority() {
		return "ROLE_" + name.toUpperCase();
	}

}
