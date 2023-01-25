/**
 * 
 */
package p10_package;

/**
 * @author Chris Cisneros
 */
public class SimpleBSTClass 
{
    /**
     * Root of BST
     */
    private SimpleStudentClass treeRoot;
    
    /**
     * Default class constructor
     */
    public SimpleBSTClass()
    {
        // Sets the treeRoot to null
        treeRoot = null;
    }
    
    /**
     * Copy constructor
     * 
     * @param copied - SimpleBSTClass object to be copied
     */
    public SimpleBSTClass( SimpleBSTClass copied )
    {
        /*
         * Calls the helper with the copied tree's root node and sets it as 
         * the new trees root node
         */
        treeRoot = copyConstructorHelper( copied.treeRoot );
    }
    
    /**
     * Clears tree of all data
     */
    public void clearTree()
    {
        // Sets the treeRoot to null, clearing the tree
        treeRoot = null;
    }
    
    /**
     * Recursive copy constructor helper
     * 
     * @param wkgCopiedRef - reference to SimpleStudentClass node
     * @return - SimpleStudentClass reference to link to calling method/node
     */
    private SimpleStudentClass copyConstructorHelper( SimpleStudentClass wkgCopiedRef )
    {
        // Checks for is the current level is null
        if( wkgCopiedRef != null )
        {
            // Creates the current recursion level Node
            SimpleStudentClass currentNode = new SimpleStudentClass( wkgCopiedRef );
            
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
     * Displays BST in order
     */
    public void displayInOrder()
    {
        // Begins the recursive process with the root
        displayInOrderHelper( treeRoot );
    }
    
    /**
     * Recursively implements inOrder traversal action
     * 
     * @param wkgRef - SimpleStudentClass tree root reference at the current 
     * recursion level
     */
    private void displayInOrderHelper( SimpleStudentClass wkgRef )
    {
        // Checks if the current passed node is null
        if (wkgRef != null)
        {
            // Prints in the order left, parent, right
            displayInOrderHelper( wkgRef.leftChildRef );
            System.out.println( wkgRef.toString() );
            displayInOrderHelper( wkgRef.rightChildRef );
        }
    }
    
    /**
     * Returns larger of two values
     * <p>
     * Note: used by treeHeightHelper
     * 
     * @param one - one of the two values to be tested
     * @param other - the other of the two values to be tested
     * @return - highest value of the two input values
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
     * Finds height of BST Node: empty tree: -1; root node only: 0; number of 
     * edges thereafter
     * 
     * @return - height of tree - maximum number of edges from root node to 
     * lowest part of tree
     */
    public int getTreeHeight()
    {
        // Begins the recursive process with the root
        return treeHeightHelper( treeRoot );
    }
    
    /**
     * Insert method for BST
     * <p>
     * Note: Overloaded insert uses insert method with individual student 
     * information data items
     * 
     * @param inName - name data to be added to BST
     * @param inStudentID - student ID data to be added to BST
     * @param inGender - gender data to be added to BST
     * @param inGPA - gpa data to be added to BST
     * @return - Boolean result of action
     */
    public boolean insert( String inName, int inStudentID, char inGender, double inGPA )
    {
        // Creates a new student object and utilizes the student object insert method
        return insert( new SimpleStudentClass( inName, inStudentID, 
        		inGender, inGPA ) );
    }
    
    /**
     * Insert method for BST
     * <p>
     * Note: Overloaded insert uses insert helper method with a 
     * SimpleStudentClass object
     * 
     * @param newNode - SimpleStudentClass object to be added to BST
     * @return - Boolean result of action
     */
    public boolean insert( SimpleStudentClass newNode )
    {
        // Checks for an empty tree
        if( isEmpty() )
        {
            // Adds node as the root and return true
            treeRoot = newNode;
            return true;
        }
        
        // Calls the recursive helper with the root node and data
        return insertHelper( treeRoot, newNode );
    }
    
    /**
     * Insert helper method for BST insert action
     * <p>
     * Note: Does not allow duplicate entries (i.e., duplicate student IDs)
     * 
     * @param wkgRef - SimpleStudentClass tree root reference at the current
     * recursion level
     * @param newNode - SimpleStudentClass object to be added to BST
     * @return - Boolean result of insertion action
     */
    private boolean insertHelper( SimpleStudentClass wkgRef, SimpleStudentClass newNode )
    {
        
        if( newNode.studentID < wkgRef.studentID )
        {
            if( wkgRef.leftChildRef != null )
            {
                return insertHelper( wkgRef.leftChildRef, newNode );
            }
            
            else
            {
                wkgRef.leftChildRef = newNode;
                return true;
            }
        }
        
        else if( newNode.studentID > wkgRef.studentID )
        {
            if( wkgRef.rightChildRef != null )
            {
                return insertHelper( wkgRef.rightChildRef, newNode );
            }
            
            else
            {
                wkgRef.rightChildRef = newNode;
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Test for empty tree
     * 
     * @return - Boolean result of test
     */
    public boolean isEmpty()
    {
        // Returns of the root node is null, which would mean its empty
        return treeRoot == null;
    }
    
    /**
     * Recursively searches tree from given node to minimum value node, stores 
     * data value found, and then unlinks the node
     * 
     * @param minParent - SimpleStudentClass reference to parent of child node;
     * used for linking from parent to child's right child
     * @param minChild - SimpleStudentClass reference to child node to be 
     * tested
     * @return - SimpleStudentClass reference containing removed node
     */
    private SimpleStudentClass removeFromMin( SimpleStudentClass minParent, SimpleStudentClass minChild )
    {
    	// Checks for the child's next left node to be null
        if( minChild.leftChildRef != null )
        {
            /*
             * Recursive calls with the child as the parent and the child's 
             * left as child
             */
            return removeFromMin( minChild, minChild.leftChildRef );
        }
        
        // Creates a temporary variable with the needed data
        SimpleStudentClass temp = new SimpleStudentClass( minChild );
        
        /*
         * Sets the parents left child (node to be unlinked) as the child's 
         * current right node
         */
        minParent.leftChildRef = minChild.rightChildRef;
        
        // Returns the temporary variable 
        return temp;
    }
    
    /**
     * Removes data node from tree using given key
     * <p>
     * Note: verifies data node is in the tree, then uses remove helper method
     * 
     * @param studentID - item that is used for search/removal
     * @return - SimpleStudenClass result of remove action
     */
    public SimpleStudentClass removeNode( int studentID )
    {
        // Uses search method to search the tree for the item
        if( search( studentID ) != null )
        {
            // Searches for and saves the student
            SimpleStudentClass removed = search( studentID );
            
            // Calls the remove helper to remove it and relink
            treeRoot = removeNodeHelper( treeRoot, studentID );
            
            // Returns the removed object
            return removed;
        }
        
        // Otherwise returns null
        return null;
    }
    
    /**
     * Recursive helper for BST remove action
     * <p>
     * Note: uses removeFromMin method
     * Note: Since removeNode calling method verifies data node existence,
     * this method does not need to treat missing node condition
     * 
     * @param wkgRef - SimpleStudentClass tree root reference at the current 
     * recursion level
     * @param studentID - item that is used for search/removal
     * @return - SimpleStudentClass reference result of remove helper action
     */
    private SimpleStudentClass removeNodeHelper( SimpleStudentClass wkgRef, 
    		int studentID )
    {
        // Checks if the current node is greater than the data
        if ( wkgRef.studentID < studentID )
        {
            // Recursively calls the remove helper with the right child
            wkgRef.rightChildRef = removeNodeHelper( wkgRef.rightChildRef, 
            		studentID );
        }
        // Checks if the current node is less than the data
        else if( wkgRef.studentID > studentID )
        {
            // Recursively calls the remove helper with the left child
            wkgRef.leftChildRef = removeNodeHelper( wkgRef.leftChildRef, 
            		studentID );
        }
        // Checks if there is a left child
        else if( wkgRef.leftChildRef == null )
        {
            // Connects to the right child
            wkgRef = wkgRef.rightChildRef;
        }
        // Checks if there is a right child
        else if( wkgRef.rightChildRef == null )
        {
            // Connects to the left child
            wkgRef = wkgRef.leftChildRef;
        }
        /*
         * It got the node that will be removed so check the right child 
         * for a left child
         */
        else if( wkgRef.rightChildRef.leftChildRef == null )
        {
            // Moves the data from the right child of the node to the node
            wkgRef = wkgRef.rightChildRef;
            
            // Moves the node's right child to the right child's right child
            wkgRef.rightChildRef = wkgRef.rightChildRef.rightChildRef;
        }
        // Otherwise just use the removeFromMin method
        else
        {
            // Makes the closest found value the wkg ref's node data
            wkgRef = removeFromMin( wkgRef, wkgRef.leftChildRef );
        }
        
        // Returns the ref to be linked
        return wkgRef;
    }
    
    /**
     * Searches for data in BST given necessary student ID key
     * 
     * @param studentID - search value
     * @return - SimpleStudentClass reference to found data
     */
    public SimpleStudentClass search( int studentID )
    {
        // Calls the search helper with root node and requested data
        return searchHelper( treeRoot, studentID );
    }
    
    /**
     * Helper method for BST search action
     * 
     * @param wkgRef - SimpleStudentClass working reference at the current 
     * recursion level
     * @param studentID - item containing key (student ID number)
     * @return - Boolean result of search
     */
    private SimpleStudentClass searchHelper( SimpleStudentClass wkgRef, 
    		int studentID )
    {
        // Checks for an empty tree
        if( wkgRef == null )
        {
            // Returns null if its empty
            return null;
        }
        // Checks the wkg ref against itself
        else if( wkgRef.studentID == studentID )
        {
            // If it matches then it returns the data
            return wkgRef;
        }
        // Checks the wkg ref against itself
        else if( wkgRef.studentID > studentID )
        {
            // If its less than it, then recursive call with the left child
            return searchHelper( wkgRef.leftChildRef, studentID );
        }
        // Checks the wkg ref against itself
        else
        {
            // If its greater than it, then recursive call with the right child
            return searchHelper( wkgRef.rightChildRef, studentID );
        }
    }
    
    /**
     * Helper method to find height of BST
     * 
     * @param wkgRef - SimpleStudentClass working reference used at the current
     * level of recursion
     * @return - height of tree - maximum number of edges from root node to 
     * lowest part of tree
     */
    private int treeHeightHelper( SimpleStudentClass wkgRef )
    {
        // Declares the variables needed for this method
        int leftTree;
        int rightTree;
        
        // Checks for the end of the tree
        if( wkgRef == null )
        {
            // Starts the chain back up from an empty tree, -1
            return -1;
        }
        
        // Saves the result of the recursive left call
        leftTree = treeHeightHelper( wkgRef.leftChildRef );
        
        // Saves the result of the recursive right call
        rightTree = treeHeightHelper( wkgRef.rightChildRef );
        
        // Returns the greater of the heights
        return getMax( leftTree, rightTree ) + 1;
    }
}
