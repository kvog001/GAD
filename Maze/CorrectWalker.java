package Maze;

public class CorrectWalker {
    private boolean[][] maze; // gegebenes Labyrinth, "false" steht für eine leere Zelle, "true" für eine volle Zelle
    private boolean[][] visited; // besuchte Zellen
    private int goalX, goalY; // Zielpunkt im Labyrinth

    private int width, height; // Breite und Höhe des Labyrinths

    private boolean exitFound = false;

    public CorrectWalker(boolean[][] maze, int goalX, int goalY) {
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


        //check if entrance is blocked
        if(maze[1][0]){
            return false;
        } else {
            visited[1][0] = true;
        }

        //check if exit is blocked
        if(maze[width-1][height-2]){
            return false;
        }

        walk(1,0,'S');

        return exitFound;
    }


    private boolean walk(int x, int y, char heading) {
        //System.out.println("("+x+","+y+")");
        if (x == goalX && y == goalY) {
            visited[x][y] = true;
            exitFound = true;
            return true;
        }

        if(maze[x][y]==false){
            visited[x][y] = true;
        }

        try {
            if(heading == 'S'){
                if( feelWallRightSide(x,y,heading) ){
                    if( !roadAheadBlocked(x,y,heading) )
                        walk(x,y+1,heading);// continue walking south if road ahead is free
                    else
                        walk(x,y,'E');// if road ahead blocked, walk east
                }else{//if there is no wall to the right when walking south, walk west
                    walk(x-1,y,'W');
                }

            } else if(heading == 'E'){ //todo if or else if
                if( feelWallRightSide(x,y,heading) ){
                    if( !roadAheadBlocked(x,y,heading) )
                        walk(x+1,y,heading); // if road ahead free continue walking east
                    else
                        walk(x,y,'N'); // if road ahead blocked, walk north
                }else{ //if there is no wall to the right, walk south
                    walk(x,y+1,'S');
                }

            } else if(heading == 'N'){
                if( feelWallRightSide(x,y,heading) ){
                    if( !roadAheadBlocked(x,y,heading) )
                        walk(x,y-1,heading); //continue walking north if road ahead free
                    else
                        walk(x,y,'W'); //if road ahead blocked, walk west
                }else{//if there is no wall to the right, walk east
                    walk(x+1,y,'E');
                }

            } else if(heading == 'W'){
                if( feelWallRightSide(x,y,heading) ){
                    if( !roadAheadBlocked(x,y,heading) )
                        walk(x-1,y,heading); //continue walking west
                    else
                        walk(x,y,'S'); //if road ahead blocked, walk south
                }else{ //if there is no wall to the right, walk north
                    walk(x,y-1,'N');
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e.getMessage());
        }

        return false;
    }

    /**
     *
     * @param x actual x coordinate of the walker
     * @param y actual y coordinate of the walker
     * @param heading parametric char value showing where the walker is headed
     * @return true if there is a wall to the right of the walker
     */
    private boolean feelWallRightSide(int x, int y, char heading) {
        if(heading == 'S'){ //heading south
            return this.maze[x-1][y];
        } else if(heading == 'E') { //heading east
            return this.maze[x][y+1];
        } else if(heading == 'N'){//heading north
            return this.maze[x+1][y];
        } else { //heading == 'W'  , heading west
            return this.maze[x][y-1];
        }
    }

    /**
     *
     * @param x actual x coordinate of the walker
     * @param y actual y coordinate of the walker
     * @param heading parametric char value showing where the walker is headed
     * @return true if road ahead is blocked (meaning if there is a wall in front of us)
     */
    private boolean roadAheadBlocked(int x, int y, char heading){
        if(heading == 'S'){ //heading south
            return this.maze[x][y+1];
        } else if(heading == 'E') { //heading east
            return this.maze[x+1][y];
        } else if(heading == 'N'){//heading north
            return this.maze[x][y-1];
        } else { //heading == 'W'  , heading west
            return this.maze[x-1][y];
        }
    }


}
