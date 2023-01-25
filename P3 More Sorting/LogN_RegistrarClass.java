/**
 * 
 */
package p3_package;

/**
 * @author Chris Cisneros
 *
 */
public class LogN_RegistrarClass 
{
    /**
     * Private default and size capacity
     */
    private int capacity;
	
    /**
     * Constant default capacity
     */
    private final int DEFAULT_CAPACITY = 10;
	
    /**
     * Constant used if item not found in the array
     */
    private final int NOT_FOUND = -1;
	
    /**
     * Private capacity and size data
     */
    private int size;
	
    /**
     *  Private array holding student data
     */
    private StudentClass[] studentArr;
	
    /**
     * Default Constructor
     */
    public LogN_RegistrarClass()
    {
        capacity = DEFAULT_CAPACITY;
        studentArr = new StudentClass[capacity];
        size = 0;
    }
    
    /**
     * Copy Constructor
     * @param copied - LogN_RegistrarClass object to be copied
     */
    public LogN_RegistrarClass( LogN_RegistrarClass copied )
    {
        capacity = copied.capacity;
        size = copied.size;
        studentArr = new StudentClass[capacity];
        copyArrayData(studentArr, copied.studentArr, 0, size - 1);
    }
	
    /**
     * Adds a StudentClass item to list
     * <p>
     * Note: Overloaded method
     * 
     * @param newStudent - StudentClass object to be added to array
     */
    public void addStudent( StudentClass newStudent )
    {  
        // Checks if the array needs to be resized and resizes if necessary
    	checkForResize();
    	// Assigns the student to the current size index of the array
        studentArr[size] = newStudent;
        // Increments the size variable
        size++;
    }
	
    /**
     * Creates a StudentClass item, then adds to list using other method
     * <p>
     * Note: Uses anonymous StudentClass instantiation in call to other 
     * method; one line of code
     * Note: Overloaded method
     * 
     * @param stdName - String name of student
     * @param stdId - integer student ID of student
     * @param stdGender - character gender of student
     * @param stdGPA - double GPA of student
     */
    public void addStudent( String stdName, int stdId, 
    		char stdGender, double stdGPA )
    {
        // Creates a new student class object and stores the entered data
        StudentClass newStudent = new StudentClass(stdName, stdId, 
        		stdGender, stdGPA);
    
        // Adds the student to the array with the addStudent method
        addStudent(newStudent);
	}
	
    /**
     * Checks array capacity to verify there is room to accept new data; 
     * if array is at capacity, resizes array and copies data as needed to 
     * double capacity of the array
     */
    private void checkForResize()
    {
        // Creates variables for use in this method
        int ARRAY_START = 0;
        StudentClass[] tempArray;
        
        // Checks if the array is at max capacity
        if (size == capacity)
        {
            // Creates a temp array to hold the current array
            tempArray = new StudentClass[capacity];
            
            // Copies the original over to the temp array
            copyArrayData(tempArray, studentArr, ARRAY_START, size - 1);
            
            // Updates the capacity to the new capacity
            capacity = capacity * 2;
            
            // Recreates the original array at double the capacity
            studentArr = new StudentClass[capacity];
            
            // Copies the data in temp back over to the resized original array
            copyArrayData(studentArr, tempArray, ARRAY_START, size - 1);
        }
    }
	
    /**
     * Copies student list from one array to other
     * <p>
     * Note: Must create new StudentClass object to assign to each element 
     * to destination array to eliminate aliasing
     * 
     * @param dest - StudentClass array to which data is copied
     * @param source - StudentClass array from which data is copied
     * @param lowIndex - integer index at which to start copying from source 
     * array, inclusive
     * @param highIndex - integer index at which to end copying from source 
     * array, inclusive
     */
    private void copyArrayData( StudentClass[] dest,
            StudentClass[] source, int lowIndex, int highDex )
    {   
        // Creates a variable to index through the array
        int arrDex;
        int destDex;
        
        // Initializes the destination dex to 0
        destDex = 0;
        
        /*
         *  Creates a for loop to create a non-aliased copy of the data 
         *  from source to dest
         */
        for(arrDex = lowIndex; arrDex <= highDex; arrDex++)
        {
    	    // Creates a new object with the same data as the original
    	    dest[destDex] = new StudentClass(source[arrDex]);
    	    
    	    // Increments the destination index
    	    destDex++;
        }
    }
	
