package contacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneBookTest {
    PhoneBook phoneBook;
    @BeforeEach void setUp(){phoneBook = new PhoneBook("password");}
    @Test public void objectOfClassExist(){assertNotNull(phoneBook);}
    @Test public void phoneBookIsLockedByDefault(){
        assertTrue(phoneBook.isLocked());
    }
    @Test public void phoneBookCanBeUnlockedWithPassword(){
        phoneBook.unlock("password");
        assertFalse(phoneBook.isLocked());
    }
    @Test public void phoneBookCanBeLockedAfterUnlocking(){
        assertTrue(phoneBook.isLocked());
        phoneBook.unlock("password");
        phoneBook.lock();
        assertTrue(phoneBook.isLocked());
    }
    @Test public void phoneBookCanNotBeUnlockedWithWrongPassword(){
        assertTrue(phoneBook.isLocked());
        assertThrows(IncorrectPasswordException.class, ()->phoneBook.unlock("jjjj"));
        assertTrue(phoneBook.isLocked());
    }
    @Test public void phoneBookCanAddContact(){
        assertTrue(phoneBook.isLocked());
        phoneBook.unlock("password");
        phoneBook.createContact("FirstName", "LastName", "08063587905");
        assertEquals(1, phoneBook.sizeOfContacts());
    }
    @Test public void phoneBookCanCreateMultipleContacts(){
        assertTrue(phoneBook.isLocked());
        phoneBook.unlock("password");
        phoneBook.createContact("FirstName", "LastName", "08063587905");
        phoneBook.createContact("FirstName", "LastName", "08063587905");
        phoneBook.createContact("FirstName", "LastName", "08063587905");
        phoneBook.createContact("FirstName", "LastName", "08063587905");
        assertEquals(4, phoneBook.sizeOfContacts());
    }
    @Test public void phoneBookCannotCreateContactsWithWrongPassword(){
        assertTrue(phoneBook.isLocked());
        assertThrows(IncorrectPasswordException.class, ()->phoneBook.unlock("kkkk"));
        assertThrows(IncorrectPasswordException.class, ()->phoneBook.createContact("FirstName", "LastName", "08063587905"));
        assertEquals(0, phoneBook.sizeOfContacts());
    }
    @Test public void phoneBookCanSearchContactThroughPhoneNumber(){
        assertTrue(phoneBook.isLocked());
        phoneBook.unlock("password");
        phoneBook.createContact("FirstName", "LastName", "08063587905");
        String result = phoneBook.searchContact("08063587905");
        assertEquals("FirstName LastName", result);
    }
    @Test public void phoneBookCanSearchThroughManyContactsToBringResult(){
        assertTrue(phoneBook.isLocked());
        phoneBook.unlock("password");
        phoneBook.createContact("FirstName", "LastName", "08063587905");
        phoneBook.createContact("firstName", "LastName", "08057891707");
        assertEquals(2, phoneBook.sizeOfContacts());
        String result = phoneBook.searchContact("08057891707");
        assertEquals("firstName LastName", result);
    }
    @Test public void phoneBookCanSearchThroughManyContactsToBringResultByName(){
        assertTrue(phoneBook.isLocked());
        phoneBook.unlock("password");
        phoneBook.createContact("FirstName", "LastName", "08063587905");
        phoneBook.createContact("firstName", "LastName", "08057891707");
        assertEquals(2, phoneBook.sizeOfContacts());
        String result = phoneBook.searchContact("firstName");
        assertEquals("08057891707", result);
    }
    @Test public void phoneBookThrowsExceptionWhenTheSearchDoesNotExist(){
        assertTrue(phoneBook.isLocked());
        phoneBook.unlock("password");
        phoneBook.createContact("Dele", "Oladele", "08063587905");
        phoneBook.createContact("Sam", "Samuel", "08057891707");
        assertEquals(2, phoneBook.sizeOfContacts());
        assertThrows(PhoneNumberDoesNotExistException.class, ()->phoneBook.searchContact("080635879056"));
        assertThrows(NameDoesNotExistException.class, ()->phoneBook.searchContact("sir"));
    }
}
