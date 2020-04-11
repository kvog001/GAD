package DynamicaRingschlange;
/**
 * Die Klasse DynamicStack soll einen Stapel auf
 * Basis der Klasse {@link DynamicArray} implementieren.
 */
public class DynamicStack {
  private DynamicArray dynArr;
  
  /**
   * Dieses Feld speichert die Anzahl der Elemente auf dem Stapel.
   */
  private int length;
  
  public int getLength() {
    return length;
  }
  
  public DynamicStack (int growthFactor, int maxOverhead) {
    dynArr = new DynamicArray(growthFactor, maxOverhead);
    length = 0;
  }
  
  /**
   * Diese Methode legt ein Element auf den Stapel.
   * 
   * @param value das Element, das auf den Stapel gelegt werden soll
   */
  public void pushBack (int value) {
    /*
     * Todo
     */

    dynArr.set(length,value);
    length++;
  }

  /**
   * Diese Methode nimmt ein Element vom Stapel.
   * @return das entfernte Element
   */
  public int popBack () {
    /*
     * Todo
     */
    if (getLength() == 0){
      System.out.println("bad call of popBack(), length = 0 : return 0 by default");
      return 0;
    }

    int lastElement = 0;

    if (length > 0){
      length--;
      lastElement = dynArr.get(length);
    }

    return lastElement;
  }

  public int getElementAtIndex(int index){
    return dynArr.getElement(index);
  }

  public int getSize(){
    return dynArr.getInnerLength();
  }

  @Override
  public String toString(){

    String output = "[";

    for (int i=0; i < dynArr.getInnerLength(); i++){
      if (i != dynArr.getInnerLength()-1)
        output += dynArr.getElement(i)+", ";
      else
        output += dynArr.getElement(i);
    }
    output += "], length: "+length;

    return output;
  }
}
