package algd2.ab01.unsortedset;

import java.util.AbstractCollection;
import java.util.Iterator;

public abstract class MyAbstractCollection<E extends Comparable<E>> extends
        AbstractCollection<E> {

    @Override
    public abstract boolean add(E e);

    @Override
    public abstract boolean remove(Object o);

    @Override
    public abstract boolean contains(Object o);

    @Override
    public abstract Object[] toArray();

    protected final void checkNull(Object e) {
        if (e == null) {
            throw new NullPointerException("Element must not be null");
        }
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException(
                "Iterator not supported. Call toArray().");
    }
}
