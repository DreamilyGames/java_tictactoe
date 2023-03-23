
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
public class AI 
{
    Mark[][] map = new Mark[3][3]; 
    int[][] pointmap = {{70, 50, 70},
                        {50, 100, 50},
                        {70, 50, 70}};
    int round = 0;
    
    public AI()
    {
        for(int i = 0; i < 3 ; ++i)
        {
            for(int j = 0; j < 3; ++j)
            {
                 Mark m = new Mark();
            
                 m.x = j;
                 m.y = i;
                 
            
                 map[i][j] = m;
            }
        }
            
    }
    
    public Mark GetHighestPointMark( Vector<Mark> v)
    {   
        Mark m = new Mark();
        
        int pts = 0;
        
        for(int i = 0; i < 3; ++i)
        {
            for(int j = 0; j < 3; ++j)
            {
                int p = pointmap[j][i];
                if(p > pts && CheckAvailability(v, i, j))
                {
                    pts = p;
                    m.x = i;
                    m.y = j;
                }
            }
        }
        
        return m;
    }
    
   
    
    public int GetTargetSlot(int x1, int x2)
    {
        if(x1 == 0 && x2 == 1)
        {
            return 2;
        }
        else if(x1 == 1 && x2 == 2)
        {
            return 0;
        }
        else if(x1 == 0 && x2 == 2)
        {
            return 1;
        }
        else if(x1 == 1 && x2 == 0)
        {
            return 2;
        }
        else if(x1 == 2 && x2 == 1)
        {
            return 0;
        }
        else if(x1 == 2 && x2 == 0)
        {
            return 1;
        }
        
        return -1;
    }
    
    public boolean CheckAvailability(Vector<Mark> clears, int x, int y)
    {
        for(int i = 0; i < clears.size(); ++i)
        {
            Mark m = clears.get(i);
            if(m.x == x && m.y == y)
                return true;
        }
        
        return false;
    }

    public Mark TryWin(Vector<Mark> v)
    {
        Mark one = null;
        Mark two = null;
        
        for(int i = 0; i < 9; ++i)
        {
            one = map[i / 3][i % 3];
            
            for(int j = 0; j < 9; ++j)
            {
                two = map[j / 3][j % 3];
                
                if(one.isPC && two.isPC && one.flag && two.flag)
                {
                    int onetwoslope = two.x == one.x ? -1 : ((two.y - one.y) / (two.x - one.x));
                    
                    if(one.x == two.x)
                    {
                        int targety = GetTargetSlot(one.y, two.y);
                        if(targety != -1 && CheckAvailability(v, one.x, targety))
                        {
                            //win
                            Mark m = new Mark();
                            m.flag = true;
                            m.isPC = true;
                            m.x = one.x;
                            m.y = targety;
                            
                            return m;
                        }
                    }
                    else if(one.y == two.y)
                    {
                        int targetx = GetTargetSlot(one.x, two.x);
                        if(targetx != -1 && CheckAvailability(v,targetx, one.y ))
                        {
                            //win
                            Mark m = new Mark();
                            m.flag = true;
                            m.isPC = true;
                            m.x = targetx;
                            m.y = one.y;
                            
                            return m;
                        }
                    }
                    else if(onetwoslope == 1 || onetwoslope == -1)
                    {
                        int targetx = GetTargetSlot(one.x, two.x);
                        int targety = GetTargetSlot(one.y, two.y);
                        
                        if(targetx != -1 && targety != -1 && CheckAvailability(v,targetx, targety ))
                        {
                            //win
                            Mark m = new Mark();
                            m.flag = true;
                            m.isPC = true;
                            m.x = targetx;
                            m.y = targety;
                            
                            return m;
                        }
                    }
                }      
            }
        }
        
        return null;
    }
    
    public Mark TryDefend(Vector<Mark> v)
    {
        Mark one = null;
        Mark two = null;
        
        for(int i = 0; i < 9; ++i)
        {
            one = map[i / 3][i % 3];
            
            for(int j = 0; j < 9; ++j)
            {
                two = map[j / 3][j % 3];
                
                if(!one.isPC && !two.isPC && one.flag && two.flag)
                {
                    int onetwoslope = two.x == one.x ? -1 : ((two.y - one.y) / (two.x - one.x));
                    
                    if(one.x == two.x)
                    {
                        int targety = GetTargetSlot(one.y, two.y);
                        if(targety != -1 && CheckAvailability(v, one.x, targety))
                        {
                            //defend
                            Mark m = new Mark();
                            m.flag = true;
                            m.isPC = true;
                            m.x = one.x;
                            m.y = targety;
                            
                            return m;
                        }
                    }
                    else if(one.y == two.y)
                    {
                        int targetx = GetTargetSlot(one.x, two.x);
                        if(targetx != -1 && CheckAvailability(v,targetx, one.y ))
                        {
                            //defend
                            Mark m = new Mark();
                            m.flag = true;
                            m.isPC = true;
                            m.x = targetx;
                            m.y = one.y;
                            
                            return m;
                        }
                    }
                    else if(onetwoslope == 1 || onetwoslope == -1)
                    {
                        int targetx = GetTargetSlot(one.x, two.x);
                        int targety = GetTargetSlot(one.y, two.y);
                        
                        if(targetx != -1 && targety != -1 && CheckAvailability(v,targetx, targety ))
                        {
                            //defend
                            Mark m = new Mark();
                            m.flag = true;
                            m.isPC = true;
                            m.x = targetx;
                            m.y = targety;
                            
                            return m;
                        }
                    }
                }      
            }
        }
        
        return null;
    }
    
    public Mark Compute()
    {
        //get clear space
        Vector<Mark> clear = new Vector<Mark>();
        for(int i = 0; i < 3; ++i)
        {
            for(int j = 0; j < 3; ++j)
            {
                Mark m = map[i][j];
                if(!m.flag)
                    clear.add(m);
            }
        }
        
        //defend
        Mark defendingMark = TryDefend(clear);
        if(defendingMark != null)
        {
            return defendingMark;
        }
        
        //can win
        Mark winningMark = TryWin(clear);
        if(winningMark != null)
        {
            return winningMark;
        }
        
        //get point
        Mark target = GetHighestPointMark(clear);
        target.isPC = true;
        target.flag = true;
        
        return target;
    }
    
    public boolean ValidatePlacement(int x, int y)
    {
        Mark m = map[y][x];
        return !m.flag;
    }
    
    public Vector<Mark> UserTouch(int x, int y)
    {
        //System.out.println("" + x +", " + y);
        
        if(!ValidatePlacement(x, y))
            return new Vector<Mark>();
        
        Mark m = map[y][x];
        m.flag = true;
        m.isPC = false;
        
        Vector<Mark> ret = new Vector<Mark>();
        ret.add(m);
        
        ++round;
        
        
        
        Mark pcMove = Compute();
        ret.add(pcMove);
        //System.out.println("" + pcMove.x + " , " + pcMove.y);
        
        if(pcMove.x < 0|| pcMove.y < 0)
        {
            System.out.println("" + pcMove.x + " , " + pcMove.y);
        }
            
        
        map[pcMove.y][pcMove.x].flag = true;
        map[pcMove.y][pcMove.x].isPC = true;
        
         ++round;
        
        return ret;
    }
}
