package bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accounts = new ArrayList<>();
    private String accountNumber;
    public void registerCustomer(String firstName, String lastName, String password) {
        emptyEntry(firstName);
        emptyEntry(lastName);
        stringContains(password);
        lengthOfPassword(password);
        String accountNumber = generateAccountNumber();
        Account account = new Account(firstName, lastName, password);
        account.setAccountNumber(accountNumber);
        accounts.add(account);
    }
    public String getAccountNumber(){
        return accountNumber;
    }
    private String generateAccountNumber(){
        accountNumber = "112233445" + accounts.size();
        if (accountNumber.length() > 10) accountNumber = accountNumber.substring(1);
        return accountNumber;
    }
    public int getSizeOfAccounts() {
        return accounts.size();
    }
    public void depositToAccount(String accountNumber, BigDecimal amount) {
        stringContains(accountNumber);
        checkAmount(amount);
        Account account = findAccountByAccountNumber(accountNumber);
        account.deposit(amount);
    }
    private void checkAmount(BigDecimal amount){
       // for (char a: amount.toString().toCharArray()){if (!Character.isDigit(a)) throw new NumberFormatException("Amount Contains Alphabet(s)");}
        String stringAmount = amount.toString();
        for (char stringAmt : stringAmount.toCharArray()) if (!Character.isDigit(stringAmt)) throw new NumberFormatException("Amount Contains Alphabet(s)");
    }
    private void lengthOfPassword(String inputs){
        if (inputs.length() != 4) throw new IllegalArgumentException("Password must be 4 digits long");
    }
    private void stringContains(String inputs){
        emptyEntry(inputs);
        containsAlphabet(inputs);
    }
    private void containsAlphabet(String inputs){
        for (char input : inputs.toCharArray()) if (!Character.isDigit(input)) throw new IllegalArgumentException("Account number contains alphabet(s)");
    }
    private void emptyEntry(String inputs) {
        if (inputs == null) throw new NullPointerException("Null is not acceptable");
        if (inputs.isEmpty()) throw new NullPointerException("Empty Input");
        if (inputs.isBlank()) throw new IllegalArgumentException("Space Not Allowed");
    }
    private Account findAccountByAccountNumber(String accountNumber){
        for (Account account : accounts) if (account.getAccountNumber().equals(accountNumber))return account;
        throw new NullPointerException("Account does not exist");
    }
    public BigDecimal checkAccountBalance(String accountNumber, String password) {
        stringContains(password);
        lengthOfPassword(password);
        stringContains(accountNumber);
        Account account = findAccountByAccountNumber(accountNumber);
        return account.checkBalance(password);
    }
    public void withdrawFromAccount(String accountNumber, BigDecimal amount, String password) {
        stringContains(accountNumber);
        stringContains(password);
        lengthOfPassword(password);
        checkAmount(amount);
        Account account = findAccountByAccountNumber(accountNumber);
        account.withdraw(password,amount);
    }
    public void transfer(String senderAccountNumber, String receiverAccountNUmber, BigDecimal amount, String password) {
        withdrawFromAccount(senderAccountNumber, amount,password);
        depositToAccount(receiverAccountNUmber,amount);
    }
}
