package InClass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BookTest {
    Book book;
    @BeforeEach public void setUp(){
        book = new Book("password");
    }
    @Test void objectOfClassExist(){assertNotNull(book);}
    @Test public void bookIsLockedByDefault(){
        assertTrue(book.isLocked());
    }
    @Test public void bookCanBeUnLockedWithTheCorrectPassword(){
        assertTrue(book.isLocked());
        book.unlock("password");
        assertFalse(book.isLocked());
    }
    @Test public void wrongPasswordOrUserNameCannotBeUsedToUnlockPassword(){
        assertTrue(book.isLocked());
        assertThrows(IllegalArgumentException.class,()->book.unlock("sword"));
    }
}
