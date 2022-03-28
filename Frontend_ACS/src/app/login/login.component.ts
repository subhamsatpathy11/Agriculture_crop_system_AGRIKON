import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { LoginService } from '../services/login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  email:any
  resStore:string
  credentials={
    dealerid:'',
    dealerpass:''
  }
    constructor(private loginService: LoginService, private router:Router)
    {}

    ngOnInit(): void {
      
    } 

    backToHome()
  {
    this.router.navigate(["/landing-page"])
  }

    onSubmit(){
      console.log("Form is Submitted");

      if((this.credentials.dealerid!='' && this.credentials.dealerpass!='') && (this.credentials.dealerid!=null && this.credentials.dealerpass!=null))
      {
        console.log("Save the form to server")
        this.loginService.generateTokenDealer(this.credentials).subscribe(
          (response: any) => {
            console.log(response); 
            this.resStore = response;
            
            this.loginService.loginUser(response.response)
            //window.location.href="/dealer-dashboard" 
            this.router.navigate(['/dealer-dashboard',this.credentials.dealerid])
          },

          error=>{
            console.log(error);
          }
        )
      }
      else{
        console.log("Fields are empty");
      }
    }
    
  }