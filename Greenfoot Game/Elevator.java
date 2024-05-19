import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * This is a class called Elevator and extends the Weapon class
 * it cost 30 energy to shoot this weapon
 * @author Lu
 * @version 100
 */
public class Elevator extends Weapon
{
    private Player sam;//the player
    final int COST=30;
    /**
     * scale the image to the size(50,70)
     */
    public Elevator(Player sam)
    {
        this.sam=sam;
        GreenfootImage elevator=getImage();
        elevator.scale(50,70);
        setImage(elevator);
    }
    
    /**
     * shoot the weapon, inherits from the parent class
     */
    public void act() 
    {
        // Add your action code here.
        shoot();
    }

    /**
     * lose energy by the constant if it shoots the weapon
     */
    public void loseEnergy()
    {
        Space spaceWorld = (Space) getWorld();
        Counter counter = spaceWorld.getCounter();
        counter.bumpCount(COST*-1);
    }
}
