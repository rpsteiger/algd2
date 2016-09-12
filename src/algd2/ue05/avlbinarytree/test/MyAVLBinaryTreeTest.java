package algd2.ue05.avlbinarytree.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import algd2.ue04.binarytree.BinaryTree;
import algd2.ue05.avlbinarytree.MyAVLBinaryTree;

public class MyAVLBinaryTreeTest {
    
    private BinaryTree classUnderTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    /**
     * Pretty simple. Would like to check whether the root node points U to itself
     * but that is not possible since we have no access to the nodes from outside.
     */
    public void testConstructorNormal() {
        this.classUnderTest = null;
        this.classUnderTest = new MyAVLBinaryTree();        
    }

}
