/**
 * 
 */
package p10_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Chris Cisneros
 */
public class HashClass 
{
    /**
     * Table size default
     */
    private final int DEFAULT_TABLE_SIZE = 10;
    
    /**
     * Size of the base table
     */
    private int tableSize;
    
    /**
     * Array for hash table
     */
    private SimpleBSTClass[] tableArray;
    
    /**
     * File reader object for use in data load operation
     * <p>
     * Note: Used globally within the class to allow supporting input methods to be used
     */
    private static java.io.FileReader fileIn;
    
    /**
     * Constant for identifying end of file in data load operation
     */
    private static final int END_OF_FILE_MARKER = -1;
    
    /**
     * Constant for identifying character in file in data load operation
     */
    private static final char SPACE = ' ';
    
    /**
     * Constant for identifying character in file in data load operation
     */
    private static final char SEMICOLON = ';';
    
    /**
     * Constant for identifying character in file in data load operation
     */
    private static final char TAB_CHAR = '\t';
    
    /**
     * Constant for identifying character in file in data load operation
     */
    private static final char NEWLINE_CHAR = '\n';
    
    /**
     * Constant for identifying character in file in data load operation
     */
    private static final char CARRIAGE_RETURN_CHAR = '\r';
    
    /**
     * Default constructor
     */
    public HashClass()
    {
        // Declares the variables needed for this method
        int arrDex;
        
        // Sets the table size to the default
        tableSize = DEFAULT_TABLE_SIZE;
        
        // Initializes the array
        tableArray = new SimpleBSTClass[ tableSize ];
        
        // Loops through the array
        for( arrDex = 0; arrDex < tableSize; arrDex++ )
        {
            // Creates a new BST Class Object for each element
            tableArray[arrDex] = new SimpleBSTClass();
        }
    }
    
    /**
     * Initialization constructor
     * 
     * @param inTableSize - sets table size
     */
    public HashClass( int inTableSize )
    {
        // Declares the variables needed for this method
        int arrDex;
        
        // Sets the table size to the default
        tableSize = inTableSize;
        
        // Initializes the array
        tableArray = new SimpleBSTClass[ tableSize ];
        
        // Loops through the array
        for( arrDex = 0; arrDex < tableSize; arrDex++ )
        {
            // Creates a new BST Class Object for each element
            tableArray[arrDex] = new SimpleBSTClass();
        }
    }
    
    /**
     * Copy constructor
     * 
     * @param copied - HashClass object to be copied
     */
    public HashClass( HashClass copied )
    {
        // Declares the variables needed for this method
        int arrDex;
        
        // Sets the table size to the default
        tableSize = copied.tableSize;
        
        // Initializes the array
        tableArray = new SimpleBSTClass[ tableSize ];
        
        // Loops through the array
        for( arrDex = 0; arrDex < tableSize; arrDex++ )
        {
            // Creates a new BST Class Object for each element
            tableArray[arrDex] = new SimpleBSTClass( copied.tableArray[arrDex] );
        }
    }
    
    /**
     * Adds item to hash table
     * Uses overloaded addItem with object; optionally may be anonymous 
     * constructor
     * 
     * @param inName - name of student
     * @param inStudentID - student ID
     * @param inGender - gender of student
     * @param inGPA - gpa of student
     * @return - Boolean success of operation
     */
    public boolean addItem( String inName, int inStudentID, char inGender, double inGPA )
    {
        /*
         * Anonymously creates a new SimpleStudentClass object and passes it 
         * to the other addItem method
         */
        return addItem( new SimpleStudentClass( inName, inStudentID, 
        		inGender, inGPA ) );
    }
    
    /**
     * Adds item to hash table
     * Overloaded method that accepts SimpleStudentClass object
     * 
     * @param newItem - student class object
     * @return - Boolean success of operation
     * @throws - java.lang.IndexOutOfBoundsException - if hash calculation
     * failure
     */
    public boolean addItem( SimpleStudentClass newItem )
    {
        // Declares the variables needed for this method
        int hashedID;
        
        // Hashes the ID
        hashedID = generateHash( newItem.studentID );
        
        // Attempts to index with hash
        try
        {
            // Returns the result of the ID being inserted
            return tableArray[hashedID].insert( newItem );
        }
        
        // Catches the possible hash failure
        catch( IndexOutOfBoundsException e )
        {
        	System.out.println("There was a hash calculation failure!");
        	System.out.println();
        	return false;
        }
    }
    
