import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Dealer } from '../dealer-dashboard/dealer.model';
import { DealerServiceService } from '../services/dealer-service.service';

@Component({
  selector: 'app-dealer-details',
  templateUrl: './dealer-details.component.html',
  styleUrls: ['./dealer-details.component.css']
})
export class DealerDetailsComponent implements OnInit {

  constructor(private dealerService: DealerServiceService,  private formBuilder: FormBuilder,private route:ActivatedRoute) { }

  public dealerid: any;
  formValue!: FormGroup;
  dealerDetails:Dealer;
  dummyprofileimage:any;
  dealerUpdateObject: Dealer = new Dealer();

  ngOnInit(): void {
    this.formValue = this.formBuilder.group(
      {
        dealerid:[''],
        dealeremail:[''],
        dealerpass:[''],
        dealername:[''],
        dealerphone:[''],
        dealerlocation:[''],
        dealerabout:[''],
        dealerimage:[''],
        dealerbank:[''],
        dealerbranch:[''],
        dealeraccountno:[''],
        dealerpaytmid:['']
       }
     )
     let id = String(this.route.snapshot.paramMap.get('dealerid') || '');
    this.dealerid = id;
    this.getDealerDetails();
  }

  getDealerDetails()
  {
    this.dealerService.getDealerDetails(this.dealerid).subscribe((res:any)=>
    {
      console.log(res);
      this.dealerDetails = res;
    })
  }

  //edit
  editRes()
  {
    this.dummyprofileimage = this.dealerDetails.dealerimage;
    this.formValue.controls['dealerid'].setValue(this.dealerDetails.dealerid);
    this.formValue.controls['dealername'].setValue(this.dealerDetails.dealername);
    this.formValue.controls['dealeremail'].setValue(this.dealerDetails.dealeremail);
    this.formValue.controls['dealerabout'].setValue(this.dealerDetails.dealerabout);
    this.formValue.controls['dealerphone'].setValue(this.dealerDetails.dealerphone);
    this.formValue.controls['dealerlocation'].setValue(this.dealerDetails.dealerlocation);
    this.formValue.controls['dealerimage'].setValue(this.dealerDetails.dealerimage);
    this.formValue.controls['dealerbank'].setValue(this.dealerDetails.dealerbank);
    this.formValue.controls['dealerbranch'].setValue(this.dealerDetails.dealerbranch);
    this.formValue.controls['dealeraccountno'].setValue(this.dealerDetails.dealeraccountno);
    this.formValue.controls['dealerpaytmid'].setValue(this.dealerDetails.dealerpaytmid);
  }

  updateDealerDetails()
  {
    this.dealerUpdateObject.dealerid = this.formValue.value.dealerid;
    this.dealerUpdateObject.dealername = this.formValue.value.dealername;
    this.dealerUpdateObject.dealeremail = this.formValue.value.dealeremail;
    this.dealerUpdateObject.dealerpass = this.formValue.value.dealerpass;
    this.dealerUpdateObject.dealerabout = this.formValue.value.dealerabout;
    this.dealerUpdateObject.dealerphone = this.formValue.value.dealerphone;
    this.dealerUpdateObject.dealerpass = this.dealerDetails.dealerpass;
    this.dealerUpdateObject.dealerlocation = this.formValue.value.dealerlocation;
    this.dealerUpdateObject.dealerimage = this.formValue.value.dealerimage;
    this.dealerUpdateObject.dealerbank = this.formValue.value.dealerbank;
    this.dealerUpdateObject.dealerbranch = this.formValue.value.dealerbranch;
    this.dealerUpdateObject.dealeraccountno = this.formValue.value.dealeraccountno;
    this.dealerUpdateObject.dealerpaytmid = this.formValue.value.dealerpaytmid;


    this.dealerService.updateDealerDetails(this.dealerUpdateObject, this.dealerUpdateObject.dealerid)
    .subscribe((res:any)=>
    {
      console.log(res);
      alert("Profile Updated");
      this.getDealerDetails();
      
    },
    err=>
    
    {
      alert("Profile Updated");
      this.getDealerDetails();
      
    })
  }

  //delete
  deleteDealerDetails()
  {
    this.dealerService.deleteDealerDetails(this.dealerid).subscribe((res:any)=>
    {
      alert("Dealer Deleted")
      this.getDealerDetails();
    },err=>
    {
      alert("Dealer Deleted");
      this.getDealerDetails();
    })
  }
}
