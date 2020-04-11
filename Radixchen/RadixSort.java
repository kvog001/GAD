package Radixchen;
import java.util.ArrayList;
import java.util.Iterator;

public class RadixSort {

    private static ArrayList<Integer>[] buckets;

    private static int key(Integer element, int decimalPlace) throws IllegalArgumentException {
        // TODO
        if(element < 0){
            throw new IllegalArgumentException("Negative numbers not allowed!");
        }
        if (decimalPlace > Integer.toString(element).length()-1)
            return 0;

        String el = Integer.toString(element);
        char result = el.charAt(el.length()-decimalPlace-1);
        return Character.getNumericValue(result);
    }

    private static void concatenate(ArrayList<Integer>[] buckets, Integer[] elements) {
        // TODO
        int stelle = 0;
        for (int i = 0; i < buckets.length ; i++) {
            if (buckets[i] != null){
                Iterator<Integer> bucketIterator = buckets[i].iterator();
                while (bucketIterator.hasNext()){
                    elements[stelle] = bucketIterator.next();
                    stelle++;
                }
            }
        }
    }

    private static void kSort(Integer[] elements, int decimalPlace) {
        // TODO
        for (int i = 0; i < elements.length; i++) {
            if (buckets[ key(elements[i],decimalPlace) ] == null){
                buckets[ key(elements[i],decimalPlace) ] = new ArrayList<>();
            }
            buckets[key(elements[i],decimalPlace)].add(elements[i]);
        }
        concatenate(buckets,elements);
    }

    private static int getMaxDecimalPlaces(Integer[] elements) {
        // TODO
        int max = elements[0];
        for (int i = 0; i < elements.length ; i++) {
            if (max < elements[i])
                max = elements[i];
        }
        return Integer.toString(max).length();
    }

    public static void sort(Integer[] elements, boolean verbose) {
        // TODO: sortiere das Array "elements"
        // TODO: falls "verbose" auf "true", gib die Zwischenergebnisse aus!
        // Verwende dafür die statische Methode "printArray..." aus der "Program" Klasse:
        for (int i = 0; i < getMaxDecimalPlaces(elements) ; i++) {
            buckets = new ArrayList[10]; //for 10 decimal places: 0-9
            kSort(elements,i);
            if (verbose)
                Program.printArray(elements);
        }
    }
}
