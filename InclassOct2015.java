/* Nicholas Vadivelu
 * 14 October 2015
 * ICS 4U1
 * In class string assignment
 */


/* For this in-class assignment, you are to complete the program by writing these methods so that
 the program can produce the output shown.
 
 I would suggest that you write near-empty methods with return statements first to get the
 program running, then work on the problems themselves.
 
 1. numwords accepts a string, then returns the number of "words" separated by one or more
 spaces (ex. "Hey,   it's R2D2!"  is 3 words)
 
 2. shuffle accepts a string, then returns the same characters with all the vowels first,
 followed by all the consonants then all the other characters in their original "order".
 (ex. "This is like a test!"  changes to  "iiieaeThsslktst    !"
 
 3. edit accepts a string, then returns it with duplicate characters eliminated from the
 string (ex. "Madam, I'm Adam!"  returns  "Madm, I'A!")
 
 YOU ARE NOT ALLOWED TO CHANGE THE MAIN PROGRAM (except for testing purposes).
 */
import java.util.Scanner;
import java.lang.Math.*;

public class InclassOct2015
{
  static Scanner sc;
  
  public static int numwords (String message)
  {
    int numWords = 1, index = message.indexOf(" ");;
    message = message.trim();
    while (index != -1)
    {
      index = message.indexOf(" ");
      if (index != -1)
        numWords++;
      message = message.substring(index+1);
      message = message.trim();
    }
    
    return numWords;
  }
  
  public static String shuffle (String message)
  {
    String shuffled = "";
    for (int i = 0; i <  message.length(); i++)
    {
      if ("aeiouAEIOU".indexOf(message.charAt(i)) != -1)
        shuffled = shuffled + message.charAt(i);
    }
    for (int i = 0; i <  message.length(); i++)
    {
      if ("qwrtypsdfghjklmnbvcxzQWRTYPLKJHGFDSZXCVBNM".indexOf(message.charAt(i)) != -1)
        shuffled = shuffled + message.charAt(i);
    }
    for (int i = 0; i <  message.length(); i++)
    {
      if ("qwrtypsdfghjklmnbvcxzQWRTYPLKJHGFDSZXCVBNMaeiouAEIOU".indexOf(message.charAt(i)) == -1)
        shuffled = shuffled + message.charAt(i);
    }
    return shuffled;
  }
  
  public static String edit (String message)
  {
    String edited = "";
    
    for (int i = 0; i < message.length(); i++)
    {
      if (message.substring(0, i).indexOf(message.charAt(i)) == -1)
        edited = edited + message.charAt(i);
    }
    return edited;
  }
  
  public static void main (String[] args)
  {
    sc = new Scanner (System.in);
    
    System.out.print ("\n\nEnter a multi-word message: ");
    String message = sc.nextLine ();
    
    System.out.println ("\nThe message has " + numwords (message) + " words.");
    
    System.out.println ("\nThe shuffled version is: " + shuffle (message));
    
    System.out.println ("\nThe edited version is: " + edit (message));
  } // main method
} // InclassOct2015 class

/* Sample run
 * 
 Enter a multi-word message: Too much programming is bad for your teeth!
 
 The message has 8 words.
 
 The shuffled version is: oouoaiiaooueeTmchprgrmmngsbdfryrtth       !
 
 The edited version is: To muchprgainsbdfyte!
 
 */
