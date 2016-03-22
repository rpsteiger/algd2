package algd2.ue04.binarytree.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import algd2.ue04.binarytree.BinaryTree;
import algd2.ue04.binarytree.MyBinaryTree;

public class MyBinaryTreeTest {
    private BinaryTree classUnderTest;
    
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
}
