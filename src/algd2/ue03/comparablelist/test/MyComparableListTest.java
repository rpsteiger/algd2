package algd2.ue03.comparablelist.test;

import static org.junit.Assert.*;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import algd2.ue03.comparablelist.ComparableList;
import algd2.ue03.comparablelist.MyComparableList;

public class MyComparableListTest {
    private ComparableList<Integer> classUnderTest = null;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.classUnderTest = new MyComparableList<>();
    }

    @Test
    /**
     * Add 3 elements to the list then verify that size returns 3
     */
    public void testSizeNormal()
    {
        this.classUnderTest.addHead(24);
        this.classUnderTest.addHead(25);
        this.classUnderTest.addHead(24);
        
        assertEquals(3, this.classUnderTest.size());
    }
    
    @Test
    /** 
     * Verify that size returns 0 on an empty list
     */
    public void testSizeOnEmptyList()
    {
        assertEquals(0, this.classUnderTest.size());
    }
    
    @Test
    /**
     * Add 1 element to the list then make sure size returns 1.
     */
    public void testSize1Item()
    {
        this.classUnderTest.addHead(1350);
        
        assertEquals(1, this.classUnderTest.size());
    }
    
    @Test
    /**
     * Add 1 item to the list then retrieve it with removeHead
     */
    public void testAddHeadNormal()
    {
        Integer expected = 1700;
        this.classUnderTest.addHead(expected);
        Integer actual = this.classUnderTest.removeHead();
        
        assertEquals(expected, actual);
        
    }
    
    @Test
    /**
     * Add 3 items then retrieve them with removeTail
     */
    public void testAddHead3Items()
    {
        Integer expected1 = 1700, expected2 = 1630, expected3 = 2230;
        this.classUnderTest.addHead(expected1); // -> pos 2
        this.classUnderTest.addHead(expected2); // -> pos 1
        this.classUnderTest.addHead(expected3); // -> pos 0
        
        assertEquals(expected1.intValue(), this.classUnderTest.removeTail().intValue());
        assertEquals(expected2.intValue(), this.classUnderTest.removeTail().intValue());
        assertEquals(expected3.intValue(), this.classUnderTest.removeTail().intValue());        
    }
    
    @Test(expected=IllegalArgumentException.class)
    /**
     * Call addHead with null
     */
    public void testAddHeadNull()
    {
        this.classUnderTest.addHead(null);
    }
    
    @Test
    /**
     * Add 1 item to the end of the list then retrieve it with removeTail
     */
    public void testAddTailNormal()
    {
        Integer expected = 1828;
        this.classUnderTest.addTail(expected);
        
        assertEquals(expected, this.classUnderTest.removeTail());
    }
    
    @Test
    /**
     * Add 3 items using addTail then retrieve them using removeTail
     */
    public void testAddTail3Items()
    {
        Integer expected1 = 700, expected2 = 1330, expected3 = 4230;
        this.classUnderTest.addTail(expected1); // -> pos 0
        this.classUnderTest.addTail(expected2); // -> pos 1
        this.classUnderTest.addTail(expected3); // -> pos 2
        
        assertEquals(expected3.intValue(), this.classUnderTest.removeTail().intValue());
        assertEquals(expected2.intValue(), this.classUnderTest.removeTail().intValue());
        assertEquals(expected1.intValue(), this.classUnderTest.removeTail().intValue());        
    }
    
    @Test(expected=IllegalArgumentException.class)
    /**
     * Call addTail with null
     */
    public void testAddTailNull()
    {
        this.classUnderTest.addTail(null);
    }
    
    @Test(expected=IllegalStateException.class)
    /**
     * Call removeTail() on empty list
     */
    public void testRemoveTailOnEmptylist()
    {
        this.classUnderTest.removeTail();
    }
    
    @Test(expected=IllegalStateException.class)
    /**
     * Call removeTail() on empty list
     */
    public void testRemoveHeadOnEmptylist()
    {
        this.classUnderTest.removeHead();
    }
    
    /**
     * ListIterator Test Cases
     */
    
    @Test
    /**
     * Not much to do here. Let's check if no exception is thrown
     */
    public void testListIteratorNormal()
    {
        /* check if no exception is thrown */
        this.classUnderTest.listIterator();        
    }
    
    @Test
    /**
     * Insert two items to the list. 
     * Construct iterator from index 1 and call next.
     * Should return the item added second to the list.
     */
    public void testListeIterator_startIndexConstructorNormal()
    {
        Integer expected1 = 300, expected2 = 400;
        this.classUnderTest.addTail(expected1); // pos -> 0
        this.classUnderTest.addTail(expected2); // pos -> 1
        
        ListIterator<Integer> listIterator = this.classUnderTest.listIterator(1);
        Integer actual2 = listIterator.next();
        
        assertEquals(expected2.intValue(), actual2.intValue());
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    /**
     * Insert 1 item to the list.
     * Construct iterator from index 25.
     */
    public void testListIterator_startIndexConstructorOutOfBounds()
    {
        this.classUnderTest.addHead(3000);        
        this.classUnderTest.listIterator(25);        
    }
    
    @Test(expected=NoSuchElementException.class)
    /**
     * get iterator on empty list and call iterator.next()
     */
    public void testListIterator_NextEmptyList()
    {
        this.classUnderTest.listIterator().next();
    }
    
    @Test
    /**
     * insert 3 items to the list and try to retrieve them using iterator.next() 
     */
    public void testListIteratorNextNormal()
    {
        Integer expected1 = 555, expected2 = 666, expected3 = 777;
        this.classUnderTest.addHead(expected1); // pos -> 2
        this.classUnderTest.addHead(expected2); // pos -> 1
        this.classUnderTest.addHead(expected3); // pos -> 0
        
        ListIterator<Integer> listIterator = this.classUnderTest.listIterator();
        
        Integer actual1, actual2, actual3;
        actual3 = listIterator.next();
        actual2 = listIterator.next();
        actual1 = listIterator.next();
        
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
    }
    
    @Test
    /**
     * Insert 1 item to the list and retrieve it using iterator.next()
     */
    public void testListIterator_Next1Item()
    {
        Integer expected = 723;
        this.classUnderTest.addTail(expected);
        
        ListIterator<Integer> listIterator = this.classUnderTest.listIterator();
        
        Integer actual = listIterator.next();
        
        assertEquals(expected, actual);
    }
    
    @Test
    /**
     * Insert 1 item to the list. hasNext() should return true.
     */
    public void testListIterator_hastNextNormal()
    {
        this.classUnderTest.addTail(12);
        assertTrue(this.classUnderTest.listIterator().hasNext());
        
    }
    
    @Test
    /**
     * call hasNext() on empty list
     */
    public void testListIterator_hasNextOnEmptyList()
    {
        assertFalse(this.classUnderTest.listIterator().hasNext());
    }
    
    @Test
    /**
     * call hasNext when next() points to the end of the list
     */
    public void testListIterator_hasNextOnEndOfList()
    {
        this.classUnderTest.addTail(13);
        
        ListIterator<Integer> listIterator = this.classUnderTest.listIterator();
        listIterator.next();
        
        assertFalse(listIterator.hasNext());        
    }
    
    @Test
    /**
     * Create a list with 2 items. Call next, then hasPrevious().
     * Should return true.
     */
    public void testListIterator_hasPreviousNormal()
    {
        this.classUnderTest.addHead(25);
        this.classUnderTest.addHead(50);
        
        ListIterator<Integer> listIterator = this.classUnderTest.listIterator();
        listIterator.next();
        
        assertTrue(listIterator.hasPrevious());        
    }
    
    @Test
    /**
     * Call hasPrevious on index 0. Should return false.
     */
    public void testListIterator_hasPreviousOnIndex0()
    {
        this.classUnderTest.addHead(578);
        assertFalse(this.classUnderTest.listIterator().hasPrevious());
    }
    
    @Test
    /**
     * Call has Previous on empty list. Should return false.
     */
    public void testListIterator_hasPreviousOnEmptyList()
    {
        assertFalse(this.classUnderTest.listIterator().hasPrevious());
    }
    
    @Test
    /**
     * Create a list with 4 items. Get iterator from index 3 and call previous() 3 times.
     * Then verify the values.
     */
    public void testListIterator_previousNormal()
    {
        Integer expected1 = 45, expected2 = 20, expected3 = 100;
        
        this.classUnderTest.addHead(500);       // pos -> 4
        this.classUnderTest.addHead(expected1); // pos -> 3
        this.classUnderTest.addHead(expected2); // pos -> 2
        this.classUnderTest.addHead(expected3); // pos -> 1        
        
        ListIterator<Integer> listIterator = this.classUnderTest.listIterator(3);
        
        Integer actual1, actual2, actual3;
        actual1 = listIterator.previous();
        actual2 = listIterator.previous();
        actual3 = listIterator.previous();
        
        assertEquals(expected1.intValue(), actual1.intValue());
        assertEquals(expected2.intValue(), actual2.intValue());
        assertEquals(expected3.intValue(), actual3.intValue());        
    }
    
    @Test
    /**
     * Create a liste with 1 item. Call next() once then previous() and verify the item.
     */
    public void testListIterator_previous1Item()
    {
        Integer expected = 3;
        
        this.classUnderTest.addTail(expected);
        
        ListIterator<Integer> listIterator = this.classUnderTest.listIterator();
        
        listIterator.next();
        Integer actual = listIterator.previous();
        
        assertEquals(expected.intValue(), actual.intValue());
    }
    
    @Test(expected=NoSuchElementException.class)
    /**
     * Call previous on empty list. Should throw an NoSuchElementException
     */
    public void testListIterator_previousOnEmptyList()
    {
        this.classUnderTest.listIterator().previous();
    }
    
    @Test
    /**
     * Call nextIndex on empty list. Should return 0.
     */
    public void testListIterator_nextIndexEmptyList()
    {
        Integer expected = 0;
        ListIterator<Integer> listIterator = this.classUnderTest.listIterator();
        Integer actual = listIterator.nextIndex();
        
        assertEquals(expected, actual);
    }
    
    @Test
    /**
     * Create a list with 2 itmes. Call next() once, then call nextIndex().
     * Should return 1.
     */
    public void testListIterator_nextIndex()
    {
        this.classUnderTest.addHead(10);
        this.classUnderTest.addHead(15);
        
        ListIterator<Integer> listIterator = this.classUnderTest.listIterator();
        listIterator.next();
        
        Integer expected = 1;
        Integer actual = listIterator.nextIndex();
        
        assertEquals(expected, actual);
    }
    
    @Test
    /**
     * Create a list with 3 items. Call next() twice then verify previousIndex().
     * Should be 1
     */
    public void testListIterator_previousIndexNormal()
    {
        this.classUnderTest.addTail(30); // pos -> 0       
        this.classUnderTest.addTail(40); // pos -> 1
        this.classUnderTest.addTail(50); // pos -> 2
        
        ListIterator<Integer> listIterator = this.classUnderTest.listIterator();
        listIterator.next();
        listIterator.next();
        
        Integer expected = 1;
        Integer actual = listIterator.previousIndex();
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testListIterator_previousIndexOnEmptyList()
    {
        // TODO: no idea how this should behave with an empty list ..
    }
    
    @Test
    /**
     * Create a list with 1 item. Get the iterator, call next() once then call remove().
     * List size should be 0 after.
     */
    public void testListIterator_remove1Item()
    {
        this.classUnderTest.addTail(1922);
        ListIterator<Integer> listIterator = this.classUnderTest.listIterator();
        listIterator.next();
        listIterator.remove();
        
        assertEquals(0, this.classUnderTest.size());
    }
    
    @Test
    /**
     * Create a list with 1 item. Call next(), then change the value using set().
     * Verify with previous().
     */
    public void testListIterator_setNormal()
    {
        this.classUnderTest.addHead(7840);
        
        ListIterator<Integer> listIterator = this.classUnderTest.listIterator();
        listIterator.next();
        
        Integer expected = 7940;
        listIterator.set(expected);
        Integer actual = listIterator.previous();
        
        assertEquals(expected, actual);
    }
    
    @Test
    /**
     * Create a list with 1 item. Call add on index 0.
     * Verify that newItem is new head.
     */
    public void testListIterator_addInsertAtHead()
    {
        this.classUnderTest.addHead(500);
        
        ListIterator<Integer> listIterator = this.classUnderTest.listIterator();
        Integer expected = 600; 
        listIterator.add(expected);
        
        ListIterator<Integer> listIteratorAtIndex0 = this.classUnderTest.listIterator(0);
        Integer actual = listIteratorAtIndex0.next();
        
        assertEquals(expected, actual);        
    }
    
    @Test
    /**
     * Create a list with 3 items. Iterate through it and add 1 element each.
     */
    public void testListIterator_addNormal()
    {
        this.classUnderTest.addHead(100);
        this.classUnderTest.addHead(200);
        this.classUnderTest.addHead(300);
        
        ListIterator<Integer> addIterator = this.classUnderTest.listIterator();
        while(addIterator.hasNext())
        {
            addIterator.add(addIterator.next().intValue()/2);
        }
        // list should be like this [300,150,200,100,100,50]
        
        assertEquals(6, this.classUnderTest.size());
        
        ListIterator<Integer> confirmIterator = this.classUnderTest.listIterator(0);
        assertEquals(300, confirmIterator.next().intValue());
        assertEquals(150, confirmIterator.next().intValue());
        assertEquals(200, confirmIterator.next().intValue());
        assertEquals(100, confirmIterator.next().intValue());
        assertEquals(100, confirmIterator.next().intValue());
        assertEquals(50, confirmIterator.next().intValue());
    }
    
    @Test
    /**
     * Create an empty list. Add 3 elements using iterator.add().
     */
    public void testListIterator_addOnEmptyList()
    {
        ListIterator<Integer> addIterator = this.classUnderTest.listIterator();
        addIterator.add(300);
        addIterator.add(200);
        addIterator.add(100);
        // list should be like this [300,200,100]
        
        ListIterator<Integer> confirmIterator = this.classUnderTest.listIterator(0);
        assertEquals(300, confirmIterator.next().intValue());
        assertEquals(200, confirmIterator.next().intValue());
        assertEquals(100, confirmIterator.next().intValue());      
    }
}