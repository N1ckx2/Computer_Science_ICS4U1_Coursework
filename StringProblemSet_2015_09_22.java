/* Nicholas Vadivelu
 * 22 September 2015
 * StringProblemSet
 * ICS4U1-04
 */
import java.util.Scanner;
import java.util.ArrayList;

public class StringProblemSet_2015_09_22
{
  private static Scanner scan;
  
  private static boolean palindrome()
  {
    //declare variables
    String text, lowerCase, converted = "", reversed = ""; 
    boolean isPalindrome = false;
    
    //Prompt user for input
    System.out.print("Enter a string: ");
    scan.nextLine();
    text = scan.nextLine();
    
    //Convert string into a string without spaces, punctuation, etc
    lowerCase = text.toLowerCase();
    for (int i = 0; i < lowerCase.length(); i++)
    {
      if (lowerCase.charAt(i) >= 'a' && lowerCase.charAt(i) <= 'z')
      {
        converted = converted + lowerCase.charAt(i);
        reversed = lowerCase.charAt(i) + reversed;
      }
    }
    
    //Check if string is a palindrome
    for (int i = 0; i < converted.length(); i++)
      isPalindrome = converted.charAt(i) == reversed.charAt(i);
    if (converted.equals(""))
      isPalindrome = true;
    
    //Output whether or not string is palindrome
    if (isPalindrome)
      System.out.println("\"" + text + "\" is a palindrome.");
    else
      System.out.println("\"" + text + "\" is not a palindrome.");
    
    //Return if it's a palindrome
    return isPalindrome;
  }
  
  private static String shiftcode()
  {
    //declare variables
    String text, shifted = "";
    int shift;
    
    //Prompt user for text input and shift
    System.out.print("Enter a string to shift: ");
    scan.nextLine();
    text = scan.nextLine();
    System.out.print("Enter the amount you want to shift by: ");
    shift = scan.nextInt();
    
    //Create shifted text
    for (int i = 0; i < text.length(); i++)
    {
      if (shift >= 0)
      {
        if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z')
          shifted = shifted + (char)((text.charAt(i) - 'A' + shift)%26 + 'A');
        else if (text.charAt(i) >= 'a' && text.charAt(i) <= 'z')
          shifted = shifted + (char)((text.charAt(i) - 'a' + shift)%26 + 'a');
        else
          shifted = shifted + text.charAt(i);
      }
      else 
      {
        if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z')
          shifted = shifted + (char)((text.charAt(i) - 'A' + (26+shift))%26 + 'A');
        else if (text.charAt(i) >= 'a' && text.charAt(i) <= 'z')
          shifted = shifted + (char)((text.charAt(i) - 'a' + (26+shift))%26 + 'a');
        else
          shifted = shifted + text.charAt(i);
      }
    }
    
    //outputs shifted text
    System.out.println("\"" + text + "\" shifted by " + shift + " is \"" + shifted + "\"");
    
    //returns shifted text
    scan.nextLine();
    return shifted;
  }
  
  private static String cryptocode()
  {
    //declare variables
    String text, key = "", encoded = "";
    int position;
    
    //Prompt user for string
    System.out.print("Enter a string to encode: ");
    scan.nextLine();
    text = scan.nextLine();
    
    //Create scrambled alphabet
    for (char i = 'a'; i <= 'z'; i++)
    {
      position = (int) (Math.random()*key.length());
      key = key.substring(0, position) + i + key.substring(position, key.length());
    }
    key = "HOAZXJRTUYBIVEWKLSNCDMFGPQ".toLowerCase();
    //Compute encoded message
    for (int i = 0; i < text.length(); i++)
    {
      if (text.charAt(i) >= 'a' && text.charAt(i) <= 'z')
        encoded = encoded + (char)(key.charAt((text.charAt(i)-'a')));
      else if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z')
        encoded = encoded + (char)(key.charAt((text.charAt(i)-'A'))+('A'-'a'));
      else
        encoded = encoded + text.charAt(i);
    }
    
    //Output results
    System.out.println("\"" + text + "\" with scrambled alphabet \"" + key + "\" becomes \"" + encoded + "\"");
    
    //Return encoded text
    return encoded;
  }
  
  private static void menu ()
  {
    int n = -1; //declares integer used in menu
    while (n != 0) //when user presses 0, the loop will exit
    {
      //Prompts the user and receives input
      System.out.println("String Problem Set\nby Nicholas Vadivelu\n");
      System.out.println("Which program do you want to use? (input an integer)");
      System.out.println("1 - Palindrome\n2 - Shiftcode\n3 - Cryptocode\n0 - Exit");
      n = scan.nextInt();
      
      //Clears screen
      for (int i = 0; i < 50; i++)
        System.out.println("\n");
      
      //Executes function depending on user's choice
      if (n == 1)
        palindrome();
      else if (n == 2)
        shiftcode();
      else if (n == 3)
        cryptocode();
      
      //Pres any key to continue unless user chooses to exit
      if (n!=0)
      {
        System.out.println("\n Press any key to continue...");
        scan.nextLine();
        for (int i = 0; i < 50; i++)
          System.out.println("\n");
      }
    }
  }
  public static void main (String[] args)
  {
    scan = new Scanner(System.in); //initializes scanner
    menu();
    scan.close();
  }
}