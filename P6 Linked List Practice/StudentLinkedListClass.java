/**
 * 
 */
package p6_package;

/**
 * @author Chris Cisneros
 *
 */
public class StudentLinkedListClass 
{
    /**
     * For N not found in search
     */
    public static final int NOT_FOUND = -1;
    
    /**
     * Member data
     */
    private StudentClass headRef;
    
    /**
     * Default constructor, initializes linked list
     */
    public StudentLinkedListClass()
    {
        // Sets the headRef to null to indicate an empty list
        headRef = null;
    }
    
    /**
     * Copy constructor, creates new nodes to eliminate aliasing
     * 
     * @param copied - StudentListClass object to be copied
     */
    public StudentLinkedListClass( StudentLinkedListClass copied )
    {
        // Declares the variables needed for this method
        StudentClass copyCurrent = copied.headRef;
        StudentClass listCurrent = new StudentClass( copyCurrent );
        
        // Checks if the copied list has a next node
        while( copyCurrent.nextRef != null )
        {
            // Makes a copy of the next node and adds it to the new list
            listCurrent.nextRef = new StudentClass( copyCurrent.nextRef );
            
            // Moves the list indexes forward
            copyCurrent = copyCurrent.nextRef;
            listCurrent = listCurrent.nextRef;
        }
    }
    
    /**
     * Sets data at end of list
     * <p>
     * Note: Uses appendDataAtEnd_Helper
     * 
     * @param student - StudentClass object to be appended to list
     */
    public void appendDataAtEnd( StudentClass student )
    {
        // Calls the helper method with the headRef to start
        headRef = appendDataAtEnd_Helper( headRef, student );
    }
    
    /**
     * Recursive helper method that appends data to end of list
     * 
     * @param wkgRef - StudentClass reference for linking nodes
     * @param student - StudentClass data to be added to the list
     * @return - StudentClass link to calling method
     */
    private StudentClass appendDataAtEnd_Helper( StudentClass wkgRef, 
    		StudentClass student )
    {
        // Checks for if the current node is null
        if( wkgRef == null)
        {
            // Sets the wkgRef to the student item
            wkgRef = student;
            
            // Returns the wkgRef where the item was set
            return wkgRef;
        }
        else
        {
            // Recursively moves till the end of the list
            wkgRef.nextRef = appendDataAtEnd_Helper( wkgRef.nextRef, student );
        }
        
        // Returns the workingRef
        return wkgRef;
    }
    
    /**
     * Clears linked list of all valid values by setting linked list 
     * head to null
     */
    public void clear()
    {
        // Sets the head of the linked list to null
        headRef = null;
    }
    
    /**
     * Displays student list
     */
    public void displayList()
    {
        // Declares the variables needed for this method
        int listDex;
        int listSize = getCurrentSize();
        StudentClass listCurrent = headRef;
        
        System.out.println("Student Class List:");
        
        // For loop that indexes through the rest of the list
        for (listDex = 0; listDex < listSize; listDex++)
        {
            // Converts the object to a string and then prints on its own line
            System.out.println( listCurrent.toString() );
            
            // Moves the current node
            listCurrent = listCurrent.nextRef;
        }
        
        System.out.println();
    }
    
    /**
     * Gets number of student if found in list
     * 
     * @param student - StudentClass object for finding N
     * @return - integer N of the StudentClass object, or NOT_FOUND if 
     * not in list
     */
    public int findStudentNumber( StudentClass student )
    {
        // Declares the variables needed for this method
        int rtrDex = 1;
        StudentClass listCurrent = headRef;
        
        // Loop that runs until it hits the end of the list
        while( listCurrent != null )
        {
            // If statement that looks for a matching object
            if( student.compareTo( listCurrent ) == 0 )
            {
                // Returns the index of the found item
                return rtrDex;
            }
            
            // Otherwise it increments the object and index
            listCurrent = listCurrent.nextRef;
            rtrDex++;
        }
        
        // Returns not found if it never gets to the end of the list
        return NOT_FOUND;
    }
    
    /**
     * Gets current size of linked list
     * <p>
     * Note: Uses getCurrentSize_Helper
     * 
     * @return - integer size of linked list
     */
    public int getCurrentSize()
    {
        // Declares the variables needed for this method
        int size;
        
        // Calls the helper with the head of the linked list to start
        size = getCurrentSize_Helper( headRef );
        
        // Returns the recursively found size
        return size;
    }
    
    /**
     * Recursive helper method for finding list size
     * 
     * @param wkgRef - StudentClass reference for recursion management
     * @return - integer size at each recursive level
     */
    private int getCurrentSize_Helper( StudentClass wkgRef )
    {
        // Checks if the current wkgRef is null
        if( wkgRef != null )
        {
            // Recursively calls the method again with the nextRef and adds 1
            return getCurrentSize_Helper( wkgRef.nextRef ) + 1;
        }
        
        // If it is null then you hit then end of the list and you return 0
        return 0;
    }
    
