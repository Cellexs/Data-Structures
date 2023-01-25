/**
 * 
 */
package p4_package;

import java.util.Random;

/**
 * @author Chris Cisneros
 */
public class SudokuGeneratorClass 
{
    /**
     *  Private cell class to identify fixed (locked) cells and data (number)
     * @author disco
     */
    private class CellNode
    {
        // Private field that holds the locked/unlocked nature of the CellNode
        private boolean fixedCell;
        
        // Private field that hold the value of the CellNode
        private int value;
        
        /**
         * Default constructor for cell node
         */
        public CellNode()
        {
            // Initializes the fixed cell of a new CellNode to false
            fixedCell = false;
            
            // Initializes the value of a new CellNode to 0
            value = 0;
        }
        
        /**
         * Copy constructor for cell node
         * @param copied - CellNode object to be copied
         */
        public CellNode( CellNode copied )
        {
            // Copies the fixedCell from the passed in CellNode object
            fixedCell = copied.fixedCell;
            
            // Copies the value from the passed in CellNode object
            value = copied.value;
        }
    }
    
    /**
     * Constant for side of grid
     */
    private int GRID_SIDE = 9;
    
    /**
     * Constant for the side of sub grid
     */
    private int SUB_GRID_SIDE = 3;
    
    /**
     * Constant for range of numbers in Sudoku
     */
    private int SUDOKU_RANGE = 9;
    
    /**
     * Two dimensional array for holding cell nodes with fixed/locked code 
     * and number
     */
    private CellNode[][] sudokuArray;
    
    /**
     * Default generator class array sets up and initializes the Sudoku array
     */
    public SudokuGeneratorClass()
    {
        // Declares variables for the constructor
        int rowDex;
        int colDex;
        
        // Initializes the sudoku array to the needed size
        sudokuArray = new CellNode[GRID_SIDE][GRID_SIDE];
        
        // For loop that loops through the rows of the array
        for (rowDex = 0; rowDex < GRID_SIDE; rowDex++)
        {
            // For loop that loops through the columns of the array
            for (colDex = 0; colDex < GRID_SIDE; colDex++)
            {
                // Fills the array with default cell nodes
                sudokuArray[rowDex][colDex] = new CellNode();
            }
        }
    }
    
    /**
     * Generator class copy constructor
     * <p>
     * Note: Must create new CellNode for each copied element to eliminate 
     * aliasing
     * 
     * @param copied - SudokuGeneratorClass object to be copied
     */
    public SudokuGeneratorClass( SudokuGeneratorClass copied )
    {
        // Creates the variables needed for the method
        int rowDex;
        int colDex;
        CellNode[][] sudokuArray = new CellNode[GRID_SIDE][GRID_SIDE];
        
        // Indexes through the columns
        for (rowDex = 0; rowDex < GRID_SIDE; rowDex++)
        {
            // Indexes through the rows
            for (colDex = 0; colDex < GRID_SIDE; colDex++)
            {
                /*
                 *  Creates a new CellNode object at the current index of the 
                 *  original array and then adds it to the new array
                 */
                sudokuArray[rowDex][colDex] = new CellNode
                		(copied.sudokuArray[rowDex][colDex]);
            }
        }
    }
    
    /**
     * Method called to create the Sudoku game
     * <p>
     * Note: Sets up diagonal grid and calls helper
     * 
     * @param numEmpties - integer value indicating how many Sudoku cells to 
     * leave for the game player to fill in
     * @param showGrid - Boolean value that supports display of the 
     * transactions and the grids as the program runs
     */
    public void createSudoku( int numEmpties, boolean showGrid )
    {
        // Sets up the diagonal grids and sets them to be fixed
        setDiagonalSubGrids();
        
        displayGrid();
        
        // Calls the solver that utilizes recursion
        createSudokuHelper(0, 0, showGrid);
        
        displayGrid();
        
        // Removes the needed amount of spaces
        removeNumbers(numEmpties);
        
        // Checks if grid should be shown
        if (showGrid)
        {
            // Displays the grid
            displayGrid();
        }
    }
    
