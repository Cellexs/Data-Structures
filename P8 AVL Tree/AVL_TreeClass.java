/**
 * 
 */
package p8_package;

/**
 * @author Chris Cisneros
 */
public class AVL_TreeClass 
{
    /**
     * Class global variable used to display tree structure
     */
    private boolean rowStartFlag;
    
    /**
     * Null character returned if data not available
     */
    private char NULL_CHAR = '\0';
    
    /**
     * Root of AVL Tree
     */
    private Node treeRoot;
    
    /**
     * Constant used to represent dash
     */
    private static char DASH = '-';
    
    /**
     * Constant used to represent space
     */
    private static char SPACE = ' ';
    
    
    private class Node
    {
        /**
         * Character data for Node class
         */
        private char data;
        
        /**
         * Left child reference for tree
         */
        private Node leftChildRef;
        
        /**
         * Right child reference for tree
         */
        private Node rightChildRef;
        
        /**
         * Initialization constructor for Node class
         * 
         * @param inData - char quantity
         */
        Node( char inData )
        {
            // Sets the inputed data as the data
            data = inData;
            
            // Sets the default left child to null
            leftChildRef = null;
            
            // Sets the default right child to null
            rightChildRef = null;
        }
        
        /**
         * Initialization constructor for data and child references
         * 
         * @param inData - char quantity
         * @param leftRef - reference for left child
         * @param rightRef - reference for right child
         */
        Node( char inData, Node leftRef, Node rightRef )
        {
            // Sets the passed in data as the data
            data = inData;
            
            // Sets the passed in left child as left child
            leftChildRef = leftRef;
            
            // Sets the passed in right child as the right child
            rightChildRef = rightRef;
        }
        
        /**
         * Copy constructor for AVL tree node
         * 
         * @param copied - Node object to be copied
         */
        Node( Node copied )
        {
            // Copies the nodes data
            data = copied.data;
            
            // Copies the nodes left child ref and assigns it
            leftChildRef = copied.leftChildRef;
            
            // Copies the nodes right child ref and assigns it
            rightChildRef = copied.rightChildRef;
        }
    }
    
    /**
     * Default class constructor
     */
    public AVL_TreeClass()
    {
        // Sets the tree root as null, indicating empty tree
        treeRoot = null;
    }
    
    /**
     * Copy constructor
     * 
     * @param copied - AVL_TreeClass object to be copied
     */
    public AVL_TreeClass( AVL_TreeClass copied )
    {
        /*
         * Calls the helper with the copied tree's root node and sets it as 
         * the new trees root node
         */
        treeRoot = copyConstructorHelper( copied.treeRoot );
    }
    
    /**
     * Recursive copy constructor helper
     * <p>
     * Note: Uses preorder strategy to copy nodes
     * 
     * @param wkgCopiedRef - Node reference at which method starts at 
     * each level of recursion
     * @return - Node reference to link current node information to 
     * methods/Nodes calling this method
     */
    private Node copyConstructorHelper( Node wkgCopiedRef )
    {
        // Checks for is the current level is null
        if( wkgCopiedRef != null )
        {
            // Creates the current recursion level Node
            Node currentNode = new Node( wkgCopiedRef );
            
            /*
             * Recursively calls the helper with the copied left child and 
             * sets its return as the currentNode's left child ref
             */
            currentNode.leftChildRef = copyConstructorHelper
            		( wkgCopiedRef.leftChildRef );
            
            /*
             * Recursively calls the helper with the copied right child and 
             * sets its return as the currentNode's right child ref
             */
            currentNode.rightChildRef = copyConstructorHelper( wkgCopiedRef.rightChildRef );
            
            // Returns the object for the linking process
            return currentNode;
        }
        
        // It hit the bottom of the root so return null as the link
        return null;
    }
    
    /**
     * Clears tree
     */
    public void clearTree()
    {
        // Sets the tree root node to null
        treeRoot = null;
    }
    
