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
        assertEquals("08063587905", result);
        //String string = phoneBook.searchContact("FirstName");
        //assertEquals("08057891707", string);
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
    @Test public void phoneBookCanDeleteContactFromContacts(){
        assertTrue(phoneBook.isLocked());
        phoneBook.unlock("password");
        phoneBook.createContact("Dele", "Ola", "08063587905");
        assertEquals(1, phoneBook.sizeOfContacts());
        phoneBook.deleteContact("08063587905");
        assertEquals(0, phoneBook.sizeOfContacts());
        assertThrows(PhoneNumberDoesNotExistException.class, ()->phoneBook.searchContact("08063587905"));
    }
    @Test public void phoneBookCanDeleteMultipleContacts(){
        assertTrue(phoneBook.isLocked());
        phoneBook.unlock("password");
        phoneBook.createContact("Dele", "Ola", "08063587905");
        phoneBook.createContact("Dele", "Dele", "08057891705");
        phoneBook.createContact("Sam", "Sam", "09061117599");
        phoneBook.createContact("Samuel", "Ola", "09046078272");
        phoneBook.createContact("Samuel", "Samuel", "22222");
        assertEquals(5, phoneBook.sizeOfContacts());
        phoneBook.deleteContact("09046078272");
        phoneBook.deleteContact("08057891705");
        phoneBook.deleteContact("08063587905");
        assertEquals(2, phoneBook.sizeOfContacts());
        assertThrows(PhoneNumberDoesNotExistException.class, ()->phoneBook.searchContact("08063587905"));
        assertThrows(PhoneNumberDoesNotExistException.class, ()->phoneBook.searchContact("08057891705"));
        assertThrows(PhoneNumberDoesNotExistException.class, ()->phoneBook.searchContact("09046078272"));
    }
    @Test public void phoneBookCanDeleteContactWithName(){
        assertTrue(phoneBook.isLocked());
        phoneBook.unlock("password");
        phoneBook.createContact("Dele", "Ola", "08063587905");
        assertEquals(1, phoneBook.sizeOfContacts());
        phoneBook.deleteContact("Dele Ola");
        assertEquals(0, phoneBook.sizeOfContacts());
        assertThrows(NameDoesNotExistException.class, ()->phoneBook.searchContact("Dele"));
    }
    @Test public void phoneBookCannotDeleteContactWhenItIsLockedAndTriedUnLockingWithWrongPassword(){
        assertTrue(phoneBook.isLocked());
        assertThrows(IncorrectPasswordException.class, ()->phoneBook.unlock("2020"));
        assertThrows(IncorrectPasswordException.class, ()->phoneBook.createContact("Dele", "Ola", "08063587905"));
        assertEquals(0, phoneBook.sizeOfContacts());
        assertThrows(IncorrectPasswordException.class, ()->phoneBook.deleteContact("Dele Ola"));
        assertEquals(0, phoneBook.sizeOfContacts());
    }
    @Test public void phoneBookCanChangePhoneNumberInContact(){
        assertTrue(phoneBook.isLocked());
        phoneBook.unlock("password");
        phoneBook.createContact("Dele", "Ola", "08063587905");
        assertEquals(1, phoneBook.sizeOfContacts());
        assertEquals("08063587905", phoneBook.searchContact("dele"));
        phoneBook.modifySavedContact("Dele", "Ope", "08063587905");
        assertEquals("Dele Ope", phoneBook.searchContact("08063587905"));
    }
    @Test public void setPhoneBookCannotModifyContactWhenItIsLocked(){
        assertTrue(phoneBook.isLocked());
        assertThrows(IncorrectPasswordException.class, ()->phoneBook.unlock("3333"));
        assertThrows(IncorrectPasswordException.class, ()->phoneBook.createContact("Dele", "Ola", "08063587905"));
        assertThrows(IncorrectPasswordException.class, ()->phoneBook.createContact("Sam", "Lo", "0880"));
        assertEquals(0, phoneBook.sizeOfContacts());
        assertThrows(IncorrectPasswordException.class, ()-> phoneBook.searchContact("dele"));
        assertThrows(IncorrectPasswordException.class, ()->phoneBook.modifySavedContact("Sam", "Lo", "09046078272"));
    }
    @Test public void confirmAllThrowableAreEffective(){
        assertTrue(phoneBook.isLocked());
        phoneBook.unlock("password");
        assertThrows(IllegalArgumentException.class, ()-> phoneBook.createContact("dele", "sam", "0807yh6"));
        assertEquals(0, phoneBook.sizeOfContacts());
    }
}
