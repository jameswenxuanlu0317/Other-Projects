import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * this is a class called player and extends the Actor class
 * It can shoot differenct types of weapons and eat Hershey kisses
 * it will lose energy everytime it shoots a weapon
 * the amount of energy loss is indicated by the constants
 * 
 * @author Lu
 */
public class Player extends Actor
{
    private int shotTimer;//the shottimer that delays each shot
    final int PENCIL_COST=5;//cost of shooting each pencil 
    final int ERASER_COST=15;//cost of shooting each eraser
    final int ELEVATOR_COST=30;//cost of shooting each elevator
    final int HERSHEY_VALUE=5;
    /**
     * scale the image of the player to the size(100,100)
     */
    public Player()
    {
        GreenfootImage sam=getImage();
        sam.scale(100,100);
        setImage(sam);
    }

    /**
     * Shoot weapons and eat hershey kisses
     */
    public void act() 
    {
        shoot();
        eat();
    }

    /**
     * shoot the weapon, delay each shot.
     * if press key board 1, shoot pencils
     * if press 2, shoot eraser
     * if press 3, shoot elevator
     * check if has enough energy to shoot each weapons
     */
    public void shoot()
    {
        if (shotTimer > 0) {
            shotTimer = shotTimer - 1;
        }
        else if (Greenfoot.isKeyDown("1")) {
            if(score()>=PENCIL_COST)
            {
                createPencil();
            }
        }
        else if(Greenfoot.isKeyDown("2"))
        {
            if(score()>=ERASER_COST)
            {
                createEraser();
            }
        }
        else if(Greenfoot.isKeyDown("3"))
        {
            if(score()>=ELEVATOR_COST)
            {
                createElevator(); 
            }
        }
    }

    /**
     * creates the weapon pencil, set the shot timer to 10 to delay each shot
     * character loses energy
     */
    public void createPencil()
    {        
        Pencil pencil=new Pencil(this);
        getWorld().addObject(pencil, getX(), getY());
        shotTimer = 10; // delay next shot
        loseEnergy(PENCIL_COST);
    }

    /**
     * creates the weapon eraser, set the shot timer to 10 to delay each shot
     * character loses energy
     */
    public void createEraser()
    {
        Eraser eraser = new Eraser(this);
        getWorld().addObject(eraser, getX(), getY());
        shotTimer = 10; // delay next shot
        loseEnergy(ERASER_COST);
    }

    /**
     * creates the weapon elevator, set the shot timer to 10 to delay each shot
     * character loses energy
     */
    public void createElevator()
    {
        Elevator elevator=new Elevator(this);
        elevator.setRotation(this.getRotation());
        getWorld().addObject(elevator, getX(), getY());
        shotTimer = 10; // delay next shot
        loseEnergy(ELEVATOR_COST);
    }

    /**
     * returns the rotation of the character
     */
    public int samRotation()
    {
        return getRotation();
    }

    /**
     * eat the hershey kisses
     * if the object reaches hershey, remove them from the world
     * play a sound
     * gain energy
     */
    public void eat()
    {
        if(isTouching(Hershey.class))
        {
            removeTouching(Hershey.class);
            Greenfoot.playSound("eating.wav");
            gainEnergy();
        }

    }

    /**
     * lose the energy by an indicated amount in the parameter
     * uses the counter in the world
     */
    public void loseEnergy(int x)
    {
        Space spaceWorld=(Space) getWorld();
        Counter counter=spaceWorld.getCounter();//calls the counter
        counter.bumpCount(x*-1);//lose energy
    }

    /**
     * gain energy by the constant hershey values
     */
    public void gainEnergy()
    {
        Space spaceWorld = (Space) getWorld();
        Counter counter = spaceWorld.getCounter();
        counter.bumpCount(HERSHEY_VALUE);
    }

    /**
     * returns the energy of the player
     * calls the counter in the world
     */
    public int score()
    {
        Space spaceWorld = (Space) getWorld();
        Counter counter = spaceWorld.getCounter();
        return counter.getScore();
    }

}
