package contacts;

import java.time.LocalDateTime;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime createdTImeAndDate;
    public Contact(String firstName, String lastName, String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        createdTImeAndDate = LocalDateTime.now();
    }
    public String getPhoneNumber(){return phoneNumber;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public void modify(String firstName, String lastName, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
    }
}
