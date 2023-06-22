package InClass;

public class Book {
    private String password;
    private String userName;
    private boolean isLocked = true;
    public Book(String password) {
        this.password =password;
        this.userName = userName;
    }
    public boolean isLocked() {
        return isLocked;
    }
    public void unlock(String password) {
        boolean check = this.password.equals(password);
        if (check) isLocked = false;
        else throw new IllegalArgumentException("Password or UserName mismatch");
    }
    public String getUserName(){return userName;}
    public String getPassword(){return password;}
}
