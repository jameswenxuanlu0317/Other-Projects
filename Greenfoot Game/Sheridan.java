import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * This is a class called Sheridan that extends Teachers class
 * Sheridan is an enemy, he will randomly move around and the main character will be trying to escape from him
 * has a health counter with him
 * @Author Lu
 * version 100
 */
public class Sheridan extends Teachers
{
    final int INITIAL_HEALTH=20;//initial health level is 20
    private int health=INITIAL_HEALTH;
    private TeacherCounter healthCounter;//a health counter for this enemy
    final int PENCIL_LOSS=3;
    final int ERASER_LOSS=10;
    final int ELEVATOR_LOSS=30;
    /**
     * scale the background image to size(100,100)
     * adds a health counter
     */
    public Sheridan()
    {
        GreenfootImage b=getImage();
        b.scale(100,100);
        setImage(b);
        healthCounter=new TeacherCounter(getHealth());
    }

    /**
     * returns the healthCounter
     */
    public TeacherCounter getHealthLevel()
    {
        return healthCounter;
    }

    /**
     * returns the string of heath values
     */
    public String healthString()
    {
        String str="";
        return str+health;
    }

    /**
     * returns health value
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * loses the health by constant if it got hit by the weapons
     * adds a new health counter to follow the enemy each time
     */
    public void loseHealth()
    {
        if(reachedEraser())
        {
            health=health-ERASER_LOSS;
            healthCounter.decrease(ERASER_LOSS);//decrease the health level
            Space space=(Space)getWorld();
            space.addObject(healthCounter,this.getX(),this.getY()-100);//adds the healthcounter
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
     * returns true if there are still hershey kisses left
     * otherwise return false
     */
    public boolean isHealthy()
    {
        if(getHealth()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * move around the world, inherits the moveAround method from parent class
     * remove the counter and add the counter to the current spot
     */
    public void moveAround()
    {
        super.moveAround();
        healthCounter.move(this);
        Space space=(Space)getWorld();
        space.addObject(healthCounter,this.getX(),this.getY()-100);
    }

    /**
     * check if it gets hit by the weapon and lose health accordingly
     * if the enemy is healthy, keep moving around
     * otherwise remove the object , enemy is dead
     */
    public void act() 
    {
        loseHealth();       
        if(isHealthy())
        {
            moveAround();
        }
        else
        {
            getWorld().removeObject(this);
        }

    }    
}

