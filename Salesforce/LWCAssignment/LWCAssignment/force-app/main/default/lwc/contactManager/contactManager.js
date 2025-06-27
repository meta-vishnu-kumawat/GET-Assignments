import { LightningElement, track, wire } from 'lwc';
import getContacts from '@salesforce/apex/ContactController.getContacts';
import createContactRecord from '@salesforce/apex/ContactController.createContact';
import updateContactRecord from '@salesforce/apex/ContactController.updateContact';
import deleteContactRecord from '@salesforce/apex/ContactController.deleteContact';
import { ShowToastEvent } from 'lightning/platformShowToastEvent';
import { refreshApex } from '@salesforce/apex';

export default class ContactManager extends LightningElement {
  @track contacts = [];
  @track contactRecord = {
    FirstName: '',
    LastName: '',
    Phone: '',
    Email: ''
  };

  wiredContactsResult;

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
  wiredContacts(result) {
    this.wiredContactsResult = result;
    const { data, error } = result;
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
    // Validate LastName before sending to Apex (required field)
    if (!this.contactRecord.LastName) {
      this.showToast('Validation Error', 'Last Name is required', 'warning');
      return;
    }

    createContactRecord({ contact: this.contactRecord })
      .then(result => {
        this.showToast('Success', 'Contact Created', 'success');
        this.contactRecord = { FirstName: '', LastName: '', Phone: '', Email: '' };
        return refreshApex(this.wiredContactsResult);
      })
      .catch(error => {
        this.showToast('Error creating contact', error.body.message || error.message, 'error');
      });
  }

  handleRowAction(event) {
    const action = event.detail.action.name;
    const row = event.detail.row;

    if (action === 'edit') {
      const updatedFirstName = prompt('Enter First Name:', row.FirstName || '');
      const updatedLastName = prompt('Enter Last Name:', row.LastName || '');
      const updatedPhone = prompt('Enter Phone:', row.Phone || '');
      const updatedEmail = prompt('Enter Email:', row.Email || '');

      if (!updatedLastName) {
        this.showToast('Validation Error', 'Last Name is required', 'warning');
        return;
      }

      const updatedContact = {
        Id: row.Id,
        FirstName: updatedFirstName,
        LastName: updatedLastName,
        Phone: updatedPhone,
        Email: updatedEmail
      };

      updateContactRecord({ contact: updatedContact })
        .then(() => {
          this.showToast('Updated', 'Contact updated', 'success');
          return refreshApex(this.wiredContactsResult);
        })
        .catch(error => this.showToast('Error', error.body.message || error.message, 'error'));
    }

    if (action === 'delete') {
      deleteContactRecord({ contactId: row.Id })
        .then(() => {
          this.showToast('Deleted', 'Contact deleted', 'success');
          return refreshApex(this.wiredContactsResult);
        })
        .catch(error => this.showToast('Error', error.body.message || error.message, 'error'));
    }
  }

  showToast(title, message, variant) {
    this.dispatchEvent(new ShowToastEvent({ title, message, variant }));
  }
}
