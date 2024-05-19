import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;
/**
 * this is Level3 class that extends Level2 and implements the interface level
 * In this class, there will be both enemies exist in this level
 * In order to win, you need to reach energy level of 50, and kill all the enemies existing in the game
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level3 extends Level2 implements Level
{
    private int initialSheridan=1;
    private int initialTwyman=1;
    private int initialTeacher=initialSheridan+initialTwyman;//initial total amount of enemies
    private int initialHershey=15;
    private int level=3;    
    private int numTwyman=getObjects(Twyman.class).size();
    private int numSheridan=getObjects(Sheridan.class).size();
    private int numEnemy=numTwyman+numSheridan;//total amount of enemies
    private int numHersheys=getObjects(Hershey.class).size();
    private Counter theCounter;
    private Sam sam;
    private Sheridan sheridan;
    private Twyman twyman;
    private Hershey hershey;
    final int NEXT_ROUND=50;
    static boolean threeInstruction=true;
    /**
     * Constructor for objects of class Level2.
     * scale the background image to the size of (1000,600)
     * 
     */
    public Level3()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.        
        GreenfootImage k = getBackground();
        k.scale(1000,600);
        setBackground(k);
    }

    /**
     * returns the number of hershey kisses
     */
    public int countHershey()
    {
        return numHersheys;
    }

    /**
     * returns the counter inherited from the parent class
     */
    public Counter getCounter()
    {
        return super.getCounter();
    }

    /**
     * returns the number of Charlie Twyman
     */
    public int countTwyman()
    {
        return numTwyman;
    }

    /**
     * returns the number Mr. Sheridan
     */
    public int countSheridan()
    {
        return numSheridan;
    }

    /**
     * returns the number of total enemies
     */
    public int countEnemy()
    {
        return numEnemy;
    }

    /**
     * Return false if the number of hershey kisses is 0, otherwise return true
     */
    public boolean isHersheyLeft()
    {
        if(getObjects(Hershey.class).size()==0)
        {
            numHersheys=0;
            return false;
        }
        else
        {
            return true;
        }

    }

    /**
     * if there aren't hershey kisses left, add more number enemies indicate by the parameter
     */
    public void rounds(int round)
    {
        if(!isHersheyLeft())     
        {            
            int x=round*5;
            numSheridan=getObjects(Sheridan.class).size();
            numTwyman=getObjects(Twyman.class).size();
            numHersheys=getObjects(Hershey.class).size();
            addSheridan(initialSheridan+x);//Adds enemy Mr.Sheridan
            addTwyman(initialTwyman+x);//Adds enemy Charlie Twyman
            addHershey(initialHershey);
        }
    }

    /**
     * Add enemy Charlie Twyman to the game indicated by the parameter
     */
    public void addTwyman(int x)
    {
        if(countTwyman()<x)
        {
            twyman=new Twyman();
            addObject(twyman, Greenfoot.getRandomNumber(WORLD_X), Greenfoot.getRandomNumber(WORLD_Y));
            // healthCount=new TeacherCounter();
            numTwyman++;
        }
    }

    /**
     * Add hershey kisses into the class with a parameter that indicates how many hershey kisses it supposed to add in
     */
    public void addHershey(int x)
    {
        if(countHershey()<x)
        {            
            hershey=new Hershey();            
            addObject(hershey, Greenfoot.getRandomNumber(WORLD_X), Greenfoot.getRandomNumber(WORLD_Y));
            numHersheys++;        
        }
    }

    /**
     * Print out the instructions to the game
     */
    public void printInstructions()
    {
        String s="In this level, we are adding both enemies"+"\n"
            +"at the start, there will be only 1 Twyman and 2 Sheridans"+"\n"
            +"each time you finished all the hershey kisses, one more of each enemy will show up"+"\n"
            +"you are one step away from winning the whole game, you need to kill all the enemies and reach energy of 50, click ok to continue";
        JOptionPane.showMessageDialog(null,"Welcome to Level 3");
        JOptionPane.showMessageDialog(null,s);
    }

    /**
     * Print out the end game message after you won
     */
    public void printEnd()
    {
        JOptionPane.showMessageDialog(null,"Congrats! You won the game");
    }
    /**
     * Adding instructions if it's at the start, once the game goes on, set boolean instruction to false
     */
    public void addInstructions()
    {

        if(threeInstruction)
        {
            printInstructions();
            threeInstruction=false;            
        }
    }

    /**
     * Add instructions at the start
     * if energy is below the energy required for next round
     * keep adding enemies and energy boosts
     * add 1 more enemy each time when hershey kisses are eaten by the main character
     * if the score reaches the goal, prints out end game message and ends the game
     */

    public void act()
    {
        addInstructions();
        if(getCounter().getScore()<NEXT_ROUND)
        {
            addSheridan(initialSheridan);
            addTwyman(initialTwyman);
            addHershey(initialHershey);
            rounds(1);
        }
        else 
        {
            if(getObjects(Teachers.class).size()<=0)
            {
                printEnd();
                Greenfoot.stop();
            }
        }

    }

}
