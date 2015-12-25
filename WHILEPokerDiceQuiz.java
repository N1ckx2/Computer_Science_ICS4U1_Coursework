//Nicholas Vadivelu
//21 September 2015
//WHILE Poker Dice quiz
import java.util.Scanner;
import java.lang.Math.*;

public class WHILEPokerDiceQuiz
{
  public static void main (String[] args)
  {
    Scanner scan = new Scanner(System.in);
    int a, b, pairs=0, runs=0;
    char choice = 'y';
    System.out.println("Mini-Poker Dice\n");
    while (choice == 'y')
    {
      a = (int) (Math.random()*6+1);
      b = (int) (Math.random()*6+1);
      
      System.out.print(a + " " + b + " ");
      
      if (a == b)
      {
        System.out.print("Pair");
        pairs++;
      }
      else if (Math.abs(a-b) == 1)
      {
        System.out.print("Run ");
        runs++;
      }
      else
        System.out.print("Dud ");
      
      System.out.print("    Play again? ");
      choice = scan.next().charAt(0);
    }
    
    System.out.println("\nPairs: " + pairs + "     Runs: " + runs);
  }
}