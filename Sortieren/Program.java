package Sortieren;
import java.awt.GraphicsEnvironment;
import java.util.Random;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Program Klasse darf nicht erweitert oder verändert werden, außer explizit
 * angegeben!
 */
public class Program {    
    // Initialisierung des Testarrays, darf abgeändert werden
    // Quicksort >>>>>>> MergeSort
    public final static int SEED = 5; //5
    public final static int N = 10; //1000
    public final static int MAX_V = 100; //10000
    
    // Ausgabe der Zwischenergebnisse, darf abgeändert werden
    public final static boolean VERBOSE = true;

    /**
     * Folgender Code darf nicht geändert werden!
     */

    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------

    // Wird bei Ausführung in TUMJudge automatisch auf "true" gesetzt. Falls "true" keine zusätzliche Ausgaben ausgeben!
    public final static boolean TUMJUDGE = GraphicsEnvironment.isHeadless();

    // "main"-Funktion nicht ändern!
    public static void main(String[] args) {
        // Anzahl der zu sortierenden Elemente
        int n;
        int[] numbers;
        if (TUMJUDGE) {
            Scanner scanner = new Scanner(new Scanner(System.in).nextLine());
            LinkedList<Integer> tmp = new LinkedList<>();
            while (scanner.hasNextInt()) {
                tmp.add(scanner.nextInt());
            }
            n = tmp.size();
            numbers = new int[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = tmp.get(i);
            }
        } else {
            // Zahlen für den Vergleich vorbereiten
            numbers = new int[N];
            Random r = new Random(SEED);
            for (int i = 0; i < N; i++) {
                // Mit zufälligen Zahlen initialisieren
                numbers[i] = r.nextInt(MAX_V) + 1;
            }
            n = N;
            printArray("Testarray:\n\t", numbers);
        }

        LinkedList<SortingBase> implementations = new LinkedList<>();
        // Hier alle Implementierungen hinzufügen
        implementations.add(new Mergesort());
        implementations.add(new Quicksort());

        for (SortingBase sorter : implementations) {
            int[] numbersImpl = Arrays.copyOf(numbers, n);
            if (TUMJUDGE) {
                sorter.setVerbose(true);
                sorter.sort(numbersImpl);
            } else {
                System.out.println("\n==== " + sorter.getName() + " ====\n");
                System.out.println("Sortiertest:");
                
                sorter.setVerbose(VERBOSE);
                sorter.sort(numbersImpl);
                
                printArray("\nErgebnis:\n\t", numbersImpl);
                System.out.println();
                
                // Zeitmessung mit großem, zufällig generiertem Array
                
                int[] tmpA = new int[100000];
                Random r = new Random();
                for (int i = 0; i < tmpA.length; i++) {
                    // Mit zufälligen Zahlen initialisieren
                    tmpA[i] = r.nextInt();
                }
                sorter.setVerbose(false);
                
                long startTime = System.currentTimeMillis();
                sorter.sort(tmpA);
                long estimatedTime = System.currentTimeMillis() - startTime;

                if (estimatedTime > 0) {
                    System.out.println("Zeit:\n\t" + estimatedTime + "ms\n");
                } else {
                    System.out.println("Zeit:\n\t<1ms\n");
                }
            }
            checkSorting(numbersImpl);
            System.out.println();
        }
    }

    public static void printArray(String prefix, int[] numbers) {
        int displayCount = numbers.length;
        String ext = "";
        if (numbers.length > 15) {
            displayCount = 15;
            ext = "...";
        }
        System.out.print(prefix);
        for (int i = 0; i < displayCount - 1; i++) {
            System.out.print(numbers[i] + ", ");
        }
        System.out.println(numbers[displayCount - 1] + ext);
    }

    public static void checkSorting(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i - 1] > numbers[i]) {
                System.out.println("! FEHLER: " + numbers[i - 1] + " > " + numbers[i]);
            }
        }
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
}
