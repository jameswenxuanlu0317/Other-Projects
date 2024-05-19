import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane; 
import java.util.ArrayList; 
/**
 * This is a class Sam that extends the Player class
 * It can move and inherits other features from player class
 * it will restart the level if it reaches teachers
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sam extends Player
{
    /**
     * it inherits features from the parent class
     * it can move, see the moveCharacter method
     * it restarts the level if reaches to teacher
     */
    public void act() 
    {
        // Add your action code here.
        super.act();//inherit from parent class
        moveCharacter();//move the character
        backToStart();//restarts the level
    }


    /**
     * moves the character by key board arrows indicated
     * up=up 5
     * down=down 5
     * left=left 5
     * right=right 5
     */
    public void moveCharacter()
    {
        if(Greenfoot.isKeyDown("up"))
        {
            setLocation(getX(),getY()-5);
        }
        else if(Greenfoot.isKeyDown("down"))
        {
            setLocation(getX(),getY()+5);
        }
        else if(Greenfoot.isKeyDown("left"))
        {
            setLocation(getX()-5,getY());
        }
        else if(Greenfoot.isKeyDown("right"))
        {
            setLocation(getX()+5,getY());
        }
    }

    /**
     * checks if it reaches teacher, return true if it does, return false if it doesn't
     */
    public boolean reachTeacher()
    {
        if(isTouching(Teachers.class))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * if reaches teacher, restart the current round
     * prints out the joption pane 
     */
    public void backToStart()
    {

        if(reachTeacher())     
        {
            World world=getWorld();//get the current world
            if(world instanceof Level3)//if the current world is level 3
            {
                Greenfoot.setWorld(new Level3());//restarts level 3
            }
            else if(world instanceof Level2)//if the current world is level 2
            {
                Greenfoot.setWorld(new Level2());//restarts level 2
            }
            else if(world instanceof Space)//if the current world is level 1
            {
                Greenfoot.setWorld(new Space());//restarts level 1
            }
            JOptionPane.showMessageDialog(null,"You got caught by your enemy, automatically restarts the current round");
        }
    }



}
