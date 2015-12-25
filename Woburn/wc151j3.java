import java.util.*;

public class wc151j3
{
  public static void main (String[] args)
  {
    Scanner scan = new Scanner(System.in);
    int duration = 10;
    int[] songLengths = new int[scan.nextInt()];
    for (int i = 0; i < songLengths.length; i++)
    {
      songLengths[i] = scan.nextInt();
    }
    Arrays.sort(songLengths);
    songLengths[songLengths.length-1] = songLengths[songLengths.length-1]*2;
    songLengths[songLengths.length-2] = songLengths[songLengths.length-2]*2;
    
    for (int i = 0; i < songLengths.length; i++)
      duration += songLengths[i];
    
    System.out.print(duration);
    scan.close();
  }
}