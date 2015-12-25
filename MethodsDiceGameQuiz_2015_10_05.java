import java.util.Scanner;
import java.lang.Math.*;

public class MethodsDiceGameQuiz_2015_10_05
{
  public static void message (String message, int numExclam)
  {
    System.out.print(message);
    for (int i = 0; i < numExclam; i++)
    {
      System.out.print("!");
    }
    System.out.println();
  }
  
  public static String result (int a, int b)
  {
    if (a == b)
      return "Pair";
    else if (Math.max(a, b) == 2*Math.min(a, b))
      return "Double";
    else
      return "Nothing";
  }
  public static void main (String[] args)
  {
    int a, b, rounds;
    Scanner scan = new Scanner (System.in);
    message("The Boring Dice Game", 8);
    System.out.print("\nHow many rounds? ");
    rounds = scan.nextInt();
    for (int i = 0; i < rounds; i++)
    {
      a = (int) (Math.random()*6 + 1);
      b = (int) (Math.random()*6 + 1);
      System.out.println("You rolled " + a + " " + b + " ====> " + result(a, b));
    }
  }
}