package com.controller.dealerservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document("farmer")
public class Farmer {
	
	@Id
	private @Getter @Setter String fid;
	@Field
	private @Getter @Setter String femail;
	@Field
	private @Getter @Setter String fpass;
	@Field
	private @Getter @Setter String fname;
	@Field
	private @Getter @Setter String fcontact;
	@Field
	private @Getter @Setter String flocation;
	@Field
	private @Getter @Setter String fabout;
	@Field
	private @Getter @Setter List<Crops> crops;
	
}
