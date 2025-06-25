import { LightningElement, track, wire } from 'lwc';
import getContacts from '@salesforce/apex/ContactController.getContacts';
import createContactRecord from '@salesforce/apex/ContactController.createContact';
import updateContactRecord from '@salesforce/apex/ContactController.updateContact';
import deleteContactRecord from '@salesforce/apex/ContactController.deleteContact';
import { ShowToastEvent } from 'lightning/platformShowToastEvent';

export default class ContactManager extends LightningElement {
  @track contacts = [];
  @track contactRecord = { Name: '', Phone: '', Email: '' };

  columns = [
    { label: 'Name', fieldName: 'Name' },
    { label: 'Phone', fieldName: 'Phone' },
    { label: 'Email', fieldName: 'Email' },
    {
      type: 'button',
      typeAttributes: { label: 'Edit', name: 'edit', variant: 'brand' }
    },
    {
      type: 'button',
      typeAttributes: { label: 'Delete', name: 'delete', variant: 'destructive' }
    }
  ];

  @wire(getContacts)
  wiredContacts({ data, error }) {
    if (data) {
      this.contacts = data;
    } else if (error) {
      this.showToast('Error loading contacts', error.body.message, 'error');
    }
  }

  handleInputChange(event) {
    const field = event.target.name;
    this.contactRecord[field] = event.target.value;
  }

  createContact() {
    createContactRecord({ contact: this.contactRecord })
      .then(() => {
        this.showToast('Success', 'Contact Created', 'success');
        this.contactRecord = { Name: '', Phone: '', Email: '' };
        return refreshApex(this.contacts);
      })
      .catch(error => {
        this.showToast('Error creating contact', error.body.message, 'error');
      });
  }

  handleRowAction(event) {
    const action = event.detail.action.name;
    const row = event.detail.row;

    if (action === 'edit') {
      const updatedName = prompt('Enter new name:', row.Name);
      const updatedPhone = prompt('Enter new phone:', row.Phone);
      const updatedEmail = prompt('Enter new email:', row.Email);
      const updatedContact = { ...row, Name: updatedName, Phone: updatedPhone, Email: updatedEmail };

      updateContactRecord({ contact: updatedContact })
        .then(() => {
          this.showToast('Updated', 'Contact updated', 'success');
          return refreshApex(this.contacts);
        })
        .catch(error => this.showToast('Error', error.body.message, 'error'));
    }

    if (action === 'delete') {
      deleteContactRecord({ contactId: row.Id })
        .then(() => {
          this.showToast('Deleted', 'Contact deleted', 'success');
          return refreshApex(this.contacts);
        })
        .catch(error => this.showToast('Error', error.body.message, 'error'));
    }
  }

  showToast(title, message, variant) {
    this.dispatchEvent(
      new ShowToastEvent({ title, message, variant })
    );
  }
}
