package Maze;

public class AlgorithmApproach {
    private boolean[][] maze; // gegebenes Labyrinth, "false" steht für eine leere Zelle, "true" für eine volle Zelle
    private boolean[][] visited; // besuchte Zellen
    private int goalX, goalY; // Zielpunkt im Labyrinth

    private int width, height; // Breite und Höhe des Labyrinths

    private boolean finished = false;

    public AlgorithmApproach(boolean[][] maze, int goalX, int goalY) {
        this.maze = maze;
        this.goalX = goalX;
        this.goalY = goalY;

        this.width = maze.length;
        this.height = maze[0].length;
        this.visited = new boolean[width][height];
    }

    public boolean[][] getVisited() { return visited; }

    public boolean walk() {
        // TODO: Implementiere einen Algorithmus nach Vorgaben des Übungsblattes, welcher den Weg durch das Labyrinth berechnet.
        // Gibt es einen Weg, soll "true" zurück gegeben werden, ansonsten "false".
        // Zusätzlich sollen die besuchten Zellen in "visited" eingetragen werden.

        walk(1,0);

        return finished;
    }


    private boolean walk(int x, int y) {


        if (visited[x][y])
            return false;

        if (x == goalX && y == goalY) return true;

        if (x != 0) // Checks if not on left edge
            if (walk(x-1, y)) { // Recalls method one to the left
                visited[x][y] = true;
                return true;
            }
        if (x != width - 1) // Checks if not on right edge
            if (walk(x+1, y)) {
                visited[x][y] = true;
                return true;
            }
        if (y != 0)  // Checks if not on top edge
            if (walk(x, y-1)) {
                visited[x][y] = true;
                return true;
            }
        if (y != height - 1) // Checks if not on bottom edge
            if (walk(x, y+1)) {
                visited[x][y] = true;
                return true;
            }


        return false;
    }

    private boolean canMoveLeft(int x, int y){
        return x > 0 && !maze[x-1][y];
    }

    private boolean canMoveRight(int x, int y){
        return x < maze.length && !maze[x+1][y];
    }

    private boolean canMoveUp(int x, int y){
        return y > 0 && !maze[x][y-1];
    }

    private boolean canMoveDown(int x, int y){
        return y < maze[x].length && !maze[x][y+1];
    }


    //visited() methods
    private boolean visitedUp(int x, int y){
        return y > 0 && visited[x][y-1];
    }

    private boolean visitedLeft(int x, int y){
        return x > 0 && visited[x-1][y];
    }

    private boolean visitedRight(int x, int y){
        return x < maze.length && visited[x+1][y];
    }

    private boolean visitedDown(int x, int y){
        return y < maze[x].length && visited[x][y+1];
    }




}