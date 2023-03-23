import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.Vector;

class MainFrame extends JFrame implements TouchDetectorInterface
{
    AI ai;
    
    Vector<Mark> marks = new Vector<Mark>();
    
    @Override
    public void OnDrawMarks(Vector<Mark> m) 
    {
        if(m.size() <= 0)
            return;
        
        marks.addAll(m);
        repaint();
    }
    
    public MainFrame(AI ai)
    {
        this.ai = ai;
        JPanel panel=new JPanel();
        getContentPane().add(panel);
        setSize(900, 900);
        
        TouchDetectorComponent tdc = new TouchDetectorComponent(ai);
        tdc.setDelegate(this);
        this.addMouseListener(tdc);
    }

    public void paint(Graphics g) 
    {
        super.paint(g);  // fixes the immediate problem.
        Graphics2D g2 = (Graphics2D) g;
        Line2D lin = new Line2D.Float(0, 300, 900, 300);
        Line2D lin2 = new Line2D.Float(0, 600, 900, 600);
        
        Line2D lin3 = new Line2D.Float(300, 0, 300, 900);
        Line2D lin4 = new Line2D.Float(600, 0, 600, 900);
        
        g2.draw(lin); g2.draw(lin2); g2.draw(lin3); g2.draw(lin4);
        
        for(int i = 0; i < marks.size(); ++i)
        {
            Mark m = marks.get(i);
            if(m.isPC)
            {
                MarkDrawer.DrawCircle(m.x, m.y, g2);
            }
            else
            {
                MarkDrawer.DrawCross(m.x, m.y, g2);
            }
        }
    }

    
}