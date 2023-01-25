/**
 * 
 */
package p6_package;

/**
 * @author Chris Cisneros
 *
 */
public class StudentClass 
{
    /**
     * Gender data for class
     */
    public char gender;
    
    /**
     * GPA data for class
     */
    public double gpa;
    
    /**
     * Name data for class
     */
    public String name;
    
    /**
     * Next reference for linked list
     */
    public StudentClass nextRef;
    
    /**
     * Student ID data for class
     */
    int studentID;
    
    /**
     * Initialization constructor for data
     * <p>
     * Note: Class does not require a default constructor
     * 
     * @param inName - name of student to be input into object
     * @param inStudentID - ID number of student to be input into object
     * @param inGender - gender of student to be input into object
     * @param inGPA - gpa of student to be input into object
     */
    public StudentClass( String inName, int inStudentID, 
    		char inGender, double inGPA )
    {
        // Sets the name as the entered name
        name = inName;
        
        // Sets the student id as the entered id
        studentID = inStudentID;
        
        // Sets the gender as the entered gender
        gender = inGender;
        
        // Sets the gpa as the entered gpa
        gpa = inGPA;
        
        // Sets the default next node as null
        nextRef = null;
    }
    
    /**
     * Copy constructor
     * Calls other constructor with copied data
     * 
     * @param copied - StudentClass object to be copied
     */
    public StudentClass( StudentClass copied )
    {
        // Calls the default constructor with the copied fields
        this( copied.name, copied.studentID, copied.gender, copied.gpa );
    }
    
    /**
     * Compares student objects
     * <p>
     * Note: Compares name as class key; returns integer result such that 
     * if this name is less than other name, a negative number is returned; 
     * if this name is greater than other name, a positive number is returned; 
     * if this name is equal to, and the same length as other name, zero 
     * is returned
     * 
     * @param other - StudentClass object to be compared with this object
     * @return - integer difference between the names
     */
    public int compareTo( StudentClass other )
    {
        // Declares the variables needed for this method
        String fName = name;
        String sName = other.name;
        int fSize = fName.length();
        int sSize = sName.length();
        int difference, strDex = 0;
        
        // Loop that runs till the strDex is larger than one of the strings
        while( strDex < fSize && strDex < sSize )
        {
            // Compares the same letter 
            difference = fName.charAt( strDex ) - 
            		sName.charAt( strDex );
            
            // Checks to see if there was a difference
            if( difference != 0 )
            {
                // Returns the difference
                return difference;
            }
            
            // Increments the strDex
            strDex++;
        }
        
        // Returns if the first string is larger or not
        return fSize - sSize;
    }
    
    /**
     * Overrides Object toString with local
     * 
     * @Override - toString in class java.lang.Object
     */
    public String toString()
    {
        // Returns the objects fields separated by /'s
        return name + '/' + studentID + '/' + gender + '/' + gpa;
    }
}
