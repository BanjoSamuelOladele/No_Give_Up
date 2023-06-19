package Contacts;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int keyValue;
    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    public void editContact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    public String showDetails(){
        return String.format("""
                %s
                """,firstName+"\t"+lastName+"\t"+phoneNumber);
    }
    public void assignKeyValue(int value) {keyValue = value;}
    public String getName(){return firstName;}
    public int getKeyValue(){return keyValue;}
}
