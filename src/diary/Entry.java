package diary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Entry {
    private String title;
    private String body;
    private LocalDateTime timeCreated = LocalDateTime.now();
    public Entry(String title, String body) {
        this.title = title;
        this.body = body;
    }
    public void editGist(String gist, String title){
        this.body = gist;
        this.title = title;
    }
    public String getTitle(){return title;}
    public String getBody(){return body;}
    public void assignTimeCreated(String timeCreated) {
        this.timeCreated = LocalDateTime.parse(timeCreated);
    }
    private String formatTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE hh:mm:ssa");
        return formatter.format(timeCreated);
    }
    public String showDetails(){
        return String.format("""
                Title: %s
                Body: %s
                Time Created: %s
                """, title,body, formatTime());
    }
}