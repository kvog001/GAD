package DynamicaRingschlange;
/**
 * Die Klasse RingQueue soll eine zirkuläre Warteschlange auf
 * Basis der Klasse {@link DynamicArray} implementieren.
 */
public class RingQueue {
  private DynamicArray dynArr;
  
  private int size;
  
  private int from;
  
  private int to;
  
  public int getSize() {
    return size;
  }
  
  public boolean isEmpty() {
    /*
     * Todo
     */
    return (size == 0);
  }
  
  /**
   * Dieser Konstruktor erzeugt eine neue Ringschlange. Ein leere
   * Ringschlange habe stets eine Größe von 0, sowie auf 0
   * gesetzte Objektvariablen to und from. 
   * 
   * @param growthFactor der Wachstumsfaktor des zugrundeliegenden
   * dynamischen Feldes
   * @param maxOverhead der maximale Overhead des zugrundeliegenden
   * dynamischen Feldes
   */
  public RingQueue (int growthFactor, int maxOverhead) {
    dynArr = new DynamicArray(growthFactor, maxOverhead);
    size = 0;
    from = 0;
    to = 0;
  }
  
  /**
   * Diese Methode reiht ein Element in die Schlange ein.
   * 
   * @param value der einzufügende Wert
   */
  public void enqueue(int value) {
    /*
     * Todo
     */

    if (size == dynArr.getInnerLength()){
      Interval reallocate = dynArr.reportUsage(new NonEmptyInterval(from, to), size+1);
      to = reallocate.getTo();
      from = reallocate.getFrom();
    }

    if (size != 0)
      to=(to+1)%dynArr.getInnerLength();
    dynArr.set(to,value);
    size++;

  }

  
  /**
   * Diese Methode entfernt ein Element aus der Warteschlange.
   * 
   * @return das entfernte Element
   */
  public int dequeue() {
    /*
     * Todo
     */

    int result = 0;
    if(!isEmpty()){
      result = dynArr.getElement(from);
      size--;

      if(size!=0)
        from++;

      Interval reallocate = dynArr.reportUsage(new NonEmptyInterval(from, to), size);
      to = reallocate.getTo();
      from = reallocate.getFrom();
    }
    return result;
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
    output += "], size: "+size+", from "+from+" to "+to;

    return output;
  }
}
