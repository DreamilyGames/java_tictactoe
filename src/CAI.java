
import java.awt.Point;
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
public class CAI 
{
    private FMark[][] mMap = new FMark[3][3]; 
    private int[][] mPointMap = {{70, 50, 70},
                        {50, 100, 50},
                        {70, 50, 70}};
    private int mRound = 0;
    
    private  FWinningData mWinningData = new FWinningData();
    
    public CAI()
    {
        for(int i = 0; i < 3 ; ++i)
        {
            for(int j = 0; j < 3; ++j)
            {
                 FMark m = new FMark();
            
                 m.mX = j;
                 m.mY = i;
                 
            
                 mMap[i][j] = m;
            }
        }
            
    }
    
    private FMark GetHighestPointMark( Vector<FMark> v)
    {   
        FMark m = new FMark();
        
        int pts = 0;
        
        for(int i = 0; i < 3; ++i)
        {
            for(int j = 0; j < 3; ++j)
            {
                int p = mPointMap[j][i];
                if(p > pts && CheckAvailability(v, i, j))
                {
                    pts = p;
                    m.mX = i;
                    m.mY = j;
                }
            }
        }
        
        return m;
    }
    
   
    
    private int GetTargetSlot(int x1, int x2)
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
    
    public boolean CheckAvailability(Vector<FMark> clears, int x, int y)
    {
        for(int i = 0; i < clears.size(); ++i)
        {
            FMark m = clears.get(i);
            if(m.mX == x && m.mY == y)
                return true;
        }
        
        return false;
    }

    private FMark TryWin(Vector<FMark> v)
    {
        FMark one = null;
        FMark two = null;
        
        for(int i = 0; i < 9; ++i)
        {
            one = mMap[i / 3][i % 3];
            
            for(int j = 0; j < 9; ++j)
            {
                two = mMap[j / 3][j % 3];
                
                if(one.mIsPC && two.mIsPC && one.mFlag && two.mFlag)
                {
                    int onetwoslope = two.mX == one.mX ? Integer.MAX_VALUE : ((two.mY - one.mY) / (two.mX - one.mX));
                    
                    if(one.mX == two.mX)
                    {
                        int targety = GetTargetSlot(one.mY, two.mY);
                        if(targety != -1 && CheckAvailability(v, one.mX, targety))
                        {
                            //win
                            FMark m = new FMark();
                            m.mFlag = true;
                            m.mIsPC = true;
                            m.mX = one.mX;
                            m.mY = targety;
                            
                            mWinningData.mFlag = true;
                            mWinningData.mIsPC = true;
                            mWinningData.mFirstPoint = new Point(one.mX, Math.min(Math.min(one.mY, two.mY), targety));
                            mWinningData.mLastPoint = new Point(one.mX, Math.max(Math.max(one.mY, two.mY), targety));
                            
                            return m;
                        }
                    }
                    else if(one.mY == two.mY)
                    {
                        int targetx = GetTargetSlot(one.mX, two.mX);
                        if(targetx != -1 && CheckAvailability(v,targetx, one.mY ))
                        {
                            //win
                            FMark m = new FMark();
                            m.mFlag = true;
                            m.mIsPC = true;
                            m.mX = targetx;
                            m.mY = one.mY;
                            
                            mWinningData.mFlag = true;
                            mWinningData.mIsPC = true;
                            mWinningData.mFirstPoint = new Point(Math.min(Math.min(one.mX, two.mX), targetx), one.mY);
                            mWinningData.mLastPoint = new Point(Math.max(Math.max(one.mX, two.mX), targetx), one.mY);
                            
                            return m;
                        }
                    }
                    else if(onetwoslope == 1 || onetwoslope == -1)
                    {
                        int targetx = GetTargetSlot(one.mX, two.mX);
                        int targety = GetTargetSlot(one.mY, two.mY);
                        
                        if(targetx != -1 && targety != -1 && CheckAvailability(v,targetx, targety ))
                        {
                            //win
                            FMark m = new FMark();
                            m.mFlag = true;
                            m.mIsPC = true;
                            m.mX = targetx;
                            m.mY = targety;
                            
                            mWinningData.mFlag = true;
                            mWinningData.mIsPC = true;
                            mWinningData.mFirstPoint = new Point(Math.min(Math.min(one.mX, two.mX), targetx), 
                                                                 Math.min(Math.min(one.mY, two.mY), targety));
                            mWinningData.mLastPoint = new Point(Math.max(Math.max(one.mX, two.mX), targetx), 
                                                                 Math.max(Math.max(one.mY, two.mY), targety));
                            
                            return m;
                        }
                    }
                }      
            }
        }
        
        return null;
    }
    
