import java.lang.*;
import java.awt.*;
import javax.imageio.*; // allows image loading
import java.io.*; // allows file access
import javax.swing.*;
import java.awt.event.*;  // Needed for ActionListener
import java.lang.Math.*; //need for Math.random();
import java.util.*; //need this for lists
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * This class controls the user's interactions
 * 
 * @author Nicholas Vadivelu, Frederick Ngo, Danyal Abbasi
 * @version 16 December 2015
 */
class GUI extends JFrame implements MouseListener, ActionListener //GUI that will be used for this game
{
    private PresidentCardGame game; //local copy of the card game to refer to
    private Player[] player; //creates a local reference of the player array for easy access
    private JButton b_enterChoice, b_pass; //b_enter is to Play cards; b_pass is to pass on the turn
    private int[] cards; //this is used to store user input when they select their cards
    private boolean didChoose, showing, isBeginning; //didChoose is true when user has selected a card for their turn; showing is true when the table has cards on it; isBeginning is true when the beginning display is up
    private DrawArea table; //this is the jpanel containing all the graphocs
    private Card[] choice; //local copy of the current cards played
    private JLabel info; //information on the bottom jpanel about what is going on in game (used in case graphics error)
    private boolean[] chosen = {false, false, false, false, false, false, false, false, false, false, false, false, false}; //this array is used to control which cards are sticking out and which cards in their initial position

    public boolean isBeginning() { return isBeginning; }; //returns whether or not start screen is up

    public GUI(PresidentCardGame g, boolean sec)
    {
        // 1... Create/initialize components
        isBeginning = !sec; //won't show start screen when second round
        game = g; //a local copy of the game
        player = game.player(); //local copy of the players in game

        didChoose = false; 
        showing = false; //initialize booleans

        cards = new int[1];
        cards[0] = 1;//initializing cards array which wil be changed later

        info = new JLabel(); //information jlabel at the bottom

        b_enterChoice = new JButton("Play");
        b_enterChoice.addActionListener(this);
        b_pass = new JButton("Pass");
        b_pass.addActionListener(this); //the two buttons for user input

        JPanel content = new JPanel ();        // Create a content pane
        content.setLayout (new BorderLayout ()); // Use BorderLayout for panel
        JPanel south = new JPanel (); // where the buttons, etc. will be
        table = new DrawArea (800, 600); // Area for cards to be displayed
        table.addMouseListener(this);

        table.setLayout (new BorderLayout ());

        south.add(info);
        south.add(b_enterChoice);
        south.add(b_pass); //adding elements to bottom bar

        content.add (south, "South"); // Input area
        content.add (table, "North"); // Deck display area
        // 4... Set this window's attributes.
        setContentPane (content);
        pack ();
        setResizable(false); //don't want user to resize or else images will not be aligned
        setTitle ("Deck GUI Lists");
        //setSize (868, 675);
        setSize (858, 665);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo (null); // Center window.
    }

    private void sleep (int time) //this method is for easy access to thread.sleep
    {
        try { Thread.sleep(time); }
        catch (InterruptedException e) { System.out.println("interrupted"); }
    }

    public int[] getChoice() //this method waits until the user selects cards on their turn
    {
        while(!didChoose) //sleeps until the user chooses
            sleep(15);
        didChoose = false;
        return cards; 
    }

    public void showCards (int pos, Card[] choice) //shows the cards that are played
    {
        showing = true;
        this.choice = choice;
        repaint();
    }

    public void clear () { showing = false;} //clears the play area 

    public void info(String text) { info.setText(text); } //allows the PresidentCardGame class to easily display information

    public void alert(String text) { JOptionPane.showMessageDialog (null, text); } //allows main class to easily alert user

    public boolean question (String text)  //used for play again text so user can choose to play again
    {
        int n = JOptionPane.showConfirmDialog(null, text, "Play Again?", JOptionPane.YES_NO_OPTION); 
        return JOptionPane.YES_OPTION == n;
    }

