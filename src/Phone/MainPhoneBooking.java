package Phone;

import java.util.Scanner;

public class MainPhoneBooking {
    private static PhoneBooking phoneBooking = new PhoneBooking("Dele", "Dele");
    private static Scanner userInput = new Scanner(System.in);
    public static void main(String[] args) {
        entryPoint();
    }
    private static void entryPoint(){
        displayMessage("Welcome to AlphaPhone Management\n");
        String input = """
                1 -> Create Contact
                2 -> Search Contact
                3 -> Delete Contact
                4 -> Show All Contact
                0 -> Exit
                """;
        displayMessage(input);
        entryPointChoices();
    }
    private static void entryPointChoices(){
        displayMessage("Enter your choice: \n");
        String choice = input(userInput);
        switch (choice.charAt(0)){
            case '1' -> createAccount();
            case '2' -> searchContact();
            case '3' -> deleteContact();
            case '4' -> showAllContacts();
            case '0' -> exit();
            default -> entryPoint();
        }
    }
    private static void showAllContacts() {
        displayMessage("Viewing all contacts\n");
        displayMessage(phoneBooking.showContacts());
        showAllContactsOptions();
    }
    private static void showAllContactsOptions(){
        phoneBooking.showContacts();
        displayMessage("\nEnter your choice below\n");
        String output = """
                1 -> View Contact Details
                2 -> Delete Contact
                3 -> Search contact
                9 -> Previous Menu
                0 -> Exit
                """;
        displayMessage(output);
        pickSearchOption();
    }
    private static void pickSearchOption(){
        String input = input(userInput);
        switch (input.charAt(0)){
            case '1' -> checkSearchContact();
            case '2' -> deleteFromAllContacts();
            case '3' ->searchContact();
            case '9' -> entryPoint();
            case '0' -> exit();
        }
    }
    private static void deleteFromAllContacts(){
        displayMessage(phoneBooking.showContacts());
        deleteContact();
    }
    private static void deleteContact() {
        displayMessage("Enter id to delete\n");
        try {
            String input = input(userInput);
            phoneBooking.deleteContactByUniqueNumber(Integer.parseInt(input));
        }catch (NullPointerException exception){
            excepting(exception);
        }
        displayMessage("\nDeleted\n");
        entryPoint();
    }
    private static void exit() {
        displayMessage("GoodBye!");
        System.exit(0);
    }
    private static void searchContact() {
        displayMessage("Enter name or number to search ");
        try {
            String input = input(userInput);
            phoneBooking.searchContact(input);
        }catch (NullPointerException exception){
            excepting(exception);
        }
        searchResultPhase();
    }
    private static void searchResultPhase(){
        displayMessage("All search result\n");
        displayMessage(phoneBooking.searchResult());
        checkSearchContact();
    }
    private static void checkSearchContact(){
        displayMessage("input ID to view contact\n");
        String input = input(userInput);
        try {
            displayMessage(phoneBooking.searchByUniqueKey(Integer.parseInt(input)));
        }catch (NullPointerException exception){
            excepting(exception);
        }
        showAllContactsOptions();
    }
    private static void excepting(Exception exception){
        displayMessage(exception.getMessage()+"\n");
        displayMessage("Back to main menu \n");
        entryPoint();
    }
    private static void createAccount() {
        displayMessage("Enter firstName ");
        String firstName = input(userInput);
        displayMessage("Enter last name: ");
        String lastName = input(userInput);
        displayMessage("Enter phone number: ");
        try {
            String phoneNumber = input(userInput);
            phoneBooking.createContact(firstName,lastName,phoneNumber);
        }catch (IllegalArgumentException exception){
           excepting(exception);
        }
        displayMessage("\nContact Saved\n");
        showAllContactsOptions();
    }
    private static void displayMessage(String message){
        System.out.printf("%s", message);
    }
    private static String input(Scanner userInput){
        return userInput.nextLine();
    }
}
