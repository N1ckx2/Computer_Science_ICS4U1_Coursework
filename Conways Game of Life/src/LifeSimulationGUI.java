/**
 * Nicholas Vadivelu
 * 23 December 2015
 * ICS4U1
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  // Needed for ActionListener
import java.lang.Math.*;

public class LifeSimulationGUI extends JFrame
{
    private Colony colony = new Colony (0.5, 125, 125);
    private Timer t;
    private boolean populate = true, drawRect = false;
    private int startX, startY, endX, endY;


    //======================================================== constructor
    public LifeSimulationGUI ()
    {
        // 1... Create/initialize components
        BtnListener btnListener = new BtnListener (); // listener for all buttons

        JToolBar toolbar = new JToolBar();

        JComboBox<String> box = new JComboBox<>();
        box.addItem("Populate");
        box.addItem("Eradicate");
        box.addItemListener(new ItemListener()
        { public void itemStateChanged(ItemEvent e) { populate = e.getItem().equals("Populate");}});

        JButton simulateBtn = new JButton ("Simulate");
        simulateBtn.addActionListener (btnListener);
        JButton saveBtn = new JButton ("Save");
        saveBtn.addActionListener (btnListener);
        JButton loadBtn = new JButton ("Load");
        loadBtn.addActionListener (btnListener);

        // 2... Create content pane, set layout
        JPanel content = new JPanel ();        // Create a content pane
        content.setLayout (new BorderLayout ()); // Use BorderLayout for panel
        JPanel north = new JPanel ();
        north.setLayout (new FlowLayout ()); // Use FlowLayout for input area

        DrawArea board = new DrawArea (500, 500);
        board.addMouseListener(new ClickListener());
        board.addMouseMotionListener(new DragListener());

        // 3... Add the components to the input area.
        toolbar.add(simulateBtn);
        toolbar.addSeparator();
        toolbar.add(saveBtn);
        toolbar.add(loadBtn);
        toolbar.addSeparator();
        toolbar.add(box);

        north.add (toolbar);

        content.add (north, "North"); // Input area
        content.add (board, "South"); // Output area

        // 4... Set this window's attributes.
        setContentPane (content);
        pack ();
        setTitle ("Life Simulation");
        //setSize  (510, 570);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo (null);           // Center window.
    }


    class BtnListener implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            if (e.getActionCommand ().equals ("Simulate"))
            {
                Movement moveColony = new Movement (); // ActionListener for timer
                t = new Timer (200, moveColony); // set up Movement to run every 200 milliseconds
                t.start (); // start simulation
            } else if (e.getActionCommand ().equals ("Save"))
            {

            } else if (e.getActionCommand ().equals ("Simulate"))
            {

            }
            repaint ();            // refresh display of colony
        }
    }


    class DrawArea extends JPanel
    {
        public DrawArea (int width, int height)
        {
            this.setPreferredSize (new Dimension (width, height)); // size
        }

        public void paintComponent (Graphics g)
        {
            colony.show (g); // display current state of colony
            if (drawRect && (endX != startX || endY != startY))
            {
                g.setColor(new Color(0, 158, 200, 120));
                int x = (startX < endX ? startX : endX);
                int width = Math.abs(endX - startX);
                int y = (startY < endY ? startY : endY);
                int height = Math.abs(endY - startY);
                g.fillRect(x, y, width, height);
            }
        }
    }


    class Movement implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            colony.advance (); // advance to the next time step
            repaint (); // refresh
        }
    }

    class ClickListener extends MouseAdapter
    {
        public void mousePressed(MouseEvent e)
        {
            startX = e.getX();
            startY = e.getY();
        }
        public void mouseReleased(MouseEvent e)
        {
            drawRect = false;
            endX = e.getX();
            endY = e.getY();
            int x1 = (startX < endX ? startX : endX);
            int x2 = (startX < endX ? endX : startX);
            int y1 = (startY < endY ? startY : endY);
            int y2 = (startY < endY ? endY : startY);
            if (populate)
                colony.populate(x1, y1, x2, y2);
            else
                colony.eradicate(x1, y1, x2, y2);
            startX = 0;
            startY = 0;
            endX = 0;
            endY = 0;
        }
    }

    class DragListener extends MouseAdapter
    {
        public void mouseDragged (MouseEvent e)
        {
            drawRect = true;
            endX = e.getX();
            endY = e.getY();
            repaint();
        }
    }


    //======================================================== method main
    public static void main (String[] args)
    {
        LifeSimulationGUI window = new LifeSimulationGUI();
        window.setVisible (true);
    }
}