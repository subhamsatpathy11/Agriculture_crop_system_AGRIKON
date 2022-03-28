package com.controller.farmerservice.resources;

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

import com.controller.farmerservice.model.AuthenticationFarmerRequest;
import com.controller.farmerservice.model.AuthenticationFarmerResponse;
import com.controller.farmerservice.model.Crops;
import com.controller.farmerservice.model.Farmer;
import com.controller.farmerservice.model.FarmerList;
import com.controller.farmerservice.repository.FarmerRepository;
import com.controller.farmerservice.service.FarmerService;
import com.controller.farmerservice.service.UserService;
import com.controller.farmerservice.utils.JwtUtils;

@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/farmer")
public class FarmerResource {

	@Autowired
	FarmerService farmerService;
	
	@Autowired
	FarmerRepository farmerRepo;
	
	
	  @Autowired 
	  RestTemplate restTemplate;
	  
	  @Autowired 
	  private AuthenticationManager authenticationManager;
	  
	  @Autowired 
	  private UserService userService;
	  
	  @Autowired 
	  private JwtUtils jwtUtils;
	  
	  @PostMapping("/registerFarmer") 
	  private ResponseEntity<?> subscribeClient(@RequestBody AuthenticationFarmerRequest authenticationRequest) {
	  String femail = authenticationRequest.getFemail(); 
	  String fpass = authenticationRequest.getFpass(); String fname =
	  authenticationRequest.getFname(); String fimage =
	  authenticationRequest.getFimage(); String fbank =
	  authenticationRequest.getFbank(); String fpaytmid =
	  authenticationRequest.getFpaytmid(); String faccountno =
	  authenticationRequest.getFaccountno(); String fbankbranch =
	  authenticationRequest.getFbankbranch(); String fcontact =
	  authenticationRequest.getFcontact(); String flocation =
	  authenticationRequest.getFlocation(); String fabout =
	  authenticationRequest.getFabout(); Farmer farmer = new Farmer();
	  farmer.setFemail(femail); farmer.setFpass(fpass); farmer.setFname(fname);
	  farmer.setFimage(fimage); farmer.setFbank(fbank);
	  farmer.setFpaytmid(fpaytmid); farmer.setFaccountno(faccountno);
	  farmer.setFbankbranch(fbankbranch); farmer.setFcontact(fcontact);
	  farmer.setFlocation(flocation); farmer.setFabout(fabout);
	  farmer.setCrops(null); try { farmerRepo.save(farmer); } catch(Exception e) {
	  return ResponseEntity.ok(new AuthenticationFarmerResponse("Error Subscription " +
	  fname)); }
	  
	  return ResponseEntity.ok(new
	  AuthenticationFarmerResponse("Successful Subscription " + fname)); }
	  
	  @PostMapping("/authenticateFarmer") private ResponseEntity<?>authenticateClient(@RequestBody AuthenticationFarmerRequest authenticationRequest)
	  {
	  
	  String femail = authenticationRequest.getFemail(); 
	  String fpass = authenticationRequest.getFpass(); 
	  try {
	  authenticationManager.authenticate(new
	  UsernamePasswordAuthenticationToken(femail, fpass)); } catch(Exception e) {
	  return ResponseEntity.ok(new
	  AuthenticationFarmerResponse("Failed Authentication")); }
	  
	  UserDetails loadedUser = userService.loadUserByUsername(femail); String
	  generateToken = jwtUtils.generateToken(loadedUser);
	  
	  return ResponseEntity.ok(new AuthenticationFarmerResponse(generateToken)); }
	 
	
	
	//ADMIN
	@RequestMapping("/listfarmers")
	public FarmerList getAllCrop()
	{
		return farmerService.getAllCrops();
	}
	
	//FETCHING
	
	//get farmer details
	@GetMapping("/{fid}")
	public Optional<Farmer> getFarmer(@PathVariable("fid") String fid)
	{
		return farmerService.getFarmerDetails(fid);
	}
	
	//display farmers added crops
	@RequestMapping("/{femail}/fetchcrops")
	public List<Crops> findCrops(@PathVariable("femail") String femail)
	{
		return farmerService.findCrops(femail);
	}
	
	//search crops (soon to be deployed with name)
	@RequestMapping("/{fid}/search/{cropid}")
	public Crops searchCrops(@PathVariable("fid") String fid, @PathVariable("cropid") int cropid)
	{
		return farmerService.fetchCrop(fid, cropid);
	}
	
	//ADDING
	
	//add crops for farmers
	@RequestMapping(method = RequestMethod.POST, value = "/{fid}/addcrops")
	public String addCrops(@PathVariable("fid") String fid, @RequestBody Crops crop)
	{
		return farmerService.addCrops(fid, crop);
	}
	
	//UPDATION
	
	//update farmer details
	@RequestMapping(method = RequestMethod.PUT, value = "/update/{fid}")
	public String updateDetails(@RequestBody Farmer farmer, @PathVariable("fid") String fid)
	{
		return farmerService.updateFarmer(farmer, fid);
	}
	
	//update crops for farmers
	@RequestMapping(method = RequestMethod.PUT, value = "/{fid}/updateCrops/{cropid}")
	public String updateCrops(@PathVariable("fid") String fid, @PathVariable("cropid") int cropid, @RequestBody Crops crop)
	{
		return farmerService.updateCrop(fid, cropid, crop);
	}
	
	//DELETION
	
	//let farmer delete its account
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{fid}")
	public String deleteFarmer(@PathVariable("fid") String fid)
	{
		return farmerService.deleteFarmer(fid);
	}
	
	//let farmer delete the crop that he has added
	@RequestMapping(method = RequestMethod.DELETE, value = "/{fid}/delete/{cropid}")
	public String deleteCrop(@PathVariable("fid") String fid, @PathVariable("cropid") int cropid)
	{
		return farmerService.deleteCrop(fid, cropid);
	}
	
	//farmer email fetch
		@GetMapping("/farmer/{femail}")
		public List<Farmer> dealerEmailFetch(@PathVariable("femail") String femail)
		{
			return farmerService.getFarmerDetailsbyEmail(femail);
		}

}
