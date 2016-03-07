package algd2.ue02.linearlist;

public interface SinglyLinkedList<E> {
    // TODO: how can i declare constructors in a interface?
    //public SinglyLinkedList();
    //public SinglyLinkedList(SinglyLinkedList<E> orig);
    public void insertFirst(E e);
    public void insertAfter(E e, int index);
    public void removeFirst();
    public void remove(int index);
    public void removeAll();
    public E getFirst();
    public E get(int index) throws InvalidListIndexException;
    public int size();
    public String toString();
}
