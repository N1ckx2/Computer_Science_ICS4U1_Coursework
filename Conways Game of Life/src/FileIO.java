// The "FileIO" class.
import java.io.*;
import java.util.Scanner;

public class FileIO
{
    static Scanner scan;

    public static void main (String[] args) throws IOException
    {
        scan = new Scanner(System.in);

        System.out.print ("File name to view: ");
        String fname = scan.nextLine ();

        FileReader fr = new FileReader (fname);
        BufferedReader filein = new BufferedReader (fr);

        String line;
        while ((line = filein.readLine ()) != null) // file has not ended
            System.out.println (line); // display text
        filein.close (); // close file

        System.out.print ("File name to create: ");
        fname = scan.nextLine ();

        FileWriter fw = new FileWriter (fname);
        PrintWriter fileout = new PrintWriter (fw);


        for (int x = 1 ; x <= 100 ; x++) // save first 1000 integers
            fileout.println (x); // write a value to file
        fileout.close (); // close file
        scan.close();

    } // main method
} // FileIO class
