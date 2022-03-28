package com.controller.farmerservice.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class AuthenticationFarmerRequest {
	
	public AuthenticationFarmerRequest()
	{
		
	}
	//private @Getter @Setter String fid;
	private @Getter @Setter String femail;
	private @Getter @Setter String fpass;
	private @Getter @Setter String fname;
	private @Getter @Setter String fimage;
	private @Getter @Setter String fbank;
	private @Getter @Setter String fpaytmid;
	private @Getter @Setter String faccountno;
	private @Getter @Setter String fbankbranch;
	private @Getter @Setter String fcontact;
	private @Getter @Setter String flocation;
	private @Getter @Setter String fabout;
	private @Getter @Setter List<Crops> crops;
	

}
