import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import java.lang.Math.*;
import java.awt.geom.AffineTransform;

public class GUI extends JFrame
{
  private int x,y;
  boolean isFiring = false;
  double angle;
  private final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
  // Supporting diagonal movement
  private boolean[] direction;
  private BufferedImage[] weapon = new BufferedImage[3];
  private int curwep;
  
  public GUI()
  {
    // Initializing variables
    x = 350;
    y = 350;
    direction = new boolean[] {false,false,false,false,false};
    drawWeapons();
    curwep = 0;
    angle = 0;
    
    // Create content pane and set layout
    JPanel content = new JPanel();
    content.setLayout(new BorderLayout());
    DrawArea display = new DrawArea(750,750);
    
    // Add components to content panes
    content.add(display, "Center");
    
    // Setting up keybindings to move with wasd
    content.getInputMap(IFW).put(KeyStroke.getKeyStroke("W"), "move up");
    content.getActionMap().put("move up", new MoveAction(1,true));
    content.getInputMap(IFW).put(KeyStroke.getKeyStroke("S"), "move down");
    content.getActionMap().put("move down", new MoveAction(2,true));
    content.getInputMap(IFW).put(KeyStroke.getKeyStroke("A"), "move left");
    content.getActionMap().put("move left", new MoveAction(3,true));
    content.getInputMap(IFW).put(KeyStroke.getKeyStroke("D"), "move right");
    content.getActionMap().put("move right", new MoveAction(4,true));
    
    content.getInputMap(IFW).put(KeyStroke.getKeyStroke("released W"), "stop up");
    content.getActionMap().put("stop up", new MoveAction(1,false));
    content.getInputMap(IFW).put(KeyStroke.getKeyStroke("released S"), "stop down");
    content.getActionMap().put("stop down", new MoveAction(2,false));
    content.getInputMap(IFW).put(KeyStroke.getKeyStroke("released A"), "stop left");
    content.getActionMap().put("stop left", new MoveAction(3,false));
    content.getInputMap(IFW).put(KeyStroke.getKeyStroke("released D"), "stop right");
    content.getActionMap().put("stop right", new MoveAction(4,false));
    
    // Setting up keybindings to change weapons
    content.getInputMap(IFW).put(KeyStroke.getKeyStroke("released Q"), "switch left");
    content.getActionMap().put("switch left", new SwitchAction(-1));
    content.getInputMap(IFW).put(KeyStroke.getKeyStroke("released E"), "switch right");
    content.getActionMap().put("switch right", new SwitchAction(1));
    
    //set up Mouse wheel listener for window
    content.addMouseWheelListener(new MouseAdapter() {      
      public void mouseWheelMoved (MouseWheelEvent e)
      {
        curwep+=e.getWheelRotation(); //this shifts the weapon based on the rotation
        System.out.printf("Mouse wheel rotated %s. ", (e.getWheelRotation()>0 ? "down":"up"));
        System.out.println("public void mouseWheelMoved (MouseWheelEvent e) in content.addMouseWheelListener()");
        // Make sure the weapon is valid
        // Wraparound when switching weapons
        if(curwep>2) curwep = 0;
        else if(curwep<0) curwep = 2;
        repaint();
      }
    });
    
    //set up Mouse motion listener for window for when cahracter rotates
    content.addMouseMotionListener(new MouseAdapter() {      
      public void mouseMoved (MouseEvent e) //checks if mouse moved
      {
        double dx = e.getX() - x;
        double dy = e.getY() - y;
        angle = Math.atan2(dy, dx) - Math.PI/2;
        repaint();
      }
    });
    
    //set up mouse listener for shooting
    content.addMouseListener(new MouseAdapter() {      
      public void mouseClicked (MouseEvent e) //checks if mouse moved
      {
        System.out.println("Mouse is was clicked. public void mouseClicked (MouseEvent e) in method content.addMouseListener().");
        isFiring = true;
        repaint();
      }
      
      public void mousePressed (MouseEvent e)
      {
        System.out.println("Mouse is being pressed. public void mousePressed (MouseEvent e) in method content.addMouseListener().");
        isFiring = true;
        repaint();
      }
    });
    
    // Setting window attributes
    setContentPane(content);
    pack();
    setTitle("WASD Click and Fire");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
  }
  
  private static void sleep (int sleep)
  {
    try { Thread.sleep(sleep); }
    catch (Exception e){};
  }
  
  class MoveAction extends AbstractAction
  {
    int dir;
    boolean set;
    
    public MoveAction(int dir, boolean set)
    {
      this.dir = dir;
      this.set = set;
    }
    
    public void actionPerformed(ActionEvent e)
    {
      // Set the movement boolean to true as long as the corresponding key is being pressed
      // Set the movement boolean to false the moment the key is released
      direction[dir] = set;
      
      // Outputting which key was pressed
      char[] key = {' ','W','S','A','D'};
      System.out.printf("%c key %s. ", key[dir],(direction[dir] ? "pressed":"released"));
      System.out.println("public void actionPerformed (ActionEvent e) in class MoveAction.");
      
      // Change x and y depending on which keys are pressed
      if(direction[1]) y-=10; // Up
      if(direction[2]) y+=10; // Down
      if(direction[3]) x-=10; // Left
      if(direction[4]) x+=10; // Right
      
      // Keey x and y within bounds
      if(x<0) x = 0;
      else if(x>700) x = 700;
      if(y<0) y = 0;
      else if(y>700) y = 700;
      repaint();
    }
  }
  
