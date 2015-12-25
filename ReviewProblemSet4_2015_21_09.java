/* Nicholas Vadivelu
 * 21 September 2015
 * Problem Set 4
 * ICS4U1-04
 */
import java.util.Scanner;

public class ReviewProblemSet4_2015_21_09
{
  private static Scanner scan;
  
  private static void invest2()
  {
    //declare variables
    double monthInvest, annualROR, endingBalance = 0, startBalance = 0, interestEarned = 0;
    int targetValue, numMonths = 0;
    
    //Prompt user for input
    System.out.print("Monthly Deposit          : ");
    monthInvest = scan.nextDouble();
    System.out.print("Annual Interest Rate (%) : ");
    annualROR = scan.nextDouble() * 0.01;
    System.out.print("Target Value             : ");
    targetValue = scan.nextInt();
    
    //Calculate ending balance and Output Results
    System.out.println("\nStarting  Interest  Monthly  Ending");
    System.out.println("Balance    Earned   Deposit  Balance");
    System.out.println("--------  --------  -------  -------"); 
    while (endingBalance < targetValue)
    {
      //calculate ending balance and interestEarned
      interestEarned = startBalance*annualROR/12;
      endingBalance = endingBalance + interestEarned + monthInvest;
      
      //output results
      System.out.format("%6.2f%9.2f%11.2f%9.2f%n", startBalance, interestEarned, monthInvest, endingBalance);
      
      startBalance = endingBalance;
      numMonths++;
    }
    System.out.println("\nIt took " + numMonths + " months to reach your target");
  }
  
  private static void bankAccount()
  {
    //declares variables
    double amount, deposits = 0, withdrawals;
    int counter = 1;
    
    //Prompt user for input
    System.out.print("Starting Balance: ");
    amount = scan.nextDouble();
    do
    {
      //prompting for more input
      System.out.print("\nMonth #" + counter + "    Deposits: ");
      deposits = scan.nextDouble();
      if (deposits == -1)
        break;
      System.out.print("            Withdrawals: ");
      withdrawals = scan.nextDouble();
      
      //calculate balance
      amount = amount + deposits - withdrawals;
      counter++;
    }while (deposits != -1 && amount >= 0);
    
    //Ouput result
    System.out.format("%nYou have $%.2f left.%n", amount);
  }
  
  private static void hiLo()
  {
    //declare variables
    int random1 = (int) (Math.random()*100 + 1), random2, points = -1;
    char guess, correctAns = 'h';
    
    do
    {
      //Promp user for input
      System.out.print("The number is " + random1 + ". Next one? (h/l)  ");
      guess = scan.next().charAt(0);
      
      //Generate random number and accumulate points
      random2 = (int) (Math.random()*100 + 1);
      if (random2 > random1)
        correctAns = 'h';
      else if (random1 > random2)
        correctAns = 'l';
      random1 = random2;
      points++;
      
    } while (guess == correctAns);
    
    //Output number of points
    System.out.println("The number is " + random1 + ". You scored " + points + " points.");
  }
  
  private static void menu ()
  {
    int n = -1; //declares integer used in menu
    while (n != 0) //when user presses 0, the loop will exit
    {
      //Prompts the user and receives input
      System.out.println("Review Problem Set 4\nby Nicholas Vadivelu\n");
      System.out.println("Which program do you want to use? (input an integer)");
      System.out.println("1 - Invest2\n2 - BankAccount\n3 - HiLo\n0 - Exit");
      n = scan.nextInt();
      
      //Clears screen
      for (int i = 0; i < 50; i++)
        System.out.println("\n");
      
      //Executes function depending on user's choice
      if (n == 1)
        invest2();
      else if (n == 2)
        bankAccount();
      else if (n == 3)
        hiLo();
      
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