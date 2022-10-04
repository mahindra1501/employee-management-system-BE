package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
@Theme(value = "SpringWithMongoBd") 
@SpringBootApplication
public class SpringWithMongoBdApplication extends SpringBootServletInitializer implements AppShellConfigurator { 
	public static void main(String[] args) {
		SpringApplication.run(SpringWithMongoBdApplication.class, args);
		
	}

}