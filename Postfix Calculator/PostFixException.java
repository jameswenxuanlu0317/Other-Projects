
/**
 * This is a post fix exception class that extends class Exception
 * Called when the string of expression is invalid
 * Prints out the given message
 * 
 * @Author Lu
 * @Version 11111
 */
public class PostFixException extends Exception
{
    /**
     * Prints out a given error message
     */    
    public PostFixException(String message)
    {
        super(message);
    }
}
