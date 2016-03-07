package algd2.ue02.linearlist.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import algd2.ue02.linearlist.IllegalGetOperationException;
import algd2.ue02.linearlist.IllegalRemoveOperationException;
import algd2.ue02.linearlist.InvalidListIndexException;
import algd2.ue02.linearlist.MySinglyLinkedList;
import algd2.ue02.linearlist.SinglyLinkedList;

public class SinglyLinkedListTest {
    
    private SinglyLinkedList<String> classUnderTest;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.classUnderTest = new MySinglyLinkedList();
    }

    @Test
    public void testEmptyConstructor() {
        /* nothing really to check here. Let's just see if no exception is thrown */
        this.classUnderTest = null;
        this.classUnderTest = new MySinglyLinkedList<String>();        
    }
    
    @Test
    public void testDeepCopyConstructorNormal()
    {
        // TODO: this test has a dependency to get. Is this ok?
        
        this.classUnderTest = null;
        
        /* create original list */
        SinglyLinkedList<String> originalList = new MySinglyLinkedList<String>();
        originalList.insertFirst("Fizz");
        originalList.insertFirst("Twisted Fate");
        originalList.insertFirst("Zed");
        
        /* create new list (deep copy) */
        SinglyLinkedList<String> copiedList = new MySinglyLinkedList<String>(originalList);
        
        /* assert that the strings are the same */
        assertEquals(originalList.get(0), copiedList.get(0));
        assertEquals(originalList.get(1), copiedList.get(1));
        assertEquals(originalList.get(2), copiedList.get(2));
        // TODO: do i have to ensure that the string objects are not of the same instance?
    }
    
    @Test(expected = IllegalArgumentException.class)
    /**
     * we call the deep copy constructor with null. Should throw a InvalidArgumentException
     */
    public void testDeepCopyConstructorNull()
    {
        this.classUnderTest = null;
        this.classUnderTest = new MySinglyLinkedList<String>(null);        
    }
    
    /**
     * Create a deep copy from an empty list
     */
    @Test    
    public void testDeepCopyConstructorEmptyList()
    {
        // TODO: this test has a dependency to size. Is this ok?
        this.classUnderTest = new MySinglyLinkedList<String>(this.classUnderTest);
        assertEquals(this.classUnderTest.size(), 0);
    }
    
    @Test
    public void testInsertFirstNormal()
    {
        // TODO: this test has a dependency to get. Is this ok?
        
        this.classUnderTest.insertFirst("Zed");
        this.classUnderTest.insertFirst("Twisted Fate");
        
        assertEquals(this.classUnderTest.get(1), "Zed");
        assertEquals(this.classUnderTest.get(0), "Twisted Fate");
    }
    
    @Test
    public void testInsertAfterNormal()
    {
        //TODO: depenedency to insertFirst and get
        // insert an element using insertFirst
        this.classUnderTest.insertFirst("Fizz");
        this.classUnderTest.insertAfter("Zed", 0);
        
        assertEquals(this.classUnderTest.get(1), "Zed");
    }
    
    @Test(expected=InvalidListIndexException.class)
    /**
     * Try to insertAfter using an index that is out of bounds
     */
    public void testInsertAfterInvalidIndex()
    {
        this.classUnderTest.insertFirst("Anivia");
        this.classUnderTest.insertAfter("Lux", 7);
    }
    
    @Test
    /**
     * Use insertAfter correct with index 0 on an empty list
     */
    public void testInsertAfterEmptyList()
    {
        this.classUnderTest.insertAfter("Karma", 0);
    }
    
    @Test
    /**
     * Insert one entry in a list with 3 existing entries.
     * Verify that the last entry enters at index 3.
     */
    public void testInsertAfterNormal3Elements()
    {
        // TODO: dependency to insertFirst(), get() .. ok?
        /* add 3 entries to the list first */
        this.classUnderTest.insertFirst("Fizz");
        this.classUnderTest.insertFirst("Brand");
        this.classUnderTest.insertFirst("Kassadin");
        
        this.classUnderTest.insertAfter("Malzahar", 2);
        assertEquals(this.classUnderTest.get(3), "Malzahar");
    }
    
    @Test
    public void testRemoveFirstNormal()
    {
        
        this.classUnderTest.insertFirst("Talon"); // add 1 entry first 
        
        this.classUnderTest.removeFirst();
        assertEquals(this.classUnderTest.size(), 0);
    }
    
    @Test
    public void testRomveFirst3Elements()
    {
        /* add 3 entries first */
        this.classUnderTest.insertFirst("Lulu"); // 2 -> 1
        this.classUnderTest.insertFirst("Le Blanc"); // 1 -> 0
        this.classUnderTest.insertFirst("Volibear"); // 0
        
        this.classUnderTest.removeFirst();
        
        assertEquals(this.classUnderTest.get(0), "Le Blanc");
        assertEquals(this.classUnderTest.get(1), "Lulu");
    }
    
    @Test(expected=IllegalRemoveOperationException.class)
    /**
     * Remove first on an empty list should throw an IllegalRemoveOperaionException
     */
    public void testRemoveFirstOnEmptyList()
    {
        this.classUnderTest.removeFirst();
    }

    @Test
    /**
     * add one item then remove it using index 0.
     * After that we should have en empty list
     */
    public void testRemoveNormal()
    {
        this.classUnderTest.insertFirst("Twisted Fate");
        this.classUnderTest.remove(0);
        assertEquals(this.classUnderTest.size(), 0);
    }
    
    @Test
    /**
     * Create a list with 3 items.
     * Remove the middle one. Make sure item 0 and 1 are still there
     */
    public void testRemove3ItemsRemoveMiddleItem()
    {
        this.classUnderTest.insertFirst("Janna"); // pos 2
        this.classUnderTest.insertFirst("Braum"); // pos 1
        this.classUnderTest.insertFirst("Morgana"); // pos 0
        
        this.classUnderTest.remove(1);
        
        assertEquals(this.classUnderTest.get(0), "Morgana");
        assertEquals(this.classUnderTest.get(1), "Janna");
    }
    
    @Test
    /**
     * Create a list with 3 items.
     * Remove the last one. Make sure item 0 and 1 are unchanged
     */
    public void testRemove3ItemsRemoveLastItem()
    {
        this.classUnderTest.insertFirst("Janna"); // pos 2
        this.classUnderTest.insertFirst("Braum"); // pos 1
        this.classUnderTest.insertFirst("Morgana"); // pos 0
        
        this.classUnderTest.remove(2);
        
        assertEquals(this.classUnderTest.get(0), "Morgana");
        assertEquals(this.classUnderTest.get(1), "Braum");
    }
    
    @Test(expected=IllegalRemoveOperationException.class)
    /**
     * Call remove on an empty list.
     * Should throw an IllegalRemoveOperationException
     */
    public void testRemoveOnEmptyList()
    {
        this.classUnderTest.remove(0);
    }
    
    @Test
    /**
     * Create a list with 3 items. Remove all. List size should be 0
     */
    public void testRemoveAllNormal()
    {
        this.classUnderTest.insertFirst("Hanna"); // pos 2
        this.classUnderTest.insertFirst("Jacob"); // pos 1
        this.classUnderTest.insertFirst("Gundela"); // pos 0
        
        this.classUnderTest.removeAll();
        
        assertEquals(0, this.classUnderTest.size());
    }
    
    @Test
    /**
     * Call removeAll on a liste with only one item. Should result in an empty list
     */
    public void testRemoveAll1Item()
    {
        this.classUnderTest.insertFirst("Patrick");
        
        this.classUnderTest.removeAll();
        
        assertEquals(0, this.classUnderTest.size());
    }
    
    @Test
    /**
     * Call getFirst on a list with 3 items. Should return item at position 0
     */
    public void testGetFirstNormal()
    {
        this.classUnderTest.insertFirst("Hanna"); // pos 2
        this.classUnderTest.insertFirst("Jacob"); // pos 1
        this.classUnderTest.insertFirst("Gundela"); // pos 0
        
        assertEquals("Gundela", this.classUnderTest.getFirst());
    }
    
    @Test
    /**
     * Call getFirst on a list with 1 item. Should return the item
     */
    public void testGetFirst1Item()
    {
        this.classUnderTest.insertFirst("Daniel");
        
        assertEquals("Daniel", this.classUnderTest.get(0));
    }
    
    @Test(expected=InvalidListIndexException.class)
    /**
     * Call getFirst on an empty list. Should throw an InvalidListIndexException
     */
    public void testGetFirstOnEmptyList()
    {
        this.classUnderTest.getFirst();
    }
    
    @Test
    /**
     * Create a list with 3 items. Call get with index 1. Should return the middle item
     */
    public void testGetNormal()
    {
        this.classUnderTest.insertFirst("Hanna"); // pos 2
        this.classUnderTest.insertFirst("Jacob"); // pos 1
        this.classUnderTest.insertFirst("Gundela"); // pos 0
        
        assertEquals("Jacob", this.classUnderTest.get(1));
    }
    
    @Test
    /**
     * Create a list with only one item. Call get with index 0. Should return the item.
     */
    public void testGet1Item()
    {
        this.classUnderTest.insertFirst("Carmen");
        
        assertEquals("Carmen", this.classUnderTest.get(0));
    }
    
    @Test(expected=InvalidListIndexException.class)
    /**
     * Call get(0) on an empty list. Should throw an InvalidListIndexException
     */
    public void testGetOnEmptyList()
    {
        this.classUnderTest.get(0);
    }
    
    @Test(expected=InvalidListIndexException.class)
    /**
     * Create a list with 3 items. Call get with index 25. Should throw an InvalidListIndexException
     */
    public void testGetIndexOutOfBounds()
    {
        this.classUnderTest.insertFirst("Hanna"); // pos 2
        this.classUnderTest.insertFirst("Jacob"); // pos 1
        this.classUnderTest.insertFirst("Gundela"); // pos 0
        
        this.classUnderTest.get(25);
    }
    
    @Test
    /**
     * Create a list with 3 items. Size() should return 3
     */
    public void testSizeNormal()
    {
        this.classUnderTest.insertFirst("Hanna"); // pos 2
        this.classUnderTest.insertFirst("Jacob"); // pos 1
        this.classUnderTest.insertFirst("Gundela"); // pos 0
        
        assertEquals(3, this.classUnderTest.size());
    }
    
    @Test
    /** 
     * Create a list with a single item. Size() should return 1
     */
    public void testSize1Item()
    {
        this.classUnderTest.insertFirst("IG Londeu");
        
        assertEquals(1, this.classUnderTest.size());
    }
    
    @Test
    /**
     * Call size on an empty list. Should return 0
     */
    public void testSizeOnEmptyList()
    {
        assertEquals(0, this.classUnderTest.size());
    }
}