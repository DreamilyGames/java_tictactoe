
import javax.swing.JFrame;
import javax.swing.JPanel;


public class CMain 
{  
    public static void main(String[] args) 
    {
        CAI ai = new CAI();
        
         CMainFrame mf = new CMainFrame(ai);
         mf.setDefaultCloseOperation(CMainFrame.EXIT_ON_CLOSE);
         
         
         
         mf.setVisible(true);
    }  
}   