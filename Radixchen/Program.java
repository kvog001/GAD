package Radixchen;
import java.util.ArrayList;
import java.awt.GraphicsEnvironment;
import java.util.Random;
import java.util.Scanner;

/**
 * Program Klasse darf nicht erweitert oder verändert werden, außer explizit angegeben!
 */
public class Program {
    // Initialisierung des Testarrays, darf abgeändert werden
    public final static int SEED = 10;
    public final static int N = 15;
    public final static int MAX_V = 10000;

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
        Integer[] elements;
        if (TUMJUDGE) {
            Scanner scanner = new Scanner(new Scanner(System.in).nextLine());
            ArrayList<Integer> numbers = new ArrayList<>();
            while (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            }
            elements = new Integer[numbers.size()];
            elements = numbers.toArray(elements);
            
        } else {
            elements = new Integer[N];
            Random r = new Random(SEED);

            for (int i = 0; i < elements.length; i++) {
                elements[i] = r.nextInt(MAX_V);
            }

            System.out.println("Eingabe:");
            printArray(elements);
            System.out.println();
        }

        try {
            System.out.println("Zwischenergebnisse:");
            RadixSort.sort(elements, VERBOSE || TUMJUDGE);
        } catch (IllegalArgumentException ex) {
            System.out.println("IllegalArgumentException in sorter: " + ex.getMessage());
            return;
        }
        System.out.println("\nEndergebnis:");
        printArray(elements);
        checkSorting(elements);

    }

    public static void printArray(Integer[] numbers) {
        int displayCount = numbers.length;
        String ext = "";
        if (numbers.length > 15) {
            displayCount = 15;
            ext = "...";
        }
        for (int i = 0; i < displayCount - 1; i++) {
            System.out.print(numbers[i] + ", ");
        }
        System.out.println(numbers[displayCount - 1] + ext);
    }

    public static void checkSorting(Integer[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i - 1] > numbers[i]) {
                System.out.println("! FEHLER: " + numbers[i - 1] + " > " + numbers[i]);
            }
        }
    }
}
