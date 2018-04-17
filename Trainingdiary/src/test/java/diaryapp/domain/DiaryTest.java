/*
 * 
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
        exe = new Exercise(5, type.JUOKSU , 10.5, 60, 17042018);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getIdWorks() {
        assertEquals(5, exe.getId());
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
    
}
