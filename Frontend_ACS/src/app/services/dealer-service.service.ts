import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Dealer } from '../dealer-dashboard/dealer.model';
import { Crops } from '../farmer-dashboard/farmer-dashboard';

@Injectable({
  providedIn: 'root'
})
export class DealerServiceService {

  constructor(private http:HttpClient) { }

  //get crops
  getLiveCrops():Observable<Crops[]>
  {
    return this.http.get<Crops[]>("http://localhost:8002/dealerCrops/621340089416fc7fff2b78b9");
  }

  getDealerDetails(dealerid:string):Observable<Dealer[]>
  {
    return this.http.get<Dealer[]>("http://localhost:8002/"+dealerid);
  }

  //update
  updateDealerDetails(dealer:Dealer, dealerid:string):Observable<Dealer[]>
  {
    return this.http.put<Dealer[]>("http://localhost:8002/update/"+dealerid, dealer);
  }

  //delete
  deleteDealerDetails(dealerid:string):Observable<Dealer[]>
  {
    return this.http.delete<Dealer[]>("http://localhost:8002/delete/"+dealerid);
  }
}
