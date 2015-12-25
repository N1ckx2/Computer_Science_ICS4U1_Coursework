import java.util.*;
import java.lang.Math.*;

public class wc151j4
{
  private static int[] e, t, h, f;
  private static int cost, food, entertain, travel, hotel;
  
  public static int cost (int ty,int hy,int fy)
  {
    if (food - fy > 0)
      food = food - fy;
    else
      food = 0;
    return ty; 
  }
  
  public static void main (String[] args)
  {
    Scanner scan = new Scanner (System.in);
    int smallHotel = 0;
    entertain = scan.nextInt();
    travel = scan.nextInt();
    hotel = scan.nextInt();
    food = scan.nextInt();
    int len = scan.nextInt();
    String binary = "";
    e = new int[len];
    t = new int[e.length];
    h = new int[e.length];
    f = new int[e.length];
    int originFood = food;
    for (int i = 0; i < e.length; i++)
    {
      e[i] = scan.nextInt();
      t[i] = scan.nextInt();
      h[i] = scan.nextInt();
      f[i] = scan.nextInt();
      cost += t[i];
    }
    cost = 0;
    int currCost = 0;
    int totE = 0;
    for (int i = 1; i < len*len; i++)
    {
      binary = Integer.toBinaryString(i);
      //System.out.println(binary);
      currCost = 0;
      smallHotel = hotel;
      for (int j = 0; j < binary.length(); j++)
      {
        if (binary.charAt(binary.length()-j-1) == '1')
        {
          if (h[j] > smallHotel)
            smallHotel = h[j];
          currCost += cost(t[j], h[j], f[j]);
          //System.out.println(currCost);
          totE += e[j];
        }
      }
      currCost += food + smallHotel + travel;
      if ((currCost < cost && totE >= entertain && currCost >= 0) || i == 1)
        cost = currCost;
      totE = 0;
      food = originFood;
    }
    System.out.println(cost);
    scan.close();
  }
}