import kareltherobot.*;
/**
 * Example program that shows inheritance
 */
public class SmartBot extends UrRobot
{
    /**
     * constructor for SmartBot
     */
    public SmartBot(int street, int avenue, Direction facing, int beepers)
    {
        super(street, avenue, facing, beepers);

    }

    /**
     * this makes the robot turn right
     */
    public void turnRight(){
        this.turnLeft();
        this.turnLeft();
        this.turnLeft();
    }

    /**
     * this puts 5 beepers
     */
    public void goput5(){
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();    
    }

    /**
     * this turns around
     */
    public void Uturn(){
        this.turnLeft();
        this.turnLeft();
    } 

    public void go3(){
        this.move();
        this.move();
        this.move();
    }

    public void drop2(){
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
    }

    public void drop3(){
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
    }

    /**
     * this makes the robot move 4 blocks
     */
    public void moveFour()
    {
        this.move();
        this.move();
        this.move();
        this.move();
    }

    /**
     * this makes the robot turn around
     */
    public void turnAround()
    {
        this.turnLeft();
        this.turnLeft();
        this.turnLeft();
    }

    /**
     * this makes the robot goes downright when facing east
     */
    public void downrightEast()
    {
        this.move();
        this.turnRight();
        this.move();
    }

    /**
     * this makes the robot goes downright when facing south
     */
    public void downrightSouth()
    {
        this.move();
        this.turnLeft();
        this.move();
    }

    /**
     * this makes the robot goes upright when facing east
     */
    public void uprightEast()
    {
        this.move();
        this.turnLeft();
        this.move();
    }

    /**
     * this makes the robot goes upright when facing north
     */
    public void uprightNorth()
    {
        this.move();
        this.turnRight();
        this.move();
    }

    /**
     * This drops 9 beepers for the diagnol parts of letter J
     */
    public void diagonalJ()
    {
        this.putBeeper();
        this.downrightEast();
        this.putBeeper();
        this.downrightSouth();
        this.putBeeper();
        this.downrightEast();
        this.putBeeper();
        this.downrightSouth();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.uprightEast();
        this.putBeeper();
        this.uprightNorth();
        this.putBeeper();
        this.uprightEast();
        this.putBeeper();
        this.move();
    }

    /**
     * This drops 17 beepers for vertical parts of letter J
     */
    public void verticalJ()
    {
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
    }

    /**
     * this drops 20 beepers for the vertical part of letter W
     */
    public void verticalW()
    {
        this.verticalJ();
        this.putBeeper();
        this.move();
        this.putBeeper();
        this.move();
        this.putBeeper();
    }

    /**
     * this drops 23 beepers for the diagnal part of letter W
     */
    public void diagnalW()
    {
        this.downrightSouth();
        this.putBeeper();
        this.uprightEast();
        this.putBeeper();
        this.uprightNorth();
        this.putBeeper();
        this.uprightEast();
        this.putBeeper();
        this.uprightNorth();
        this.putBeeper();
        this.uprightEast();
        this.putBeeper();
        this.uprightNorth();
        this.putBeeper();
        this.uprightEast();
        this.putBeeper();
        this.uprightNorth();
        this.putBeeper();
        this.uprightEast();
        this.putBeeper();
        this.uprightNorth();
        this.putBeeper();
        this.uprightEast();
        this.putBeeper();
        this.turnRight();
        this.downrightEast();
        this.putBeeper();
        this.downrightSouth();
        this.putBeeper();
        this.downrightEast();
        this.putBeeper();
        this.downrightSouth();
        this.putBeeper();
        this.downrightEast();
        this.putBeeper();
        this.downrightSouth();
        this.putBeeper();
        this.downrightEast();
        this.putBeeper();
        this.downrightSouth();
        this.putBeeper();
        this.downrightEast();
        this.putBeeper();
        this.downrightSouth();
        this.putBeeper();
        this.downrightEast();
        this.putBeeper();
        this.turnLeft();
        this.uprightEast();
    }

    /**
     * this drops 20 vertical beepers for letter L
     */
    public void verticalL()
    {
        this.verticalW();
        this.turnLeft();
    }

    /**
     * this drops 17 horizontal beepers for letter L
     */
    public void horiL()
    {
        this.verticalJ();
        this.move();
    }

    /**
     * this procedure constructs letter J
     */
    public void letterJ()
    {
        this.diagonalJ();
        this.verticalJ();
    }

    /**
     * this procedure constructs letter W
     */
    public void letterW()
    {
        this.verticalW();
        this.diagnalW();
        this.verticalW();
    }

    /**
     * this procedure constructs letter L
     */
    public void letterL()
    {
        this.verticalL();
        this.horiL();
    }
    
    public void makeinitials()
    {
        this.letterJ();
        this.turnRight();
        this.moveFour();
        this.turnRight();
        this.letterW();
        this.turnRight();
        this.moveFour();
        this.turnRight();
        this.letterL();
    }
}

