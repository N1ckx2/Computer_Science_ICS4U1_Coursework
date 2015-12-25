/* Nicholas Vadivelu
 * 15 October 2015
 * ICS 4U1
 */

/* For this in-class assignment, you are to complete the program by writing these methods so that
 the program can produce the output shown.
 
 I would suggest that you write near-empty methods with return statements first to get the
 program running, then work on the problems themselves.
 
 1. capitalize accepts a string of words, then returns the string with the first letter in each 
 word capitalized.  A word starts after a space (or spaces).
 
 2. shuffle accepts a string, then returns the same characters with all the upper-case letters first,
 followed by all the lower-case letters then all the other characters in their original "order".
 
 3. occur accepts two strings, then returns the number of characters from the second string
 that are in the first string. It is used to find vowels in the example, but may be used 
 for any purpose.
 
 YOU ARE NOT ALLOWED TO CHANGE THE MAIN PROGRAM (except for testing purposes).
 */
import java.util.Scanner;

public class InclassOct2015b
{
  static Scanner sc;
  
  public static String capitalize (String msg)
  {
    boolean spaceBefore = false;
    String capitalized = "";
    if ("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM".indexOf(msg.charAt(0)) != -1)
      capitalized = msg + msg.charAt(0);
    else
      capitalized = 
    for (int i = 0; i < msg.length(); i++)
    {
      if (msg.charAt(i-1) == ' ')
        spaceBefore = true;
      if (msg.
    }
  }
  
  public static String shuffle (String msg)
  {
    
  }
  
  public static int occur (String msg, String vowels)
  {
    
  }
  public static void main (String[] args)
  {
    sc = new Scanner (System.in);
    
    System.out.print ("\n\nEnter a multi-word message: ");
    String message = sc.nextLine ();
    
    System.out.println ("\nThe capitalized version is: " + capitalize (message));
    
    System.out.println ("\nThe shuffled version is: " + shuffle (message));
    
    System.out.println ("\nThe message has " + occur (message,"aeiouAEIOU") + " vowels.");
  } // main method
} // InclassOct2015b class

/* Sample run
 * 
 Enter a multi-word message: Too much programming is bad for your teetH!
 
 The capitalized version is: Too Much Programming Is Bad For Your TeetH!
 
 The shuffled version is: THoomuchprogrammingisbadforyourteet       !       !
 
 The message has 13 vowels.
 
 */
