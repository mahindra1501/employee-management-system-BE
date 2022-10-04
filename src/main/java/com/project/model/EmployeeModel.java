/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.project.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class Description goes here.
 * Created by mahendrak on 19/09/22
 */

@Document( value = "employee")
public class EmployeeModel {

    @Id
    int EID;
    String fName;
    String lName;
    String role;
    String date;
    String contact;
    String office;
	public int getEID() {
		return EID;
	}
	public void setEID(int eID) {
		EID = eID;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	@Override
	public String toString() {
		return "EmployeeModel [EID=" + EID + ", fName=" + fName + ", lName=" + lName + ", role=" + role + ", date="
				+ date + ", contact=" + contact + ", office=" + office + "]";
	}
	public EmployeeModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeModel(int eID, String fName, String lName, String role, String date, String contact, String office) {
		super();
		EID = eID;
		this.fName = fName;
		this.lName = lName;
		this.role = role;
		this.date = date;
		this.contact = contact;
		this.office = office;
	}

    
}
