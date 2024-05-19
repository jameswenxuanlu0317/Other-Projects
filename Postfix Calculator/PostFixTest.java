import org.junit.*;

public class PostFixTest extends junit.framework.TestCase {

    @Before
    public void setUp()
    {
        // System.out.println("setUp");
    }

    /**
     * tests if the post fix expressions is valid
     * evaluate an invalid string on purpose
     * Succeed if catches the post fix error
     * Otherwise fail
     */
    @Test
    public void testDivideByZero()
    {
        PostFix a=new PostFix();
        try
        {
         a.evaluate("30/");
         fail();
        }
        catch(PostFixException e)
        {
            fail();
        }
        catch(ArithmeticException x)
        {
            
        }

    }

    /**
     * tests if the post fix expressions is valid
     * evaluate an invalid string on purpose
     * Succeed if catches the post fix error
     * Otherwise fail
     */
    @Test
    public void testInvalid()
    {
        PostFix a=new PostFix();
        try
        {
            a.evaluate("a3+");
            fail();
        }
        catch(PostFixException e)
        {

        }

    }

    /**
     * tests if the post fix expressions is valid
     * evaluate an invalid string on purpose
     * Succeed if catches the post fix error
     * Otherwise fail
     */
    @Test
    public void testInvalidTwo()
    {
        PostFix a=new PostFix();
        try
        {
            a.evaluate("123+");
            fail();
        }
        catch(PostFixException e)
        {

        }

    }

    /**
     * tests if the post fix expressions is valid
     * evaluate an invalid string on purpose
     * Succeed if catches the post fix error
     * Otherwise fail
     */
    @Test
    public void testInvalidThree()
    {
        PostFix a=new PostFix();
        try
        {
            a.evaluate("1+2+3");
            fail();
        }
        catch(PostFixException e)
        {

        }

    }

    /**
     * Tests if the operator subtraction works
     * evaluate a given string. check if the result matches the correct answer
     */
    @Test
    public void testSubtraction()
    {
        PostFix a=new PostFix();
        try
        {
            a.evaluate("987654321--------");
            assertEquals(5,a.getResult());
        }
        catch(PostFixException e)
        {

        }
    }

    /**
     * Tests if the operator multiplication works
     * evaluate a given string. check if the result matches the correct answer
     */
    @Test
    public void testMultiplication()
    {
        PostFix a=new PostFix();
        try
        {
            a.evaluate("987654321********");
            assertEquals(362880,a.getResult());
        }
        catch(PostFixException e)
        {

        }
    }

    /**
     * Tests if the operator division works
     * evaluate a given string. check if the result matches the correct answer
     */
    @Test
    public void testDivision()
    {
        PostFix a=new PostFix();
        try
        {
            a.evaluate("987654321////////");
            assertEquals(1,a.getResult());
        }
        catch(PostFixException e)
        {

        }
    }

    /**
     * Tests if the mixed up operation works
     * evaluate a given string. check if the result matches the correct answer
     */
    @Test
    public void testMix()
    {
        PostFix a=new PostFix();
        try
        {
            a.evaluate("987654321/*+-/*+-");
            assertEquals(8,a.getResult());
        }
        catch(PostFixException e)
        {

        }
    }

    /**
     * test if the method toString() works
     * evaluate a given string
     * succeed if the toString() matches the correct post fix expression and result
     */
    @Test
    public void testToString()
    {
        PostFix a=new PostFix();
        try     
        {
            a.evaluate("12+");
            assertEquals("Original postfix expression: 12+ Result: 3", a.toString());
        }
        catch(PostFixException e)
        {

        }
    }

}
