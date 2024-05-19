import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is a class called Twyman that extends Teachers class
 * Sheridan is an enemy, he will move towards the main character and the main character will be trying to escape from him
 * has a health counter with him
 * @Author Lu
 * version 100
 */
public class Twyman extends Teachers
{
    final int INITIAL_HEALTH=30;
    private int health=INITIAL_HEALTH;
    private TeacherCounter healthCounter;
    final int PENCIL_LOSS=3;
    final int ERASER_LOSS=10;
    final int ELEVATOR_LOSS=30;
    /**
     * Scale the image to size(100,100)
     * adds a health counter
     */
    public Twyman() 
    {
        // Add your action code here.
        GreenfootImage b=getImage();
        b.scale(100,100);
        setImage(b);
        healthCounter=new TeacherCounter(getHealth());
    }
    /**
     * returns the counter
     */
    public TeacherCounter getHealthLevel()
    {
        return healthCounter;
    }
    /**
     * adds health level to the string
     */
    public String healthString()
    {
        String str="";
        return str+health;
    }

    /**
     * returns the health
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * loses health by each contants indicated when gets hit by the weapon
     */
    public void loseHealth()
    {
        if(reachedEraser())
        {
            health=health-ERASER_LOSS;
            healthCounter.decrease(ERASER_LOSS);
            Space space=(Space)getWorld();
            space.addObject(healthCounter,this.getX(),this.getY()-100);
        }
        else if(reachedPencil())
        {
            health=health-PENCIL_LOSS;
            healthCounter.decrease(PENCIL_LOSS);
            Space space=(Space)getWorld();
            space.addObject(healthCounter,this.getX(),this.getY()-100);
        }
        else if(reachedElevator())
        {
            health=health-ELEVATOR_LOSS;
            healthCounter.decrease(ELEVATOR_LOSS);
            Space space=(Space)getWorld();
            space.addObject(healthCounter,this.getX(),this.getY()-100);
        }
    }

    /**
     * move around follows the player indicated in the parameter
     */
    public void moveAround(Actor player)
    {
        turnTowards(player.getX(),player.getY());
        move(2);
        setRotation(0);
        healthCounter.move(this);//health counter moves with
        Space space=(Space)getWorld();
        space.addObject(healthCounter,this.getX(),this.getY()-100);
    }
    /**
     * loses health if it gets hit
     * if still healthy, move towards Sam(player)
     * otherwise remove this object, enemy is dead
     */
    public void act()
    {
        loseHealth();
        if(isHealthy())
        {
            Level2 spaceWorld = (Level2) getWorld();
            Sam sam=spaceWorld.getSam();;
            moveAround(sam);
        }
        else
        {
            getWorld().removeObject(this);
        }
    }

}
