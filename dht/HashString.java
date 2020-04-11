package dht;

import java.util.ArrayList;
import java.util.Map;

/**
 * Die Klasse {@link HashString} kann dazu verwendet werden,
 * Strings zu hashen.
 */
public class HashString {
    /*
     * Todo
     */
    private int tableSize;

    /**
     * Dieser Konstruktor initialisiert ein {@link HashString}
     * Objekt für einen gegebenen Maximalwert (size - 1) der gehashten
     * Werte.
     *
     * @param size die Größe der Hashtabelle; die Eingabe sollte eine Primzahl sein
     */
    public HashString(int size) {
        /*
         * Todo
         */
        this.tableSize = size;
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


        int keyLength = key.length();
        int w = log2(tableSize);
        ArrayList<Integer> randomKeyValues = new ArrayList<>();
        ArrayList<Integer> randomHashValues = new ArrayList<>();

        int keyLimit = (int)Math.pow(2,w)-1;
        int hashLimit = tableSize-1;

        for (int i=0; i < keyLength; i++){
            int element = key.charAt(i);
            randomKeyValues.add(i,element);
        }

        for (int i=0; i < keyLength; i++){
            int element = key.charAt(i);
            randomHashValues.add(i,element);
        }

        int sum =0;
        for (int i=0; i < keyLength; i++){
            sum += randomKeyValues.get(i) * randomHashValues.get(i);
        }

        return sum % tableSize;
    }

    private int log2(int m){
        double logValue = (Math.log(m) / Math.log(2));
        int roundedLog = (int) Math.floor(logValue);
        return roundedLog;
    }

}
