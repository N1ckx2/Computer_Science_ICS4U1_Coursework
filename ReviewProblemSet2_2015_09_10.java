/* Nicholas Vadivelu
 * 10 September 2015
 * Review Problem Set 2
 * ICS4U1-04
 */
import java.util.Scanner;

public class ReviewProblemSet2_2015_09_10
{
  private static Scanner scan;
  
  private static void arithmetic2()
  {
    //Variable declaration
    int operand1, operand2, add, subtract, multiply;
    double divide = 0;
    
    //Prompts user for 2 operands
    System.out.print("Operand #1: ");
    operand1 = scan.nextInt();
    System.out.print("Operand #2: ");
    operand2 = scan.nextInt();
    
    //Calculates addition, subtraction, multiplication, division
    add = operand1 + operand2;
    subtract = operand1 - operand2;
    multiply = operand1 * operand2; 
    if (operand2 != 0)
      divide = operand1 / (double) operand2;
    
    
    //Dispays addition, subtraction, multiplication, division
    System.out.println("\n" + operand1 + " + " + operand2 + " = " + add);
    System.out.println(operand1 + " - " + operand2 + " = " + subtract);
    System.out.println(operand1 + " x " + operand2 + " = " + multiply);
    if (operand2 != 0)
      System.out.format("%d / %d = %.3f%n", operand1, operand2, divide);
    else
      System.out.format("%d / %d = undefined%n", operand1, operand2);
  }
  
  private static void equation()
  {
    //Declare variables
    int a, b, minusB;
    double minusBOverA;
    
    //Prompts user and recives input
    System.out.println("Enter values for Ax + B = 0");
    System.out.print("A: ");
    a = scan.nextInt();
    System.out.print("B: ");
    b = scan.nextInt();
    System.out.println();
    
    //Calculate -b and -b/a
    minusB = 0-b;
    minusBOverA = ((0-b)/(double)a);
    
    //Outputs equation solver
    System.out.println(a + "x + " + b + " = 0");
    System.out.println(a + "x = " + minusB);
    System.out.println("x = " + minusB + "/" + a);
    if (a != 0)
      System.out.format("x = %.3f%n", minusBOverA);
    else 
      System.out.format("x = undefined%n");
  }
  
  private static void paycheque()
  {
    //declare variables
    int hours;
    double wage, overPay = 0, regPay, totalPay;
    
    //Prompts user and recives input
    System.out.print("Enter an hourly wage: $");
    wage = scan.nextDouble();
    System.out.print("Enter the number of hours worked in one week: ");
    hours = scan.nextInt();
    System.out.println();
    
    //Calculates pay
    if (hours <= 40)
      regPay = wage*hours;
    else
    {
      regPay = wage*40;
      overPay = wage*(hours-40)*1.5;
    }
    totalPay = regPay + overPay;
    
    //Makes formatting beautiful
    int formatting = Integer.toString((int)(regPay+overPay)).length() +4;
    
    //Ouputs pay for thw eek
    System.out.format("Regular Pay:  $%" + formatting + ".2f%n", regPay);
    System.out.format("Overtime Pay: $%" + formatting +".2f%n", overPay);
    System.out.format("Total Pay:    $%" + formatting + ".2f%n", totalPay);
  }
  
  private static void menu ()
  {
    int n = -1; //declares integer used in menu
    while (n != 0) //when user presses 0, the loop will exit
    {
      //Prompts the user and receives input
      System.out.println("Review Problem Set 2\nby Nicholas Vadivelu\n");
      System.out.println("Which program do you want to use? (input an integer)");
      System.out.println("1 - Modified Arithmetic\n2 - Equation\n3 - Paycheque\n0 - Exit");
      n = scan.nextInt();
      
      //Clears screen
      for (int i = 0; i < 50; i++)
        System.out.println("\n");
      
      //Executes function depending on user's choice
      if (n == 1)
        arithmetic2();
      else if (n == 2)
        equation();
      else if (n == 3)
        paycheque();
      
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