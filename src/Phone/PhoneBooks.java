package Phone;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBooks {
    private List<PhoneBooking> phoneBookings = new ArrayList<>();
    private String password;

    public PhoneBooks(String password) {
        this.password = password;
    }
    public void createPhoneBook(String userName, String password) {
        checkPasswordLength(password);
        passwordCharacterNumericErrorLead(password);
        PhoneBooking phoneBooking = new PhoneBooking(userName, password);
        giveExceptionWhenItAlreadyExist(phoneBooking);
        phoneBookings.add(phoneBooking);
    }
    private void giveExceptionWhenItAlreadyExist(PhoneBooking userName){
        String user = userName.getUserName();
        for (PhoneBooking phoneBooking : phoneBookings)
            if (phoneBooking.getUserName().equals(user))
                throw new IllegalArgumentException("Username already exist");
    }
    private void checkPasswordLength(String password){
        if (password.length()< 8 || password.length() > 13)
            throw new
                    IllegalArgumentException("Password must between 8 - 13 characters long");
    }
    private void passwordCharacterNumericErrorLead(String password){
        boolean check = checkIfPasswordContainNumberAndSpecialCharacter(password);
        if (!check)
            throw new InputMismatchException
                    ("Password must contain numbers and special characters");
    }
    private boolean checkIfPasswordContainNumberAndSpecialCharacter(String password){
        String operation = "(?=.*\\d)(?=.*\\W)";
        Pattern pattern = Pattern.compile(operation);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
    public int sizeOfPhoneBookInPhoneBooks() {return phoneBookings.size();}
    public void removePhone(String userName, String password) {
        if (this.password.equals(password))phoneBookings.removeIf(phoneBooking -> phoneBooking.getUserName().equals(userName));
        else throw new IllegalArgumentException("Password is Incorrect");
    }
    public void createContact(String userName, String firstName, String lastName, String phoneNumber) {
        PhoneBooking phoneBooking = findByUserName(userName);
        phoneBooking.createContact(firstName,lastName,phoneNumber);
    }
    public void searchContact(String userName, String name) {
        PhoneBooking phoneBooking = findByUserName(userName);
        phoneBooking.searchContact(name);
    }
    public String searchResult(String userName) {
        PhoneBooking phoneBooking = findByUserName(userName);
        return phoneBooking.searchResult();
    }
    private PhoneBooking findByUserName(String userName){
        for (PhoneBooking phoneBooking: phoneBookings)
            if (phoneBooking.getUserName().equals(userName))
                return phoneBooking;
        throw new NullPointerException("Nothing was found");
    }
}
