import { Crops } from "../farmer-dashboard/farmer-dashboard";

export class Farmer {

    fid: string = '';
    femail: string = '';
    fpass: string = '';
    fname: string = '';
    fcontact: string = '';
    fbank:string = '';
    fimage:string = '';
    faccountno: string = '';
    fpaytmid:string = '';
    fbankbranch:string = '';
    flocation: string = '';
    fabout: string = '';
    crops: Array<Crops> = [];
}