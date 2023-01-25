package p10_package;

/**
 * Binary Search Tree node class for managing student data
 * 
 * @author MichaelL
 *
 */
public class SimpleStudentClass
   {
    /**
     * Member field - name
     */
    public String name;
           
    /**
     * Member field - studentID
     */
    public int studentID;
           
    /**
     * Member field - gender
     */
    public char gender;
     
    /**
     * Member field - gpa
     */
    public double gpa;
     
    public SimpleStudentClass leftChildRef;
    
    public SimpleStudentClass rightChildRef;

    /**
     * Initialization constructor for data (overloaded)
     * 
     * @param copied SimpleStudentClass object to be copied
     * 
     */
    public SimpleStudentClass( SimpleStudentClass copied )
       {
        this( copied.name, copied.studentID, copied.gender, copied.gpa );
       }
     
    /**
     * Initialization constructor for data (overloaded)
     * 
     * @param inName String name of student to be input into object
     * 
     * @param inStudentID integer ID number of student to be input into object
     * 
     * @param inGender character gender of student to be input into object
     * 
     * @param inGPA double gpa of student to be input into object
     * 
     */
    public SimpleStudentClass( String inName, 
                           int inStudentID, char inGender, double inGPA )
       {
        name = inName;
         
        studentID = inStudentID;
         
        gender = inGender;
         
        gpa = inGPA;
         
        leftChildRef = null;
         
        rightChildRef = null;
       }
     
    /**
     * Overridden toString method
     */
    @Override
    public String toString()
       {
        return name + '/' + studentID + '/' + gender + '/' + gpa;           
       }      
   }