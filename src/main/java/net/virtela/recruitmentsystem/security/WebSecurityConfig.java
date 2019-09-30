package net.virtela.recruitmentsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LdapContextSource ldapContextSource;

	@Autowired
	private JdbcLdapAuthoritiesPopulator jdbcLdapAuthoritiesPopulator;

	@Autowired
	private EmployeeUserDetailsContextMapper employeeUserDetailsContextMapper;

	/**
	 * There is somewhat an issue when the browser has not been cleared of old cookies.
	 * The result is that authentication fails and the previous requested url is lost.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/", "/index.xhtml").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/editor/**").hasRole("EDITOR")
			.antMatchers("/recruiter/**").hasRole("RECRUITER")
//			.antMatchers("/candidate/**").hasRole("CANDIDATE")
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login.xhtml")
			.permitAll()
			.failureUrl("/login.xhtml?error=true")
			.and()
		.logout()
			.logoutSuccessUrl("/index.xhtml")
			.and()
		.csrf().disable(); // This is required when form login is used otherwise the authentication will not push through.
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
			.antMatchers("/javax.faces.resource/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.ldapAuthentication()
//			.contextSource(ldapContextSource)
//			.userSearchFilter("(&(objectClass=user)(sAMAccountName={0}))")
//			.ldapAuthoritiesPopulator(jdbcLdapAuthoritiesPopulator)
//			.userDetailsContextMapper(employeeUserDetailsContextMapper)
//			.passwordCompare()
//				.passwordEncoder(null) // Forces the use of BindAuthenticator thus the entered raw password will be sent to the LDAP server for matching instead of the app retrieving the user password from the LDAP server.
//				.passwordAttribute("userPassword");

		// For embedded LDAP server only.
		auth.ldapAuthentication()
			.contextSource(ldapContextSource)
			.userSearchBase("ou=Employees,ou=Virtela Users,dc=tn,dc=virtela,dc=cc") // For some reason you still need to include the domain component when using embedded LDAP server even though a base DN has beed declared.
			.userSearchFilter("(&(objectClass=user)(sAMAccountName={0}))")
			.ldapAuthoritiesPopulator(jdbcLdapAuthoritiesPopulator)
			.userDetailsContextMapper(employeeUserDetailsContextMapper)
			.passwordCompare()
				.passwordAttribute("userPassword");
	}

//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		// For testing purposes
//		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//		userDetailsManager.createUser(User.withUsername("rtan").password("test1").roles("UNASSIGNED").build());
//		userDetailsManager.createUser(User.withUsername("mramos").password("test2").roles("ADMIN").build());
//		userDetailsManager.createUser(User.withUsername("rreyles").password("test3").roles("EDITOR").build());
//		userDetailsManager.createUser(User.withUsername("vbarcellano").password("test4").roles("RECRUITER").build());
//		return userDetailsManager;
//	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		// For testing purposes
//		return NoOpPasswordEncoder.getInstance();
//	}

}
