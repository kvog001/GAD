package Binomilia;

import java.util.Arrays;

public class Test {
    public static void main(String arg[]){
        BinomialTreeNode a = new BinomialTreeNode(20);
        BinomialTreeNode b = new BinomialTreeNode(40);
        BinomialTreeNode c = new BinomialTreeNode(60);
        BinomialTreeNode d = new BinomialTreeNode(80);
//        BinomialTreeNode.merge(a,b);
//        BinomialTreeNode.merge(a,d);
//        BinomialTreeNode.merge(c,b);
//        a.print();

        BinomialHeap heap = new BinomialHeap();
        heap.insert(3);
        heap.printTree();
        heap.insert(4);
        heap.printTree();
        heap.insert(5);
        heap.printTree();
        heap.insert(2);
        heap.printTree();


        heap.insert(17);
        heap.printTree();

        heap.insert(20);
        heap.printTree();

        heap.insert(50);
        heap.printTree();

//        heap.insert(30);
        heap.insert(1);
        heap.printTree();


        int[] valuesToInsert = new int[]{40,16,9,31,0};
        for (int i=0; i<valuesToInsert.length;i++){
            heap.insert(valuesToInsert[i]);
            heap.printTree();
        }

//        System.out.println("minimum = "+heap.min());
//        heap.insert(2);
//        heap.printTree();
    }
}
