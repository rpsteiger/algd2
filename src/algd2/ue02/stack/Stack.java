package algd2.ue02.stack;

public interface Stack<E> {
    /**
     * Return item ontop of the stack
     * @return
     */
    public E top();
    
    /**
     * Push a new item onto the stack
     * @param data
     */
    public void push(E data);
    public E pop();
    public boolean isEmpty();
}
