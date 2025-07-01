import { LightningElement, api, track } from 'lwc';

export default class CartDetails extends LightningElement {
    @api cartItems = [];
    @track internalCartItems = [];

    connectedCallback() {
        this.internalCartItems = this.getCartItemsWithTotal();
    }

    getCartItemsWithTotal() {
        if (!this.cartItems || !Array.isArray(this.cartItems)) {
            return [];
        }

        return this.cartItems.map(item => {
            const total = (item.Price__c || 0) * (item.Quantity || 0);
            return {
                ...item,
                Total: total
            };
        });
    }
}
