/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Is the main logic of the game, similar to Controller in MVC.
 * {@code @author:} Toan Nguyen
 * @version 07 20 2022
 */

public class DungeonAdventure {
    private Room [][] myMap;
    //private Hero myHero; //Comment out because Hero has not been implemented
    private final static int MAP_SIZE_WIDTH = 5;
    private final static int MAP_SIZE_HEIGHT = 5;

    //Serve as a reference for North, East, South, West coordinate
    private final int [] DX = new int[]{-1, 0, 1, 0};
    private final int [] DY = new int[]{ 0, 1, 0, -1};

    //Coordinate for the starting position
    private final int myEntranceY;
    private final int myEntranceX;

    private Room myCurrentRoom;
    private boolean[][] myRoomVisitedStatus;//True if the room has been visited, false otherwise

    private final static Random RANDOM_SEED = new Random();

    public DungeonAdventure(){
        myMap = new Room[MAP_SIZE_HEIGHT][MAP_SIZE_WIDTH];
        myRoomVisitedStatus = new boolean[MAP_SIZE_HEIGHT][MAP_SIZE_WIDTH];
        myEntranceX = 0;
        myEntranceY = RANDOM_SEED.nextInt(MAP_SIZE_HEIGHT);//Randomly selected starting cell

        initMap();
        //Testing outputting world map
        System.out.println(getWorldMap());
    }
    /**
     * @return a boolean, true if coordinate [i,j] is within the border of the map, false otherwise
     */
    static boolean checkValid(final int i, final int j){
        if (j >= MAP_SIZE_WIDTH || j < 0){
            return false;
        }
        return i < MAP_SIZE_HEIGHT && i >= 0;
    }

    /**
     * Initialize the map, build the maze (With custom access)
     *         Algorithm to implement a maze generator
     *         Choose the initial cell, mark it as visited and push it to the stack
     *         While the stack is not empty
     *         Pop a cell from the stack and make it a current cell
     *         If the current cell has any neighbours which have not been visited
     *         Push the current cell to the stack
     *
     *         Remove the wall between the current cell and the chosen cell
     */
    private void initMap(){
        int id = 0;
        for (int i = 0 ; i < MAP_SIZE_HEIGHT; i++){
            myMap[i] = new Room[MAP_SIZE_WIDTH];

            for (int j = 0 ; j < MAP_SIZE_WIDTH; ++j){
                myMap[i][j] = new Room(id);
                ++id;
            }
        }

        myCurrentRoom = myMap[myEntranceY][myEntranceX];
        myRoomVisitedStatus[myEntranceY][myEntranceX] = true;

        Stack<Room> stack = new Stack<>();
        stack.push(myCurrentRoom);

        //Use to keeping track of coordinate of the room in dfs
        int ux, uy, vx, vy, nextDirection;

        while (!stack.isEmpty()){

            //Pop a room from the stack and make it a current cell
            Room u = stack.pop();
            ux = u.getID() / MAP_SIZE_WIDTH;
            uy = u.getID() % MAP_SIZE_WIDTH;

            ArrayList<Integer> unvisitedPool = new ArrayList<>();
            //Checking visited neighbors
            for (int direction = 0 ; direction < 4; direction++){
                vx = ux + DX[direction];
                vy = uy + DY[direction];
                if (checkValid(vx, vy) && !myRoomVisitedStatus[vx][vy]){
                    unvisitedPool.add(direction);
                }
            }

            // //Pick a random neighbor
            if (!unvisitedPool.isEmpty()){
                //If the current cell has any neighbours which have not been visited
                //Push the current cell to the stack
                stack.push(myMap[ux][uy]);
                //Choose one of the unvisited neighbours
                nextDirection = unvisitedPool.get(RANDOM_SEED.nextInt(unvisitedPool.size()));
                vx = ux + DX[nextDirection];
                vy = uy + DY[nextDirection];

                //If the chosen neighbour has not been visited:
                if (checkValid(vx, vy) && !myRoomVisitedStatus[vx][vy]){
                    //Remove the wall between the current cell and the chosen neighbour.

                    myMap[ux][uy].openAccess(nextDirection);
                    myMap[vx][vy].openAccess((nextDirection + 2) % 4);

                    //Mark the chosen cell as visited and push it to the stack
                    myRoomVisitedStatus[vx][vy] = true;
                    stack.push(myMap[vx][vy]);
                }
            }
        }

        //Clearing visited status
        myRoomVisitedStatus = new boolean[MAP_SIZE_HEIGHT][MAP_SIZE_WIDTH];
    }

    /**
     * @return a String, as the whole map
     * X means no access, ' ' means there is a way
     * S is the entrance
     * P is current location of player
     */
    public String getWorldMap(){
        StringBuilder res = new StringBuilder();
        res.append("Width: " + MAP_SIZE_WIDTH + " Height: " + MAP_SIZE_HEIGHT + "\n");
        res.append("X means no access, ' ' means there is a way\n");
        res.append("S is the entrance\n");


        for (int i = 0; i < MAP_SIZE_HEIGHT; i++) {
            res.append("X");

            for (int j = 0; j < MAP_SIZE_WIDTH; j++) {
                if (i == 0 || !myMap[i][j].getAccess(0)) {
                    res.append("X");
                } else
                    res.append(" ");

                res.append("X");
            }
            res.append("\n");
            res.append("X");
            for (int j = 0; j < MAP_SIZE_WIDTH; j++) {

                if (myMap[i][j] == myCurrentRoom){
                    res.append('P');
                }
                else if (i == myEntranceY && j == myEntranceX) {
                    res.append("S");
                }
                else if (myRoomVisitedStatus[i][j]){
                    res.append('.');
                }
//                    else if (i == destinationX && j == destinationY) {
//                        res.append("G");
//                    }
//                    else if (maze[i][j].sol) {
//                        res.append("+");
//                    }
                else {
                    res.append(" ");
                }
                if (j == MAP_SIZE_WIDTH - 1 || !myMap[i][j].getAccess(1)) {
                    res.append("X");
                } else
                    res.append(" ");
            }

            res.append("\n");
        }

        //Creating a border
        res.append("XX".repeat(MAP_SIZE_WIDTH));
        res.append("X\n");
        return res.toString();
    }


}