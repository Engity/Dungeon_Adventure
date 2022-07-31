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
    void attachController(final DungeonAdventure theGameController){
        myGameController = theGameController;
    }
    private static final TextBasedGUI_NavigationView myNavigationViewInstance = new TextBasedGUI_NavigationView();

    public static TextBasedGUI_NavigationView getInstance(){
        return myNavigationViewInstance;
    }

    public int promptUserForDirection(){
        int theCurrentRoomAccess = myGameController.getCurrentRoomAccessCode();

        int userChoice;
        InputChecker directionChecker = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);

        directionChecker.setMyInitialPrompt("""
                .__   __.      ___   ____    ____  __    _______      ___   .___________. __    ______   .__   __.\s
                |  \\ |  |     /   \\  \\   \\  /   / |  |  /  _____|    /   \\  |           ||  |  /  __  \\  |  \\ |  |\s
                |   \\|  |    /  ^  \\  \\   \\/   /  |  | |  |  __     /  ^  \\ `---|  |----`|  | |  |  |  | |   \\|  |\s
                |  . `  |   /  /_\\  \\  \\      /   |  | |  | |_ |   /  /_\\  \\    |  |     |  | |  |  |  | |  . `  |\s
                |  |\\   |  /  _____  \\  \\    /    |  | |  |__| |  /  _____  \\   |  |     |  | |  `--'  | |  |\\   |\s
                |__| \\__| /__/     \\__\\  \\__/     |__|  \\______| /__/     \\__\\  |__|     |__|  \\______/  |__| \\__|
                """);

        //Add the map to repeating prompt
        StringBuilder repeatingPrompt = new StringBuilder("Current Location: \n");
        repeatingPrompt.append(myGameController.parseWorldMapWithVisibility());

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
