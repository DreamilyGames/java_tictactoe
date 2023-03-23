
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JComponent;

public class TouchDetectorComponent extends JComponent implements MouseListener {

    
    TouchDetectorInterface tdi = null;
    AI ai;
    
    
    public TouchDetectorComponent(AI ai)
    {
        this.ai = ai;
    }
    
    public void setDelegate(TouchDetectorInterface i)
    {
        tdi = i;
    }
    
    @Override
    public void mouseClicked(MouseEvent arg0) 
    {
        //System.out.println("" + arg0.getPoint().x +", " + arg0.getPoint().y);
        
        //Translate (x, y) into {0,1,2}
        int x = arg0.getPoint().x / 300;
        int y = arg0.getPoint().y / 300;
        
        Vector<Mark> m = ai.UserTouch(x, y);
        tdi.OnDrawMarks(m);
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