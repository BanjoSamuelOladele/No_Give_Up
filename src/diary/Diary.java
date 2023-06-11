package diary;

public class Diary {
    private String password;
    private boolean isLocked = true;
    public Diary(String username, String password) {
        this.password = password;
    }
    public boolean isLocked() {
        return isLocked;
    }
    public void unlock(String password) {
        if (this.password.equals(password)) isLocked = false;
        else throw new IllegalArgumentException("Password does not match");
    }
    public void lock() {
        isLocked = true;
    }
}
