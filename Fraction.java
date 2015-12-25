// Nicholas Vadivelu

public class Fraction
{
  int num, denom;
  public Fraction (int n, int d)
  {
    num = n;
    denom = d;
  }
  
  public String toString()
  {
    String returner = "";
    if (num == 0)
      returner= "0";
    else if (denom !=0)
    {
      if (num%denom == 0)
        returner = Integer.toString(num/denom);
    }
    else
      returner= num + "/" +denom;
    return returner;
  }
  
  public int compareTo
}