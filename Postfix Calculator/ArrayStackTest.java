import org.junit.*;

public class ArrayStackTest extends junit.framework.TestCase {
    int count;
    @Before
    public void setUp()
    {
        // System.out.println("setUp");
    }
    /**
     * succeed if the arraystack isn't empty
     */
    @Test
    public void testNotEmpty() {
        ArrayStack a = new ArrayStack(0);
        a.push("hey");
        assertTrue(!a.isEmpty());
    }
    /**
     * succeed if the arraystack is empty
     */
    @Test
    public void testEmpty()
    {
        ArrayStack a = new ArrayStack(0);
        assertTrue(a.isEmpty());
    }
    
    /**
     * test if it catches the stack exception
     * create an empty string
     * pop the empty string
     * succeed if catches the error otherwise fail
     */
    @Test
    public void testPopEmpty()
    {
        ArrayStack a = new ArrayStack(0);
        try{
            a.pop();
            fail();
        }
        catch(StackException e)
        {
            
        }
    }

    /**
     * test push and pop methods
     * create a new arraystack
     * push 1000 object
     * pop those 1000 object
     * see if the number of object popped equals to the number of object it pushed
     */
    @Test
    public void testPushPop()
    {        
        ArrayStack a = new ArrayStack(0);
        try{
            for(int i=0;i<1000;i++)
            {
                a.push("hey");
            }
            for(int i=0;i<1000;i++)
            {
                Object obj=a.pop();
                if(obj.equals("hey"))
                {
                    count++;
                }
            }
            assertEquals(1000,count);
        }
        catch(StackException e)
        {

        }
    }

    /**
     * test the method peekTop()
     * create a new arraystack
     * pushes one object
     * succeed if it peeks the same object
     */
    @Test
    public void testPeekTop()
    {
        ArrayStack a=new ArrayStack(0);
        a.push("hey");
        try{
            assertEquals("hey",a.peekTop());
        }
        catch(StackException e)
        {

        }

    }
}