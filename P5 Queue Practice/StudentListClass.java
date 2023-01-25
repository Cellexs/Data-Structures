/**
 * 
 */
package p5_package;

/**
 * @author Chris Cisneros
 *
 */
public class StudentListClass 
{
    
    /**
     * Member data
     */
    private int arrayCapacity;
    
    /**
     * Member data
     */
    private int arraySize;
    
    /**
     * Default constant capacity
     */
    private static final int DEFAULT_CAPACITY = 10;
    
    /**
     * For N not found in search
     */
    public static  final int NOT_FOUND = -1;
    
    /**
     * Member - StudentClass array
     */
    private StudentClass[] studentArray;
    
    /**
     * Default constructor, initializes array to default capacity
     */
    public StudentListClass()
    {
        // Sets the array capacity to the default capacity
        arrayCapacity = DEFAULT_CAPACITY;
        
        // Initializes the array to the capacity
        studentArray = new StudentClass[arrayCapacity];
        
        // Sets the array size to 0, since its empty right now
        arraySize = 0;
    }
    
    /**
     * Initializing constructor, initializes array to specified capacity
     * <p>
     * Note: Does not allow capacity less than default capacity
     * 
     * @param capacity - integer initial capacity specification for the array
     */
    public StudentListClass( int capacity )
    {
        // Checks if the capacity is less than default
        if (capacity < DEFAULT_CAPACITY)
        {
            // Forces the capacity to be default
            arrayCapacity = DEFAULT_CAPACITY;
        }
        else
        {
            // Sets the array capacity to the needed capacity
            arrayCapacity = capacity;
        }
        
        // Initializes the array to the capacity
        studentArray = new StudentClass[arrayCapacity];
        
        // Sets the array size to 0, since its empty right now
        arraySize = 0;
    }
    
    /**
     * Copy constructor, initializes array to size of copied array, 
     * and capacity of copied array, then copies only the elements up to 
     * the given size
     * 
     * @param copied - StudentListClass object to be copied 
     */
    public StudentListClass( StudentListClass copied )
    {
        // Declares variables for use in this method
        int arrDex;
        
        // Copies the capacity and size of the old array
        arrayCapacity = copied.arrayCapacity;
        arraySize = copied.arraySize;
        
        // Initializes the new array
        studentArray = new StudentClass[arrayCapacity];
        
        // For loop that copies that data from old array to new array
        for (arrDex = 0; arrDex < arraySize; arrDex++)
        {
            /*
             *  Creates, copies and adds the StudentClass objects in the 
             *  copied array to the new array
             */
            studentArray[arrDex] = new StudentClass
            		(copied.studentArray[arrDex]);
        }
    }
    
    /**
     * Sets data at end of list
     * <p>
     * Note: Uses insertDataAtNthPosition
     * 
     * @param student - StudentClass object to be appended to list
     */
    public void appendDataAtEnd( StudentClass student )
    {
        // Creates the variables needed for this method
        boolean beginFlag = true;
        
        // Checks to see if the array is empty
        if ( isEmpty() )
        {
            // Adds the element to the start
            insertDataAtBeginning( student );
            
            // Sets the flag to false
            beginFlag = false;
        }
        
        // Checks if the flag was triggered
        if ( beginFlag )
        {
            // Checks if it needs resizing
            checkForResize();
            
            // Inserts data at the nth position (arraySize) using method
            insertDataAtNthPosition( arraySize + 1, student );
        }
    }
    
    /**
     * Checks for need to resize; if this is necessary, creates new array 
     * with double the original capacity, loads data from original array 
     * to new one, then sets studentArray to new array
     */
    private void checkForResize()
    {
        // Creates the variables needed for this method
        int arrDex;
        StudentClass[] tempArray;
        
        // Checks for size being equal to capacity
        if( arrayCapacity == arraySize )
        {
            // Updates the array capacity global variable
            arrayCapacity = arrayCapacity * 2;
            
            // Initializes the temporary array
            tempArray = new StudentClass[arrayCapacity];
            
            // For loop to copy the data
            for ( arrDex = 0; arrDex < arraySize; arrDex++)
            {
                // Copies data to the temp array
                tempArray[arrDex] = studentArray[arrDex];
            }
            
            // Assigns the temp array to student array
            studentArray = tempArray;
        }
    }
    
    /**
     * Clears array of all valid values by setting array size to zero, 
     * values remain in array but are not accessible
     */
    public void clear()
    {
        // Sets the array size to 0
        arraySize = 0;
    }
    
    /**
     * Displays student list
     */
    public void displayList()
    {
        // Instantiates a new variable for the array index
        int arrDex;
        	
        // For loop that indexes through the array
        for (arrDex = 0; arrDex < arraySize; arrDex++)
        {
            // Converts the object to a string and then prints on its own line
            System.out.println( studentArray[arrDex].toString() );
        }
    }
    
    /**
     * Gets number of student if found in list
     * 
     * @param student - StudentClass object for finding N
     * @return - integer N of the StudentClass object, or NOT_FOUND if not in 
     * list
     */
    public int findStudentNumber( StudentClass student )
    {
        // Declares the variables needed for this method
        int arrDex;
        
        // For loop to index through array
        for (arrDex = 0; arrDex < arraySize; arrDex++)
        {
            // Compares every student to the desired student
            if (studentArray[arrDex].compareTo(student) == 0)
            {
                // Returns index of the desired student if found
                return arrDex + 1;
            }
        }
        
        // Returns not found if student is never found
        return NOT_FOUND;
    }
    
