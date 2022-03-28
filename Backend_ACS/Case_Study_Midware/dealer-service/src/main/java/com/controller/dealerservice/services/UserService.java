package com.controller.dealerservice.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.controller.dealerservice.model.Dealer;
import com.controller.dealerservice.repository.DealerRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private DealerRepository dealerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String dealeremail) throws UsernameNotFoundException {
		
		Dealer foundedUser = dealerRepo.findByDealeremail(dealeremail);
		if(foundedUser == null)
		{
			return null;
		}
		else
		{
			String name = foundedUser.getDealeremail();
			String pwd = foundedUser.getDealerpass();
			return new User(name, pwd, new ArrayList<>());
		}
	}
}
