package studentsGradeAndSummary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StudentGradeTest {
    private StudentGrade studentGrade;
    @BeforeEach void startsWith(){
        studentGrade = new StudentGrade();
    }
    @Test void objectExist(){assertNotNull(studentGrade);}
    @Test public void numberOfStudentIsOneAndTakingTwoSubjects(){
        String expected ="[[0, 0]]";
        String actual = (Arrays.deepToString(studentGrade.numberOfStudentAndSubjectsOffered(1, 2)));
        assertEquals(expected,actual);
    }
    @Test public void numberOfStudentIsFourSubjectOfferedIS2Test(){
        String expected ="[[0, 0], [0, 0], [0, 0], [0, 0]]";
        String actual = (Arrays.deepToString(studentGrade.numberOfStudentAndSubjectsOffered(4, 2)));
        assertEquals(expected, actual);
    }
    @Test public void scoresAreUploadedAndCanBeSeenTest(){
        String expected = "[78, 78], [98, 98], [67, 67]";
        String actual = (Arrays.deepToString(studentGrade.numberOfStudentAndSubjectsOffered(3, 2)));
        int[][] array = new int[3][2];
        array[0][0] = 78;
        array[0][1] = 78;
        array[1][1] = 78;
        array[1][0] = 78;
        array[2][1] = 78;
        array[2][0] = 78;
        studentGrade.fillArray(array);
        assertEquals(expected, actual);
    }
}
