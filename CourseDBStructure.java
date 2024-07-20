import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Represents a hash table-based data structure to store and manage courses.
 * This structure supports adding, retrieving, and listing courses. 
 * Courses are stored in buckets based on their CRN using a hash table.
 * 
 * @author JlLi
 */
public class CourseDBStructure implements CourseDBStructureInterface {
	private LinkedList<CourseDBElement>[] hashTable;
	private int hashTableSize;
	
	/**
     * Constructs a CourseDBStructure with a size determined based on the specified initial capacity.
     * The size is adjusted to be a prime number and to fit the load factor constraints.
     * 
     * @param n the initial number of expected elements to determine the size of the hash table
     */
	public CourseDBStructure(int n) {
		double loadFactor = 1.5;
		hashTableSize = (int) (n / loadFactor);
		while (!isPrime(hashTableSize) || (hashTableSize - 3) % 4 != 0) {
			hashTableSize++;
		}
		hashTable = new LinkedList[hashTableSize];
	}

	/**
     * Constructs a CourseDBStructure with a specified hash table size.
     * 
     * @param testing a string indicating a testing mode (not used in the current implementation)
     * @param size the size of the hash table
     */
	public CourseDBStructure(String testing, int size) {
		hashTableSize = size;
		hashTable = new LinkedList[hashTableSize];
	}

	/**
     * Adds a course to the data structure. If a course with the same CRN already exists,
     * it is replaced with the new course.
     * 
     * @param element the CourseDBElement to be added to the data structure
     */
	@Override
	public void add(CourseDBElement element) {
		int index = getHashCode(element.getCRN());
		
		if (hashTable[index] == null) {
		    hashTable[index] = new LinkedList<>();
		}

		for (CourseDBElement e : hashTable[index]) {
		    if (e.getCRN() == element.getCRN()) {
		        hashTable[index].remove(e);
		        break; 
		    }
		}

		hashTable[index].add(element);
	}

	/**
     * Retrieves a course based on its CRN.
     * 
     * @param crn the CRN of the course to retrieve
     * @return the CourseDBElement with the specified CRN
     * @throws IOException if no course with the specified CRN is found
     */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		int index = getHashCode(crn);
        if (hashTable[index] != null) {
            for (CourseDBElement e : hashTable[index]) {
                if (e.getCRN() == crn) {
                    return e;
                }
            }
        }
        throw new IOException("CRN not found");
	}
	
	/**
     * Lists all courses in the data structure, sorted by the instructor's name.
     * 
     * @return a list of strings where each string represents a course in the format:
     *         "Course:ID CRN:CRN Credits:CREDITS Instructor:INSTRUCTOR Room:ROOM"
     */
	@Override
	public ArrayList<String> showAll() {
		ArrayList<CourseDBElement> allElements = new ArrayList<>();
	    for (LinkedList<CourseDBElement> bucket : hashTable) {
	        if (bucket != null) {
	            allElements.addAll(bucket);
	        }
	    }
	    allElements.sort(null); 
	    ArrayList<String> courseList = new ArrayList<>();
	    for (CourseDBElement element : allElements) {
	        courseList.add(element.toString());
	    }
	    return courseList;
	}

	/**
     * Retrieves the size of the hash table.
     * 
     * @return the size of the hash table
     */
	@Override
	public int getTableSize() {
		return hashTableSize;
	}
	
	/**
     * Checks if a number is a prime number.
     * 
     * @param number the number to check
     * @return true if the number is prime, false otherwise
     */
	private boolean isPrime(int number) {
		if (number <= 1) {
	        return false;
	    }
	    for (int i = 2; i <= Math.sqrt(number); i++) {
	        if (number % i == 0) {
	            return false;
	        }
	    }
	    return true;
	}
	
	/**
     * Computes the hash code for a given CRN.
     * 
     * @param crn the CRN for which to compute the hash code
     * @return the hash code for the CRN, adjusted to fit the hash table size
     */
	private int getHashCode(int crn) {
        int hashCode = Integer.toString(crn).hashCode();
        return Math.abs(hashCode % hashTableSize);
    }

}
