package algd2.ue04.binarytree.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import algd2.ue04.binarytree.IllegalRemoveOperationException;
import algd2.ue04.binarytree.BinaryTree;
import algd2.ue04.binarytree.DuplicateInsertException;
import algd2.ue04.binarytree.MyBinaryTree;
import algd2.ue04.binarytree.MyBinaryTree.SearchResult;

public class MyBinaryTreeTest {
    private BinaryTree classUnderTest;
    
    private void createBinaryTree()
    {
        int[] sortedData = {9,12,14,17,19,23,50,54,67,72,76};
        this.classUnderTest = null;
        this.classUnderTest = new MyBinaryTree(sortedData);
    }
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception
    {
        this.classUnderTest = new MyBinaryTree();
    }

    @Test
    /**
     * Not much to do here. We just verify that no exception is thrown.
     */
    public void testConstructorNormal()
    {
        this.classUnderTest = null;
        this.classUnderTest = new MyBinaryTree();
    }
    
    @Test
    /**
     * Again we just verify that no exception is thrown.
     */
    public void testConstructorSortedArray()
    {
        int[] sortedData = {9,12,14,17,19,23,50,54,67,72,76};
        
        this.classUnderTest = null;
        this.classUnderTest = new MyBinaryTree(sortedData);
    }
    
    @Test(expected=IllegalArgumentException.class)
    /**
     * Call sorted array constructor with Integer.MIN_VALUE. 
     * Should throw an IllegalArgumentException.
     */
    public void testConstructorSortedArrayWithIntegerMinValue()
    {
        int[] sortedData = {Integer.MIN_VALUE, 3};
        this.classUnderTest = null;
        this.classUnderTest = new MyBinaryTree(sortedData);
    }
    
    @Test
    /**
     * Create a tree using the sorted array constructor. Then call show.
     */
    public void testShowIntegrationTestNormal()
    {
        int[] sortedData  = {9,12,14,17,19,23,50,54,67,72,76};
        
        this.classUnderTest = new MyBinaryTree(sortedData);
        this.classUnderTest.show();
    }
    
    @Test
    /**
     * Create a tree using the sorted array constructor. 
     * The item we are looking for exists in our sorted array, so we just verify that exists() returns true.
     */
    public void testExistsNormal()
    {
        int[] sortedData = {1,2,3,5,10};
        this.classUnderTest = new MyBinaryTree(sortedData);
        
        assertTrue(this.classUnderTest.exists(10));
    }
    
    @Test
    /**
     * Call exists on a tree where the element does not exist.
     * Should return false.
     */
    public void testExistsDoesNotExist()
    {
        int[] sortedData = {1,2,3,4};
        this.classUnderTest = new MyBinaryTree(sortedData);
        
        assertFalse(this.classUnderTest.exists(4000));
    }
    
    @Test
    /**
     * Call exists on an empty tree.
     * Should return false.
     */
    public void testExistsOnEmptyTree()
    {
        assertFalse(this.classUnderTest.exists(3000));
    }
    
    @Test
    /**
     * Create binary tree. Search for 12. Make sure all the properties of SearchResult are correct.
     */
    public void testFindNormal()
    {
        this.createBinaryTree();
        MyBinaryTree tree = (MyBinaryTree) this.classUnderTest;
        SearchResult searchResult = tree.find(12);
        
        assertNotNull(searchResult.node);
        assertEquals(12, searchResult.node.data);
        
        assertNotNull(searchResult.parent);
        assertEquals(9, searchResult.parent.data);
        
        assertFalse(searchResult.isLeftChild);
    }
    
    @Test
    /**
     * Create binary tree. Search for 8 which doesnt exist.
     * Make sure that SearchResult is as expected.
     */
    public void testFindDoesntExist()
    {
        this.createBinaryTree();
        MyBinaryTree tree = (MyBinaryTree) this.classUnderTest;
        SearchResult searchResult = tree.find(8);
        
        assertNull(searchResult.node);
        assertTrue(searchResult.isLeftChild);
        
        assertNotNull(searchResult.parent);
        assertEquals(9, searchResult.parent.data);
    }
    
    @Test(expected=DuplicateInsertException.class)
    /**
     * Create binary tree. Try to insert element 17 (which allready exists).
     * Should throw an exception.
     */
    public void testInsertAllreadyExists()
    {
        this.createBinaryTree();
        this.classUnderTest.insert(17);
    }
    
    @Test
    /**
     * Create binary tree. Insert element 8 and make sure it was added as left element.
     */
    public void testInsertLeft()
    {
        this.createBinaryTree();
        boolean result = this.classUnderTest.insert(8);
        
        assertTrue(result);
        assertTrue(this.classUnderTest.exists(8));
        
        MyBinaryTree tree = (MyBinaryTree) this.classUnderTest;
        SearchResult searchResult = tree.find(8);
        assertNotNull(searchResult.node);
        assertTrue(searchResult.isLeftChild);
        
        this.classUnderTest.show();
    }
    
    @Test
    /**
     * Create binary tree. Insert element 13 and make sure it was added as right element.
     */
    public void testInsertRight()
    {
        this.createBinaryTree();
        boolean result = this.classUnderTest.insert(13);
        
        assertTrue(result);
        assertTrue(this.classUnderTest.exists(13));
        
        MyBinaryTree tree = (MyBinaryTree) this.classUnderTest;
        SearchResult searchResult = tree.find(13);
        assertNotNull(searchResult.node);
        assertFalse(searchResult.isLeftChild);      
        
        this.classUnderTest.show();
    }
    
    @Test(expected=IllegalRemoveOperationException.class)
    /**
     * Create binary tree. Try to remove element 10 which doesnt exist in the binary tree.
     * Should throw an exception.
     */
    public void testRemoveItemDoesntExist()
    {
        this.createBinaryTree();
        this.classUnderTest.remove(10);
    }
    
    @Test
    /**
     * Create binary tree. Remove element with no children // case 1
     */
    public void testRemoveCase1()
    {
        this.createBinaryTree();
        this.classUnderTest.remove(12);
        
        assertFalse(this.classUnderTest.exists(12));
    }
    
    @Test
    /**
     * Create binary tree. Remove element with one child // case 2
     */
    public void testRemoveCase2()
    {
        this.createBinaryTree();
        this.classUnderTest.remove(9);
        
        assertFalse(this.classUnderTest.exists(9));
        
        /* make sure 14.L points to 12 */
        MyBinaryTree tree = (MyBinaryTree) this.classUnderTest;
        SearchResult movedElement = tree.find(12);
        assertEquals(14, movedElement.parent.data);        
    }
    
    @Test
    /**
     * Create binary tree. Remove element with two children // case 3
     */
    public void testRemoveCase3()
    {
        this.createBinaryTree();
        this.classUnderTest.remove(14); // delete 14. 12 should be found as replacement node
        
        assertFalse(this.classUnderTest.exists(14));
        
        /* make sure 23.L points to 12 */
        MyBinaryTree tree = (MyBinaryTree) this.classUnderTest;
        SearchResult movedElement = tree.find(12);
        assertEquals(23, movedElement.parent.data);
        
        /* check if 12 replaces the remove element correct. L and R are linked properly */
        assertEquals(9, movedElement.node.L.data);
        assertEquals(17, movedElement.node.R.data);
        
        /* make sure that moved element isnt linked as biggest left element anymore. Link 9 -> 12 was set to null */
        assertNull(tree.find(9).node.R);
        
        this.classUnderTest.show();
    }
}
