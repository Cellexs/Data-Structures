/**
 * 
 */
package p6_package;

/**
 * @author Chris Cisneros
 *
 */
public class StudentLinkedStackClass extends StudentLinkedListClass
{
    /**
     * Constant for displaying spaces
     */
    private static final String SPACE = " ";
    
    /**
     * Default constructor
     */
    public StudentLinkedStackClass()
    {
        // Calls the super default constructor
        super();
    }
    
    /**
     * Copy constructor
     * 
     * @param copied - StudentStackClass object to be copied
     */
    public StudentLinkedStackClass( StudentLinkedStackClass copied )
    {
        // Calls the super copy constructor
        super( copied );
    }
    
    /**
     * Clears stack using parent's operation
     */
    public void clearStack()
    {
        // Calls the super clear method
        super.clear();
    }
    
    /**
     * Recursive method displays spaces for displayStack
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
     * Displays stack from bottom to top
     */
    public void displayStack()
    {
        // Declares the variables needed for this method
        int listDex;
        int numSpaces = 2;
        
        // Adds the bottom of the stack
        System.out.println("Bottom of the Stack:");
        
        // For loop that indexes through the array
        for (listDex = 1; listDex <= super.getCurrentSize(); listDex++)
        {
            // Prints the needed spaces for the element 
            displaySpaces(numSpaces * listDex + 1);
            
            // Prints the element without brackets
            System.out.print( super.getNthStudent( listDex ) + "\n" );
        }
        
        // Adds the spaces needed for the last line
        displaySpaces ( numSpaces * listDex + 1);
        
        // Adds the top of the stack
        System.out.println( "Top of the Stack" );
    }
    
    /**
     * Reports stack empty using parent's operation
     * 
     * @Override - isEmpty in class StudentLinkedListClass
     * @return - Boolean result of empty test peekTop
     */
    public boolean isEmpty()
    {
        // Returns the result of the super method
        return super.isEmpty();
    }
    
    /**
     * Peeks at the top of the stack, no state change
     * 
     * @return - StudentClass object viewed at the top of the stack
     */
    public StudentClass peekTop()
    {
        // Declares the variables needed for this method
        int end = super.getCurrentSize();
        
        // Returns the object at the end of the list
        return super.getNthStudent( end );
    }
    
    /**
     * Pops from top of stack
     * 
     * @return - StudentClass object popped from stack
     */
    public StudentClass pop()
    {
        // Declares the variables needed for this method
        int end = super.getCurrentSize();
        
        // Removes and returns the object at the end of the list
        return super.removeNthStudent( end );
    }
     /**
      * Pushes onto top of stack
      * 
      * @param student - StudentClass object to be pushed onto stack
      */
    public void push( StudentClass student )
    {
        // Adds the student to the end of the list
        super.appendDataAtEnd( student );
    }
}
