package Phone;

import Contacts.Contact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PhoneBooking {
    private boolean isLocked = true;
    private String password;
    private final String userName;
    private List<Contacting>contactings;
    private int keyValue;
    private List<String> searchResult;
    private String listOfAllContacts = "";
    public PhoneBooking(String userName, String password) {
        contactings = new ArrayList<>();
        this.password = password;
        this.userName = userName;
    }
    public boolean isLocked() {
        return isLocked;
    }
    public void unlock(String password, String username) {
        boolean checkEntryDetails = this.userName.equals(username) && this.password.equals(password);
        if (checkEntryDetails) isLocked = false;
        else throw new IllegalArgumentException("Password or Username does not exist");
    }
    public void lock() {isLocked = true;}
    public String getUserName(){return userName;}
    public void createContact(String firstName, String lastName, String phoneNumber) {
        checkPhoneNumber(phoneNumber);
        checkLengthOfPhoneNumber(phoneNumber);
        Contacting contacting = new Contacting(firstName,lastName,phoneNumber);
        int uniqueId = keyValue+=1;
        contacting.assignKeyValue(uniqueId);
        contactings.add(contacting);
    }
    private void checkLengthOfPhoneNumber(String phoneNumber){
        if (phoneNumber.length() != 11 && phoneNumber.length() != 13) throw new IllegalArgumentException("PhoneNumber must be between 11 and 13 digits long");
    }
    public int size() {
        return contactings.size();
    }
    private void checkIfItContainsDigits(String input){
        for (Character character : input.toCharArray())
            if (Character.isDigit(character)) searchByPhoneNumber(input);
        else searchByName(input);
    }
    public void searchContact(String input){
        checkIfItContainsDigits(input);
        if (searchResult.size() == 0 ){
            throw new NullPointerException("Name not found");
        }
    }
    private void searchByName(String nameInput) {
        searchResult = new ArrayList<>();
        for (Contacting contacting : contactings)
            checkingNamesSearchResult(nameInput, contacting);
    }
    private void checkingNamesSearchResult(String nameInput, Contacting contacting) {
        if (contacting.getName().equalsIgnoreCase(nameInput)
                || contacting.getOtherName().equalsIgnoreCase(nameInput))
            searchResult.add(contacting.getName() + " " + contacting.getOtherName());
    }
    public int searchSize() {return searchResult.size();}
    public String searchResult() {
        searchResult.sort(Comparator.naturalOrder());
        if (searchResult.size() > 0)
            return searchResult.toString();
        else throw new NullPointerException("Nothing was found");
    }
    private void checkPhoneNumber(String phoneNumber){
        for (Character phone : phoneNumber.toCharArray())
            if (!Character.isDigit(phone))
                throw new IllegalArgumentException("Phone number contains alphabet(s)");
    }
    private void searchByPhoneNumber(String phoneNumber) {
        searchResult = new ArrayList<>();
        checkPhoneNumber(phoneNumber);
        for (Contacting contacting : contactings)
            if (contacting.getPhoneNumber().equals(phoneNumber))
                searchResult.add(contacting.getName()+" "+contacting.getOtherName());
        else throw new NullPointerException("Number not found");
    }
    public String  searchByUniqueKey(int uniqueKey) {
        for (Contacting contacting : contactings)
            if (contacting.getKeyValue() == uniqueKey) return contacting.showDetails();
        throw new NullPointerException("Not found");
    }
    private Contacting searchUniqueID(int uniqueID){
        for (Contacting contacting : contactings)
            if (contacting.getKeyValue() == uniqueID)
                return contacting;
        throw new NullPointerException("Invalid input");
    }
    public void deleteContactByUniqueNumber(int uniqueID) {
        Contacting contacting = searchUniqueID(uniqueID);
        contactings.remove(contacting);
    }
    public String modifyContact(int keyValue, String firstName, String lastName, String phoneNumber) {
        Contacting contacting = searchUniqueID(keyValue);
        contacting.edit(firstName,lastName,phoneNumber);
        return contacting.showDetails();
    }
    public String showContacts() {
        for (Contacting contacting : contactings) listOfAllContacts += ((contactings.indexOf(contacting)) +"\t"+ (contacting.showDetails()));
        return listOfAllContacts;
    }
}