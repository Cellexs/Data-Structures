package p5_package;

public class StudentListClass_Main
   {

   public static void main(String[] args)
      {

///////////////// TEST ITERATOR /////////////////////////////////////////////////////////////

       StudentIteratorClass sic = new StudentIteratorClass( 25 );
       StudentClass s1 = new StudentClass( "Johnson, Robert", 603667, 'M', 2.844077875 );
       StudentClass s2 = new StudentClass( "Elliott, Cayley", 135658, 'F', 2.978848017 );
       StudentClass s3 = new StudentClass( "Reyes, Connor", 191261, 'M', 3.295578577 );
       StudentClass s4 = new StudentClass( "Rodriguez, Susan", 154838, 'F', 2.063213296 );
       StudentClass s5 = new StudentClass( "Penn, Frederick",  19367, 'M', 2.828974752 );
       StudentClass s6 = new StudentClass( "Deangelis, Shawna", 764050, 'F', 2.203316877 );

       sic.insertAfterCurrent( s1 );
       sic.insertAfterCurrent( s2 );
       sic.insertAfterCurrent( s3 );
       sic.runDiagnosticDisplay();
       System.out.println();

       sic.insertBeforeCurrent( s4 );
       sic.insertBeforeCurrent( s5 );
       sic.insertBeforeCurrent( s6 );
       sic.runDiagnosticDisplay();
       System.out.println();
       
       sic.clear();
       sic.insertBeforeCurrent( s5 );
       sic.insertBeforeCurrent( s6 );
       sic.runDiagnosticDisplay();
       System.out.println();
       
       sic.removeAtCurrent();
       sic.removeAtCurrent();
       sic.runDiagnosticDisplay();
       System.out.println();
       
       sic.insertBeforeCurrent( s5 );
       sic.insertBeforeCurrent( s6 );
       sic.runDiagnosticDisplay();
       System.out.println();

/*
       StudentQueueClass sqc = new StudentQueueClass( 25 );
       StudentClass s1 = new StudentClass( "Johnson, Robert", 603667, 'M', 2.844077875 );
       StudentClass s2 = new StudentClass( "Elliott, Cayley", 135658, 'F', 2.978848017 );
       StudentClass s3 = new StudentClass( "Reyes, Connor", 191261, 'M', 3.295578577 );
       StudentClass s4 = new StudentClass( "Rodriguez, Susan", 154838, 'F', 2.063213296 );
       StudentClass s5 = new StudentClass( "Penn, Frederick",  19367, 'M', 2.828974752 );
       StudentClass s6 = new StudentClass( "Deangelis, Shawna", 764050, 'F', 2.203316877 );
       StudentClass s7 = new StudentClass( "Shafer, Tristan", 693686, 'F', 3.180700609 );

       sqc.enqueue( s1 );
       sqc.enqueue( s2 );
       sqc.enqueue( s3 ); 
       
       sqc.displayQueue();
       
       sqc.enqueue( s4 );
       sqc.enqueue( s5 );
       sqc.enqueue( s6 ); 
       
       System.out.println( "Dequeued: " + sqc.dequeue().toString() );
       System.out.println( "Dequeued: " + sqc.dequeue().toString() );
       System.out.println( "Dequeued: " + sqc.dequeue().toString() );
       System.out.println( "Dequeued: " + sqc.dequeue().toString() );
       
       sqc.displayQueue();

       sqc.clear();
       
       sqc.enqueue( s5 );
       sqc.enqueue( s6 );
       sqc.enqueue( s7 ); 
       
       sqc.displayQueue();
*/
/*
       StudentListClass slc = new StudentListClass( 25 );
       StudentClass s1 = new StudentClass( "Johnson, Robert", 603667, 'M', 2.844077875 );
       StudentClass s2 = new StudentClass( "Elliott, Cayley", 135658, 'F', 2.978848017 );
       StudentClass s3 = new StudentClass( "Reyes, Connor", 191261, 'M', 3.295578577 );
       StudentClass s4 = new StudentClass( "Rodriguez, Susan", 154838, 'F', 2.063213296 );
       StudentClass s5 = new StudentClass( "Penn, Frederick",  19367, 'M', 2.828974752 );
       StudentClass s6 = new StudentClass( "Deangelis, Shawna", 764050, 'F', 2.203316877 );
       StudentClass s7 = new StudentClass( "Shafer, Tristan", 693686, 'F', 3.180700609 );
       StudentClass s8 = new StudentClass( "Ruan, Francisco", 587182, 'M', 3.726603614 );
       StudentClass s9 = new StudentClass( "Werner, Riley", 444253, 'F', 2.34553428 );


       slc.appendDataAtEnd( s1 );
       slc.appendDataAtEnd( s2 );
       slc.appendDataAtEnd( s3 );

       slc.displayList();
       
       int num = slc.findStudentNumber( s2 );
       
       System.out.println( "Number is: " + num );
       
       System.out.println( "Size: " + slc.getCurrentSize() );
       
       System.out.println();
       
       slc.insertDataAtBeginning( s4 );
       slc.insertDataAtBeginning( s5 );
       slc.insertDataAtBeginning( s6 );
       
       slc.displayList();
       
       System.out.println( "Nth Student: " + slc.getNthStudent( 5 ).toString() );
       
       System.out.println();
       
       slc.insertDataAtNthPosition( 1, s7 );
       slc.insertDataAtNthPosition( 8, s8 );
       slc.insertDataAtNthPosition( 4, s9 );
       
       slc.displayList();
       
       System.out.println( "Removed: " + slc.removeNthStudent( 7 ).toString() );
       
       System.out.println();
*/
      }
   }
//        StudentClass s10 = new StudentClass( "Davis, Glen-Andrew", 488133, 'M', 3.937722411 );