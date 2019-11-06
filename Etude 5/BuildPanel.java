import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.DecimalFormat;

public class BuildPanel extends JPanel{

  public BuildPanel(){
    setBackground(Color.yellow);
  }

  public void paintComponent (Graphics g){
    super.paintComponent(g);
    g.drawLine(50, 50, 100, 100);
  }
}