    /**
     * Displays text-graphical representation of one level/line of the AVL tree
     * 
     * @param workingNode - node reference at current recursive level
     * @param nodeHeight - height of tree plus two for current height of nodes, 
     * including lowermost null children
     * @param displayLevel - level of tree at which the current line of display
     * is to be presented
     * @param workingLevel - current level during recursive actions
     */
    private void displayAtTreeLevel( Node workingNode, int nodeHeight, 
    		int displayLevel, int workingLevel )
    {
        char charOut = workingNode.data;
        
        if( workingLevel == displayLevel )
        {
            displayValue( charOut, nodeHeight, workingLevel );
            
            return;
        }
        
        if( workingNode.leftChildRef != null )
        {
            displayAtTreeLevel( workingNode.leftChildRef, nodeHeight,
                                           displayLevel, workingLevel + 1 );
        }
        
        else
        {
            displayEmptyNodeSpaces( nodeHeight, displayLevel, workingLevel + 1 );
        }
        
        if( workingNode.rightChildRef != null )
        {
            displayAtTreeLevel( workingNode.rightChildRef, nodeHeight,
                        displayLevel, workingLevel + 1 );
        }
        
        else
        {
            displayEmptyNodeSpaces( nodeHeight, displayLevel, workingLevel + 1 );
        }
    }
    
    /**
     * Local recursive method to display a specified number of a specified 
     * character
     * 
     * @param numChars - number of characters to display
     * @param outChar - character to display
     */
    private void displayChars( int numChars, char outChar )
    {
        // Checks for if numChars is valid
        if( numChars > 0 )
        {
            // Prints the desired character
            System.out.print( outChar );
            
            // Then recursively calls the method again
            displayChars( numChars - 1, outChar );
        }
    }
    
    /**
     * Method that displays null or blank nodes for a tree at null locations
     * <p>
     * Note: used by displayAtTreeLevel
     * 
     * @param nodeHeight - height of tree plus two for current height of 
     * nodes, including lowermost null children
     * @param displayLevel - level of the tree at which the display will be 
     * applied
     * @param workingLevel - level of tree just below non-null node at which 
     * method is currently working
     */
    private void displayEmptyNodeSpaces( int nodeHeight, int displayLevel, 
    		int workingLevel )
    {
        int nodesToDisplay = toPower( 2, displayLevel - workingLevel ); 
        char charOut = SPACE;
        
        if( displayLevel == workingLevel )
        {
            charOut = DASH;
        }
        
        while( nodesToDisplay > 0 )
        {
            displayValue( charOut, nodeHeight, displayLevel );
            
            nodesToDisplay--;
        }
    }
    
    /**
     * Displays text-graphical representation of AVL tree
     */
    public void displayTreeStructure()
    {
        int displayLevel, nodeHeight = getTreeHeight( treeRoot ) + 2;
        int workingLevel = 1;
        
        if( treeRoot != null )
        {
            for( displayLevel = 1; displayLevel <= nodeHeight; displayLevel++ )
            {
                rowStartFlag = true;
                
                displayAtTreeLevel( treeRoot, nodeHeight, 
                                             displayLevel, workingLevel );
                
                System.out.println();
            }
        }
        
        else
        {
            System.out.println( "\nEmpty Tree - No Display");
        }
    }
    
    /**
     * Method used to display a character or color letter along with 
     * calculated leading spaces
     * <p>
     * Note: used in displayAtTreeLevel and displayEmptyNodeSpaces
     * 
     * @param data - data value to display, either letter or color data
     * @param nodeHeight - height of tree plus two for current height of nodes,
     * including lowermost null children
     * @param workingLevel - current level during recursive actions
     */
    private void displayValue( char data, int nodeHeight, int workingLevel )
    {
        int leadingSpaces;
        
        if( rowStartFlag )
        {
            leadingSpaces = toPower( 2, nodeHeight - workingLevel );
            
            rowStartFlag = false;
        }
        
        else
        {
            leadingSpaces = toPower( 2, nodeHeight - workingLevel + 1 ) - 1;
        }
        
        displayChars( leadingSpaces, SPACE );
        
        System.out.print( data );
    }
    
    /**
     * Provides tree height to user
     * <p>
     * Note: uses getTreeHeight
     * 
     * @return - integer height of tree
     */
    public int findTreeHeight()
    {
        // Returns the height of the tree root node
        return getTreeHeight( treeRoot );
    }
    
    /**
     * Gets balance factor indicating if tree is unbalanced from given root 
     * down
     * 
     * @param wkgLocalRef - Node from which balance factor is found
     * @return - integer balance factor
     */
    private int getBalanceFactor( Node wkgLocalRef )
    {
        // Checks is the wkg ref is null
        if( wkgLocalRef == null )
        {
            // Returns 0 if the wkg ref is null
            return 0;
        }
        
        // Returns the difference of the left height and right height
        return getTreeHeight(wkgLocalRef.leftChildRef) - getTreeHeight
        		(wkgLocalRef.rightChildRef);
    }
    
