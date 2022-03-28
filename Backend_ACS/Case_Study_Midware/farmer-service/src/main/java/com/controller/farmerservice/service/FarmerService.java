package com.controller.farmerservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controller.farmerservice.model.Crops;
import com.controller.farmerservice.model.Farmer;
import com.controller.farmerservice.model.FarmerList;
import com.controller.farmerservice.repository.FarmerRepository;

@Service
public class FarmerService {
	
	//Getting the repository functionality (MongoDB)
	@Autowired
	FarmerRepository farmerRepo;
	
	@Autowired
	private SequenceGeneratorService service;
	
	//public FarmerService(FarmerService farmerService) {
		//this.farmerService = farmerService;
	//}
	
	//FETCH CROPS FROM ALL FARMERS
	
	public FarmerList getAllCrops()
	{
		List<Farmer> allCrops = farmerRepo.findAll();
		FarmerList listfarm = new FarmerList();
		listfarm.setFarmerlist(allCrops);
		
		//return
		return listfarm;
	}
	
	//FARMER OPERATIONS
	
	//add a farmer
	public String addFarmer(Farmer f)
	{
		f.setCrops(null);
		f.setFabout(null);
		farmerRepo.save(f);
		return "Added Farmer";
	}
	
	//get farmer details
	public Optional<Farmer> getFarmerDetails(String fid)
	{
		return farmerRepo.findById(fid);
	}
	
	//update farmer details
	public String updateFarmer(Farmer f, String fid)
	{
		farmerRepo.save(f);
		//message
		String success = "Updated Farmer Details";
		return success+" "+fid;
	}
	
	//delete the farmer
	public String deleteFarmer(String fid)
	{
		farmerRepo.deleteById(fid);
		String message = "Deleted";
		return message;
	}
	
	//CROP OPERATIONS
	
	//fetch crops for farmers
	public Crops fetchCrop(String fid, int cropid)
	{
		Crops nullobject = new Crops(); //null object
		for(int i = 0; i < farmerRepo.findById(fid).get().getCrops().size(); i++)
		{
			Crops storecrops = farmerRepo.findById(fid).get().getCrops().get(i);
			if(storecrops.getCropid() == cropid)  //if(fob.getSource().equals(source) && fob.getDest().equals(dest))
			{
				return storecrops;
			}
		}
		return nullobject;
	}
	
	//Add crops by farmers
	public String addCrops(String fid, Crops crop)
	{
		crop.setFid(fid);
		crop.setCropid(service.getSequenceNumber(Crops.SEQUENCE_NAME));
		List<Crops> posts = farmerRepo.findById(fid).get().getCrops();
		//validate
		if(posts == null)
		{
			posts = new ArrayList<>();
		}
		//else add a crop
		posts.add(crop);
		//get the farmer details and set the crop for it
		Farmer f = farmerRepo.findById(fid).get();
		f.setCrops(posts);
		farmerRepo.save(f);
		
		return "Crop Added";
	}
	
	//find all crops added by the farmer
	public List<Crops> findCrops(String fid)
	{
		return farmerRepo.findById(fid).get().getCrops();
		
		//search functionality yet to be deployed
	}
	
	//update the crop for the farmer
	public String updateCrop(String fid, int cropid, Crops crop)
	{
		for(int i = 0; i < farmerRepo.findById(fid).get().getCrops().size(); i++)
		{
			Crops cropidobject = farmerRepo.findById(fid).get().getCrops().get(i);
			if(cropidobject.getCropid() == cropid)
			{
				//cross check this
				//cropidobject.setCropid(crop.getCropid());
				cropidobject.setCropname(crop.getCropname());
				cropidobject.setCropimage(crop.getCropimage());
				cropidobject.setCropqlty(crop.getCropqlty());
				cropidobject.setCroplocation(crop.getCroplocation());
				cropidobject.setCropcontact(crop.getCropcontact());
				cropidobject.setCropqnty(crop.getCropqnty());
				cropidobject.setCropprice(crop.getCropprice());
				cropidobject.setCropdesc(crop.getCropdesc());
				
				//instantiate the farmer class
				Farmer farmer = farmerRepo.findById(fid).get();
				farmer.getCrops().set(i, cropidobject);
				
				//update
				farmerRepo.save(farmer);
				
				//message success
				return "Updated Successfully";
			}
		}
		//message failure
		return "Update Failed";
	}
	
	//delete crops added by farmer
	public String deleteCrop(String fid,int cropid)
	{
		for(int i = 0; i < farmerRepo.findById(fid).get().getCrops().size(); i++)
		{
			Crops cropobject = farmerRepo.findById(fid).get().getCrops().get(i);
			if(cropobject.getCropid() == cropid)
			{
				//Crops farm = farmerRepo.findById(fid).get().getCrops().remove(i);
				//Farmer farm = farmerRepo.findById(fid).get().getCrops().remove(i);		
				Farmer farm = farmerRepo.findById(fid).get();
				Crops crop = farm.getCrops().remove(i);
				
				//update
				farmerRepo.save(farm);
				
				//message Success
				String success = "Deleted Successfully";
				return success;
			}
		}
		
		//message failure
		String failure = "Delete failed";
		return failure;
	}
	
	//find by farmer email
		public List<Farmer> getFarmerDetailsbyEmail(String femail)
		{
			List<Farmer> d = farmerRepo.findAll();
			List<Farmer> s = new ArrayList<>();
			
			for(Farmer temp: d)
			{
				if(temp.getFemail().equals(femail))
				{
					s.add(temp);
					System.out.println(s);
				}
			}
			return s;
		}
}





/*		//alternate
Farmer farm = farmerRepo.findById(fid).get();
Crops crop = farm.getCrops().remove(i);
*/