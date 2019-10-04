package net.virtela.recruitmentsystem.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import net.virtela.recruitmentsystem.model.Employee;
import net.virtela.recruitmentsystem.model.Role;

@Component
public class EmployeeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		Employee employee = (Employee) authentication.getPrincipal();
		Role role = employee.getRole();
		redirectStrategy.sendRedirect(request, response, String.format("/%s/index.xhtml", role.getName()));
	}

}
