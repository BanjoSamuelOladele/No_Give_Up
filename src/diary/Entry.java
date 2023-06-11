package diary;

public class Entry {
    private String title;
    private String body;
    public Entry(String title, String body) {
        this.title = title;
        this.body = body;
    }
    public String getTitle(){return title;}
    public String getBody(){return body;}
    public void setBody(String body){
        this.body = body;
    }
}
