import greenfoot.*;
import java.util.*; 
import javax.swing.JOptionPane; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * This is a class called Space, it extends the World class and implements the Level class
 * this class displays level one of the game
 * in this level, there will be one enemy: Mr. Sheridan, and will have a counter to keep count of the health of this enemy
 * He will randomly move and if the main character reaches them, they are dead and will restart this round
 * Prints out an instruction at the start
 * there will be a counter at the top left corner counts the energy level of the character
 * if energy level reaches certain number(default is 100), you get pass this level and proceeds to the next level
 * 
 * @author Lu 
 * @version 100
 */
public class Space  extends World implements Level
{
    private int numTeachers=getObjects(Sheridan.class).size();//records the number of Sheridan in the world
    private int numHersheys=getObjects(Hershey.class).size();//records the number of hershey kisses in the world
    private Counter theCounter;//a counter for the energy level
    private Sam theRocket;//the character we are using, our friend sam starr
    private Sheridan theAsteroid;//the enemy in this round
    private Hershey theHershey;//the energy boost, hershey kisses
    private int initialTeacher=3;//initial amount of enemy is 3
    private int initialHershey=20;//initial number of hershey kisses is 20
    final int WORLD_X=1000;//the x coordinate size of the world
    final int WORLD_Y=600;//the y coordinate size of the world
    final int NEXT_ROUND=100;//amount of energy you need to gain to get to next round
    static boolean instruction=true;//allows to print boolean at the start of the game
    /**
     * Constructor for objects of class Space. Create a world with 600x400 cells with a cell size of 1x1 pixels
     * Add sam starr into the game at the position of (300,540)
     * Add a counter to record energy levels of Sam at the position of (10,5)
     */
    public Space()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1);
        theRocket=new Sam();
        addObject(theRocket, 300, 540);
        theCounter = new Counter();
        addObject(theCounter, 10, 5);
    }
    /**
     * Returns the main character Sam
     */
    public Sam getSam()
    {
        return theRocket;
    }

    /**
     * Returns the energy counter
     */
    public Counter getCounter()
    {
        return theCounter;
    }


    /**
     * returns the number of hershey kisses
     */
    public int countHershey()
    {
        return numHersheys;
    }

    /**
     * return the number of Mr. Sheridan(enemy)
     */
    public int countTeacher()
    {
        return numTeachers;
    }

    /**
     * Add enemies into the class with a parameter that indicates how many enemies it supposed to add in
     */
    public void addSheridan(int x)
    {
        if(countTeacher()<x)
        {
            theAsteroid=new Sheridan();
            addObject(theAsteroid, Greenfoot.getRandomNumber(WORLD_X), Greenfoot.getRandomNumber(WORLD_Y));
            numTeachers++;
        }
    }

    /**
     * Add hershey kisses into the class with a parameter that indicates how many hershey kisses it supposed to add in
     */
    public void addHershey(int x)
    {
        if(countHershey()<x)
        {            
            theHershey=new Hershey();            
            addObject(theHershey, Greenfoot.getRandomNumber(WORLD_X), Greenfoot.getRandomNumber(WORLD_Y));
            numHersheys++;        
        }
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
     * if there aren't hershey kisses left, add more enemies indicate by the parameter
     */
    public void rounds(int round)
    {
        if(!isHersheyLeft())     
        {
            numTeachers=getObjects(Sheridan.class).size();
            int x=round*5;
            addSheridan(initialTeacher+x);
            addHershey(initialHershey);
        }
    }

    /**
     * prints out the instructions at the start of the game
     */
    public void printInstruction()
    {
        String s="this game has 3 levels." +"\n"
            + "In level one, the goal is to gain as much of energy as you can by eating hershey kisses. "  +"\n"
            +"the energy counter would be at the top left corner. " +"\n"
            +" there will be Mr.Sheridan showing up as enemy, they will randomly move around, if you reach them, you will automatically restart the game." +"\n"
            +"They have an energy level of 20"+"\n"
            +" if you reached energy level of 100, you won this round and will assign to the next round";
        String y="You can shoot 3 types of weapons: Elevator, eraser or pencil"+"\n"
            +"You need energy of 5 to shoot pencil, 10 to shoot eraser, 30 to shoot elevator"+"\n"
            +"Pencil will destroy 3 energy of the enemy, eraser will destroy 10, elevator will destroy 30"+"\n"
            +"Press 1 to shoot pencil, 2 to shoot Eraser, 3 to shoot elevator";
        JOptionPane.showMessageDialog(null,"Welcome to this game!");
        JOptionPane.showMessageDialog(null,s);
        JOptionPane.showMessageDialog(null,y);
        JOptionPane.showMessageDialog(null,"Here we go, click ok to start level one!");
    }

    /**
     * Adding instructions if it's at the start, once the game goes on, set boolean instruction to false
     */
    public void addInstructions()
    {

        if(instruction)
        {
            printInstruction();
            instruction=false;            
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
        if(theCounter.getScore()<NEXT_ROUND)
        {
            addSheridan(initialTeacher);
            addHershey(initialHershey);
            rounds(1);
        }
        else
        {
            Greenfoot.setWorld(new Level2());
        }
    }
}
