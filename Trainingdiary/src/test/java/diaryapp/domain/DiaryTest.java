/*
 * Exercise, DiaryService ja SportType luokan testit
 */
package diaryapp.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import diary.domain.Exercise;
import diary.domain.SportType;

public class DiaryTest {
    
    Exercise exe;
    Exercise exe2;
    SportType type;
    
    public DiaryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        exe = new Exercise(2, type.JUOKSU , 10.5, 60, 17042018);
        exe2 = new Exercise(type.HIIHTO, 5.5, 40, 18052018);
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void getTypeWorks() {
        SportType s = null;       
        assertEquals(s.JUOKSU, exe.getType());
    }
    
    @Test
    public void getKmWorks() {
        assertEquals(10.5, exe.getKm(), 0.001);
    }
    
    @Test
    public void getDurationWorks() {
        assertEquals(60.0, exe.getDuration(), 0.001);
    }
    
    @Test
    public void getDateWorks() {
        assertEquals(17042018, exe.getDate());
    }
    @Test
    public void getTypeWorks2() {
        SportType s = null;       
        assertEquals(s.HIIHTO, exe2.getType());
    }
    
    @Test
    public void getKmWorks2() {
        assertEquals(5.5, exe2.getKm(), 0.001);
    }
    
    @Test
    public void getDurationWorks2() {
        assertEquals(40.0, exe2.getDuration(), 0.001);
    }
    
    @Test
    public void getDateWorks2() {
        assertEquals(18052018, exe2.getDate());
    }
    @Test
    public void getIdWorks(){
        assertEquals(2,exe.getId());
    }
    
    @Test
    public void getContentWorks(){
        assertEquals("17042018, JUOKSU, 10.5, 60.0", exe.getContent());
    }
    
}
