/*
 * Emily Jetton, COSC 310
 */
package edu.frostburg.cosc310.JettonEmily.LifeSim;

import java.util.Random;

/**
 *
 * @author emilyjetton
 */
public class Bunny implements Animal
{
     public int move(Animal[] habitat)
    {
       Random rand = new Random();     //returns a random number (0-10000) to move the bunny. make sure bunny is still in      
       int rand1= rand.nextInt(habitat.length);    //need to change
       return rand1;                                        
    }

    public Animal meet(Animal newMeet) 
    {
         if(newMeet instanceof Bobcat)
         {
           return null; 
         }
         else
         {
             Bunny n = new Bunny();
             return n;
         }
    }   
}
