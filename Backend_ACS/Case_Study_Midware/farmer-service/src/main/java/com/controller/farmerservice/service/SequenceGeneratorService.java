package com.controller.farmerservice.service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.controller.farmerservice.model.IdGenerator;

@Service
public class SequenceGeneratorService 
{

	@Autowired
	private MongoOperations mongoOperations;

	public int getSequenceNumber(String sequenceName) 
	{
		Query query = new Query(Criteria.where("seqid").is(sequenceName));
		Update update = new Update().inc("sequence",1);
		IdGenerator idGen= mongoOperations
				.findAndModify(query,
						update, options().returnNew(true).upsert(true),
						IdGenerator.class);

		return !Objects.isNull(idGen) ? idGen.getSequence() : 1;
	}
}