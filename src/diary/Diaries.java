package diary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Diaries {
    private List<Diary> diary = new ArrayList<>();
    private LocalDateTime dateTimeCreated;
    public void addDiary(String username, String password) {
        Diary newDiary = new Diary(username, password);
        String time = String.valueOf(generatedTime());
        newDiary.setTime(time);
        diary.add(newDiary);
    }
    public String showDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE hh:mm:ssa");
        return formatter.format(dateTimeCreated);
    }
    private LocalDateTime generatedTime(){
        return dateTimeCreated = LocalDateTime.now();
    }
    public int sizeOfDiary() {
        return diary.size();
    }
    private Diary findInDiaryByUserName(String userName) {
        for (Diary newDiary : diary) if (newDiary.getUserName().equalsIgnoreCase(userName)) return newDiary;
        throw new NullPointerException("Diary does not exist");
    }
    public String searchForDiary(String username) {
        Diary newDiary = findInDiaryByUserName(username);
        return newDiary.getUserName();
    }
    public void deleteDiary(String username, String password) {
        for (Diary newDiary : diary) {
            if (newDiary.getPassword().equals(password)) {
                if (newDiary.getUserName().equals(username)) diary.remove(newDiary);
            }
        }
    }
}
