import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.DecimalFormat;
import java.util.*;

public class ToothPickPanel extends JPanel{
  private int generation = 0;
  private int midPoint = 30;
  private double ratio = 1;
  private JButton add, minus, ratioAdd, ratioMinus;
  private JLabel generationLabel, ratioLabel, zoomLabel;
  private JPanel buttonPanel, zoomPanel;
  private JSlider zoomSlider;

  public ToothPickPanel(){
    setLayout(new BorderLayout());
    setBackground(Color.gray);

    minus = new JButton("-");
    add = new JButton("+");
    ratioAdd = new JButton("+");
    ratioMinus = new JButton("-");

    ButtonListener listener = new ButtonListener();
    minus.addActionListener(listener);
    add.addActionListener(listener);
    ratioAdd.addActionListener(listener);
    ratioMinus.addActionListener(listener);

    generationLabel = new JLabel("Generation: " + generation);
    ratioLabel = new JLabel("Ratio: " + ratio);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.white);
    buttonPanel.add(minus);
    buttonPanel.add(generationLabel);
    buttonPanel.add(add);
    buttonPanel.add(ratioLabel);
    buttonPanel.add(ratioMinus);
    buttonPanel.add(ratioAdd);

    zoomLabel = new JLabel("Zoom:   0");
    zoomSlider = new JSlider(JSlider.HORIZONTAL, 0, 300, 0);
    zoomSlider.setMajorTickSpacing(50);
    zoomSlider.setMinorTickSpacing(10);
    zoomSlider.setPaintTicks(true);
    SliderListener listener1 = new SliderListener();
    zoomSlider.addChangeListener(listener1);

    JPanel zoomPanel = new JPanel();
    zoomPanel.add(zoomLabel);
    zoomPanel.add(zoomSlider);


    add(buttonPanel, BorderLayout.NORTH);
    add(zoomPanel, BorderLayout.SOUTH);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    LinkedList<Point> currentEndPointList = new LinkedList<Point>();
    LinkedList<Point> nextEndPointList = new LinkedList<Point>();

    int width = getWidth()/2;
    int height = getHeight()/2;
    int count = 0;
    int length = 0;

    currentEndPointList.add(new Point(width - midPoint, height));
    currentEndPointList.add(new Point(width + midPoint, height));

    g.drawLine(width - midPoint, height, width + midPoint, height);

    while(count < generation){
      length = midPoint * 2;
      length *= ratio;
      midPoint = length/2;
      while(currentEndPointList.size() > 0){
        Point coord = currentEndPointList.remove();
        if(count % 2 == 0){
          g.drawLine(coord.x, coord.y - midPoint, coord.x, coord.y + midPoint);
          nextEndPointList.add(new Point(coord.x, coord.y + midPoint));
          nextEndPointList.add(new Point(coord.x, coord.y - midPoint));
        }
        else{
          g.drawLine(coord.x - midPoint, coord.y, coord.x + midPoint, coord.y);
          nextEndPointList.add(new Point(coord.x + midPoint, coord.y));
          nextEndPointList.add(new Point(coord.x - midPoint, coord.y));
        }
      }
      currentEndPointList.addAll(nextEndPointList);
      while(nextEndPointList.size() > 0){
        nextEndPointList.remove();
      }
      count++;
    }
    midPoint = 30;
}

  private class ButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      if((event.getSource() == minus) && (generation >= 1)){
        generation--;
        generationLabel.setText("Generation: " + generation);
        repaint();
      }
      else if((event.getSource() == add) && (generation <= 21)){
        generation++;
        generationLabel.setText("Generation: " + generation);
        repaint();
      }
      else if((event.getSource() == ratioAdd) && (ratio < 2.0)){
        DecimalFormat f = new DecimalFormat("#.0");
        ratio += 0.1;
        ratio = Double.parseDouble(f.format(ratio));
        ratioLabel.setText("Ratio: " + ratio);
        repaint();
      }
      else if((event.getSource() == ratioMinus) && (ratio > 0.1)){
        DecimalFormat f = new DecimalFormat("#.0");
        ratio -= 0.1;
        ratio = Double.parseDouble(f.format(ratio));
        ratioLabel.setText("Ratio: " + ratio);
        repaint();
      }
    }
  }

  private class SliderListener implements ChangeListener{
    private int zoomValue;

    public void stateChanged(ChangeEvent event){
      zoomValue = zoomSlider.getValue();
      midPoint = midPoint + zoomValue;
      zoomLabel.setText("Zoom:   " + zoomValue);
      repaint();
    }
  }
}
