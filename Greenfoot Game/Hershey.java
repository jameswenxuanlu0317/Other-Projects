import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is a class called Hershey and extends the Actor class
 * this class serves as the energy boost of the main character, see class player
 * 
 * @author Lu
 * @version 100
 */
public class Hershey extends Actor
{
    /**
     * scale the image size to (50,50)
     */
    public Hershey()
    {
        GreenfootImage h=getImage();
        h.scale(50,50);
        setImage(h);
    } 
}
