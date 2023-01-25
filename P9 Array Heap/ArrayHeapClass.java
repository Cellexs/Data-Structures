/**
 * 
 */
package p9_package;

/**
 * @author Chris Cisneros
 */
public class ArrayHeapClass 
{
    /**
     * Initial array capacity
     */
    public final int DEFAULT_ARRAY_CAPACITY = 10;
    
    /**
     * Constant for not found value
     */
    public final int NOT_FOUND = 99999;
    
    /**
     * Array for heap
     */
    private int[] heapArray;
    
    /**
     * Management data for array
     */
    private int arraySize;
    
    /**
     * Management data for array
     */
    private int arrayCapacity;
    
    /**
     * Display flag can be set to observe bubble up and trickle down operations
     */
    private boolean displayFlag;
    
    /**
     * Default constructor sets up array management conditions and default 
     * display flag setting
     */
    public ArrayHeapClass()
    {
        // Sets the array size of the fresh array to 0
        arraySize = 0;
        
        // Sets the arrayCapacity to the default capacity
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        
        // Sets the displayFlag to false by default
        displayFlag = false;
        
        // Initializes the array to the arrayCapacity
        heapArray = new int[arrayCapacity];
    }
    
    /**
     * Copy constructor copies array and array management conditions and 
     * default display flag setting
     * 
     * @param copied - ArrayHeapClass object to be copied
     */
    public ArrayHeapClass( ArrayHeapClass copied )
    {
        // Creates the variables needed for this constructor
        int arrDex;
        
        // Copies the arraySize to the new object
        arraySize = copied.arraySize;
        
        // Copies the arrayCapacity to the new object
        arrayCapacity = copied.arrayCapacity;
        
        // Copies the displayFlag to the new object
        displayFlag = copied.displayFlag;
        
        // Creates the array for the new object
        heapArray = new int[arrayCapacity];
        
        // Loops through the arrays and copies values
        for ( arrDex = 0; arrDex < arraySize; arrDex++)
        {
            // Copies data to the temp array
            heapArray[arrDex] = copied.heapArray[arrDex];
        }
    }
    
    /**
     * Accepts integer item and adds it to heap
     * <p>
     * Note: uses bubbleUpArrayHeap to resolve unbalanced heap after data 
     * addition
     * Note: Always checks for resize before adding data
     * 
     * @param newItem - integer item to be added
     */
    public void addItem( int newItem )
    {
        // Checks if a resize is necessary to add the new item
        checkForResize();
        
        if( displayFlag )
        {
            System.out.println();
            System.out.println("Adding new value: " + newItem);
        }
        
        // Sets the new item as the farthest right of the bottom row
        heapArray[arraySize] = newItem;
        
        // Calls bubble up on that new item
        bubbleUpArrayHeap( arraySize );
        
        // Increments the array size
        arraySize++;
    }
    
    /**
     * Recursive operation to reset data in the correct order for the max heap 
     * after new data addition
     * 
     * @param currentIndex - index of current item being assessed, and moved
     * up as needed
     */
    private void bubbleUpArrayHeap( int currentIndex )
    {
        // Creates a convenience variable for the parent
        int parentIndex = (currentIndex - 1) / 2;
        
        // Checks current against the parent
        if( heapArray[currentIndex] > heapArray[parentIndex])
        {
            if( displayFlag )
            {
                System.out.println("   - Bubble up:");
                System.out.println("     - Swapping parent: " + heapArray
                		[parentIndex] + " with child: " + heapArray
                		[currentIndex]);
            }
            
            // Creates a temp variable and save the parent
            int temp = heapArray[parentIndex];
            
            // Sets the parent as the current
            heapArray[parentIndex] = heapArray[currentIndex];
            
            // Sets the current as the temp with parent
            heapArray[currentIndex] = temp;
            
            // Recursive calls with the parent
            bubbleUpArrayHeap( parentIndex );
        }
    }
    
