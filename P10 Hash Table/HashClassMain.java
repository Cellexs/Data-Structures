package p10_package;

public class HashClassMain
   {
      public static void main(String[] args)
         {
          int tableSize = 8;
          
          //SimpleBSTClass testClass = new SimpleBSTClass();
          HashClass hashClassTest = new HashClass( tableSize );
          SimpleStudentClass tempStudent;
          
          System.out.println( "Loading Data from file\n" );
          hashClassTest.loadDataFromFile( "inData.txt" );
          
          // Showing original hash table tree heights
          System.out.print( "Original Hash Table - ");
          hashClassTest.showHashTableStatus();
          
          // Testing copy constructor
          HashClass newHC_Test = new HashClass( hashClassTest );
          System.out.print( "Copied Hash Table - ");                   
          newHC_Test.showHashTableStatus();

          tempStudent = hashClassTest.findItem( 488133 );
          System.out.println("Data found: " + tempStudent.toString() );

          tempStudent = hashClassTest.findItem( 419808 );
          System.out.println("Data found: " + tempStudent.toString() );

          tempStudent = hashClassTest.removeItem( 653875 );
          System.out.println("Data removed: " + tempStudent.toString() );

          tempStudent = hashClassTest.removeItem( 193833 );
          System.out.println("Data removed: " + tempStudent.toString() );

          tempStudent = hashClassTest.removeItem( 729716 );
          System.out.println("Data removed: " + tempStudent.toString() );

          System.out.println( "\nTree Report before clearing.");
          hashClassTest.showHashTableStatus();
          hashClassTest.clearHashTable();
          System.out.println( "Tree Report after clearing.");
          hashClassTest.showHashTableStatus();
                    
          // Adding data
          System.out.println( "\nAdding 11 items");
          hashClassTest.addItem( "Johnson, Robert", 603667, 'M', 2.844077875 );
          hashClassTest.addItem( "Evangelista, Nicholas", 982317, 'M', 3.645258579 );      
          hashClassTest.addItem( "Reyes, Connor", 191261, 'M', 3.295578577 );
          hashClassTest.addItem( "Sanchez, Susan", 154838, 'F', 2.063213296 );
          hashClassTest.addItem( "Deangelis, Shawna", 764050, 'F', 2.203316877 );
          hashClassTest.addItem( "Benavidez, Bradley", 783372, 'M', 3.674861734 );
          hashClassTest.addItem( "Catania, DeMarco", 733002, 'M', 2.577448044 );
          hashClassTest.addItem( "Elliott, Cayley", 135658, 'F', 2.978848017 );
          hashClassTest.addItem( "Gamboa, Wanisha", 362001, 'F', 3.157668898 );
          hashClassTest.addItem( "Andrieu, Andrew", 295673, 'M', 3.800520395 );
          hashClassTest.addItem( "Penn, Frederick", 819367, 'M', 2.828974752 );
          
          // Finding data
          tempStudent = hashClassTest.findItem( 982317 );
          System.out.println("\nData found: " + tempStudent.toString() );
          
          tempStudent = hashClassTest.findItem( 603667 );
          System.out.println("Data found: " + tempStudent.toString() );

          tempStudent = hashClassTest.findItem( 819367 );
          System.out.println("Data found: " + tempStudent.toString() );

          tempStudent = hashClassTest.findItem( 362001 );
          System.out.println("Data found: " + tempStudent.toString() );

          tempStudent = hashClassTest.findItem( 783372 );
          System.out.println("Data found: " + tempStudent.toString() );

          // Removing data
          tempStudent = hashClassTest.removeItem( 295673 );
          System.out.println("Data removed: " + tempStudent.toString() );
          hashClassTest.showHashTableStatus();
                    
          tempStudent = hashClassTest.removeItem( 135658 );
          System.out.println("Data removed: " + tempStudent.toString() );
          hashClassTest.showHashTableStatus();

          tempStudent = hashClassTest.removeItem( 764050 );
          System.out.println("Data removed: " + tempStudent.toString() );
          hashClassTest.showHashTableStatus();

          tempStudent = hashClassTest.removeItem( 733002 );
          System.out.println("Data removed: " + tempStudent.toString() );
          hashClassTest.showHashTableStatus();

          tempStudent = hashClassTest.removeItem( 154838 );
          System.out.println("Data removed: " + tempStudent.toString() );
          hashClassTest.showHashTableStatus();
          
         }

   }
