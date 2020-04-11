package Sortieren;

abstract class SortingBase {

    protected boolean verbose = false;

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public abstract void sort(int[] numbers);

    public abstract String getName();
}
