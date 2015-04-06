import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 * Write a description of class SpiralComponent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpiralComponent extends JComponent
{

    public void SpiralComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        
        LogSpiralPanel logPanel = new LogSpiralPanel();
        
        logPanel.paintComponent(g2);
    }


}
