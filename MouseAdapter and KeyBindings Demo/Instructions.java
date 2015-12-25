import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class Instructions extends JFrame
{
  public Instructions()
  {// Create content pane, set layout
    JPanel content = new JPanel();
    content.setLayout(new BorderLayout());
    
    JTextArea _words = new JTextArea(4,20);
    _words.setLineWrap(true);
    _words.setWrapStyleWord(true); 
    _words.setEditable(false);
    _words.setFont(new Font("Veranda", Font.BOLD, 12));
    _words.setBorder(BorderFactory.createCompoundBorder(
                                                        BorderFactory.createLineBorder(Color.BLACK),
                                                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                                                       ));
    _words.setText(
                   "WASD to move\n"+
                   "Q/E or scroll wheel to switch weapons\n"+
                   "Mouse to aim\n"+
                   "Left click to shoot"
                  );
    
    // Add components to content panes
    content.add(_words,"Center");
    
    // Set window attributes
    setContentPane(content);
    pack();
    setTitle("Instructions");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
  }
}