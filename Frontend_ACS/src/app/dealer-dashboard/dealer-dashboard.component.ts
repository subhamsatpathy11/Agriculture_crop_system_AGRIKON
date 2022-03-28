import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Crops } from '../farmer-dashboard/farmer-dashboard';
import { Farmer } from '../farmer-details/farmer-detail.model';
import { DealerServiceService } from '../services/dealer-service.service';
import { FarmerServiceService } from '../services/farmer-service.service';
import { LoginService } from '../services/login.service';
import { Dealer } from './dealer.model';

@Component({
  selector: 'app-dealer-dashboard',
  templateUrl: './dealer-dashboard.component.html',
  styleUrls: ['./dealer-dashboard.component.css']
})
export class DealerDashboardComponent implements OnInit {
  public loggedIn=false;
  liveCrops:Crops[];
  liveCropsViewObject:Crops = new Crops();
  searchText:any;
  oneFarmer:Farmer;
  dealeremail:any;
  formValue!:FormGroup;
  dealerDetails:Dealer;
  buyObject:Crops;
  dealerObject: Dealer = new Dealer();
  dealerId: any;
  //dealerId: string = "62376af69be2d36022834c6f";

  constructor(private formBuilder: FormBuilder,private farmerService: FarmerServiceService,private route: ActivatedRoute,private router: Router,private loginService: LoginService, private dealerService: DealerServiceService){}

  ngOnInit(): void {
    this.loggedIn=this.loginService.isLoggedIn();
    let did = String(this.route.snapshot.paramMap.get('token') || '');
    this.dealerId = did;
    this.formValue = this.formBuilder.group(
      {
        fid:[''],
        cropid:[],
        cropname:[''],
        cropimage: [''],
        cropqlty:[''],
        croplocation: [''],
        cropcontact:[''],
        cropqnty: [''],
        cropprice: [''],
        cropdesc: ['']
      }
    )
    this.getDealerDetails();
    this.getLiveCrops();
    this.getOneFarmerDetails();
    
  }
  logoutUser()
  {
    this.loginService.logout()
    location.reload()
  }

  onSelect()
  {
    this.router.navigate(['/dealer-dashboard',this.dealerId,this.dealerId]);
  }

 //fetch crops
 getLiveCrops()
 {
 this.dealerService.getLiveCrops().subscribe((data:any[])=> {
   console.log(data);
   this.liveCrops= data;
   //this.liveCrops = Array.of(this.liveCrops); 
 })
}

onBuyRes(data:any)
{
  this.formValue.controls['fid'].setValue(data.fid);
  this.formValue.controls['cropid'].setValue(data.cropid);
  this.formValue.controls['cropprice'].setValue(data.cropprice);
  this.formValue.controls['cropqnty'].setValue(data.cropqnty);
}

//get the farmerDetails of the selected crop
oneRes(data:any)
{
  this.oneFarmer.fid = data.fid;
  this.getOneFarmerDetails();
}

getOneFarmerDetails()
{
  
  this.farmerService.getFarmerDetails(this.liveCropsViewObject.fid).subscribe((data:any)=>
  {
    console.log(data);
    this.oneFarmer=data;
    
  })
}

getDealerDetails()
{
  this.dealerService.getDealerDetails(this.dealerId).subscribe((resp:any)=>
  {
    console.log(resp);
    this.dealerDetails = resp;
    //this.dealerDetails = Array.of(this.dealerDetails);

  })
}

onliveCropsViewRes(res:any)
{
  this.liveCropsViewObject.fid = res.fid;
  this.liveCropsViewObject.cropid = res.cropid;
  this.liveCropsViewObject.cropname = res.cropname;
  this.liveCropsViewObject.cropcontact = res.cropcontact;
  this.liveCropsViewObject.cropdesc = res.cropdesc;
  this.liveCropsViewObject.croplocation = res.croplocation;
  this.liveCropsViewObject.cropprice = res.cropprice;
  this.liveCropsViewObject.cropqlty = res.cropqlty;
  this.liveCropsViewObject.cropqnty = res.cropqnty;
  this.liveCropsViewObject.cropimage = res.cropimage;
}
}