    /**
     * Optional method, local array dump for diagnostics
     */
    public void diagnosticArrayDump()
    {
        // Instantiates a new variable for the array index
        int arrDex;
        	
        // For loop that indexes through the array
        for (arrDex = 0; arrDex < size; arrDex++)
        {
            // Converts the object to a string and then prints on its own line
        	System.out.println(studentArr[arrDex].toString());
        }
    }
	
    /**
     * Finds student in array, returns data
     * 
     * @param student - StudentClass object to be found
     * @return - StudentClass object found, or null if not found
     */
    public StudentClass findStudent( StudentClass student )
    {
        // Creates a variable to hold the returned student index
        int stdDex;
     	
        // Calls the findStudentIndex method to get the student index
        stdDex = this.findStudentIndex(student);
    	
        // Checks to see if the student was ever found
        if (stdDex == NOT_FOUND)
        {
    	    // Returns null if it stdDex was never changed aka not found
    	    return null;
        }
    	
        // Returns the student object at the found index
        return studentArr[stdDex];
    }
	
    /**
     * Finds student's index in array, returns index, or returns NOT_FOUND 
     * if item is not in the array
     * 
     * @param student - StudentClass object to be found
     * @return - index of StudentClass object, or NOT_FOUND
     */
    public int findStudentIndex( StudentClass student )
    {
        // Creates a student index variable that is initialized to NOT_FOUND
        int stdDex = NOT_FOUND;
        // Declares an arrDex for indexing through the array
        int arrDex;
    			
        // For loop to search through all elements of the array
        for (arrDex = 0; arrDex < size; arrDex++)
        {
            // Compares the searched for student obj to all objs in the array
            if ( studentArr[arrDex].compareTo(student) == 0 )
            {
                // Sets the stdDex to the arrDex when the student was found
                stdDex = arrDex;
            }
        }
    	
        // Returns the stdDex
        return stdDex;
    }
    
    /**
     * Removes student from array, shifts elements down to keep array 
     * contiguous
     * 
     * @param student - StudentClass object to be removed
	 * @return - StudentClass object that was removed, or null if not found
     */
    public StudentClass removeStudent( StudentClass student )
    {
        // Creates variables to hold the returned student index and array index
        int stdDex;
        int arrDex;
        
        // Creates a variable to hold the removed student
        StudentClass removedStd;
        		
        // Calls the findStudentIndex method to get the student index
        stdDex = this.findStudentIndex(student);
        		
        // Checks to see if the student was ever found
        if (stdDex == NOT_FOUND)
        {
            // Returns null if it stdDex was never changed aka not found
            return null;
        }
	    
        // Stores the removed student before he gets removed from the array
        removedStd = new StudentClass(studentArr[stdDex]);
        
        // For loop that iterates through the array
        for (arrDex = 0; arrDex < size; arrDex++)
        {
            // Starts the process at the removed items index
            if (arrDex >= stdDex)
            {
                /*
                 * Swaps the current item with the item ahead of it until
                 * the removed item ends up in the last element of the array
                 * (Very much like a bubble sort bubbling the item to the top)
                 */
                swapValues(studentArr, arrDex, arrDex + 1);
            }
        }
	    
	    // Sets the last element in the array to null since it was removed
	    studentArr[capacity - 1] = null;
	    
	    // Decreases the size of the array since it lost an element
	    size--;
	    
	    // Returns the removed element
	    return removedStd;
    }
	
