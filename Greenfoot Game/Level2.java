import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;
/**
 * This is the level 2 class, it extends the Space class and implements the Level class
 * there will be a new enemy: Charlie Twyman, initial amount of enemy would be 1, and as you finished eating all the hershey kisses, 
 * there will be one more enemy added
 * he likes to follow you around, if you reaches them, you will be dead and automatically restarts this level
 * if you reaches certain energy level(default is 100), you get passed this level and proceeds to the third level
 * @author Lu 
 * @version 100
 */
public class Level2 extends Space implements Level
{
    private int initialTwyman=2;
    private int initialHershey=15;
    private int level=2;
    private int numTwyman=getObjects(Twyman.class).size();
    private int numHersheys=getObjects(Hershey.class).size();
    private Counter theCounter;
    private Sam theRocket;
    private Twyman theAsteroid;
    private Hershey theHershey;
    static boolean twoInstruction=true;
    /**
     * Constructor for objects of class Level1.
     * scale the background image to the size of (1000,600)
     */
    public Level2()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        GreenfootImage k = getBackground();
        k.scale(1000,600);
        setBackground(k);
    }

    /**
     * returns the counter, inherits and calls super from the Space class
     */
    public Counter getCounter()
    {
        return super.getCounter();
    }

    /**
     * Count the number of Charlie Twyman(enemy) in the world 
     */
    public int countTwyman()
    {
        return numTwyman;
    }

    /**
     * Add enemies into the class with a parameter that indicates how many enemies it supposed to add in
     */
    public void addTwyman(int x)
    {
        if(countTwyman()<x)
        {
            theAsteroid=new Twyman();
            addObject(theAsteroid, Greenfoot.getRandomNumber(WORLD_X), Greenfoot.getRandomNumber(WORLD_Y));
            // healthCount=new TeacherCounter();
            numTwyman++;
        }
    }

    /**
     * if there aren't hershey kisses left, add more enemies indicate by the parameter
     */
    public void rounds(int round)
    {
        if(!isHersheyLeft())//the method from the parent class   
        {
            numTwyman=getObjects(Twyman.class).size();
            int x=round*5;
            addTwyman(initialTwyman+x);
            addHershey(initialHershey);//a method from the parent class
        }
    }

    /**
     * Print out the instructions of the game in a Joption Pane
     */
    public void printInstructions()
    {
        String s="In this level, we are introducing you a new friend(enemy): Mr. Charlie Twyman"+"\n"
        +"Twyman likes to follow people around, and once he reaches you, you are dead, and will automatically go back to the first level"+"\n"
        +"Twyman is a tougher man. He has a energy of 30, he will follow you until he dies. Please be mentally prepared"+"\n"
        +"To pass this level, you need to reach 100 energy, click ok to continue";
        JOptionPane.showMessageDialog(null,"Welcome to Level 2");
        JOptionPane.showMessageDialog(null,s);
    }
    /**
     * Adding instructions at the start, then set the boolean twoInstruction to false to prevent it reprints as the game progresses
     */
    public void addInstructions()
    {
        
        if(twoInstruction)
        {
            printInstructions();
            twoInstruction=false;            
        }
    }
    

    /**
     * Add instructions at the start
     * if energy is below the energy required for next round
     * keep adding enemies and energy boosts
     * add 1 more enemy each time when hershey kisses are eaten by the main character
     * if the score reaches the goal, game goes to the next round
     */
    public void act()
    {
        addInstructions();
        if(getCounter().getScore()<NEXT_ROUND)
        {
            addTwyman(initialTwyman);
            addHershey(initialHershey);        
            rounds(1);
        }
        else
        {
            Greenfoot.setWorld(new Level3());
        }
    }
}
