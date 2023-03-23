/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author s225342
 */
public class FMark 
{
    public int mX = -1;
    public int mY = -1;
    public boolean mIsPC;
    public boolean mFlag;
    
    public boolean Different(FMark m2)
    {
        return mX != m2.mX && mY != m2.mY;
    }
}