    /**
     * Merges StudentClass values brought in between a low and high index 
     * segment (inclusive) of an array
     * Creates local temporary array for the exact size needed to conduct 
     * a merge
     * 
     * @param workingArray - StudentClass array to be managed by method
     * @param lowIndex - lowest index of array segment to be managed
     * @param middleIndex - middle index of array segment to be managed
     * @param highIndex - high index of array segment to be managed
     */
     private void runMerge( StudentClass[] workingArray, int lowIndex,
    		int middleIndex, int highIndex )
    {
        // Declares variables use for this method
        int firstSubLimit, secondSubLimit;
        int firstSubDex, secondSubDex;
        int workingDex;
        int localCapacity;
        StudentClass[] tempArray;
        
        // Initializes the localCapacity as highIndex - lowIndex
        localCapacity = highIndex - lowIndex + 1;
        
        // Initializes the temp array to needed capacity
        tempArray = new StudentClass[localCapacity];
        
        // Copies the data over to the temp array
        copyArrayData(tempArray, workingArray, lowIndex, highIndex);
        
        // Finds the limits of both the sub arrays
        firstSubLimit = (localCapacity - 1) / 2;
        secondSubLimit = localCapacity - 1;
        
        // Sets indexes to the starts of their respective sub arrays
        firstSubDex = 0;
        secondSubDex = firstSubLimit + 1;
        
        // Sets the index for the workingArray
        workingDex = lowIndex;
        
        // While loop that runs while objects can be compared
        while(firstSubDex <= firstSubLimit && secondSubDex <= secondSubLimit)
        { 
            // Checks to see which StudentClass Object is lesser
            if(tempArray[firstSubDex].compareTo
            		(tempArray[secondSubDex]) < 0)
            {
        		// Adds the first array object at first array index
        	    workingArray[workingDex] = tempArray[firstSubDex];
                // Increments the first array index
        	    firstSubDex++;
            }
            else
            {
                // Adds the second array object at the second array index
                workingArray[workingDex] = tempArray[secondSubDex];
                // Increments the second array index
                secondSubDex++;
            }
        	// Increments the index of the workingArray
            workingDex++;
        }
        
        // While loop for if the first array has leftover objects
        while(firstSubDex <= firstSubLimit)
        {
            // Adds the item to the working array
        	workingArray[workingDex] = tempArray[firstSubDex];
            // Increments the firstSubDex
        	firstSubDex++;
        	// Increments the working
        	workingDex++;
        }
        
        // While loop for if the second array has leftover objects
        while(secondSubDex <= secondSubLimit)
        {
        	// Adds the item to the working array
        	workingArray[workingDex] = tempArray[secondSubDex];
            // Increments the secondSubDex
        	secondSubDex++;
        	// Increments the working
        	workingDex++;
        }
    }
    
    /**
     * StudentClass data sorted using merge sort algorithm
     * <p>
     * Note: Calls runMergeSortHelper with lower and upper indices of array
     * to be sorted
     * Note: Creates new StudentClass array, sorts contents of array, and
     * returns the sorted result; does not modify (this) object student array
     * 
     * @return - StudentClass array containing sorted data
     */
    public StudentClass[] runMergeSort()
    {
        // Creates a new array initialized to the needed size
        StudentClass[] returnedArray = new StudentClass[size];
        
        // Copies the original array data to a new array for sorting
        copyArrayData(returnedArray, studentArr, 0, size - 1);
        
        // Runs the actual sort on the new array
        runMergeSortHelper(returnedArray, 0, size - 1);
        
        // Returns the sorted array
        return returnedArray;
    }
    
    /**
     * Merge sort helper, recursively breaks given array segment down to 
     * smaller segments between lowIndex and highIndex (inclusive), then 
     * sorts data using merge sort method
     * 
     * @param workingArr - String array holding unsorted values
     * @param lowIndex - lowest index of array segment to be managed; 
     * this varies as the segments are broken down recursively
     * @param highIndex - highest index of array segment to be managed; 
     * this varies as the segments are broken down recursively
     */
    private void runMergeSortHelper( StudentClass[] workingArray,
    		int lowIndex, int highIndex)
    {
        // Creates a variable to hold the middle of he array
    	int midDex;
    	
    	// Checks to be sure entered indexes are valid, stops the recursion
    	if (lowIndex < highIndex)
        {
            // Calculates the middle index using the two entered
    		midDex = (lowIndex + highIndex) / 2;
            
    	    // Recursively calls the merge helper till unable, first sub array
            runMergeSortHelper(workingArray, lowIndex, midDex);
            
            // Recursively calls the merge helper till unable, second sub array
            runMergeSortHelper(workingArray, midDex + 1, highIndex);
            
            // Sorts and merges the sub arrays
            runMerge(workingArray, lowIndex, midDex, highIndex);
        }
    }
    
