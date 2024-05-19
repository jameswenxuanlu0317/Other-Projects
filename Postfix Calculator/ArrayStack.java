import java.util.*;
/**
 * This is class ArrayStack implements interface Stack Class
 * It contains method push, pop , peekTop, and checks if the stack is empty
 * It creates a new object array and could be used to store a stack of objects
 * 
 *
 * @author Lu
 * @version 11111
 */
public class ArrayStack<E> implements Stack
{
    int length;
    Object a[]=new Object[length];

    /**
     * points to the object array with the given length
     */
    public ArrayStack(int l)
    {
        length=l;
        a=new Object[l];
    }

    /** Returns true if the stack is empty
     * otherwise return false
     */
    public boolean isEmpty()
    {
        if(length==0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /** Return the value that pop would give, without modifying
     * the stack by returning the first value of the array
     * Throw a StackException if the stack is empty. 
     */
    public E peekTop() throws StackException 
    {        
        if(isEmpty())
        {
            throw new StackException("the stack is empty");
        }
        else
        {
            return (E)a[0];
        }
    }

    /** Remove and return the value that has been in the stack the
     * least time. 
     * Throw a StackException if the stack is empty. 
     */
    public E pop() throws StackException
    {
        if(a.length==0)
        {
            throw new StackException("the stack is empty");
        }        
        Object []b=new Object[length];
        Object obj=new Object();
        obj=a[0];
        for(int i=0;i<length-1;i++)
        {
            if(length>1)
            {
                b[i]=a[i+1];
            }
        }
        length--;
        a=b;
        if(b.length>0)
        {
            return (E)obj;            
        }
        else
        {
            return null;
        }
    }

    /** Add the given value to the stack. 
     */
    public void push(Object ob)
    {
        length++;
        Object[]c=new Object[length];
        c[0]=ob;
        for(int i=1; i<c.length;i++)
        {
            c[i]=a[i-1];
        }
        a=c;
    }
}
