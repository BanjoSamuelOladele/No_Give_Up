package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account account;
    @BeforeEach
    void setUp() {account = new Account("Dele", "Tayo", "1111");}
    @Test public void objectOfAccountExist(){assertNotNull(account);}
    @Test public void checkBalanceWhenDepositWasMade(){
        account.deposit(BigDecimal.valueOf(1000));
        BigDecimal balance = account.checkBalance("1111");
        assertEquals(BigDecimal.valueOf(1000),balance);
    }
    @Test public void checkBalanceWhenAnotherDepositOfDifferentAmountWasMade(){
        account.deposit(BigDecimal.valueOf(2000));
        BigDecimal balance = account.checkBalance("1111");
        assertEquals(BigDecimal.valueOf(2000),balance);
    }
    @Test public void checkBalanceWhenMultipleDepositWasMade(){
        account.deposit(BigDecimal.valueOf(2000));
        account.deposit(BigDecimal.valueOf(1000));
        BigDecimal balance = account.checkBalance("1111");
        assertEquals(BigDecimal.valueOf(3000),balance);
    }
    @Test public void depositThrowsAnExceptionWhenANegativeValueIsEntered(){
        assertThrows(IllegalArgumentException.class, ()->account.deposit(BigDecimal.valueOf(-20)));
    }
    @Test public void accountBalanceCannotBeAccessedWithWrongPassword(){
        assertThrows(IllegalArgumentException.class, () -> account.checkBalance("2222"));
    }
    @Test public void withdrawalCanBeMadeInAccount(){
        account.deposit(BigDecimal.valueOf(2000));
        account.withdraw("1111", BigDecimal.valueOf(1000));
        BigDecimal balance = account.checkBalance("1111");
        assertEquals(BigDecimal.valueOf(1000),balance);
        account.withdraw("1111", BigDecimal.valueOf(500));
        assertEquals(BigDecimal.valueOf(500), account.checkBalance("1111"));
    }
    @Test public void wrongPasswordCannotBeUsedToWithdrawFromAccount(){
        account.deposit(BigDecimal.valueOf(2000));
        assertThrows(IllegalArgumentException.class, ()->account.withdraw("2222", BigDecimal.valueOf(1000)));
        assertEquals(BigDecimal.valueOf(2000), account.checkBalance("1111"));
    }
    @Test public void amountLessThanZeroCannotBeWithDrawnFromAccount(){
        account.deposit(BigDecimal.valueOf(2000));
        assertThrows(IllegalArgumentException.class, ()->account.withdraw("1111", BigDecimal.valueOf(-1000)));
        assertEquals(BigDecimal.valueOf(2000), account.checkBalance("1111"));
    }
    @Test public void amountGreaterThanAccountBalanceCannotBeWithdrawnFromAccount(){
        account.deposit(BigDecimal.valueOf(2000));
        assertThrows(IllegalArgumentException.class, ()->account.withdraw("1111", BigDecimal.valueOf(10000)));
        assertEquals(BigDecimal.valueOf(2000), account.checkBalance("1111"));
    }
    @Test public void checkAllAccountFunctionalityAtOnce(){
        account.deposit(BigDecimal.valueOf(2000));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(BigDecimal.valueOf(-200)));
        assertThrows(IllegalArgumentException.class, () -> account.checkBalance("2222"));
        assertEquals(BigDecimal.valueOf(2000), account.checkBalance("1111"));
        account.withdraw("1111",BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(1000), account.checkBalance("1111"));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw("2222", BigDecimal.valueOf(20)));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw("1111", BigDecimal.valueOf(-20)));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw("1111", BigDecimal.valueOf(200000)));
        account.withdraw("1111", BigDecimal.valueOf(500));
        assertEquals(BigDecimal.valueOf(500), account.checkBalance("1111"));
    }
}