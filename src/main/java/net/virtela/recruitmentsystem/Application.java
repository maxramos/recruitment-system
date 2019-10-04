package net.virtela.recruitmentsystem;

import javax.faces.webapp.FacesServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return servletContext -> servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
	}

	@Bean
	public ServletRegistrationBean<FacesServlet> facesServletRegistrationBean() {
		ServletRegistrationBean<FacesServlet> registrationBean = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
		registrationBean.setLoadOnStartup(1);
		return registrationBean;
	}

}
