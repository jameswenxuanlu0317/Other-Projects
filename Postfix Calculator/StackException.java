
/**
 * This is a stack exception class that extends class Exception
 * Called when the stack is empty
 * Prints out the given message
 *
 * @author Lu
 * @version 11111
 */
public class StackException extends Exception
{
    // instance variables - replace the example below with your own
    /**
     * Prints out the given error message
     */    
    public StackException(String message)
    {
        super(message);
    }
}
