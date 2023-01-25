package p9_package;

public class ArrayHeapClassMain
   {
    public static void main(String[] args)
       {
        ArrayHeapClass ahc = new ArrayHeapClass();
        int[] loadArray;
        int index, removedItem, numItems = 25;
        boolean removalDisplayFlag = false;
        
        ahc.setDisplayFlag( true );
       
       System.out.println( "Adding Items to heap");
        loadArray = setUniqueRandsInArray( numItems, 10, 99 );
        
        for( index = 0; index < numItems; index++ )
           {
            ahc.addItem( loadArray[ index ] );           
           }
        
        ahc.setDisplayFlag( true );
        
        System.out.println( "\nRemoving Items from heap");

        while( !ahc.isEmpty() )
           {
            removedItem = ahc.removeItem();
           
            System.out.println( removedItem );
           }
           
       }
   
    public static int[] setUniqueRandsInArray( int itemCount, 
                                                       int lowVal, int highVal )
       {
        int[] tempArray = new int[ itemCount ];
        int index, tempVal = 0;
        
        System.out.print( "Loading: " );
        
        for( index = 0; index < itemCount; index++ )
           {
            do
               {
                tempVal = getRandBetween( lowVal, highVal );
               }
            while( isInArray( tempArray, tempVal, itemCount ) );

            System.out.print( tempVal + ", " );
            
            tempArray[ index ] = tempVal;
           }        
        
        System.out.println();
        
        return tempArray;
       }

    public static int getRandBetween( int lowVal, int highVal )
       {
        int range = highVal - lowVal + 1;
        
        return (int)( Math.random() * range + 1 ) + lowVal;
       }
    
    public static boolean isInArray( int[] array, int value, int size )
       {
        int index;
        
        for( index = 0; index < size; index++ )
           {
            if( array[ index ] == value )
               {
                return true;
               }
           }
        
        return false;
       }
   }