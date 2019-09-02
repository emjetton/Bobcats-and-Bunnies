/*Emily Jetton, COSC 310
 * 
 */
package edu.frostburg.cosc310.JettonEmily.LifeSim; 

import java.util.Random;

/**
 *
 * @author emilyjetton
 */
public class Bobcat implements Animal 
{   
 
    int HEALTH = 20; 
    
    public int move(Animal[] newSpot)
    {
         Random rand = new Random();             //going to return a random number -1 or 1
         Boolean rand1= rand.nextBoolean();
         int n= 0;
         if( rand1 == true)
         {
             n = 1;
         }
         if(rand1 == false)
         {
             n= -1;
         }
         return n;
    }

    public Animal meet(Animal newMeet) 
    {
        
         if(newMeet instanceof Bobcat)
         {
           Bobcat n = new Bobcat();
           return n;
         }
         else if (newMeet instanceof Bunny) 
         {
           this.HEALTH = 20;
           return null;
         }
         else
         {
            this.HEALTH--;
            return null;
         }
         
    }
}
