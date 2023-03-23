/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author s225342
 */
public enum Mobile {
    
    Samsung(400), Nokia(250), Motorola(325);
    
    int price;
    
    Mobile(int i) { price = i;}
    
    int showPrice()
    {
        return price;
    }
    
}


