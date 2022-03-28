package com.controller.dealerservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.controller.dealerservice.model.Dealer;

@Repository
public interface DealerRepository extends MongoRepository<Dealer, String>{
	
	Dealer findByDealeremail(String dealeremail);

}
