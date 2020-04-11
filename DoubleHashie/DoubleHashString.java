package DoubleHashie;
/**
 * Die Klasse {@link DoubleHashString} kann dazu verwendet werden,
 * Strings zu hashen.
 */
public class DoubleHashString implements DoubleHashable<String> {
  /*
   * Todo
   */
  private int tableSize;

  /**
   * Dieser Konstruktor initialisiert ein {@link DoubleHashString}
   * Objekt für einen gegebenen Maximalwert (size - 1) der gehashten
   * Werte.
   * 
   * @param size die Größe der Hashtabelle
   */
  public DoubleHashString (int size) {
    /*
     * Todo
     */
    this.tableSize = size;
  }
  
  /**
   * Diese Methode berechnet h(key) für einen String.
   * 
   * @param key der Schlüssel, der gehasht werden soll
   * @return der Hashwert des Schlüssels
   */
  public long hash (String key) {
    /*
     * Todo
     */
    int summe = 0;
    for (int i=0; i<key.length(); i++){
      summe += (int) key.charAt(i);
    }
    return summe % tableSize;
  }
  
  /**
   * Diese Methode berechnet h'(key) für einen String.
   * 
   * @param key der Schlüssel, der gehasht werden soll
   * @return der Hashwert des Schlüssels
   */
  public long hashTick (String key) {
    /*
     * Todo
     */
    int k = 0;
    for (int i=0; i<key.length(); i++){
      k += (int) key.charAt(i);
    }
    return 1 + (k % (tableSize-1));
  }
}
