/**
 * 
 */
package p5_package;

/**
 * @author Chris Cisneros
 *
 */
public class StudentQueueClass extends StudentListClass
{
    
    /**
     * Constant for displaying spaces
     */
    private static String SPACE = " ";
    
    /**
     * Default constructor
     */
    public StudentQueueClass()
    {
        // Calls the StudentListClass constructor
        super();
    }
    
    /**
     * Initialization constructor
     * 
     * @param capacity - integer value to specify initial capacity
     */
    public StudentQueueClass( int capacity )
    {
        // Calls the StudentListClass constructor
        super( capacity );
    }
    
    /**
     * Copy constructor
     * 
     * @param copied - StudentQueueClass object to be copied
     */
    public StudentQueueClass( StudentQueueClass copied )
    {
        // Calls the StudentListClass copy constructor
        super( copied );
    }
    
    /**
     * Clears queue using parent's operation
     */
    public void clearQueue()
    {
        // Calls the parent clear method
        super.clear();
    }
    
    /**
     * Dequeues from end of list, front of queue
     * 
     * @return - StudentClass object if available; null otherwise
     */
    public StudentClass dequeue()
    {
        // Declares variables for use in this method
        int lastStd;
        
        // Initializes the last student variable to the current size
        lastStd = super.getCurrentSize();
        
        // Calls remove Nth student with lastStd variable
        return super.removeNthStudent( lastStd );
    }
    
    /**
     * Displays queue from tail to head
     */
    public void displayQueue()
    {
        // Declares the variables needed for this method
        int arrDex;
        int numSpaces = 2;
        
        // Adds the tail of queue
        System.out.println("Tail of Queue:");
        
        // For loop that indexes through the array
        for (arrDex = 1; arrDex <= super.getCurrentSize(); arrDex++)
        {
            // Prints the needed spaces for the element 
            displaySpaces(numSpaces * arrDex + 1);
            
            // Prints the element without brackets
            System.out.print( super.getNthStudent( arrDex ) + "\n" );
        }
        
        // Adds the spaces needed for the last line
        displaySpaces ( numSpaces * arrDex + 1);
        
        // Adds the head of queue
        System.out.println( "Head of Queue" );
    }
    
    /**
     * Recursive method displays spaces for displayQueue
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
     * Enqueues to beginning of list, tail of queue
     * 
     * @param student - StudentClass object to be enqueued
     */
    public void enqueue( StudentClass student )
    {
        // Inserts the student at the tail of the queue
        super.insertDataAtBeginning( student );
    }
    
    /**
     * Reports queue empty using parent's operation
     * 
     * @Override - isEmpty in class StudentListClass
     * @return - Boolean result of empty test
     */
    public boolean isEmpty()
    {
        // Calls the parent method of isEmpty
        return super.isEmpty();
    }
    
    /**
     * Peek at front of queue, no state change
     * 
     * @return - StudentClass object if available; null otherwise
     */
    public StudentClass peekFront()
    {
        // Declares variables for use in this method
        int lastStd;
        
        // Initializes the last student variable to the current size
        lastStd = super.getCurrentSize();
        
        // Calls remove Nth student with lastStd variable
        return super.getNthStudent( lastStd );
    }
}