package Contacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContactTest {
    Contact contact;
    @BeforeEach void setUp(){contact = new Contact("firstName", "lastName", "phoneNumber");}
    @Test public void objectExist(){assertNotNull(contact);}
    @Test public void contactEditItState(){
        contact.editContact("Stephen", "lastName", "phoneNumber");
        String result = """
                Stephen\tlastName\tphoneNumber
                """;
        assertEquals(result, contact.showDetails());
    }
    @Test public void contactCanEdit(){
        contact.editContact("firstName", "lastName", "08063587905");
        String result = """
                firstName\tlastName\t08063587905
                """;
        assertEquals(result, contact.showDetails());
    }
}
