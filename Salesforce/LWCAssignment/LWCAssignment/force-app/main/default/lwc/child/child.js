import { api, LightningElement } from 'lwc';

export default class Child extends LightningElement {
   uppercasedItem = 'defult value';
    @api
   get itemName(){
        return this.uppercasedItem;
    }
    set itemName(value){
        this.uppercasedItem = value.toUpperCase();
    }
    
}