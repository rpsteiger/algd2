package algd2.ue02.linearlist;

public class MySinglyLinkedList<E> implements SinglyLinkedList<E>{
    /*
     * reference to the head of the list. The dummy element
     */
    private Element<E> head;
    
    static class Element<E>
    {
        public E data;
        public Element<E> next;
    }
    
    public MySinglyLinkedList()
    {
        this.head = this.createDummyFirstElement();
        this.head.next = null;
    }
    
    public MySinglyLinkedList(SinglyLinkedList<E> orig)
    {        
        this();
        
        if(orig == null)
        {
            throw new IllegalArgumentException();
        }
        
        for(int i=orig.size()-1; i>=0; i--)
        {
            this.insertFirst(orig.get(i)); // TODO: do i need to clone or copy here?
        }
    }
    
    public void insertFirst(E e)
    {
        Element<E> newItem = new Element<E>();
        newItem.data = e;
        
        Element<E> oldFirst = this.head.next;
        this.head.next = newItem;
        
        newItem.next = oldFirst;        
    }
    
    public void insertAfter(E e, int index)
    {
        Element<E> element2InsertAfter = null; 
        Element<E> oldNext = null;
        
        /* works differently on get(0) on empty list */
        if(index == 0 && this.size() == 0)
        {
            element2InsertAfter = this.head; // in this case its the dummy
        }
        else 
        {
            element2InsertAfter = this.getElement(index);
            oldNext = element2InsertAfter.next;
        }

        Element<E> newItem = new Element<E>();
        newItem.data = e;
        
        element2InsertAfter.next = newItem;
        newItem.next = oldNext;
    }
    
    public void removeFirst()
    {
        if(this.size() > 0)
        {
            this.head.next = this.head.next.next;
        }
        else 
        {
            throw new IllegalRemoveOperationException();
        }
    }
    
    public void remove(int index)
    {
        // if list is empty. Don't do anything
        if(this.head.next == null) throw new IllegalRemoveOperationException();
        
        Element<E> old = this.head, current = this.head.next;
        int i = 0;
        
        while(current != null && i<index)
        {
            old = current;
            current = current.next;
            i++;
        }
        
        old.next = old.next.next;        
    }
    
    public void removeAll()
    {
        this.head.next = null;
    }
    
    public E getFirst()
    {
        return this.get(0);
    }
    
    private Element<E> getElement(int index) throws InvalidListIndexException
    {
        if(index < 0 || index > this.size() -1)
        {
            throw new InvalidListIndexException();
        }
        
        Element<E> currentItem = this.head.next;
        
        int i=0;
        while(i<index)
        {
            currentItem = currentItem.next;
            i++;
        }
        
        return currentItem;
    }
    
    public E get(int index) throws InvalidListIndexException
    {
        return this.getElement(index).data;
    }
    
    public int size()
    {
        int size = 0;
        Element<E> currentItem = this.head.next;
        while(currentItem != null)
        {
            size++;
            currentItem = currentItem.next;
        }
        
        return size; 
    }
    
    public String toString()
    {
        return null;
    }
    
    private Element<E> createDummyFirstElement()
    {
        Element<E> dummy = new Element<E>();
        dummy.data = null;
        return dummy;
    }
}
