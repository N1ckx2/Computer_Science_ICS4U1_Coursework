/* Nicholas Vadivelu
 * 14 September 2015
 * Review Problem Set 3
 * ICS4U1-04
 */
import java.util.Scanner;

public class ReviewProblemSet3_2015_09_14
{
  private static Scanner scan;
  
  private static void product()
  {
    //declare variables
    int nums, input, product = 1;
    
    //Prompts user for number of numbers, then numbers
    System.out.print("How many numbers to process? ");
    nums = scan.nextInt();
    System.out.println("Enter " + nums + " positive integers");
    //Prompts for each individual number, and calculates the total product
    for (int i = 1; i <= nums; i++)
    {
      //prompts for number
      System.out.print("#" + i + ": ");
      input = scan.nextInt();
      //calculates product
      if (input <= 0)
      {
        System.out.println(input + " not counted");
      }
      else
        product *= input;
    }
    
    //Outputs products
    System.out.println("\nThe product of the positives is " + product + ".");
  }
  
  private static void factorial()
  {
    //declare variables
    int num, factorial = 1;
    
    //Prompt user for number
    System.out.print("Enter a non-negative integer: ");
    num = scan.nextInt();
    System.out.println();
    if (num > 31)
    {
      System.out.println("Number is too large");
    }
    else
    {
      //Calculate Factorial and output result
      for (int i = num; i > 0; i--)
      {
        //calculate factorial
        factorial *= i;
        
        //output each number
        if (i != 1)
          System.out.print(i + " x ");
        else 
          System.out.print(i);
      }
      
      //Output the factorial
      System.out.println(" = " + factorial);
    }
  }
  
  private static void invest()
  {
    //declare variables
    double monthInvest, annualROR, endingBalance = 0, startBalance = 0, interestEarned = 0;
    int numMonths;
    
    //Prompt user for input
    System.out.print("Monthly Deposit          : ");
    monthInvest = scan.nextDouble();
    System.out.print("Annual Interest Rate (%) : ");
    annualROR = scan.nextDouble() * 0.01;
    System.out.print("Term (Months)            : ");
    numMonths = scan.nextInt();
    
    //Calculate ending balance and Output Results
    System.out.println("\nStarting  Interest  Monthly  Ending");
    System.out.println("Balance    Earned   Deposit  Balance");
    System.out.println("--------  --------  -------  -------"); 
    for (int i = 0; i < numMonths; i++)
    {
      //calculate ending balance and interestEarned
      interestEarned = startBalance*annualROR/12;
      endingBalance = endingBalance + interestEarned + monthInvest;
      
      //output results
      System.out.format("%6.2f%9.2f%11.2f%9.2f%n", startBalance, interestEarned, monthInvest, endingBalance);
      
      startBalance = endingBalance;
    }
    
  }
  private static void menu ()
  {
    int n = -1; //declares integer used in menu
    while (n != 0) //when user presses 0, the loop will exit
    {
      //Prompts the user and receives input
      System.out.println("Review Problem Set 3\nby Nicholas Vadivelu\n");
      System.out.println("Which program do you want to use? (input an integer)");
      System.out.println("1 - Product\n2 - Factorial\n3 - Invest\n0 - Exit");
      n = scan.nextInt();
      
      //Clears screen
      for (int i = 0; i < 50; i++)
        System.out.println("\n");
      
      //Executes function depending on user's choice
      if (n == 1)
        product();
      else if (n == 2)
        factorial();
      else if (n == 3)
        invest();
      
      //Pres any key to continue unless user chooses to exit
      if (n!=0)
      {
        System.out.println("\n Press any key to continue...");
        scan.nextLine();
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