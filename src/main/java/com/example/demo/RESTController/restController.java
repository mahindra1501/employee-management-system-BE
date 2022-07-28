package com.example.demo.RESTController;
import java.util.List;
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
import com.example.demo.Entity.emp;
import com.example.demo.repo.empRepo;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/Neova")
public class restController 
{
	@Autowired
	empRepo erepo;
	
	@GetMapping("/Emps")
	public List<emp> emps()
	{
		System.out.println("List of Employee is Fetch ");	
		return erepo.findAll();	
	}
	
	@GetMapping("/Emp/{id}")
	private emp getEmp(@PathVariable Integer id) 
	{
		System.out.println("Employee is Fetch ");
		return erepo.findById(id).get();
	}
	
	@PostMapping("/Emp")
	private emp add(@RequestBody emp e) 
	{
		System.out.println("Employee Is Added");
		return erepo.save(e);
	}
	
	@DeleteMapping("Emp/{id}")
	private String deleteEmp(@PathVariable int id )
	{	 erepo.deleteById(id);
	System.out.println("Employee Is Deleted");
	return "Employee Is Deleted";
	}
	
	@PutMapping("/updateEmp/{id}")
	private ResponseEntity<emp> update(@PathVariable int id , @RequestBody emp emp)
	{
		System.out.println("Update Employee");
		emp updateEmp = erepo.findById(id).get();
		updateEmp.setfName(emp.getfName());
		updateEmp.setlName(emp.getlName());
		updateEmp.setEmail(emp.getEmail());
		updateEmp.setAddress(emp.getAddress());
		updateEmp.setContact(emp.getContact());
		updateEmp.setPosition(emp.getPosition());
		emp saveEmp = erepo.save(updateEmp);
		return ResponseEntity.ok(saveEmp);
	}	
}