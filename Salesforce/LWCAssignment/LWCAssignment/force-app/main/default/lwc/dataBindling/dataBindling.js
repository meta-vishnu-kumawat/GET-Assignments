import { LightningElement } from 'lwc';

export default class DataBindling extends LightningElement {
    UppercasedFullName = "";
    firstName = "";
    lastName = "";
    handleChange(event){
      const  field = event.target.name;
       if(field === 'fname'){
 this.firstName  = event.target.value;
       }
       else if(field === 'lname')   {
this.lastName = event.target.value;
       }
       this.UppercasedFullName = `${this.firstName} ${this.lastName}`.toUpperCase();
    }
    
    
    
}