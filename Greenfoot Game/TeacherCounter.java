import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is a class called TeacherCounter and extends the Actor class
 * It sets and Greenfoot image at a certain postition to indicate the energy level of the enemy
 * It can lose energy by the method decrase, which has the parameter indicates the amound of energy it wants to lose
 * It will move with the enemy, see the method move(Teachers t)
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TeacherCounter extends Actor
{
    private int health;
    /**
     * sets an image and starts with the initial energy of the parameter
     */
    public TeacherCounter(int x)
    {
        setImage(new GreenfootImage(""+x, 20, Color.WHITE, Color.BLACK));
    }

    /**
     * decrease the amount of health by the give parameter
     */
    public void decrease(int x)
    {
        health-=x;
        setImage(new GreenfootImage("" + health, 20, Color.WHITE, Color.BLACK));
    }

    /**
     * draw this object at a given position with the parameter
     */
    public void drawHealth(int x, int y)
    {
        Space space=(Space)getWorld();
        space.addObject(this,x,y);
    }

    /**
     * move the object with the enemy indicated by the parameter
     */
    public void move(Teachers t)
    {
        health=t.getHealth();//set the health equals to the enemy's health
        setLocation(t.getX(),t.getY()-100);//set the location 100 upwards of the enemy
        setImage(new GreenfootImage("" + health, 20, Color.WHITE, Color.BLACK));//set the image
    }

}
