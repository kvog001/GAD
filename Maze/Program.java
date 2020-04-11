package Maze;

import java.awt.GraphicsEnvironment;
import java.util.Scanner;

/**
 * Maze.Program Klasse darf nicht erweitert oder verändert werden, außer explizit angegeben!
 */
public class Program {    
    // Initialisierung der Domäne, darf abgeändert werden
    private static int width =  20;
    private static int height = 15;
    private static int seed = 1; //15,9,2,1,3,4 tested

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
        if(TUMJUDGE) {
            Scanner scanner = new Scanner(System.in);
            width = scanner.nextInt();
            height = scanner.nextInt();
            seed = scanner.nextInt();
            scanner.close();
        }
        
        boolean[][] maze = Maze.generateMaze(width, height, seed);
        
        Walker walker = new Walker(maze, width-1, height-2);

        boolean res = walker.walk();
        
        boolean visited[][] = walker.getVisited();

        if(TUMJUDGE) {
            System.out.print(res + " ");
            if(res) {
                for(int i = 0; i < width; i++) {
                    for(int j = 0; j < height; j++) {
                        if(visited[i][j]){
                            System.out.print("(" + i + "," + j + ") ");  
                        }
                    }
                }
            }
        } else {
            Maze.draw(width-1, height-2, maze, visited);
            if (!res) //have we found any solutions?
                System.out.println("Ahhhh... no way out!");
            else {
                System.out.println("Finished!");
            }
        }
    }


    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------

}