    /**
     * Finds maximum of two given numbers
     * 
     * @param one - one of two values to be tested
     * @param other - other of two values to be tested
     * @return - greater of the two values
     */
    private int getMax( int one, int other )
    {
        // Checks if one is greater
        if( one > other)
        {
            // Returns one
            return one;
        }
        
        // Otherwise it returns other
        return other;
    }
    
    /**
     * Tree height helper method
     * 
     * @param wkgLocalRef - Node node from which height is found
     * @return - integer height of tree
     */
    private int getTreeHeight( Node wkgLocalRef )
    {
        // Declares the variables needed for this method
        int leftTree;
        int rightTree;
        
        // Checks for the end of the tree
        if( wkgLocalRef == null )
        {
            // Starts the chain back up from an empty tree, -1
            return -1;
        }
        
        // Saves the result of the recursive left call
        leftTree = getTreeHeight( wkgLocalRef.leftChildRef );
        
        // Saves the result of the recursive right call
        rightTree = getTreeHeight( wkgLocalRef.rightChildRef );
        
        // Returns the greater of the heights
        return getMax( leftTree, rightTree ) + 1;
    }
    
    /**
     * In order display of tree
     */
    public void inOrderDisplay()
    {
        // Calls the recursive helper with the tree root
        inOrderDisplayHelper( treeRoot ); 
    }
    
    /**
     * Provides inOrder traversal action
     * 
     * @param wkgLocalRef - Node tree root reference at the current 
     * recursion level
     */
    private void inOrderDisplayHelper( Node wkgLocalRef )
    {
        // Checks if the current ref is null
        if( wkgLocalRef != null )
        {
            // Displays in order
            inOrderDisplayHelper(  wkgLocalRef.leftChildRef );
            System.out.print( wkgLocalRef.data );
            System.out.print(SPACE);
            inOrderDisplayHelper( wkgLocalRef.rightChildRef );
        }
    }
    
    /**
     * Insert method for AVL Tree
     * <p>
     * Note: uses insert helper method which returns the root node reference
     * to this method
     * 
     * @param inData - char data to be added to AVL Tree
     */
    public void insert( char inData )
    {
        // Calls the recursive helper with the root node
        treeRoot = insertHelper( treeRoot, inData );
    }
    
    /**
     * Insert helper method for AVL Tree insert action
     * <p>
     * Note: Does not allow duplicate keys
     * 
     * @param wkgLocalRef - Node tree root reference at the current 
     * recursion level
     * @param inData - char item to be added to AVL Tree
     * @return - Node reference to current AVL Tree root
     */
    private Node insertHelper( Node wkgLocalRef, char inData )
    {
        // Checks for a null value
        if( wkgLocalRef != null )
        {
            // Checks for duplicate data
            if( wkgLocalRef.data == inData )
            {
                // Simply keeps the list linked
                return wkgLocalRef;
            }
            // Checks if the data is greater than the node
            else if( wkgLocalRef.data < inData )
            {
                    // Recurses right and repeats
                    wkgLocalRef.rightChildRef = insertHelper
                    		( wkgLocalRef.rightChildRef, inData );
               
            }
            // Otherwise it isn't greater
            else
            {
                    // Recurses left and repeats
                    wkgLocalRef.leftChildRef = insertHelper
                    		( wkgLocalRef.leftChildRef, inData );
            }
            
            // Checks if a left left rotate is needed
            if( getBalanceFactor( wkgLocalRef ) > 1 && wkgLocalRef
            		.leftChildRef.leftChildRef != null )
            {
                    System.out.println("  Identified: Left Left Case");
                    
                    // Rotates the upper part last
                    return rotateRight( wkgLocalRef );
            }
            
            // Checks if a left right rotate is needed
            else if( getBalanceFactor( wkgLocalRef ) > 1 && wkgLocalRef
            		.leftChildRef.leftChildRef == null )
            {
                    System.out.println("  Identified: Left Right Case");
                    
                    // Rotates the lower part first
                    wkgLocalRef.leftChildRef = rotateLeft
                    		( wkgLocalRef.leftChildRef );
                    
                    // Rotates the upper part last
                    return rotateRight( wkgLocalRef );
            }
            
            // Checks if a right left rotate is needed
            else if( getBalanceFactor( wkgLocalRef ) < -1 && wkgLocalRef
            		.rightChildRef.rightChildRef == null)
            {
                System.out.println("  Identified: Right Left Case");
                
                // Rotates the lower part first
                wkgLocalRef.rightChildRef = rotateRight
                		( wkgLocalRef.rightChildRef );
                
                // Rotates the upper part last
                return rotateLeft( wkgLocalRef );   
            }
            
            // Checks if a right right rotate is needed
            else if( getBalanceFactor( wkgLocalRef ) < -1 && wkgLocalRef
            		.rightChildRef.rightChildRef != null)
            {
                System.out.println("  Identified: Right Right Case");
                    
                // Rotates the upper part last
                return rotateLeft( wkgLocalRef );
            }
            
            // Returns the node to be linked
            return wkgLocalRef;
        }
        
        System.out.println("Inserting " + inData + " and balancing");
        
        // Creates a new node and returns it for linking
        Node newNode = new Node( inData );
        return newNode;      
    }
    
