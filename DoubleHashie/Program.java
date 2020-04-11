package DoubleHashie;
import java.awt.GraphicsEnvironment;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Program Klasse darf nicht erweitert oder verändert werden, außer explizit angegeben!
 */
public class Program {

    // Wird bei Ausführung in TUMJudge automatisch auf "true" gesetzt. Falls "true" keine zusätzlichen Ausgaben ausgeben!
    public final static boolean TUMJUDGE = GraphicsEnvironment.isHeadless();

    private static String getRandomString(int size) {
        Random r = new Random();
        String characters = "abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-+/&";
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append(characters.charAt(r.nextInt(characters.length())));
        }
        return sb.toString();
    }

    // Bitte nicht ändern!
    private static double findMedian(long[] arr) {
        Arrays.sort(arr);
        if (arr.length % 2 != 0)
            return (double) arr[arr.length / 2];
        return (double) (arr[(arr.length - 1) / 2] + arr[arr.length / 2]) / 2.0;
    }

    // Bitte nicht ändern!
    private static <T> float testHash(DoubleHashable<T> hash, T[] data, int length) {
        long[] hash_v = new long[data.length];
        long[] hash_v_collision = new long[length];

        for (int i = 0; i < hash_v.length; i++) {
            hash_v[i] = hash.hash(data[i]);
        }

        for (long value : hash_v) {
            if (value < 0) {
                throw new RuntimeException("Zu kleiner Hashwert generiert!");
            }
            if (value >= length) {
                throw new RuntimeException("Zu großer Hashwert generiert!");
            }
            hash_v_collision[(int) value]++;
        }

        double median_col = findMedian(hash_v_collision);
        return Math.abs((float) (median_col * length) / data.length - 1);
    }

    // Generisches Interface zur Nutzung als Lambda in der testHashTable Funktion
    interface KeyGen<K> {
        K get();
    }

    // Generisches Interface zur Nutzung als Lambda in der checkCollisionRate Funktion
    interface DataGen<K> {
        K[] get();
    }

    // Die Funktion checkCollisionRate wird zur Überprüfung der Lösung verwendet.
    // Hierfür werden mehrere zufällige Strings generiert, von denen anschließend Hashes erzeugt werden.
    // Die finale Kollisionsrate wird daraufhin statistisch ausgewertet, indem ein Mittel aus mehreren Durchläufen berechnet wird.
    // Bitte nicht ändern!
    private static <T> void checkCollisionRate(DoubleHashable<T> hash, int length, DataGen<T> nextData) {
        float[] coll_rates = new float[10];
        float coll_rate_avg = 0.0f;
        for (int j = 0; j < coll_rates.length; ++j) {
            T[] data = nextData.get();
            coll_rates[j] = testHash(hash, data, length);
            coll_rate_avg += coll_rates[j];
        }
        coll_rate_avg /= coll_rates.length;
        if (coll_rate_avg > 0.01) {
            throw new RuntimeException("Falsche Kollisionsrate! " + coll_rate_avg);
        }
        if (!TUMJUDGE) System.out.println("\tDurchschnittliche Abweichung der Kollisionsrate: " + coll_rate_avg);
    }

    /**
     * @param <K> der Typ des Schlüssels, der getestet werden soll
     */
    private static <K> void testHashTable(int length, HashableFactory<K> factory, Random r, KeyGen<K> nextKeyFn) {
        float collision_rate = 0.0f;
        int collision_count = 0;
        int max_rehashes = 0;
        int test_count = 50;

        /// Im Folgenden werden 50 Testdurchläufe ausgeführt
        for (int test_id = 0; test_id < test_count; ++test_id) {
            if (!TUMJUDGE) System.out.println("Testdurchlauf " + test_id);

            DoubleHashTable<K, Integer> ht = new DoubleHashTable<K, Integer>(length, factory);
            Hashtable<K, Integer> htJava = new Hashtable<>();

            if (!TUMJUDGE) System.out.println("\tTeste insert-Funktion");
            for (int i = 0; i < length + 1; i++) {
                K key = nextKeyFn.get();
                int value = r.nextInt();
                if (htJava.containsKey(key)) {
                    i--;
                }

                if (!ht.insert(key, value)) {
                    if (i < length) {
                        throw new RuntimeException("DoubleHashTable: Einfügen fehlgeschlagen :/");
                    }
                } else {
                    if (i >= length) {
                        throw new RuntimeException("DoubleHashTable: Hashtable sollte voll sein!");
                    }
                    htJava.put(key, value);
                }
            }

            if (!TUMJUDGE) System.out.println("\tTeste find-Funktion");
            Iterator<Entry<K, Integer>> it = htJava.entrySet().iterator();
            while (it.hasNext()) {
                Entry<K, Integer> entry = it.next();
                Integer ht_val = ht.find(entry.getKey()).get();
                if (!entry.getValue().equals(ht_val))
                    throw new RuntimeException("Falscher Eintrag zurückgegeben! Erwartet: " + entry.getValue() + " Bekommen: " + ht_val);
            }
            max_rehashes = Math.max(ht.maxRehashes(), max_rehashes);
        }

        if (!TUMJUDGE) System.out.println("Teste Rehashanzahl");
        if (max_rehashes >= length) {
            throw new RuntimeException("Falsche Rehash Anzahl! Die maximalen Rehashes " + max_rehashes + " dürfen die Tabellengröße nicht überschreiten!");
        }
    }

    public static void main(String[] args) {
        Random r;
        int length;
        if (TUMJUDGE) {
            Scanner scanner = new Scanner(System.in);
            Scanner lineScanner = new Scanner(scanner.nextLine());
            r = new Random(lineScanner.nextInt());
            length = lineScanner.nextInt();
            lineScanner.close();
            scanner.close();
        } else {
            r = new Random(123455);
            length = 17;
        }

        if (!TUMJUDGE) System.out.println("Teste DoubleHashInt");
        DoubleHashInt hi = new DoubleHashInt(length);
        checkCollisionRate(hi, length, () -> {
            Integer[] data = new Integer[length * 1000];
            for (int i = 0; i < data.length; i++) {
                data[i] = r.nextInt();
            }
            return data;
        });

        if (!TUMJUDGE) System.out.println("Teste DoubleHashString");
        DoubleHashString hs = new DoubleHashString(length);
        checkCollisionRate(hs, length, () -> {
            String dataS[] = new String[length * 1000];
            for (int i = 0; i < dataS.length; i++) {
                dataS[i] = getRandomString(r.nextInt(10) + 5);
            }
            return dataS;
        });

        testHashTable(length, new IntHashableFactory(), r, () -> {
            return r.nextInt(length * 10);
        });
        testHashTable(length, new StringHashableFactory(), r, () -> {
            byte[] bytes = new byte[r.nextInt(10) + 5];
            r.nextBytes(bytes);
            return new String(bytes);
        });

        System.out.println("Tests abgeschlossen! :)");
    }
}
