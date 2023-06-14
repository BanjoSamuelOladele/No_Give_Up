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
        checkIfItIsDigits(phoneNumber);
        Contact contact = new Contact(firstName, lastName, phoneNumber);
        if (!isLocked) contacts.add(contact);
        else throw new IncorrectPasswordException("Password is Incorrect.");
    }
    private Contact  findByPhoneNumber(String phoneNumber){
        checkIfItIsDigits(phoneNumber);
        for (Contact contact : contacts) if (contact.getPhoneNumber().equals(phoneNumber))return contact;
        throw new PhoneNumberDoesNotExistException("Phone Number Not Found.");
    }
    private String searchNumberByPhoneNumber(String phoneNumber) {
        Contact contact = findByPhoneNumber(phoneNumber);
        return contact.getFirstName() +" " + contact.getLastName();
    }
    private Contact findByName(String firstName){
        for (Contact contact : contacts) if (contact.getFirstName().equalsIgnoreCase(firstName)) return contact;
        throw new NameDoesNotExistException(firstName +" not found");
    }
    private String searchContactByName(String firstName) {
        Contact contact = findByName(firstName);
        return contact.getPhoneNumber();
    }
    private String checkIfIt(String input){
        for (Character in : input.toCharArray()) if (Character.isDigit(in)) return searchNumberByPhoneNumber(input);
        return searchContactByName(input);
    }
    public String searchContact(String input){
        if (!isLocked) {return checkIfIt(input);}
        throw new IncorrectPasswordException("Password Is Incorrect.");
    }
    private boolean ifItContains(String input){
        for (Character in : input.toCharArray()) if (Character.isDigit(in)) return true;
        return false;
    }
    public void deleteContact(String input) {
        if (!isLocked) {
            boolean check = ifItContains(input);
            if (check) {
                Contact contact = findByPhoneNumber(input);
                contacts.remove(contact);
            } else {
                int space = input.indexOf(" ");
                if (space != -1) {
                    String firstName = input.substring(0, space);
                    String lastName = input.substring(space + 1);
                    Contact contact = findByBothNames(firstName, lastName);
                    contacts.remove(contact);
                }
            }
        }else throw new IncorrectPasswordException("Password Is Incorrect.");
    }
    private Contact findByBothNames(String firstName, String lastName){
        for (Contact contact : contacts) if (contact.getFirstName().equalsIgnoreCase(firstName)
        && contact.getLastName().equals(lastName)) return contact;
        throw new NameDoesNotExistException(firstName +" not found");
    }
    public void modifySavedContact(String firstName, String lastName, String phoneNumber) {
        checkIfItIsDigits(phoneNumber);
        if (!isLocked) {
            Contact contact = returnAll(firstName, lastName, phoneNumber);
            contact.modify(firstName, lastName, phoneNumber);
        }else throw new IncorrectPasswordException("Password Is Incorrect.");
    }
    private void checkIfItIsDigits(String phoneNumber){
        for (Character number : phoneNumber.toCharArray()) if (!Character.isDigit(number)) throw new IllegalArgumentException("Invalid Number");
    }
    private Contact returnAll(String firstName, String lastName, String phoneNumber){
        for (Contact contact : contacts) {
            boolean check = contact.getFirstName().equals(firstName) &&
                    contact.getLastName().equals(lastName) || contact.getPhoneNumber().equals(phoneNumber) &&
                    contact.getLastName().equals(lastName) || contact.getPhoneNumber().equals(phoneNumber) &&
                    contact.getFirstName().equals(firstName);
            if (check) return contact;
        }
        throw new NullPointerException("Invalid Input");
    }
}
