import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FractalTreeViewer implements ActionListener
{
    private final int WIDTH = 800;
    private final int HEIGHT = 1000;

    private final int MIN = 1, MAX = 12;

    private JButton increase, decrease, increaseAngle, decreaseAngle, increaseRatio, decreaseRatio, shiftUp, shiftDown, increaseLength, decreaseLength;
    private JLabel titleLabel, orderLabel;
    private FractalPanel drawing;
    private JPanel panel, tools;
    private JFrame frame;
    
    int increment = 30;
    //-----------------------------------------------------------------
    //  Sets up the components for the applet.
    //-----------------------------------------------------------------
    public static void main(String[] args)
    {
        FractalTreeViewer viewer = new FractalTreeViewer();
    }

    public FractalTreeViewer()
    {
        tools = new JPanel ();
        tools.setLayout (new BoxLayout(tools, BoxLayout.X_AXIS));
        tools.setBackground (Color.yellow);
        tools.setOpaque (true);

        titleLabel = new JLabel ("The Fractal Tree");
        titleLabel.setForeground (Color.black);

        increase = new JButton (new ImageIcon ("increase.gif"));
        increase.setPressedIcon (new ImageIcon ("increasePressed.gif"));
        increase.setMargin (new Insets (0, 0, 0, 0));
        increase.addActionListener (this);
        decrease = new JButton (new ImageIcon ("decrease.gif"));
        decrease.setPressedIcon (new ImageIcon ("decreasePressed.gif"));
        decrease.setMargin (new Insets (0, 0, 0, 0));
        decrease.addActionListener (this);
        
        increaseAngle = new JButton (new ImageIcon ("IncrAng.png"));
        increaseAngle.setPressedIcon (new ImageIcon ("IncrAng.png"));
        increaseAngle.setMargin (new Insets (0, 0, 0, 0));
        increaseAngle.addActionListener (this);
        decreaseAngle = new JButton (new ImageIcon ("DecrAng.png"));
        decreaseAngle.setPressedIcon (new ImageIcon ("DecrAng.png"));
        decreaseAngle.setMargin (new Insets (0, 0, 0, 0));
        decreaseAngle.addActionListener (this);
        
        increaseRatio = new JButton (new ImageIcon ("IncrLen.png"));
        increaseRatio.setPressedIcon (new ImageIcon ("IncrLen.png"));
        increaseRatio.setMargin (new Insets (0, 0, 0, 0));
        increaseRatio.addActionListener (this);
        decreaseRatio = new JButton (new ImageIcon ("DecrLen.png"));
        decreaseRatio.setPressedIcon (new ImageIcon ("DecrLen.png"));
        decreaseRatio.setMargin (new Insets (0, 0, 0, 0));
        decreaseRatio.addActionListener (this);
        

        orderLabel = new JLabel ("Order: 1");
        orderLabel.setForeground (Color.black);

        tools.add (titleLabel);
        tools.add (Box.createHorizontalStrut (20));
        tools.add (decrease);
        tools.add (increase);
        tools.add(increaseAngle);
        tools.add(decreaseAngle);
        tools.add(increaseRatio);
        tools.add(decreaseRatio);

        tools.add (Box.createHorizontalStrut (20));
        tools.add (orderLabel);

        drawing = new FractalPanel (1, increment, .8);

        panel = new JPanel();
        panel.add (tools);
        panel.add (drawing);

        frame = new JFrame();
        frame.setTitle("Fractal Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(panel);
        frame.setVisible(true);
    }

    //-----------------------------------------------------------------
    //  Determines which button was pushed, and sets the new order
    //  if it is in range.
    //-----------------------------------------------------------------
    public void actionPerformed (ActionEvent event)
    {
        int order = drawing.getOrder();
        int increment = drawing.getIncrement();
        double ratio = drawing.getRatio();

        if (event.getSource() == increase)
            order++;
        else if(event.getSource() == increaseAngle)
            increment+= 5;
        else if(event.getSource() == decreaseAngle)
            increment-= 5;
        else if(event.getSource() == increaseRatio)
            ratio+= .05;
        else if(event.getSource() == decreaseRatio)
            ratio-= .05;
        else
            order--;

        if (order >= MIN && order <= MAX)
        {
            orderLabel.setText ("Order: " + order);
            drawing.setOrder (order);
            frame.repaint();
        }
        
        if(increment >= 0 && increment <= 90)
        {
            drawing.setIncrement (increment);
            frame.repaint();
        }
        
        if(ratio >= 0 && ratio <= 1)
        {
            drawing.setRatio (ratio);
            frame.repaint();
        }

    }
}
