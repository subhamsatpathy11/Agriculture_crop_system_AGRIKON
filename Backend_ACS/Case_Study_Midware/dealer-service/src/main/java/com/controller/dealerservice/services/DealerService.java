package com.controller.dealerservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.controller.dealerservice.model.Crops;
import com.controller.dealerservice.model.CropsList;
import com.controller.dealerservice.model.Dealer;

import com.controller.dealerservice.model.FarmerList;
import com.controller.dealerservice.repository.CropListRepository;
import com.controller.dealerservice.repository.DealerRepository;

@Service
public class DealerService {
	
	//RestTemplate
	@Autowired
	RestTemplate restTemplate;
	
	//Get the dealer Repo
	@Autowired
	private DealerRepository dealerRepo;
	
	//get the crop list Repo
	@Autowired
	private CropListRepository croplistRepo;
	
	
	//add a dealer (implement in register)
	public String addDealer(Dealer dealer)
	{
		dealerRepo.save(dealer);
		return "Added Dealer";
	}

	//update dealer details
	public String updateDealer(Dealer d, String dealerid)
	{
		dealerRepo.save(d);
		//message
		String success = "Updated Dealer Details";
		return success+" "+dealerid;
	}
	
	//delete Dealer
	public String deleteDealer(String dealerid)
	{
		dealerRepo.deleteById(dealerid);
		String message = "Deleted";
		return message;
	}
		
	//get dealer details
	public Optional<Dealer> getDealer(String dealerid)
	{
		return dealerRepo.findById(dealerid);
	}
	
	//get all crops for the dealer
	public Optional<CropsList> getDealerCrops(String randomid)
	{
		//interconnection
		FarmerList listFarm = restTemplate.getForObject("http://farmer-service/listfarmers", FarmerList.class);
		List<Crops> addedCrops = new ArrayList<>();
		for(int i = 0 ; i < listFarm.getFarmerlist().size(); i++)
		{
			addedCrops.addAll(listFarm.getFarmerlist().get(i).getCrops());
		}
		
		Optional<CropsList> cropList = croplistRepo.findById(randomid);
		CropsList clist = cropList.get();
		clist.setCrops(addedCrops);
		croplistRepo.save(clist);
		
		//return
		return cropList;
		
	}
	
	public Optional<CropsList> getDealercrops(String randomid)
	{
		FarmerList listfarmer = restTemplate.getForObject("http://farmer-service/listfarmers", FarmerList.class);
		List<Crops> addCrops = new ArrayList<>();
		for(int i = 0; i<listfarmer.getFarmerlist().size(); i++)
		{
			addCrops.addAll(listfarmer.getFarmerlist().get(i).getCrops());
		}
		
		Optional<CropsList> croplist = croplistRepo.findById(randomid);
		CropsList cropl = croplist.get();
		cropl.setCrops(addCrops);
		croplistRepo.save(cropl);
		
		return croplist;
	}
	
	public List<Crops> getAllCropsArray(String randomid)
	{
		getDealercrops(randomid);
		return croplistRepo.findById(randomid).get().getCrops();
	}
	
	//get crops
	public List<Crops> getCropsarray(String randomid)
	{
		getDealerCrops(randomid);
		return croplistRepo.findById(randomid).get().getCrops();
	}
	
	

	
	/*
	 * //find by dealer email public List<Dealer> getDealerDetailsbyEmail(String
	 * dealeremail) { List<Dealer> d = dealerRepo.findAll(); List<Dealer> s = new
	 * ArrayList<>();
	 * 
	 * for(Dealer temp: d) { if(temp.getDealeremail().equals(dealeremail)) {
	 * s.add(temp); System.out.println(s); } } return s; }
	 */
}
