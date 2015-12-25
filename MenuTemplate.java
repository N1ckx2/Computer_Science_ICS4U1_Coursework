/* Nicholas Vadivelu
 * <date>
 * <problem set>
 * ICS4U1-04
 */
import java.util.Scanner;

public class MenuTemplate
{
  private static Scanner scan;
  private static void menu ()
  {
    int n = -1; //declares integer used in menu
    while (n != 0) //when user presses 0, the loop will exit
    {
      //Prompts the user and receives input
      System.out.println("<problemset>\nby Nicholas Vadivelu\n");
      System.out.println("Which program do you want to use? (input an integer)");
      System.out.println("1 - \n2 - \n3 - \n0 - Exit");
      n = scan.nextInt();
      
      //Clears screen
      for (int i = 0; i < 50; i++)
        System.out.println("\n");
      
      //Executes function depending on user's choice
      if (n == 1)
        ();
      else if (n == 2)
        ();
      else if (n == 3)
        ();
      
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