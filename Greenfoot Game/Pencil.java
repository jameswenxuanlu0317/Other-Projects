import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *This is a class called Elevator and extends the Weapon class
 * it cost 5 energy to shoot this weapon
 * @author Lu
 * @version 100
 */
public class Pencil extends Weapon
{
    private Player sam;
    final int ACTIVATE=5;
    final int COST=5;
    /**
     * scale the image to the size(25,30)
     */
    public Pencil(Player sam)
    {
        this.sam=sam;
        GreenfootImage pencil=getImage();
        pencil.scale(25,30);
        setImage(pencil);
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

    /**
     *  shoot the weapon, method inherited from the parent class
     */
    public void act() 
    {
        // Add your action code here.
        shoot();
    }

}
