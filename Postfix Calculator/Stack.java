public interface Stack<E> // not in the Java library
{
	/** Tell whether the stack has no more elements. 
	*/
	public boolean isEmpty();

	/** Return the value that pop would give, without modifying
	* the stack. TODO:  Throw a StackException if the stack is empty. 
	*/
	public E peekTop() throws StackException;

	/** Remove and return the value that has been in the stack the
	* least time. TODO: Throw a StackException if the stack is empty. 
	*/
	public E pop() throws StackException;

	/** Add the given value to the stack. */
	public void push (E ob);
}

