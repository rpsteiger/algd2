package algd2.ue03.comparablelist.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import algd2.ue03.comparablelist.ComparableList;
import algd2.ue03.comparablelist.MyComparableList;
import algd2.ue03.comparablelist.MyMergeSort;

public class MyMergeSortTest {
    private MyMergeSort<Integer> classUnderTest;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
        this.classUnderTest = new MyMergeSort<Integer>();
    }

    @Test
    public void testSortNormal() {
        MyComparableList<Integer> data = new MyComparableList<Integer>();
        data.addHead(25);
        data.addHead(9);
        data.addHead(59);
        data.addHead(11);
        data.addHead(39);
        data.addHead(5);
        data.addHead(23);
        data.addHead(33);
        
        MyComparableList<Integer> sorted = new MyComparableList<Integer>();
        sorted.addHead(59);
        sorted.addHead(39);
        sorted.addHead(33);
        sorted.addHead(25);
        sorted.addHead(23);
        sorted.addHead(11);
        sorted.addHead(9);
        sorted.addHead(5);
        
        this.classUnderTest.sort(data);
        
        /* check first if list size is the same */
        assertEquals(8, sorted.size());
        assertEquals(5, sorted.removeHead().intValue());
        assertEquals(59, sorted.removeTail().intValue());
        
        /* check if element by element if the lists are identical */
        ListIterator<Integer> dataIterator = data.listIterator();
        ListIterator<Integer> sortedIterator = sorted.listIterator();
        while(dataIterator.hasNext() && sortedIterator.hasNext())
        {
            assertEquals(dataIterator.next().intValue(), sortedIterator.next().intValue());
        }        
    }
    
    @Test
    public void testSort7Items() {
        MyComparableList<Integer> data = new MyComparableList<Integer>();
        data.addHead(25);
        data.addHead(9);
        data.addHead(59);
        data.addHead(11);
        data.addHead(39);
        data.addHead(5);
        data.addHead(23);
        
        MyComparableList<Integer> sorted = new MyComparableList<Integer>();
        sorted.addHead(59);
        sorted.addHead(39);
        sorted.addHead(25);
        sorted.addHead(23);
        sorted.addHead(11);
        sorted.addHead(9);
        sorted.addHead(5);
        
        this.classUnderTest.sort(data);
        
        /* check first if list size is the same */
        assertEquals(7, sorted.size());
        assertEquals(5, sorted.removeHead().intValue());
        assertEquals(59, sorted.removeTail().intValue());
        
        /* check if element by element if the lists are identical */
        ListIterator<Integer> dataIterator = data.listIterator();
        ListIterator<Integer> sortedIterator = sorted.listIterator();
        while(dataIterator.hasNext() && sortedIterator.hasNext())
        {
            assertEquals(dataIterator.next().intValue(),sortedIterator.next().intValue());
        }        
    }
    
    @Test
    public void testSplitNormal()
    {
        MyComparableList<Integer> data = new MyComparableList<Integer>();
        data.addHead(25);
        data.addHead(9);
        data.addHead(59);
        data.addHead(11);
        data.addHead(39);
        data.addHead(5);
        data.addHead(23);
        data.addHead(33);
        
        MyComparableList<Integer> other = this.classUnderTest.split(data);
        
        /* verify size and individual items */
        assertEquals(4, other.size());
        assertEquals(33, other.removeHead().intValue());
        assertEquals(23, other.removeHead().intValue());
        assertEquals(5, other.removeHead().intValue());
        assertEquals(39, other.removeHead().intValue());        
    }
    
    @Test
    public void testSplit2Items()
    {
        MyComparableList<Integer> data = new MyComparableList<Integer>();
        data.addHead(25);
        data.addHead(9);
        
        MyComparableList<Integer> other = this.classUnderTest.split(data);
        
        /* verify size and individual items */
        assertEquals(1, other.size());
        assertEquals(9, other.removeHead().intValue());
    }
    
    @Test
    public void testMerge1()
    {
        //private void merge(MyComparableList<E> left, MyComparableList<E> right)
        
        MyComparableList<Integer> left = new MyComparableList<Integer>();
        left.addHead(33);
        MyComparableList<Integer> right = new MyComparableList<Integer>();
        right.addHead(23);
        
        this.classUnderTest.merge(left, right);
        
        System.out.println("size of right: " + right.size());
        
        assertEquals(23, right.removeHead().intValue());
        assertEquals(33, right.removeHead().intValue());
    }
    
    @Test
    public void testMerge2()
    {
        MyComparableList<Integer> left = new MyComparableList<Integer>();
        left.addHead(39);
        left.addHead(33);
        left.addHead(23);
        left.addHead(5);
        
        MyComparableList<Integer> right = new MyComparableList<Integer>();
        right.addHead(59);
        right.addHead(25);
        right.addHead(11);
        right.addHead(9);
        
        this.classUnderTest.merge(left, right);
        
        System.out.println("size of right: " + right.size());
        
        /* check whether first and last element are correct, and verify size */
        assertEquals(8, right.size());
        assertEquals(5, right.removeHead().intValue());
        assertEquals(59, right.removeTail().intValue());
    }    
}
