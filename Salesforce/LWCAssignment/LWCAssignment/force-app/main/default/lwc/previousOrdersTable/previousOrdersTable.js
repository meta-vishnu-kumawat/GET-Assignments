import { LightningElement, api } from 'lwc';

export default class PreviousOrdersTable extends LightningElement {
    @api records = [];

    sortField = 'CreatedDate';
    sortDirection = 'desc';

    get sortedData() {
        return [...this.records].sort((a, b) => {
            let valA = a[this.sortField];
            let valB = b[this.sortField];

            return this.sortDirection === 'asc'
                ? valA > valB ? 1 : -1
                : valA < valB ? 1 : -1;
        });
    }

    handleSort(event) {
        const field = event.target.dataset.field;
        this.sortDirection = this.sortField === field && this.sortDirection === 'asc' ? 'desc' : 'asc';
        this.sortField = field;
    }
}
