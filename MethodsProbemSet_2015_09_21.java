/* Nicholas Vadivelu
 * 21 September 2015
 * Methods Problem Set
 * ICS4U1-04
 */
import java.util.Scanner;
import java.lang.Math.*;

public class MethodsProbemSet_2015_09_21
{
  private static Scanner scan;
  
  /**
   * Displays the title with a nice box
   * @param text is a String that contains the title
   **/
  private static void title (String text)
  {
    //Outputs top border
    for (int i = 0; i < text.length()+2; i++)
      System.out.print("-");
    System.out.print("\n|" + text + "|\n"); //outputs title
    //Outputs bottom border
    for (int i = 0; i < text.length()+2; i++)
      System.out.print("-");
    System.out.println("\n");
  }
  
  /**
   * Accepts two integers, then returns a random in that range(inclusive)
   * @param low is the integer lower bound of the random integer
   * @param high is the integer higher bound of the random integer
   * @return a random integer between low and high (inclusive)
   **/
  private static int getRandom (int low, int high)
  {
    //declare variables
    int random = 0;
    
    //calculate random number
    random = (int) (Math.random()*(high-low +1) + low);
    
    //return random number
    return random;
  }
  
  /**
   * Accepts two characters (high and low), and forces user to enter character in that range (inclusive). This character is then returned
   * @param low is the lower bound for the character that the user can enter
   * @param high is the upper bound for the character that the user can enter
   * @return is the character that the user entered in the range
   **/
  private static char readChar (char low, char high)
  {
    //declare variables
    char userInput;
    
    //Recieve user input
    do
    {
      userInput = scan.next().charAt(0);
      
      //checks to ensure character is within range
      if (userInput < low || userInput > high)
        System.out.print("Error " + low + "-" + high + " only: ");
    }while (userInput < low || userInput > high);
    
    return userInput;
  }
  
  /**
   * Accepts a string of acceptable characters, and forces user to enter character in that range (inclusive). This character is then returned
   * @param acceptable contains the characters that are acceptable for input by the user
   * @return is the character that the user entered in the range
   **/
  private static char readChar (String acceptable)
  {
    //declare variables
    char userInput;
    int isInRange = -1;
    
    //Recieves user input
    do
    {
      userInput = scan.next().charAt(0);
      
      //Checks if the character is in the characters
      isInRange = acceptable.indexOf(userInput);
      if (isInRange == -1)
        System.out.print("Error " + acceptable + " only: ");
    }while (isInRange == -1);
    
    return userInput;
  }
  
  /**
   * Accepts two integers and returns least common multiple
   * @param a is the first integer
   * @param b is the second integer
   * @return is the lowest common multiple of both a and b
   **/
  private static int lcm (int a, int b)
  {
    //declare variables
    int lcm = 1;
    boolean isLCM = false;
    
    //find lcm
    if (a >= 0 && b >= 0)
    {
      while (lcm%a != 0 || lcm%b != 0)
      {
        lcm++;
      }
    }
    else
      lcm = 0;
    //return lcm
    return lcm;
  }
  
  public static void main (String[] args)
  {
    //declare variables
    scan = new Scanner(System.in); //initializes scanner
    int pairs, num1, num2, lcm;
    char goAgain = 'y';
    
    do
    {
      //Prompt user for input
      title("The LCM Machine");
      System.out.print("How many pairs to process (1-9)?");
      pairs = (int)(readChar('1', '9')-48);
      System.out.println("\nNumbers  LCM");
      
      for (int i = 0; i < pairs; i++)
      {
        //Calculate random numbers and lcm
        num1 = getRandom(1, 100);
        num2 = getRandom(1, 100);
        lcm = lcm(num1, num2);
        
        //output results
        System.out.format("%3d %3d %4d%n", num1, num2, lcm);
      }
      
      //Ask user if they want to go again
      System.out.print("\nWant to go again? ");
      goAgain = readChar("yYnN");
      
      //clearing screen
      for (int i = 0; i < 50; i++)
      {
        System.out.println("\n");
      }
    } while (goAgain == 'y' || goAgain == 'Y');
    
    scan.close();
  }
}