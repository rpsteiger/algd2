package algd2.ue02.stack;

import java.util.EmptyStackException;

import algd2.ue02.linearlist.MySinglyLinkedList;
import algd2.ue02.linearlist.SinglyLinkedList;

public class MyStack<E> implements Stack {
    private SinglyLinkedList<E> stackList = new MySinglyLinkedList<E>();
    
    @Override
    public E top() {
        if(this.stackList.size() == 0)
        {
            throw new EmptyStackException();
        }
        
        return this.stackList.getFirst();
    }

    @Override
    // TODO: why do i have to use Object instead of E here?
    public void push(Object data) {
        if(data == null)
        {
            throw new IllegalArgumentException();
        }
        
        E castedData = (E) data;
        this.stackList.insertFirst(castedData);        
    }

    @Override
    public E pop() {
        if(this.stackList.size() == 0)
        {
            throw new EmptyStackException();
        }
        
        E returnItem = this.stackList.getFirst();
        this.stackList.removeFirst();
        return returnItem;
    }

    @Override
    public boolean isEmpty() {
        return (this.stackList.size() == 0);
    }

}