    //-------------------------------Mouse Related Events ---------------------------------------------------------
    public void mouseClicked(MouseEvent e) //checks which card is clicked
    {
        if (isBeginning) //makes the start screen disapear
            isBeginning = false;
        else
        {
            for (int i = 0; i < player[0].hand().size(); i++) //checks which card is being clicked
            {
                if (e.getX() > player[0].hand().get(i).left() && e.getX() < player[0].hand().get(i).right()-(73-20) && e.getY() > player[0].hand().get(i).up() && e.getY() < player[0].hand().get(i).down())
                    chosen[i] = !chosen[i];
                else if (i == player[0].hand().size()-1 && e.getX() > player[0].hand().get(i).left() && e.getX() < player[0].hand().get(i).right() && e.getY() > player[0].hand().get(i).up() && e.getY() < player[0].hand().get(i).down())
                    chosen[i] = !chosen[i];
                repaint();
            }
        }
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}  //these methods are not needed but must be overwritten when implemented

    // ActionListener
    public void actionPerformed (ActionEvent e)
    {
        if (e.getSource() == b_enterChoice) 
        {
            boolean isValid = false;
            java.util.List<Integer> nums = new ArrayList<Integer>();
            for (int i = 0 ; i < chosen.length; i++) //sees which cards were chosen
            {
                if (chosen[i])
                    nums.add(i+1);
                isValid = isValid || chosen[i]; //prevents blank input
            }
            if (isValid) //if the selection was not blank
            {
                cards = buildIntArray(nums); //creates an array from the arraylist
                didChoose = true; //so the getChoice method can continue
                for (int i = 0 ; i < chosen.length; i++) //makes all the selections false again
                    chosen[i] = false;
            }
        } else if (e.getSource() == b_pass) //allow sthe user to pass
        {
            cards = new int[1];
            cards[0] = -1;
            didChoose = true;
            for (int i = 0 ; i < chosen.length; i++)
                chosen[i] = false;
        }
    }

    private int[] buildIntArray(java.util.List<Integer> integers) //creates an int array from an integer arraylist
    {
        int[] ints = new int[integers.size()];
        int i = 0;
        for (Integer n : integers)
            ints[i++] = n;
        return ints;
    }

    class DrawArea extends JPanel  //where all the graphics are 
    {
        private Image background, openning, layout; //all the required images
        private Image[] won, passed, turn; 
        public DrawArea (int width, int height) //constructor
        {
            this.setPreferredSize (new Dimension (width, height)); 
            won = new Image[4];
            passed = new Image[4];
            turn = new Image[4];
            try //imports all the images
            { 
                background = ImageIO.read (new File ("images\\bg.jpg"));
                openning = ImageIO.read (new File ("images\\opening.jpg"));
                layout = ImageIO.read (new File ("images\\layout.png"));
                for (int i = 0 ; i < 4; i++)
                {
                    won[i] = ImageIO.read (new File ("images\\signals\\" + i + "won.png"));
                    passed[i] = ImageIO.read (new File ("images\\signals\\" + i + "passed.png"));
                    turn[i] = ImageIO.read (new File ("images\\signals\\" + i + "turn.png"));
                }
            } // load file into Image object
            catch (IOException e) { System.out.println ("GUI Image not found"); }
        } // size 

        public void paintComponent (Graphics g)
        {

            if (isBeginning) //start screen
                g.drawImage(openning, 0, 0, null);
            else 
            {
                g.drawImage(background, 0, 0, null); //background
                g.drawImage(layout, 0, 0, null);//player cards
                for (int i = 0 ; i < player.length; i ++) //shows all of the cards and status
                {
                    player[i].hand().show(g, i, chosen);
                    //the following statements show if the player is passed, has won, or is currently playing
                    if (player[i].win())
                        g.drawImage(won[i], 0, 0, null);
                    else if (player[i].pass())
                        g.drawImage(passed[i], 0, 0, null);
                    else if (player[i].turn())
                        g.drawImage(turn[i], 0, 0, null);
                }
                if (showing) //shows the cards that were last played 
                {
                    for (int i = 0 ; i < choice.length ; i++)
                    {
                        choice[i].flip(true);
                        choice[i].show(g, 363 + 20*i, 251);
                    }
                }
            }
        }
    }
}