/**
 * 
 */
package p7_package;

/**
 * @author Chris Cisneros
 *
 * Binary Search Tree (BST) class for managing generic data
 * <p>
 * Note: Data used must have implemented Comparable interface
 */
public class Generic_BST_Class<GenericData extends Comparable<GenericData>>
{
    /**
     * Traverse code - inorder
     */
    public static final int IN_TRAVERSE = 102;
    
    /**
     * Traverse code - postorder
     */
    public static final int POST_TRAVERSE = 103;
    
    /**
     * Traverse code - preorder
     */
    public static final int PRE_TRAVERSE = 101;
    
    /**
     * Root of BST
     */
    private Node rootNode;
    
    /**
     * @author Chris Cisneros
     * 
     * Binary Search Tree node class for managing generic data
     */
    private class Node
    {
        /**
         * 
         */
        private GenericData nodeData;
        
        /**
         * left child reference
         */
        private Node leftChildRef;
        
        /**
         * right child reference
         */
        private Node rightChildRef;
        
        /**
         * Initialization constructor for data
         * 
         * @param inData - GenericData quantity
         */
        public Node( GenericData inData )
        {
            // Sets the inData to the nodes stored data
            nodeData = inData;
            
            // Sets the left child ref to null
            leftChildRef = null;
            
            // Sets the right child ref to null
            rightChildRef = null;
        }
    }
    
    /**
     * Default class constructor, initializes BST
     */
    public Generic_BST_Class()
    {
        // Simply sets the rootNode to null indicating empty tree
        rootNode = null;
    }
    
    /**
     * Clears tree
     */
    public void clearTree()
    {
        // Sets the rootNode to null
        rootNode = null;
    }
    
    /**
     * Provides inOrder traversal action using recursion
     * 
     * @param wkgRef - Node tree root reference at the current recursion level
     */
    private void displayInOrder( Node wkgRef )
    {
        // Checks if the current passed node is null
        if (wkgRef != null)
        {
            // Prints in the order left, parent, right
            displayInOrder( wkgRef.leftChildRef );
            System.out.println( wkgRef.nodeData.toString() );
            displayInOrder( wkgRef.rightChildRef );
        }
    }
    
    /**
     * Provides postOrder traversal action using recursion
     * 
     * @param wkgRef - Node tree root reference at the current recursion level
     */
    private void displayPostOrder( Node wkgRef )
    {
        // Checks if the current passed node is null
        if (wkgRef != null)
        {
            // Prints in the order left, right, parent
            displayPostOrder( wkgRef.leftChildRef );
            displayPostOrder( wkgRef.rightChildRef );
            System.out.println( wkgRef.nodeData.toString() );
        }
    }
    
    /**
     * Provides preOrder traversal action using recursion
     * 
     * @param wkgRef - Node tree root reference at the current recursion level
     */
    private void displayPreOrder( Node wkgRef )
    {
        // Checks if the current passed node is null
        if (wkgRef != null)
        {
            // Prints in the order parent, left, right
            System.out.println( wkgRef.nodeData.toString() );
            displayPreOrder( wkgRef.leftChildRef );
            displayPreOrder( wkgRef.rightChildRef );
        }
    }
    
    /**
     * Provides user with three ways to display BST data
     * 
     * @param traverseCode - int code for selecting BST traversal method, 
     * accepts PRE_TRAVERSE, IN_TRAVERSE, POST_TRAVERSE
     */
    public void displayTree( int traverseCode )
    {
        if( traverseCode == IN_TRAVERSE )
        {
            // Calls the display in order method
            displayInOrder( rootNode );
        }
        else if( traverseCode == POST_TRAVERSE )
        {
            // Calls the display post order method
            displayPostOrder( rootNode );
        }
        else if( traverseCode == PRE_TRAVERSE )
        {
            // Calls the display pre order method
            displayPreOrder( rootNode );
        }
        else
        {
            // The entered traverse code didn't match any of them
            System.out.println("The entered traverseCode isn't recognized.");
        }
    }
    
    /**
     * Insert method for BST
     * <p>
     * Note: method adds first node if tree is empty; otherwise calls 
     * insertHelper method
     * 
     * @param inData - GenericData data to be added to BST
     * @return - Boolean result of operation
     */
    public boolean insert( GenericData inData )
    {
        // Checks if its empty
        if( isEmpty() )
        {
            // Makes the root node a newly created node with the data
            rootNode = new Node( inData );
            
            // Returns true
            return true;
        }
        
        // Otherwise calls the helper with the root node and data
        return insertHelper( rootNode, inData );
    }
    
