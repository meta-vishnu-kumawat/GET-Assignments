public with sharing class ContactController {
    @AuraEnabled(cacheable=true)
    public static List<Contact> getContacts() {
        return [SELECT Id, Name, Phone, Email FROM Contact LIMIT 100];
    }

    @AuraEnabled
    public static Contact createContact(Contact contact) {
        insert contact;
        return contact;
    }

    @AuraEnabled
    public static Contact updateContact(Contact contact) {
        update contact;
        return contact;
    }

    @AuraEnabled
    public static void deleteContact(Id contactId) {
        delete [SELECT Id FROM Contact WHERE Id = :contactId];
    }
}