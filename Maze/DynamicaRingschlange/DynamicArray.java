package DynamicaRingschlange;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Diese Klasse implementiert ein dynamisches Feld.
 */
public class DynamicArray {
  private int[] elements;

  /**
   * Diese Methode gibt die Größe des inneren
   * Feldes zurück.
   * 
   * @return die Größe des inneren Feldes
   */
  public int getInnerLength () {
    return elements.length;
  }

  private final int growthFactor;

  private final int maxOverhead;

  /**
   * Dieser Konstruktor initialisiert ein dynamishes Feld. Es muss dabei gelten,
   * dass
   * 
   * 1. growthFactor >= 1
   * 2. maxOverhead >= 2
   * 3. growthFactor < maxOverhead
   * 
   * @param growthFactor der Wachstumsfaktor; um diesen wird der interne Speicher
   * vergrößtert, wenn nicht mehr genug Platz zur Verfügung steht.
   * @param maxOverhead der maximale Overhead; wird weniger als [maximaler Overhead]-fache
   * des genutzten Speichers benötigt, so wird der interne Speicher verkleinert.
   */
  public DynamicArray (int growthFactor, int maxOverhead) {
    if (growthFactor < 1 || maxOverhead < 2 || maxOverhead <= growthFactor)
      throw new RuntimeException("DynamicArray(): Invalid arguments");
    this.growthFactor = growthFactor;
    this.maxOverhead = maxOverhead;
    elements = new int[0];
  }
  
  /**
   * Diese Methode erlaubt es dem Benutzer, das dynamische Feld über Änderungen
   * im verwendeten Feldbereich zu informieren. Die Methode passt ggf. den internen
   * Speicher an (Reallocation). Elemente dürfen dabei umkopiert und der verwendete Bereich
   * verschoben werden. Die Methode gibt zurück, wo sich die Elemente, die
   * sich vor dem Aufruf in Verwendung befanden, nach dem Aufruf befinden.
   * 
   * Bezüglich der Speicherverwaltung, die z.B. mit Hilfe einer privaten Hilfsfunktion 
   * implementiert werden kann, gelten die folgenden Regeln: 
   * 
   * - Wird aktuell mehr Speicher benötigt, als vorhanden ist,
   * wird die Größe des internen Speichers derart angepasst, dass er anschließend
   * [Speicherforderung]*[Wachstumsfaktor] viele Elemente fassen kann.
   * 
   * - Ist die Speicherforderung zu gering, ist also der bereitgestellte Speicher
   * mehr als um das [Maximaler Overhead]-fache größter als die Speicherforderung,
   * wird die Größe des internen Speichers derart angepasst, dass er anschließend
   * [Speicherforderung]*[Wachstumsfaktor] viele Elemente fassen kann.
   * 
   * @param usage gibt an, welche Elemente des dynamischen Feldes aktuell in Benutzung
   * befindlich sind; es kann ein beliebiger Bereich in Benutzung sein. Insbesondere
   * kann es vorkommen, dass usage.getFrom() größer ist als usage.getTo() - in diesem
   * Fall sind die Elemente [usage.getFrom(); elements.length - 1] und [0; usage.getTo()]
   * in Benutzung.
   * 
   * @param minSize gibt die minimale Größe benötigten Feldbereiches nach dem Aufruf
   * an; der Aufruf löscht niemals Elemente. Es gilt also, dass nach dem Aufruf 
   * max(usage.getSize(), minSize) viel Platz zur Verfügung steht.
   * 
   * @return ein neues Intervall, in dem sich alle Elemente in ungeänderter Reihenfolge
   * befinden, die vor dem Auruf in Verwendung waren
   */
  public Interval reportUsage(Interval usage, int minSize) {
    /*
     * Todo
     */

    int from = usage.getFrom(), to = usage.getTo();
    if (from > to){

      int temp = elements.length-from;
      reallocateFromBiggerTo(from,to,minSize * growthFactor);
      from = 0;
      to = temp;

      return new NonEmptyInterval(from,to);
    }

    if ( minSize > elements.length ){
      reallocate(minSize * growthFactor);

    } else if((maxOverhead * minSize) < elements.length){ //when reportUsage() method gets called by get() method

      reallocateFromTo(from,minSize,(minSize * growthFactor));

      if (minSize == 0){
        to = from = 0;
      } else{
        to = to-from;
        from = 0;
      }
    }

    return new NonEmptyInterval(from,to);
  }

  private void reallocateFromBiggerTo(int from, int to, int size){
    int[] tempArray = new int[size];
    int lngth = elements.length-from;
    System.arraycopy(elements,from,tempArray,0,lngth);
    tempArray[elements.length-from] = elements[0];
    elements = tempArray;
  }

  private void reallocateFromTo(int from, int leftElements, int size){
    if (size==0){
      elements = new int[0];
      return;
    }
    int[] tempArray = new int[size];
    System.arraycopy(elements,from,tempArray,0,leftElements);
    elements = tempArray;
  }

  /**
   * Diese Methode vergrößert das dynamische Feld
   * @param newSize
   */
  private void reallocate(int newSize){

    if (elements.length == 0){
      elements = new int[newSize];
      return;
    }

    int[] tempArray = new int[newSize];
    int nrOfElements;
    if (newSize < elements.length)
      nrOfElements = newSize;
    else
      nrOfElements = elements.length;
    for (int i=0; i < nrOfElements; i++)
      tempArray[i] = elements[i];

    elements = tempArray;
  }



  /**
   * Diese Methode holt ein Element aus dem dynamischen Feld.
   * 
   * @param index der Index desjenigen Elementes, das ermittelt
   * werden soll
   * @return das ermittelte Element
   */
  public int get(int index) {
    /*
     * Todo
     */
    int result = 0;
    if (index >= 0 && index < elements.length){
      result = elements[index];
      elements[index] = 0;
    }


    else {
      System.out.println("get() method has bad index. return 0 by default"); //todo comment out
      return 0;
    }

    if (index > 0 && index * maxOverhead < elements.length){
      reallocate(index * growthFactor);
    } else if( index == 0 && index * maxOverhead < elements.length){
      elements = new int[0];
    }

    return result;
  }

  public int getElement(int index) {
    int result =0;
    if (index >= 0 && index < elements.length)
      result = elements[index];
    return result;
  }
  
  /**
   * Diese Methode setzt des Wert des dynamischen Feldes an einem
   * bestimmten Index auf einen Wert.
   * 
   * @param index der Index des zu setzenden Elementes
   * @param value der Wert des zu setzenden Elementes
   */
  public void set(int index, int value) {
    /*
     * Todo
     */

    if (elements.length == 0){
      reallocate(growthFactor);
    }

    if (index == elements.length)
      reallocate(growthFactor * (elements.length+1));


    if (index >= 0 && index < elements.length)
      elements[index] = value;
    else {
      System.out.println("set(index,value) has bad index"); //todo comment out
    }

  }

}
