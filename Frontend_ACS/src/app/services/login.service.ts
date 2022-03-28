import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Dealer } from '../dealer-dashboard/dealer.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  res:any

  constructor(private http:HttpClient) { }

  generateTokenDealer(dcredentials: any){
    return this.http.post("http://localhost:8002/authenticate", dcredentials)
  }

  generateTokenFarmer(fcredentials:any)
  {
    return this.http.post("http://localhost:8001/authenticateFarmer", fcredentials);
  }

  loginUser(token: string)
  {
    localStorage.setItem("token", token)
    //location.reload();
    return true;
  }

  //register
  registerFarmer(farmerCredentials:any)
  {
    return this.http.post("http://localhost:8001/registerFarmer", farmerCredentials);
  }

  //register Dealer
  registerDealer(dealerCredentials:any)
  {
    return this.http.post("http://localhost:8002/register",dealerCredentials);
  }

  // //get the details from token
  // detailsFromLogin(token: string)
  // {
  //   var tk = localStorage.getItem("token");
  //   var details = atob(tk.split(".")[1]);
  //   var store = JSON.parse(details);
  //   return store.subs;
  // }

  isLoggedIn()
  {
    let token=localStorage.getItem("token");
    if (token==undefined || token==='' || token === null || token == "Failed Authentication")
    {
      return false;
    } 
    else{
      return true;
    }
  }

  logout()
  {
    localStorage.removeItem('token');
    location.reload();
    return true;
  }

  getToken()
  {return localStorage.getItem("token")}


  //get user email
 getUser(token: string) : Dealer
  {
    
    return JSON.parse(atob(token.split('.')[1])).sub as Dealer;
  }

}
