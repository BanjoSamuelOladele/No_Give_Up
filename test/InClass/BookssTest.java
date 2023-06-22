package InClass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookssTest {
    Bookss bookss;

    @BeforeEach
    void setUp() {
        bookss = new Bookss();
    }

    @Test
    void objectExist() {
        assertNotNull(bookss);
    }

    @Test
    public void bookssCanCreateBooks() {
        Book book = new Book("password");
        bookss.createBooks(book);
        assertEquals(1, bookss.size());
        assertEquals(1, bookss.sizeOfAccounts());
    }

    @Test
    public void bookssCanUnlockBook() {
        Book book = new Book("password");
        bookss.createBooks(book);
        assertEquals(1, bookss.sizeOfAccounts());
        assertFalse(bookss.unlockAll());
    }
    @Test
    public void bookssCanGetAllBooks() {
        Book book = new Book("password");
        bookss.createBooks(book);
        assertEquals(1, bookss.sizeOfAccounts());
        bookss.unlockAll();
    }
    @Test
    public void bookssCanGetAllBooksA() {
        Book book2 = new Book("password");
        Book book1 = new Book("word1");
        Book book = new Book("word");
        bookss.createBooks(book);
        bookss.createBooks(book1);
        bookss.createBooks(book2);
        assertEquals(3, bookss.sizeOfAccounts());
        for (int index = 0; index < bookss.size(); index++){
            assertFalse(bookss.unlockAll());
        }
    }
}
