//Round 2: Round Numbers
import java.util.Scanner;

public class wc95p2
{
  public static void main (String[] args)
  {
    Scanner scan = new Scanner (System.in);
    int k = scan.nextInt(), one = 0, zero = 0, round = 0, j = 0, n = 0, x = 2, y = 4;
    String binary = "";
    
    for (int i = 1; i <= k; i++)
    {
      for (j = 2; j*2 < i; j*=2)
      {
        n++;
      }
      if (i % 2 == 0)
        zero++;
      else 
        one++;
      for (j = 0, x = 2, y = 4; j < n; j++)
      {
        for (int p = 0; p < 2*n; p++, x*=2, y*=2)
        {
          System.out.println(y);
          if ((i-x)%y == 0)
            
            zero++;
          else
            one++;
        }
      }
      if (zero >= one)
        round++;
    }
    /*
     binary = Integer.toBinaryString(k);
     System.out.println(binary);
     
     for (int i = 1; i <= k; i++)
     {
     binary = Integer.toBinaryString(i);
     for (int j = 0; j < binary.length(); j++)
     {
     if (binary.charAt(j) == '1')
     one++;
     else
     zero++;
     }
     if (zero >= one)
     round++;
     zero = 0;
     one = 0;
     } */
    
    System.out.println("There are " + round + " round numbers less than or equal to " + k + "." );
  }
}