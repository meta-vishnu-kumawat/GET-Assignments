import { LightningElement, api } from 'lwc';

export default class CheckoutSummary extends LightningElement {
    @api cartItems = [];

    get totalAmount() {
        if (!this.cartItems) return 0;
        return this.cartItems.reduce((sum, item) => {
            return sum + (item.Price__c * item.Quantity);
        }, 0);
    }

    get cartItemsWithTotal() {
        const items = this.cartItems||[];
        return items.map(item => {
            return {
                ...item,
                TotalAmount: item.Price__c * item.Quantity
            }
        });
    }
}
