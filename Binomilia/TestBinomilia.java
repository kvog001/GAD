package Binomilia;

public class TestBinomilia {
    public static void main(String arg[]){

        BinomialHeap heap = new BinomialHeap();
        heap.insert(15);
        heap.printTree();
        heap.insert(62);
        heap.printTree();
        heap.insert(87);
        heap.printTree();
        heap.insert(990);
        heap.printTree();
        heap.insert(21);
        heap.printTree();
        heap.insert(3);
        heap.printTree();
        heap.insert(25);
        heap.printTree();
        heap.insert(44);
        heap.printTree();

        heap.deleteMin();
        heap.printTree();

        heap.deleteMin();
        heap.printTree();

        heap.deleteMin();
        heap.printTree();

        //error
        heap.deleteMin();
        heap.printTree();

        heap.deleteMin();
        heap.printTree();

        heap.deleteMin();
        heap.printTree();

        heap.deleteMin();
        heap.printTree();

        heap.deleteMin();
        heap.printTree();




//        System.out.println("min()  -> "+heap.min());
//        System.out.println("getMinNode()  -> "+heap.getMinNode().min());


    }
}
