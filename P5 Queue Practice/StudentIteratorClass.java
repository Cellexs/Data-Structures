/**
 * 
 */
package p5_package;

/**
 * @author Chris Cisneros
 *
 */
public class StudentIteratorClass 
{
    
    /**
     * Current index of iterator
     */
    private int currentIndex;
    
    /**
     * StudentClassList data used by this class
     */
    private StudentListClass iteratorList;
    
    /**
     * Constant character for display
     */
    private final char LEFT_BRACKET = 91;
    
    /**
     * Constant character for display
     */
    private final char RIGHT_BRACKET = 93;
    
    /**
     * Constant character for display
     */
    private final char SPACE = 32;
    
    /**
     * Default constructor for StudentIteratorClass
     */
    public StudentIteratorClass()
    {
        // Sets the index to 0 indicating an empty list
        currentIndex = 0;
        
        // Creates a StudentClassList using its constructor
        iteratorList = new StudentListClass();
    }
    
    /**
     * Initialization constructor for StudentIteratorClass
     * 
     * @param initCapacity - integer value at which to set initial 
     * array capacity
     */
    public StudentIteratorClass( int initCapacity )
    {
        // Sets the index to 0 indicating an empty list
        currentIndex = 0;
        
        // Creates a StudentClassList using its constructor
        iteratorList = new StudentListClass( initCapacity );
    }
    
    /**
     * Copy constructor for StudentIteratorClass
     * 
     * @param copied - StudentIteratorClass object to be copied
     */
    public StudentIteratorClass( StudentIteratorClass copied )
    {
        // Copies the currentIndex from the object
        currentIndex = copied.currentIndex;
        
        // Copies the StudentListClass object using its copy constructor
        iteratorList = new StudentListClass(copied.iteratorList);
    }
    
    /**
     * Clears array
     */
    public void clear()
    {
        // Clears the array using the clear method in StudentListClass
        iteratorList.clear();
        
        // Sets the current index location back to 0
        currentIndex = 0;
    }
    
    /**
     * Recursive method displays spaces for displayDiagnosticDisplay
     * 
     * @param numSpaces - integer value specifying number of spaces to display
     */
    private void displaySpaces( int numSpaces )
    {
        if ( numSpaces > 0 )
        {
            // Prints out a single space
            System.out.print( SPACE );
            
            // Decrements the numspaces by 1 and recursively calls
            displaySpaces( numSpaces - 1 );
        }
    }
    
    /**
     * Gets value at iterator cursor location
     * 
     * @return - integer value returned; FAILED_ACCESS if not found
     */
    public StudentClass getAtCurrent()
    {
        // Returns the student at the current index using get Nth student
        return iteratorList.getNthStudent( currentIndex );
    }
    
    /**
     * Inserts new value after value at iterator cursor
     * <p>
     * Note: Current value must remain the same after data set, unless there 
     * is only one item in the set
     * 
     * @param newValue - integer value to be inserted in list
     * @return - boolean result of action; true if successful, false otherwise
     */
    public boolean insertAfterCurrent( StudentClass newValue )
    {
        // Checks if the list is empty
        if ( iteratorList.isEmpty() )
        {
            // Calls insert at beginning specifically
            iteratorList.insertDataAtBeginning( newValue );
            
            // Sets the current index to 1
            currentIndex = 1;
            
            // Returns true
            return true;
        }
        
        // Returns the result of adding the element
        return iteratorList.insertDataAtNthPosition( currentIndex + 1, newValue );
    }
    
    /**
     * Inserts new before value at iterator cursor 
     * <p>
     * Note: Current value must remain the same after data set, unless there 
     * is only one item in the set
     * 
     * @param newValue - integer value to be inserted in list
     * @return - boolean result of action; true if successful, false otherwise
     */
    public boolean insertBeforeCurrent( StudentClass newValue )
    {
        // Checks if the list is empty
        if ( iteratorList.isEmpty() )
        {
            // Calls insert at beginning specifically
            iteratorList.insertDataAtBeginning( newValue );
            
            // Sets the current index to 1
            currentIndex = 1;
            
            // Returns true
            return true;
        }
        
        currentIndex++;
        
        // Returns the result of adding the element
        return iteratorList.insertDataAtNthPosition( currentIndex - 1, newValue );
    }
    
    /**
     * Reports if iterator cursor is at beginning
     * If list is empty, cursor is not at beginning
     * 
     * @return - boolean result of action; true if at beginning, false otherwise
     */
    public boolean isAtBeginning()
    {
        // Checks for an empty list
        if( iteratorList.isEmpty() )
        {
            // Returns false if its empty
            return false;
        }
        
        // Checks the current index is equal to the index of the first element
        return currentIndex ==  1;
    }
    
