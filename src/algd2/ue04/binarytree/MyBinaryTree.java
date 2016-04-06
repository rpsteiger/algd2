package algd2.ue04.binarytree;

public class MyBinaryTree implements BinaryTree {
    private Node root;
    
    public static class Node
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
    
    public SearchResult find(int data)
    {
        SearchResult result = new SearchResult(this.root.R, null, false);
        
        while(result.node != null)
        {
            if(result.node.data == data)
            {
                return result;
            }
            
            result.parent = result.node;
            if(data > result.node.data)
            {
                result.isLeftChild = false;
                result.node = result.node.R;
            }
            else
            {
                result.isLeftChild = true;
                result.node = result.node.L;
            }
        }
        
        return result;
    }
    
    public static class SearchResult
    {
        public Node node;
        public Node parent;
        public boolean isLeftChild;
        
        public SearchResult(Node node, Node parent, boolean isLeftChild)
        {
            this.node = node;
            this.parent = parent;
            this.isLeftChild = isLeftChild;
        }
    }

    @Override
    public boolean insert(int data) {
        SearchResult searchResult = this.find(data);
        
        if(searchResult.node != null)
        {
            throw new DuplicateInsertException();
        }
        
        Node newNode = new Node(data);
        
        if(searchResult.isLeftChild)
        {
            searchResult.parent.L = newNode;
        }
        else
        {
            searchResult.parent.R = newNode;
        }
        
        return true;
    }

    @Override
    public boolean remove(int data) {
        SearchResult searchResult = this.find(data);
        if(searchResult.node == null)
        {
            throw new IllegalRemoveOperationException();
        }
        
        /* Node has no children -> remove */
        if(searchResult.node.L == null && searchResult.node.R == null)
        {
            if(searchResult.isLeftChild)
            {
                searchResult.parent.L = null;
            }
            else 
            {
                searchResult.parent.R = null;
            }
            
            return true;
        }
        
        /* Node has one child -> skip node */
        if(searchResult.node.L == null ^ searchResult.node.R == null)
        {
            Node childNode = (searchResult.isLeftChild)? searchResult.node.L : searchResult.node.R;
            
            if(searchResult.isLeftChild)
            {
                searchResult.parent.L = childNode;
            }
            else 
            {
                searchResult.parent.R = childNode;
            }
            
            return true;
        }
        
        /* Node has two children */        
        if(searchResult.node.L != null && searchResult.node.R != null)
        {
            // find biggest key from left subtree
            SearchResult replaceNode = new SearchResult(searchResult.node.L,searchResult.node, true); 
            while(replaceNode.node.R != null) 
            {
                replaceNode.parent = replaceNode.node;
                replaceNode.node = replaceNode.node.R;
            }
            
            // set isLeftChild for replaceNode. We need the parent in order to determine that
            replaceNode.isLeftChild = (replaceNode.parent.L != null && replaceNode.parent.L == replaceNode.node);

            // replace node with replacement from left subtree 
            replaceNode.node.L = searchResult.node.L;
            replaceNode.node.R = searchResult.node.R;
            
            if(searchResult.isLeftChild)
            {
                searchResult.parent.L = replaceNode.node;
            }
            else 
            {
                searchResult.parent.R = replaceNode.node;
            }
            
            // delete replace node from original location
            if(replaceNode.isLeftChild)
            {
                replaceNode.parent.L = null;
            }
            else
            {
                replaceNode.parent.R = null;
            }            
            
            return true;
        }
        
        return false;
    }
    
}
