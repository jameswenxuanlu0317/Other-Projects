import java.util.*;
import java.io.*;
/**
 * This class evaluates post fix expressions using integer math
 * It has evaluate method to calculate a string of expressions
 * It has a getResult method to return the final value
 * It has a toString method to return the original expressions with the final answer
 *
 * @author Lu
 * @version 11111
 */
public class PostFix<E>
{
    // instance variables - replace the example below with your own
    private int result=0;
    private int numNumbers=0;
    private int numOperations=0;
    private String expressions;//stores the orginal expression
    /**
     * This method evaluates a given string of post fix expressions with integer math
     * It creates a new arrayStack
     * It goes through the given string, if one character is a integer, it gets pushed to the arraystack
     * If the character is an operation, the array stack pops two elements and do the appropriate calculations
     * if the expression is invalid, it throws a post fix exception.
     */
    public void evaluate(String str) throws PostFixException
    {
        ArrayStack postfix = new ArrayStack(str.length());
        expressions=str;
        for(int i=0;i<expressions.length();i++)
        {
            if(Character.isDigit(expressions.charAt(i)))
            {
                numNumbers++;
            }
            else if(isOperation(expressions.charAt(i)))
            {
                numOperations++;
            }
        }
        int diff=numNumbers-numOperations;

        for(int i=0;i<expressions.length();i++)
        {
            if(!Character.isDigit(expressions.charAt(i))&&!isOperation(expressions.charAt(i)))
            {
                throw new PostFixException("Expression is invalid");
            }
            else if(diff>1)
            {
                throw new PostFixException("Expression is invalid");
            }
            else if(isOperation(expressions.charAt(0)))
            {
                throw new PostFixException("Expression is invalid");
            }
        }
        if(notPostFix(expressions))
        {
            throw new PostFixException("Expression is invalid");
        }
        for(int i=0;i<str.length();i++)
        {
            char c=str.charAt(i);
            int x=0;
            int y=0;
            int r=0;            
            if(Character.isDigit(c))
            {
                numNumbers++;
                int t=Character.getNumericValue(c);
                postfix.push(t);
            }

            else if(c=='+')
            {
                try{
                    x=(int)postfix.pop();
                    y=(int)postfix.pop();
                    r=y+x;
                    postfix.push(r);                    
                }
                catch(StackException e)
                {
                    throw new PostFixException("Expression is invalid");
                }

            }
            else if(c=='-')
            {
                try{
                    x=(int)postfix.pop();
                    y=(int)postfix.pop();
                    r=y-x;
                    postfix.push(r);
                }
                catch(StackException e)
                {
                    throw new PostFixException("Expression is invalid");
                }

            }
            else if(c=='*')
            {
                try{
                    x=(int)postfix.pop();
                    y=(int)postfix.pop();
                    r=y*x;
                    postfix.push(r);
                }
                catch(StackException e)
                {
                    throw new PostFixException("Expression is invalid");
                }

            }
            else if(c=='/')
            {
                try{
                    x=(int)postfix.pop();
                    y=(int)postfix.pop();
                    r=y/x;
                    postfix.push(r);
                }
                catch(StackException e)
                {
                    throw new PostFixException("Expression is invalid");
                }
                catch(ArithmeticException a)
                {
                    throw new ArithmeticException("Dividing by zero");
                }

            }

        }
        try
        {
            result=(int)postfix.pop();
        }
        catch(StackException a)
        {

        }
    }

    /**
     * Returns false if the given string is not a operator
     * otherwise return true
     */
    public boolean isOperation(Character str)
    {

        if(str!='+'&&str!='-'&&str!='*'&&str!='/')
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * check if the expression is a post fix expression
     * Take a string as a parameter, go through the string
     * if one index has a digit, then try two more index after it, see if it is still a digit
     * Return false if it is not a digit any more
     * If one index has a operator, then try two more index after it, see if it is still a operator
     * Return false if it is not a operator anymore
     * Otherwise return true
     */
    public boolean notPostFix(String str)
    {
        for(int i=0;i<str.length()-1;i++)
        {
            if(Character.isDigit(str.charAt(i)))
            {
                for(int k=i+2;k<str.length();k+=2)
                {
                    if(!Character.isDigit(str.charAt(k)))
                    {
                        return false;
                    }
                }
            }
            else if(isOperation(str.charAt(i)))
            {
                for(int l=i+2;l<str.length();l+=2)
                {
                    if(!isOperation(str.charAt(l)))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Return the result of the calculation
     */
    public int getResult()
    {
        return result;
    }

    /**
     * Return the string contains the original postfix expressions and the calculation results
     */
    public String toString()
    {
        return "Original postfix expression: "+expressions+" Result: "+result;
    }
}
