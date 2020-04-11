package Maze;

public class Walker {
    private boolean[][] maze; // gegebenes Labyrinth, "false" steht für eine leere Zelle, "true" für eine volle Zelle
    private boolean[][] visited; // besuchte Zellen
    private int goalX, goalY; // Zielpunkt im Labyrinth

    private int width, height; // Breite und Höhe des Labyrinths

    private int umdrehungszähler = 0;

    public Walker(boolean[][] maze, int goalX, int goalY) {
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


        /**
         *                  1 - down
         *                  2 - left
         *                  3 - up
         *                  4 - right
         */

        int x = 1, y = 0, turnDirection = 1;
        int fullcircle = 0;
        do {
            do {
                //gehe unten
                visited[x][y] = true;
                if (y < height && !maze[x][y+1] && x > 0 && maze[x-1][y]){ // kein wand unten....wand rechts
                    turnDirection = 1;
                    ++y;
                }
            }while ( !maze[x][y+1] && maze[x-1][y]);

            do {
                visited[x][y] = true;

                if (x == 1 && y == 0)
                    fullcircle++;

                if (fullcircle == 2)
                    return false;

                if(x == goalX && y == goalY)
                    return true;

                //when going down
                if(turnDirection == 1){

                    //if no obstacle on its right, it turns on that direction
                    if(x > 0 && !maze[x-1][y]){
                        --x;
                        turnDirection = 2;
                        umdrehungszähler--;

                    } else if( x > 0 && y > 0 && !maze[x][y+1] && maze[x-1][y] ){
                        //if there is an obstacle in its right but not in front, moves forward
                        ++y;

                    } else if( x > 0 && maze[x-1][y]  && y > 0 && maze[x][y+1]){
                        //if there are obstacles both front and right, turns left
                        turnDirection = 4;
                        umdrehungszähler++;
                    }


                } else if(turnDirection == 2)/* when going left*/{

                    if(y > 0 && !maze[x][y-1]){
                        turnDirection = 3;
                        --y;
                        umdrehungszähler--;

                    } else if(y > 0 && maze[x][y-1] && x > 0 && !maze[x-1][y]){
                        --x;

                    } else if(y > 0 && maze[x][y-1] && x > 0 && maze[x-1][y]){
                        turnDirection = 1;
                        umdrehungszähler++;
                    }

                } else if(turnDirection == 3)/* when going up */{

                    if( x < width && !maze[x+1][y] ){
                        turnDirection = 4;
                        ++x;
                        umdrehungszähler--;

                    } else if(x < width && maze[x+1][y] && y > 0 && !maze[x][y-1] ){
                        --y;

                    } else if(x > 0 && x < width && maze[x+1][y] && y > 0 && maze[x][y-1]){ //error because we reduce x
                        turnDirection = 2;
                        umdrehungszähler++;
                    }

                } else /* when going right */{

                    if(y < height && !maze[x][y+1]){
                        turnDirection = 1;
                        ++y;
                        umdrehungszähler--;

                    } else if(y < height && x < width && maze[x][y+1] && !maze[x+1][y]){
                        turnDirection = 4;
                        ++x;

                    } else if( y < height && x < width && maze[x][y+1] && maze[x+1][y]){
                        turnDirection = 3;
                        umdrehungszähler++;
                    }

                }

            }while (umdrehungszähler != 0);

        }while (x != goalX || y != goalY);

        return x == goalX && y == goalY;
    }
}