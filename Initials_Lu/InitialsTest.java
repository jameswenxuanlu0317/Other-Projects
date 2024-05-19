import kareltherobot.*;
import org.junit.*;

public class InitialsTest extends KJRTest 
{

    @Before
    public void setUp()
    {   
        World.reset();
        World.setVisible(true);
        Initials lh = new Initials();
        lh.task();
    }

    @Test
    public void initials()
    { 
    }

    @Test
    public void Test()
    {
    }
}

