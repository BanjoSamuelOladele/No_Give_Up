package diary;

import java.util.ArrayList;
import java.util.List;

public class Diary {
    private String password;
    private List<Entry> entries = new ArrayList<>();
    private int size;
    private boolean isLocked = true;
    public Diary(String username, String password) {
        this.password = password;
    }
    public boolean isLocked() {
        return isLocked;
    }
    public void unlock(String password) {
        if (this.password.equals(password)) isLocked = false;
        else throw new IllegalArgumentException("Password does not match");
    }
    public void lock() {
        isLocked = true;
    }
    public void createEntry(String title, String body) {
        Entry entry = new Entry(title, body);
        entries.add(entry);
    }
    public int sizeOfEntry() {
        return entries.size();
    }
    private Entry findEntryByTitleInEntry(String title){
        for (Entry entry : entries) if (entry.getTitle().equals(title)) return entry;
        throw new NullPointerException("Entry " + title + " does not exist");
    }
    public String searchEntryByTitle(String title) {
        Entry entry = findEntryByTitleInEntry(title);
        return entry.getBody();
    }
    public void deleteEntry(String title) {
        Entry entry = findEntryByTitleInEntry(title);
        entries.remove(entry);
    }
    public void updateEntryByTitle(String title, String body) {
        Entry entry = findEntryByTitleInEntry(title);
        entry.setBody(body);
        entries.add(entry);
    }
}
