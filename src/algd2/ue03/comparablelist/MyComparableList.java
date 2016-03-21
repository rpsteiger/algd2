package algd2.ue03.comparablelist;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyComparableList<E extends Comparable<E>> implements ComparableList<E> {
    private Element<E> head;
    private Element<E> tail;

    private static class Element<E> {
        public E data;
        public Element<E> next;
        public Element<E> previous;
    }

    public MyComparableList() {
        /* create dummy items for the first and the last item in the list */
        Element<E> dummyStartItem = new Element<E>();
        dummyStartItem.data = null;
        Element<E> dummyEndItem = new Element<E>();
        dummyEndItem.data = null;

        dummyStartItem.next = dummyEndItem;
        dummyStartItem.previous = null;

        dummyEndItem.next = null;
        dummyEndItem.previous = dummyStartItem;

        this.head = dummyStartItem;
        this.tail = dummyEndItem;
    }

    @Override
    public int size() {
        int itemCount = 0;
        Element<E> currentItem = this.head.next;
        while (currentItem.next != null) {
            currentItem = currentItem.next;
            itemCount++;
        }

        return itemCount;
    }

    @Override
    public void addHead(E data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        /* store old item at position 0 */
        Element<E> oldFirst = this.head.next;

        /* create new item */
        Element<E> newItem = new Element<E>();
        newItem.data = data;

        /* link item into list */
        this.head.next = newItem;
        newItem.previous = this.head;
        newItem.next = oldFirst;
        oldFirst.previous = newItem;
    }

    @Override
    public void addTail(E data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        /* store old item at position n */
        Element<E> oldLast = this.tail.previous;

        /* create new item */
        Element<E> newItem = new Element<E>();
        newItem.data = data;

        /* link item into list */
        this.tail.previous = newItem;
        newItem.next = this.tail;
        newItem.previous = oldLast;
        oldLast.next = newItem;
    }

    @Override
    public E removeTail() {
        if (this.size() == 0) {
            throw new IllegalStateException();
        }

        Element<E> tempTail = this.tail.previous;
        this.tail.previous = this.tail.previous.previous;

        return tempTail.data;
    }

    @Override
    public E removeHead() {
        if (this.size() == 0) {
            throw new IllegalStateException();
        }

        Element<E> tempHead = this.head.next;
        this.head.next = this.head.next.next;
        
        // TODO: forgote to set previous
        // TODO: write test case for this        

        return tempHead.data;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyComparableListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyComparableListIterator(index);
    }

    class MyComparableListIterator implements ListIterator<E> {
        Element<E> returned;
        Element<E> next;
        int index;
        
        public MyComparableListIterator() {
            this.returned = null;
            this.index = 0;
            this.next = MyComparableList.this.head.next; // this.head is the dummy       
        }

        public MyComparableListIterator(int startIndex) {
            this.returned = null;
            this.next = MyComparableList.this.head.next; // this.head is the dummy
            
            int i=0;
            while(this.next.data != null && i<startIndex) // until we reach the dummy at the end of the list
            {
                i++;
                this.next = this.next.next;
            }
            
            if(i != startIndex)
            {
                throw new IndexOutOfBoundsException();
            }
            else 
            {
                this.index = i;
            }
        }

        @Override
        public boolean hasNext() {
            /* check whether next is the final dummy -> end of list */
            return (this.next.data != null);
        }

        @Override
        public E next() {
            if(this.next.data == null) // check if current element is dummy final element
            {
                throw new NoSuchElementException();
            }
            
            this.returned = this.next;            
            this.next = this.next.next;
            this.index++;
            
            return this.returned.data;
        }

        @Override
        public boolean hasPrevious() {
            return (this.next.previous.data != null); // check if previous of next is the initial dummy -> start of list 
        }

        @Override
        public E previous() {
            if(this.next.previous.data == null) // check if current's previous is the initial dummy -> start of the list
            {
                throw new NoSuchElementException();
            }
            
            this.returned = this.next.previous;
            this.next = this.next.previous;
            this.index--;
            
            return this.next.data;
        }

        @Override
        public int nextIndex() {
            return this.index;            
        }

        @Override
        public int previousIndex() {
            return (this.index -1 < 0) ? 0 : this.index -1;
        }

        @Override
        public void remove() {
            if (returned == null) {
                throw new IllegalStateException();
            } else {
                if (returned == next) {
                    next = returned.next;
                } else
                    index--;
                this.remove(returned);
                //MyComparableList.this.remove(returned);
                
                returned = null;
            }
        }
        
        private void remove(Element<E> item2Remove)
        {
            Element<E> previous = item2Remove.previous;
            Element<E> next = item2Remove.next;
            
            previous.next = next;
            next.previous = previous;
        }

        @Override
        public void set(E data) {
            if (returned == null)
                throw new IllegalStateException();
            else {
                if (!(data instanceof Comparable<?>)) {
                    throw new IllegalArgumentException();
                }
                returned.data = data;
            }
        }

        @Override
        public void add(E e) {
            Element<E> previousItem = this.next.previous;
            Element<E> newItem = new Element<E>();
            newItem.data = e;
            
            previousItem.next = newItem;
            newItem.next = this.next;
            this.next.previous = newItem;
            newItem.previous = previousItem;
        }
    }
}
