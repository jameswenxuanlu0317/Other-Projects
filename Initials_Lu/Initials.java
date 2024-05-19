import kareltherobot.*;

public class Initials implements RobotTask
{
    /**
     * Starting at 1,2 make a letter H.  See the test file for shape.
     */
    public void task()
    {
        World.showSpeedControl(true);
        SmartBot lu= new SmartBot(5, 1, East, 200);
        World.setSize(35,120);
        lu.makeinitials();
       
    }


    }
