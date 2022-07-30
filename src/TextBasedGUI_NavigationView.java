/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;

/**
 * Text based GUI for navigating the rooms
 * {@code @author:} Toan Nguyen
 * @version 07 29 2022
 */
public class TextBasedGUI_NavigationView {
    /**
     * The source to input from.
     * Default is System.in
     */
    private static final InputStream INPUT_SOURCE = System.in;

    /**
     * The destination to output to.
     * Default is System.out
     */
    private static final PrintStream OUTPUT_DESTINATION = System.out;
    private DungeonAdventure myGameController;
    private TextBasedGUI_NavigationView(){

    }
    private static final TextBasedGUI_NavigationView myNavigationViewInstance = new TextBasedGUI_NavigationView();

    public static TextBasedGUI_NavigationView getInstance(){
        return myNavigationViewInstance;
    }

    public void attachController(final DungeonAdventure theGameController){
        myGameController = theGameController;
    }

//    String getWorldMapWithVisibility() {
//        //a 3x3 char matrix for each room, representing access of the room and whether it has been visited or not
//        //Exp: If player is in Room (0,0), and it has access to the East and South
//        //          'X', 'X', 'X',
//        //          'X', 'P', ' ',
//        //          'X', ' ', 'X',
//        //Exp: If player has not visited this room, room (0,0) is:
//        //          'X', 'X', 'X',
//        //          'X', '?', '?',
//        //          'X', '?', 'X',
//
//        char[][][][] roomPresentation = new char[MAP_SIZE_HEIGHT][MAP_SIZE_WIDTH][3][3];
//        for (int i = 0; i < MAP_SIZE_HEIGHT; i++) {
//            for (int j = 0; j < MAP_SIZE_WIDTH; j++) {
//                //Fill the room with 'X'
//                if (myRoomVisitedStatus[i][j]) {
//                    for (int k = 0; k < 3; k++) {
//                        for (int l = 0; l < 3; l++) {
//                            //The wall
//                            roomPresentation[i][j][k][l] = '+';
/////                            switch (k){
////                                case (0) -> {
////                                    switch (l) {
////                                        case (0) -> roomPresentation[i][j][k][l] = '|';//'┌';
////                                        case (1) -> roomPresentation[i][j][k][l] = '-';
////                                        case (2) -> roomPresentation[i][j][k][l] = '|';//'┐';
////                                    }
////                                }
////                                case (1) -> {
////                                    switch (l) {
////                                        case (0), (2) -> roomPresentation[i][j][k][l] = '|';
////                                        case (1) -> roomPresentation[i][j][k][l] = '-';
////                                    }
////                                }
////                                case (2) -> {
////                                    switch (l) {
////                                        case (0) -> roomPresentation[i][j][k][l] = '└';
////                                        case (1) -> roomPresentation[i][j][k][l] = '-';
////                                        case (2) -> roomPresentation[i][j][k][l] = '┘';
////                                    }
////                                }
//                        }
//                    }
//                } else {
//                    for (int k = 0; k < 3; k++) {
//                        for (int l = 0; l < 3; l++) {
//                            //The unvisited room
//                            roomPresentation[i][j][k][l] = '?';
//                        }
//                    }
//                }
//
//                //Review the direction the room has access to
//                for (int direction = 0; direction < 4; direction++) {
//                    int x = 1 + DX[direction];
//                    int y = 1 + DY[direction];
//
//                    //If the room has access in the corresponding direction
//                    if (myMap[i][j].getAccess(direction)) {
//                        if (myRoomVisitedStatus[i][j]) {
//                            roomPresentation[i][j][x][y] = ' ';
////                            //For visibility, add direction
////                            if (direction % 2 == 0)
////                                roomPresentation[i][j][x][y] = ' ';
////                            else roomPresentation[i][j][x][y] = '-';
//                        } else {
//                            roomPresentation[i][j][x][y] = '?';
//                        }
//                    }
//                }
//                if (myMap[i][j].equals(myCurrentRoom)) {
//                    roomPresentation[i][j][1][1] = 'P';
//                } else {
//                    if (!myRoomVisitedStatus[i][j]) {
//                        roomPresentation[i][j][1][1] = '?';
//                    } else {
//                        //Visited room
//                        roomPresentation[i][j][1][1] = 'o';
//                    }
//                }
//
//            }
//        }
//    }

    public int promptUserForDirection(){
        int theCurrentRoomAccess = myGameController.getCurrentRoomAccessCode();


        int userChoice;
        InputChecker directionChecker = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);

        //Add the map to repeating prompt
        StringBuilder repeatingPrompt = new StringBuilder("Current Location: \n");
        repeatingPrompt.append(myGameController.getWorldMapWithVisibility());

        //Name of the direction
        final String[] directionName = {"North ^", "East ->", "South v", "West <-"};

        //Create a list of available direction to go and attach it the prompt
        repeatingPrompt.append("Please choose the direction you wishes to go: (0 to go back to pause menu)");
        HashMap<Integer, Integer> availableDirections = new HashMap<>();
        for (int direction = 0; direction < 4; direction++){
            //If the room has access to the corresponding direction, add it to the map
            if (((theCurrentRoomAccess >> direction) & 1) == 1){
//                availableDirections.put(availableDirections.size() + 1, direction);
//                //Attach to the display prompt
//                repeatingPrompt.append("\n");
//                repeatingPrompt.append(availableDirections.size());
//                repeatingPrompt.append(". ").append(directionName[direction]);
                repeatingPrompt.append("\n");
                repeatingPrompt.append(direction + 1).append(". ");
                repeatingPrompt.append(directionName[direction]);
            }
            else{
                //Add the direction that does not have access to the
                directionChecker.addToMyForbiddenNumbers(direction + 1);
            }
        }

        directionChecker.setBound(0, 4);



        //Add options to the repeating prompt

        //Setting prompts
        directionChecker.setMyRepeatingPrompt(repeatingPrompt.toString());
        directionChecker.setMyWrongRangePrompt("There is no the direction corresponding to the index you just inputted, please try again");
        directionChecker.setMyErrorPrompt("Wrong format, please input numbers only!");

        userChoice = directionChecker.inputCheckForNumber() - 1;

        //Launch pause menu
        if (userChoice == -1){
            //Will replace main menu with pause menu
            TextBasedGUI_MainDisplay.getInstance().displayMainMenu();
        }
        return userChoice;
    }
}
