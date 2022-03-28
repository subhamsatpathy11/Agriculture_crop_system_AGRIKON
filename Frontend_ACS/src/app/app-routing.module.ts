import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { DealerDashboardComponent } from './dealer-dashboard/dealer-dashboard.component';
import { DealerDetailsComponent } from './dealer-details/dealer-details.component';
import { FarmerDashboardComponent } from './farmer-dashboard/farmer-dashboard.component';
import { FarmerDetailsComponent } from './farmer-details/farmer-details.component';
import { FAuthGuard } from './fauth.guard';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LoginFarmerComponent } from './login-farmer/login-farmer.component';
import { LoginComponent } from './login/login.component';
import { SignupDealerComponent } from './signup-dealer/signup-dealer.component';
import { SignupFarmerComponent } from './signup-farmer/signup-farmer.component';

const routes: Routes = [
  {path:'', redirectTo:'landing-page', pathMatch:'full'},
  {path:'landing-page', component:LandingPageComponent},
  {path:'farmer-dashboard/:tk', component:FarmerDashboardComponent, canActivate: [FAuthGuard]},
  {path:'dealer-dashboard/:token', component:DealerDashboardComponent, canActivate: [AuthGuard]},
  {path:'login', component: LoginComponent},
  {path:'login-farmer', component:LoginFarmerComponent},
  {path:'farmer-dashboard/:tk/:fid', component:FarmerDetailsComponent, canActivate:[FAuthGuard]},
  {path:'dealer-dashboard/:token/:dealerid', component:DealerDetailsComponent, canActivate:[AuthGuard]},
  {path:'signup-farmer', component:SignupFarmerComponent},
  {path:'signup-dealer', component:SignupDealerComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routerLinks = [LandingPageComponent,FarmerDashboardComponent,
  LoginComponent, DealerDashboardComponent, FarmerDetailsComponent, LoginFarmerComponent,
DealerDetailsComponent, SignupFarmerComponent, SignupDealerComponent]