    /**
     * Sudoku creation method tries new numbers at each cell from left to 
     * right and top to bottom; backtracks if numbers don't work for given cell
     * 
     * @param rowPos - integer row location of current element
     * @param colPos - integer column location of current element
     * @param showGrid - boolean indicator that shows grids and transactions
     * as the method progresses if true
     * @return - boolean value to indicate success or failure of the 
     * recursive process
     */
    private boolean createSudokuHelper( int rowPos, int colPos, boolean showGrid )
    {
        // Declares all the variables needed for this method
        int tryNum;
        
        // Checks if I have passed the max columns
        if ( colPos == GRID_SIDE )
        {
            // Reset the position of the column index
            colPos = 0;
            
            // Increments the row index
            rowPos++;
        }
        
        // Checks if I have passed the max rows
        if ( rowPos == GRID_SIDE)
        {
            // Then your off the grid and everything works
            return true;
        }
        
        // Checks if the current index is fixed, and finds the next sub grid
        if ( sudokuArray[rowPos][colPos].fixedCell )
        {
            // Calls the next 
            return createSudokuHelper(rowPos, colPos + 1, showGrid);
        }
        
        // For loop that attempts 1 - 9
        for (tryNum = 1; tryNum <= SUDOKU_RANGE; tryNum++)
        {
            // Checks if the number works
            if ( !hasConflict(rowPos, colPos, tryNum) )
            {
                // Sets the number as the value 
                sudokuArray[rowPos][colPos].value = tryNum;
                
                // Checks if everything below it works
                if ( createSudokuHelper(rowPos, colPos + 1, showGrid) )
                {
                    // If it does returns true
                    return true;
                }
                
                // Resets the value back to zero and then loops again 
                sudokuArray[rowPos][colPos].value = 0; 
            }
        }
        
        // If it gets here then it failed
        return false;
        
    }
    
    /*
     * Displays grid as it is currently set up
     * Uses character formatting for grid display
     */
    public void displayGrid()
    {
        // Creates the variables for use in this method
        String strGrid;
        int rowDex;
        int colDex;
        
        // Creates the top of the grid
        strGrid = "-------------------------------------\n";
        
        // For loop that indexes through rows
        for (rowDex = 0; rowDex < GRID_SIDE; rowDex++)
        {
            // For loop that indexes through columns
            for (colDex = 0; colDex < GRID_SIDE; colDex++)
            {
                // Adds the format and the value
                strGrid = strGrid + "| ";
                
                // Checks if the value is a 0
                if ((sudokuArray[rowDex][colDex]).value == 0)
                {
                    // Adds a space instead
                    strGrid = strGrid + " ";
                }
                else
                {
                    // Adds the value
                    strGrid = strGrid + (sudokuArray[rowDex][colDex].value);
                }
                
                // Adds the rest of the grid block
                strGrid = strGrid + " ";
            }
            
            // Checks if there isn't any more rows
            if (rowDex < GRID_SIDE - 1)
            {
                // Adds the horizontal dividers
                strGrid = strGrid + "|\n|===================================|\n";
            }
        }
        
        // Creates the bottom of the grid
        strGrid = strGrid + "|\n-------------------------------------";
        
        System.out.println(strGrid);
    }
    
    /*
     * Generates random value between 1 and 9
     * <p>
     * Note: Uses double stage process. Calls random to get number between 1
     * and 9, then loops that many times generating random values. Finally 
     * takes the last value generated. Uses Math.random
     */
    private int genRandSudokuValue()
    {
        // Declares variables for use in this method
        int strtDex;
        int randIter;
        int randNum = 0;
        
        // Creates a Random object to be used
        Random rand = new Random();
        
        // Creates the random amount of iterations
        randIter = rand.nextInt(SUDOKU_RANGE) + 1;
        
        // Runs the random num generation code x amount of times
        for( strtDex = 0; strtDex < randIter; strtDex++ )
        {
            // Generates the random number
            randNum = rand.nextInt(SUDOKU_RANGE) + 1;
        }
        
        // Returns the random number selected
        return randNum;
    }
    
    /**
     * Checks for conflict of a given number in a given element
     * <p>
     * Note: Uses isInRow, isInCol, and isInSubGrid in one line of code to 
     * indicate if the number has already been used in the same row, the 
     * same column, or the same sub grid
     * 
     * @param rowLocIndex - integer row index of element
     * @param colLocIndex - integer column index of element
     * @param value - integer value to be tested for conflict
     * @return - boolean result of test
     */
    private boolean hasConflict( int rowLocIndex, int colLocIndex, int value )
    {
        // Returns true if the row, col or sub grid fails
        return isInRow(rowLocIndex, value) || isInCol(colLocIndex,
        		value) || isInSubGrid(rowLocIndex, colLocIndex, value);
    }
    
    /**
     * Checks for conflict of value in the same column
     * 
     * @param colIndex - integer column index
     * @param value - integer value to be tested
     * @return - boolean result of test
     */
    private boolean isInCol( int colIndex, int value )
    {
        // Creates the variables needed for this method
        int rowIndex;
        
        // Indexes through the entire column
        for (rowIndex = 0; rowIndex < GRID_SIDE; rowIndex++)
        {
            // Checks for a matching value in the column
            if ((sudokuArray[rowIndex][colIndex]).value == value)
            {
                // Returns true if the value is already used
                return true;
            }
        }
        
        // Returns false if the value is never found
        return false;
    }
    
