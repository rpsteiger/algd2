package algd2.ue03.comparablelist;

import java.util.ListIterator;

public interface ComparableList<E extends Comparable<E>> {
    public int size();
    public void addHead(E data);
    public void addTail(E data);
    public E removeTail();
    public E removeHead();
    
    public ListIterator<E> listIterator();
    public ListIterator<E> listIterator(int index);
}
