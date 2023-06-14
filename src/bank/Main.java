package bank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final Bank bank = new Bank();
    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        entryPoint();
    }
    private static void entryPoint() throws InterruptedException {
        try {
            welcomeNote();
            period_OfThe_Day();
            displayDateAndTimeOfTheDay();
            bankingFirm();
        }catch (StringIndexOutOfBoundsException exception){
            displayMessage("You have not entered any input.");
            entryPoint();
        }
    }
    private static void bankingFirm() throws InterruptedException {
        displayMessage("""
                Kindly choose one of the options below
                1 -> Create New Account
                2 -> Cash Transaction
                3 -> Exit
                """);
        String choice = input(userInput);
        switch (choice.charAt(0)){
            case '1' -> registerNewCustomer();
            case '2' -> bankingServices();
            case '3' -> {
                displayMessage("odaboo");
                System.exit(0);
            }
            default -> bankingFirm();
        }
    }
    private static void bankingServices() throws InterruptedException {
        displayMessage("""
                Kindly choose one of the Options below
                
                1 -> Deposit
                2 -> Withdraw
                3 -> Transfer
                4 -> Check Balance
                0 -> Main Menu
                9 -> Quit
                """);
        String option = input(userInput);
        switch (option.charAt(0)){
            case '1' : depositing();
            case '2' : withdrawing();
            case '3' : transferring();
            case '4' : checkBalance();
            case '0' : bankingFirm();
            case '9' : {
                displayMessage("odaboo");
                System.exit(99);
            }
            default  : bankingServices();
        }
    }
    private static void checkBalance() throws InterruptedException {
        methodsEntryPoint("\n");
        displayMessage("=== Check Balance Window ===\n");
        displayMessage("Enter Your Account Number: ");
        try {
            String accountNumber = input(userInput);
            displayMessage("Enter Your Password: ");
            String password = input(userInput);
            BigDecimal balance = bank.checkAccountBalance(accountNumber,password);
            displayMessage("Your Account Balance is: " + balance);displayMessage("\n");
        }catch (NullPointerException | IllegalArgumentException exception){encounterException(exception.getMessage());}
        afterEachSuccessfulTransaction();
    }
    private static void transferring() throws InterruptedException {
        methodsEntryPoint("\n");
        displayMessage("=== Transfer Window ===\n");
        displayMessage("Enter Your Account Number: ");
        try {
            String senderAccountNumber = input(userInput);
            displayMessage("Enter Receiver Account Number: ");
            String receiverAccountNumber = input(userInput);
            displayMessage("Enter Amount To Transfer: ");
            String amount = input(userInput);
            BigDecimal bigDecimalAmount = BigDecimal.valueOf(Long.parseLong(amount));
            displayMessage("Enter Password To Confirm: ");
            String password = input(userInput);
            bank.transfer(senderAccountNumber,receiverAccountNumber,bigDecimalAmount,password);
        }catch (NullPointerException | IllegalArgumentException exception){
            encounterException(exception.getMessage());
        }
        displayMessage("\nTransfer Successful\n");
        afterEachSuccessfulTransaction();
    }
    private static void encounterException(String exception) throws InterruptedException {
        displayMessage(exception);
        displayMessage("\nBack To Menu\n");
        bankingFirm();
    }
    private static void afterEachSuccessfulTransaction() throws InterruptedException {
        displayMessage("""
                Would you like to continue?
                
                1 -> yes
                2 -> no
                """);
        successfulTransaction();
    }
    private static void withdrawing() throws InterruptedException {
        methodsEntryPoint("\n");
        displayMessage("=== Withdrawal Window ===\n");
        displayMessage("Enter Account Number: ");
        try {
            String accountNumber = input(userInput);
            displayMessage("Enter Amount To Withdraw: ");
            String amount = input(userInput);
            displayMessage("Enter Password: ");
            String password = input(userInput);
            bank.withdrawFromAccount(accountNumber,BigDecimal.valueOf(Long.parseLong(amount)),password);
        }catch (IllegalArgumentException | NullPointerException exception){
            encounterException(exception.getMessage());
        }
        displayMessage("\nWithdrawal Successful\n");
       afterEachSuccessfulTransaction();
    }
    private static void methodsEntryPoint(String lineBreak) throws InterruptedException {
        generateDelay();
        displayMessage("\n");
        displayDateAndTimeOfTheDay();
        displayMessage(lineBreak);
    }
    private static void successfulTransaction() throws InterruptedException {
        String option = input(userInput);
        switch (option.charAt(0)){
            case '1' : bankingServices();
            case '2' : System.exit(4);
            default  : System.exit(4);
        }
    }
    private static void depositing() throws InterruptedException {
        methodsEntryPoint("\n");
        displayMessage("=== Deposit Window ===\n");
        displayMessage("Enter Account Number: ");
        try {
            String accountNumber = input(userInput);
            displayMessage("Enter Depositing Amount: ");
            String amount = input(userInput);
            bank.depositToAccount(accountNumber,BigDecimal.valueOf(Long.parseLong(amount)));
        }catch (NullPointerException | IllegalArgumentException exception){
            encounterException(exception.getMessage());
        }
        displayMessage("\nDeposit Successful\n");
        afterEachSuccessfulTransaction();
    }
    private static void welcomeNote(){
        displayMessage("""
                **********************************************
                ==============================================
                Welcome To Wizard Bank, Let's Serve You Better
                ==============================================
                **********************************************
                """);
    }
    private static void registerNewCustomer() throws InterruptedException {
        methodsEntryPoint("\n== New Customer Window ==\n");
        displayMessage("Enter Firstname: ");
        try {
            String firstName = input(userInput);
            displayMessage("Enter Lastname: ");
            String lastName = input(userInput);
            displayMessage("Enter Password: ");
            String password = input(userInput);
            bank.registerCustomer(firstName,lastName,password);
            displayMessage("Dear " +firstName.toUpperCase()+" Your Account has been Created Successfully!\n");
        }catch (NullPointerException | IllegalArgumentException exception){
            encounterException(exception.getMessage());
        }
        displayMessage("Account Number is: " + bank.getAccountNumber()+ "\n");
        displayMessage("Date created is: " + bank.getTimeAndDateCreated()+"\n");
        bankingServices();
    }
    private static void generateDelay() throws InterruptedException {
        displayMessage("Loading, please wait");
        for (int index = 0; index < 3; index++) {
            Thread.sleep(1000);
            displayMessage(".");
            Thread.sleep(1000);
        }
    }
    private static void period_OfThe_Day(){
        Date date = new Date();
        int hour = date.getHours();
        if (hour <= 12) displayMessage("Good Morning!\n");
        if (hour >= 12) displayMessage("Good Afternoon!\n");
        if ( hour > 16) displayMessage("Good Evening!\n");
    }
    private static void displayDateAndTimeOfTheDay(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE hh:mm:ssa ");
        LocalDateTime localDateTime = LocalDateTime.now();
        displayMessage("Today is "+formatter.format(localDateTime));
    }
    private static String input(Scanner userInput){
        return userInput.nextLine();
    }
    private static void displayMessage(String message){
        System.out.printf("%s", message);
    }
}
