package Navigatoria;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Program Klasse darf nicht erweitert oder verändert werden, außer explizit
 * angegeben!
 */
public class Program {
    // Initialisierung des Testarrays, darf abgeändert werden
    public final static int SEED = 12;
    public final static int N = 10;

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
        Graph g = new Graph();
        int start, target;
        if (TUMJUDGE) {
            Scanner scanner = new Scanner(System.in);
            Scanner lineScanner = new Scanner(scanner.nextLine());

            int n = lineScanner.nextInt(); // Zeile 1: wie viele Knoten sind vorhanden?
            for (int i = 0; i < n; i++) {
                g.addNode(); // Bereite n Knoten mit aufsteigenden Indices vor
            }

            for (int i = 0; i < n; i++) {
                // nächste n Zeilen: Kantenverbindungen...
                Graph.Node node = g.getNode(i);

                lineScanner = new Scanner(scanner.nextLine());
                while (lineScanner.hasNextInt()) {
                    // ...jede folgende Zahl entspricht dem Knotenindex,
                    // zu dem eine Kante existiert, gefolgt vom Kantengewicht
                    Graph.Node neighbor = g.getNode(lineScanner.nextInt());
                    g.addEdge(node, neighbor, lineScanner.nextInt());
                }
            }

            lineScanner = new Scanner(scanner.nextLine());
            start = lineScanner.nextInt(); // ID des Ausgangsknotens
            target = lineScanner.nextInt(); // ID des Zielknotens

            lineScanner.close();
            scanner.close();
        } else {
            Random r = new Random(SEED);
            for (int i = 0; i < N; i++) {
                g.addNode(); // Bereite N Knoten mit aufsteigenden Indices vor
            }
            for (int i = 0; i < N; i++) {
                // nächste n Zeilen: Kantenverbindungen...
                Graph.Node node = g.getNode(i);
                int e = r.nextInt(5) + 1;
                ArrayList<Integer> edges = new ArrayList<>();
                for (int j = 0; j < N; j++) {
                    if (j != i) {
                        edges.add(j);
                    }
                }
                for (int j = 0; j < N - e; j++) {
                    edges.remove(r.nextInt(edges.size()));
                }
                for (int edge : edges) {
                    Graph.Node neighbor = g.getNode(edge);
                    g.addEdge(node, neighbor, r.nextInt(9) + 1);
                }
            }
            start = r.nextInt(N);
            target = r.nextInt(N);
        }

//        System.out.println("Graph: ");
//        for (Graph.Node v :g.getNodes()) {
//            for (Graph.Node w :v.getEdges().keySet()) {
//                System.out.println("("+v.getID()+", "+w.getID()+")"+"    kantengewicht = "+v.getEdges().get(w));
//            }
//            System.out.println();
//        }
//        System.out.println("Start: "+start);
//        System.out.println("Target: "+target);

        // Aufgabe: Berechne mithilfe des Dijkstra Algorithmus
        // den kürzesten Pfad von start nach target, und gebe ihn
        // auf die Standardausgabe aus, jeweils eine Knoten-ID pro
        // Zeile, beginnend mit start und endend mit target.
        // Abschließend geben Sie die Pfadlänge aus.

        Dijkstra d = new Dijkstra();
        try {
//            System.out.println("calculating route");
            d.findRoute(g, g.getNode(start), g.getNode(target));
//            System.out.println(d.getDistances().toString());
//            System.out.println(d.getPredecessors().toString());
//            System.out.println("route calculated");
            for (Graph.Node step : d.getShortestPath()) {
                System.out.println(step.getID());
            }
//            System.out.println("length");
            System.out.println(d.getShortestPathLength());
        } catch (RuntimeException e) {
            System.out.println("Kein Weg gefunden!");
        }
    }
}
