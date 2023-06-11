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
    @Test public void bankCantAcceptAlphabetsWhenDealingWithAmountAndBalance(){
        bank.registerCustomer("Dele", "Tayo", "1111");
        assertThrows(NumberFormatException.class, ()->bank.depositToAccount("1122334450", BigDecimal.valueOf(Long.parseLong("erfd"))));
    }
    @Test public void withdrawalCanBeMadeFromAccountThroughBank(){
        bank.registerCustomer("Dele", "Tayo", "1111");
        bank.depositToAccount("1122334450", BigDecimal.valueOf(2000));
        bank.withdrawFromAccount("1122334450", BigDecimal.valueOf(1000), "1111");
        assertEquals(BigDecimal.valueOf(1000),bank.checkAccountBalance("1122334450", "1111"));
    }
    @Test public void bankCanGrantTransferBetweenAccountsOfSameBank(){
        bank.registerCustomer("Dele", "Tayo", "1111");
        bank.registerCustomer("Dele", "Tayo", "2222");
        bank.depositToAccount("1122334450", BigDecimal.valueOf(2000));
        bank.transfer("1122334450", "1122334451", BigDecimal.valueOf(800), "1111");
        assertEquals(BigDecimal.valueOf(1200),bank.checkAccountBalance("1122334450", "1111"));
        assertEquals(BigDecimal.valueOf(800),bank.checkAccountBalance("1122334451", "2222"));
    }
    @Test public void accountNumberTakesToForm(){
        bank.registerCustomer("Dele", "Tayo", "1111");
        bank.registerCustomer("Dele", "Tayo", "1111");
        bank.registerCustomer("Dele", "Tayo", "1111");
        bank.registerCustomer("Dele", "Tayo", "1111");
        bank.registerCustomer("Dele", "Tayo", "1111");
        bank.registerCustomer("Dele", "Tayo", "1111");
        bank.registerCustomer("Dele", "Tayo", "1111");
        bank.registerCustomer("Dele", "Tayo", "1111");
        bank.registerCustomer("Dele", "Tayo", "1111");
        bank.registerCustomer("Dele", "Tayo", "1111");
        bank.registerCustomer("Dele", "Tayo", "1111");
        bank.depositToAccount("1223344510", BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(100), bank.checkAccountBalance("1223344510","1111"));
    }
    @Test public void testAllPossibilitiesWithBankAndAccount(){
        bank.registerCustomer("Dele", "Tayo", "1111");
        bank.registerCustomer("Dele", "Tayo", "2222");
        assertThrows(NullPointerException.class, ()->bank.transfer("122334451","11223344551",BigDecimal.valueOf(100),"1111"));
    }
}