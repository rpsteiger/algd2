package algd2.ue02.stack.test;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import algd2.ue02.stack.MyStack;
import algd2.ue02.stack.Stack;

public class MyStackTest {
    private Stack<Integer> classUnderTest = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.classUnderTest = new MyStack<Integer>();
    }
    
    @Test    
    public void testPushNormal()
    {
        this.classUnderTest.push(2);
        
        Integer expected = 2;
        Integer actual = this.classUnderTest.top();        
        
        assertEquals(expected, actual);
    }
    
    @Test(expected=IllegalArgumentException.class)
    /**
     * Try to push null onto the stack.
     * Should throw an IllegalArgumentException
     */
    public void testPushNull()
    {
        this.classUnderTest.push(null);
    }
    
    @Test
    /**
     * Push 3 items on to the stack.
     * Then pop them one by one.
     */
    public void testPush3Items()
    {
        this.classUnderTest.push(25); 
        this.classUnderTest.push(26);
        this.classUnderTest.push(27);
        
        /* stack be like
         * -> 27
         * -> 26
         * -> 25
         */
         
        Integer expected, actual;
        expected = this.classUnderTest.pop();
        actual = 27;
        assertEquals(expected, actual);
        expected = this.classUnderTest.pop();
        actual = 26;
        assertEquals(expected, actual);
        expected = this.classUnderTest.pop();
        actual = 25;
        assertEquals(expected, actual);
    }
    
    @Test
    /**
     * Push 1 item on the stack. Then read it with top
     */
    public void testTopNormal()
    {
        this.classUnderTest.push(265);
        Integer expected, actual;
        expected = 265;
        actual = this.classUnderTest.top();
        
        assertEquals(expected, actual);
    }
    
    @Test
    /**
     * Push 2 items on the stack. Call top. Should return the item that was added to the stack last.
     */
    public void testTop2Items()
    {
        this.classUnderTest.push(345);
        this.classUnderTest.push(4000);
        
        Integer expected, actual;
        expected = 4000;
        actual = this.classUnderTest.pop();
        
        assertEquals(expected, actual);
    }
    
    @Test(expected=EmptyStackException.class)
    /**
     * Call top when the stack is empty.
     * Should throw a EmptyStackException
     */
     public void testTopOnEmptyStack()
     {
        this.classUnderTest.top();
     }
    
    @Test
    /**
     * Push one item to the stack then remove it again with pop
     */
    public void testPopNormal()
    {
        Integer expected = 680;
        this.classUnderTest.push(expected);
        Integer actual = this.classUnderTest.pop();
        
        assertEquals(expected.intValue(), actual.intValue());
    }
    
    @Test
    /**
     * Push 3 items to the stack then remove them using popping
     */
    public void testPop3Items()
    {
        Integer expected1 = 30, expected2 = 40, expected3 = 85;        
        
        this.classUnderTest.push(expected1);
        this.classUnderTest.push(expected2);
        this.classUnderTest.push(expected3);
        
        assertEquals(expected3.intValue(), this.classUnderTest.pop().intValue());
        assertEquals(expected2.intValue(), this.classUnderTest.pop().intValue());
        assertEquals(expected1.intValue(), this.classUnderTest.pop().intValue());
    }
    
    @Test(expected=EmptyStackException.class)
    /**
     * Call pop on an empty stack. Should throw EmptyStackException.
     */
    public void testPopOnEmptyStack()
    {
        this.classUnderTest.pop();
    }
    
    @Test
    public void testIsEmptyWhenEmpty()
    {
        assertEquals(true, this.classUnderTest.isEmpty());
    }
    
    @Test
    public void testIstEmptyWhenNotEmpty()
    {
        this.classUnderTest.push(90109109);
        assertEquals(false, this.classUnderTest.isEmpty());
    }
}
