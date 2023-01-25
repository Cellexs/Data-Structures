package p6_package;

public class StudentLinkedListClass_Main
   {

   public static void main(String[] args)
      {

///////////////// TEST ITERATOR /////////////////////////////////////////////////////////////
/*
       StudentLinkedIteratorClass slic = new StudentLinkedIteratorClass();
       StudentClass s1 = new StudentClass( "Johnson, Robert", 603667, 'M', 2.844077875 );
       StudentClass s2 = new StudentClass( "Elliott, Cayley", 135658, 'F', 2.978848017 );
       StudentClass s3 = new StudentClass( "Reyes, Connor", 191261, 'M', 3.295578577 );
       StudentClass s4 = new StudentClass( "Rodriguez, Susan", 154838, 'F', 2.063213296 );
       StudentClass s5 = new StudentClass( "Penn, Frederick",  19367, 'M', 2.828974752 );
       StudentClass s6 = new StudentClass( "Deangelis, Shawna", 764050, 'F', 2.203316877 );
       //StudentClass s7 = new StudentClass( "Shafer, Tristan", 693686, 'F', 3.180700609 );
       //StudentClass s8 = new StudentClass( "Ruan, Francisco", 587182, 'M', 3.726603614 );
       StudentClass s9 = new StudentClass( "Werner, Riley", 444253, 'F', 2.34553428 );
       //StudentClass s10 = new StudentClass( "Davis, Glen-Andrew", 488133, 'M', 3.937722411 );

       slic.insertAfterCurrent( s1 );
       slic.insertAfterCurrent( s2 );
       slic.insertAfterCurrent( s3 );
       slic.runDiagnosticDisplay();

       slic.insertBeforeCurrent( s4 );
       slic.runDiagnosticDisplay();
       slic.insertBeforeCurrent( s5 );
       slic.runDiagnosticDisplay();
       slic.insertBeforeCurrent( s6 );
       slic.runDiagnosticDisplay();
       
       slic.replaceAtCurrent( s9 );
       slic.runDiagnosticDisplay();
       
       slic.clear();
       slic.insertBeforeCurrent( s5 );
       slic.insertBeforeCurrent( s6 );
       slic.runDiagnosticDisplay();
       
       slic.removeAtCurrent();
       slic.removeAtCurrent();
       slic.runDiagnosticDisplay();
       
       slic.insertBeforeCurrent( s5 );
       slic.insertBeforeCurrent( s6 );
       slic.runDiagnosticDisplay();
*/
/*
       StudentLinkedStackClass slsc = new StudentLinkedStackClass();
       StudentClass s1 = new StudentClass( "Johnson, Robert", 603667, 'M', 2.844077875 );
       StudentClass s2 = new StudentClass( "Elliott, Cayley", 135658, 'F', 2.978848017 );
       StudentClass s3 = new StudentClass( "Reyes, Connor", 191261, 'M', 3.295578577 );
       StudentClass s4 = new StudentClass( "Rodriguez, Susan", 154838, 'F', 2.063213296 );
       StudentClass s5 = new StudentClass( "Penn, Frederick",  19367, 'M', 2.828974752 );
       StudentClass s6 = new StudentClass( "Deangelis, Shawna", 764050, 'F', 2.203316877 );
       StudentClass s7 = new StudentClass( "Shafer, Tristan", 693686, 'F', 3.180700609 );
       
       System.out.println( "Pushing " + s1.toString() );
       slsc.push( s1 );
       System.out.println( "Pushing " + s2.toString() );
       slsc.push( s2 );
       System.out.println( "Pushing " + s3.toString() );
       slsc.push( s3 ); 
       
       slsc.displayStack();
       
       System.out.println( "Pushing " + s4.toString() );
       slsc.push( s4 );
       System.out.println( "Pushing " + s5.toString() );
       slsc.push( s5 );
       System.out.println( "Pushing " + s6.toString() );
       slsc.push( s6 ); 

       slsc.displayStack();

       System.out.println( "Popped: " + slsc.pop().toString() );
       System.out.println( "Popped: " + slsc.pop().toString() );
       System.out.println( "Popped: " + slsc.pop().toString() );
       System.out.println( "Popped: " + slsc.pop().toString() );
       
       slsc.displayStack();

       slsc.clear();
       
       slsc.push( s5 );
       slsc.push( s6 );
       slsc.push( s7 ); 
       
       slsc.displayStack();
*/

       StudentLinkedListClass sllc = new StudentLinkedListClass();
       StudentClass s1 = new StudentClass( "Johnson, Robert", 603667, 'M', 2.844077875 );
       StudentClass s2 = new StudentClass( "Elliott, Cayley", 135658, 'F', 2.978848017 );
       StudentClass s3 = new StudentClass( "Reyes, Connor", 191261, 'M', 3.295578577 );
       StudentClass s4 = new StudentClass( "Rodriguez, Susan", 154838, 'F', 2.063213296 );
       StudentClass s5 = new StudentClass( "Penn, Frederick",  19367, 'M', 2.828974752 );
       StudentClass s6 = new StudentClass( "Deangelis, Shawna", 764050, 'F', 2.203316877 );
       StudentClass s7 = new StudentClass( "Shafer, Tristan", 693686, 'F', 3.180700609 );
       StudentClass s8 = new StudentClass( "Ruan, Francisco", 587182, 'M', 3.726603614 );
       StudentClass s9 = new StudentClass( "Werner, Riley", 444253, 'F', 2.34553428 );       
       
       sllc.appendDataAtEnd( s1 );
       sllc.appendDataAtEnd( s2 );
       sllc.appendDataAtEnd( s3 );
       
       sllc.displayList();
       
       int num = sllc.findStudentNumber( s2 );
       
       System.out.println( "Number is: " + num );
       
       System.out.println( "Size: " + sllc.getCurrentSize() );
       
       sllc.insertDataAtBeginning( s4 );
       sllc.insertDataAtBeginning( s5 );
       sllc.insertDataAtBeginning( s6 );
       
       sllc.displayList();
       
       System.out.println( "Nth Student: " + sllc.getNthStudent( 5 ).toString() );
       
       sllc.insertDataAtNthPosition( 1, s7 );
       sllc.insertDataAtNthPosition( 8, s8 );
       sllc.insertDataAtNthPosition( 4,  s9 );
     
       sllc.displayList();
       
       System.out.println( "Removed: " + sllc.removeNthStudent( 3 ).toString() );

      }

   }
