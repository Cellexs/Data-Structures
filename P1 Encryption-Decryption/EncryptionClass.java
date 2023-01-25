package p1_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class EncryptionClass 
   {
	/**
	 * Stores the length of the side of the array
	 */
	private int arrSide;
	/**
	 * Sets up the array that will store the encrypted data
	 */
	private int[][] encryptedArr;
    /**
     * Constant for maximum input char limit
     */
    private final int MAX_INPUT_CHARS = 256;
    
    /**
     * Constant for unprintable char value used as message end char
     */
    private final int UNPRINTABLE_CHAR_VALUE = 127; // ASCII for delete key

    /**
     * Constant for minus sign used in getAnInt
     */
    private final char MINUS_SIGN = '-';
    
    /**
     * Class Global FileReader variable so methods can be used
     */
    private FileReader fileIn;
    
    /**
     * Default Constructor
     */
    public EncryptionClass()
    {
    	fileIn = null;
    	encryptedArr = new int[arrSide][arrSide];
    	arrSide = 0;
    }
    
    /**
     * Copy Constructor
     * @param copied - EncryptionClass object to be copied
     */
    public EncryptionClass( EncryptionClass copied )
    {
       fileIn = copied.fileIn;
       encryptedArr = copied.encryptedArr;
       arrSide = copied.arrSide;	
    }
    
    /**
     * Tests and reports if a character is found in a given string
     * <p>
     * Note: uses .charAt and .length Java utilities for string management
     * @param testChar - Character to be tested against the string
     * @param testString - String within which the character is tested
     * @return - Boolean result of test
     */
    private boolean charInString(char testChar,
            String testString)
    {
       // Sets the max length of the string
       int maxString = testString.length();
       // Declares an index variable for the loop
       int stringDex;
    	
       // Initializes the index to 0 and stops at the max length of the string
       for( stringDex = 0; stringDex < maxString; stringDex++ )
       {
    	   // Tests for if the character at the index is the desired character
    	   if ( testString.charAt(stringDex) == testChar )
    	   {
    		   // Returns true if it finds it
    		   return true;
    	   }
       }
       // Returns false if it doesn't ever find it
       return false;
    }
    
    /**
     * Decrypts string from array
     * @return - String result of decryption process
     */
    public String decryptData()
    {
       // Instantiates new variables for this method
       int diagRow;
       int colDex;
       int rowDex;
       boolean charTracker = true;
       String decryptedStr = "";
    	
       // Adds the sides to get the last element, and its diagonal row
       diagRow = arrSide + arrSide;
    	
       // Creates a while loop that ends when diagonal row becomes 0
       while (diagRow >= 0)
       {
    	   // For loop that initializes and decrements the row index
    	   for (rowDex = arrSide - 1; rowDex >= 0; rowDex--)
    	   {
    		   // For loop that initializes and increments the column index
    		   for (colDex = 0; colDex < arrSide; colDex++)
    		   {
    			   // Checks if it found the right box in the 2D array
    			   if (rowDex + colDex == diagRow )
    			   {
    				   // Checks for an unprintable character
    				   if ( encryptedArr[rowDex][colDex] == 
    						   UNPRINTABLE_CHAR_VALUE )
    				   {
    					   // Increments the tracker
    					   charTracker = false;
    				   }
    				   // Checks if the tracker has ever seen the character
    				   if ( charTracker )
    				   {
    					   // Adds each letter to the empty string
        				   decryptedStr += 
        						   (char)(encryptedArr[rowDex][colDex]);
    				   }
    			   }
    		   }
    	   }
    	   // Decreases the diagonal row variable
    	   diagRow--;
       }
       // Returns the string
       return decryptedStr;
    }
    
    /**
     * Displays array in character form for diagnostics
     * <p>
     * Note: No Java utilities are used in this method
     */
    public void displayCharArray()
    {
       // Instantiates new variables for this method
       int colDex;
       int rowDex;
    	
       // For loop that indexes through the rows
       for ( rowDex = 0; rowDex < arrSide; rowDex++)
       {
           // For loop that indexes through the columns
    	   for ( colDex = 0; colDex < arrSide; colDex++)
    	   {
    		   // Casts the elements to a char and prints
    		   System.out.print( (char)(encryptedArr[rowDex][colDex]) );
    	   }
    	   System.out.println();
       }
    }
    
    /**
     * Downloads encrypted data to file
     * <p>
     * Note: No action taken if array is empty
     * 
     * @param fileName String object holding file name to use
     */
    void downloadData( String fileName )
       {
        FileWriter toFile;
       
        int rowIndex, colIndex;
       
        if( arrSide > 0 )
           {
            try
               {
                toFile = new FileWriter( fileName );
            
                toFile.write( "" + arrSide + "\r\n" );
           
                for( rowIndex = 0; rowIndex < arrSide; rowIndex++ )
                   {
                    for( colIndex = 0; colIndex < arrSide; colIndex++ )
                       {
                        if( encryptedArr[ rowIndex ][ colIndex ] < 100 )
                           {
                            toFile.write( "0" );
                           }
                        
                        toFile.write("" 
                                 + encryptedArr[ rowIndex ][ colIndex ] + " " );
                       }
                 
                    toFile.write( "\r\n" );
                   }
           
                toFile.flush();
                toFile.close();
               }
       
            catch( IOException ioe )
               {
                ioe.printStackTrace();
               }
           }
       }
    
    /**
     * Encrypts given string into array
     * @param toEncrypt - String object to be encrypted
     */
    public void encryptData( String toEncrypt )
    {
       // Instantiates new variables for this method
       int strLength;
       int strDex;
       int diagRow;
       int colDex;
       int rowDex;
    	
       // Gives a variable of the length of the entered string
       strLength = toEncrypt.length();
    	
       /* Finds the square root of the string and adds 1 to it to make sure 
        * in can encompass the whole string, and then saves it as the 
        * side of the array
        */
       arrSide = (int)(findSquareRoot(strLength)) + 1;
    	
       // Initializes the array to a size that fits the string
       encryptedArr = new int[arrSide][arrSide];
    	
       // Creates the index to use for charAt when adding to the array
       strDex = 0;
       // The total that finds the diagonal rows by adding rowDex and colDex
       diagRow = arrSide + arrSide;

       // Creates a while loop that ends when diagonal row becomes 0
       while ( diagRow != -1 )
       {
    	   // For loop that initializes and increments the column index
    	   for ( colDex = 0; colDex < arrSide; colDex++)
    	   {
    		   // For loop that initializes and increments the row index
    		   for ( rowDex = 0; rowDex < arrSide; rowDex++)
    		   {
    			   // Checks for the right box in the 2D array
    			   if ( rowDex + colDex == diagRow )
    			   {
    				   // Checks if the string still has elements
    				   if ( strDex < strLength )
    				   {
    					   // This adds the character into the array
    					   encryptedArr[rowDex][colDex] = 
    							   (int)(toEncrypt.charAt(strDex));
    					   // This moves the string along
    					   strDex++;
    				   }
    				   else if ( strDex == strLength)
    				   {
						   /* This adds the unprintable character right 
						    * after the string ends
							*/
						   encryptedArr[rowDex][colDex] = 
								   (int)(UNPRINTABLE_CHAR_VALUE);
						   /*
						    *  Increments one last time to ensure this 
						    *  code only runs once
						    */
						   strDex++;
					   }
    				   else
    				   {
						   // Generates a random character and adds it
    					   encryptedArr[rowDex][colDex] = (getRandomCharValue());
					   }
    			   }
    		   }
    	   }
    	   // Decreases the diagonal row variable
    	   diagRow--;
       }
    }
    
    /**
     * Finds the square root of an integer value
     * <p>
     * Note: Finds square root to precision of 0.000001 without using any 
     * Java utilities other than abs
     * 
     * @param value - integer value to find square root of
     * @return - double square root value
     */
    private double findSquareRoot( int value )
    {
       // Sets the precision required for this particular square root
       double PRECISION = 0.000001;
    	
       // Sets up 0 as the min and the value as the max
       double lowerVal = 0;
       double upperVal = value;
    	
       // Calculates the mid value between upper and lower bounds
       double middleVal = ( (lowerVal + upperVal) / 2.0 );
    	
       // Sets the temp variable for the start of the loop
       double tempVal = middleVal * middleVal;
    	
       // Loop to continue the divide and conquer until within the precision
       while ( Math.abs( tempVal - value) > PRECISION)
       {
    	   // Tests the tempVal for if its greater than the value
    	   if ( tempVal > value)
    	   {
    		   // Sets the upper to the middle val
    		   upperVal = middleVal;
    	   }
    	   else
    	   {
    		   // Sets the lower value to the middle val
    		   lowerVal = middleVal;
    	   }
    		
    	   // Determines the new middle value
    	   middleVal = ( (lowerVal + upperVal) / 2.0 );
    	   
    	   // Determines the new temp value
    	   tempVal = middleVal * middleVal;
       }
       // Returns the desired squareroot to the precision entered
       return middleVal;
    }


    /**
     * gets an integer from the input stream
     * 
     * @param maxLength maximum length of characters
     * input to capture the integer
     * 
     * @return integer captured from file
     */
    private int getAnInt( int maxLength )
       {
        int inCharInt;
        int index = 0;
        String strBuffer = "";
        int intValue;
        boolean negativeFlag = false;

        try
           {
            inCharInt = fileIn.read();

            // clear space up to number
            while( index < maxLength && !charInString( (char)inCharInt, 
                                                           "0123456789+-" ) )
               {
                inCharInt = fileIn.read();
               
                index++;
               }
           
            if( inCharInt == MINUS_SIGN )
               {
                negativeFlag = true;
               
                inCharInt = fileIn.read();
               }

            while( charInString( (char)inCharInt, "0123456789" ) )
               {   
                strBuffer += (char)( inCharInt );

                index++;
               
                inCharInt = fileIn.read();
               }            
           }
       
        catch( IOException ioe )
           {
            System.out.println( "INPUT ERROR: Failure to capture character" );
          
            strBuffer = "";
           }
          
        intValue = Integer.parseInt( strBuffer );
       
        if( negativeFlag )
           {
            intValue *= -1;
           }
       
        return intValue;
       }
    
    /**
     * Generates the integer value of a random character between the 
     * lowest possible character value (space) and the highest possible 
     * character value (tilde)
     * <p>
     * Note: Method must be capable of generating a space value, a tilde 
     * value, and any possible character between; may use any appropriate 
     * Math utilities
     * 
     * @return - integer value of randomly generated character
     */
    private int getRandomCharValue()
    {
       // Creates a random object in order for me to use one of its methods
       Random rand = new Random();
       
       char space = ' ';
       char tilde = '~';
       
        int spaceInt = (int)(space);
        int tildeInt = (int)(tilde);
    	
       /* Creates a random value between 0 and int casted '~', then adds int casted ' ' to make it 
        * between 32 and 126 inclusive
        */
       int randVal = rand.nextInt(tildeInt) + spaceInt;
    	
       // Returns the random number
       return randVal;
    }

    /**
     * Uploads data from file holding a square array
     * 
     * @param fileName String object holding file name
     */
    void uploadData( String fileName )
       { 
        int rowIndex, colIndex;
      
        try
           {
            // Open FileReader 
            fileIn = new FileReader( fileName );
        
            // get side length
            arrSide = getAnInt( MAX_INPUT_CHARS );           
      
            encryptedArr = new int[ arrSide ][ arrSide ];
            
            for( rowIndex = 0; rowIndex < arrSide; rowIndex++ )
               {
                for( colIndex = 0; colIndex < arrSide; colIndex++ )
                   {
                    encryptedArr[ rowIndex ][ colIndex ] 
                                                  = getAnInt( MAX_INPUT_CHARS );
                   }
               }
            
            fileIn.close();
           }
      
        // for opening file
        catch( FileNotFoundException fnfe )
           {
            fnfe.printStackTrace();
           }
        
        // for closing file
        catch (IOException ioe)
           {
            ioe.printStackTrace();
           }
       }  
   }    