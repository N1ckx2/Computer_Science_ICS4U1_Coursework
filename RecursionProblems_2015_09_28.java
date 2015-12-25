/* Nicholas Vadivelu
 * 28 September 2015
 * Recursion Problem Set
 * ICS4U1-04
 */
import java.util.Scanner;

public class RecursionProblems_2015_09_28
{
  private static Scanner scan;
  
  private static int gcfRecursion(int a, int b)
  {
    //declare variables
    int gcf = 0;
    
    // calculate GCF
    if (a == b)
      gcf = a;
    else if (a < b)
      gcf = gcfRecursion(b, a);
    else if (a > b)
      gcf = gcfRecursion(b, a-b);
    
    //Return GCF
     return gcf;
  }
  
  private static int gcfLoop(int a, int b)
  {
    //declare variables
    int temp; 
    
    //calculate GCF
    while (a != b)
    {
      if (a < b)
      {
        temp = a;
        a = b;
        b = temp;
      }
      else if (a > b)
      {
        temp = a;
        a = b;
        b = temp-b;
      }
    }
    
    //Return GCF
    return a;
  }
  
  private static int fibonnacciRecursion(int n)
  {
    //declare variables
    int fibonacci = 0;
    
    //calculate fibonacci number
    if (n <= 0)
      fibonacci = 0;
    else if (n == 1)
      fibonacci = 1;
    else
      fibonacci = fibonnacciRecursion(n-2) + fibonnacciRecursion(n-1);
    
    //return fibonacci number
    return fibonacci;
  }
  
  private static int fibonnacciLoop(int n)
  {
    //declare variables
    int fibonacci = 0, a = 0, b = 1;
    
    //calculate fibonacci number
    if (n == 1)
      fibonacci = 1;
    else 
    {
    for (int i = 0; i < n-1; i++)
    {
      fibonacci = a + b;
      a = b;
      b = fibonacci;
    }
    }
    
    //return fibonacci number
    return fibonacci;
  }
  
  private static char[][] floodFill(char[][] map, int row, int col, char fill)
  {
    //Make sure row and column are inside map
    if (row < 0 || col < 0 || row >= map.length || col >= map[0].length) return map;
    
    //make sure this spot hasn't been visited yet
    if (map[row][col] != ' ') return map;
    
    //check to see if it's the right colour
    
    //normally this would fill with a colour, but fill with number for purpose of demo
    map[row][col] = fill;
    fill++;
    
    //animate
    String output = "";
     for (int x = 0; x < map[0].length; x++)
    {
      for (int y = 0; y < map[0].length; y++)
      output = output +  " " + map[x][y] + " ";
                       output = output+"\n";
    }
     System.out.println(output);
     try
     {
       Thread.sleep(500);
     }catch (InterruptedException e)
     {
     }
     
     
    //recursively fill the surrounding pixels (equivalent to a depth first search)
    map = floodFill(map, row - 1, col, fill);
    map = floodFill(map, row + 1, col, fill);
    map = floodFill(map, row , col - 1, fill);
    map = floodFill(map, row, col + 1, fill);
    
     return map;
  }
  
  private static void fill()
  {
    char[][] map = {
      {'|', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '|'},
      {'|', ' ', ' ', ' ', ' ', '|', ' ', '|', ' ', '|', ' ', '|'},
      {'|', '-', '-', '|', ' ', '|', ' ', '-', '-', '-', ' ', '|'},
      {'|', ' ', ' ', '-', '-', '-', ' ', ' ', ' ', ' ', ' ', '|'},
      {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '-', '|', ' ', '|'},
      {'|', ' ', ' ', '-', ' ', ' ', ' ', '-', '|', ' ', ' ', '|'},
      {'|', ' ', '-', '|', ' ', '-', '-', '|', ' ', '|', '-', '|'},
      {'|', '-', '|', ' ', ' ', '|', ' ', '|', ' ', '|', ' ', '|'},
      {'|', ' ', ' ', ' ', ' ', '-', '-', '-', ' ', '|', ' ', '|'},
      {'|', ' ', '-', '-', ' ', ' ', ' ', ' ', ' ', '|', '-', '|'},
      {'|', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
      {'|', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '|'}
    };
    for (int x = 0; x < 12; x++)
    {
      for (int y = 0; y < 12; y++)
      System.out.print(" " + map[x][y] + " ");
                       System.out.println();
    }
    scan.next();
    map = floodFill(map, 1, 6, 'a');
    for (int x = 0; x < 12; x++)
    {
      for (int y = 0; y < 12; y++)
      System.out.print(" " + map[x][y] + " ");
                       System.out.println();
    }
  }
  
  private static void menu ()
  {
    int n = -1; //declares integer used in menu
    while (n != 0) //when user presses 0, the loop will exit
    {
      //Prompts the user and receives input
      System.out.println("Recursion Problem Set\nby Nicholas Vadivelu\n");
      System.out.println("Which program do you want to use? (input an integer)");
      System.out.println("1 - GCF Recursion\n2 - GCF Loop\n3 - Fibonnacci Recursion\n4 - Fibonnacci Loop\n5 - Flood Fill\n0 - Exit");
      n = scan.nextInt();
      
      //Clears screen
      for (int i = 0; i < 50; i++)
        System.out.println("\n");
      
      //Executes function depending on user's choice
      if (n == 1)
      {
        //declare variables
        int a, b;
        
        //prompt user for input
        System.out.print("Enter the first integer (must be greater than or equal to 0): ");
        a = readerInt(1, 2147483647);
        System.out.print("Enter the second integer (must be greater than or equal to 0): ");
        b = readerInt(1, 2147483647);
        
        //calculates GCF and outputs results
        System.out.format("The greatest common factor of %d and %d is %d.%n", a, b, gcfRecursion(a, b));
      }
      else if (n == 2)
      {
        //declare variables
        int a, b;
        
        //prompt user for input
        System.out.print("Enter the first integer (must be greater than 0): ");
        a = readerInt(1, 2147483647);
        System.out.print("Enter the second integer (must be greater than 0): ");
        b = readerInt(1, 2147483647);
        
        //calculates GCF and outputs results
        System.out.format("The greatest common factor of %d and %d is %d.%n", a, b, gcfLoop(a, b));
      }
      else if (n == 3)
      {
        //declare variables
        int z;
        //Prompt user for n
        System.out.print("Which fibonacci number do you want (enter integer over 0) :");
        z = readerInt(1, 46);
        
        //Calculate fibonacci and output
        System.out.format("The %dth fibonacci number is %d.%n", z, fibonnacciRecursion(z));
      }
      else if (n == 4)
      {
        //declare variables
        int z;
        //Prompt user for n
        System.out.print("Which fibonacci number do you want (enter integer over 0) :");
        z = readerInt(1, 46);
        
        //Calculate fibonacci and output
        System.out.format("The %dth fibonacci number is %d.%n", z, fibonnacciLoop(z));
      }
      
      else if (n == 5)
      {
        fill();
      }
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
  
  private static int readerInt (int low, int high)
  {
    //declare variables
    int userInput;
    
    //Recieve user input
    do
    {
      userInput = scan.nextInt();
      
      //checks to ensure character is within range
      if (userInput < low || userInput > high)
        System.out.print("Error " + low + "-" + high + " only: ");
    } while (userInput < low || userInput > high);
    
    return userInput;
  }
    
  public static void main (String[] args)
  {
    scan = new Scanner(System.in); //initializes scanner
    menu();
    scan.close();
  }
}