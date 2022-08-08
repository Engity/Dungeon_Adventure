/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Is the main logic of the game, similar to Controller in MVC.
 * {@code @author:} Toan Nguyen
 * @version 08 07 2022
 */

public class DungeonAdventure implements Serializable {
    private Room [][] myMap;
    //private Hero myHero; //Comment out because Hero has not been implemented
    private final static int MAP_SIZE_WIDTH = 4;
    private final static int MAP_SIZE_HEIGHT = 4;

    //Serve as a reference for North, East, South, West coordinate
    private final static int [] DX = new int[]{-1, 0, 1, 0};
    private final static int [] DY = new int[]{ 0, 1, 0, -1};

    //Coordinate for the starting position
    private int myEntranceY;
    private int myEntranceX;

    private Room myCurrentRoom;
    private boolean[][] myRoomVisitedStatus;//True if the room has been visited, false otherwise

    private final static Random RANDOM_SEED = new Random();

    //Used in Serialization
    private static final long serialVersionUID = 11L;

    //Used to track the state of the game
    private boolean myGameOverStatus;//Is false if the player has not lost yet
    private boolean myVictoryStatus;//Is false if the player has not won yet

    private static DungeonAdventure myDungeonAdventureInstance = new DungeonAdventure();

    /**
     * Default constructor
     * Attach controller to the views
     * Init the components
     */
    private DungeonAdventure(){
        init();
        TextBasedGUI_MainDisplay.attachController(this);
        CombatController.attachController(this);
    }

    /**
     * Init fields and variables
     */
    void init(){
        myMap = new Room[MAP_SIZE_HEIGHT][MAP_SIZE_WIDTH];
        myRoomVisitedStatus = new boolean[MAP_SIZE_HEIGHT][MAP_SIZE_WIDTH];
        myEntranceX = RANDOM_SEED.nextInt(MAP_SIZE_WIDTH);
        myEntranceY = RANDOM_SEED.nextInt(MAP_SIZE_HEIGHT);//Randomly selected starting cell

        int id = 0;
        for (int i = 0 ; i < MAP_SIZE_HEIGHT; i++){
            myMap[i] = new Room[MAP_SIZE_WIDTH];

            for (int j = 0 ; j < MAP_SIZE_WIDTH; ++j){
                myMap[i][j] = new Room(id);
                ++id;
            }
        }

        myCurrentRoom = myMap[myEntranceY][myEntranceX];
    }

    /**
     * A part of the Singleton design. Return the instance of DungeonAdventure
     * @return the instance of DungeonAdventure
     */
    public static DungeonAdventure getInstance(){
        return myDungeonAdventureInstance;
    }

    /**
     * Creating a new game
     * @param theHeroName the player's name
     * @param theHeroClass the player's class
     */

    void createANewGame(final String theHeroName,final int theHeroClass){
        init();
        randomizeMap();
        //Testing outputting world map
        System.out.println(getWorldMapFullVisibility());

        //Testing visibility world map
        System.out.println(parseWorldMapWithVisibility());

        gameLoop();
    }

    /**
     * @return a boolean, true if coordinate [i,j] is within the border of the map, false otherwise
     */
    static boolean checkValid(final int theXCoordinate, final int theYCoordinate){
        if (theYCoordinate >= MAP_SIZE_WIDTH || theYCoordinate < 0){
            return false;
        }
        return theXCoordinate < MAP_SIZE_HEIGHT && theXCoordinate >= 0;
    }

