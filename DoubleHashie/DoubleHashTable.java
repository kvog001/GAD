package DoubleHashie;
import java.util.*;

/**
 * Die Klasse DoubleHashTable implementiert eine Hashtabelle, die doppeltes
 * Hashing verwendet.
 *
 * @param <K> der Typ der Schlüssel, die in der Hashtabelle gespeichert werden
 * @param <V> der Typ der Werte, die in der Hashtabelle gespeichert werden
 */
public class DoubleHashTable<K, V> {
  /*
   * Todo
   */
  private Pair[] hashTable;
  private DoubleHashable<K> doubleHashing;
  private int primeSize, maxRehashOp = 0;

  /**
   * Diese Methode implementiert h(x, i).
   * 
   * @param key der Schlüssel, der gehasht werden soll
   * @param i der Index, der angibt, der wievielte Hash für den gegebenen Schlüssel
   * berechnet werden soll
   * @return der generierte Hash
   */
  private int hash(K key, int i) {
    /*
     * Todo
     */

    return (int) ( doubleHashing.hash(key) + i* doubleHashing.hashTick(key) ) % primeSize;
  }

  /**
   * Dieser Konstruktor initialisiert die Hashtabelle.
   * 
   * @param primeSize die Größe 'm' der Hashtabelle; es kann davon ausgegangen
   * werden, dass es sich um eine Primzahl handelt.
   * @param hashableFactory Fabrik, die aus einer Größe ein DoubleHashable<K>-Objekt erzeugt.
   */
  public DoubleHashTable(int primeSize, HashableFactory<K> hashableFactory) {
    /*
     * Todo
     */
    this.primeSize = primeSize;
    this.doubleHashing = hashableFactory.create(primeSize);
    this.hashTable = new Pair[primeSize];
  }

  /**
   * Diese Methode fügt entsprechend des doppelten Hashens ein Element
   * in die Hashtabelle ein.
   * 
   * @param k der Schlüssel des Elements, das eingefügt wird
   * @param v der Wert des Elements, das eingefügt wird
   * @return 'true' falls das einfügen erfolgreich war, 'false' falls die
   * Hashtabelle voll ist.
   */
  public boolean insert(K k, V v) {
    /*
     * Todo
     */

    int i=0, rehashCounter = 0;
    while (i < primeSize){
      int hashedIndex = this.hash(k,i);
      if ( emptySlot(hashedIndex) ){
        insert( hashedIndex , new Pair(k,v));
        return true;

      } else if (getKey(hashedIndex).equals(k) ){
          insert(hashedIndex, new Pair(k,v));
          return true;
        }

      rehashCounter++;
      i++;

      if(i==primeSize && maxRehashOp < rehashCounter-1){
        maxRehashOp = rehashCounter-1;
      }
    }

    return false;
  }

  private boolean emptySlot(int index){
    return hashTable[index] == null;
  }

  private void insert(int index, Pair pair){
    hashTable[index] = pair;
  }

  private K getKey(int index){ //todo Object return type and remove casting
    return (K) hashTable[index]._1;
  }

  private V getValue(int index){ //todo Object return type and remove casting
    return (V) hashTable[index]._2;
  }

  /**
   * Diese Methode sucht ein Element anhand seines Schlüssels in der Hashtabelle
   * und gibt den zugehörigen Wert zurück, falls der Schlüssel gefunden wurde.
   * 
   * @param k der Schlüssel des Elements, nach dem gesucht wird
   * @return der Wert des zugehörigen Elements, sofern es gefunden wurde
   */
  public Optional<V> find(K k) {
    /*
     * Todo
     */
    int i=0;
    while (i < primeSize){
      if ( emptySlot(this.hash(k,i))){
        return Optional.empty();
      } else if(getKey(this.hash(k,i)).equals(k)){
        return Optional.of(getValue(this.hash(k,i)));
      }
      i++;
    }

    return Optional.empty();
  }

  /**
   * Diese Methode ermittelt die Anzahl der Kollisionen, also die Anzahl
   * der Elemente, die nicht an der 'optimalen' Position in die Hashtabelle eingefügt
   * werden konnten. Die optimale Position ist diejenige Position, die der
   * erste Aufruf der Hashfunktion (i = 0) bestimmt.
   * 
   * @return die Anzahl der Kollisionen
   */
  public int collisions() {
    /*
     * Todo
     */

    int collisions = 0;
    for (int i=0; i < primeSize; i++){
        K element = getKey(i);
        if (!element.equals(this.hash(element,0))){
          collisions++;
        }
    }

    return collisions;
  }
 
  /**
   * Diese Methode berechnet die maximale Anzahl von Aufrufen der Hashfunktion,
   * die nötig waren, um ein einziges Element in die Hashtabelle einzufügen.
   * 
   * @return die berechnete Maximalzahl von Aufrufen
   */
  public int maxRehashes() {
    /*
     * Todo
     */
    return maxRehashOp;
  }

  @Override
  public String toString(){

    return Arrays.toString(hashTable);
  }
}