    /**
     * Automatic resize operation used prior to any new data addition in 
     * the heap
     * <p>
     * Note: Tests for full heap array, and resizes to twice the current 
     * capacity as required
     */
    private void checkForResize()
    {
    	// Creates the variables needed for this method
        int arrDex;
        int[] tempArray;
        
        // Checks for size being equal to capacity
        if( arrayCapacity == arraySize )
        {
            // Updates the array capacity global variable
            arrayCapacity = arrayCapacity * 2;
            
            // Initializes the temporary array
            tempArray = new int[arrayCapacity];
            
            // For loop to copy the data
            for ( arrDex = 0; arrDex < arraySize; arrDex++)
            {
                // Copies data to the temp array
                tempArray[arrDex] = heapArray[arrDex];
            }
            
            // Assigns the temp array to student array
            heapArray = tempArray;
        }
    }
    
    /**
     * Tests for empty heap
     * 
     * @return - boolean result of test
     */
    public boolean isEmpty()
    {
        // Returns if the size is zero, indicating an empty heap
        return arraySize == 0;
    }
    
    /**
     * Removes integer item from top of max heap
     * <p>
     * Note: Uses trickleDownArrayHeap to resolve unbalanced heap after data
     * removal
     * 
     * @return - integer item removed
     */
    public int removeItem()
    {
        // Creates the variables needed for this method
        int removed;
        
        // Checks if the heap is empty
        if( isEmpty( ))
        {
            // Returns not found
            return NOT_FOUND;
        }
        
        // Saves the element being removed
        removed = heapArray[0];
        
        if( displayFlag )
        {
            System.out.println();
            System.out.println("Removing value: " + removed);
        }
        
        // Sets the farthest right element in the bottom row to the top
        heapArray[0] = heapArray[arraySize - 1];
        
        // Decrements the size
        arraySize--;
        
        // Calls trickle down on the first element of the heap
        trickleDownArrayHeap( 0 );
        
        // Returns the removed object
        return removed;
    }
    
    /**
     * Utility method to set the display flag for displaying internal 
     * operations of the heap bubble and trickle operations
     * 
     * @param setState - flag used to set the state to display, or not
     */
    public void setDisplayFlag( boolean setState )
    {
        // Sets the display flag as the entered state
        displayFlag = setState;
    }
    
    /**
     * Dumps array to screen as is, no filtering or management
     */
    public void showArray()
    {
        // Prints the heap without any formatting
        System.out.print(heapArray);
    }
    
    /**
     * Recursive operation to reset data in the correct order for the max 
     * heap after data removal
     * 
     * @param currentIndex - index of current item being assessed, and moved 
     * down as required
     */
    private void trickleDownArrayHeap( int currentIndex )
    {
        // Creates convenience variables for the children
        int leftChild = currentIndex * 2 + 1;
        int rightChild = currentIndex * 2 + 2;
        
        // Guards against going off the heap
        if( rightChild < arraySize )
        {
            /*
             *  Checks if the currentIndex and leftChild are less than the 
             *  rightChild
             */
            if( heapArray[currentIndex] < heapArray[rightChild] && heapArray
            		[leftChild] < heapArray[rightChild] )
            {
                if( displayFlag )
                {
                    System.out.println("   - Trickle down:");
                    System.out.println("     - Swapping parent: " + heapArray
                    		[currentIndex] + " with right child: " + heapArray
                    		[rightChild]);
                }
                
                // Saves the rightChild in a temp
                int temp = heapArray[rightChild];
                
                // Sets the rightChild as the currentIndex
                heapArray[rightChild] = heapArray[currentIndex];
                
                // Sets the currentIndex as the temp with right child
                heapArray[currentIndex] = temp;
                
                // Recurses with the rightChild as the new current
                trickleDownArrayHeap( rightChild );
            }
        }
            
        // Guards against going off the heap
        if( leftChild < arraySize)
        {
            // Checks if the currentIndex is less than the leftChild
            if( heapArray[currentIndex] < heapArray[leftChild] )
            {
                if( displayFlag )
                {
                    System.out.println("   - Trickle down:");
                    System.out.println("     - Swapping parent: " + heapArray
                    		[currentIndex] + " with left child: " + heapArray
                        	[leftChild]);
                }
                    
                // Saves the leftChild in a temp
                int temp = heapArray[leftChild];
                    
                // Sets the leftChild as the currentIndex
                heapArray[leftChild] = heapArray[currentIndex];
                    
                // Sets the currentIndex as the temp with left child
                heapArray[currentIndex] = temp;
                    
                // Recurses using the leftChild as the new current
                trickleDownArrayHeap( leftChild );
            }
        } 
    }
}