    /**
     * Indicates whether a given character is found in a given string
     * <p>
     * Note: Uses .length and .charAt
     * 
     * @param testChar - character to be tested in the string
     * @param str - string given for the character search
     * @return - Boolean indication that the character was found in the string
     */
    private boolean charInString( char testChar, String str )
    {
        // Declares the variables needed for this method
        int strLength = str.length();
        int strDex;
        
        // Loops through the entire string
        for( strDex = 0; strDex < strLength; strDex++ )
        {
            // Compares the letter at the strDex to the testChar
            if( str.charAt(strDex) == testChar )
            {
                // Returns true if the letter was found
                return true;
            }
            
        }
        
        // The char wasn't found, so return false
        return false;
    }
    
    /**
     * Clears hash table by clearing all trees
     */
    public void clearHashTable()
    {
        // Declares the variables needed for this method
        int arrDex;
        
        // Indexes through the array
        for( arrDex = 0; arrDex < tableSize; arrDex++ )
        {
            // Clears all the trees
            tableArray[arrDex].clearTree();
        }
    }
    
    /**
     * Searches for item in hash table
     * 
     * @param studentID - used for requesting data
     * @return - SimpleStudentClass object found, or null if not found
     */
    public SimpleStudentClass findItem( int studentID )
    {
        // Declares the variables needed for this method
        int hashedID;
        
        // Hashes the ID
        hashedID = generateHash( studentID );
        
        // Returns the ID if in the tree, or null
        return tableArray[hashedID].search( studentID );
    }
    
    /**
     * Method converts student ID within data value to hash value for use as 
     * index in hash table
     * <p>
     * Note: Method is overloaded, this one can be used with a student class 
     * string value that holds the student ID
     * Note: calls overloaded generateHash with string converted to integer
     * Note: digits are individually multiplied by position (1 - 6, left to 
     * right) and added to sum before being set to hash value. E.g., for 
     * 654987, last (LSD) digit (7) is multiplied by 6, then fifth digit 
     * (8) is multiplied by 5, then fourth digit (9) is multiplied by 4, etc.
     * 
     * @param dataString - String value contains student data within which the student ID will be converted to hash value
     * @return - integer hash index value
     */
    public int generateHash( String dataString )
    {
        // Calls the other hash method with the student ID
        return generateHash( getStudentID( dataString ) );
    }
    
    /**
     * Method converts student ID within data value to hash value for use as 
     * index in hash table
     * <p>
     * Note: Method is overloaded, this one can be used with a student ID 
     * number
     * Note: digits are individually multiplied by position (1 - 6, left to 
     * right) and added to sum before being set to hash value. E.g., for 
     * 654987, last (LSD) digit (7) is multiplied by 6, then fifth digit (8) 
     * is multiplied by 5, then fourth digit (9) is multiplied by 4, etc.
     * 
     * @param studentID- contains student ID number to be converted to hash 
     * value
     * @return - integer hash index value
     */
    public int generateHash( int studentID )
    {
        // Declares the variables needed for this method
        int currentID = studentID;
        int multiple = 6;
        int popNum, hashValue = 0;
        
        // Runs until it gets the first digit alone
        while( currentID > 0 )
        {
            // Saves the last digit
            popNum = currentID % 10;
            
            // Lowers the total digits by one, removing the last
            currentID = currentID / 10;
            
            // Multiplies the popped digit by the multiple
            hashValue += popNum * multiple;
            
            // Decrements the multiple
            multiple--;
        }
        
        // Returns the hashed index
        return hashValue % tableSize;
    }
    
    /**
     * Removes item from hash table
     * 
     * @param studentID - used for requesting data
     * @return - SimpleStudentClass object removed, or null if not found
     * @throws - java.lang.IndexOutOfBoundsException - if hash calculation 
     * failure
     */
    public SimpleStudentClass removeItem( int studentID )
    {
        // Declares the variables needed for this method
        int hashedID;
        
        // Hashes the ID
        hashedID = generateHash( studentID );
        
        // Attempts to index with hash
        try
        {
            // Returns the result of the ID being inserted
            return tableArray[hashedID].removeNode( studentID );
        }
        
        // Catches the possible failure of hashing
        catch( IndexOutOfBoundsException e )
        {
        	System.out.println("There was a hash calculation failure!");
        	System.out.println();
        	return null;
        }
    }
    
    /**
     * Traverses through string, finds student ID, returns
     * 
     * @param dataString - String object through which process traverses
     * @return - extracted student ID
     */
    private int getStudentID( String dataString )
    {
        int index = 0;
        int strLen = dataString.length();
        char testChar;
        String numString = "";
        
        while( index < strLen && 
                    !charInString( dataString.charAt( index ), "0123456789" ) )
        {
            index++;
        }
        
        testChar = dataString.charAt( index );
        
        while( index < strLen && charInString( testChar, "0123456789" ) )
        {
            numString += testChar;
            
            index++;
            
            testChar = dataString.charAt( index );
        }
        
        return Integer.parseInt( numString );
    }
    
