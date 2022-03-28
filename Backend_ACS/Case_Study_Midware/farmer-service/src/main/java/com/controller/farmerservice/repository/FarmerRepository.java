package com.controller.farmerservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.controller.farmerservice.model.Farmer;

@Repository
public interface FarmerRepository extends MongoRepository<Farmer, String>
{
	Farmer findByFemail(String femail);
}