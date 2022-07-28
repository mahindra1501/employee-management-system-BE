package com.springrest.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.empRepo.EmpRepos;
import com.springrest.entity.neovaEmp;

@CrossOrigin( origins = "http://localhost:3000")
@RequestMapping("/usermaster")
@RestController
public class empController
{
		@Autowired
		EmpRepos emprepo;	
		int cnt=0;
		
	
	@GetMapping("/getallrecorde")
	public List<neovaEmp> show() 
	{	System.out.println("Working"+cnt++);
	return emprepo.findAll();
	} 
	
	@PostMapping("/newrecorde")
	public neovaEmp save(@RequestBody neovaEmp emp)
	{
		System.out.println("Add Call");
		return emprepo.save(emp);
	}

	@PutMapping("/edit/{id}")	
	public ResponseEntity<neovaEmp> edit(@RequestBody neovaEmp emp,@PathVariable int id) 
	{	System.out.println("edit call");
		neovaEmp oneEmp = emprepo.findById(id).get(); 
		oneEmp.setfName(emp.getfName());
		oneEmp.setlName(emp.getlName());
		oneEmp.setMob(emp.getMob());
		oneEmp.setAddress(emp.getAddress()); 	
		neovaEmp updateEmp = emprepo.save(oneEmp);
		return ResponseEntity.ok(updateEmp);
	}		
	
	@DeleteMapping("/delete/{id}") 
	public List<neovaEmp> delete(@PathVariable int id) 
	{
		emprepo.deleteById(id);
		return emprepo.findAll();
	} 
	
	@GetMapping("/emp/{id}")
	private neovaEmp onerec(@PathVariable int id) 
	{
		System.out.println("1record");
		return emprepo.findById(id).get();	
	}
}