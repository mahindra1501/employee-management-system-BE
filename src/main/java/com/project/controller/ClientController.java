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
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.ClientModel;
import com.project.repo.ClientRepo;
import com.vaadin.flow.spring.annotation.UIScope;

/**
 * Class Description goes here. Created by mahendrak on 19/09/22
 */
@Component
public class ClientController {

	private static final int initialClientID = 101;

	private final Logger LOG = LoggerFactory.getLogger(getClass());


	@Autowired
	ClientRepo repo;
	
//	@RequestMapping(value = "/Clients", method = RequestMethod.GET)
	public List<ClientModel> showAllClient() {
		LOG.info("<<<<<<---------Getting all Client's.------------->>>>>>>>>");
		return repo.findAll();
	}
//
//	//@RequestMapping(value = "/Client", method = RequestMethod.POST)
	public ClientModel saveClient(ClientModel client) {
		LOG.info("<<<<<<---------Creating Client.------------->>>>>>>>>");
		List<ClientModel> clientList = repo.findAll();

		if (clientList.size() <= 0) {
			client.setCID(initialClientID);
		} else {
			int obj = clientList.size();
			ClientModel Cl = clientList.get(obj - 1);
			int id = Cl.getCID();
			client.setCID(id + 1);
		}
		return repo.save(client);
	}
//
//	//@RequestMapping(value = "/Client/{id}", method = RequestMethod.DELETE)
	public String deleteClient(int id) {
		LOG.info("<<<<<<---------Deleting single Client.------------->>>>>>>>> ");
		repo.deleteById(id);
		return "Client Deleted Successfully";
	}
//
//	//@RequestMapping(value = "/Client/{id}", method = RequestMethod.GET)
	public ClientModel getClient( int id) {
		LOG.info("<<<<<<---------Getting single Client.------------->>>>>>>>> ");
		return repo.findById(id).get();
	}
//
//	//@RequestMapping(value = "/Client/{id}", method = RequestMethod.PUT)
	public ClientModel editClient(ClientModel client, int id) {
		LOG.info("<<<<<<---------Edit single Client.------------->>>>>>>>> ");
		ClientModel clientObj = repo.findById(id).get();
		clientObj.setfName(client.getfName());
		clientObj.setlName(client.getlName());
		clientObj.setDate(client.getDate());
		clientObj.setProject(client.getProject());
		clientObj.setContact(client.getContact());
		return repo.save(clientObj);
	}
	
}		