    /**
     * Acquires the Nth item in the list, starting with N = 1
     * 
     * @param N_value - integer N value for accessing student
     * @return - StudentClass value at element or null if attempt to acquire 
     * data out of bounds
     */
    public StudentClass getNthStudent( int N_value )
    {
        // Declares the variables needed for this method
        int listDex;
        StudentClass listCurrent = headRef;
        
        // If the N-value is less then zero
        if( N_value <= 0)
        {
            // Then return null
            return null;
        }
        
        // For loop that runs N_value times
        for(listDex = 0; listDex < N_value - 1; listDex++)
        {
            // If the listCurrent ever becomes null
            if( listCurrent == null )
            {
                // Then return null
                return null;
            }
            else
            {
                // Else make listCurrent its nextRef
                listCurrent = listCurrent.nextRef;
            }
        }
        
        // It got to the element so return it
        return listCurrent;
    }
    
    /**
     * Sets data at beginning of list; moves all subsequent data up by 
     * one element
     * <p>
     * Note: No failure mode; data will be inserted at beginning no matter 
     * what the size of the linked list is
     * 
     * @param student - StudentClass object to set at beginning
     */
    public void insertDataAtBeginning( StudentClass student )
    {
        // Sets the new node's nextRef to the head
        student.nextRef = headRef;
        
        // Then set the headRef to the new node
        headRef = student;
    }
    
    /**
     * Links data into list at Nth position, where N starts at 1
     * <p>
     * Note: Allows item to be appended to end of list
     * 
     * @param NthPos - integer value to indicate insertion location
     * @param student - StudentClass object to be inserted at Nth position in list
     * @return - Boolean indication of successful operation
     */
    public boolean insertDataAtNthPosition( int NthPos, StudentClass student )
    {
        // Creates objects to hold the data before and after the node
        StudentClass beforeNode = getNthStudent( NthPos - 1);
        StudentClass NthNode = getNthStudent( NthPos );
        
        // Series of checks to deal with the possible situations
        if( beforeNode == null )
        {
            // Adds the data to the beginning
            insertDataAtBeginning(student);
            
            // Returns true
            return true;
        }
        else if ( NthNode == null && beforeNode != null )
        {
            // Adds the data to the end
            appendDataAtEnd( student );
            
            // Returns true
            return true;
        }
        else if ( NthPos >= 1 && NthPos <= getCurrentSize() )
        {
            // Sets the references as needed
            beforeNode.nextRef = student;
            student.nextRef = NthNode;
            
            // Returns true
            return true;
        }
        // Returns that it failed
        return false;
    }
    
    /**
     * Checks for empty list
     * 
     * @return - Boolean result of test for empty
     */
    public boolean isEmpty()
    {
        // Checks of the headRef is null and returns it
        return headRef == null;
    }
    
    /**
     * Removes Nth item from linked list
     * 
     * @param numberN - integer number of element value to be removed, starts at N = 1
     * @return - removed StudentClass value if successful, null if not
     */
    public StudentClass removeNthStudent( int numberN )
    {
        // Creates objects to hold the data before and after the insertion
        StudentClass beforeNode = getNthStudent( numberN - 1);
        StudentClass afterNode = getNthStudent( numberN + 1);
        StudentClass removed = getNthStudent( numberN );
        
        // Checks if beforeNode is null
        if( beforeNode == null )
        {
            // It was called on 1 so make the afterNode the head
            headRef = afterNode;
        }
        else
        {
            // Sets the references as needed
            beforeNode.nextRef = afterNode;
        }
        
        // Returns that it was successful
        return removed;
    }
    
    /**
     * Replaces item in linked list at specified Nth position, where N starts at 1
     * 
     * @param NthPos - integer value to indicate position of value to be replaced
     * @param student - StudentClass object to be inserted at Nth position
     * @return - Boolean success if replaced, or failure if incorrect N was used
     */
    public boolean replaceDataAtNthPosition( int NthPos, StudentClass student )
    {
        // Creates objects to hold the data before and after the insertion
        StudentClass beforeNode = getNthStudent( NthPos - 1);
        StudentClass afterNode = getNthStudent( NthPos + 1);
        
        // Checks if beforeNode is null
        if( beforeNode == null )
        {
            // It was called on 1 so make the student the head and update nextRef
            student.nextRef = afterNode;
            headRef = student;
        }
        else
        {
            // Sets the references as needed
            beforeNode.nextRef = student;
            student.nextRef = afterNode;
        }
        
        // Returns if it was succesful
        return getNthStudent( NthPos ).compareTo( student ) == 0;
    }
}
