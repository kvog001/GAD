package DoubleHashie;
public class IntHashableFactory implements HashableFactory<Integer> {

  @Override public DoubleHashable<Integer> create (int size) {
    return new DoubleHashInt(size);
  }

}