    /**
     * Initialize the map, build the maze (With custom access)
     *         Algorithm to implement a maze generator
     *         Choose the initial cell, mark it as visited and push it to the stack
     *         While the stack is not empty
     *         Pop a cell from the stack and make it a current cell
     *         If the current cell has any neighbours which have not been visited
     *         Push the current cell to the stack
     * <p>
     *         Remove the wall between the current cell and the chosen cell
     */
    void randomizeMap(){
        myCurrentRoom = myMap[myEntranceY][myEntranceX];
        myRoomVisitedStatus[myEntranceY][myEntranceX] = true;

        Stack<Room> stack = new Stack<>();
        stack.push(myCurrentRoom);

        //Use to keeping track of coordinate of the room in dfs
        int vx, vy, nextDirection;

        while (!stack.isEmpty()){

            //Pop a room from the stack and make it a current cell
            Room u = stack.pop();
            int[] uCoordinate = Room.convertIDtoCoordinate(u.getID());

            ArrayList<Integer> unvisitedPool = new ArrayList<>();
            //Checking visited neighbors
            for (int direction = 0 ; direction < 4; direction++){
                vx = uCoordinate[0]  + DX[direction];
                vy = uCoordinate[1] + DY[direction];
                if (checkValid(vx, vy) && !myRoomVisitedStatus[vx][vy]){
                    unvisitedPool.add(direction);
                }
            }

            // //Pick a random neighbor
            if (!unvisitedPool.isEmpty()){
                //If the current cell has any neighbours which have not been visited
                //Push the current cell to the stack
                stack.push(myMap[uCoordinate[0]][uCoordinate[1]]);
                //Choose one of the unvisited neighbours
                nextDirection = unvisitedPool.get(RANDOM_SEED.nextInt(unvisitedPool.size()));
                vx = uCoordinate[0]  + DX[nextDirection];
                vy = uCoordinate[1] + DY[nextDirection];

                //If the chosen neighbour has not been visited:
                if (checkValid(vx, vy) && !myRoomVisitedStatus[vx][vy]){
                    //Remove the wall between the current cell and the chosen neighbour.

                    myMap[uCoordinate[0]][uCoordinate[1]].openAccess(nextDirection);
                    myMap[vx][vy].openAccess((nextDirection + 2) % 4);

                    //Mark the chosen cell as visited and push it to the stack
                    myRoomVisitedStatus[vx][vy] = true;
                    stack.push(myMap[vx][vy]);
                }
            }
        }

        //Clearing visited status
        myRoomVisitedStatus = new boolean[MAP_SIZE_HEIGHT][MAP_SIZE_WIDTH];
        myRoomVisitedStatus[myEntranceY][myEntranceX] = true;

//        myRoomVisitedStatus[3][3] = true;
//        myRoomVisitedStatus[3][4] = true;
//        myRoomVisitedStatus[2][3] = true;
//        myRoomVisitedStatus[1][2] = true;
//        myRoomVisitedStatus[2][1] = true;


//        //Testing
//        if (myEntranceY + 1 < MAP_SIZE_HEIGHT)
//            myRoomVisitedStatus[myEntranceY + 1][myEntranceX] = true;
//        if (myEntranceY + 1 < MAP_SIZE_HEIGHT)
//            myRoomVisitedStatus[myEntranceY][myEntranceX+1] = true;
    }

    /**
     * @return a String, as the whole map
     * X means no access, ' ' means there is a way
     * S is the entrance
     * P is current location of player
     * Full visibility is only used for testing purposes
     */
    String getWorldMapFullVisibility(){
        StringBuilder res = new StringBuilder();
        res.append("Width: " + MAP_SIZE_WIDTH + " Height: " + MAP_SIZE_HEIGHT + "\n");
        res.append("X means no access, ' ' means there is a way\n");
        res.append("E is the entrance\n");


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
                    res.append("E");
                }
                else if (myRoomVisitedStatus[i][j]){
                    res.append('.');
                }
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

