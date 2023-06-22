package diary;

public class Entry {
    private String title;
    private String body;
    private String timeCreated;
    public Entry(String title, String body) {
        this.title = title;
        this.body = body;
    }
    public String getTitle(){return title;}
    public String getBody(){return body;}
    public void setBody(String body){
        this.body = body;
    }
    public void assignTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }
    public String showDetails(){
        return String.format("""
                Title: %s
                Body: %s
                Time Created: %s
                """, title,body, timeCreated);
    }
}