    /**
     * Reports if iterator cursor is at end
     * If list is empty, cursor is not at end
     * 
     * @return - boolean result of action; true if at end, false otherwise
     */
    public boolean isAtEnd()
    {
        // Checks for an empty list
        if( iteratorList.isEmpty() )
        {
            // Returns false if its empty
            return false;
        }
        
        // Checks the current index is equal to the index of the last element
        return currentIndex ==  iteratorList.getCurrentSize();
    }
    
    /**
     * Reports if list is empty
     * 
     * @return - boolean result of action; true if empty, false otherwise
     */
    public boolean isEmpty()
    {
        // Returns the boolean result of the isEmpty method is StudentListClass
        return iteratorList.isEmpty();
    }
    
    /**
     * If possible, moves iterator cursor one position to the right, or next
     * 
     * @return - boolean result of action; true if successful, false otherwise
     */
    public boolean moveNext()
    {
        // Checks if list has a next to move to
        if ( currentIndex + 1 < iteratorList.getCurrentSize() )
        {
            // Moves the current index to the next index
            currentIndex = currentIndex + 1;
            
            // Then returns true
            return true;
        }
        
        // Otherwise returns false
         return false;
    }
    
    /**
     * If possible, moves iterator cursor one position to the left, or previous
     * 
     * @return - boolean result of action; true if successful, false otherwise
     */
    public boolean movePrev()
    {
        // Checks if list has a next to move to
        if ( currentIndex - 1 > 0 )
        {
            // Moves the current index to the next index
            currentIndex = currentIndex - 1;
            
            // Then returns true
            return true;
        }
        
        // Otherwise returns false
        return false;
    }
    
    /**
     * Removes and returns a data value from the iterator cursor position
     * <p>
     * Note: cursor must be located at succeeding element unless last item 
     * removed
     * 
     * @return - StudentClass object removed from list, or null if not found
     */
    public StudentClass removeAtCurrent()
    {
        // Returns the result of the remove student method
        return iteratorList.removeNthStudent( currentIndex );
    }
    
    /**
     * Replaces value at iterator cursor with new value
     * 
     * @param newValue - integer value to be inserted in list
     * @return - boolean result of action; true if successful, false otherwise
     */
    public boolean replaceAtCurrent( StudentClass newValue )
    {
        // Calls replace data method from StudenListClass using current index
        return iteratorList.replaceDataAtNthPosition( currentIndex, newValue);
    }
    
    /**
     * Shows space-delimited list with cursor location indicated
     */
    public void runDiagnosticDisplay()
    {
        // Declares the variables needed for this method
        int arrDex;
        int numSpaces = 2;
        
        // Adds the left end of iterator
        System.out.println("Left End of Iterator:");
        
        // For loop that indexes through the array
        for (arrDex = 1; arrDex <= iteratorList.getCurrentSize(); arrDex++)
        {
            // Checks for the current index element
            if ( iteratorList.getNthStudent( arrDex ) == 
            		iteratorList.getNthStudent( currentIndex ) )
            {
                // Prints the needed spaces for the element
                displaySpaces( numSpaces * arrDex + 1 );
                
                // Prints the element with brackets
                System.out.print( LEFT_BRACKET + iteratorList.getNthStudent
                		( arrDex ).toString() + RIGHT_BRACKET + "\n" );
            }
            else
            {
                // Prints the needed spaces for the element 
                displaySpaces( numSpaces * arrDex + 1 );
                
                // Prints the element without brackets
                System.out.print( iteratorList.getNthStudent
                		( arrDex ) + "\n" );
            }
        }
        
        // Adds the spaces needed for the last line
        displaySpaces ( numSpaces * arrDex + 1);
        
        // Adds the right end of iterator
        System.out.println( "Right End of Iterator" );
    }
    
    /**
     * Sets iterator cursor to beginning of list
     * 
     * @return - boolean result of action; true if successful, false otherwise
     */
    public boolean setToBeginning()
    {
        // Checks for an empty list
        if ( iteratorList.isEmpty() )
        {
            // If its empty then there is no beginning, so return false
            return false;
        }
        
        // Sets the current index to the first element
        currentIndex = 1;
        
        // Returns true
        return true;
    }
    
    /**
     * Sets iterator cursor to the end of the list
     * 
     * @return - boolean result of action; true if successful, false otherwise
     */
    public boolean setToEnd()
    {
        // Checks for an empty list
        if ( iteratorList.isEmpty() )
        {
            // If its empty then there is no end, so return false
            return false;
        }
        
        // Sets the index to the last element in the list
        currentIndex = iteratorList.getCurrentSize();
        
        // Returns true
        return true;
    }
}
