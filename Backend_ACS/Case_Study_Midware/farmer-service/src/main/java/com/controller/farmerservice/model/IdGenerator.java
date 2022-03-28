package com.controller.farmerservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document("cropsequence")
public class IdGenerator {
	
	private @Getter @Setter String seqid;
	private @Getter @Setter int sequence;

}
