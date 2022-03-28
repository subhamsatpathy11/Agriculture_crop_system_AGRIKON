import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router'
import { __spreadArrays } from 'tslib';
import { Farmer } from '../farmer-details/farmer-detail.model';
import { FarmerServiceService } from '../services/farmer-service.service';
import { LoginService } from '../services/login.service';
import { Crops } from './farmer-dashboard';

@Component({
  selector: 'app-farmer-dashboard',
  templateUrl: './farmer-dashboard.component.html',
  styleUrls: ['./farmer-dashboard.component.css']
})
export class FarmerDashboardComponent implements OnInit {

  cropsObject: Crops = new Crops();
  public loggedIn=false;
  public email:any;
  cropsEditObject: Crops= new Crops();
  crops: any;
  fetchedbyemail:Farmer[];
  searchText:any;
  farmerDetails: Farmer;
  farmerId:any;
  //farmerId: string ="62388868431ff25ebe08edd6";
  formValue!: FormGroup
  dummycropimage:any;
  cropsViewObject: Crops = new Crops();

  constructor(private route: ActivatedRoute,private loginService: LoginService, private router: Router, private formBuilder: FormBuilder,private farmerService:FarmerServiceService) {
   }

  ngOnInit(): void {
    this.loggedIn=this.loginService.isLoggedIn();
    let fid = String(this.route.snapshot.paramMap.get('tk') || '');
    this.farmerId = fid;
    this.formfunction();  
    this.getCrops();
    this.getFarmerDetails();
    
    }
  logoutUser()
  {
    this.loginService.logout()
    location.reload()
  }

    //form Builder
    formfunction()
    {
      this.formValue = this.formBuilder.group(
        {
          fid:[''],
          cropid:[],
          cropname:[''],
          cropimage: [],
          cropqlty:[''],
          croplocation: [''],
          cropcontact:[''],
          cropqnty: [''],
          cropprice: [''],
          cropdesc: ['']
        }
      )
    }

  
    onSelectionFile(event:any)
    {
      ​​​​​​const reader = new FileReader();
      if(event.target.files && event.target.files.length) 
      {
        ​​​​​​const [file] = event.target.files;
        reader.readAsDataURL(file);
        retrievedImage: this.getCrops;
        reader.onload = () => {​​​​​​
          this.formValue.patchValue({​​​​​​
            cropimage: reader.result
          }​​​​​​);
          //this.getProduct();  
        }​​​​​​
      }​​​​​​ 
    }​​​​​



    //onselect
     onSelect()
     {
       this.router.navigate(['/farmer-dashboard',this.farmerId,this.farmerId]);
     }


    //fetch crops
    getCrops()
    {
      this.farmerService.getDetails(this.farmerId).subscribe((data:any[])=> {
        console.log(data);
        this.crops= data;
        //this.crops = Array.of(this.crops); 
    })
  }
    //add crops
    save()
    {
      this.cropsObject.cropname = this.formValue.value.cropname;
      this.cropsObject.cropqlty = this.formValue.value.cropqlty;
      this.cropsObject.croplocation = this.formValue.value.croplocation;
      this.cropsObject.cropimage = this.formValue.value.cropimage;
      this.cropsObject.cropcontact = this.formValue.value.cropcontact;
      this.cropsObject.cropqnty = this.formValue.value.cropqnty;
      this.cropsObject.cropprice = this.formValue.value.cropprice;
      this.cropsObject.cropdesc = this.formValue.value.cropdesc;

      this.farmerService.addCrops(this.cropsObject, this.farmerId).subscribe((data:any[])=> {
        console.log(data)});
        alert("Crop Added");
        this.formValue.reset();
        this.getCrops();
    }

    onViewRes(data1:any)
    {
      this.cropsViewObject.fid = data1.fid;
      this.cropsViewObject.cropid = data1.cropid;
      this.cropsViewObject.cropname = data1.cropname;
      this.cropsViewObject.cropqlty = data1.cropqlty;
      this.cropsViewObject.cropqnty = data1.cropqnty;
      this.cropsViewObject.cropcontact = data1.cropcontact;
      this.cropsViewObject.croplocation = data1.croplocation;
      this.cropsViewObject.cropprice = data1.cropprice;
      this.cropsViewObject.cropimage = data1.cropimage;
      this.cropsViewObject.cropdesc = data1.cropdesc;
    }

  onEditRes(data:any)
  {
    this.dummycropimage = data.cropimage;
    this.formValue.controls['fid'].setValue(data.fid);
    this.formValue.controls['cropid'].setValue(data.cropid);
    this.formValue.controls['cropname'].setValue(data.cropname);
    this.formValue.controls['cropqlty'].setValue(data.cropqlty);
    this.formValue.controls['cropimage'].setValue(data.cropimage);
    this.formValue.controls['croplocation'].setValue(data.croplocation);
    this.formValue.controls['cropcontact'].setValue(data.cropcontact);
    this.formValue.controls['cropqnty'].setValue(data.cropqnty);
    this.formValue.controls['cropprice'].setValue(data.cropprice);
    this.formValue.controls['cropdesc'].setValue(data.cropdesc);
  }

  cropUpdate()
  {
    this.cropsEditObject.fid = this.formValue.value.fid;
    this.cropsEditObject.cropid = this.formValue.value.cropid;
    this.cropsEditObject.cropname = this.formValue.value.cropname;
    this.cropsEditObject.cropimage = this.formValue.value.cropimage;
    this.cropsEditObject.cropqlty = this.formValue.value.cropqlty;
    this.cropsEditObject.croplocation = this.formValue.value.croplocation;
    this.cropsEditObject.cropcontact = this.formValue.value.cropcontact;
    this.cropsEditObject.cropqnty = this.formValue.value.cropqnty;
    this.cropsEditObject.cropprice = this.formValue.value.cropprice;
    this.cropsEditObject.cropdesc = this.formValue.value.cropdesc;
    //console.log(this.cropsObject)

    this.farmerService.updateCrops(this.cropsEditObject, this.cropsEditObject.fid, this.cropsEditObject.cropid).subscribe((res:any[])=>{
      console.log(res);
      alert("Record Updated");
      this.getCrops();
    },
    err => {
      alert("Record Updated");
      this.getCrops();
  });
    
  }

  //delete
  AdDelete(data:any)
  {
    this.farmerService.deleteAd(data.fid,data.cropid).subscribe((res:any[])=>{
      console.log(res);
      alert("Record Deleted");
      this.getCrops();
    },
    err => {
      alert("Record Deleted");
      this.getCrops();
  });
}

getFarmerDetails()
{
  this.farmerService.getFarmerDetails(this.farmerId).subscribe((res:any)=>
  {
    console.log(res);
    this.farmerDetails = res;
  })
}
}
