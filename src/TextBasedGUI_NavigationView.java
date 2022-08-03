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
    
    private TextBasedGUI_NavigationView(){

    }
    
    private static final TextBasedGUI_NavigationView myNavigationViewInstance = new TextBasedGUI_NavigationView();

    public static TextBasedGUI_NavigationView getInstance(){
        return myNavigationViewInstance;
    }

    public int promptUserForDirection(){
        int theCurrentRoomAccess = DungeonAdventure.getInstance().getCurrentRoomAccessCode();

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
        repeatingPrompt.append(DungeonAdventure.getInstance().parseWorldMapWithVisibility());

        //Name of the direction
        final String[] directionName = {"North ^", "East ->", "South v", "West <-"};

        //Create a list of available direction to go and attach it the prompt
        repeatingPrompt.append("Please choose the direction you wishes to go: (0 to go back to pause menu)");
        HashMap<Integer, Integer> availableDirections = new HashMap<>();
        for (int direction = 0; direction < 4; direction++){
            //If the room has access to the corresponding direction, add it to the map
            if (((theCurrentRoomAccess >> direction) & 1) == 1){
                repeatingPrompt.append("\n");
                //Add options to the repeating prompt
                repeatingPrompt.append(direction + 1).append(". ");
                repeatingPrompt.append(directionName[direction]);
            }
            else{
                //Add the direction that does not have access to the
                directionChecker.addToMyForbiddenNumbers(direction + 1);
            }
        }

        directionChecker.setBound(0, 4);


        //Setting prompts
        directionChecker.setMyRepeatingPrompt(repeatingPrompt.toString());
        directionChecker.setMyWrongRangePrompt("There is no the direction corresponding to the index you just inputted, please try again");
        directionChecker.setMyErrorPrompt("Wrong format, please input numbers only!");

        userChoice = directionChecker.inputCheckForNumber();

        //Launch pause menu
        if (userChoice == 0){
            int pauseMenuChoice = displayPauseMenu();
            if (pauseMenuChoice == 2){
                return 5;
            }
        }

        return userChoice;
    }

    public int displayPauseMenu(){
        InputChecker pauseMenuSelection = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);

        StringBuilder optionPrompt = new StringBuilder("Please enter your selection: ");
        String [] optionName = {"Resume", "Save game", "Load game", "Return to main menu", "Exit"};

        //Attach option names to the prompt
        for (int i = 0; i < optionName.length; i++){
            optionPrompt.append("\n\t");
            optionPrompt.append(i).append(". ");
            optionPrompt.append(optionName[i]);
        }
        pauseMenuSelection.setBound(0, optionName.length - 1);
        pauseMenuSelection.setMyRepeatingPrompt(optionPrompt.toString());
        pauseMenuSelection.setMyWrongRangePrompt("There is no the option corresponding to the index you just inputted, please try again");
        pauseMenuSelection.setMyErrorPrompt("Wrong format, please input numbers only!");

        int userChoice = pauseMenuSelection.inputCheckForNumber();

        switch (userChoice){
            //Resume
            case (0)->{
                OUTPUT_DESTINATION.println("\nResuming\n");
                return 0;
            }
            //Save
            case(1)->{
                OUTPUT_DESTINATION.println("\nSaving the game\n");
                //Call the save game function
                System.out.println("CALL THE SAVE GAME FUNCTION HERE");
                TextBasedGUI_MainDisplay.getInstance().saveGame();
            }
            //Load
            case(2)->{
                OUTPUT_DESTINATION.println("\nLoading the game\n");
                //Call the load game function
                System.out.println("CALL THE LOAD GAME FUNCTION HERE");
                TextBasedGUI_MainDisplay.getInstance().loadGame();
                return 2;
            }
            //Return to main menu
            case(3)->{
                InputChecker yesNoChecker = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);

                yesNoChecker.setMyInitialPrompt("Are you sure, unsaved progress will be lost");
                boolean userConfirm = yesNoChecker.inputCheckForYNConfirmation();

                if (userConfirm){
                    //return to the main menu
                    TextBasedGUI_MainDisplay.getInstance().displayMainMenu();
                    return 0;
                }
            }

            //Exit
            case (4) ->{
                OUTPUT_DESTINATION.println("Exiting the game!");
                System.exit(0);
            }
        }
        //Display the pause menu again
        return displayPauseMenu();
    }


}
