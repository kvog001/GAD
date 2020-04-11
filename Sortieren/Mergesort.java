package Sortieren;

public class Mergesort extends SortingBase {

    @Override
    public void sort(int[] numbers) {
        sort(numbers, 0, numbers.length - 1);
    }

    @Override
    public String getName() {
        return "Mergesort";
    }

    protected void sort(int[] numbers, int l, int r) {

        int[] b = new int[numbers.length];

        if (l == r) return;
        int m = (r+l)/2;
        sort(numbers,l,m);
        sort(numbers,m+1,r);

        int j = l;
        int k = m+1;

        for (int index = 0; index <= r-l ; index++) {

            if (j > m){
                b[index] = numbers[k];
                k++;

            }

            else {

                if (k > r){
                    b[index] = numbers[j];
                    j++;

                }

                else {

                    if (numbers[j] <= numbers[k]){
                        b[index] = numbers[j];
                        j++;
                    }

                    else{
                        b[index] = numbers[k];
                        k++;
                    }
                }
            }
        }

        for (int i = 0; i <= r-l ; i++) {
            numbers[l+i]=b[i];
        }

        if (verbose) {
            Program.printArray("", numbers);
        }
    }
}
