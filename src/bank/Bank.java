package bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accounts = new ArrayList<>();
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
    private String generateAccountNumber(){
        return "112233445" + accounts.size();
    }
    public int getSizeOfAccounts() {
        return accounts.size();
    }
    public void depositToAccount(String accountNumber, BigDecimal amount) {
        stringContains(accountNumber);
        Account account = findAccountByAccountNumber(accountNumber);
        account.deposit(amount);
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
        lengthOfPassword(password);
        stringContains(accountNumber);
        Account account = findAccountByAccountNumber(accountNumber);
        return account.checkBalance(password);
    }
}
