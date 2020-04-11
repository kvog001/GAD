package Maze;

public class RightHand {
    private boolean[][] maze; // gegebenes Labyrinth, "false" steht für eine leere Zelle, "true" für eine volle Zelle
    private boolean[][] visited; // besuchte Zellen
    private int goalX, goalY; // Zielpunkt im Labyrinth

    private int width, height; // Breite und Höhe des Labyrinths

    private boolean finished = false;

    public RightHand(boolean[][] maze, int goalX, int goalY) {
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
        visited[x][y] = true;


        try {
            if (x == goalX && y == goalY) {
                finished = true;

            } else if( canMoveUp(x,y) && !visitedUp(x,y)){
                walk(x,y-1); // walk up

            } else if( canMoveRight(x,y) && !visitedRight(x,y)){
                walk(x+1,y);

            } else if( canMoveLeft(x,y) ){
                walk(x-1,y);

            } else if( canMoveDown(x,y)){
                walk(x,y+1);
            }

        }catch (StackOverflowError soe){
            System.out.println("StackOverflow error my dawg");
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