package p4_package;

public class SudokuGeneratorClassMain 
{

    public static void main(String[] args) 
    {
        boolean showTransactions = true;
        int numberOfEmptyCells = 25;
        
        SudokuGeneratorClass sgc = new SudokuGeneratorClass();
        
        sgc.createSudoku( numberOfEmptyCells, showTransactions);
    }

}
