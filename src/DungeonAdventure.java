/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Is the main logic of the game, similar to Controller in MVC.
 * {@code @author:} Toan Nguyen
 * @version 07 29 2022
 */

public class DungeonAdventure implements Serializable {
    private Room [][] myMap;
    //private Hero myHero; //Comment out because Hero has not been implemented
    private final static int MAP_SIZE_WIDTH = 5;
    private final static int MAP_SIZE_HEIGHT = 5;

    //Serve as a reference for North, East, South, West coordinate
    private final int [] DX = new int[]{-1, 0, 1, 0};
    private final int [] DY = new int[]{ 0, 1, 0, -1};

    //Coordinate for the starting position
    private int myEntranceY;
    private int myEntranceX;

    private Room myCurrentRoom;
    private boolean[][] myRoomVisitedStatus;//True if the room has been visited, false otherwise

    private final static Random RANDOM_SEED = new Random();

    private final TextBasedGUI_MainDisplay myMainDisplayView = TextBasedGUI_MainDisplay.getInstance();
    private final TextBasedGUI_NavigationView myNavigationView = TextBasedGUI_NavigationView.getInstance();
    private final TextBasedGUI_CombatView myCombatView = TextBasedGUI_CombatView.getInstance();

    //Used to track the state of the game
    private boolean gameOver;//Is false if the player has not lost yet
    private boolean victory;//Is false if the player has not won yet

    private final static DungeonAdventure myDungeonAdventureInstance = new DungeonAdventure();

    private DungeonAdventure(){
        //Init the view and attach controller to them
        myMainDisplayView.attachController(this);

        myNavigationView.attachController(this);

        myCombatView.attachController(this);

        myMainDisplayView.displayMainMenu();
    }

    public static DungeonAdventure getInstance(){
        return myDungeonAdventureInstance;
    }

    /**
     * Creating a new game
     * @param theHeroName the player's name
     * @param theHeroClass the player's class
     */

    void createANewGame(final String theHeroName,final int theHeroClass){
        myMap = new Room[MAP_SIZE_HEIGHT][MAP_SIZE_WIDTH];
        myRoomVisitedStatus = new boolean[MAP_SIZE_HEIGHT][MAP_SIZE_WIDTH];
        myEntranceX = RANDOM_SEED.nextInt(MAP_SIZE_WIDTH);
        myEntranceY = RANDOM_SEED.nextInt(MAP_SIZE_HEIGHT);//Randomly selected starting cell
        initMap();
        //Testing outputting world map
        System.out.println(getWorldMapFullVisibility());

        //Testing visibility world map
        System.out.println(getWorldMapWithVisibility());

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
    String getWorldMapWithVisibility(){
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
                if (myRoomVisitedStatus[i][j]) {
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            //The wall
                            roomPresentation[i][j][k][l] = '+';
///                            switch (k){
//                                case (0) -> {
//                                    switch (l) {
//                                        case (0) -> roomPresentation[i][j][k][l] = '|';//'┌';
//                                        case (1) -> roomPresentation[i][j][k][l] = '-';
//                                        case (2) -> roomPresentation[i][j][k][l] = '|';//'┐';
//                                    }
//                                }
//                                case (1) -> {
//                                    switch (l) {
//                                        case (0), (2) -> roomPresentation[i][j][k][l] = '|';
//                                        case (1) -> roomPresentation[i][j][k][l] = '-';
//                                    }
//                                }
//                                case (2) -> {
//                                    switch (l) {
//                                        case (0) -> roomPresentation[i][j][k][l] = '└';
//                                        case (1) -> roomPresentation[i][j][k][l] = '-';
//                                        case (2) -> roomPresentation[i][j][k][l] = '┘';
//                                    }
//                                }
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
                        if (myRoomVisitedStatus[i][j]){
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
                    if (!myRoomVisitedStatus[i][j]){
                        roomPresentation[i][j][1][1] = '?';
                    }
                    else{
                        //Visited room
                        roomPresentation[i][j][1][1] = ' ';
                    }
                }

            }
        }

        //Collect result from the representation of the room
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < MAP_SIZE_HEIGHT; i++){
            for (int k = 0 ; k < 3; k++){
                int prevSpace = 0;
                boolean addANewLine = false;
                for (int j = 0 ; j < MAP_SIZE_WIDTH ; j++){
                    //Add space to correct the alignment
                    if (myRoomVisitedStatus[i][j]){
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

    private void movePlayer(final int theDirection){
        //Get the currentCoordinate
        int currentLocationX = myCurrentRoom.getID() / MAP_SIZE_WIDTH;
        int currentLocationY = myCurrentRoom.getID() % MAP_SIZE_WIDTH;

        //New coordinate
        int newLocationX = currentLocationX + DX[theDirection];
        int newLocationY = currentLocationY + DY[theDirection];
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

    int getMapSizeWidth() {
        return MAP_SIZE_WIDTH;
    }

    int getMapSizeHeight() {
        return MAP_SIZE_WIDTH;
    }


    /**
     * Set the player's position, only used for testing
     * @param theXPos the player's x position
     * @param theYPos the player's y position
     */
    void setPlayerPosition(final int theXPos, final int theYPos){
        //Check whether the new coordinate is valid
        if (checkValid(theXPos, theYPos)){
            myCurrentRoom = myMap[theXPos][theYPos];
        }
    }

    /**
     * Looping until the player die or have achieved victory
     */
    void gameLoop(){
        while (!gameOver || !victory){
            int userInputDirection = myNavigationView.promptUserForDirection();
            movePlayer(userInputDirection);
        }
    }
}
