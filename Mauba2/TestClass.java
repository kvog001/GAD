package NeatMauba;

import java.util.ArrayList;

public class TestClass {
    public static void main(String arg[]){

//        ABTree tree = new ABTree(3, 5);
        ABTree tree = new ABTree(2, 4);
//        int[] valuesToAdd = new int[]{60,48,29,47,15,53,91,61,19,54};
        int[] valuesToAdd = new int[]{5,7,20,25,32,38,52,53,68,78,91,92,98,100,13,27,58,89,42};
//        int[] valuesToAdd = new int[]{5,8,3,4,6,7,9};
//        int[] valuesToAdd = new int[]{5,8,12,10,6};
//        int[] valuesToAdd = new int[]{10,19,14,28,1,3,5};
//        int[] valuesToAdd = new int[]{10,19,14,28,1,3,5,8,6,7};
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 0; i < valuesToAdd.length; i++) {
            values.add(valuesToAdd[i]);
            System.out.println("inserting "+valuesToAdd[i]);
            tree.insert(values.get(i));
            System.out.println(tree.dot());

        }

//        System.out.println(tree.leftMostKey(68)+" should be 78");
//        System.out.println(tree.rightMostKey(68)+ " should be 58");
//
//        tree.remove(61);
//        System.out.println(tree.dot());
//        tree.remove(47);
//        System.out.println(tree.dot());
//        tree.remove(91);
//        System.out.println(tree.dot());
//        tree.remove(48);
//        System.out.println(tree.dot());
//        tree.remove(29);
//        System.out.println(tree.dot());
//        tree.remove(53);
//        System.out.println(tree.dot());

    }
}
