package bank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
public class BankTest {
    Bank bank;
    @BeforeEach void setUp(){ bank = new Bank();}
    @Test public void objectExist(){assertNotNull(bank);}
    @Test public void bankCanHelpCreateAccountThroughRegisterNewCustomer(){
        bank.registerCustomer("Dele", "Tayo", "1111");
        assertEquals(1, bank.getSizeOfAccounts());
    }
    @Test public void sizeOfAccountsIncreasesWhenBankRegisterTwoOrMoreCustomers(){
        bank.registerCustomer("Dele", "Tayo", "1111");
        bank.registerCustomer("Dele", "Tayo", "1111");
        bank.registerCustomer("Dele", "Tayo", "1111");
        assertEquals(3, bank.getSizeOfAccounts());
    }
    @Test public void bankCanDepositToAnExistingBankAndAlsoCheckBalance(){
        bank.registerCustomer("Dele", "Tayo", "1111");
        bank.depositToAccount("1122334450", BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(1000), bank.checkAccountBalance("1122334450", "1111"));
    }
    @Test public void bankCannotDepositToAnAccountThatDoesNotExist(){
        bank.registerCustomer("Dele", "Tayo", "1111");
        assertThrows(NullPointerException.class, () -> bank.depositToAccount("11223344500", BigDecimal.valueOf(1000)));
        assertEquals(BigDecimal.ZERO, bank.checkAccountBalance("1122334450", "1111"));
    }
    @Test public void emptyFieldForAccountNumberIsNotAllowed(){
        bank.registerCustomer("Dele", "Tayo", "1111");
        assertThrows(IllegalArgumentException.class, ()-> bank.depositToAccount(" ", BigDecimal.valueOf(1000)));
        assertThrows(NullPointerException.class, ()-> bank.depositToAccount("", BigDecimal.valueOf(1000)));
        assertThrows(IllegalArgumentException.class, ()-> bank.depositToAccount("erfjut6543", BigDecimal.valueOf(1000)));
    }
    @Test public void checkAccountRegisterInputForNoErrors(){
        assertThrows(NullPointerException.class, ()->bank.registerCustomer("Dele", "", "1111"));
        assertThrows(IllegalArgumentException.class, ()->bank.registerCustomer(" ", "Ola", "1111"));
        assertThrows(IllegalArgumentException.class, ()->bank.registerCustomer("Dele", "Ola", "333e"));
        assertThrows(IllegalArgumentException.class, ()->bank.registerCustomer("Dele", "Ola", "defy"));
        assertThrows(IllegalArgumentException.class, ()->bank.registerCustomer("Dele", "Ola", "333"));
        assertThrows(IllegalArgumentException.class, ()->bank.registerCustomer("Dele", "Ola", "334443"));
    }
}