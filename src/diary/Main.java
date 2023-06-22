package diary;

import java.util.Scanner;

public class Main {
    private static Scanner userInput = new Scanner(System.in);
    private static Diary diary = new Diary("Dele", "Samuel");

    public static void main(String[] args) {
        signUpPage();
    }
    private static void signUpPage(){
        displayMessage("Welcome to Wizard Phone Book\n");
        String output = """
                1 -> SignUp
                2 -> LogIn
                0 -> Exit
                """;
        displayMessage(output);
        logInSignUp();
    }
    private static void logInSignUp(){
        try {
            String string = input(userInput);
            switch (string.charAt(0)) {
                case '1' -> signUp();
                case '2' -> logIn();
                case '0' -> exit();
                default -> signUpPage();
            }
        }catch (StringIndexOutOfBoundsException exception){
            excepting(exception);
        }
    }
    private static void exit() {
        displayMessage("GoodBye!");
        System.exit(0);
    }
    private static void logIn() {
        displayMessage("""
                =======
                 Login
                =======
                """);
        displayMessage("UserName: ");
        try {
            String string = input(userInput);
            displayMessage("Enter password: ");
            String string1 = input(userInput);
            diary.unlock(string,string1);
        }catch (IllegalArgumentException exception){
            excepting(exception);
        }
        entryPoint();
    }
    private static void excepting(Exception exception){
        displayMessage(exception.getMessage()+"\n");
        displayMessage("Back to main menu\n");
        signUpPage();
    }
    private static void signUp() {
        displayMessage("""
                ========
                 SIGNUP
                ========
                """);
        displayMessage("Enter username: ");
        String userName = input(userInput);
        displayMessage("Enter a password: ");
        String password = input(userInput);
        diary = new Diary(userName, password);
        signUpPage();
    }
    private static void entryPoint(){
        String output= """
                1 -> Create Gist
                2 -> Edit Gist
                3 -> Delete Gist
                4 -> Search Gist
                5 -> Show All Gist
                9 -> Previous Menu
                0 -> Exit
                """;
        displayMessage(output);
        optionMenu();
    }
    private static void optionMenu(){
        String input = input(userInput);
        switch (input.charAt(0)){
            case '1' -> creatingGist();
            case '2' -> editGist();
            case '3' -> deletingGist();
            case '4' -> searchingGist();
            case '5' -> showingGist();
            case '9' -> signUpPage();
            case '0' -> exit();
            default -> entryPoint();
        }
    }
    private static void showingGist() {
    }

    private static void searchingGist() {
    }

    private static void deletingGist() {
    }

    private static void editGist() {
    }

    private static void creatingGist() {
    }

    private static String input(Scanner message){
        return message.nextLine();
    }
    private static void displayMessage(String message){
        System.out.printf("%s", message);
    }
}
