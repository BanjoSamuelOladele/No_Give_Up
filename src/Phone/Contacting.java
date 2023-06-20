package Phone;

public class Contacting {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int keyValue;

    public Contacting(String firstName, String lastName, String phoneNumber) {
        edit(firstName,lastName,phoneNumber);
    }

    public void edit(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    public String showDetails(){
        return String.format("""
                %s
                %s
                %s
                """, lastName, firstName, phoneNumber);
    }
    public void assignKeyValue(int keyValue) {
        this.keyValue = keyValue;
    }
    public int getKeyValue(){return keyValue;}
    public String getName(){
        return firstName;
    }
    public String getOtherName(){
        return lastName;
    }
    public String getPhoneNumber() {return phoneNumber;}
}