    /**
     * @return a String, as the whole map but will conceal room where player has not visited
     * + means no access, ' ' means there is a way
     * Room has not been visited will not show up
     * P is current location of player
     */
    String parseWorldMapWithVisibility(){
        //a 3x3 char matrix for each room, representing access of the room and whether it has been visited or not
        //Exp: If player is in Room (0,0), and it has access to the East and South
        //          'X', 'X', 'X',
        //          'X', 'P', ' ',
        //          'X', ' ', 'X',
        //Exp: If player has not visited this room, room (0,0) is:
        //          'X', 'X', 'X',
        //          'X', '?', '?',
        //          'X', '?', 'X',

        char[][][][] roomPresentation = new char[MAP_SIZE_HEIGHT] [MAP_SIZE_WIDTH][3][3];
        for (int i = 0 ; i < MAP_SIZE_HEIGHT; i++){
            for (int j = 0 ; j < MAP_SIZE_WIDTH; j++){
                //Fill the room with 'X'
                if (myRoomVisitedStatus[i][j] || canBeSeenByVisionPotion(i,j)) {
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            //The wall
                            roomPresentation[i][j][k][l] = '+';
                        }
                    }
                }
                else{
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            //The unvisited room
                            roomPresentation[i][j][k][l] = '?';
                        }
                    }
                }

                //Review the direction the room has access to
                for (int direction = 0; direction < 4; direction++){
                    int x = 1 + DX[direction];
                    int y = 1 + DY[direction];

                    //If the room has access in the corresponding direction
                    if (myMap[i][j].getAccess(direction)){
                        if (myRoomVisitedStatus[i][j] || canBeSeenByVisionPotion(i,j)){
                            roomPresentation[i][j][x][y] = ' ';
//                            //For visibility, add direction
//                            if (direction % 2 == 0)
//                                roomPresentation[i][j][x][y] = ' ';
//                            else roomPresentation[i][j][x][y] = '-';
                        }
                        else{
                            roomPresentation[i][j][x][y] = '?';
                        }
                    }
                }
                if (myMap[i][j].equals(myCurrentRoom)){
                    roomPresentation[i][j][1][1] = 'P';
                }
                else{
                    if (!myRoomVisitedStatus[i][j] && !canBeSeenByVisionPotion(i,j)){
                        roomPresentation[i][j][1][1] = '?';
                    }
                    else{
                        //Visited room
                        roomPresentation[i][j][1][1] = '.';
                    }
                }

            }
        }

        //Collect result from the representation of the room
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < MAP_SIZE_HEIGHT; i++){
            for (int k = 0 ; k < 3; k++){
                int prevSpace = -1;
                boolean addANewLine = false;
                for (int j = 0 ; j < MAP_SIZE_WIDTH ; j++){
                    //Add space to correct the alignment
                    if (myRoomVisitedStatus[i][j] || canBeSeenByVisionPotion(i,j)){
                        res.append(" ".repeat(Math.max(0, (j - 1 - prevSpace) * 3)));
                        prevSpace = j;
                    }
                    for (int l = 0; l < 3; l++){
                        if (roomPresentation[i][j][k][l] != '?') {
                            res.append(roomPresentation[i][j][k][l]);
                            addANewLine = true;
                        }
                    }
                }
                //Only add a new line when there is new content
                if (addANewLine){
                    res.append("\n");
                }

            }
        }
        return res.toString();
    }

    /**
     * Move the player in the according direction. Will not move if the new location is invalid
     * @param theDirection the direction wishes to move the player to (0: North, 1: East, 2: South, 3: West)
     */

    void movePlayer(final int theDirection){
        //Get the currentCoordinate
        int[] currentCoordinate = Room.convertIDtoCoordinate(myCurrentRoom.getID());

        //New coordinate
        int newLocationX = currentCoordinate[0] + DX[theDirection];
        int newLocationY = currentCoordinate[1] + DY[theDirection];
        //If it is a valid location then move the player
        if (checkValid(newLocationX, newLocationY)){
            myCurrentRoom = myMap[newLocationX][newLocationY];
            //Unlocking visited status
            myRoomVisitedStatus[newLocationX][newLocationY] = true;
        }
    }

    /**
     * The code is byte (8 bit) with the bit from 0th to 3rd signify whether the room has access to the corresponding direction
     * 0th is for North, 1st is for East, 2nd is for South, 3rd is for West
     * If the bit is 1, the room has access. If it is 0, the room doesn't have access to that direction.
     * @return int, as the access code
     */
    byte getCurrentRoomAccessCode(){
        return myCurrentRoom.getAccessCode();
    }
    /**
     * The code is byte (8 bit) with the bit from 0th to 3rd signify whether the room has access to the corresponding direction
     * 0th is for North, 1st is for East, 2nd is for South, 3rd is for West
     * If the bit is 1, the room has access. If it is 0, the room doesn't have access to that direction.
     * @param theXPos the desired room's x position
     * @param theYPos the desired room's y position
     * @return int, as the access code
     */
    byte getRoomAccessCode(final int theXPos, final int theYPos){
        return myMap[theXPos][theYPos].getAccessCode();
    }

    /**
     * Width of the map
     * @return an int that is the width of the map
     */
    int getMapSizeWidth() {
        return MAP_SIZE_WIDTH;
    }
    /**
     * Height of the map
     * @return an int that is the height of the map
     */
    int getMapSizeHeight() {
        return MAP_SIZE_HEIGHT;
    }
    /**
     * The room where the player is at
     * @return a Room, where the player currently located in
     */
    Room getMyCurrentRoom(){
        return myCurrentRoom;
    }

    /**
     * Return whether a room is visited or not
     * @param theXPos the desired room's x position
     * @param theYPos the desired room's y position
     * @return a boolean, true if the room has been visited, false otherwise
     */
    boolean getRoomVisitedStatus(final int theXPos, final int theYPos){
        return myRoomVisitedStatus[theXPos][theYPos];
    }

    /**
     * Set the player's position, only used for testing
     * Do nothing if the new location is invalid
     * @param theXPos the player's x position
     * @param theYPos the player's y position
     */
    void setPlayerPosition(final int theXPos, final int theYPos){
        int[] currentCoordinate = Room.convertIDtoCoordinate(getMyCurrentRoom().getID());

        //Check whether the new coordinate is valid
        if (checkValid(theXPos, theYPos)){
            //Set the visited status of the current location to false
            myRoomVisitedStatus[currentCoordinate[0]][ currentCoordinate[1]] = false;
            //Move to the new place
            myCurrentRoom = myMap[theXPos][theYPos];
            myRoomVisitedStatus[theXPos][theYPos] = true;
        }
    }

    /**
     * Looping until the player die or have achieved victory
     */
    static void gameLoop(){
        while (!DungeonAdventure.getInstance().myGameOverStatus || !DungeonAdventure.getInstance().myVictoryStatus){
            //moving
            int userInputDirection = TextBasedGUI_NavigationView.getInstance().promptUserForDirection();
            if (userInputDirection > 0 && userInputDirection <= 4){
                DungeonAdventure.getInstance().movePlayer(userInputDirection - 1);
            }

            if (userInputDirection == 5){

                System.out.println("As we are loading the game, this game loop wil end or the status can be changed here");

                //return;
            }

        }
    }

    /**
     * Experimenting fields
     */

    //These fields will be brought up if tested and implemented

    private boolean canBeSeenByVisionPotion(final int theXPos, final int theYPos){
        //Add an if statement here to check whether healing potion is on or off

        //THis code works but don't merge yet because we don't have buff/debuff
        //offset to track surrounding
        final int[] dx = {-1,  0,  1, 1, 1, 0, -1, -1};
        final int[] dy = {-1, -1, -1, 0, 1, 1, 1,   0};
        int[] currentPlayerPos = Room.convertIDtoCoordinate(myCurrentRoom.getID());

        for (int i = 0; i < 8; i++){
            if (theXPos - dx[i] == currentPlayerPos[0] && theYPos - dy[i] == currentPlayerPos[1])
                return true;
        }

        return false;
    }

    /**
     * Used for loading save game
     */

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        myDungeonAdventureInstance = this;
    }

    private Object readResolve()  {
        return myDungeonAdventureInstance;
    }

}