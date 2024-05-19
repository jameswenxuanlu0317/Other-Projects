import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * this is an abstract class called Teachers and extends the Actor class
 * it serves as enemy class
 * it has a abstract methods returning the health counters of each enemy class and lose health if it got hit by weapons
 * it has a string method returns the enemy health into the string
 * it randomly moves around
 * 
 * @author Lu 
 * @version 100
 */
public abstract class Teachers extends Actor
{
    private int health;
    final int PENCIL_LOSS=3;
   
    abstract TeacherCounter getHealthLevel();//returns the health counter
    abstract void loseHealth();//loses the health of the enemy
    /**
     * returns the health level of each class
     */
    public int returnHealth(Teachers teacher)
    {
        return teacher.getHealth();
    }
    /**
     * returns the health into a string
     */
    public String healthString(Teachers teacher)
    {
        String str="";
        return str+teacher.getHealth();
    }

    /**
     * returns the health
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * returns true if it gets hit by an elevator
     */
    public boolean reachedElevator()
    {
        if(isTouching(Elevator.class))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * returns true if it gets hit by an eraser
     */
    public boolean reachedEraser()
    {
        if(isTouching(Eraser.class))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * returns true if it gets hit by a pencil
     */
    public boolean reachedPencil()
    {
        if(isTouching(Pencil.class))
        {
            return true;
        }
        else
        {
            return false;
        }
    }



    /**
     * returns true if  the enemy still remains the health level above 0
     * otherwise returns false
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
     * randomly moves around the world
     */
    public void moveAround()
    {
        move(4);
        if(Greenfoot.getRandomNumber(100)<10)//1/10 chance of turning
        {
            turn(Greenfoot.getRandomNumber(90)-45);
        }
        if(getX()<=5||getX()>=getWorld().getWidth()-5)
        {
            turn(180);
        }
        if(getY()<=5||getX()>=getWorld().getWidth()-5)
        {
            turn(180);
        }
    }
}
