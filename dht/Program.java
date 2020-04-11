package dht;
import java.awt.GraphicsEnvironment;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;


/**
 * Data Klasse darf nicht erweitert oder verändert werden!
 */
class Data {
    public String key;
    public int value;

    Data(String k, int v) {
        key = k;
        value = v;
    }
}

/**
 * DataList Klasse darf nicht erweitert oder verändert werden!
 */
class DataList extends ArrayList<Data> {
}

/**
 * Program Klasse darf nicht erweitert oder verändert werden, außer explizit angegeben!
 */
public class Program {

    // Wird bei Ausführung in TUMJudge automatisch auf "true" gesetzt. Falls "true" keine zusätzlichen Ausgaben ausgeben!
    public final static boolean TUMJUDGE = GraphicsEnvironment.isHeadless();

    /**
     * Folgender Code darf nicht geändert werden!
     */

    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    private static String getRandomString(int size) {
        Random r = new Random();
        String characters = "abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-+/&";
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append(characters.charAt(r.nextInt(characters.length())));
        }
        return sb.toString();
    }

    private static double findMedian(int[] arr) {
        Arrays.sort(arr);
        if (arr.length % 2 != 0)
            return (double) arr[arr.length / 2];
        return (double) (arr[(arr.length - 1) / 2] + arr[arr.length / 2]) / 2.0;
    }

    // Bitte nicht ändern!
    private static float testHash(String[] data, int length) {
        HashString hash = new HashString(length);
        int[] hash_v = new int[data.length];
        int[] hash_v_collision = new int[length];

        for (int i = 0; i < hash_v.length; i++) {
            hash_v[i] = hash.hash(data[i]);
        }

        int minK = 100000;
        int maxK = 0;
        for (int value : hash_v) {
            if (value < minK) {
                minK = value;
            }
            if (value > maxK) {
                maxK = value;
            }
            hash_v_collision[value]++;
        }

        if (minK < 0) {
            System.out.println("Zu kleiner Hashwert generiert!");
        }
        if (maxK >= length) {
            System.out.println("Zu großer Hashwert generiert!");
        }

        double median_col = findMedian(hash_v_collision);
        return Math.abs((float) (median_col * length) / data.length - 1);
    }

    // Die Funktion checkCollisionRate wird zur Überprüfung der Lösung verwendet.
    // Hierfür werden mehrere zufällige Strings generiert, von denen anschließend Hashes erzeugt werden.
    // Die finale Kollisionsrate wird daraufhin statistisch ausgewertet, indem ein Mittel aus mehreren Durchläufen berechnet wird.
    // Bitte nicht ändern!
    private static void checkCollisionRate(int store_count, Random r) {
        float[] coll_rates = new float[10];
        float coll_rate_avg = 0.0f;
        for (int j = 0; j < coll_rates.length; ++j) {
            String data[] = new String[10000];
            for (int i = 0; i < data.length; i++) {
                data[i] = getRandomString(r.nextInt(10) + 5);
            }

            coll_rates[j] = testHash(data, store_count);
            coll_rate_avg += coll_rates[j];
        }
        coll_rate_avg /= coll_rates.length;
        if (coll_rate_avg > 0.01) {
            System.out.println("Falsche Kollisionsrate! " + coll_rate_avg);
        }
        System.out.println("end");
    }


    // "main"-Funktion nicht ändern!
    public static void main(String[] args) {
        if (TUMJUDGE) {
            Scanner scanner = new Scanner(System.in);
            Scanner lineScanner = new Scanner(scanner.nextLine());

            Random r = new Random(lineScanner.nextInt());
            int store_count = lineScanner.nextInt();
            int size_count = lineScanner.nextInt();

            checkCollisionRate(store_count, r);

            DHT dht = new DHT(store_count, size_count);

            DataList[][] dataStruct = new DataList[store_count][size_count];
            for (int i = 0; i < store_count; i++) {
                for (int j = 0; j < size_count; j++) {
                    dataStruct[i][j] = new DataList();
                }
            }

            lineScanner = new Scanner(scanner.nextLine());
            while (lineScanner.hasNext()) {
                String k = lineScanner.next();
                HashPair p = dht.hashKey(k);
                dataStruct[p.master_index][p.store_index].add(new Data(k, lineScanner.nextInt()));
            }

            lineScanner = new Scanner(scanner.nextLine());
            while (lineScanner.hasNext()) {
                String k = lineScanner.next();
                HashPair p = dht.hashKey(k);
                for (Data d : dataStruct[p.master_index][p.store_index]) {
                    if (d.key.compareTo(k) == 0) {
                        System.out.print(d.value + " ");
                    }
                }
            }

            scanner.close();
            return;
        }

        /// Lokaler Testcode
        Random r = new Random(349587);
        int store_count = 17;
        int size_count = 13;

        checkCollisionRate(store_count, r);

        DHT dht = new DHT(store_count, size_count);

        DataList[][] dataStruct = new DataList[store_count][size_count];
        for (int i = 0; i < store_count; i++) {
            for (int j = 0; j < size_count; j++) {
                dataStruct[i][j] = new DataList();
            }
        }

        Data random_test_data[] = {
                new Data("hdr2a", 2),
                new Data("gklje", 5934),
                new Data("kjtrz", 5123534),
                new Data("tifikbnsD", 12662623),
                new Data("Phdflasd", 63452),
                new Data("Gasdfjr", 483498982)};

        for (Data random_test_entry : random_test_data) {
            String k = random_test_entry.key;
            HashPair p = dht.hashKey(k);
            dataStruct[p.master_index][p.store_index].add(random_test_entry);
        }

        for (Data random_test_entry : random_test_data) {
            String k = random_test_entry.key;
            HashPair p = dht.hashKey(k);
            int val = -1;
            for (Data d : dataStruct[p.master_index][p.store_index]) {
                if (d.key.compareTo(k) == 0 && d.value == random_test_entry.value) {
                    System.out.print(d.value + " ");
                    val = d.value;
                }
            }
            if (val == -1) {
                System.out.println("Keinen Wert für Schlüssel \"" + k + "\" gefunden!");
            } else if (val != random_test_entry.value) {
                System.out.println("Falschen Wert gefunden: \"" + val + "\" erwartet: \"" + random_test_entry.value + "\"!");
            }
        }
    }
}
