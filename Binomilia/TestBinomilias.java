package Binomilia;

public class TestBinomilias {
    public static void main(String arg[]){
        BinomialHeap heap = new BinomialHeap();
//        int[] valuesToInsert = new int[]{15,62,87,990,21,3,25,44};
        int[] valuesToInsert = new int[]{93,80,78,65,90,53,54,7,81,81};

        for (int i=0; i<valuesToInsert.length;i++){
            heap.insert(valuesToInsert[i]);
            heap.printTree();
        }

//        for (int i=0; i<valuesToInsert.length;i++){
//            heap.deleteMin();
//            heap.printTree();
//        }

        heap.deleteMin();
        heap.printTree();
//
        heap.deleteMin();
        heap.printTree();

//        heap.deleteMin();
//        heap.printTree();
//
//        heap.deleteMin();
//        heap.printTree();

    }
}
