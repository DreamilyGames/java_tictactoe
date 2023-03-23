
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author s225342
 */
public class MarkDrawer 
{
    public static void DrawCross(int x1, int y1, Graphics2D g2)
    {
        int x = x1 + 1;
        int y = y1 + 1;
        
        Line2D lin1 = new Line2D.Float(40 + 300 * x1, 40 + 300 * y1, 260 + 300 * x1, 260 + 300 * y1);
        
        g2.draw(lin1);
        
         Line2D lin2 = new Line2D.Float(40 + 300 * x1, 260 + 300 * y1, 260 + 300 * x1, 40 + 300 * y1);
        
        g2.draw(lin2);
    }
    
    public static void DrawCircle(int x1, int y1, Graphics2D g2)
    {
        int x = x1 + 1;
        int y = y1 + 1;
        
        g2.drawOval(50 + 300 * x1, 50 + 300 * y1, 200, 200);
    }
}
