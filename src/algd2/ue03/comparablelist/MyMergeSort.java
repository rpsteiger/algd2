package algd2.ue03.comparablelist;

import java.util.ListIterator;

public class MyMergeSort<E extends Comparable<E>>{
    public void sort(MyComparableList<E> data)
    {
        this.recursiveSort(data);
    }
    
    private void recursiveSort(MyComparableList<E> data)
    {
        System.out.println("current data size: " + data.size());
        if(data.size() > 1)
        {
            MyComparableList<E> other = this.split(data);
            this.recursiveSort(other);
            this.recursiveSort(data);
            this.merge(other, data);
        }
    }
    
    public MyComparableList<E> split(MyComparableList<E> data)
    {
        int m = data.size()/2;
        MyComparableList<E> other = new MyComparableList<E>();
        
        ListIterator<E> dataIterator = data.listIterator();
        ListIterator<E> otherIterator = other.listIterator();
        while(dataIterator.nextIndex() < m)
        {
            otherIterator.add(dataIterator.next());
        }
        
        /* remove from data list */
        int tmpM = (m == 1) ? 2 : m; 
        ListIterator<E> deleteIterator = data.listIterator(tmpM);
        deleteIterator.previous();
        while(deleteIterator.hasPrevious())
        {
            deleteIterator.remove();
            deleteIterator.previous();
        }
        deleteIterator.remove();
        
        return other;
    }
    
    public void merge(MyComparableList<E> left, MyComparableList<E> right)
    {
        ListIterator<E> leftIterator = left.listIterator();
        ListIterator<E> rightIterator = right.listIterator();
        ListIterator<E> position = right.listIterator(0);
        
        MyComparableList<E> mergeResult = new MyComparableList<E>();
        
        while(leftIterator.hasNext() && rightIterator.hasNext())
        {
            E value1 = leftIterator.next();
            E value2 = rightIterator.next();
            
            if(value1.compareTo(value2) <= 0)
            {
                position.add(value1);
                position.next();
            }
            else 
            {                
                position.next();
                position.add(value1);
            }
        }
        
        while(leftIterator.hasNext())
        {
            mergeResult.addTail(leftIterator.next());
        }
        
        while(rightIterator.hasNext())
        {
            mergeResult.addTail(rightIterator.next());
        }
    }
}
