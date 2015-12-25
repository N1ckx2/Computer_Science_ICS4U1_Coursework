import java.util.Scanner;

public class wc151j1
{
  public static void main (String[] args)
  {
    Scanner scan = new Scanner(System.in);
    String colours = scan.next() + scan.next();
    
    if (colours.indexOf('W') == -1)
      System.out.print ("WHITE");
    else if (colours.indexOf('R') == -1)
      System.out.print ("RED");
    else
      System.out.print ("BLUE");
    scan.close();
  }
}