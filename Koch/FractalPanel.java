import java.awt.*;
import javax.swing.JPanel;
import java.lang.Math;

public class FractalPanel extends JPanel
{
   private final int PANEL_WIDTH = 800;
   private final int PANEL_HEIGHT = 800;
   
   private final double SIZE_RATIO = .8;

   private final int TOPX = 200, TOPY = 20;
   private final int LEFTX = 60, LEFTY = 300;
   private final int RIGHTX = 340, RIGHTY = 300;
   
   private int current; //current order
   
   private int increment;
   private double ratio;
   private double angle;
   private double leftRadians;
   private double rightRadians;
   private int lengthIncrement;
   private int shiftLength;
   //-----------------------------------------------------------------
   //  Sets the initial fractal order to the value specified.
   //-----------------------------------------------------------------
   public FractalPanel (int currentOrder, int angle1, double ratio)
   {
      current = currentOrder;
      setBackground (Color.black);
      setPreferredSize (new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
      this.increment = angle1;
      this.ratio = ratio;
   }

   //-----------------------------------------------------------------
   //  Draws the fractal recursively. Base case is an order of 1 for
   //  which a simple straight line is drawn. Otherwise three
   //  intermediate points are computed, and each line segment is
   //  drawn as a fractal.
   //-----------------------------------------------------------------
   public void drawFractal (int order, int x1, int y1, int x2, int y2, double angle, int r, int g, int b,
                            Graphics page)
   {

      int deltaX, deltaY, x3, y3;
      double newLength;
      double radians;
      
      if (order == 1){
         page.drawLine (x1, y1, x2, y2);
      }
      else
      {
         Color lineColor = new Color(r, g, b);
         page.setColor (lineColor);
         page.drawLine (x1, y1, x2, y2);       
         
         deltaX = x2 - x1;  // distance between end points
         deltaY = y2 - y1;
         
         newLength = Math.pow(Math.pow(deltaX, 2) + Math.pow(deltaY, 2), .5);
         newLength = newLength * ratio;
         
         x1 = x2;
         y1 = y2;

         angle += increment;
         radians = Math.toRadians(angle);
         double RIGHT_SIN_ANGLE = Math.cos(radians);
         double RIGHT_COS_ANGLE = Math.sin(radians);
         
         x2 = (int)(x1 + RIGHT_COS_ANGLE*(newLength));
         y2 = (int)(y1 - RIGHT_SIN_ANGLE*(newLength));
         
         page.drawLine (x1, y1, x2, y2);        
         drawFractal(order - 1, x1, y1, x2, y2, angle, order*20, 75 + order * 15, 0 + order*10 , page);        
         
         angle -= 2*increment;
         radians = Math.toRadians(angle);
         double LEFT_SIN_ANGLE = Math.cos(-radians);
         double LEFT_COS_ANGLE = Math.sin(-radians);
         
         x3 = (int)(x1 - LEFT_COS_ANGLE*(newLength));
         y3 = (int)(y1 - LEFT_SIN_ANGLE*(newLength));
         
         page.drawLine (x1, y1, x3, y3);        
         drawFractal(order - 1, x1, y1, x3, y3, angle, order*20, 75 + order * 15, 0 + order*10 , page);        
         
         
      }
   }

   //-----------------------------------------------------------------
   //  Performs the initial calls to the drawFractal method.
   //-----------------------------------------------------------------
   public void paintComponent (Graphics page)
   {
      super.paintComponent (page);
      
      drawFractal (current, 400, 700, 400, 650, 0, 120, 75, 0, page);
   }

   //-----------------------------------------------------------------
   //  Sets the fractal order to the value specified.
   //-----------------------------------------------------------------
   public void setOrder (int order)
   {
      current = order;
   }
   public void setRatio (double ratio)
   {
      this.ratio = ratio;
   }
   public void setIncrement(int increment)
   {
       this.increment = increment;
   }

   
   //-----------------------------------------------------------------
   //  Returns the current order.
   //-----------------------------------------------------------------
   public int getOrder ()
   {
      return current;
   }
   public double getRatio ()
   {
      return this.ratio;
   }
   public int getIncrement()
   {
       return this.increment;
    }

    
    
}
