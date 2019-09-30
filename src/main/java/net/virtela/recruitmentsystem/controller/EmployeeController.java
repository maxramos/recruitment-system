package net.virtela.recruitmentsystem.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.virtela.recruitmentsystem.model.Employee;

@Controller
@Scope("request")
public class EmployeeController {

	private Employee user = new Employee("testUser", "testPassword");

	public Employee getUser() {
		return user;
	}

}
