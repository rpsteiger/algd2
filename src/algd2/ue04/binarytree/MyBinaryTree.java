package algd2.ue04.binarytree;

public class MyBinaryTree implements BinaryTree {
    private Node root;
    
    private static class Node
    {
        public int data;
        public Node L;
        public Node R;
        
        Node(int data)        
        {
            this.data = data;
        }
      }
    
    public MyBinaryTree()
    {
        /* create root dummy element */
        Node dummy = new Node(Integer.MIN_VALUE);
        this.root = dummy;
    }
    
    public MyBinaryTree(int[] data)
    {
        this();
        
        /* check if sorted array elements are not equal to Integer.MIN_VALUE.
         * Since this value is reserved to identify our dummy.
         */
        for(int i=0; i<data.length; i++)
        {
            if(data[i] == Integer.MIN_VALUE)
            {
                throw new IllegalArgumentException("Integer.MIN_VALUE cannot be stored in this collection");
            }
        }
        
        /* attach nodes to dummy.R by convention */
        this.root.R = this.buildTree(data, 0, data.length-1);        
    }
    
    private Node buildTree(int[] data, int start, int end)
    {
        Node result = null;
        if(start <= end)
        {
            int m = (start + end)/2;
            result = new Node(data[m]);
            result.L = this.buildTree(data, start, m-1);
            result.R = this.buildTree(data, m+1, end);
        }
        
        return result;
    }
    
    private void traverse(Node root, int level)
    {
        String spacing = "";
        for(int i=0; i<level; i++)
        {
            spacing += "    ";
        }
        
        if(root != null)
        {            
            traverse(root.R, level+1);
            System.out.println(spacing + root.data);
            traverse(root.L, level+1);
        }            
    }

    @Override
    public boolean exists(int data) {
        Node currentNode = this.root.R;
        
        while(currentNode != null)
        {
            if(currentNode.data == data)
            {
                return true;
            }
            
            currentNode = (currentNode.data < data) ? currentNode.R : currentNode.L;
        }
        
        return false;
    }

    @Override
    public void show() {
        this.traverse(this.root.R, 0);
    }
    
}
