import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages a collection of courses using the CourseDBStructure.
 * Provides methods to add courses, retrieve courses by CRN, read courses from a file,
 * and list all courses in the data structure.
 * 
 * @author JLi
 */

public class CourseDBManager implements CourseDBManagerInterface {
	private CourseDBStructure cds;
	
	/**
     * Constructs a CourseDBManager with a default CourseDBStructure.
     * Initializes the internal CourseDBStructure with a size of 20.
     */
	public CourseDBManager() {
		cds = new CourseDBStructure(20);
	}
	
	/**
     * Adds a course to the data structure.
     * 
     * @param id the course ID
     * @param crn the CRN of the course
     * @param credits the number of credits for the course
     * @param roomNum the room number where the course is held
     * @param instructor the name of the instructor for the course
     */
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement cde = new CourseDBElement(id, crn, credits, roomNum, instructor);
		cds.add(cde);
	}

	/**
     * Retrieves a course from the data structure based on its CRN.
     * 
     * @param crn the CRN of the course to retrieve
     * @return the CourseDBElement with the specified CRN, or null if not found
     */
	@Override
	public CourseDBElement get(int crn) {
		try {
			return cds.get(crn);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
     * Reads courses from a file and adds them to the data structure.
     * The file is expected to have lines in the format:
     * "ID CRN CREDITS ROOM INSTRUCTOR"
     * 
     * @param input the file containing course data
     * @throws FileNotFoundException if the specified file does not exist
     */
	@Override
	public void readFile(File input) throws FileNotFoundException {
		Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ", 5);
            String id = tokens[0];
            int crn = Integer.parseInt(tokens[1]);
            int credits = Integer.parseInt(tokens[2]);
            String roomNum = tokens[3];
            String instructor = tokens[4];
            add(id, crn, credits, roomNum, instructor);
        }
        scanner.close();
	}
	
	/**
     * Lists all courses in the data structure.
     * 
     * @return a list of strings where each string represents a course in the format:
     *         "Course:ID CRN:CRN Credits:CREDITS Instructor:INSTRUCTOR Room:ROOM"
     */
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> courseList = cds.showAll();
	    return courseList;
	}

}
