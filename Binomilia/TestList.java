package Binomilia;

import java.util.ArrayList;
import java.util.List;

public class TestList {
    public static void main(String args[]){
        List<Integer> list = new ArrayList<>();
        list.add(2);
        System.out.println(list);
        list.add(4);
        System.out.println(list);
        list.add(60);
        System.out.println(list);
        list.add(20);
        System.out.println(list);
//        list.set(3,2);
        list.set(list.indexOf(20),50);
        System.out.println(list);
        list.remove(1);
        System.out.println(list);
        list.add(0,4222);
        System.out.println(list);
    }
}
