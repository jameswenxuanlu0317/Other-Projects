import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * This is a class called Weapon and extends the Actor class
 * it shoots the weapon to the right
 * removes the enemies if they aren't healthy anymore
 * contains different types of weapons: elevator, eraser, and pencils as subclasses
 * 
 * @author Lu
 * @version (a version number or a date)
 */
public class Weapon extends Actor
{   
    /**
     * shoot the weapon, removes the enemy if they arent healthy 
     * removes the weapon if they reaches the barrier
     */
    public void shoot()
    {

        int ypos = getY();
        int xpos=getX()+1;

        if (ypos > 0) {
            ypos = ypos - 5;
            move(10);
            Actor rock = getOneIntersectingObject(Teachers.class);
            if(xpos>=getWorld().getWidth())
            {
                getWorld().removeObject(this);
            }
            if (rock != null) {
                // We've hit an asteroid!
                Greenfoot.playSound("ouch.mp3");
                Teachers s=(Teachers)rock;
                s.loseHealth();
                TeacherCounter c=s.getHealthLevel();//returns the health level of the object
                if(!s.isHealthy())
                {
                    getWorld().removeObject(rock);//removes the enemy
                    getWorld().removeObject(c);//removes the health counter
                }
                getWorld().removeObject(this);//removes the weapon 
            }

        }
        else {
            // I reached the top of the screen
            getWorld().removeObject(this);
        }

    }

    /**
     * returns the energy level of the player
     */
    public int score()
    {
        Space spaceWorld = (Space) getWorld();
        Counter counter = spaceWorld.getCounter();
        return counter.getScore();
    }

}
