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

@Document( value = "client")
public class ClientModel {

	@Id
    int CID;
    String fName;
    String lName;
    String project;
    String date;
    String contact;

    public ClientModel() {
    }

    public ClientModel(int CID, String fName, String lName, String project, String date, String contact) {
        this.CID = CID;
        this.fName = fName;
        this.lName = lName;
        this.project = project;
        this.date = date;
        this.contact = contact;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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

    @Override public String toString() {
        return "ClientModel{" +
                "CID=" + CID +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", project='" + project + '\'' +
                ", data='" + date + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
