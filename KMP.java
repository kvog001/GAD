import java.util.Arrays;

public class KMP {
    public static void main(String arg[]){

        int[] border = new int[10];
        char[] s = new char[]{'N','A','C','B','N','A','C','C','N','B'};
        int m = border.length;
        int i = 0;
        for (int j=2; j <= m; j++){
            //i = border[j-1]
            while (i >= 0 && (s[i] != s[j-1])){
                System.out.println("13"+Arrays.toString(border));
                i = border[i];
            }
            i++;
            border[j]=i;
            System.out.println("18"+Arrays.toString(border));
        }

        System.out.println(Arrays.toString(border));
    }
}
