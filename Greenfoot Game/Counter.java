import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * This is a class called Counter and extends the Actor class
 * It sets and Greenfoot image at a certain postition to indicate the energy level of the main character
 * It can add/lose energy by the method bumpCount, which has the parameter indicates the amound of energy it wants to add/lose
 * 
 * @author Lu
 * @version 100
 */
public class Counter  extends Actor
{
    private int totalCount = 0;//initial energy
    /**
     * sets an image and starts with the initial energy of 0
     */
    public Counter()
    {
        setImage(new GreenfootImage("0", 20, Color.WHITE, Color.BLACK));
    }

    /**
     * Increase the total amount displayed on the counter, by a given amount.
     */
    public void bumpCount(int amount)
    {
        totalCount += amount;//increase the energy by the amount indicated
        setImage(new GreenfootImage("" + totalCount, 20, Color.WHITE, Color.BLACK));    
    }

    /**
     * returns the energy level of the character
     */
    public int getScore()
    {
        return totalCount;
    }
}
