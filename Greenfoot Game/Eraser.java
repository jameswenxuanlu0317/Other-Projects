import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *This is a class called Elevator and extends the Weapon class
 * it cost 15 energy to shoot this weapon
 * @author Lu
 * @version 100
 */
public class Eraser extends Weapon
{
    private Player sam;
    final int ACTIVATE=15;
    final int COST=15;
    /**
     * scale the image to the size(25,30)
     */
    public Eraser(Player sam)
    {
        this.sam=sam;
        GreenfootImage eraser=getImage();
        eraser.scale(25,30);
        setImage(eraser);
    }

    /**
     * shoot the weapon, method inherited from the parent class
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
