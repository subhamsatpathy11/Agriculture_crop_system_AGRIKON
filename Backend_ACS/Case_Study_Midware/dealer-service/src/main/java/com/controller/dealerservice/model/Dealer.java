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
@Document("dealers")
public class Dealer {
	
	@Id
	private @Getter @Setter String dealerid;
	@Field 	//username
	private @Getter @Setter String dealeremail;
	@Field	//password
	private @Getter @Setter String dealerpass;
	@Field
	private @Getter @Setter String dealername;
	@Field
	private @Getter @Setter String dealerphone;
	@Field
	private @Getter @Setter String dealerimage;
	@Field
	private @Getter @Setter String dealerlocation;
	@Field
	private @Getter @Setter String dealerabout;
	@Field
	private @Getter @Setter String dealerbank;
	@Field
	private @Getter @Setter String dealerbranch;
	@Field
	private @Getter @Setter String dealeraccountno;
	@Field
	private @Getter @Setter String dealerpaytmid;
	//@Field
	//private @Getter @Setter List<Crops> crops;
}
