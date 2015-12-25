/* Nicholas Vadivelu
 * 08 September 2015
 * Review Problem Set 1
 * ICS4U1-04
 */
import java.util.Scanner;

public class ReviewProblemSet1
{
  private static Scanner scan; //creates a scanner for input
  
  private static void interest()
  {
    //Prompts user and recives input
    System.out.print("Principal ($): "); 
    double principal = scan.nextDouble();
    System.out.print("Interest Rate (%): ");
    double rate = scan.nextDouble();
    
    //Calculates and outputs results
    System.out.format("%nYear 1 Interest = $%.2f%n", (principal*rate/100));
    System.out.format("Year 2 Interest = $%.2f%n", ((principal+(principal*rate/100))*rate/100));
  }
  
  private static void arithmetic()
  {
    //Prompts user and recives input
    System.out.print("Operand #1: ");
    int x = scan.nextInt();
    System.out.print("Operand #2: ");
    int y = scan.nextInt();
    
    //Calculates and outputs results
    System.out.println("\n" + x + " + " + y + " = " + (x+y));
    System.out.println(x + " - " + y + " = " + (x-y));
    System.out.println(x + " x " + y + " = " + (x*y));
    System.out.format("%d / %d = %.3f%n", x, y, (x/(double)y));
  }
  
  private static void purchase()
  {
    //Prompts user and recives input
    System.out.println("Enter item information\n");
    System.out.print("Price: ");
    double price = scan.nextDouble();
    System.out.print("Quantity: ");
    int quantity = scan.nextInt();
    
    //Calculates and outputs results
    System.out.println("--------------------");
    System.out.println("Here is your receipt\n");
    System.out.format("%d x %.2f = %.2f%n%n", quantity, price, (quantity*price));
    System.out.println("Thank you. Come again.");
    
  }
  
  public static void main (String [] args)
  {
    scan = new Scanner(System.in); //initializes scanner
    int n = -1; //declares integer used in menu
    while (n != 0) //when user presses 0, the loop will exit
    {
      //Prompts the user and receives input
      System.out.println("Review Problem Set 1\nby Nicholas Vadivelu\n");
      System.out.println("Which program do you want to use? (input an integer)");
      System.out.println("1 - Interest\n2 - Arithmetic\n3 - Purchase\n0 - Exit");
      n = scan.nextInt();
      
      //Clears screen
      for (int i = 0; i < 50; i++)
        System.out.println("\n");
      
      //Executes function depending on user's choice
      if (n == 1)
        interest();
      else if (n == 2)
        arithmetic();
      else if (n == 3)
        purchase();
      
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
    scan.close();
  }
}