    /**
     * Partitions array using the first value as the pivot; when this method
     * is complete the partition value is in the correct location in the array
     * 
     * @param workingArray - StudentClass array holding array to be managed 
     * by method
     * @param lowIndex - low index of array segment to be partitioned
     * @param highIndex - high index of array segment to be partitioned
     * @return - integer index of partition pivot
     */
    private int runPartition( StudentClass[] workingArray, int lowIndex, 
    		int highIndex )
    {
        // Creates the variables for use in this method
    	int pivDex;
    	int workingDex;
    	
    	// Creates a partition and sets it to the starting element
    	StudentClass pivot = workingArray[lowIndex];
        
        // Creates an index that sits behind the pivot
        pivDex = lowIndex;
        
        // For loop that moves through the array
        for (workingDex = lowIndex + 1; workingDex <= highIndex; workingDex++)
        {
            // Checks the current arrDex element against the pivot 
            if (workingArray[workingDex].compareTo(pivot) < 0)
            {
                // Increments the pivFollower before the swap
        	    pivDex++;
                
                // Swaps the pivFollower element and the current arrDex element
                swapValues(workingArray, pivDex, workingDex);
            }
        }
        
        // Swaps the piv follower element with the highest index in the array
        swapValues(workingArray, pivDex, lowIndex);
        
        // Returns the pivFollower index
        return pivDex;
    }
    
    /**
     * StudentClass data sorted using quick sort algorithm
     * <p>
     * Note: Calls runQuickSortHelper with lower and upper indices of 
     * array to be sorted
     * Note: Creates new StudentClass array, sorts contents of array, and 
     * returns the sorted result; does not modify (this) object student array
     * 
     * @return - StudentClass array containing sorted data
     */
    public StudentClass[] runQuickSort()
    {
        // Creates a new array initialized to the needed size
        StudentClass[] returnedArray = new StudentClass[size];
        
        // Copies the original array data to a new array for sorting
        copyArrayData(returnedArray, studentArr, 0, size - 1);
        
        // Runs the actual sort on the new array
        runQuickSortHelper(returnedArray, 0, size - 1);
        
        // Returns the sorted array
        return returnedArray;
    }
    
    /**
     * Helper method run with parameters that support recursive access
     * 
     * @param workingArray - StudentClass array holding unsorted values
     * @param lowIndex - low index of the segment of the array to be processed
     * @param highIndex - high index of the segment of the array to be 
     * processed
     */
    private void runQuickSortHelper( StudentClass[] workingArray, int lowIndex,
    		int highIndex )
    {
        // Creates variables for use in this method
        int partition;
        
        // For loop that ends the recursion process
        if (lowIndex < highIndex)
        {
            // Finds the partition for use with recursion
            partition = runPartition(workingArray, lowIndex, highIndex);
        
            // Recursively calls quickSortHelper and passes the partitioned values
            runQuickSortHelper(workingArray, lowIndex, partition - 1);
            runQuickSortHelper(workingArray, partition + 1, highIndex);
        }
    }
    
    /**
     * Swaps values within given array
     * @param stdArray - StudentClass array used for swapping
     * @param indexOne - integer index for one of the two items to be swapped
     * @param indexOther - integer index for the other of the two items to be 
     * swapped
     */
    private void swapValues( StudentClass[] stdArray, int indexOne, 
    		int indexOther )
    {
        // Creates a temp variable to store a student object
        StudentClass stdTemp = new StudentClass(stdArray[indexOne]);
        
        // Swaps the indexOne obj with the indexOther obj
        stdArray[indexOne] = stdArray[indexOther];
        
        // Swaps the indexOther obj with the obj stored in stdTemp
        stdArray[indexOther] = stdTemp;
    }
}