    /**
     * Test for empty tree
     * 
     * @return - Boolean result of test
     */
    public boolean isEmpty()
    {
        // Checks if the tree root node is null
        return treeRoot == null; 
    }
    
    /**
     * Rotates local tree left or CCW
     * 
     * @param wkgLocalRef - reference of current item
     * @return - Node resulting current root
     */
    private Node rotateLeft( Node wkgLocalRef )
    {
        System.out.println("   - Rotating Left");
        
        // Makes a reference to the wkg ref
        Node tempRef = wkgLocalRef.rightChildRef;
             
        // Sets the temp's right child as the right's left child
        wkgLocalRef.rightChildRef = tempRef.leftChildRef;
        
        // Sets the wkg ref's right left's child to the temp ref
        tempRef.leftChildRef = wkgLocalRef;
        
        // Returns the temp
        return tempRef;
    }
    
    /**
     * Rotates local tree right or CW
     * 
     * @param wkgLocalRef - reference of current item
     * @return - Node resulting current root
     */
    private Node rotateRight( Node wkgLocalRef )
    {
        System.out.println("   - Rotating Right");
        
        // Makes a reference to the wkg ref's left child
        Node tempRef = wkgLocalRef.leftChildRef;
        
        // Sets the wkg ref's left child to the temp's right child
        wkgLocalRef.leftChildRef = tempRef.rightChildRef;
         
        // Sets the temp's right child to the wkg ref
        tempRef.rightChildRef = wkgLocalRef;
        
        // Returns the temp
        return tempRef;
    }
    
    /**
     * Searches for data in AVL Tree given char with necessary key
     * 
     * @param searchData - char item containing key
     * @return - char reference to found data
     */
    public char search( char searchData )
    {
        // Returns the result of the recursion started at the root
        return searchHelper( treeRoot, searchData );
    }
    
    /**
     * Helper method for AVL Tree search action
     * 
     * @param wkgLocalRef - Node tree root reference at the current 
     * recursion level
     * @param searchData - char item containing key
     * @return - char result of search
     */
    private char searchHelper( Node wkgLocalRef, char searchData )
    {
        // Checks for if the wkg ref is null
        if( wkgLocalRef != null )
        {
            // Checks for if the data is the same as the search
            if( searchData == wkgLocalRef.data )
            {
                // Returns the char found
                return wkgLocalRef.data; 
            }
            // Checks for if the search is greater than the wkg ref
            else if( (int)(searchData) > (int)(wkgLocalRef.data) )
            {
                // Recursive calls right and returns the result
                return searchHelper( wkgLocalRef.rightChildRef, searchData );
            }
            // Otherwise its less than the wkg ref
            else
            {
                // So it Recursive calls left and returns the result
                return searchHelper( wkgLocalRef.leftChildRef, searchData );
            }
        }
        
        // Returns null char since it was never found
        return NULL_CHAR;
    }
    
    /**
     * Local recursive method to calculate exponentiation with integers
     * 
     * @param base - base of exponentiation
     * @param exponent - exponent of exponentiation
     * @return - result of exponentiation calculation
     */
    private int toPower( int base, int exponent )
    {
        // Checks for if exponent has hit zero
        if( exponent > 0 )
        {
            // Returns the result of the recursive call
            return toPower( base, exponent - 1) * base;
        }
        
        // Returns a 1, 
        return 1;
    }
}