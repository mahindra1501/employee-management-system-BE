package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class emp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int empId;
	String fName, lName;
	String email;
	long contact;
	String address;
	String position;

	public emp() {
		super();
	}

	public emp(int empId, String fName, String lName, String email, long contact, String address, String position) {
		super();
		this.empId = empId;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.contact = contact;
		this.address = address;
		this.position = position;
	}

	@Override
	public String toString() {
		return "emp [empId=" + empId + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", contact="
				+ contact + ", address=" + address + ", position=" + position + "]";
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}


}