    /**
     * Checks for conflict of value in the same row
     * 
     * @param rowIndex - integer row index
     * @param value - integer value to be tested
     * @return - boolean result of test
     */
    private boolean isInRow( int rowIndex, int value )
    {
        // Creates the variables needed for this method
        int colIndex;
        
        // Indexes through the entire column
        for (colIndex = 0; colIndex < GRID_SIDE; colIndex++)
        {
            // Checks for a matching value in the row
            if ((sudokuArray[rowIndex][colIndex]).value == value)
            {
                // Returns true if the value is already used
                return true;
            }
        }
        
        // Returns false if the value is never found
        return false;
    }
    
    /**
     * Checks for conflict of value in sub grid
     * <p>
     * Note: Must find upper left corner of sub grid from the row and 
     * column indices, then search the sub grid
     * 
     * @param rowLocIndex - integer row index of test item
     * @param colLocIndex - integer column index of test item
     * @param value - integer value to be tested
     * @return - boolean result of test
     */
    private boolean isInSubGrid( int rowLocIndex, int colLocIndex, int value )
    {
        // Creates the variables needed for this method
        int rowStrt;
        int colStrt;
        int rowDex;
        int colDex;
        
        // Finds the start of the row and column
        rowStrt = rowLocIndex - (rowLocIndex % SUB_GRID_SIDE);
        colStrt = colLocIndex - (colLocIndex % SUB_GRID_SIDE);
        
        // For loop that indexes sub grid rows
        for (rowDex = rowStrt; rowDex < (rowStrt + SUB_GRID_SIDE); rowDex++)
        {
            // For loop that indexes sub grid columns
            for (colDex = colStrt; colDex < (colStrt + SUB_GRID_SIDE); colDex++)
            {
                // Checks if the value at the index is matches the value
                if ( sudokuArray[rowDex][colDex].value == value)
                {
                    // Returns true if the value is found
                    return true;
                }
            }
        }
        
        // Returns false if the value was not found in the sub array
        return false;
    }
    
    /**
     * Randomly removes the specified number of items from the Sudoku array 
     * for preparing the game
     * 
     * @param numbersToBeRemoved - integer number of elements to be cleared
     */
    private void removeNumbers( int numbersToBeRemoved )
    {
        // Creates the variables needed for this method
        int removed;
        int randRow;
        int randCol;
        
        // For loop that removes item and loops the times needed
        for (removed = 0; removed < numbersToBeRemoved; removed++)
        {
            // Creates the random row and column indexes
            randRow = genRandSudokuValue() - 1;
            randCol = genRandSudokuValue() - 1;
            
            // Checks if the element has a value to be removed
            if ( sudokuArray[randRow][randCol].value != 0 )
            {
                // Sets the value at the element to 0 
                sudokuArray[randRow][randCol].value = 0;
            }
            else
            {
                // Makes the loop run another time should nothing be removed
                numbersToBeRemoved++;
            }
        }
    }
    
    /**
     * Sets all three diagonal sub grids in preparation for setting other 
     * values
     * <p>
     * Note: Calls setInitialSubGrid for each grid to be set up
     */
    private void setDiagonalSubGrids()
    {
        // Declares the variables needed for this method
        int rowDex;
        int colDex;
        int subArrIter;
        final int SUB_ARRAY_AMOUNT = 2;
        
        // Initializes the row and column indexes to 0
        rowDex = 0;
        colDex = 0;
        
        for (subArrIter = 0; subArrIter < SUB_ARRAY_AMOUNT; subArrIter++)
        {
            // Sets the current sub grid
            setInitialSubGrid(rowDex, colDex);
            
            // Moves the indexes to the next sub array
            rowDex = rowDex + SUB_GRID_SIDE;
            colDex = colDex + SUB_GRID_SIDE;
        }
        
        // Sets the final sub grid
        setInitialSubGrid(rowDex, colDex);
    }
    
    /**
     * Sets one sub grid with non-repeating values
     * 
     * @param startRow - integer row of upper left corner of sub grid to set up
     * @param startCol - integer column of upper left corner of sub grid to 
     * set up
     */
    private void setInitialSubGrid( int startRow, int startCol )
    {
        // Declares the variables needed for this method
        int rowDex;
        int colDex;
        int value;
        
        // Loops through the rows of the sub array
        for (rowDex = startRow; rowDex < startRow + SUB_GRID_SIDE; rowDex++)
        {
            // Loops through the columns of the sub array
            for (colDex = startCol; colDex < startCol + SUB_GRID_SIDE; colDex++)
            {
                // Sets the fixedCell field to true 
                (sudokuArray[rowDex][colDex]).fixedCell = true;
                
                // Generates a random first attempt
                value = genRandSudokuValue();
                
                // If the value doesn't work it re-generates a value
                while(isInSubGrid(rowDex, colDex, value))
                {
                    // Regenerates the value 
                    value = genRandSudokuValue();
                }
                
                // Sets the value once it finds a valid one
                (sudokuArray[rowDex][colDex]).value = value;
            }
        }
    }
}
