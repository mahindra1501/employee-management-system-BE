/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.project.repo;



import org.springframework.data.mongodb.repository.MongoRepository;


import com.project.model.ClientModel;

/**
 * Class Description goes here.
 * Created by mahendrak on 19/09/22
 */

public interface ClientRepo extends MongoRepository<ClientModel, Integer> {
	
	
}
