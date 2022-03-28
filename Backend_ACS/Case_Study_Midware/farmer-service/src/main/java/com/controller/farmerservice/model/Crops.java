package com.controller.farmerservice.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Document("crops")
public class Crops {
	
	@Transient
	public static final String SEQUENCE_NAME = "cropidsequence";
	
	private @Getter @Setter String fid;
	private @Getter @Setter int cropid;
	private @Getter @Setter String cropname;
	private @Getter @Setter String cropimage;
	private @Getter @Setter String cropqlty;
	private @Getter @Setter String croplocation;
	private @Getter @Setter String cropcontact;
	private @Getter @Setter String cropqnty;
	private @Getter @Setter String cropdesc;
	private @Getter @Setter String cropprice;
	
	
}
