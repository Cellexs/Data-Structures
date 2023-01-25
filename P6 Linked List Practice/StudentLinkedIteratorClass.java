/**
 * 
 */
package p6_package;

/**
 * @author Chris Cisneros
 *
 */
public class StudentLinkedIteratorClass 
{
    /**
     * Current index of iterator
     */
    private int currentIndex;
    
    /**
     * StudentClassList data used by this class
     */
    private StudentLinkedListClass iteratorList;
    
    /**
     * Constant character for display
     */
    private char LEFT_BRACKET = 91;
    
    /**
     * Constant character for display
     */
    private char RIGHT_BRACKET = 93;
    
    /**
     * Constant character for display
     */
    private char SPACE = 32;
    
    /**
     * Default constructor for StudentIteratorClass
     */
    public StudentLinkedIteratorClass()
    {
        // Sets the current index to 0 indicating an empty list
        currentIndex = 0;
        
        // Creates a new StudentLinkedListClass object
        iteratorList = new StudentLinkedListClass();
    }
    
    /**
     * Copy constructor for StudentIteratorClass
     * 
     * @param copied - StudentIteratorClass object to be copied
     */
    public StudentLinkedIteratorClass( StudentLinkedIteratorClass copied )
    {
        // Copies over the current index
        currentIndex = copied.currentIndex;
        
        /*
         *  Copies over the iterator list using the StudentLinkedListClass 
         *  copy constructor
         */
        iteratorList = new StudentLinkedListClass( copied.iteratorList );
    }
    
