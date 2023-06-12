package contacts;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    public Contact(String firstName, String lastName, String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getPhoneNumber(){return phoneNumber;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
}
