
import javax.swing.JFrame;
/**
 * Write a description of class SpiralViewer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpiralViewer
{
   public static void main(String[] args)
   {
       JFrame frame = new JFrame();
        
        frame.setSize(800 /* x */, 600 /* y */);
        
        SpiralComponent component = new SpiralComponent();
 
        frame.add(component);
        
        frame.setVisible(true);
    }
}