    private FMark TryDefend(Vector<FMark> v)
    {
        FMark one = null;
        FMark two = null;
        
        for(int i = 0; i < 9; ++i)
        {
            one = mMap[i / 3][i % 3];
            
            for(int j = 0; j < 9; ++j)
            {
                two = mMap[j / 3][j % 3];
                
                if(!one.mIsPC && !two.mIsPC && one.mFlag && two.mFlag)
                {
                    int onetwoslope = two.mX == one.mX ? Integer.MAX_VALUE : ((two.mY - one.mY) / (two.mX - one.mX));
                    
                    if(one.mX == two.mX)
                    {
                        int targety = GetTargetSlot(one.mY, two.mY);
                        if(targety != -1 && CheckAvailability(v, one.mX, targety))
                        {
                            //defend
                            FMark m = new FMark();
                            m.mFlag = true;
                            m.mIsPC = true;
                            m.mX = one.mX;
                            m.mY = targety;
                            
                            return m;
                        }
                    }
                    else if(one.mY == two.mY)
                    {
                        int targetx = GetTargetSlot(one.mX, two.mX);
                        if(targetx != -1 && CheckAvailability(v,targetx, one.mY ))
                        {
                            //defend
                            FMark m = new FMark();
                            m.mFlag = true;
                            m.mIsPC = true;
                            m.mX = targetx;
                            m.mY = one.mY;
                            
                            return m;
                        }
                    }
                    else if(onetwoslope == 1 || onetwoslope == -1)
                    {
                        int targetx = GetTargetSlot(one.mX, two.mX);
                        int targety = GetTargetSlot(one.mY, two.mY);
                        
                        if(targetx != -1 && targety != -1 && CheckAvailability(v,targetx, targety ))
                        {
                            //defend
                            FMark m = new FMark();
                            m.mFlag = true;
                            m.mIsPC = true;
                            m.mX = targetx;
                            m.mY = targety;
                            
                            return m;
                        }
                    }
                }      
            }
        }
        
        return null;
    }
    
    private boolean IsCollinear(Point point3[])
    {
        //area of triangle
        int a = point3[0].x * (point3[1].y - point3[2].y) +
            point3[1].x * (point3[2].y - point3[0].y) +
            point3[2].x * (point3[0].y - point3[1].y);
        
        return a == 0;
    }
    
    private boolean HasPlayerWon(FMark playerMove)
    {
        FMark one = null;
        FMark two = null;
        
        for(int i = 0; i < 9; ++i)
        {
            one = mMap[i / 3][i % 3];
            
            for(int j = 0; j < 9; ++j)
            {
                two = mMap[j / 3][j % 3];
                
                if(!one.mIsPC && !two.mIsPC && one.mFlag && two.mFlag
                        && one.Different(two) && two.Different(playerMove) && one.Different(playerMove))
                {
                    
                    Point[] p3 = new Point[3];
                    p3[0] = new Point(one.mX, one.mY);
                    p3[1] = new Point(two.mX, two.mY);
                    p3[2] = new Point(playerMove.mX, playerMove.mY);
                    
                    if(IsCollinear(p3))
                    {
                        mWinningData.mFlag = true;
                        mWinningData.mIsPC = false;
                        mWinningData.mFirstPoint = new Point(Math.min(Math.min(one.mX, two.mX), playerMove.mX), 
                                                                 Math.min(Math.min(one.mY, two.mY), playerMove.mY));
                        mWinningData.mLastPoint = new Point(Math.max(Math.max(one.mX, two.mX), playerMove.mX), 
                                                                 Math.max(Math.max(one.mY, two.mY), playerMove.mY));
                        return true;
                    }
                }      
            }
        }
        
        return false;
    }
    
    private FMark Compute()
    {
        //get clear space
        Vector<FMark> clear = new Vector<FMark>();
        for(int i = 0; i < 3; ++i)
        {
            for(int j = 0; j < 3; ++j)
            {
                FMark m = mMap[i][j];
                if(!m.mFlag)
                    clear.add(m);
            }
        }
        if(clear.isEmpty())
        {
            return null;
        }
        
        //can win
        FMark winningMark = TryWin(clear);
        if(winningMark != null)
        {
            return winningMark;
        }
        
        //defend
        FMark defendingMark = TryDefend(clear);
        if(defendingMark != null)
        {
            return defendingMark;
        }
        
        //get point
        FMark target = GetHighestPointMark(clear);
        target.mIsPC = true;
        target.mFlag = true;
        
        return target;
    }
    
    private boolean ValidatePlacement(int x, int y)
    {
        FMark m = mMap[y][x];
        return !m.mFlag;
    }
    
    public FWinningData GetWinningData()
    {
        return mWinningData;
    }
    
    public Vector<FMark> UserTouch(int x, int y)
    {
        if(!ValidatePlacement(x, y))
            return new Vector<FMark>();
        
        FMark m = mMap[y][x];
        m.mFlag = true;
        m.mIsPC = false;
        
        Vector<FMark> ret = new Vector<FMark>();
        ret.add(m);
        
        ++mRound;
        
        if(HasPlayerWon(m) || mRound == 9)
        {
            //end game (player)
            return ret;
        }
        
        FMark pcMove = Compute();      
        ret.add(pcMove);
        
        if(pcMove.mX < 0|| pcMove.mY < 0)
        {
            System.out.println("" + pcMove.mX + " , " + pcMove.mY);
        }
            
        
        mMap[pcMove.mY][pcMove.mX].mFlag = true;
        mMap[pcMove.mY][pcMove.mX].mIsPC = true;
        
         ++mRound;
        
        return ret;
    }
}
