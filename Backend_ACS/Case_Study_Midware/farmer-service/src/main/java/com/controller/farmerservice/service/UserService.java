package com.controller.farmerservice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.controller.farmerservice.model.Farmer;
import com.controller.farmerservice.repository.FarmerRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	FarmerRepository farmerRepo;

	@Override
	public UserDetails loadUserByUsername(String femail) throws UsernameNotFoundException {
		
		Farmer foundFemail = farmerRepo.findByFemail(femail);
		if(foundFemail == null)
		{
			return null;
		}
		else
		{
			String name = foundFemail.getFemail();		//if error comes, change this file
			String pwd = foundFemail.getFpass();
			return new User(name, pwd, new ArrayList<>());
		}
	}

}

/*Farmer foundFemail = farmerRepo.findByFemail(femail);*/
