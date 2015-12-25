// Steven Jay
// Gradenator 1.0 Program
// September 11, 2015
// ICS 2O3

import hsa.Console; 

public class Gradenator 
{
    static Console c;     

    public static void main (String[] args)  
    {
	c = new Console ();
	
	// Variable Declaration 
	int mark, total; 
	double percent;  

	// Request mark and total
	c.print ("What mark did you get? "); 
	mark = c.readInt (); 
	c.print ("What was it out of? ");
	total = c.readInt ();

	// Calculate percentage
	percent = mark * 100.0 / total; 

	// Display percentage
	c.println (); 
	c.println ("Your mark is " + percent + "%"); 
    }
}
