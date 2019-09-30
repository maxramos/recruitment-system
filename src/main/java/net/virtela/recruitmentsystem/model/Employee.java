package net.virtela.recruitmentsystem.model;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "RS_EMPLOYEES")
@Getter
@Setter
public class Employee implements UserDetails {

	private static final long serialVersionUID = -4726884432656488161L;

	@Id
	@Column(name = "EMPLOYEE_ID")
	@SequenceGenerator(name = "RS_EMPLOYEES_SEQ", sequenceName = "RS_EMPLOYEES_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "RS_EMPLOYEES_SEQ")
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private Role role;

	@Column(name = "ENABLED")
	private boolean enabled;

	@Transient
	private String password;

	@Transient
	private boolean accountNonLocked = true;

	@Transient
	private boolean accountNonExpired = true;

	@Transient
	private boolean credentialsNonExpired = true;

	public Employee() {
		super();
	}

	public Employee(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(role);
	}

}
