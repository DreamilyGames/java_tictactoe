
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main 
{  
    public static void main(String[] args) 
    {
        AI ai = new AI();
        
         MainFrame mf = new MainFrame(ai);
         mf.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
         
         
         
         mf.setVisible(true);
    }  
}   