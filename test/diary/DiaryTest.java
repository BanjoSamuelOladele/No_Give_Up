package diary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryTest {
    Diary diary;
    Diaries diaries;
    @BeforeEach void setUp(){
        diary = new Diary("username", "password");
        diaries = new Diaries();
    }
    @Test public void objectExist(){assertNotNull(diary);}
    @Test public void diaryIsLockedByDefault(){
        assertTrue(diary.isLocked());
    }
    @Test public void diaryCanBeUnLockedW(){
        diary.unlock("username","password");
        assertFalse(diary.isLocked());
    }
    @Test public void diaryCanBeLockedAfterUnlocking(){
        diary.unlock("username","password");
        diary.lock();
        assertTrue(diary.isLocked());
    }
    @Test public void diaryCannotBeUnlockedWithAWrongPassword(){
        assertThrows(IllegalArgumentException.class,
                ()-> diary.unlock("username","dele"));
        assertTrue(diary.isLocked());
    }
    @Test public void diaryCanCreateEntry(){
        assertTrue(diary.isLocked());
        diary.unlock("username","password");
        assertFalse(diary.isLocked());
        diary.createEntry("title","body");
        assertEquals(1, diary.sizeOfEntry());
    }
    @Test public void diaryCanCreateMultiple_Entries(){
        assertTrue(diary.isLocked());
        diary.unlock("username","password");
        assertFalse(diary.isLocked());
        diary.createEntry("title","body");
        diary.createEntry("title","body");
        diary.createEntry("title","body");
        assertEquals(3, diary.sizeOfEntry());
    }
    @Test public void diaryCanFindEntryByTitle(){
        assertTrue(diary.isLocked());
        diary.unlock("username","password");
        assertFalse(diary.isLocked());
        diary.createEntry("title","body");
        assertEquals("body", diary.searchEntryByTitle("title"));
    }
    @Test public void diaryCanSearchThroughEntryThroughTitle(){
        assertTrue(diary.isLocked());
        diary.unlock("username","password");
        assertFalse(diary.isLocked());
        diary.createEntry("title","body");
        diary.createEntry("Pains,GainsAndProgress","we");
        assertEquals("we", diary.searchEntryByTitle("Pains,GainsAndProgress"));
    }
    @Test public void diaryCanTellIfAnEntryDoesNotExist(){
        assertTrue(diary.isLocked());
        diary.unlock("username","password");
        assertFalse(diary.isLocked());
        diary.createEntry("title","body");
        diary.createEntry("Pains,GainsAndProgress","we");
        assertThrows(NullPointerException.class, ()->diary.searchEntryByTitle("Rows"));
    }
    @Test public void diaryCanDeleteEntryThroughTitle(){
        assertTrue(diary.isLocked());
        diary.unlock("username","password");
        assertFalse(diary.isLocked());
        diary.createEntry("title","body");
        diary.deleteEntry("title");
        assertEquals(0, diary.sizeOfEntry());
        assertThrows(NullPointerException.class, ()->diary.searchEntryByTitle("title"));
    }
    @Test public void diaryCannotDeleteAnEntryThatDoesNotExist(){
        assertTrue(diary.isLocked());
        diary.unlock("username","password");
        assertFalse(diary.isLocked());
        diary.createEntry("title","body");
        assertThrows(NullPointerException.class, ()->diary.deleteEntry("MyBook"));
    }
    @Test public void diaryCanUpdateBodyOfAnEntryThroughTitle(){
        assertTrue(diary.isLocked());
        diary.unlock("username","password");
        assertFalse(diary.isLocked());
        diary.createEntry("title","Ola");
        assertEquals("Ola", diary.searchEntryByTitle("title"));
        diary.updateEntryByTitle("title", "body");
        assertEquals("body", diary.searchEntryByTitle("title"));
    }
    @Test public void diariesCanAddDiary(){
        diaries.addDiary("username", "password");
        assertEquals(1, diaries.sizeOfDiary());
    }
    @Test public void diariesCanAddMultipleDiary(){

        diaries.addDiary("username", "password");
        diaries.addDiary("username", "password");
        diaries.addDiary("username", "password");
        diaries.addDiary("username", "password");
        diaries.addDiary("username", "password");
        assertEquals(5, diaries.sizeOfDiary());
    }
    @Test public void diariesCanSearchIfADiaryExistInitThroughUsername(){
        diaries.addDiary("MyPhone", "password");
        diaries.addDiary("Shadow", "password");
        diaries.addDiary("username", "password");
        assertEquals("MyPhone", diaries.searchForDiary("MyPhone"));
    }
    @Test public void diariesCanSearchIfADiaryExistInitThroughUserName(){
        diaries.addDiary("MyPhone", "password");
        diaries.addDiary("Shadow", "password");
        diaries.addDiary("username", "password");
        assertEquals("Shadow", diaries.searchForDiary("Shadow"));
    }
    @Test public void diariesCannotReturnDiaryThatDoesNotExistInIt(){
        assertThrows(NullPointerException.class, () -> diaries.searchForDiary("title"));
    }
    @Test public void diariesCanDeleteDiary(){
        diaries.addDiary("MyPhone", "password");
        diaries.addDiary("Shadow", "password1");
        assertEquals(2, diaries.sizeOfDiary());
        diaries.deleteDiary("MyPhone", "password");
        assertEquals("Shadow", diaries.searchForDiary("Shadow"));
        assertEquals(1, diaries.sizeOfDiary());
        assertThrows(NullPointerException.class, ()-> diaries.searchForDiary("MyPhone"));
    }
}
