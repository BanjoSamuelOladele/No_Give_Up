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
        phoneBooking.createContact("firstName", "lastName", "08063587905");
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
        phoneBooking.searchByName("firstName");
        assertEquals(2, phoneBooking.searchSize());
        String result = "[FirstName lastName, firstName lastName]";
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
        phoneBooking.searchByName("Dele");
        assertEquals(1, phoneBooking.searchSize());
        String result = "[FirstName Dele]";
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
        assertThrows(NullPointerException.class, ()->phoneBooking.searchByName("Tayo"));
        assertEquals(0, phoneBooking.searchSize());
        assertThrows(NullPointerException.class,()->phoneBooking.searchResult());
    }
    @Test public void phoneBookCanSearchContactsWithPhoneNumber(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        assertEquals(1, phoneBooking.size());
        phoneBooking.searchByPhoneNumber("08063587905");
        assertEquals(1, phoneBooking.searchSize());
        assertEquals("[firstName lastName]", phoneBooking.searchResult());
    }
    @Test public void phoneGiveNoResultButResponseWhenPhoneNumberIsNotInContacts(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        assertEquals(1, phoneBooking.size());
        assertThrows(NullPointerException.class, ()->phoneBooking.searchByPhoneNumber("0806358790"));
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
        assertThrows(IllegalArgumentException.class, ()->phoneBooking.searchByPhoneNumber("0806358790y"));
        assertEquals(0, phoneBooking.searchSize());
    }
    @Test public void searchCanBeMadeWithTheUniqueValue(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password", "userName");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "08063587905");
        assertEquals(1, phoneBooking.size());
        phoneBooking.searchByUniqueKey(1);
        assertEquals(1, phoneBooking.searchSize());
        assertEquals("[firstName lastName 08063587905]",phoneBooking.searchResult());
    }
}
