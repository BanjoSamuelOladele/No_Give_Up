package Phone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneBookingTest {
    PhoneBooking phoneBooking;
    @BeforeEach void setUp(){
        phoneBooking= new PhoneBooking("userName", "password");
    }
    @Test public void objectExist(){
        assertNotNull(phoneBooking);
    }
    @Test public void phoneBookIsLockedByDefault(){
        assertTrue(phoneBooking.isLocked());
    }
    @Test public void phoneBookCanBeUnlockedWithTheRightPassword(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password","userName");
        assertFalse(phoneBooking.isLocked());
    }
    @Test public void phoneBookCannotBeUnlockedWithAWrongPassword(){
        assertTrue(phoneBooking.isLocked());
        assertThrows(IllegalArgumentException.class, ()->phoneBooking.unlock("word", "userName"));
        assertTrue(phoneBooking.isLocked());
    }
    @Test public void aWrongUserNameCannotBeUSedToUnlockPhoneBook(){
        assertTrue(phoneBooking.isLocked());
        assertThrows(IllegalArgumentException.class, ()->phoneBooking.unlock("password","username"));
        assertTrue(phoneBooking.isLocked());
    }
    @Test public void phoneBookCanBeLockedAfterUnlocking(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.lock();
        assertTrue(phoneBooking.isLocked());
    }
    @Test public void phoneBookCanCreateANewContact(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "11111111111");
        assertEquals(1, phoneBooking.size());
    }
    @Test public void phoneBookCanCreateMultipleContacts(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        assertEquals(4, phoneBooking.size());
    }
    @Test public void phoneBookCanSearchThroughContacts(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        phoneBooking.createContact("FirstName", "lastName", "08063587905");
        assertEquals(2, phoneBooking.size());
        phoneBooking.searchContact("firstName");
        assertEquals(2, phoneBooking.searchSize());
        String result = "[1 firstName lastName, 2 FirstName lastName]";
        assertEquals(result, phoneBooking.searchResult());
    }
    @Test public void phoneBookCanSearchThroughContactsWithOtherName(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        phoneBooking.createContact("FirstName", "lastName", "08063587905");
        phoneBooking.createContact("FirstName", "Dele", "08063587905");
        assertEquals(3, phoneBooking.size());
        phoneBooking.searchContact("Dele");
        assertEquals(1, phoneBooking.searchSize());
        String result = "[3 FirstName Dele]";
        assertEquals(result, phoneBooking.searchResult());
    }
    @Test public void PhoneBookCannotBringResultOfNoContactThatDoesNotExist(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        phoneBooking.createContact("FirstName", "lastName", "08063587905");
        phoneBooking.createContact("FirstName", "Dele", "08063587905");
        assertEquals(3, phoneBooking.size());
        assertThrows(NullPointerException.class, ()->phoneBooking.searchContact("Tayo"));
        assertEquals(0, phoneBooking.searchSize());
        assertThrows(NullPointerException.class,()->phoneBooking.searchResult());
    }
    @Test public void phoneBookCanSearchContactsWithPhoneNumber(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        assertEquals(1, phoneBooking.size());
        phoneBooking.searchContact("08063587905");
        assertEquals(1, phoneBooking.searchSize());
        assertEquals("[1 firstName lastName]", phoneBooking.searchResult());
    }
    @Test public void phoneGiveNoResultButResponseWhenPhoneNumberIsNotInContacts(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        assertEquals(1, phoneBooking.size());
        assertThrows(NullPointerException.class, ()->phoneBooking.searchContact("0806358790"));
        assertEquals(0, phoneBooking.searchSize());
        assertThrows(NullPointerException.class,()->phoneBooking.searchResult());
    }
    @Test public void phoneNumberDoesNotContainAlphabet_s(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        assertThrows(IllegalArgumentException.class,()->phoneBooking.createContact("firstName", "lastName", "word"));
        assertEquals(0, phoneBooking.size());
    }
    @Test public void phoneNumberCannotContainAlphabetWhenSearching(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        assertEquals(1, phoneBooking.size());
        assertThrows(IllegalArgumentException.class, ()->phoneBooking.searchContact("0806358790y"));
        assertEquals(0, phoneBooking.searchSize());
    }
    @Test public void searchCanBeMadeWithTheUniqueValue(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        assertEquals(1, phoneBooking.size());
        String expected = "lastName firstName 08063587905";
        System.out.println(expected);
        System.out.println(phoneBooking.searchByUniqueKey(1));
        assertEquals(expected, phoneBooking.searchByUniqueKey(1));
    }
    @Test public void searchByKeyValue() {
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        phoneBooking.createContact("firstName", "lastName", "44444444444");
        phoneBooking.createContact("firstName", "lastName", "08085678906");
        phoneBooking.createContact("firstName", "lastName", "33333333333");
        phoneBooking.createContact("firstName", "lastName", "20202020202");
        assertEquals(5, phoneBooking.size());
        String expected = "lastName firstName 08085678906";
        System.out.println(expected);
        System.out.println("DD "+phoneBooking.searchByUniqueKey(3));
        assertEquals(expected, phoneBooking.searchByUniqueKey(3));
    }
    @Test public void phoneBookCanDeleteContact(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        assertEquals(1, phoneBooking.size());
        phoneBooking.deleteContactByUniqueNumber(1);
        assertEquals(0, phoneBooking.size());
        assertThrows(NullPointerException.class, ()->phoneBooking.searchByUniqueKey(1));
        assertThrows(NullPointerException.class, ()-> phoneBooking.searchResult());
    }
    @Test public void phoneBookCanDeleteMultipleContacts(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        phoneBooking.createContact("Dele", "Sam", "1111111111111");
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        assertEquals(3, phoneBooking.size());
        phoneBooking.deleteContactByUniqueNumber(1);
        phoneBooking.deleteContactByUniqueNumber(3);
        assertEquals(1, phoneBooking.size());
        assertThrows(NullPointerException.class, ()->phoneBooking.searchByUniqueKey(1));
        assertThrows(NullPointerException.class, ()-> phoneBooking.searchResult());
        String expected = "Sam Dele 1111111111111";
        assertEquals(expected, phoneBooking.searchByUniqueKey(2));
    }
    @Test public void phoneBookCanModifyContact(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        phoneBooking.createContact("Dele", "Sam", "1111111111111");
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        assertEquals(3, phoneBooking.size());
        String edit = phoneBooking.modifyContact(1, "Ope","simen", "08063587905");
        String expected = "simen Ope 08063587905";
        assertEquals(expected, edit);
    }
    @Test public void phoneBookCanDisplayAllContacts(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        phoneBooking.createContact("Dele", "Sam", "1111111111111");
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        String showAll = phoneBooking.showContacts();
        String expected = """
                1\tlastName firstName 08063587905
                2\tSam Dele 1111111111111
                3\tlastName firstName 08063587905
                """;
        assertEquals(expected, showAll);
    }
    @Test public void canNotTakePhoneNumberLessThan11AndGreaterthan13(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        assertThrows(IllegalArgumentException.class, ()->phoneBooking.createContact("firstName", "lastName", "8063587905"));
        assertThrows(IllegalArgumentException.class,()->phoneBooking.createContact("firstName", "lastName", "23408063587905"));
    }
}
