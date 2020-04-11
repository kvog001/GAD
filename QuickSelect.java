import java.util.Arrays;

public class QuickSelect {
    private static int[] a = new int[]{};

    public static void main(String arg[]){
        a = new int[]{7,10,4,3,20,15};

        System.out.println(quickSelect(0,a.length-1,2));
    }

    //returns (k-1)-th smallest element in the array
    //for example if u want 3rd smallest element, k must be 2
    //for k u get k+1 smallest element
    private static int quickSelect( int l, int r, int k){
        if (r == l)
            return a[l];
        //todo int z = random position in array ???????????
        int pivot = a[r];
        int i = l-1;
        int j = r;

        do {
            do { i++; }while (a[i] < pivot);
            do { j--; }while (a[j] > pivot && j != l);

            if (i < j)
                swap(i,j);
        }while ( i < j);
        swap(i,r);
//        System.out.println(Arrays.toString(a) + "  k = "+k+"  , i = "+i); //for debugging purposes
        //also wir vergleichen k mit index des vertauschten pivots
        if (k < i) return quickSelect(l,i-1,k);
        if (k > i) return quickSelect(l+1,r,k);
        else return a[k];
    }

    private static void swap(int first, int second ) {
        int temp = a[first];
        a[first] = a[second];
        a[second] = temp;
    }
}
