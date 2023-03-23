import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.Vector;

class CMainFrame extends JFrame implements CTouchDetectorInterface
{
    private CAI mAI;
    
    private Vector<FMark> mMarks = new Vector<FMark>();
    private FWinningData mWinningData = null;
    
    @Override
    public void OnDrawMarks(Vector<FMark> m) 
    {
        if(m.size() <= 0)
            return;
        
        mMarks.addAll(m);
        repaint();
    }
    
    public CMainFrame(CAI ai)
    {
        this.mAI = ai;
        JPanel panel=new JPanel();
        getContentPane().add(panel);
        setSize(900, 900);
        
        CTouchDetectorComponent tdc = new CTouchDetectorComponent(ai);
        tdc.setDelegate(this);
        this.addMouseListener(tdc);
    }

    public void paint(Graphics g) 
    {
        super.paint(g);  // fixes the immediate problem.
        
        //grid rendering
        Graphics2D g2 = (Graphics2D) g;
        Line2D lin = new Line2D.Float(0, 300, 900, 300);
        Line2D lin2 = new Line2D.Float(0, 600, 900, 600);
        
        Line2D lin3 = new Line2D.Float(300, 0, 300, 900);
        Line2D lin4 = new Line2D.Float(600, 0, 600, 900);
        
        g2.draw(lin); g2.draw(lin2); g2.draw(lin3); g2.draw(lin4);
        
        for(int i = 0; i < mMarks.size(); ++i)
        {
            FMark m = mMarks.get(i);
            if(m.mIsPC)
            {
                CMarkDrawer.DrawCircle(m.mX, m.mY, g2);
            }
            else
            {
                CMarkDrawer.DrawCross(m.mX, m.mY, g2);
            }
        }
        
        if(mWinningData != null)
        {
            Line2D wlin = new Line2D.Float(mWinningData.mFirstPoint.x * 300 + 150, mWinningData.mFirstPoint.y * 300 + 150, 
                    mWinningData.mLastPoint.x * 300 + 150, mWinningData.mLastPoint.y * 300 + 150);
            g2.setColor(Color.GREEN);
            g2.draw(wlin);
        }
    }

    @Override
    public void OnDrawWinningLine(FWinningData wd) 
    {
        mWinningData = wd;
        repaint();
        
        JOptionPane.showMessageDialog(null, wd.mIsPC ? "AI wins!" : "You win!", "End", JOptionPane.INFORMATION_MESSAGE);
        this.removeMouseListener(this.getMouseListeners()[0]);
    }

    
}