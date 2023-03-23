
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JComponent;

public class CTouchDetectorComponent extends JComponent implements MouseListener {

    
    private CTouchDetectorInterface mTouchDetectorInterface = null;
    private CAI mAI = null;
    
    
    public CTouchDetectorComponent(CAI ai)
    {
        this.mAI = ai;
    }
    
    public void setDelegate(CTouchDetectorInterface i)
    {
        mTouchDetectorInterface = i;
    }
    
    @Override
    public void mouseClicked(MouseEvent arg0) 
    {
        //System.out.println("" + arg0.getPoint().x +", " + arg0.getPoint().y);
        
        //Translate (x, y) into {0,1,2}
        int x = arg0.getPoint().x / 300;
        int y = arg0.getPoint().y / 300;
        
        Vector<FMark> m = mAI.UserTouch(x, y);
        mTouchDetectorInterface.OnDrawMarks(m);
        
        if(mAI.GetWinningData().mFlag)
        {
            mTouchDetectorInterface.OnDrawWinningLine(mAI.GetWinningData());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }
}