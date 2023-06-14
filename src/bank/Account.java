package bank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    private String firstName;
    private String lastName;
    private String password;
    private BigDecimal balance = BigDecimal.ZERO;
    private String accountNumber;
    private String timeAndDate;
    public Account(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
    public BigDecimal checkBalance(String password) {
        if (this.password.equals(password)) return balance;
        else throw new IllegalArgumentException("Incorrect Password");
    }
    public void deposit(BigDecimal amount) {
        int checkAmount = amount.compareTo(BigDecimal.ZERO);
        boolean isAmountChecker = checkAmount > 0;
        if (isAmountChecker) balance = balance.add(amount);
        else throw new IllegalArgumentException("input Amount is Zero or less");
    }
    public void withdraw(String password, BigDecimal amount) {
        int checkWithdrawAmount = amount.intValue();
        int checkBalance = balance.intValue();
        boolean confirm = checkWithdrawAmount > 0;
        boolean checker = checkWithdrawAmount <= checkBalance;
        if (this.password.equals(password)) {
            if (confirm) {
                if(checker) {balance = balance.subtract(amount);}
                else throw new IllegalArgumentException("Inputted Amount Exceed Balance.");
            }
            else throw new IllegalArgumentException("Amount Must Be Greater Than Zero");
        }
        else throw new IllegalArgumentException("Password Incorrect");
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String  getAccountNumber() {
        return accountNumber;
    }
    public String getTimeAndDate(){return timeAndDate;}
    public void setCreatedTimeAndDate(String timeAndDate) {
        this.timeAndDate = timeAndDate;
    }
}
