/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.project.controller;

import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.EmployeeModel;
import com.project.repo.EmployeeRepo;

/**
 * Class Description goes here. Created by mahendrak on 19/09/22
 */
@RestController
public class EmployeeController {

	private static final int initialEmpID = 101;

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	EmployeeRepo repo;

//	@RequestMapping(value = "/Employees", method = RequestMethod.GET)
	public List<EmployeeModel> showAllEmployee() {
		LOG.info("<<<<<<---------Getting all Employee's.------------->>>>>>>>>");
		return repo.findAll();
	}

//	@RequestMapping(value = "/Employee", method = RequestMethod.POST)
	public EmployeeModel saveEmployee(EmployeeModel employee) {
		LOG.info("<<<<<<---------Creating Employee.------------->>>>>>>>>");

		List<EmployeeModel> employeeList = repo.findAll();

		if (employeeList.size() <= 0) {
			employee.setEID(initialEmpID);
		} else {
			int obj = employeeList.size();
			EmployeeModel Cl = employeeList.get(obj - 1);
			int id = Cl.getEID();
			employee.setEID(id + 1);
		}
		return repo.save(employee);
	}

	@RequestMapping(value = "/Employee/{id}", method = RequestMethod.DELETE)
	public String deleteEmployee(@PathVariable int id) {
		LOG.info("<<<<<<---------Deleting single Employee.------------->>>>>>>>> ");
		repo.deleteById(id);
		return "Employee Deleted Successfully";
	}

	@RequestMapping(value = "/Employee/{id}", method = RequestMethod.GET)
	public EmployeeModel getEmployee(@PathVariable int id) {
		LOG.info("<<<<<<---------Getting single Employee.------------->>>>>>>>> ");
		return repo.findById(id).get();
	}

	@RequestMapping(value = "/Employee/{id}", method = RequestMethod.PUT)
	public EmployeeModel editEmployee(@RequestBody EmployeeModel employee, @PathVariable int id) {
		LOG.info("<<<<<<---------Edit single Employee.------------->>>>>>>>> ");
		EmployeeModel employeeObj = repo.findById(id).get();
		employeeObj.setfName(employee.getfName());
		employeeObj.setlName(employee.getlName());
		employeeObj.setDate(employee.getDate());
		employeeObj.setContact(employee.getContact());
		employeeObj.setRole(employee.getRole());
		employeeObj.setOffice(employee.getOffice());
		return repo.save(employeeObj);
	}

}
