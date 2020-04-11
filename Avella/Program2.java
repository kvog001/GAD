package Avella;

import java.awt.GraphicsEnvironment;
import java.util.Random;
import java.util.Scanner;

/**
 * Program Klasse darf nicht erweitert oder verändert werden, außer explizit
 * angegeben!
 */
public class Program2 {
    // Initialisierung des Testarrays, darf abgeändert werden
    public final static int SEED = 0;
    public final static int N = 10;
    public final static int MAX_V = 100;

    // Ausgabe der Zwischenergebnisse, darf abgeändert werden
    public final static boolean VERBOSE = false;

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
        AVLTree tree = new AVLTree();

        if (TUMJUDGE) {
            Scanner scanner = new Scanner(System.in);
            Scanner lineScanner = new Scanner(scanner.nextLine());

            System.out.println("Insert Test:");
            while (lineScanner.hasNextInt()) {
                tree.insert(lineScanner.nextInt());
                System.out.println(tree.toString());
            }
            System.out.println();

            lineScanner = new Scanner(scanner.nextLine());
            System.out.println("Find Test:");
            while (lineScanner.hasNextInt()) {
                System.out.print(tree.find(lineScanner.nextInt()) + " ");
            }
            System.out.println();

            lineScanner.close();
            scanner.close();
        } else {
            Random r = new Random(SEED);
            int[] values = new int[N];
            System.out.println("Teste Einfügen:");
            for (int i = 0; i < N; i++) {
                values[i] = r.nextInt(MAX_V);
                tree.insert(values[i]);

                if (!tree.validAVL()) {
                    System.out.println(String.format("Baum ungueltig bei insert(%d)", values[i]));
                    System.out.println(tree.toString());
                    return;
                } else if (VERBOSE) {
                    System.out.println(tree.toString());
                }
            }
            System.out.println("\tOK");

            System.out.println("Teste Suche:");
            for (int i = 0; i < N; i++) {
                if (!tree.find(values[i])) {
                    System.out.println(String.format("Baum ungueltig bei find(%d), \"true\" erwartet!", values[i]));
                    System.out.println(tree.toString());
                    return;
                }
            }
            for (int i = 0; i < N; i++) {
                int v = r.nextInt(MAX_V) + MAX_V;
                if (tree.find(v)) {
                    System.out.println(String.format("Baum ungueltig bei find(%d), \"false\" erwartet!", v));
                    System.out.println(tree.toString());
                    return;
                }
            }
            System.out.println("\tOK");
        }

        System.out.println("\nFinaler Baum:\n");
        System.out.println(tree.toString());
    }
}
