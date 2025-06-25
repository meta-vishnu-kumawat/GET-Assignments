import { LightningElement, wire, api } from 'lwc';
import { getRecord } from 'lightning/uiRecordApi';
import ACCOUNT_NAME_FIELD from '@salesforce/schema/Account.Name';
import ACCOUNT_TYPE_FIELD from '@salesforce/schema/Account.Type';

export default class priPubDemo extends LightningElement {
    @api recordId;

    @wire(getRecord, { recordId: '$recordId', fields: [ACCOUNT_NAME_FIELD, ACCOUNT_TYPE_FIELD] })
    account;

    get name() {
        return this.account.data ? this.account.data.fields.Name.value : '';
    }

    get type() {
        return this.account.data ? this.account.data.fields.Type.value : '';
    }
}