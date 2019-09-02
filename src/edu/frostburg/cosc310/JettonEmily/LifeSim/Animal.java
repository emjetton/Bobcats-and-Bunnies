/*
 * Emily Jetton, COSC 310
 */
package edu.frostburg.cosc310.JettonEmily.LifeSim;

/**
 *
 * @author emilyjetton
 */
public interface Animal 
{

    /**
     *
     */
    int HEALTH = 0;
    public int move(Animal[] newSpot);
    public Animal meet(Animal newMeet);
    
}