    /**
     * Clears array
     */
    public void clear()
    {
        // Sets the index to 0
        currentIndex = 0;
        
        // Clears the list using the clear method
        iteratorList.clear();
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
     * Inserts new value after value at iterator cursor
     * <p>
     * Note: Current value must remain the same after data set, unless the 
     * list was empty prior to data set
     * 
     * @param newValue - StudentClass object to be inserted in list
     * @return - Boolean result of action; true if successful, false otherwise
     */
    public boolean insertAfterCurrent( StudentClass newValue )
    {
        // If the list is empty
        if( currentIndex == 0 )
        {
            // Sets the current index to 1, still the first element
            currentIndex = 1;
            
            // Calls the insert at Nth with the original index
            return iteratorList.insertDataAtNthPosition( currentIndex, newValue );
        }
        
        // Uses insert at NthPosition to add the data where needed, right after the current
        return iteratorList.insertDataAtNthPosition( currentIndex + 1, newValue );
    }
    
    /**
     * Inserts new before value at iterator cursor
     * <p>
     * Note: Current value must remain the same after data set, unless the 
     * list was empty prior to data set
     * 
     * @param newValue - StudentClass object to be inserted in list
     * @return - Boolean result of action; true if successful, false otherwise
     */
    public boolean insertBeforeCurrent( StudentClass newValue )
    {    	
    	// If the list is empty
        if( currentIndex <= 0 )
        {
        	// Sets the current index to 1, still the first element
            currentIndex = 1;
            
            // Calls the insert at Nth with the original index
            return iteratorList.insertDataAtNthPosition( currentIndex, newValue );
        }
        
        // Increments the index 
        currentIndex++;
        
        // Uses insert at NthPosition to add the data where needed
        return iteratorList.insertDataAtNthPosition
        		( currentIndex - 2, newValue );
    }
    
    /**
     * Reports if iterator cursor is at beginning
     * If list is empty, cursor is not at beginning
     * 
     * @return - Boolean result of action; true if at beginning, false otherwise
     */
    public boolean isAtBeginning()
    {
        // Compares the current index to the first element in the list
        return currentIndex == 1;
    }
    
    /**
     * Reports if iterator cursor is at end
     * If list is empty, cursor is not at end
     * 
     * @return - Boolean result of action; true if at end, false otherwise
     */
    public boolean isAtEnd()
    {
        // Declares the variables needed for this method
        int end = iteratorList.getCurrentSize();
        
        // Compares the current index to the last element in the list
        return currentIndex == end;
    }
    
    /**
     * Reports if list is empty
     * 
     * @return - Boolean result of action; true if empty, false otherwise
     */
    public boolean isEmpty()
    {
        // Returns the result of the isEmpty method
        return iteratorList.isEmpty();
    }
    
    /**
     * If possible, moves iterator cursor one position to the right, or next
     * 
     * @return - Boolean result of action; true if successful, false otherwise
     */
    public boolean moveNext()
    {
        // Checks if the current index has a next node
        if( iteratorList.getNthStudent( currentIndex ).nextRef != null )
        {
            // Iterate the index
            currentIndex++;
            
            // Returns true
            return true;
        }
        
        // Returns false since there is no next node
        return false;
    }
    
    /**
     * If possible, moves iterator cursor one position to the left, or previous
     * 
     * @return - Boolean result of action; true if successful, false otherwise
     */
    public boolean movePrev()
    {
        // Checks if the currentIndex can move backwards
        if(currentIndex - 1 >= 1)
        {
            // Decrement the index
            currentIndex--;
            
            // Returns true
            return true;
        }
        
        // Returns false since it can't go lower
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
        // Checks if there is only one element in the array
        if ( iteratorList.getCurrentSize() == 1 )
        {
            clear();
        }
        
        // Decrements the currentIndex
        currentIndex--;
        
        // Returns the result of the remove Nth method
        return iteratorList.removeNthStudent( currentIndex );
    }
    
    /**
     * Replaces value at iterator cursor with new value
     * 
     * @param newValue - StudentClass object to be inserted in list
     * @return - Boolean result of action; true if successful, false otherwise
     */
    public boolean replaceAtCurrent( StudentClass newValue )
    {
        // Returns the result of the replace Nth method
        return iteratorList.replaceDataAtNthPosition( currentIndex, newValue );
    }
    
    /**
     * Shows space-delimited list with cursor location indicated
     */
    public void runDiagnosticDisplay()
    {
        // Declares the variables needed for this method
        int listDex;
        int numSpaces = 2;
        
        // Adds the left end of iterator
        System.out.println("Left End of Iterator:");
        
        // For loop that indexes through the list
        for (listDex = 1; listDex <= iteratorList.getCurrentSize(); listDex++)
        {
            // Checks for the current index node
            if ( iteratorList.getNthStudent( listDex ) == 
            		iteratorList.getNthStudent( currentIndex ) )
            {
                // Prints the needed spaces for the node
                displaySpaces( numSpaces * listDex + 1 );
                
                // Prints the node with brackets
                System.out.print( LEFT_BRACKET + iteratorList.getNthStudent
                		( listDex ).toString() + RIGHT_BRACKET + "\n" );
            }
            else
            {
                // Prints the needed spaces for the node 
                displaySpaces( numSpaces * listDex + 1 );
                
                // Prints the node without brackets
                System.out.print( iteratorList.getNthStudent
                		( listDex ) + "\n" );
            }
        }
        
        // Adds the spaces needed for the last line
        displaySpaces ( numSpaces * listDex + 1);
        
        // Adds the right end of iterator
        System.out.println( "Right End of Iterator" );
        
        System.out.println();
    }
    
    /**
     * Sets iterator cursor to beginning of list
     * 
     * @return - Boolean result of action; true if successful, false otherwise
     */
    public boolean setToBeginning()
    {
        // Checks for an empty list
        if ( isEmpty() )
        {
            // There is no beginning so return false
            return false;
        }
        
        // Sets the index to 1, the beginning
        currentIndex = 1;
        
        // Returns true
        return true;
    }
    
    /**
     * Sets iterator cursor to the end of the list
     * 
     * @return - Boolean result of action; true if successful, false otherwise
     */
    public boolean setToEnd()
    {
        // Declares the variables needed for this method
        int size = iteratorList.getCurrentSize();
        
        // Checks for an empty list
        if( isEmpty() )
        {
            // There is no end so return false
            return false;
        }
        
        // Sets the index to the current size, the end
        currentIndex = size;
        
        // Returns true
        return true;
    }
}
