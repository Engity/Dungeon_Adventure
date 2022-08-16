/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Text based GUI for navigating the rooms
 * {@code @author:} Toan Nguyen
 * @version 08 09 2022
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

    /**
     * Prompt the user for what to do while navigating, mainly direction they wish to go and access to the pause menu
     * @return the user choice
     */
    public int promptUserForDirection(final Hero theHero){
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
        StringBuilder repeatingPrompt = new StringBuilder();
        //Get the inventory
        repeatingPrompt.append("\n").append(parsePlayerInventory(theHero));

        //Start to append the map
        repeatingPrompt.append("\nThe map: (P is the player current location)\n");
        repeatingPrompt.append(DungeonAdventure.getInstance().parseWorldMapWithVisibility());

        //Name of the direction
        final String[] directionName = {"North ^", "East ->", "South v", "West <-"};

        //Create a list of available direction to go and attach it the prompt
        repeatingPrompt.append("Please choose the direction you wishes to go: (0 to go back to pause menu)");
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

            switch (pauseMenuChoice){
                //Load
                case (2) -> {
                    return 5;
                }
                //Return to main menu
                case (3) -> {
                    return 6;
                }
            }
        }

        return userChoice;
    }

    /**
     * Display the pause menu that let the user load\save the game or go back to the main menu
     * @return the user choice
     */

    public int displayPauseMenu(){
        InputChecker pauseMenuSelection = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);

        StringBuilder optionPrompt = new StringBuilder("Please enter your selection: ");
        String [] optionName = {"Resume", "Save game", "Load game", "Return to main menu", "Tutorial", "About", "Exit"};

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
                TextBasedGUI_MainDisplay.getInstance().saveGame();
            }
            //Load
            case(2)->{
                OUTPUT_DESTINATION.println("\nLoading the game\n");
                //Call the load game function
                TextBasedGUI_MainDisplay.getInstance().loadGame(false);
                return 2;
            }
            //Return to main menu
            case(3)->{
                InputChecker yesNoChecker = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);

                yesNoChecker.setMyInitialPrompt("Are you sure, unsaved progress will be lost");
                boolean userConfirm = yesNoChecker.inputCheckForYNConfirmation();

                if (userConfirm){
                    //return to the main menu
                    return 3;
                }
            }

            case (4) -> {
                TextBasedGUI_MainDisplay.getInstance().displayTutorial();
                //Recursive call to loop
                return displayPauseMenu();
            }
            case (5) -> {
                TextBasedGUI_MainDisplay.getInstance().displayAboutInfo();
                //Recursive call to loop
                return displayPauseMenu();
            }

            //Exit
            case (6) ->{
                OUTPUT_DESTINATION.println("Exiting the game!");
                System.exit(0);
            }
        }
        //Display the pause menu again
        return displayPauseMenu();
    }

    /**
     * Asking the player whether they would like to drop off the pillars
     * @return the player's decision
     */
    boolean displayDropOffPillarMenu(){
        InputChecker yesNoChecker = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);
        yesNoChecker.setMyInitialPrompt("You are at the Entrance, would you like to drop off the pillars?");
        boolean userChoice = yesNoChecker.inputCheckForYNConfirmation();

        if (userChoice) {
            OUTPUT_DESTINATION.println("\n~Dropping the pillar~\n");
        }
        return userChoice;
    }

    /**
     * Display the remaining pillar lists
     * @param theRemainPillars list of the name of the remaining pillars
     * @return whether the player's choose to continue or not
     */

    boolean displayRemainPillar(HashSet<String> theRemainPillars, ArrayList<Pillar> theCurrentPillar){
        InputChecker remainPillarChecker = new InputChecker(INPUT_SOURCE, OUTPUT_DESTINATION);

        StringBuilder repeatingPrompt = new StringBuilder("""
                Dropping off these pillars: 
                """);

        int i = 0;
        for (var item : theCurrentPillar){
            repeatingPrompt.append("\t").append(++i).append(". ");
            repeatingPrompt.append(item.getMyItemName()).append("\n");
        }
        if (!theRemainPillars.isEmpty()) {
            repeatingPrompt.append("Collect all 4 pillars to win the game. The remaining pillars are:\n");
            i = 0;
            for (var item : theRemainPillars) {
                repeatingPrompt.append("\t").append(++i).append(". ");
                repeatingPrompt.append(item).append("\n");
            }
        }

        repeatingPrompt.append("Press Enter key to continue.");

        remainPillarChecker.setMyRepeatingPrompt(repeatingPrompt.toString());
        return remainPillarChecker.inputAnyKeyToContinue();
    }

    /**
     * Parse the player inventory
     *  Display the pillar they have
     *  Display the buff they have
     */
    String parsePlayerInventory(final Hero theHero){
        StringBuilder res = new StringBuilder("Current inventory: ");
        var pillars = theHero.getPillars();

        if (pillars.isEmpty()){
            res.append("\n\tYou don't have any pillar right now.");
        }else{
            for (int i = 0 ; i < pillars.size(); i++){
                res.append("\n\t").append(i + 1).append(". ");
                res.append(pillars.get(i).getMyItemName());
                res.append(".");
            }
        }
        if (pillars.size() == 4){
            res.append("\nYou have all the pillars. Bring it to the entrance to win the game!\n");
        }

        if (theHero.getMyVisionBuff().getDuration() != 0){
            res.append("\nYou have the item \"");
            res.append(theHero.getMyVisionBuff().getMyItemName());
            res.append("\" for the duration ").append(theHero.getMyVisionBuff().getDuration());
            res.append(".\n");
        }

        return res.toString();
    }

}

