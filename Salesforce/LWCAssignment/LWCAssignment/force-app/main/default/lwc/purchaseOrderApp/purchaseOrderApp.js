import { LightningElement, track } from 'lwc';
 import getPreviousOrders from '@salesforce/apex/PO_Service.getPreviousOrders';
  import getAllProducts from '@salesforce/apex/PO_Service.getAllProducts';

export default class PurchaseOrderApp extends LightningElement { @track previousOrders = []; @track cartItems = []; @track availableProducts = [];

hasPreviousOrders = false;
showProductTable = false;
cartVisible = false;
showCheckout = false;

connectedCallback() {
    this.fetchPreviousOrders();
}

fetchPreviousOrders() {
    getPreviousOrders()
        .then(result => {
            this.previousOrders = result;
            this.hasPreviousOrders = result.length > 0;
        })
        .catch(error => {
            console.error('Error fetching previous orders:', error);
        });
}

startNewOrder() {
    this.showProductTable = true;
    getAllProducts()
        .then(data => {
            this.availableProducts = data.map(item => ({ ...item }));
        })
        .catch(error => {
            console.error('Error fetching products:', error);
        });
}

handleAddToCart(event) {
    const selectedProduct = event.detail;
    const index = this.cartItems.findIndex(item => item.Product2Id === selectedProduct.Product2Id);

    if (selectedProduct.AvailableQuantity <= 0) return;

    if (index > -1) {
        if (this.cartItems[index].Quantity < selectedProduct.AvailableQuantity) {
            this.cartItems[index].Quantity += 1;
            selectedProduct.AvailableQuantity -= 1;
        }
    } else {
        this.cartItems.push({
            ...selectedProduct,
            Quantity: 1
        });
        selectedProduct.AvailableQuantity -= 1;
    }

    this.cartVisible = this.cartItems.length > 0;
    console.log('Products in cart: ', this.cartItems);
}

handlePlaceOrder() {
    alert('Place Order Successful',this.selectedProduct);
}

handleQuantityChange(event) {
    const { productId, newQuantity } = event.detail;
    const cartItem = this.cartItems.find(item => item.Product2Id === productId);
    const productItem = this.availableProducts.find(item => item.Product2Id === productId);

    const totalQuantity = parseInt(newQuantity);

    if (isNaN(totalQuantity) || totalQuantity <= 0 || totalQuantity > (cartItem.Quantity + productItem.AvailableQuantity)) {
        alert('Invalid Quantity');
        return;
    }

    const diff = totalQuantity - cartItem.Quantity;
    cartItem.Quantity = totalQuantity;
    productItem.AvailableQuantity -= diff;
}

handleDeleteItem(event) {
    const productId = event.detail;
    const index = this.cartItems.findIndex(item => item.Product2Id === productId);
    if (index > -1) {
        const deletedQty = this.cartItems[index].Quantity;
        this.availableProducts = this.availableProducts.map(prod => {
            if (prod.Product2Id === productId) {
                prod.AvailableQuantity += deletedQty;
            }
            return prod;
        });
        this.cartItems.splice(index, 1);
    }
    this.cartVisible = this.cartItems.length > 0;
}

handleCheckout() {
    this.showCheckout = true;
}

handlePlaceOrder() {
    // Use Apex to insert records
    // Reset state after placing order
    this.cartItems = [];
    this.showCheckout = false;
    this.showProductTable = false;
    this.cartVisible = false;
    this.fetchPreviousOrders();
}

}