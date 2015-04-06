import java.awt.*;
import javax.swing.JPanel;

public class LogSpiralPanel extends JPanel
{
   private static final double GOLDEN_MEAN =  (1 + Math.sqrt(5)) / 2;
   

   public void paintComponent(Graphics g)
   {
     Graphics2D g2 = (Graphics2D) g;
     g2.drawRect(0, 0, 400, (int)(400*GOLDEN_MEAN));
     recursiveDraw(g2, 0, 0, 400, 0);
   }
   
   /**
      Method that recursively draws a logarithmic spiral.
      @param x The upper left corner x-coordinate of the golden rectangle
      @param y The upper left corner y-coordinate of the golden rectangle
      @param side the smallest side size of the golden rectangle
      @param angle The angle (0, 90, 180 or 270) where the top of the 
      current golden rectangle is located. For the outermost golden 
      rectangle, the angle is 90.
   */
   public void recursiveDraw(Graphics2D g, int x, int y, int side, int angle)
   {
      if(x < 1)
      {
          g.drawRect(x, y, side, side);
          this.drawArc(g, x, y, side, angle);
      }
      else
      {
          g.drawRect(x, y, side, side);       
          this.drawArc(g, x, y, side, angle);
          int newSide = (int)(side * GOLDEN_MEAN);
          int newX = this.calculateNewX(x, angle, side, newSide);
          int newY = this.calculateNewY(y, angle, side, newSide);
          recursiveDraw(g, newX, newY, newSide, angle + 90);
   }
   }
   /**
      Draws the arc of the current iteration.
      @param x The x-coordinate of the square's upper-left corner  
      @param y The y-coordinate of the square's upper-left corner
      @param side The size of the side of the square (or the arc's radius)
      @param angle The angle (0, 90, 180 or 270) where the top of the 
      current golden rectangle is located. For the outermost golden 
      rectangle, the angle is 90.
   */
   private void drawArc(Graphics g, int x, int y, int side, int angle)
   {
      double auxX = x;
      double auxY = y;
      if (angle == 0 || angle == 270 )
      {
         auxX = x - side;
      }
      if (angle == 270 || angle == 180)
      {
         auxY = y - side;
      }
      g.drawArc((int) auxX, (int) auxY, (int) side * 2, (int) side * 2, angle, 90);
   }   
   private int calculateNewX(int x, int angle, int side, int newSide)
   {
      if (angle == 0)
         x = x + side - newSide;
      else if (angle == 90)
         x = x + side;
      else if (angle == 270)
         x = x - newSide;
      return x;
   }

   private int calculateNewY(int y, int angle, int side, int newSide)
   {
      if (angle == 0)
         y = y + side;
      else if (angle == 180)
         y = y - newSide;
      else if (angle == 270)
         y = y + side - newSide;
      return y;
   }
}