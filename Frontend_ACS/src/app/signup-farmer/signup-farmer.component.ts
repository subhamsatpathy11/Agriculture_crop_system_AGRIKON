import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-signup-farmer',
  templateUrl: './signup-farmer.component.html',
  styleUrls: ['./signup-farmer.component.css']
})
export class SignupFarmerComponent implements OnInit {

  credentials:any={
    "femail":"",
    "fpass":"",
    "fname":"",
    "fcontact":"",
    "flocation":"",
    "fimage":""
   
  };

  formValue!: FormGroup;

  constructor(private service:LoginService, private router: Router) { }

  ngOnInit(): void {
  }

  backToHome()
  {
    this.router.navigate(["/landing-page"])
  }

  onSingup()
  {
    this.service.registerFarmer(this.credentials)
    .subscribe((data:any)=>
    {
      console.log(data);
      this.router.navigate(['login-farmer']);
      
    });
  }

}
