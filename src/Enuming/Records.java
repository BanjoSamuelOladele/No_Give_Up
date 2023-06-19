package Enuming;

public enum Records {
    JHTP("java how to program", "2018"),
    CPPHTP("C++ how to program", "2002");

    private String title;
    private String publishedYear;
    Records(String title, String publishedYear) {
        this.publishedYear =publishedYear;
        this.title = title;
    }
    public String getTitle(){return title;}
    public String getPublishedYear(){return publishedYear;}
}
