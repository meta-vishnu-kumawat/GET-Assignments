import { LightningElement } from 'lwc';

export default class Parent extends LightningElement {
    areVisible = false;
    handleChange(event){
      this.areVisible = event.target.checked;}
}