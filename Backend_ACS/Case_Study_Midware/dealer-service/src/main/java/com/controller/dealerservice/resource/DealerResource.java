package com.controller.dealerservice.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.controller.dealerservice.model.AuthenticationRequest;
import com.controller.dealerservice.model.AuthenticationResponse;
import com.controller.dealerservice.model.Crops;
import com.controller.dealerservice.model.CropsList;
import com.controller.dealerservice.model.Dealer;
import com.controller.dealerservice.model.DealerList;
import com.controller.dealerservice.repository.DealerRepository;
import com.controller.dealerservice.services.DealerService;
import com.controller.dealerservice.services.UserService;
import com.controller.dealerservice.utils.JwtUtils;


@RestController
@CrossOrigin
//@RequestMapping("/dealer")
public class DealerResource {
	
	@Autowired
	DealerService dealerService;
	
	@Autowired
	DealerRepository dealerRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/register")
	private ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest)
	{
		String dealeremail = authenticationRequest.getDealeremail();
		String dealerpass = authenticationRequest.getDealerpass();
		String dealername = authenticationRequest.getDealername();
		String dealerphone = authenticationRequest.getDealerphone();
		String dealerlocation = authenticationRequest.getDealerlocation();
		String dealerabout = authenticationRequest.getDealerabout();
		String dealerbank = authenticationRequest.getDealerbank();
		String dealerimage = authenticationRequest.getDealerimage();
		String dealerbranch = authenticationRequest.getDealerbranch();
		String dealeraccountno = authenticationRequest.getDealeraccountno();
		String dealerpaytmid = authenticationRequest.getDealerpaytmid();
		
		Dealer dealer = new Dealer();
		dealer.setDealeremail(dealeremail);
		dealer.setDealerpass(dealerpass);
		dealer.setDealername(dealername);
		dealer.setDealerphone(dealerphone);
		dealer.setDealerimage(dealerimage);
		dealer.setDealerlocation(dealerlocation);
		dealer.setDealerabout(dealerabout);
		dealer.setDealerbank(dealerbank);
		dealer.setDealerbranch(dealerbranch);
		dealer.setDealeraccountno(dealeraccountno);
		dealer.setDealerpaytmid(dealerpaytmid);
		
		try {
			dealerRepo.save(dealer);
		}
		catch(Exception e)
		{
			return ResponseEntity.ok(new AuthenticationResponse("Error Subscription " + dealername));
		}
		
		return ResponseEntity.ok(new AuthenticationResponse("Successful Subscription " + dealername));
	}
	
	@PostMapping("/authenticate")
	private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest)
	{

		String dealeremail = authenticationRequest.getDealeremail();
		String dealerpass = authenticationRequest.getDealerpass();
		try
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dealeremail, dealerpass));
		}
		catch(Exception e)
		{
			return ResponseEntity.ok(new AuthenticationResponse("Failed Authentication"));
		}
		
		UserDetails loadedUser = userService.loadUserByUsername(dealeremail);
		String generateToken = jwtUtils.generateToken(loadedUser);
		
		return ResponseEntity.ok(new AuthenticationResponse(generateToken));
	}
	
	//alldealers
	
	//view the dealer details
	@RequestMapping("/{dealerid}")
	public Optional<Dealer> fetchDealer(@PathVariable("dealerid") String dealerid)
	{
		return dealerService.getDealer(dealerid);
	}
	
	//update Dealer details
	@RequestMapping(method = RequestMethod.PUT, value = "/update/{dealerid}")
	public String updateDetails(@RequestBody Dealer dealer, @PathVariable("dealerid") String dealerid)
	{
		return dealerService.updateDealer(dealer, dealerid);
	}
	
	//delete Dealer Details
	@RequestMapping(method = RequestMethod.DELETE, value="/delete/{dealerid}")
	public String deleteDetails(@PathVariable("dealerid") String dealerid)
	{
		return dealerService.deleteDealer(dealerid);
	}
	
	//view the dealer crops
	@RequestMapping("/dealercrops/{randomid}")
	public Optional<CropsList> fetchDealerCrops(@PathVariable("randomid") String randomid)
	{
		return dealerService.getDealerCrops(randomid);
	}
	
	@RequestMapping("/dcrops/{randomid}")
	public List<Crops> fetchCropsArray(@PathVariable("randomid") String randomid)
	{
		return dealerService.getAllCropsArray(randomid);
	}
	
	//view all crops
	@RequestMapping("/dealerCrops/{randomid}")
	public List<Crops> fetchCrops(@PathVariable("randomid") String randomid)
	{
		return dealerService.getCropsarray(randomid);
	}
	
	@GetMapping("/listfarmers")
	public List<Object> getStudents() {
	Object[] objects = restTemplate.getForObject("http://farmer-service/listfarmers", Object[].class);
	return Arrays.asList(objects);
	}
	
	/*
	 * //dealer email fetch
	 * 
	 * @GetMapping("/dealer/{dealeremail}") public List<Dealer>
	 * dealerEmailFetch(@PathVariable("dealeremail") String dealeremail) { return
	 * dealerService.getDealerDetailsbyEmail(dealeremail); }
	 */

}
