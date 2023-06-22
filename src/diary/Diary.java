package diary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Diary {
    private String password;
    private List<Entry> entries = new ArrayList<>();
    private boolean isLocked = true;
    private String userName;
    private LocalDateTime dateAndTimeCreated;
    private String showAllEntry;
    public Diary(String username, String password) {
        userName = username;
        this.password = password;
    }
    public String getPassword(){return password;}
    public String getUserName(){return userName;}
    public boolean isLocked() {return isLocked;}
    public void unlock(String userName,String password) {
        if (this.userName.equals(userName) && this.password.equals(password))
            isLocked = false;
        else throw new IllegalArgumentException("Password or username does not match");
    }
    private void showAllEntryInADiary(){
        if (!isLocked) {
            for (int index = 0; index < entries.size(); index++) {
                showAllEntry +=
                        ((index + 1)+ " " + entries.get(index).showDetails() +"\n");
            }
//            for (Entry entry : entries)
//                showAllEntry += entry.showDetails() + "\n";
        }else throw new IllegalArgumentException("Password is wrong");
    }
    public String showAllGist(){
        showAllEntryInADiary();;
        return showAllEntry;
    }
    public String tineCreated(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE hh:mm:ssa");
        return formatter.format(dateAndTimeCreated);
    }
    private LocalDateTime generateTime(){
        return dateAndTimeCreated = LocalDateTime.now();
    }
    public void lock() {isLocked = true;}
    public void createEntry(String title, String body) {
        if (!isLocked) {
            Entry entry = new Entry(title, body);
            String timeCreated = String.valueOf(generateTime());
            entry.assignTimeCreated(timeCreated);
            boolean check = titleDuplicateNotAllowed(entry);
            if (!check) entries.add(entry);
        }else throw new IllegalArgumentException("username or password not match");
    }
    private boolean titleDuplicateNotAllowed(Entry title){
        String title1 = title.getTitle();
        for (Entry entry : entries)
            if (entry.getTitle().equals(title1))
                throw new IllegalArgumentException("Cannot create Gist because title already exist");
        return false;
    }
    public int sizeOfEntry() {
        if (!isLocked) return entries.size();
        else throw new IllegalArgumentException("Diary is locked!");
    }
    private Entry findEntryByTitleInEntry(String title){
        for (Entry entry : entries) if (entry.getTitle().equalsIgnoreCase(title)) return entry;
        throw new NullPointerException("Entry " + title + " does not exist");
    }
    public String searchEntryByTitle(String title) {
        if (!isLocked) {
            Entry entry = findEntryByTitleInEntry(title);
            return entry.getBody();
        } else throw new IllegalArgumentException("Diary is locked");
    }
    public void deleteEntry(String title) {
        if (!isLocked) {
            showAllEntry = "";
            Entry entry = findEntryByTitleInEntry(title);
            entries.remove(entry);
        }else throw new IllegalArgumentException("Diary is locked");
    }
    public void updateEntryByTitle(String title, String body) {
        if (!isLocked) {
            Entry entry = findEntryByTitleInEntry(title);
            entry.editGist(body,title);
        }else throw new IllegalArgumentException("Diary is locked");
    }
}