    /**
     * Gets current size of array
     * <p>
     * Note: size of array indicates number of valid or viable values in the 
     * array
     * 
     * @return - integer size of array
     */
    public int getCurrentSize()
    {
        // Returns the array size
        return arraySize;
    }
    
    /**
     * Acquires the Nth item in the list, starting with N = 1
     * 
     * @param N_value - integer value to identify Nth student to retrieve
     * @return - StudentClass value at element or null if attempt to acquire 
     * data out of bounds
     */
    public StudentClass getNthStudent( int N_value )
    {
        // Checks if N_value is a valid index
        if( N_value > arraySize || N_value < 0 )
        {
            // Returns null if the Nth value isnt valid
            return null;
        }
        
        // Returns the element offset by the starting N = 1
        return studentArray[N_value - 1];
    }
    
    /**
     * Sets data at beginning of list; moves all subsequent data up by one element
     * <p>
     * Note: No failure mode; data will be set at beginning no matter what the 
     * size of the array is
     * 
     * @param student - StudentClass object to set at beginning
     */
    public void insertDataAtBeginning( StudentClass student )
    {
        // Declares the variables needed for this method
        int arrDex;
        
        // Checks if a resize is needed
        checkForResize();
        
        // For loop that indexes backwards
        for (arrDex = arraySize; arrDex > 0; arrDex--)
        {
            // Moves student objects up 
            studentArray[arrDex] = studentArray[arrDex - 1];
        }
        
        // Adds the student to the first element
        studentArray[0] = student;
        
        // Increments the size
        arraySize++;
    }
    
    /**
     * Moves data up one element, then sets item in array at specified Nth 
     * position, where N starts at 1
     * <p>
     * Note: Allows item to be appended to end of list
     * 
     * @param NthPos - integer value to indicate which position (N) at which 
     * to insert student data
     * @param student - StudentClass object to be inserted at Nth position 
     * (N - 1 in the array)
     * @return - Boolean success if inserted, or failure if incorrect N was 
     * used
     */
    public boolean insertDataAtNthPosition( int NthPos, StudentClass student )
    {
        // Declares the variables needed for this method
        int arrDex;
        
        // Checks if NthPos is a valid placement
        if( NthPos  > arraySize + 1 || NthPos  < 0 )
        {
            // Returns false if the NthPos wasn't valid
            return false;
        }
        
        // Checks if a resize is needed
        checkForResize();
        
        // For loop that indexes backwards and offsets by N starting at 1
        for (arrDex = arraySize; arrDex >= NthPos; arrDex--)
        {
            // Moves student objects up 
            studentArray[arrDex] = studentArray[arrDex - 1];
        }
        
        // Adds the student to the first element
        studentArray[NthPos - 1] = student;
        
        // Increments the size
        arraySize++;
        
        // It was added so return true
        return true;
    }
    
    /**
     * Tests for size of array equal to zero, no valid values stored in array
     * 
     * @return - Boolean result of test for empty
     */
    public boolean isEmpty()
    {
        // Returns true if its 0 (empty) or false if it isn't 0
        return arraySize == 0;
    }
    
    /**
     *  Removes Nth item from array if index within array size bounds
     *  
     * @param numberN - integer number of element value to be removed, starts 
     * at N = 1
     * @return - removed StudentClass value if successful, null if not
     */
    public StudentClass removeNthStudent( int numberN )
    {
        // Declares the variables needed for this method
        int arrDex;
        StudentClass removedStd;
        
        // Checks if numberN is a valid index
        if( numberN - 1 > arraySize || numberN - 1 < 0 )
        {
            // Returns null if numberN isn't valid
            return null;
        }
        
        // Saves the data on the student to be removed
        removedStd = studentArray[numberN - 1];
        
        // For loop that indexes backwards and offsets by N starting at 1
        for (arrDex = numberN - 1; arrDex < arraySize - 1; arrDex++)
        {
            // Moves student objects down
            studentArray[arrDex] = studentArray[arrDex + 1];
        }
        
        // Decrements the size
        arraySize--;
        
        // Returns the removed student
        return removedStd;
    }
    
    /**
     *  Replaces item in array at specified Nth position, where N starts at 1
     *  
     * @param NthPos - integer value to indicate Nth position at which to 
     * replace student data
     * @param student - StudentClass object to be inserted at Nth position 
     * (N - 1 in the array)
     * @return - Boolean success if inserted, or failure if incorrect N was 
     * used
     */
    public boolean replaceDataAtNthPosition( int NthPos, StudentClass student )
    {
        // Checks if NthPos is a valid placement
        if( NthPos - 1 > arraySize || NthPos - 1 < 0 )
        {
            // Returns false if the NthPos wasn't valid
            return false;
        }
        
        // Replaces the NthPos - 1 element with new student
        studentArray[NthPos - 1] = student;
        
        // It removed the correct NthPos element
        return true;
    }
}
