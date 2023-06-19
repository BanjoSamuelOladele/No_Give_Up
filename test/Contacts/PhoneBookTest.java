package Contacts;

import contacts.PhoneBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneBookTest {
    PhoneBooking phoneBooking;
    @BeforeEach void setUp(){
        phoneBooking = new PhoneBooking("userName", "password");
    }
    @Test public void objectExist(){assertNotNull(phoneBooking);}
    @Test public void phoneBookIsLockedByDefault(){
        assertTrue(phoneBooking.isLocked());
    }
    @Test public void phoneBookCanBeUnlockedWhenLocked(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password");
        assertFalse(phoneBooking.isLocked());
    }
    @Test public void phoneBookCannotBeUnlockedWithTheWrongPassword(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("word");
        assertTrue(phoneBooking.isLocked());
    }
    @Test public void phoneBookCanBeLockedAfterUnlocking(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.lock();
        assertTrue(phoneBooking.isLocked());
    }
    @Test public void phoneBookCanCreateContact(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "phoneNumber");
        assertEquals(1, phoneBooking.size());
    }
    @Test public void phoneBookCanCreateMultipleContacts(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "phoneNumber");
        phoneBooking.createContact("firstName", "lastName", "phoneNumber");
        phoneBooking.createContact("firstName", "lastName", "phoneNumber");
        phoneBooking.createContact("firstName", "lastName", "phoneNumber");
        assertEquals(4, phoneBooking.size());
    }
    @Test public void phoneBookCannotCreateContactWithAWrongPassword(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("word");
        assertTrue(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "phoneNumber");
        assertEquals(0, phoneBooking.size());
    }
    @Test public void phoneBookCanSearchContact(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("firstName", "lastName", "phoneNumber");
        assertEquals(1, phoneBooking.size());
        assertEquals(1, phoneBooking.findByName("firstName"));
        String result = String.format("""
                %s
                """, "firstName\tlastName\tphoneNumber");
        assertEquals(result, phoneBooking.contactsDetails());
    }
    @Test public void phoneBookCanSearchContact1(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("Name", "lastName", "08063587905");
        phoneBooking.createContact("firstName", "lastName", "phoneNumber");
        phoneBooking.createContact("firstName", "lastName", "phoneNumber");
        assertEquals(3, phoneBooking.size());
        assertEquals(2, phoneBooking.findByName("firstName"));
        String result = """
                firstName\tlastName\tphoneNumber
                firstName\tlastName\tphoneNumber
                """;
        assertEquals(result, phoneBooking.contactsDetails());
    }
    @Test public void phoneBookCannotSearchContactWithAWrongPassword(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("word");
        assertTrue(phoneBooking.isLocked());
        phoneBooking.createContact("Name", "lastName", "08063587905");
        phoneBooking.createContact("firstName", "lastName", "phoneNumber");
        phoneBooking.createContact("firstName", "lastName", "phoneNumber");
        assertEquals(0, phoneBooking.size());
        assertEquals(0, phoneBooking.findByName("firstName"));
    }
    @Test public void phoneBookCanUpdateContact(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("Name", "lastName", "08063587905");
        assertEquals(1, phoneBooking.size());
        phoneBooking.update(1, "Dele", "lastName", "08063587905");
        String result = """
                Dele\tlastName\t08063587905
                """;
        assertEquals(1, phoneBooking.findByName("Dele"));
        assertEquals(result, phoneBooking.contactsDetails());
    }
    @Test public void phoneBookCanUpdateContactAgain(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("Name", "lastName", "08063587905");
        phoneBooking.createContact("Name", "lastName", "08063587905");
        phoneBooking.createContact("Name", "lastName", "08063587905");
        phoneBooking.createContact("Name", "lastName", "08063587905");
        phoneBooking.createContact("Name", "lastName", "08063587905");
        phoneBooking.createContact("Name", "lastName", "08063587905");
        assertEquals(6, phoneBooking.size());
        phoneBooking.update(2, "Dele", "Hyde","2333");
        phoneBooking.update(5, "Dele", "Samuel", "09061117599");
        String result = """
                Dele\tHyde\t2333
                Dele\tSamuel\t09061117599
                """;
        assertEquals(2, phoneBooking.findByName("Dele"));
        assertEquals(result, phoneBooking.contactsDetails());
    }
    @Test public void phoneCanDeleteAContact(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("Name", "lastName", "08063587905");
        phoneBooking.createContact("Name", "lastName", "08063587905");
        phoneBooking.createContact("Name", "lastName", "08063587905");
        phoneBooking.createContact("Name", "lastName", "08063587905");
        phoneBooking.createContact("Name", "lastName", "08063587905");
        phoneBooking.createContact("Name", "lastName", "08063587905");
        assertEquals(6, phoneBooking.size());
        phoneBooking.deleteContact(2);
        phoneBooking.deleteContact(5);
        assertEquals(4,phoneBooking.size());
    }
    @Test public void phoneCanShowAllContacts(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("Dele", "lastName", "08063587905");
        assertEquals(1, phoneBooking.size());
        String result = """
                0\tDele\tlastName\t08063587905
                """;
        assertEquals(result, phoneBooking.showContacts());
    }
    @Test public void phoneCanShowAllContactsA(){
        assertTrue(phoneBooking.isLocked());
        phoneBooking.unlock("password");
        assertFalse(phoneBooking.isLocked());
        phoneBooking.createContact("Dele", "lastName", "08063587905");
        phoneBooking.createContact("Dele", "Samuel", "08063587905");
        phoneBooking.createContact("Dele", "lastName", "08063587905");
        phoneBooking.createContact("Dele", "lastName", "09061117599");
        phoneBooking.createContact("John", "lastName", "08063587905");
        assertEquals(5, phoneBooking.size());
        String result = """
                0\tDele\tlastName\t08063587905
                1\tDele\tSamuel\t08063587905
                2\tDele\tlastName\t08063587905
                3\tDele\tlastName\t09061117599
                4\tJohn\tlastName\t08063587905
                """;
        assertEquals(result, phoneBooking.showContacts());
    }
}
