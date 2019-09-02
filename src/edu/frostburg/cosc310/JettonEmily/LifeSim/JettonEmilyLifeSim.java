/*
 * Emily Jetton
 * COSC 310
 */
package edu.frostburg.cosc310.JettonEmily.LifeSim;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author emilyjetton
 */
public class JettonEmilyLifeSim {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Animal [] Array = new Animal[10000];
        fillHabitat(Array);                      //fills with animals or null
        runSim(Array);                           //runs simulation
       
    }
   
    /**
     * Fills habitat with a predefined number of animals or null
     * @param Habitat 
     */
    public static void fillHabitat(Animal[] Habitat)
    {
        int bunnyNum = 100;
        int bobNum = 10;
        boolean g = true;
        for(int i = 0; i< Habitat.length; i++)
        {
            Habitat[i] = null;
        }
        
        for(int i = 0; i < bunnyNum ; i++)
        {  
           
           while(g == true)
           {
               Bunny n = new Bunny();
               int pos = n.move(Habitat);
               if(pos < Habitat.length && pos >= 0 && Habitat[pos] == null)
               {
                   Habitat[pos]= n;
                   g = false;
               }
               else
                   pos = n.move(Habitat);
                   
           }
           g = true;
        }
        
        
        for(int i = 0; i < bobNum ; i++)
        {
           while(g == true)
           {
                Random rand = new Random();        
                int rand1= rand.nextInt(100); 
                
                Bobcat n = new Bobcat();
                int pos = n.move(Habitat);
                int newSpot = pos+rand1;
                
                if(newSpot<Habitat.length && newSpot>=0 && Habitat[newSpot] == null)
                {
                   
                        Habitat[newSpot]= n;
                        g = false;
                }
                else
                    pos = n.move(Habitat); 
           }
           g = true;
        }
        
    
    }
    /**
     * Runs simulation on habitat. 
     * @param habitat 
     */
    public static void runSim(Animal [] habitat)
    { 
        
        int round = 0;
        boolean go = true;
        while (go)
        {  
            if (check(habitat, round,go) == false)
                {
                    go = false;
                                 
                }
            
            check(habitat, round,go);
            round = round+1;
            
            for(int current = 0; current < habitat.length; current++)
                {
                    if(habitat[current] instanceof Bobcat)
                    {
                        int randSpot = habitat[current].move(habitat);
                        int newSpot = current+randSpot;
                        if(newSpot>0 && newSpot<habitat.length)
                        {
                            if(habitat[newSpot] instanceof Bobcat )
                            {
                                
                                if (check(habitat, round,go) == false)//=======
                                {
                                    go = false;
                                }
                                else 
                                {
                                    findSpot(habitat, current, newSpot,round);
                                }
                                
                            }
                        
                            else if (habitat[newSpot] instanceof Bunny)
                            {
                                Animal bob = habitat[current].meet(habitat[newSpot]);
                                habitat[newSpot]= bob;
                                
                            }
                            else 
                            {
                            habitat[newSpot] = habitat[current];
                            habitat[current] = null;
                            }
                        }
                    }
                } 
       for(int current = 0; current < habitat.length; current++)
            {
                    if(habitat[current] instanceof Bunny)
                    {
                        int newSpot = habitat[current].move(habitat);
                        
                        if(newSpot>0 && newSpot<habitat.length)
                        {
                  
                            if(habitat[newSpot] instanceof Bobcat )
                            {
                                Animal bun = habitat[current].meet(habitat[newSpot]);
                                habitat[current]= habitat[current];
                            }
                            else if (habitat[newSpot] instanceof Bunny)
                            {
                                if (check(habitat, round,go) != false)
                                {
                                    findSpot(habitat, current, newSpot,round);
                                }

                                 
                            }
                            else
                                habitat[newSpot] = habitat[current];
                                habitat[current] = null;
                        }
                    
                    } 
                
            }
        go = check(habitat, round,go);
        display(habitat, round);
        }
        
         System.out.println("The LifeSim has ended");
    }  
    
    /**
     * Takes in the habitat, and uses the new number produced to find a spot, looping through 
     * linearly if a spot isn't filled randomly after 20 tries. Also catches if the 
     * habitat is full.
     * @param habitat
     * @param newSpot
     * @param current
     * @param round
     * @return okay
     */
    public static  boolean findSpot(Animal [] habitat, int newSpot, int current, int round)
    {
       boolean okay = true;
        int nullCount = 0;
            for(int nCounter = 0; nCounter< habitat.length; nCounter++)
            {
                if (habitat[nCounter] == null)
                {
                    nullCount++;
                }
            }
            if (nullCount == 0)
            {
              okay = false;
              return okay;
            }
       
        
       if(habitat[newSpot] instanceof Bobcat )
        {
            Animal newBob = habitat[current].meet(habitat[newSpot]);
            boolean g = true;
            int count = 1;
            while(g)
            {
                Random rand3= new Random();
                int pos = habitat[current].move(habitat);
                int rand4 = rand3.nextInt(habitat.length);             //need array var
                pos = pos+rand4;
                
                if(pos<0 )
                {
                    pos = pos+1;
                }
                if(pos>= habitat.length)
                {
                    pos = pos-1;
                }
                
                if(habitat[pos] == null)
                {   
                    habitat[pos]= newBob;
                    g = false;
                }
                else
                    pos = habitat[current].move(habitat); 
                
                if (count >= 20)
                {
                    for(int search = 0; search < habitat.length; search++)

                    {
                       if(habitat[search] == null)
                       {
                           habitat[search]= newBob;
                           g = false;
                       }
                    }
                    g = false;
                }
                count++;
            }
            count = 0;
         }
        
       if (habitat[newSpot] instanceof Bunny)
        {
            for(int k = 0; k <= 1 ;k++)
            {
                int count = 1;
                boolean g = true;
                while(g== true)
                {
                    int pos= habitat[current].move(habitat);
                    if(habitat[pos] == null)
                    {
                        Animal bun = habitat[current].meet(habitat[newSpot]);
                        habitat[pos]= bun;
                        g = false;
                    }
                    else
                        pos = habitat[current].move(habitat); 
                    
                    if (count >= 20)
                    {
                        for(int search = 0; search < habitat.length; search++)              //doesnt break when full
                        {
                            if(habitat[search] == null)
                            {
                                Animal bun = habitat[current].meet(habitat[newSpot]);
                                habitat[pos]= bun;
                                g = false;
                            }
                        }
                        g = false;                                                          //didnt find anything open
                        
                    }
                count++;
                }
                count = 0;
            }  
        }
      return okay;                         
    }
       
    /**
     * Prints out the amount of animals and year
     * @param hab
     * @param round 
     */
    
    public static void display(Animal [] hab, int round)
    {
        int bunNum = 0;
        int bobNum = 0;
    
           for(int i = 0; i < hab.length; i++)
            {
                  if(hab[i] instanceof Bunny)
                  {     
                      bunNum= bunNum+1;
                  }  
                  if(hab[i] instanceof Bobcat)
                  {
                      bobNum= bobNum+1;
                  }
            }
           
        System.out.println("At the end of year "+round+" your remaining bunnies are: "+bunNum+". Your remaining bobcats are: "+bobNum);
    }
    
    /**
     * Checks for dead animals or if the habitat is full
     * @param hab
     * @param round
     * @param g
     * @return whoops
     */
    public  static boolean check (Animal [] hab, int round, boolean g)
    {
        int nullCount = 0;
        int bunNum= 0;
        int bobNum =0;
        boolean whoops = true;
                
        for(int i = 0; i < hab.length; i++)
            { 
                if(hab[i] == null)
                {
                    nullCount++;
                }
                  if(hab[i] instanceof Bobcat)
                  {
                      if(hab[i].HEALTH < 0)
                      {
                         hab[i] = null; 
                      }
                  }
                 
                  if(hab[i] instanceof Bunny)
                  {     
                      bunNum= bunNum+1;
                  }  
                  if(hab[i] instanceof Bobcat)
                  {
                      bobNum= bobNum+1;
                  }
            }
      
        if(bobNum == 0)
        {
            whoops = false;
        }
        if (bunNum == 0) {
            whoops = false;
        }
         if(nullCount == 0)
        {
            whoops = false;          
        }
       return whoops;           
    }
   
    
}
