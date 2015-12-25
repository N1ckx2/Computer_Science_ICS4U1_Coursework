import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class wc151j2
{
  public static void main (String[] args)
  {
    Scanner scan = new Scanner(System.in);
    List<Integer> nums = new ArrayList<Integer>();
    int students = scan.nextInt();
    int[] seniors = new int[scan.nextInt()];
    int temp = 0, index = 0;
    int[] index2 = new int[seniors.length];
    for (int i = 0; i < seniors.length; i++)
    {
      seniors[i] = 0;
      index2[i] = 0;
    }
    
    if (students % seniors.length == 0)
    {
      System.out.println(seniors.length + " group(s) of " + (students/seniors.length));
    } else
    {
        for (int i = 0; i < seniors.length && students > 0; i++)
        {
          seniors[i]++;
          students--;
          if (i == seniors.length-1)
            i = -1;
        }
      nums.add(1);
      index2[0] = seniors[0];
      //System.out.println(seniors[0]);
      for (int i = 1; i < seniors.length; i++)
      {
        //System.out.println(seniors[i]);
        if (seniors[i] == index2[index])
        {
          temp = nums.get(index) + 1;
          nums.set(index, temp);
        }
        else
        {
          index++;
          nums.add(1);
          index2[index] = seniors[i];
        }
      }
      for (int i = nums.size()-1; i >= 0; i--)
      {
        System.out.println(nums.get(i) + " group(s) of " + index2[i]);
      }
    }
    scan.close();
  }
}