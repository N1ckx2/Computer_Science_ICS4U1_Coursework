import java.util.*;

public class p84ex5
{
  public static void main (String[] args)
  {
    Scanner scan = new Scanner(System.in);
    double a = scan.nextDouble();
    double b = scan.nextDouble();
    scan.close();
    
    if (a == 0)
    {
      if (b == 0)
        System.out.println("indeterminate");
      else
        System.out.println("undefined");
    } else
    {
      double put = (-b/a);
      System.out.printf("%.2f", put);
                         }
  }
}