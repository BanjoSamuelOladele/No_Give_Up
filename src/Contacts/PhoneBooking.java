package Contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBooking {
    private boolean isLocked = true;
    private String password;
    private List<Contact> contacts;
    private String searchResults = "";
    private int searchCounter;
    private String userN = "";
    public PhoneBooking(String userName, String password) {
        contacts = new ArrayList<>();
//        this.userName = userName;
        this.password = password;
    }
    public boolean isLocked() {return isLocked;}
    public void unlock(String password) {
        boolean check = this.password.equals(password);
        if (check) isLocked = false;
    }
    public void lock() {isLocked = true;}
    public void createContact(String firstName, String lastName, String phoneNumber) {
        if (!isLocked) {
            Contact contact = getContact(firstName, lastName, phoneNumber);
            contacts.add(contact);
        }
    }
    private Contact getContact(String firstName, String lastName, String phoneNumber) {
        Contact contact = new Contact(firstName, lastName, phoneNumber);
        contact.assignKeyValue(generateKeyValue());
        return contact;
    }
    private int generateKeyValue(){return contacts.size() + 1;}
    public int size() {return contacts.size();}
    public int findByName(String name){
        if (!isLocked) {
            for (Contact contact : contacts)
                if (contact.getName().equals(name)) {
                    searchCounter++;
                    searchResults += (contact.showDetails());
                }
        }
        return searchCounter;
    }
    public String contactsDetails() {
        return searchResults;
    }
    private Contact findByKeyValue(int value){
        for (Contact contact : contacts) if (contact.getKeyValue()==value) return contact;
        return null;
    }
    public void update(int keyValue, String firstName, String lastName, String phoneNumber) {
        Contact contact = findByKeyValue(keyValue);
        assert contact != null;
        contact.editContact(firstName,lastName,phoneNumber);
    }
    public void deleteContact(int keyValue) {
        Contact contact = findByKeyValue(keyValue);
        contacts.remove(contact);
    }
    public String showContacts() {
        for (Contact contact : contacts) userN += ((contacts.indexOf(contact)) +"\t"+ (contact.showDetails()));
        return userN;
    }
}
