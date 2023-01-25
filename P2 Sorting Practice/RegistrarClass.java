/**
 * 
 */
package p2_package;

/**
 * @author Chris Cisneros
 */
public class RegistrarClass 
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
	public RegistrarClass()
	{
	    capacity = DEFAULT_CAPACITY;
		studentArr = new StudentClass[capacity];
	    size = 0;
	}
	
	/**
	 * Initialization Constructor
	 * @param initialCapacity - integer value to set class initial capacity
	 */
	public RegistrarClass( int initialCapacity )
	{
	    capacity = initialCapacity;
	    studentArr = new StudentClass[capacity];
	    size = 0;
	}
	
	/**
	 * Copy Constructor
	 * @param copied - RegistrarClass object to be copied
	 */
	public RegistrarClass( RegistrarClass copied )
	{
		capacity = copied.capacity;
		size = copied.size;
		studentArr = new StudentClass[copied.capacity];
	    copyArrayData(studentArr, copied.studentArr);
	}
	
	/**
	 * Adds a StudentClass item to list
	 * <p>
	 * Note: Overloaded method
	 * 
	 * @param newStudent - StudentClass object to be added to array
	 * @return Boolean result of item addition to array
	 */
	public boolean addStudent( StudentClass newStudent )
	{
	    // Checks to see if the array has room
	    if (capacity <= size)
	    {
		    // If it doesn't returns false immediately
	    	return false;
	    }
	   
	    // Assigns the student to the current size index of the array
	    studentArr[size] = newStudent;
	    // Increments the size variable
	    size++;
	    // Returns the success of adding it to the array
	    return true;
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
	 * @return - Boolean result of adding student
	 */
	public boolean addStudent( String stdName, int stdId, 
			char stdGender, double stdGPA )
	{   
	    // Adds the student to the array with the addStudent and returns result
	    return addStudent(new StudentClass(stdName, stdId, 
	    		stdGender, stdGPA));
	}
	
	/**
	 * Copies student list from one array to other
	 * @param dest - StudentClass array to which data is copied
	 * @param source - StudentClass array from which data is copied
	 */
	private void copyArrayData( StudentClass[] dest,
            StudentClass[] source )
	{
	    // Creates a index variable for use within the for loop
	    int arrDex;
	   
	    /*
	     *  Creates a for loop to create a non-aliased copy of the data 
	     *  from source to dest
	     */
	    for(arrDex = 0; arrDex < size; arrDex++)
	    {
		    // Creates a new object with the same data as the original
		    dest[arrDex] = new StudentClass(source[arrDex]);
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
	 * <p>
	 * Note: Uses findStudentIndex
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
	 * <p>
	 * Note: Must use appropriate comparison method for class
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
	 * Removes student from array, shifts elements down to keep array contiguous
	 * <p>
	 * Note: Uses findStudentIndex
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
	 * Sorts elements using the bubble sort algorithm
	 * <p>
	 * Note: Creates new StudentClass array, sorts contents of array, and 
	 * returns the sorted result; does not modify (this) object student array
	 * 
	 * @return - new StudentClass array with sorted items
	 */
	public StudentClass[] runBubbleSort()
    {
	    // Declares the variables needed for this method
	    int arrDex;
	    boolean swapFlag = true;
	    
	    // Creates a new array to transfer the data to
	    StudentClass[] copiedArray = new StudentClass[capacity];
	    
	    // Copies the data to the new array
	    copyArrayData(copiedArray, this.studentArr);
		
	    // Checks to see if the flag was triggered
	    while (swapFlag)
		{
		    // Changes the swap tracker to false
		    swapFlag = false;
		    // Iterates through the array
			for(arrDex = 0; arrDex < size - 1; arrDex++)
			{
			    // Checks to see if the first value is in the correct spot
				if (copiedArray[arrDex].compareTo(copiedArray[arrDex + 1]) > 0)
			    {
			        // Swaps the values if needed
				    swapValues(copiedArray, arrDex, arrDex + 1);
				    // Sets the swap tracker to true
			        swapFlag = true;
			    }
		    }
	    }
	    // Returns the bubble sorted array
	    return copiedArray;
    }
	
	/**
	 * Sorts character elements using the insertion sort algorithm
	 * <p>
	 * Note: Creates new StudentClass array, sorts contents of array, and 
	 * returns the sorted result; does not modify (this) object student array
	 * 
	 * @return - new StudentClass array with sorted items
	 */
	public StudentClass[] runInsertionSort()
    {
	    // Creates the index variables for the the nested loops
		int searchDex, listDex;
	    StudentClass tempVal;
		
	    // Creates a new array to transfer the data to
	    StudentClass[] copiedArray = new StudentClass[capacity];
	    
	    // Copies the data to the new array
	    copyArrayData(copiedArray, studentArr);
	    
	    // Starts the outer loop at index[1]
	    for (listDex = 1; listDex < size; listDex++)
	    {
	        // Saves the element that will be sorted
	    	tempVal = copiedArray[listDex];
	    	
	    	// Keeps track of where the element goes
	        searchDex = listDex;
	        
	        // 
	        while ( searchDex > 0 && copiedArray[searchDex - 1].compareTo(tempVal) > 0)
	        {
	        	copiedArray[searchDex] = copiedArray[searchDex - 1];
	        	
	        	searchDex--;
	        }
	        
	        copiedArray[searchDex] = tempVal;
	    }
	    // Returns the sorted array copy
	    return copiedArray;
    }
	
	/**
	 * Sorts character elements using the selection sort algorithm
	 * <p>
	 * Note: Creates new StudentClass array, sorts contents of array, and 
	 * returns the sorted result; does not modify (this) object student array
	 * 
	 * @return - new StudentClass array with sorted items
	 */
	public StudentClass[] runSelectionSort()
	{
	    // Creates the index variables for the the nested loops
	    int innerDex, outerDex, smallDex;
			    
	    // Creates a new array to transfer the data to
	    StudentClass[] copiedArray = new StudentClass[capacity];
			    
	    // Copies the data to the new array
	    copyArrayData(copiedArray, studentArr);
			    
	    // Starts the outer loop at the start of the array
	    for (outerDex = 0; outerDex < size - 1; outerDex++)
	    {
	        // Sets the smallest known value as the beginning of the inner loop
	    	smallDex = outerDex;
	    	
	    	// Starts the inner loop at the beginning and runs till the end
	        for(innerDex = outerDex; innerDex < size; innerDex++)
	        {
	        	/*
	             *  Compares the starting index to the rest of the array and 
	             *  updates the smallest index if a smaller element is found
		         */
	        	if(copiedArray[smallDex].compareTo(copiedArray[innerDex]) > 0)
		        {
		            // Sets the smallest known index as the compared to index
	        	    smallDex = innerDex;
		        }
	        }
	        
	        // Swap them
	        swapValues(copiedArray, smallDex, outerDex);
	    }
        // Returns the sorted array copy
	    return copiedArray;
    }
	
	/**
	 * Uses Shell's sorting algorithm to sort an array of integers
	 * Shell's sorting algorithm is an optimized insertion algorithm
	 * <p>
	 * Note: Creates new StudentClass array, sorts contents of array, and 
	 * returns the sorted result; does not modify (this) object student array
	 * 
	 * @return - new StudentClass array with sorted items
	 */
	public StudentClass[] runShellSort()
	{
	    // Creates the index variables for the the nested loops
	    int outerDex;
	    int tempDex;
	    int gap;
		StudentClass stdTemp;
	    
	    // Creates a new array to transfer the data to
	    StudentClass[] copiedArray = new StudentClass[capacity];
			    
	    // Copies the data to the new array
	    copyArrayData(copiedArray, studentArr);
	    
	    /*
	     * Divides the original size by 2 to get gap and then continues to
	     * divide the gap by 2 every iteration until gap is less than 1
	     */
	    for (gap = size / 2; gap > 0; gap /= 2)
	    {
	    	// Loop that walks through the array starting at the gap
	        for(outerDex = gap; outerDex < size; outerDex++)
	        {
	            // Creates a temporary index that can change
	        	tempDex = outerDex;
	            
	        	// Creates a temporary student class object to hold the element
	            stdTemp = copiedArray[outerDex];
	            
	            /*
	             * While loop that continuously runs till it can't jump a gap
	             * or the jumped to item doesn't need to be moved
	             */
	            while(tempDex - gap >= 0 && stdTemp.compareTo
                		(copiedArray[tempDex - gap]) < 0)
	            {
	                // Sets the original element to the jumped to element 
	            	copiedArray[tempDex] = copiedArray[tempDex - gap];
	                
	            	// Increments the gap
	                tempDex -= gap;
	            }
	            // Sets the last jumped to element as the original element
	            copiedArray[tempDex] = stdTemp;
	        }
	    }
        // Returns the sorted array copy
	    return copiedArray;
    }
	
	/**
	 * Swaps values within given array
	 * @param stdArray - StudentClass array used for swapping
	 * @param indexOne - integer index for one of the two items to be swapped
	 * @param indexOther - integer index for the other of the two items to be swapped
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