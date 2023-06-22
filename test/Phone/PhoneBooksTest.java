package Phone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhoneBooksTest {
    PhoneBooks phoneBooks;
    @BeforeEach void setUp(){
        phoneBooks = new PhoneBooks("Dleex1212!");
    }
    @Test public void objectOfPhoneBooksExist(){
        assertNotNull(phoneBooks);
    }
    @Test public void phoneBooksCanCreatePhoneBook(){
        phoneBooks.createPhoneBook("userName", "userName111!1");
        assertEquals(1, phoneBooks.sizeOfPhoneBookInPhoneBooks());
    }
    @Test public void phoneBooksCanCreateMultiplePhoneBook(){
        phoneBooks.createPhoneBook("userName", "userName1!");
        phoneBooks.createPhoneBook("Dleex", "Dleex1111!");
        phoneBooks.createPhoneBook("Sultan", "Sultan1111!");
        assertEquals(3, phoneBooks.sizeOfPhoneBookInPhoneBooks());
    }
    @Test public void phoneBooksCanAllowPhoneBookUponCreationTakeTheMinimumOfEightLengthForPassword(){
        assertThrows(IllegalArgumentException.class,()->phoneBooks.createPhoneBook("userName", "Name1!"));
        assertEquals(0, phoneBooks.sizeOfPhoneBookInPhoneBooks());
    }
    @Test public void phoneBookPasswordLengthMustNotBeLongerThan13(){
        assertThrows(IllegalArgumentException.class,()->phoneBooks.createPhoneBook("userName", "userNameYYYYYYY!"));
        assertEquals(0, phoneBooks.sizeOfPhoneBookInPhoneBooks());
    }
    @Test public void phoneBookPasswordUponCreationMustContainNumberAndSpecialCharacter(){
        assertThrows(InputMismatchException.class, ()->phoneBooks.createPhoneBook("userName", "password"));
        assertEquals(0, phoneBooks.sizeOfPhoneBookInPhoneBooks());
    }
    @Test public void phoneBooksCannotTakeDuplicateUserName(){
        phoneBooks.createPhoneBook("userName", "userName1!");
        assertThrows(IllegalArgumentException.class, ()-> phoneBooks.createPhoneBook("userName", "userName2!"));
        assertEquals(1, phoneBooks.sizeOfPhoneBookInPhoneBooks());
    }
    @Test public void phoneBooksCanRemovePhoneFromIt(){
        phoneBooks.createPhoneBook("userName", "userName1!");
        phoneBooks.removePhone("userName", "Dleex1212!");
        assertEquals(0, phoneBooks.sizeOfPhoneBookInPhoneBooks());
    }
    @Test public void phoneBooksCanRemoveMultiplePhoneBook(){
        phoneBooks.createPhoneBook("userName", "userName1!");
        phoneBooks.createPhoneBook("Dleex", "userName1!");
        phoneBooks.createPhoneBook("Whitewizard", "userName1!");
        phoneBooks.removePhone("userName", "Dleex1212!");
        phoneBooks.removePhone("Whitewizard", "Dleex1212!");
        assertEquals(1, phoneBooks.sizeOfPhoneBookInPhoneBooks());
    }
    @Test public void phonesCannotRemovePhoneBookWithAWrongPassword(){
        phoneBooks.createPhoneBook("userName", "userName1!");
        assertEquals(1, phoneBooks.sizeOfPhoneBookInPhoneBooks());
        assertThrows(IllegalArgumentException.class,()->phoneBooks.removePhone("userName", "Dleex1242!"));
        assertEquals(1, phoneBooks.sizeOfPhoneBookInPhoneBooks());
    }
    @Test public void phoneBooksCanSearchThroughPhoneBook(){
        phoneBooks.createPhoneBook("userName", "userName1!");
       // phoneBooks.createPhoneBook("Sultan", "userName1!");
        assertEquals(1, phoneBooks.sizeOfPhoneBookInPhoneBooks());
        phoneBooks.createContact("userName","dele","Sam","09078121212");
        phoneBooks.searchContact("userName", "dele");
        String result = "[1 dele Sam]";
        assertEquals(result, phoneBooks.searchResult("userName"));
    }
    @Test public void phoneBooksCannotGetSpecifiedContactDetailsFromWhereItIsNotSpecified(){
        phoneBooks.createPhoneBook("userName", "userName1!");
        phoneBooks.createPhoneBook("Sultan", "userName1!");
        assertEquals(2, phoneBooks.sizeOfPhoneBookInPhoneBooks());
        phoneBooks.createContact("Sultan","dele","Sam","09078121212");
        phoneBooks.searchContact("Sultan", "Sam");
        String result = "[1 dele Sam]";
        assertThrows(NullPointerException.class, ()->phoneBooks.searchResult("userName"));
        assertEquals(result, phoneBooks.searchResult("Sultan"));
    }
    @Test public void phoneBooksCanThroughPhoneBooksInIt(){
        phoneBooks.createPhoneBook("userName", "userName1!");
        phoneBooks.createPhoneBook("Sultan", "userName1!");
        phoneBooks.createPhoneBook("Delex", "userName1!");
        assertEquals(3, phoneBooks.sizeOfPhoneBookInPhoneBooks());
        phoneBooks.createContact("Sultan","dele","Sam","09078121212");
        phoneBooks.createContact("Delex","dele","Sam","09078121212");
        phoneBooks.createContact("userName","dele","Sam","09078121212");
        assertThrows(NullPointerException.class,()-> phoneBooks.searchContact("Sultan", "Delex"));
    }
//    @Test public void
}

