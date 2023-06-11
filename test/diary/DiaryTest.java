package diary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryTest {
    Diary diary;
    @BeforeEach void setUp(){diary = new Diary("username", "password");}
    @Test public void objectExist(){assertNotNull(diary);}
    @Test public void diaryIsLockedByDefault(){
        assertTrue(diary.isLocked());
    }
    @Test public void diaryCanBeUnLockedW(){
        diary.unlock("password");
        assertFalse(diary.isLocked());
    }
    @Test public void diaryCanBeLockedAfterUnlocking(){
        diary.unlock("password");
        diary.lock();
        assertTrue(diary.isLocked());
    }
    @Test public void diaryCannotBeUnlockedWithAWrongPassword(){
        assertThrows(IllegalArgumentException.class, ()-> diary.unlock("dele"));
        assertTrue(diary.isLocked());
    }
    @Test public void diaryCanCreateEntry(){

    }
}
