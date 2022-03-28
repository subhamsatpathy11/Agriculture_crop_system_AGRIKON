import { Component, OnInit, Input} from '@angular/core';
import { FarmerServiceService } from '../services/farmer-service.service';
import { ActivatedRoute } from '@angular/router';
import { Farmer } from './farmer-detail.model';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Crops } from '../farmer-dashboard/farmer-dashboard';


@Component({
  selector: 'app-farmer-details',
  templateUrl: './farmer-details.component.html',
  styleUrls: ['./farmer-details.component.css']
})
export class FarmerDetailsComponent implements OnInit {

  public fid: any;
  storeFarmerDetails:any;
  dummyprofileimage:any;
  formValue!: FormGroup
  updatedFarmerDetails: Farmer = new Farmer();
  updatedBankDetails: Farmer;

  constructor(private route: ActivatedRoute, private farmerService:FarmerServiceService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.formValue = this.formBuilder.group(
          {
            fid:[''],
            femail:[''],
             fpass:[''],
             fname: [''],
             fcontact:[''],
             fbank:[''],
             fimage:[''],
             faccountno:[''],
             fpaytmid:[''],
             fbankbranch:[''],
             flocation:[''],
             fabout: [''],
             crops:[Crops]
           }
         )
    let id = String(this.route.snapshot.paramMap.get('fid') || '');
    this.fid = id;
    this.getFarmerDetails();
    //this.formfunction
    console.log(this.storeFarmerDetails);
  }




  //form Builder
  // formfunction()
  // {
  //   this.formValue1 = this.formBuilder.group(
  //     {
  //       fid:[''],
  //       femail:[''],
  //       fpass:[''],
  //       fname: [''],
  //       fcontact:[''],
  //       fbank:[''],
  //       fimage:[''],
  //       faccountno:[''],
  //       fpaytmid:[''],
  //       fbankbranch:[''],
  //       flocation:[''],
  //       fabout: ['']
  //     }
  //   )
  // }

  getFarmerDetails()
  {
    this.farmerService.getFarmerDetails(this.fid).subscribe((res:any[])=>
    {
      console.log(res);
      this.storeFarmerDetails = res;
    })
  }

  updateRes()
  { 
    this.dummyprofileimage = this.storeFarmerDetails.fimage
    this.formValue.controls['fid'].setValue(this.storeFarmerDetails.fid);
    this.formValue.controls['fname'].setValue(this.storeFarmerDetails.fname);
    this.formValue.controls['femail'].setValue(this.storeFarmerDetails.femail);
    this.formValue.controls['fabout'].setValue(this.storeFarmerDetails.fabout);
    this.formValue.controls['fcontact'].setValue(this.storeFarmerDetails.fcontact);
    this.formValue.controls['flocation'].setValue(this.storeFarmerDetails.flocation);
    this.formValue.controls['fimage'].setValue(this.storeFarmerDetails.fimage);
    this.formValue.controls['fbank'].setValue(this.storeFarmerDetails.fbank);
    this.formValue.controls['fbankbranch'].setValue(this.storeFarmerDetails.fbankbranch);
    this.formValue.controls['faccountno'].setValue(this.storeFarmerDetails.faccountno);
    this.formValue.controls['fpaytmid'].setValue(this.storeFarmerDetails.fpaytmid);
    
  }

  updateFarmerDetails()
  {
    this.updatedFarmerDetails.fid = this.formValue.value.fid;
    this.updatedFarmerDetails.fname = this.formValue.value.fname;
    this.updatedFarmerDetails.femail = this.formValue.value.femail;
    this.updatedFarmerDetails.fabout = this.formValue.value.fabout;
    this.updatedFarmerDetails.fpass = this.storeFarmerDetails.fpass;
    this.updatedFarmerDetails.crops = this.storeFarmerDetails.crops;
    this.updatedFarmerDetails.fcontact = this.formValue.value.fcontact;
    this.updatedFarmerDetails.flocation = this.formValue.value.flocation;
    this.updatedFarmerDetails.fimage = this.formValue.value.fimage;
    this.updatedFarmerDetails.fbank = this.formValue.value.fbank;
    this.updatedFarmerDetails.fbankbranch = this.formValue.value.fbankbranch;
    this.updatedFarmerDetails.faccountno = this.formValue.value.faccountno;
    this.updatedFarmerDetails.fpaytmid = this.formValue.value.fpaytmid;

    this.farmerService.updateFarmerDetails(this.updatedFarmerDetails.fid, this.updatedFarmerDetails)
    .subscribe((res:any)=>
    {
      console.log(res);
      alert("Profile Updated");
      this.getFarmerDetails();
      
    },
    err=>
    {
      alert("Profile Updated");
      this.getFarmerDetails();
      
    })
  }

  //delete farmer
  deleteFarmer()
  {
    this.farmerService.deleteFarmer(this.storeFarmerDetails.fid).subscribe((res:any)=>
    {
      console.log(res);
      alert("Farmer Deleted");
      this.getFarmerDetails();
    })
  }
}
