package dht;

import java.util.ArrayList;
import java.util.Map;

/**
 * Die Klasse {@link HashString} kann dazu verwendet werden,
 * Strings zu hashen.
 */
public class HashStrings {
    /*
     * Todo
     */
    private int size;

    /**
     * Dieser Konstruktor initialisiert ein {@link HashString}
     * Objekt für einen gegebenen Maximalwert (size - 1) der gehashten
     * Werte.
     *
     * @param size die Größe der Hashtabelle; die Eingabe sollte eine Primzahl sein
     */
    public HashStrings(int size) {
        /*
         * Todo
         */
        this.size = size;
    }

    /**
     * Diese Methode berechnet den Hashwert für einen String.
     *
     * @param key ist der Schlüssel, der gehasht werden soll
     * @return der Hashwert des Schlüssels
     */
    public int hash(String key) {
        /*
         * Todo
         */

        int sum =0;
        for (int i=0; i < key.length(); i++){
            int value = key.charAt(i);
            sum += value * value;
        }

        return sum % size;
    }

}
