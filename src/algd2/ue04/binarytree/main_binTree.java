package algd2.ue04.binarytree;

//******************************************************************************
//  FHNW.ALGD2  -  Excercise 4 : Testing Binary Search Trees                   *
// --------------------------------------------------------------------------  *
//  version 1  (2011-10-19)                                               vtg  *
//  version 2  (2013-09-26) 
//******************************************************************************

public class main_binTree{

  public static void main(String[] args){
    //int[] values = {12, 19, 31, 37, 43, 49, 51, 55, 67, 70, 78, 81, 87, 92, 99};
      int[] values = {9,12,14,17,19,23,50,54,67,72,76};
    VBinaryTree bt = new VBinaryTree(values);
    //BinaryTree bt = new BinaryTree();
    bt.show();
    /*
    System.out.println("------------------------------------");
    
//    bt.testInternalSearch(55);
//    System.out.println("---------------------------");
//    bt.testInternalSearch(37);
//    System.out.println("---------------------------");
//    bt.testInternalSearch(81);
//    System.out.println("---------------------------");
//    bt.testInternalSearch(0);
    
    //bt.insert(13);
    bt.insert(50);
    bt.show();
    System.out.println("------------------------------------");
    
    //bt.remove(51);
    //bt.remove(49);
    bt.remove(55);
    bt.remove(81);
    
    bt.show();
    */
  }

}
