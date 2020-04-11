package Maze;

public class Wanderer {
    private boolean[][] maze; // gegebenes Labyrinth, "false" steht für eine leere Zelle, "true" für eine volle Zelle
    private boolean[][] visited; // besuchte Zellen
    private int goalX, goalY; // Zielpunkt im Labyrinth

    private int width, height; // Breite und Höhe des Labyrinths

    private boolean finished = false;

    public Wanderer(boolean[][] maze, int goalX, int goalY) {
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
//
//        System.out.println(x < maze[x].length && !maze[x+1][y]);
//        System.out.println("maze: "+maze[x].length+",  maze2: "+maze.length);


        if (x == goalX && y == goalY) {
            finished = true;
        }

        else if(y > 0 && !maze[x][y-1] && !visited[x][y-1] && x < maze.length &&visited[x+1][y]){
            System.out.println("walk0()   x: "+x+",  y: "+(y-1));
            visited[x][y-1]=true;
            walk(x,y-1);
        }


        //if there is no wall to the left or is not visited, go left
        else if( x > 0 && !maze[x-1][y] && !visited[x-1][y] ){
            System.out.println("walk1()   x: "+(x-1)+",  y: "+y);
            visited[x-1][y]=true;
            walk(x-1,y);
        }

        //if left is blocked and there is no wall down or is not visited, go down
        else if( y < maze[x].length && !maze[x][y+1] ){ //&& !visited[x][y+1]
            System.out.println("walk2()   x: "+x+",  y: "+(y+1));
            visited[x][y+1] = true;
            walk(x,y+1);
        }

        //if left and down are blocked and there is no wall to the right, go right
        else if( x < maze.length && !maze[x+1][y]){ //TODO change all x < maze[x].length to x<maze.length and y<maze[x].length
            System.out.println("walk3()   x: "+(x+1)+",  y: "+y);
            visited[x+1][y] = true;
            walk(x+1,y);
        }

        //if only going up is possible
        else if( y > 0 && x < maze.length && maze[x+1][y] && !maze[x][y-1]){
            System.out.println("walk4()   x: "+x+",  y: "+(y-1));
            visited[x][y-1] = true;
            walkUp(x,y-1);
        }

        else if(x < maze.length && maze[x+1][y]){

        }


        return false;
    }



    private boolean walkUp(int x, int y) {
        if (x == goalX && y == goalY) {
            finished = true;
        }

        //if going down as normal
        else if(y < maze[x].length && !maze[x][y+1] && !visited[x][y+1]){
            System.out.println("Up1()   x: "+x+",  y: "+(y+1));
            visited[x][y+1] = true;
            walk(x,y+1);
        }

        //if there is no wall right and not visited, go right
        else if(x > 0 && x < maze.length && !maze[x+1][y] && (!visited[x+1][y] || visited[x-1][y])){ //TODO when to call normal walk() ???
            System.out.println("Up2()   x: "+(x+1)+",  y: "+y);
            visited[x+1][y] = true;
            walkUp(x+1,y);
        }

        //if there is no wall up, go up
        else if(y > 0 && !maze[x][y-1]){
            System.out.println("Up3()   x: "+x+",  y: "+(y-1));
            visited[x][y-1] = true;
            walkUp(x,y-1);
        }

        //if no way right or up, go left
        else if(x > 0 && x < maze.length && maze[x+1][y] && y > 0 && maze[x][y-1]){
            System.out.println("Up4()   x: "+(x-1)+",  y: "+y);
            visited[x-1][y] = true;
            walkUp(x-1,y);
        }

        return false;
    }
}