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
@Document("croplist")
public class CropsList {

	@Id
	private @Getter @Setter String randomid;
	@Field
	private @Getter @Setter List<Crops> crops;
	
}
