package com.srinidhi.loginapp;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.srinidhi.loginapp.model.Employee;
import com.srinidhi.loginapp.repo.EmployeeRepo;
import com.srinidhi.loginapp.service.EmployeeDAOService;




@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = EmployeeRepo.class )
public class LoginappBasicAuthApplication {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private EmployeeDAOService employeeDAOService;

	public static void main(String[] args) {
		SpringApplication.run(LoginappBasicAuthApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/login").allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH").allowedOrigins("http://localhost:4200");
			}
		};
	}
	
	@Bean
	InitializingBean sendDatabase() {
		return ()->{
			Employee emp = new Employee();
			emp.setDesignation("SDET");
			emp.setEmail("demoacc@gmail.com");
			emp.setEmpName("admin");
			emp.setExperience(6);
			emp.setMobileNo("7259229999");
			emp.setProject("ADMIN_PROJECT");
			emp.setRole("ROLE_ADMIN");
			emp.setUsername("rmgyantra");
			if(!employeeRepo.findByUsername(emp.getUsername()).isPresent())
			employeeDAOService.addEmployee(emp);
		};
	}

}
