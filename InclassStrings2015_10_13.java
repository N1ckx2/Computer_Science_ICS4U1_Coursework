/*Nicholas Vadivelu
 * 13 October 2015
 * ICS 4U1
 */

/* For this in-class assignment, you are to complete the program by writing the methods so that
 the program can produce the output shown.
 
 I would suggest that you write near-empty methods first to get the program running, then work
 on the problems themselves.
 
 YOU ARE NOT ALLOWED TO CHANGE THE MAIN PROGRAM (except for commenting out code).
 */
import java.awt.*;
import java.util.Scanner;

public class InclassStrings2015_10_13
{
  //static Console c;
  
  // Insert your methods here
  public static int numCaps (String message)
  {
    //declare variables
    int counter = 0;
    
    //count the number of capitals
    for (int i = 0; i < message.length(); i++)
    {
      if (message.charAt(i) >= 'A' && message.charAt(i) <= 'Z')
        counter++;
    }
    
    //return value
    return counter;
  }
  
  public static String noVowels (String message)
  {
    //declare variables
    String noVowelsMessage = "";
    
    //create new message without vowels
    for (int i = 0; i < message.length(); i++)
    {
      if ("aeiouAEIOU".indexOf(message.charAt(i)) < 0)
        noVowelsMessage = noVowelsMessage + message.charAt(i);
    }
    
    //return num vowels
    return noVowelsMessage;
  }
  
  public static String mirror (String message)
  {
    //declare variables
    String mirrored = "";
    
    //create mirrored message
    for (int i = 0; i < message.length(); i++)
    {
      mirrored = mirrored + message.charAt(message.length()-i-1);
    }
    
    //return mirrored message
    return (message + mirrored);
  }
  
  public static void main (String[] args)
  {
     // c = new Console ();
    Scanner scan = new Scanner(System.in);
    
    System.out.print ("\n\nEnter a multi-word message: ");
    String message = scan.nextLine ();
    
    // numcaps accepts the string and returns the number of capital letters
    System.out.println ("\nThe message has " + numCaps (message) + " capital letters.");
    
    // noVowels accepts the string and returns a version that doesn't have any vowels (caps or small AEIOU)
    System.out.println ("\nThe no vowel version is  " + noVowels (message));
    
    // mirror accepts the string and returns a version with its mirror image attached
    System.out.println ("\nThe mirrored version is " + mirror (message));
  } // main method
} // Inclass2 class

/* Sample run
 * 
 Enter a multi-word message: ThiS is a tEst
 
 The message has 3 capital letter(s).
 
 The no vowel version is  ThS s  Tst
 
 The mirrored version is ThiS is a tEsttsEt a si SihT
 
 */
