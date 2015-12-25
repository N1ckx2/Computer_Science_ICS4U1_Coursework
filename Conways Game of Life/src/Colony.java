import java.awt.*;
import java.lang.Math.*;

public class Colony
{
    private boolean grid[][];
    private int maxRow, maxCol;

    public Colony (double density, int rows, int cols)
    {
        grid = new boolean [rows] [cols];
        maxRow = grid.length;
        maxCol = grid[0].length;
        for (int row = 0 ; row < maxRow ; row++)
            for (int col = 0 ; col < maxCol ; col++)
                grid [row][col] = coinFlip(density);
    }


    public void show (Graphics g)
    {
        for (int row = 0 ; row < maxRow ; row++)
            for (int col = 0 ; col < maxCol ; col++)
            {
                if (grid [row] [col]) // life
                    g.setColor (Color.black);
                else
                    g.setColor (Color.white);
                g.fillRect (col * 4 + 2, row * 4 + 2, 4, 4); // draw life form
            }
    }

    public boolean live (int row, int col) //checks if the cell here will live, die, or be created according to rules
    {
        int startRow = (row == 0 ? row : row - 1);
        int startCol = (col == 0 ? col : col - 1);
        int endRow = (row + 1 == maxRow ? row : row + 1);
        int endCol = (col + 1 == maxCol ? col : col + 1); //these four variables determines the starting and ending position
        int counter = 0;
        // also ensure that when the loop searches, it doesn't go out of the array bounds
        for (int r = startRow; r <= endRow ; r++)
            for (int c = startCol; c <= endCol; c++)
                if ((r != row || c != col) && grid[r][c])
                    counter++;

        if ((counter == 2 && grid[row][col]) || counter == 3) //if 3 surrounding, it will create life
            return true; //if two surrounding, it will maintain life
        else //if less than two, or greater than three, or if value is 2 but no life
            return false;
    }


    public void advance ()
    {
        boolean[][] temp = new boolean[maxRow][maxCol];
        for (int r = 0 ; r < maxRow ; r++)
            for (int c = 0 ; c < maxCol ; c++)
                temp[r][c] = live(r, c);
        grid = temp;
    }

    public void eradicate(int startRow, int startCol, int endRow, int endCol)
    {
        startRow = (startRow/4 > 125 ? 125 : startRow/4);
        endRow = (endRow/4 > 125 ? 125 : endRow/4);
        startCol = (startCol/4 > 125 ? 125 : startCol/4);
        endCol = (endCol/4 > 125 ? 125 : endCol/4);

        for (int r = startRow; r < endRow ; r++)
            for (int c = startCol; c < endCol; c++)
                if (grid[c][r])
                    grid[c][r] = coinFlip(0.15); //the coordinates are flipped on purpose
    }

    public void populate(int startRow, int startCol, int endRow, int endCol)
    {
        startRow = (startRow/4 > 125 ? 125 : startRow/4);
        endRow = (endRow/4 > 125 ? 125 : endRow/4);
        startCol = (startCol/4 > 125 ? 125 : startCol/4);
        endCol = (endCol/4 > 125 ? 125 : endCol/4);

        for (int r = startRow; r < endRow ; r++)
            for (int c = startCol; c < endCol; c++)
                grid[c][r] = coinFlip(0.85); //flipped on purpose
    }

    public void save ()
    {

    }

    public void load()
    {

    }

    public boolean coinFlip(double d) { return Math.random() < d;}
    public boolean coinFlip() {return coinFlip(0.5);}

}