  class SwitchAction extends AbstractAction
  {
    private int change;
    
    public SwitchAction(int change)
    {
      this.change = change;
    }
    
    public void actionPerformed(ActionEvent e)
    {
      curwep+=change;
      System.out.printf("%c key released. ", (change==1 ? 'E':'Q'));
      System.out.println("public void actionPerformed (ActionEvent e) in class SwitchAction.");
      
      // Make sure the weapon is valid
      // Wraparound when switching weapons
      if(curwep>2) curwep = 0;
      else if(curwep<0) curwep = 2;
      
      repaint();
    }
  }
  
  class DrawArea extends JPanel
  {
    public DrawArea(int width, int height)
    {
      // Setting up drawing area
      this.setPreferredSize(new Dimension(width,height));
    }
    
    public void paintComponent (Graphics g)
    {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g.create();
      
      // Draw background
      g2d.setColor(Color.WHITE);
      g2d.fillRect(0,0,getWidth(),getHeight());
      
      //firing animation
      if (isFiring)
        drawFiringWeapons();
      else
        drawWeapons();
      isFiring = false;
      
      //draw weapon
      int cx = weapon[curwep].getWidth() / 2;
      int cy = weapon[curwep].getHeight() / 2;
      AffineTransform oldAT = g2d.getTransform();
      g2d.translate(cx + x, cy + y - 75);
      g2d.rotate(angle);
      g2d.translate(-cx, -cy+55);
      g2d.drawImage(weapon[curwep], 0, 0, null);
      g2d.setTransform(oldAT);
      
      // Draw character
      g2d.setColor(Color.BLUE);
      g2d.fillOval(x,y,50,50);
      g2d.setColor(Color.BLACK);
      g2d.drawOval(x,y,50,50);
      
      g2d.dispose();
    }
  }
  
  //public static int main() //use this one for BlueJ
    public static void main (String [] args) //use this one for Dr. Java
  {
    GUI window = new GUI();
    Instructions instructions = new Instructions();
    
    instructions.setLocation(window.getX()+window.getWidth(),window.getY());
    window.setVisible(true);
    instructions.setVisible(true);
    
  //  return 0; //comment this out for Dr. Java, need it for BlueJ
  }
  
  private Image loadImage (String name)  //Loads image from file
  {
    Image img = null;
    try
    {
      img = ImageIO.read (new File (name));
    }
    catch (IOException e)
    {
      System.out.println("Error: " + name + " does not exist");
    }
    return img;
  }
  
  // Drawing weapons onto buferedimages of the same size then drawing that onto the actual canvas
  public void drawWeapons()
  {
    Graphics g;
    
    // Pistol
    weapon[0] = new BufferedImage(60,200,BufferedImage.TYPE_INT_ARGB);
    g = weapon[0].getGraphics();
    g.drawImage(loadImage("Images\\pistol.jpg"),20,60,null);
    
    // Gattling Gun
    weapon[1] = new BufferedImage(60,200,BufferedImage.TYPE_INT_ARGB);
    g = weapon[1].getGraphics();
    g.drawImage(loadImage("Images\\gattling gun.jpg"),0,0,null);
    
    // Rocket Launcher
    weapon[2] = new BufferedImage(60,200,BufferedImage.TYPE_INT_ARGB);
    g = weapon[2].getGraphics();
    g.drawImage(loadImage("Images\\rocket launcher.jpg"),10,30,null);
  }
  
  // Drawing weapons when they are firing
  public void drawFiringWeapons()
  {
    Graphics2D g;
    AffineTransform at = new AffineTransform();
    // Pistol
    weapon[0] = new BufferedImage(60,200,BufferedImage.TYPE_INT_ARGB);
    g = (Graphics2D) weapon[0].getGraphics();
    at.rotate(Math.PI);
    at.translate(-weapon[0].getWidth()/2, -weapon[0].getHeight()/2);
    at.translate(-20, -60);
    g.drawImage(loadImage("Images\\firing pistol.jpg"), at,null);
    
    // Gattling Gun
    weapon[1] = new BufferedImage(60,200,BufferedImage.TYPE_INT_ARGB);
    g = (Graphics2D) weapon[1].getGraphics();
    at =  new AffineTransform();
    at.rotate(Math.PI);
    at.translate(-weapon[1].getWidth()-5, -weapon[1].getHeight());
    g.drawImage(loadImage("Images\\firing gattling gun.jpg"),at,null);
    
    // Rocket Launcher
    weapon[2] = new BufferedImage(60,200,BufferedImage.TYPE_INT_ARGB);
    g = (Graphics2D) weapon[2].getGraphics();
    at = new AffineTransform();
    at.rotate(Math.PI);
    at.translate(-weapon[2].getWidth(), -weapon[2].getHeight());
    //at.translate(10, 30);
    g.drawImage(loadImage("Images\\firing rocket launcher.jpg"),at,null);
  }
  
  
}
