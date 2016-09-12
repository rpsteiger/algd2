package algd2.ue05.avlbinarytree;

import algd2.ue04.binarytree.MyBinaryTree;

public class MyAVLBinaryTree extends MyBinaryTree {
    
    /*public static class Node
    {
        public int data;
        public int bal;
        public Node L;
        public Node R;
        public Node U;
    }*/
    
    public MyAVLBinaryTree()
    {
        super();
        this.root.U = this.root;
    }
    

}
