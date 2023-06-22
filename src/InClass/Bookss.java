package InClass;

import java.util.ArrayList;
import java.util.List;

public class Bookss {
    private List<Book> books = new ArrayList<>();
    private List<String> passInfo = new ArrayList<>();
    public void createBooks(Book book) {
        passInfo.add(book.getPassword());
        books.add(book);
    }
    public int size() {
        return books.size();
    }
    public int sizeOfAccounts() {
        return passInfo.size();
    }
    public boolean unlockAll() {
        for (Book book : books) {
            for (String pass : passInfo)
                book.unlock(pass);
            return book.isLocked();
        }
        return true;
    }
}