    /**
     * Traverses through all array bins, finds heights of each tree, then 
     * displays as table
     * 
     * Shows table of tree heights, then shows table size and number of digits 
     * of the student ID used for hashing, then shows the number of empty bins 
     * and the tallest tree height of the set
     */
    public void showHashTableStatus()
    {
        int index, heightValue = 0, nilCount = 0, maxHeight = -1;
        
        System.out.println( "Tree height report: " );
        System.out.print( " Index: ");
        
        for( index = 0; index < tableSize; index++ )
        {
            System.out.format( "%6d ", index );
        }
        
        System.out.println();
        System.out.print("         ");
        
        for( index = 0; index < tableSize; index++ )
        {
            System.out.print( "  -----");
        }
        
        System.out.println();
        System.out.print( "         ");
        
        for( index = 0; index < tableSize; index++ )
        {
            if( !tableArray[ index ].isEmpty() )
            {
                heightValue = tableArray[ index ].getTreeHeight();
                
                if( heightValue > maxHeight )
                {
                    maxHeight = heightValue;
                }
                
                System.out.format("%6d ", heightValue );                 
            }
            
            else
            {
                nilCount++;
                
                System.out.print("     * " );                 
            }
        }
        
        // final test for max after loop
        if( heightValue > maxHeight )
        {
            maxHeight = heightValue;
        }
        
        System.out.println( "\n\nWith a table size of " + tableSize );
        System.out.println( "The number of empty bins was "
              + nilCount + ", and the tallest tree height was "
              + maxHeight + '\n' );
    }
    
    /**
     * Local method uploads data character by character, parses characters, 
     * and loads into hash data structure
     * Exception: If there is a file failure such as file not found, method 
     * will return false
     * 
     * @param fileName - name of file in local directory required for upload
     * @return - returns Boolean evidence of success
     */
    public boolean loadDataFromFile( String fileName )
    {
        String name, idStr, genderStr, gpaStr;
        int idVal;
        char genderVal;
        double gpaVal;
        boolean failedAccess = false, endInput = false;
        
        try
        {
            // Open FileReader 
            fileIn = new FileReader( fileName );
        }
        
        catch( FileNotFoundException fnfe )
        {
            failedAccess = true;
            
            return false;
        }
        
        do
        {
            // get name
            name = getStringFromFile( SEMICOLON );
             
            if( name != "" )
            {
                // get student ID
                idStr = getStringFromFile( SEMICOLON );
                idVal = Integer.parseInt( idStr );
                
                // get gender
                genderStr = getStringFromFile( SEMICOLON );
                genderVal = genderStr.charAt( 0 );
                
                // get gpa
                gpaStr = getStringFromFile( CARRIAGE_RETURN_CHAR );
                gpaVal = Double.parseDouble( gpaStr );
                
                // load data into StudentClass object
                failedAccess = !addItem( name, idVal, genderVal, gpaVal );
            }
             
            else
            {
                endInput = true;
            }
        }
        while( !failedAccess && !endInput);
        
        try
        {
            if( fileIn != null )
            {
                fileIn.close();
            }
        }
        
        catch( IOException ioe )
        {
            System.out.println( "DATA ACCESS ERROR: Failure to close file" );
        }
        
        return !failedAccess;
    }
    
    /**
     * Local method for getting a string with specified end characters, 
     * ignoring most white space
     * 
     * @param endChar - flag character to end input
     * @return - integer character for use in input process
     */
    private String getStringFromFile( char endChar )
    {
        int nextCharInt = 0;
        String outString = "";
        
        try
        {
            // skip leading white space
            do
            {
                nextCharInt = fileIn.read();
            }
            while( nextCharInt != END_OF_FILE_MARKER 
                     && ( (char)nextCharInt == SPACE 
                         || (char)nextCharInt == TAB_CHAR ) 
                             || (char)nextCharInt == NEWLINE_CHAR 
                                 || (char)nextCharInt == CARRIAGE_RETURN_CHAR );
            
            while( nextCharInt != END_OF_FILE_MARKER 
                             && (char)nextCharInt != endChar
                               && (char)nextCharInt != NEWLINE_CHAR 
                                  && (char)nextCharInt != CARRIAGE_RETURN_CHAR )
            {
                outString += (char)nextCharInt;
                
                nextCharInt = fileIn.read();
            }
        }
        
        catch( IOException ioe )
        {
            System.out.println( "INPUT ERROR: Failure to capture character" );
            
            outString = "";
        }
        
        if( nextCharInt == END_OF_FILE_MARKER )
        {
            outString = "";
        }
        
        return outString;
    }
}
