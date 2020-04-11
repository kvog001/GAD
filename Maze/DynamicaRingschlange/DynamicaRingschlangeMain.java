package DynamicaRingschlange;
import java.awt.GraphicsEnvironment;
import java.util.Scanner;


public class DynamicaRingschlangeMain {
    private static boolean tumjudge = GraphicsEnvironment.isHeadless();
    
    interface Insert {
        void insert(int v);
    }
    interface Extract {
        int extract();
    }    
    interface ToString {
        String string();
    }

    private static void runTest(Scanner lineScanner, Insert insertFn, Extract extractFn, ToString stringFn) {
        while(lineScanner.hasNext()) {
            if(lineScanner.hasNextInt()) {
                insertFn.insert(lineScanner.nextInt());
            } else {
                String v = lineScanner.next();
                if (v.equals("_")) {
                    System.out.println(extractFn.extract());
                } else {
                    System.out.println(v);
                    break;
                }
            }
            System.out.println(stringFn.string());
        }
        lineScanner.close();
    }

    private static void runTestArray(int[] values, int[] extractions, Insert insertFn, Extract extractFn, ToString stringFn) {
        int ei = 0;
        for(int i = 0; i < values.length; i++) {
            insertFn.insert(values[i]);
            while(ei < extractions.length && extractions[ei] == i) {
                System.out.println(extractFn.extract());
                ei++;
            }
            System.out.println(stringFn.string());
        }
    }
        
    public static void main(String[] args) {        
        if(tumjudge) {
            Scanner scanner = new Scanner(System.in);
            Scanner lineScanner = new Scanner(scanner.nextLine());
            if(lineScanner.hasNextInt()) {
                System.out.println("a:");
                DynamicStack dstack = new DynamicStack(lineScanner.nextInt(), lineScanner.nextInt());
                runTest(lineScanner, (n) -> dstack.pushBack(n), () -> dstack.popBack(), () -> dstack.toString());
            }
            
            lineScanner = new Scanner(scanner.nextLine());
            if(lineScanner.hasNextInt()) {
                System.out.println("b:");
                RingQueue rqueue = new RingQueue(lineScanner.nextInt(), lineScanner.nextInt());
                runTest(lineScanner, (n) -> rqueue.enqueue(n), () -> rqueue.dequeue(), () -> rqueue.toString());
            }
            
            lineScanner = new Scanner(scanner.nextLine());
            if(lineScanner.hasNextInt()) {
                System.out.println("c:");
                StackyQueue squeue = new StackyQueue(lineScanner.nextInt(), lineScanner.nextInt());
                runTest(lineScanner, (n) -> squeue.enqueue(n), () -> squeue.dequeue(), () -> squeue.toString());
            }
            scanner.close();
            return;
        }

        System.out.println("------------- Test Output -------------");

        System.out.println("\nDynamic Stack:");
        DynamicStack dstack = new DynamicStack(2, 5);
        runTestArray(new int[]{5,2,7,2,3,7,5}, new int[]{1,4,5}, (n) -> dstack.pushBack(n), () -> dstack.popBack(), () -> dstack.toString());

        System.out.println("\nRing Queue:");
        RingQueue rqueue = new RingQueue(2, 5);
        runTestArray(new int[]{4,6,7,3,2,5,3}, new int[]{1,3,5}, (n) -> rqueue.enqueue(n), () -> rqueue.dequeue(), () -> rqueue.toString());

        System.out.println("\nStacky Queue:");
        StackyQueue squeue = new StackyQueue(2, 5);
        runTestArray(new int[]{1,6,3,5,6,7,4,4}, new int[]{3,3,6}, (n) -> squeue.enqueue(n), () -> squeue.dequeue(), () -> squeue.toString());

        System.out.println("\n\n\n------------- Expected Output -------------");
        System.out.println(
                "\nDynamic Stack:\n" +
                "[5, 0], length: 1\n" +
                "2\n" +
                "[5, 0], length: 1\n" +
                "[5, 7], length: 2\n" +
                "[5, 7, 2, 0, 0, 0], length: 3\n" +
                "3\n" +
                "[5, 7, 2, 0, 0, 0], length: 3\n" +
                "7\n" +
                "[5, 7, 2, 0, 0, 0], length: 3\n" +
                "[5, 7, 2, 5, 0, 0], length: 4\n" +
                "\nRing Queue:\n" +
                "[4, 0], size: 1, from 0 to 0\n" +
                "4\n" +
                "[4, 6], size: 1, from 1 to 1\n" +
                "[7, 6], size: 2, from 1 to 0\n" +
                "6\n" +
                "[6, 7, 3, 0, 0, 0], size: 2, from 1 to 2\n" +
                "[6, 7, 3, 2, 0, 0], size: 3, from 1 to 3\n" +
                "7\n" +
                "[6, 7, 3, 2, 5, 0], size: 3, from 2 to 4\n" +
                "[6, 7, 3, 2, 5, 3], size: 4, from 2 to 5\n" +
                "\nStacky Queue:\n" +
                "[1, 0], length: 1, [], length: 0\n" +
                "[1, 6], length: 2, [], length: 0\n" +
                "[1, 6, 3, 0, 0, 0], length: 3, [], length: 0\n" +
                "1\n" +
                "6\n" +
                "[], length: 0, [5, 3, 0, 0, 0, 0], length: 2\n" +
                "[6, 0], length: 1, [5, 3, 0, 0, 0, 0], length: 2\n" +
                "[6, 7], length: 2, [5, 3, 0, 0, 0, 0], length: 2\n" +
                "3\n" +
                "[6, 7, 4, 0, 0, 0], length: 3, [5, 0], length: 1\n" +
                "[6, 7, 4, 4, 0, 0], length: 4, [5, 0], length: 1:"
        );
    }
}
