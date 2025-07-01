import { LightningElement, api, track } from 'lwc';

export default class ProductTable extends LightningElement {
    @api products = [];
    @track searchKey = '';

    get filteredProducts() {
        return this.products.filter(
            (prod) =>
                prod.Name.toLowerCase().includes(this.searchKey.toLowerCase())
        );
    }
   

    handleSearchChange(event) {
        this.searchKey = event.target.value;
    }

    handleAdd(event) {
        const productId = event.target.dataset.id;
        const selectedProduct = this.products.find((prod) => prod.Product2Id === productId);
      
        if(selectedProduct){
            const productCopy = { ...selectedProduct };
            const addEvent = new CustomEvent('addtocart', {
                detail: productCopy
            });
            this.dispatchEvent(addEvent);
        }
       
    }
}