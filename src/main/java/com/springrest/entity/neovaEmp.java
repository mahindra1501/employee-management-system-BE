package com.springrest.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class neovaEmp
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int empId;
	String fName,lName;
	long mob;
	String Address;
	
	public neovaEmp() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public neovaEmp(int empId, String fName, String lName, long mob, String address) {
		super();
		this.empId = empId;
		this.fName = fName;
		this.lName = lName;
		this.mob = mob;
		Address = address;
	}

	@Override
	public String toString() {
		return "neovaEmp [empId=" + empId + ", fName=" + fName + ", lName=" + lName + ", mob=" + mob + ", Address="
				+ Address + "]";
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public long getMob() {
		return mob;
	}

	public void setMob(long mob) {
		this.mob = mob;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
	
	
	
}
