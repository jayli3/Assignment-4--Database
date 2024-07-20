/**
 * Represents a course element with details such as course ID, CRN, credits, room number, and instructor's name.
 * This class implements the {@link Comparable} interface to allow comparison of course elements based on the instructor's name.
 * 
 * @author JLi
 */
public class CourseDBElement implements Comparable<CourseDBElement> {
	private String courseID;
	private int CRN;
	private int numOfCredits;
	private String roomNumber;
	private String instructorName;
	
	/**
     * Creates a new CourseDBElement with the specified details.
     * 
     * @param courseID the identifier for the course
     * @param CRN the unique course registration number
     * @param numOfCredits the number of credits for the course
     * @param roomNumber the room where the course is conducted
     * @param instructorName the name of the instructor for the course
     */
	public CourseDBElement(String courseID, int CRN, int numOfCredits, String roomNumber,
							String instructorName) {
		this.courseID = courseID;
		this.CRN = CRN;
		this.numOfCredits = numOfCredits;
		this.roomNumber = roomNumber;
		this.instructorName = instructorName;
	}
	
	/**
     * Default constructor for CourseDBElement. Initializes fields with default values.
     */
	public CourseDBElement() {
        this("", 0, 0, "", "");
    }
	
	/**
     * Retrieves the course ID.
     * 
     * @return the course ID
     */
	public String getID() {
	    return courseID;
	}

	/**
     * Retrieves the course registration number (CRN).
     * 
     * @return the CRN
     */
	public int getCRN() {
	    return CRN;
	}
	
	/**
     * Sets the course registration number (CRN).
     * 
     * @param CRN the new CRN value
     */
	public void setCRN(int CRN) {
		this.CRN = CRN;
	}

	/**
     * Retrieves the number of credits for the course.
     * 
     * @return the number of credits
     */
	public int getCredits() {
	    return numOfCredits;
	}

	/**
     * Retrieves the room number where the course is held.
     * 
     * @return the room number
     */
	public String getRoomNum() {
	    return roomNumber;
	}

	/**
     * Retrieves the name of the course instructor.
     * 
     * @return the instructor's name
     */
	public String getInstructorName() {
	    return instructorName;
	}

	/**
     * Compares this course with another based on the instructor's name.
     * 
     * @param other the course to compare with
     * @return a negative integer, zero, or a positive integer as this course's instructor name is less than, equal to, or greater than the specified course's instructor name
     */
	@Override
	public int compareTo(CourseDBElement o) {
	    return this.instructorName.compareTo(o.instructorName);
	}

	/**
     * Provides a string representation of the course.
     * 
     * @return a string describing the course's details
     */
    @Override
    public String toString() {
        return "\nCourse:" + courseID + " CRN:" + CRN + " Credits:" + numOfCredits + " Instructor:" + instructorName + " Room:" + roomNumber;
    }
    
    /**
     * Computes the hash code for this course based on its CRN.
     * This ensures that the hash code is consistent with the CRN, allowing for efficient storage and retrieval in a hash table.
     * 
     * @return the hash code for the course
     */
    @Override
    public int hashCode() {
        return Integer.toString(CRN).hashCode();
    }

}
