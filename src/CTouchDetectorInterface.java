
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author s225342
 */
public interface CTouchDetectorInterface 
{
    public void OnDrawMarks(Vector<FMark> marks);
    public void OnDrawWinningLine(FWinningData wd);
}