    /**
     * Recursive insert helper method for BST insert action
     * Adds new node to left or right of current node; does not allow duplicate
     * values to be inserted into tree
     * 
     * @param wkgRef - Node tree root reference at the current recursion level
     * @param inData - GenericData item to be added to BST
     * @return - Boolean result of operation
     */
    private boolean insertHelper( Node wkgRef, 
    		GenericData inData )
    {
        // Checks if the data is the same as the current node
        if( inData.compareTo( wkgRef.nodeData ) == 0 )
        {
            // Fails and returns false, preventing duplicates
            return false;
        }
        // Checks if the data is less than the wkg ref
        else if( inData.compareTo( wkgRef.nodeData ) < 0 )
        {
            // Checks for a left child
            if( wkgRef.leftChildRef != null )
            {
                // Recursive call using the left child as then new wkg ref
                insertHelper( wkgRef.leftChildRef, inData );
            }
            else
            {
                // Adds the data as the left child of the wkg ref
                wkgRef.leftChildRef = new Node( inData );
                
                // Return true
                return true;
            }
        }
        else
        {
            // Check for a right child ref
            if( wkgRef.rightChildRef != null )
            {
                // Recursive call using the right child as then new wkg ref
                insertHelper( wkgRef.rightChildRef, inData );
            }
            else
            {
                // Adds the data as the right child of the wkg ref
                wkgRef.rightChildRef = new Node( inData );
                
                // Return true
                return true;
            }
        }
        
        // If it gets here then it never was added so return false
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
        return rootNode == null;
    }
    
    /**
     * Searches tree from given node to minimum value node below it, stores 
     * data value found, unlinks the node, and returns it to the calling method
     * 
     * @param parentNode - Node reference to current node
     * @param childNode - Node reference to child node to be tested
     * @return - Node reference containing removed node
     */
    private Node removeFromMin( Node parentNode, Node childNode )
    {
        // Checks for the child's next left node to be null
        if( childNode.leftChildRef != null )
        {
            /*
             * Recursive calls with the child as the parent and the child's 
             * left as child
             */
            return removeFromMin( childNode, childNode.leftChildRef );
        }
        
        // Creates a temporary variable with the needed data
        Node temp = new Node( childNode.nodeData );
        
        /*
         * Sets the parents left child (node to be unlinked) as the child's 
         * current right node
         */
        parentNode.leftChildRef = childNode.rightChildRef;
        
        // Returns the temporary variable 
        return temp;
    }
    
    /**
     * Removes data node from tree using given key
     * <p>
     * Note: uses remove helper method
     * Note: uses search initially to get value, if it is in tree; if value 
     * found, remove helper method is called, otherwise returns null
     * 
     * @param inData - GenericData that includes the necessary key
     * @return - GenericData result of remove action
     */
    public GenericData removeItem( GenericData inData )
    {
        // Uses search method to search the tree for the item
        if( search( inData ) != null )
        {
            // If found then it calls the remove helper
            return removeItemHelper( rootNode, inData ).nodeData;
        }
        
        // Otherwise returns null
        return null;
    }
    
    /**
     * Remove helper for BST remove action
     * <p>
     * Note: Recursive method returns updated local root to maintain tree 
     * linkage
     * Note: uses removeFromMin method
     * 
     * @param wkgRef - Node tree root reference at the current recursion level
     * @param outData - GenericData item that includes the necessary key
     * @return - Node reference result of remove helper action
     */
    private Node removeItemHelper( Node wkgRef, GenericData outData )
    {
        // Checks for if the current node is greater than the data
        if ( wkgRef.nodeData.compareTo( outData ) < 0 )
        {
            // Recursively calls the remove helper with the right child
            wkgRef.rightChildRef = removeItemHelper( wkgRef.rightChildRef, outData );
        }
        // Checks if the current node is less than the data
        else if( wkgRef.nodeData.compareTo( outData ) > 0 )
        {
            // Recursively calls the remove helper with the left child
            wkgRef.leftChildRef = removeItemHelper( wkgRef.leftChildRef, outData );
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
            wkgRef.nodeData = wkgRef.rightChildRef.nodeData;
            
            // Moves the node's right child to the right child's right child
            wkgRef.rightChildRef = wkgRef.rightChildRef.rightChildRef;
        }
        // Otherwise just use the removeFromMin method
        else
        {
            // Makes the closest found value the wkg ref's node data
            wkgRef.nodeData = removeFromMin
            		( wkgRef, wkgRef.leftChildRef ).nodeData;
        }
        
        // Returns the ref to be linked
        return wkgRef;
    }
    
    /**
     * Searches for data in BST given GenericData with necessary key
     * 
     * @param searchData - GenericData item containing key
     * @return - GenericData reference to found data
     */
    public GenericData search( GenericData searchData )
    {
        // Calls the search helper with root node and requested data
        return searchHelper( rootNode, searchData );
    }
    
    /**
     * Helper method for recursive BST search action
     * 
     * @param wkgRef - Node tree root reference at the current recursion level
     * @param searchData - GenericData item containing key
     * @return - GenericData item found
     */
    private GenericData searchHelper( Node wkgRef, GenericData searchData )
    {
        // Checks for an empty tree
        if( wkgRef == null )
        {
            // Returns null if its empty
            return null;
        }
        // Checks the wkg ref against itself
        else if( searchData.compareTo( wkgRef.nodeData ) == 0 )
        {
            // If it matches then it returns the data
            return searchData;
        }
        // Checks the wkg ref against itself
        else if( searchData.compareTo( wkgRef.nodeData ) < 0 )
        {
            // If its less than it, then recursive call with the left child
            return searchHelper( wkgRef.leftChildRef, searchData );
        }
        // Checks the wkg ref against itself
        else
        {
            // If its greater than it, then recursive call with the right child
            return searchHelper( wkgRef.rightChildRef, searchData );
        }
    }
}