package NeatMauba;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * Program Klasse darf nicht erweitert oder verändert werden, außer explizit
 * angegeben!
 */
public class Program {
    // Initialisierung des Testarrays, darf abgeändert werden
    public final static int SEED = 0;
    public final static int N = 30;
    public final static int MAX_V = 10;

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
        ABTree tree = new ABTree(3, 5);

        if (TUMJUDGE) {
            Scanner scanner = new Scanner(System.in);
            Scanner lineScanner = new Scanner(scanner.nextLine());

            System.out.println("Insert Test:");
            while (lineScanner.hasNextInt()) {
                tree.insert(lineScanner.nextInt());
                System.out.println(tree.dot());
            }
            System.out.println();

            lineScanner = new Scanner(scanner.nextLine());
            System.out.println("Find Test:");
            while (lineScanner.hasNextInt()) {
                System.out.print(tree.find(lineScanner.nextInt()) + " ");
            }
            System.out.println();

            lineScanner = new Scanner(scanner.nextLine());
            System.out.println("Remove Test:");
            while (lineScanner.hasNextInt()) {
                tree.remove(lineScanner.nextInt());
                System.out.println(tree.dot());
            }
            System.out.println();

            lineScanner.close();
            scanner.close();
        } else {
            Random r = new Random(SEED);
            ArrayList<Integer> values = new ArrayList<>();
            System.out.println("Insert Test:");
            for (int i = 0; i < N; i++) {
                values.add(r.nextInt(MAX_V));
                tree.insert(values.get(i));

                if (!tree.validAB()) {
                    System.out.println(String.format("Baum ungueltig bei insert(%d)", values.get(i)));
                    System.out.println(tree.dot());
                    return;
                } else if (VERBOSE) {
                    System.out.println(tree.dot());
                }
            }
            System.out.println("\tOK");

            // Permutiere die Elemente in values, um eine zufällige Suchsequenz zu erzeugen
            Collections.shuffle(values, r);

            System.out.println("Find Test:");
            for (int i = 0; i < N; i++) {
                if (!tree.find(values.get(i))) {
                    System.out.println(String.format("Baum ungueltig bei find(%d), \"true\" erwartet!", values.get(i)));
                    System.out.println(tree.dot());
                    return;
                }
            }
            for (int i = 0; i < N; i++) {
                int v = r.nextInt(MAX_V) + MAX_V;
                if (tree.find(v)) {
                    System.out.println(String.format("Baum ungueltig bei find(%d), \"false\" erwartet!", v));
                    System.out.println(tree.dot());
                    return;
                }
            }
            System.out.println("\tOK");

            // Permutiere die Elemente in values, um eine zufällige Entfernungssequenz zu erzeugen
            Collections.shuffle(values, r);

            System.out.println("Remove Test:");
            for (int i = 0; i < N; i++) {
                tree.remove(values.get(i));

                if (!tree.validAB()) {
                    System.out.println(String.format("Baum ungueltig bei remove(%d)", values.get(i)));
                    System.out.println(tree.dot());
                    return;
                } else if (VERBOSE) {
                    System.out.println(tree.dot());
                }
            }
            System.out.println("\tOK");
        }
    }
}
