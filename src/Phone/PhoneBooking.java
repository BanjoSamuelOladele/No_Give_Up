package Phone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PhoneBooking {
    private boolean isLocked = true;
    private String password;
    private String userName;
    private List<Contacting>contactings;
    private static int keyValue;
    private List<String> searchResult;
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
    public void createContact(String firstName, String lastName, String phoneNumber) {
        checkPhoneNumber(phoneNumber);
        Contacting contacting = new Contacting(firstName,lastName,phoneNumber);
        contacting.assignKeyValue(generateKeyValue());
        System.out.println("key value "+generateKeyValue());
        contactings.add(contacting);
    }
    private int generateKeyValue(){
        return keyValue++;
    }
    public int size() {
        return contactings.size();
    }
    public void searchByName(String nameInput) {
        searchResult = new ArrayList<>();
        for (Contacting contacting : contactings)
            if (contacting.getName().equalsIgnoreCase(nameInput)
                    || contacting.getOtherName().equalsIgnoreCase(nameInput))
                searchResult.add(contacting.getName() + " " + contacting.getOtherName());
        if (searchResult.size() == 0 ){
            throw new NullPointerException("Name not found");
        }
    }
    public int searchSize() {return searchResult.size();}
    public String searchResult() {
        searchResult.sort(Comparator.naturalOrder());
        if (searchResult.size() > 0)return searchResult.toString();
        else throw new NullPointerException("Nothing was found");
    }
    private void checkPhoneNumber(String phoneNumber){
        for (Character phone : phoneNumber.toCharArray())
            if (!Character.isDigit(phone))
                throw new IllegalArgumentException("Phone number contains alphabet(s)");
    }
    public void searchByPhoneNumber(String phoneNumber) {
        searchResult = new ArrayList<>();
        checkPhoneNumber(phoneNumber);
        for (Contacting contacting : contactings)
            if (contacting.getPhoneNumber().equals(phoneNumber))
                searchResult.add(contacting.getName()+" "+contacting.getOtherName());
        else throw new NullPointerException("Number not found");
    }
    public void searchByUniqueKey(int uniqueKey) {
        searchResult = new ArrayList<>();
        for (Contacting contacting : contactings)
            if (contacting.getKeyValue() == uniqueKey)
                searchResult.add(contacting.getName()+" "
                        +contacting.getOtherName()+" "+ contacting.getPhoneNumber());
    }
}
