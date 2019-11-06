import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main{

  public static void main(String[] args){
    JFrame frame = new JFrame("ToothPicks");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setMinimumSize(new Dimension( 400 , 400));

    ToothPickPanel panel = new ToothPickPanel();
    frame.setLayout(new BorderLayout());
    frame.getContentPane().add(panel);

    frame.pack();
    frame.setVisible(true);
  }
}
