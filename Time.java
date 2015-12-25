/* Nicholas Vadivelu
 * 4 November 2015
 * Classes Quiz Practice
 */

public class Time
{
  private int minute, second;
  public Time (int min, int sec)
  {
    minute = min + sec/60;s
      second = sec%60;
  }
  
  public String toString()
  {
    
    if (second < 10)
      return minute + ":0" + second;
    else
      return minute + ":" + second;
    
  }
  
  public void advance()
  {
    second++;
    if (second >= 60)
    {
      minute ++;
      second %= 60;
      
    } 
  }
  
  public static void main (String [] args)
  {
    Time elapsed = new Time (4, 119);
    
   //elapsed.advance();
   elapsed.advance();
    System.out.print("Time is " + elapsed);
  }
}