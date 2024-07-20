import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CourseDBManager_STUDENT_Test {
    private CourseDBElement element1;
    private CourseDBElement element2;
    private CourseDBStructure structure;
    private CourseDBManager manager;

    @BeforeEach
    public void setUp() {
        element1 = new CourseDBElement("CS101", 12345, 4, "Room101", "Dr. Smith");
        element2 = new CourseDBElement("ENG202", 23456, 3, "Room202", "Prof. Jones");
        structure = new CourseDBStructure(10);
        manager = new CourseDBManager();
    }

    @AfterEach
    public void tearDown() {
        element1 = null;
        element2 = null;
        structure = null;
        manager = null;
    }

    @Test
    public void testCourseDBElement_GetID() {
        assertEquals("CS101", element1.getID());
    }

    @Test
    public void testCourseDBElement_GetCRN() {
        assertEquals(12345, element1.getCRN());
    }

    @Test
    public void testCourseDBElement_GetCredits() {
        assertEquals(4, element1.getCredits());
    }

    @Test
    public void testCourseDBElement_GetRoomNum() {
        assertEquals("Room101", element1.getRoomNum());
    }

    @Test
    public void testCourseDBElement_GetInstructorName() {
        assertEquals("Dr. Smith", element1.getInstructorName());
    }

    @Test
    public void testCourseDBElement_CompareTo() {
        assertTrue(element1.compareTo(element2) < 0);
    }

    @Test
    public void testCourseDBElement_ToString() {
        String expected = "\nCourse:CS101 CRN:12345 Credits:4 Instructor:Dr. Smith Room:Room101";
        assertEquals(expected, element1.toString());
    }

    @Test
    public void testCourseDBElement_HashCode() {
    	CourseDBElement element = new CourseDBElement("CS101", 12345, 4, "Room101", "Dr. Smith");
        assertEquals(Integer.toString(12345).hashCode(), element.hashCode());
    }

    @Test
    public void testCourseDBStructure_AddAndGet() throws IOException {
        structure.add(element1);
        assertEquals(element1, structure.get(12345));
    }

    @Test
    public void testCourseDBStructure_GetNonExistentCRN() {
        assertThrows(IOException.class, () -> {
            structure.get(99999);
        });
    }

    @Test
    public void testCourseDBStructure_ShowAll() {
        structure.add(element1);
        structure.add(element2);

        ArrayList<String> allCourses = structure.showAll();
        assertTrue(allCourses.contains(element1.toString()));
        assertTrue(allCourses.contains(element2.toString()));
    }

    @Test
    public void testCourseDBStructure_GetTableSize() {
        assertEquals(7, structure.getTableSize()); // Adjust based on your logic
    }

    @Test
    public void testCourseDBManager_AddAndGet() {
        manager.add("CS101", 12345, 4, "Room101", "Dr. Smith");
        CourseDBElement element = manager.get(12345);
        assertNotNull(element);
        assertEquals("CS101", element.getID());
    }

    @Test
    public void testCourseDBManager_GetNonExistentCRN() {
        assertNull(manager.get(99999));
    }

    @Test
    public void testCourseDBManager_ShowAll() {
        manager.add("CS101", 12345, 4, "Room101", "Dr. Smith");
        manager.add("ENG202", 23456, 3, "Room202", "Prof. Jones");

        ArrayList<String> allCourses = manager.showAll();
        assertTrue(allCourses.size() > 0);
    }

}