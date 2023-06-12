package contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private boolean isLocked = true;
    private String password;
    private List<Contact> contacts = new ArrayList<>();
    public PhoneBook(String password) {
        this.password = password;
    }
    public boolean isLocked() {return isLocked;}
    public void unlock(String password) {
        if (this.password.equals(password)) isLocked = false;
        else throw new IncorrectPasswordException("Password Is Incorrect.");
    }
    public void lock() {isLocked = true;}
    public int sizeOfContacts() {
        return contacts.size();
    }
    public void createContact(String firstName, String lastName, String phoneNumber) {
        Contact contact = new Contact(firstName, lastName, phoneNumber);
        if (!isLocked) contacts.add(contact);
    }
    private Contact  findByPhoneNumber(String phoneNumber){
        for (Contact contact : contacts) if (contact.getPhoneNumber().equals(phoneNumber))return contact;
        return null;
    }
    private String searchNumberByPhoneNumber(String phoneNumber) {
        Contact contact = findByPhoneNumber(phoneNumber);
        assert contact != null;
        return contact.getFirstName() +" " + contact.getLastName();
    }
    private Contact findByName(String firstName){
        for (Contact contact : contacts) if (contact.getFirstName().equals(firstName)) return contact;
        return null;
    }
    public String searchContactByName(String firstName) {
        Contact contact = findByName(firstName);
        assert contact != null;
        return contact.getPhoneNumber();
    }
    public String searchContact(String input){
        for (Character in : input.toCharArray()) if (Character.isDigit(in)) return searchNumberByPhoneNumber(input);
        return searchContactByName(input);
    }
}
