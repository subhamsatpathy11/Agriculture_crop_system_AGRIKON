package com.controller.dealerservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.controller.dealerservice.model.CropsList;

public interface CropListRepository extends MongoRepository<CropsList, String>{

}
