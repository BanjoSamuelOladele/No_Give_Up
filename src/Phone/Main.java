package Phone;

import diary.Entry;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.in;

public class Main {
    private static Scanner userInput = new Scanner(System.in);
    private static PhoneBooks phoneBooks;
    public static void main(String[] args) {
        startPoint();
    }
    private static void startPoint(){
        displayMessage("Welcome to AlphaPhone Management\n");
        displayMessage("Enter password to continue");
        String password = input(userInput);
        phoneBooks = new PhoneBooks(password);
        displayMessage("Created\n");
        entryPoint();
    }
    private static void entryPoint(){
        displayMessage("Welcome to AlphaPhone Management\n");
        String input = """
                1 -> SignUp
                2 -> LogIn
                3 -> Exit
                """;
        displayMessage(input);
        entryPointChoices();
    }
    private static void entryPointChoices(){
        String choice = input(userInput);
        switch (choice.charAt(0)){
            case '1' -> createAccount();
            case '2' -> logIn();
            case '3' -> exit();
            default -> entryPoint();
        }
    }
    private static void exit() {
        displayMessage("GoodBye!");
        System.exit(0);
    }
    private static void logIn() {

    }
    private static void createAccount() {
        try {
            displayMessage("Enter Unique UserName: ");
            String userName = input(userInput);
            displayMessage("Enter a golden password: ");
            String password = input(userInput);
            phoneBooks.createPhoneBook(userName, password);
            displayMessage("Phone book "+ userName+ " created successfully\n");
            entryPoint();
        }catch (IllegalArgumentException | InputMismatchException exception){
            displayMessage(exception.getMessage()+"\n");
            displayMessage("Back to main menu\n");
            entryPoint();
        }
    }
    private static void removePhones(){
        try {
            displayMessage("Enter phonebook username: ");
            String userName = input(userInput);
            displayMessage("Enter password: ");
            String password = input(userInput);
            phoneBooks.removePhone(userName,password);
            displayMessage("Phonebook "+ userName+" removed successfully");
            entryPoint();
        }catch (IllegalArgumentException exception){
            displayMessage(exception.getMessage()+"\n");
            displayMessage("Back to main menu\n");
            entryPoint();
        }
    }
    private static String  input(Scanner userInput){
        return userInput.nextLine();
    }
    private static void displayMessage(String input){
        System.out.printf("%s", input);
    }
}
